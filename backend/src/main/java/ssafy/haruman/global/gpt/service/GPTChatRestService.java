package ssafy.haruman.global.gpt.service;

import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionChatRequest;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionRequest;
import ssafy.haruman.global.gpt.dto.response.CompletionChatResponse;
import ssafy.haruman.global.gpt.dto.response.CompletionResponse;

@Service
@RequiredArgsConstructor
public class GPTChatRestService {

    private final OpenAiService openAiService;

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

    @Transactional
    public CompletionChatResponse GPT(String productDescriptions) {
        GPTCompletionChatRequest gptCompletionChatRequest = new GPTCompletionChatRequest(
                "gpt-3.5-turbo", "user", productDescriptions, 1000);
        CompletionChatResponse completionChatResponse = completionChat(gptCompletionChatRequest);
        return completionChatResponse;
    }
}
