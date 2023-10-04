package ssafy.haruman.global.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/*
 * Chat GPT 라이브러리를 사용하기전, 해당 서비스에 토큰 주입을 하기위한 Config
 * API Key 발급은 https://platform.openai.com/account/api-keys 에서 발급받을 수 있습니다.
 */
@Configuration
public class ChatGPTConfig {

    @Value("${gpt.token}")
    private String token;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(token, Duration.ofSeconds(90));
    }
}
