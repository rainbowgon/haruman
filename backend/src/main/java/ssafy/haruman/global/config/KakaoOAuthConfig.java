package ssafy.haruman.global.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoOAuthConfig {

    @Value("${oauth.kakao.redirect_uri}")
    private String redirectUri;

    @Value("${oauth.kakao.client_id}")
    private String clientId;

    @Value("${oauth.kakao.client_secret}")
    private String clientSecret;

    @Value("${oauth.kakao.scope}")
    private String[] scope;
}