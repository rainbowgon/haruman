package ssafy.haruman.domain.member.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthId {

    @Column(nullable = false)
    private String oauthServerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OAuthServerType oauthServerType;
}
