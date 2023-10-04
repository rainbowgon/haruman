package ssafy.haruman.global.oauth.kakao.client;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ssafy.haruman.global.error.exception.KakaoAccessTokenFailureException;
import ssafy.haruman.global.error.exception.KakaoUserInfoFailureException;
import ssafy.haruman.global.oauth.kakao.dto.KakaoMemberResponse;
import ssafy.haruman.global.oauth.kakao.dto.KakaoToken;
import ssafy.haruman.global.utils.WebClientUtil;

import java.util.List;
import java.util.Map;

@Component
public class KakaoApiClient {

    private final String KAKAO_BASE_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_USER_URL = "https://kapi.kakao.com/v2/user/me";

    public KakaoToken fetchToken(MultiValueMap<String, String> params) {
        WebClient kakaoOAuthWebClient = WebClientUtil.createWebClient(KAKAO_BASE_URL);
        String uri = parseMapToUri(params);

        KakaoToken kakaoToken = kakaoOAuthWebClient.post()
                .uri(uri)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoToken.class)
                .block();

        if (kakaoToken == null) {
            throw KakaoAccessTokenFailureException.EXCEPTION;
        }

        return kakaoToken;
    }

    public KakaoMemberResponse fetchMember(String bearerToken) {
        WebClient kakaoUserDetailClient = WebClientUtil.createWebClient(KAKAO_USER_URL);

        KakaoMemberResponse kakaoMemberResponse = kakaoUserDetailClient.get()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoMemberResponse.class)
                .block();

        if (kakaoMemberResponse == null) {
            throw KakaoUserInfoFailureException.EXCEPTION;
        }

        return kakaoMemberResponse;
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
