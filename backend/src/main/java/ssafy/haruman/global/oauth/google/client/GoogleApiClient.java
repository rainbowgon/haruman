package ssafy.haruman.global.oauth.google.client;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ssafy.haruman.global.oauth.google.dto.GoogleMemberResponse;
import ssafy.haruman.global.oauth.google.dto.GoogleToken;
import ssafy.haruman.global.utils.WebClientUtil;

import java.util.List;
import java.util.Map;

@Component
public class GoogleApiClient {

    private final String GOOGLE_BASE_URL = "https://oauth2.googleapis.com/token";
    private final String GOOGLE_USER_URL = "https://www.googleapis.com/oauth2/v1/userinfo";

    public GoogleToken fetchToken(MultiValueMap<String, String> params) {
        WebClient googleOAuthWebClient = WebClientUtil.createWebClient(GOOGLE_BASE_URL);
        String uri = parseMapToUri(params);

        GoogleToken googleToken = googleOAuthWebClient.post()
                .uri(uri)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(GoogleToken.class)
                .block();

        if (googleToken == null) {
            throw new RuntimeException("구글 access token을 받아오지 못함.");
        }

        return googleToken;
    }

    public GoogleMemberResponse fetchMember(String bearerToken) {
        WebClient googleUserDetailClient = WebClientUtil.createWebClient(GOOGLE_USER_URL);

        GoogleMemberResponse googleMemberResponse = googleUserDetailClient.get()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(GoogleMemberResponse.class)
                .block();

        if (googleMemberResponse == null) {
            throw new RuntimeException("구글 사용자 정보를 받아오지 못함");
        }

        return googleMemberResponse;
    }

    private String parseMapToUri(MultiValueMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                sb.append(key)
                        .append("=")
                        .append(value)
                        .append("&");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
