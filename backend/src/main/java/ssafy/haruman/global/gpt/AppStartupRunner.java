package ssafy.haruman.global.gpt;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ssafy.haruman.global.gpt.service.GPTChatRestService;
import ssafy.haruman.global.gpt.vo.BankProduct;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final GPTChatRestService gptChatRestService;

    private final String FILE_NAME = "bank_products.json";
    private final int LIMIT_LENGTH = 2000;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<BankProduct> bankProductList = parseJsonFileToBankProductList();
        sendBankProductListToGPT(bankProductList);
        log.info("GPT에 적금 입력 완료");
    }

    private void sendBankProductListToGPT(List<BankProduct> bankProductList) {
        int accumulatedLength = 0;
        StringBuilder accumulatedString = new StringBuilder();
        for (BankProduct bankProduct : bankProductList) {
            int currentLength = bankProduct.toString().length();
            if (accumulatedLength + currentLength < LIMIT_LENGTH) {
                accumulatedLength += currentLength;
                accumulatedString.append(bankProduct);
            } else {
                gptChatRestService.GPT(accumulatedString.toString());
                accumulatedLength = currentLength;
                accumulatedString = new StringBuilder().append(bankProduct);
            }
        }

        if (accumulatedLength != 0) {
            gptChatRestService.GPT(accumulatedString.toString());
        }
    }

    private List<BankProduct> parseJsonFileToBankProductList() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(FILE_NAME);
        InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream(), "UTF-8");
        Gson gson = new Gson();
        BankProduct[] array = gson.fromJson(inputStreamReader, BankProduct[].class);
        return Arrays.asList(array);
    }
}