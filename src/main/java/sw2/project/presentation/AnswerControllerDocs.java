package sw2.project.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sw2.project.presentation.dto.AnswerCreateRequest;
import sw2.project.presentation.dto.AnswerResponse;

import java.util.List;

@Tag(name = "Answer API", description = "질문에 대한 답변(Answer) 관련 API")
public interface AnswerControllerDocs {

    @Operation(summary = "특정 질문에 대한 답변 목록 조회", description = "특정 질문 ID에 달린 모든 답변의 목록을 조회합니다.")
    ResponseEntity<List<AnswerResponse>> getAnswersForQuestion(
            @Parameter(description = "답변 목록을 조회할 질문의 ID") @PathVariable Long questionId);

    @Operation(summary = "특정 질문에 답변 생성", description = "특정 질문에 대한 새로운 답변을 등록합니다.")
    ResponseEntity<AnswerResponse> createAnswer(
            @Parameter(description = "답변을 등록할 질문의 ID") @PathVariable Long questionId,
            @Valid @RequestBody AnswerCreateRequest request);
}
