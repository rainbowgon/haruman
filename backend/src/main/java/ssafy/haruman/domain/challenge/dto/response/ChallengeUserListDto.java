package ssafy.haruman.domain.challenge.dto.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeUserListDto {

    private String profileImage;
    private String nickname;
    private Integer leftoverAmount;
    private LocalDateTime latestTime;

}
