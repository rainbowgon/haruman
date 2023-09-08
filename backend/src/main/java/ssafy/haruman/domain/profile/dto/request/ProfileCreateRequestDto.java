package ssafy.haruman.domain.profile.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.profile.entity.Profile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileCreateRequestDto {

    private String nickname;

    public Profile toEntity() {
        return Profile.builder()
                .nickname(nickname).build();
    }
}
