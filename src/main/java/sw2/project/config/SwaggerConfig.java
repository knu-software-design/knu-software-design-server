package sw2.project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "프로젝트 API 명세서",
                description = "대화형 코칭, 전문가 Q&A, 그룹 챌린지, FAST 테스트 기능을 제공하는 API 문서입니다.",
                version = "v1.0.0"
        )
)
@Configuration
public class SwaggerConfig {
}
