package ssafy.haruman.global.oauth.kakao.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.common.client.OAuthMemberClient;
import ssafy.haruman.global.oauth.common.dto.OAuthResponseDto;
import ssafy.haruman.global.oauth.kakao.KakaoOAuthConfig;
import ssafy.haruman.global.oauth.kakao.dto.KakaoMemberResponse;
import ssafy.haruman.global.oauth.kakao.dto.KakaoToken;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OAuthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOAuthConfig kakaoOAuthConfig;

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
        params.add("client_id", kakaoOAuthConfig.getClientId());
        params.add("redirect_uri", kakaoOAuthConfig.getRedirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOAuthConfig.getClientSecret());
        return params;
    }
}
