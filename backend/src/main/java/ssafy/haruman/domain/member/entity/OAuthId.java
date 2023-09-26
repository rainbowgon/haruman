package ssafy.haruman.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthId {

    @Column(name = "oauth_server_id", columnDefinition = "VARCHAR(255)")
    private String oauthServerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_server_type", columnDefinition = "VARCHAR(10)")
    private OAuthServerType oauthServerType;
}
