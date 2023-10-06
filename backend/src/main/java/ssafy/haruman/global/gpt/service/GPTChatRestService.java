package ssafy.haruman.global.gpt.service;

import com.google.gson.Gson;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionChatRequest;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionRequest;
import ssafy.haruman.global.gpt.dto.response.CompletionChatResponse;
import ssafy.haruman.global.gpt.dto.response.CompletionResponse;
import ssafy.haruman.global.gpt.vo.BankProduct;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GPTChatRestService {

    private final OpenAiService openAiService;

    private final String FILE_NAME = "bank_products.json";


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
        long beforeTime = System.currentTimeMillis();

        GPTCompletionChatRequest gptCompletionChatRequest = new GPTCompletionChatRequest(
                "gpt-4", "user", productDescriptions, 1000);
        CompletionChatResponse completionChatResponse = completionChat(gptCompletionChatRequest);

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime) / 1000;
        log.info("GPT 응답 시간 [{} sec] 소요", secDiffTime);
        return completionChatResponse;
    }

    public StringBuilder sendBankProductListToGPT(List<BankProduct> bankProductList) {
        int accumulatedLength = 0;
        StringBuilder accumulatedString = new StringBuilder();
        for (BankProduct bankProduct : bankProductList) {
            int currentLength = bankProduct.toString().length();
            accumulatedLength += currentLength;
            accumulatedString.append(bankProduct);

        }

//        if (accumulatedLength != 0) {
//            GPT(accumulatedString.toString());
        return accumulatedString;
//        }
    }

    public List<BankProduct> parseJsonFileToBankProductList() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(FILE_NAME);
        InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        BankProduct[] array = gson.fromJson(inputStreamReader, BankProduct[].class);
        return Arrays.asList(array);
    }
}
