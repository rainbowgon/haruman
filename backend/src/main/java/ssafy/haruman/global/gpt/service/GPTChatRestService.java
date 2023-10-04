package ssafy.haruman.global.gpt.service;

import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.haruman.global.config.ChatGPTConfig;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionChatRequest;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionRequest;
import ssafy.haruman.global.gpt.dto.response.CompletionChatResponse;
import ssafy.haruman.global.gpt.dto.response.CompletionResponse;

import java.time.Duration;

@Service
public class GPTChatRestService {

    private OpenAiService openAiService;

    public GPTChatRestService(ChatGPTConfig chatGPTConfig) {
        this.openAiService = new OpenAiService(chatGPTConfig.getToken(), Duration.ofSeconds(120));
    }

    @Transactional
    public CompletionResponse completion(final GPTCompletionRequest restRequest) {
        CompletionResult result = openAiService.createCompletion(GPTCompletionRequest.of(restRequest));
        return CompletionResponse.of(result);
    }

    @Transactional
    public CompletionChatResponse completionChat(GPTCompletionChatRequest gptCompletionChatRequest) {
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
                GPTCompletionChatRequest.of(gptCompletionChatRequest));
        return CompletionChatResponse.of(chatCompletion);
    }
}
