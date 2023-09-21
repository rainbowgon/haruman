package ssafy.haruman.global.oauth.kakao.authcode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.authcode.AuthCodeRequestUrlProvider;
import ssafy.haruman.global.oauth.kakao.KakaoOAuthConfig;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final KakaoOAuthConfig kakaoOAuthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.KAKAO;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOAuthConfig.getClientId())
                .queryParam("redirect_uri", kakaoOAuthConfig.getRedirectUri())
                .queryParam("scope", String.join(",", kakaoOAuthConfig.getScope()))
                .toUriString();
    }
}