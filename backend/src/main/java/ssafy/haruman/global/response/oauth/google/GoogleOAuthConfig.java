package ssafy.haruman.global.response.oauth.google;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GoogleOAuthConfig {

    @Value("${oauth.google.redirect_uri}")
    private String redirectUri;

    @Value("${oauth.google.client_id}")
    private String clientId;

    @Value("${oauth.google.client_secret}")
    private String clientSecret;

    @Value("${oauth.google.scope}")
    private String[] scope;
}