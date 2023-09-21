package ssafy.haruman.domain.profile.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileUpdateRequestDto {

    private Long profileId;
    private String nickname;

}
