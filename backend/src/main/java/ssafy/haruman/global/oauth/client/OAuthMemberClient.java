package ssafy.haruman.global.oauth.client;

import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.dto.OAuthResponseDto;

public interface OAuthMemberClient {

    OAuthServerType supportServer();

    OAuthResponseDto fetch(String code);
}
