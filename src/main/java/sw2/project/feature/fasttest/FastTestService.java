package sw2.project.feature.fasttest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw2.project.domain.fasttest.FastTestRecord;
import sw2.project.domain.fasttest.FastTestRecordRepository;
import sw2.project.feature.fasttest.dto.FastTestRequest;

@Service
@RequiredArgsConstructor
public class FastTestService {

    private final FastTestRecordRepository recordRepository;
    private final ObjectMapper objectMapper; // JSON 처리를 위해 주입

    /**
     * FAST 테스트를 실행하고, Mock 결과를 반환하며, 결과를 저장합니다.
     */
    @Transactional
    public JsonNode runFastTest(FastTestRequest request) {
        // 1. Mock 분석 결과 생성
        JsonNode mockResult = generateMockAnalysis(request.getUserInput());

        // 2. 결과 저장을 위해 JsonNode를 String으로 변환
        String resultString;
        try {
            resultString = objectMapper.writeValueAsString(mockResult);
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
        return mockResult;
    }

    /**
     * FAST 테스트 분석을 대체하는 Mock 메서드
     * @param userInput 사용자의 입력 데이터
     * @return 모의 분석 결과 (JSON 형식)
     */
    private JsonNode generateMockAnalysis(Object userInput) {
        // 실제로는 userInput을 기반으로 복잡한 분석을 수행해야 함
        String jsonString = """
        {
          "summary": "당신은 '안정적인 전략가' 유형입니다.",
          "details": "변화에 대한 저항이 적고, 계획에 따라 움직이는 것을 선호합니다. 데이터 기반의 논리적인 의사결정을 내리는 경향이 있습니다.",
          "recommendations": [
            "장기적인 관점의 목표 설정하기",
            "주기적으로 새로운 기술이나 지식 학습하기",
            "팀 프로젝트에서 계획 수립 역할 맡기"
          ],
          "scores": {
            "Flexibility": 78,
            "Adaptability": 85,
            "Strategy": 92,
            "Tenacity": 88
          }
        }
        """;

        try {
            // ObjectMapper를 사용하여 JSON 문자열을 JsonNode 객체로 변환
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create mock analysis JSON.", e);
        }
    }
}
