package ssafy.haruman.domain.deposit.controller;

import ssafy.haruman.domain.deposit.dto.request.GPTCompletionChatRequest;
import ssafy.haruman.domain.deposit.dto.response.CompletionChatResponse;
import ssafy.haruman.domain.deposit.dto.response.CompletionResponse;
import ssafy.haruman.domain.deposit.dto.request.GPTCompletionRequest;
import ssafy.haruman.domain.deposit.service.GPTChatRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit")
@RequiredArgsConstructor
public class DeopositController {

    private final GPTChatRestService gptChatRestService;

    @PostMapping("/completion")
    public CompletionResponse completion(final @RequestBody GPTCompletionRequest gptCompletionRequest) {

        return gptChatRestService.completion(gptCompletionRequest);
    }

    @PostMapping("/completion/chat")
    public CompletionChatResponse completionChat(final @RequestBody GPTCompletionChatRequest gptCompletionChatRequest) {

        return gptChatRestService.completionChat(gptCompletionChatRequest);
    }

}