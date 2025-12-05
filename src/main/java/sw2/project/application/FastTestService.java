package sw2.project.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sw2.project.domain.FastTestRecord;
import sw2.project.extern.openai.dto.OpenAIChatRequest;
import sw2.project.extern.openai.dto.OpenAIChatResponse;
import sw2.project.infra.FastTestRecordRepository;
import sw2.project.presentation.dto.FastTestRequest;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FastTestService {

    private final FastTestRecordRepository recordRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    /**
     * FAST 테스트를 실행하고, OpenAI API를 호출하여 결과를 반환하며, 결과를 저장합니다.
     */
    @Transactional
    public JsonNode runFastTest(FastTestRequest request) {
        // 1. OpenAI API 호출하여 분석 결과 생성
        JsonNode analysisResult = callOpenAIForAnalysis(request);

        // 2. 결과 저장을 위해 JsonNode를 String으로 변환
        String resultString;
        try {
            resultString = objectMapper.writeValueAsString(analysisResult);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize test result to JSON string.", e);
        }

        // 3. 분석 기록 생성 및 저장
        FastTestRecord record = FastTestRecord.builder()
                .userId(request.getUserId())
                .result(resultString)
                .build();
        recordRepository.save(record);

        // 4. 분석 결과(JsonNode) 반환
        return analysisResult;
    }

    /**
     * FAST 테스트 분석을 위해 OpenAI API를 호출하는 메서드
     * @param request 사용자의 입력 데이터
     * @return 분석 결과 (JSON 형식)
     */
    private JsonNode callOpenAIForAnalysis(FastTestRequest request) {
        // 1. 프롬프트 생성
        String prompt = buildPrompt(request);

        // 2. OpenAI API 요청 생성
        OpenAIChatRequest.Message userMessage = new OpenAIChatRequest.Message("user", prompt);
        OpenAIChatRequest openAIRequest = OpenAIChatRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(userMessage))
                .maxTokens(500) // 응답 길이를 넉넉하게 설정
                .build();

        // 3. HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openaiApiKey);

        // 4. API 호출
        HttpEntity<OpenAIChatRequest> entity = new HttpEntity<>(openAIRequest, headers);
        OpenAIChatResponse openAIResponse = restTemplate.postForObject(OPENAI_API_URL, entity, OpenAIChatResponse.class);

        // 5. 응답 파싱 및 JsonNode로 변환
        try {
            String aiReply = (openAIResponse != null) ? openAIResponse.firstContent() : "{}";
            return objectMapper.readTree(aiReply);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse OpenAI response to JSON.", e);
        }
    }

    private String buildPrompt(FastTestRequest request) {
        return String.format("""
            You are a helpful medical assistant AI. A user has submitted results from a FAST (Face, Arm, Speech, Time) test for stroke symptoms. Based on the following user-provided information, please provide a preliminary analysis and recommendation.
            
            **IMPORTANT**: Please write your entire response in Korean.
            
            Return your response ONLY as a JSON object with the following structure: {"summary": "<one-sentence summary>", "details": "<detailed analysis>", "recommendations": ["<recommendation 1>", "<recommendation 2>"], "isEmergency": <true or false>}.

            User's symptoms:
            - Face: %s
            - Arm: %s
            - Speech: %s
            - Time: %s

            If the symptoms strongly indicate a stroke or other medical emergency, set 'isEmergency' to true and make the first recommendation 'Call emergency services immediately' (in Korean: '즉시 응급 서비스에 전화하세요').
            """,
                request.getFace(),
                request.getArm(),
                request.getSpeech(),
                request.getTime()
        );
    }
}
