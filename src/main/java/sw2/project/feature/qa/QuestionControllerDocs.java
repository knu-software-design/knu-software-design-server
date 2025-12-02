package sw2.project.feature.qa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.feature.qa.dto.QuestionCreateRequest;
import sw2.project.feature.qa.dto.QuestionResponse;

import java.util.List;

@Tag(name = "Question API", description = "Q&A 게시판의 질문(Question) 관련 API")
public interface QuestionControllerDocs {

    @Operation(summary = "질문 생성", description = "새로운 질문을 등록합니다.")
    ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody QuestionCreateRequest request);

    @Operation(summary = "모든 질문 목록 조회", description = "등록된 모든 질문의 목록을 조회합니다.")
    ResponseEntity<List<QuestionResponse>> getAllQuestions();

    @Operation(summary = "특정 질문 상세 조회", description = "질문 ID를 이용하여 답변을 제외한 특정 질문의 정보만 조회합니다.")
    ResponseEntity<QuestionResponse> getQuestionById(
            @Parameter(description = "조회할 질문의 ID") @PathVariable Long questionId);
}
