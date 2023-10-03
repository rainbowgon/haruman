package ssafy.haruman.global.oauth.google.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.client.OAuthMemberClient;
import ssafy.haruman.global.oauth.dto.OAuthResponseDto;
import ssafy.haruman.global.oauth.google.GoogleOAuthConfig;
import ssafy.haruman.global.oauth.google.dto.KakaoMemberResponse;
import ssafy.haruman.global.oauth.google.dto.KakaoToken;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OAuthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final GoogleOAuthConfig googleOAuthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.KAKAO;
    }

    @Override
    public OAuthResponseDto fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode));
        KakaoMemberResponse kakaoMemberResponse = kakaoApiClient.fetchMember(tokenInfo.getAccessToken());
        return OAuthResponseDto.fromKakao(kakaoMemberResponse);
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", googleOAuthConfig.getClientId());
        params.add("redirect_uri", googleOAuthConfig.getRedirectUri());
        params.add("code", authCode);
        params.add("client_secret", googleOAuthConfig.getClientSecret());
        return params;
    }
}
