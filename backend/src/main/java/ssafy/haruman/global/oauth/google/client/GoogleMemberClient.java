package ssafy.haruman.global.oauth.google.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.common.client.OAuthMemberClient;
import ssafy.haruman.global.oauth.common.dto.OAuthResponseDto;
import ssafy.haruman.global.oauth.google.GoogleOAuthConfig;
import ssafy.haruman.global.oauth.google.dto.GoogleMemberResponse;
import ssafy.haruman.global.oauth.google.dto.GoogleToken;

@Component
@RequiredArgsConstructor
public class GoogleMemberClient implements OAuthMemberClient {

    private final GoogleApiClient googleApiClient;
    private final GoogleOAuthConfig googleOAuthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.GOOGLE;
    }

    @Override
    public OAuthResponseDto fetch(String authCode) {
        GoogleToken tokenInfo = googleApiClient.fetchToken(tokenRequestParams(authCode));
        GoogleMemberResponse googleMemberResponse = googleApiClient.fetchMember(tokenInfo.getAccessToken());
        return OAuthResponseDto.fromGoogle(googleMemberResponse);
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
