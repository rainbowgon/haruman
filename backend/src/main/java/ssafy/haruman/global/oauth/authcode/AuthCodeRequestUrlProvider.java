package ssafy.haruman.global.oauth.authcode;

import ssafy.haruman.domain.member.entity.OAuthServerType;

public interface AuthCodeRequestUrlProvider {

    OAuthServerType supportServer();

    String provide();
}
