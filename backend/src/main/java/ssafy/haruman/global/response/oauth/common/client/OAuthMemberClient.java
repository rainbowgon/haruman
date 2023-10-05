package ssafy.haruman.global.response.oauth.common.client;

import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.response.oauth.common.dto.OAuthResponseDto;

public interface OAuthMemberClient {

    OAuthServerType supportServer();

    OAuthResponseDto fetch(String code);
}
