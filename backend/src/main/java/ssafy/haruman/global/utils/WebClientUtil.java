package ssafy.haruman.global.utils;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientUtil {

    public static WebClient createWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
