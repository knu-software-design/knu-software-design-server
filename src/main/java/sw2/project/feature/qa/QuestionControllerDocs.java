package sw2.project.feature.qa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.feature.qa.dto.AnswerCreateRequest;
import sw2.project.feature.qa.dto.AnswerResponse;
import sw2.project.feature.qa.dto.QuestionCreateRequest;
import sw2.project.feature.qa.dto.QuestionResponse;

import java.util.List;

@Tag(name = "Q&A API", description = "전문가 Q&A 게시판 CRUD API")
public interface QuestionControllerDocs {

    @Operation(summary = "질문 생성", description = "새로운 질문을 등록합니다.")
    ResponseEntity<QuestionResponse> createQuestion(@Valid @RequestBody QuestionCreateRequest request);

    @Operation(summary = "모든 질문 목록 조회", description = "등록된 모든 질문의 목록을 조회합니다.")
    ResponseEntity<List<QuestionResponse>> getAllQuestions();

    @Operation(summary = "특정 질문 상세 조회", description = "질문 ID를 이용하여 특정 질문과 하위 답변들을 함께 조회합니다.")
    ResponseEntity<QuestionResponse> getQuestionById(
            @Parameter(description = "조회할 질문의 ID") @PathVariable Long questionId);

    @Operation(summary = "특정 질문에 답변 생성", description = "특정 질문에 대한 새로운 답변을 등록합니다.")
    ResponseEntity<AnswerResponse> createAnswer(
            @Parameter(description = "답변을 등록할 질문의 ID") @PathVariable Long questionId,
            @Valid @RequestBody AnswerCreateRequest request);
}
