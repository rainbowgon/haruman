package ssafy.haruman.global.oauth.client;

import org.springframework.stereotype.Component;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.error.exception.OAuthNotFoundException;
import ssafy.haruman.global.oauth.dto.OAuthResponseDto;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuthMemberClientComposite {

    private final Map<OAuthServerType, OAuthMemberClient> mapping;

    public OAuthMemberClientComposite(Set<OAuthMemberClient> clients) {
        mapping = clients.stream()
                .collect(Collectors.toMap(
                        OAuthMemberClient::supportServer,
                        Function.identity()
                ));
    }

    public OAuthResponseDto fetch(OAuthServerType oauthServerType, String authCode) {
        return getClient(oauthServerType).fetch(authCode);
    }

    private OAuthMemberClient getClient(OAuthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType))
                .orElseThrow(() -> OAuthNotFoundException.EXCEPTION);
    }
}
