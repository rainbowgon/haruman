package ssafy.haruman.global.oauth.google.authcode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.authcode.AuthCodeRequestUrlProvider;
import ssafy.haruman.global.oauth.google.GoogleOAuthConfig;

@Component
@RequiredArgsConstructor
public class GoogleAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final GoogleOAuthConfig googleOAuthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.GOOGLE;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("response_type", "code")
                .queryParam("client_id", googleOAuthConfig.getClientId())
                .queryParam("redirect_uri", googleOAuthConfig.getRedirectUri())
                .queryParam("scope", String.join(",", googleOAuthConfig.getScope()))
                .toUriString();
    }
}