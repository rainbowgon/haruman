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
@Transactional(readOnly = true)
public class GPTChatRestService {
  private final OpenAiService openAiService;

  @Transactional
  public CompletionResponse completion(final GPTCompletionRequest restRequest) {
    CompletionResult result = openAiService.createCompletion(GPTCompletionRequest.of(restRequest));
    CompletionResponse response = CompletionResponse.of(result);


    return response;
  }

  @Transactional
  public CompletionChatResponse completionChat(GPTCompletionChatRequest gptCompletionChatRequest) {
    ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
        GPTCompletionChatRequest.of(gptCompletionChatRequest));

    CompletionChatResponse response = CompletionChatResponse.of(chatCompletion);


    return response;
  }
}
