package ssafy.haruman.global.oauth.common.client;

import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.common.dto.OAuthResponseDto;

public interface OAuthMemberClient {

    OAuthServerType supportServer();

    OAuthResponseDto fetch(String code);
}
