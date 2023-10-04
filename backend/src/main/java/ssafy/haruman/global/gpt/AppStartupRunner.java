package ssafy.haruman.global.gpt;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ssafy.haruman.global.gpt.dto.request.GPTCompletionChatRequest;
import ssafy.haruman.global.gpt.dto.response.CompletionChatResponse;
import ssafy.haruman.global.gpt.service.GPTChatRestService;
import ssafy.haruman.global.gpt.vo.BankProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final GPTChatRestService gptChatRestService;

    private final String FILE_NAME = "bank_products.json";
    private final int LIMIT_LENGTH = 1500;

    public void GPT(String productDescriptions) {
        GPTCompletionChatRequest gptCompletionChatRequest = new GPTCompletionChatRequest(
                "gpt-3.5-turbo", "user", productDescriptions, 1000);
        CompletionChatResponse response = gptChatRestService.completionChat(gptCompletionChatRequest);
        System.out.printf(response.getMessages().get(0).getMessage());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BankProduct> bankProductList = parseJsonFileToBankProductList();

        int accumulatedLength = 0;
        StringBuilder accumulatedString = new StringBuilder();
        for (BankProduct bankProduct : bankProductList) {
            int currentLength = bankProduct.toString().length();
            if (accumulatedLength + currentLength < LIMIT_LENGTH) {
                accumulatedLength += currentLength;
                accumulatedString.append(bankProduct);
            } else {
                GPT(accumulatedString.toString());
                accumulatedLength = currentLength;
                accumulatedString = new StringBuilder().append(bankProduct);
            }
        }
        log.info("GPT에 적금 입력 완료");
    }

    private List<BankProduct> parseJsonFileToBankProductList() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:" + FILE_NAME);
        FileReader fileReader = new FileReader(file);
        Gson gson = new Gson();
        BankProduct[] array = gson.fromJson(fileReader, BankProduct[].class);
        return Arrays.asList(array);
    }
}