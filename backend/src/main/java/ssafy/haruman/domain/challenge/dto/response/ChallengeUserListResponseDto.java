package ssafy.haruman.domain.challenge.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChallengeUserListResponseDto {

    private String groupKey;
    private List<ChallengeUserInfoDto> userList;

    public static ChallengeUserListResponseDto from(String groupKey, List<ChallengeUserInfoDto> userList) {
        return ChallengeUserListResponseDto.builder()
                .groupKey(groupKey)
                .userList(userList)
                .build();
    }
}
