package ssafy.haruman.domain.member.service;

import ssafy.haruman.domain.member.entity.OAuthServerType;

public interface OAuthService {
    
    String getAuthCodeRequestUrl(OAuthServerType oauthServerType);

    String oauthLogin(OAuthServerType oauthServerType, String code);
}
