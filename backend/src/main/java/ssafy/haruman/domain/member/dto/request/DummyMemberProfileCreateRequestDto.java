package ssafy.haruman.domain.member.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.member.entity.OAuthId;
import ssafy.haruman.domain.member.entity.OAuthServerType;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DummyMemberProfileCreateRequestDto {

    private String nickname;

    public Member toMemberEntity() {
        String testId = "TEST_SERVER_ID_" + UUID.randomUUID();
        return Member.builder()
                .oauthId(new OAuthId(testId, OAuthServerType.TEST))
                .build();
    }
}
