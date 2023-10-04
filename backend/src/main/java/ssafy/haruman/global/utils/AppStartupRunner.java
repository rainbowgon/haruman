package ssafy.haruman.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionChatRequest;
import ssafy.haruman.global.gpt.dto.response.CompletionChatResponse;
import ssafy.haruman.global.gpt.service.GPTChatRestService;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private final GPTChatRestService gptChatRestService;

    public void GPT(String productDescriptions) {
        GPTCompletionChatRequest gptCompletionChatRequest = new GPTCompletionChatRequest(
                "gpt-3.5-turbo", "user", productDescriptions, 1000);
        CompletionChatResponse response = gptChatRestService.completionChat(gptCompletionChatRequest);
        System.out.printf(response.getMessages().get(0).getMessage());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String productDescriptions = FileUtil.readFile(".\\backend\\src\\main\\resources\\savings_products.txt");

        if (productDescriptions.length() > 2500) {
            int x = productDescriptions.length() / 2500;
            for (int i = 0; i < x + 1; i++) {
                if (i != x) {
                    String departDescription = productDescriptions.substring(i * 2500, (i * 2500) + 2500);
                    GPT(departDescription);
                } else {
                    String departDescription = productDescriptions.substring(i * 2500, productDescriptions.length());
                    GPT(departDescription);
                }
            }
        } else {
            GPT(productDescriptions);
        }

    }
}