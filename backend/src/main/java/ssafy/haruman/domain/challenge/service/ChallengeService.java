package ssafy.haruman.domain.challenge.service;

import java.util.List;
import ssafy.haruman.domain.challenge.dto.response.ChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeUserListResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

public interface ChallengeService {

    ChallengeResponseDto startChallenge(Profile profile);

    List<ChallengeUserListResponseDto> selectDailyUserList();
}
