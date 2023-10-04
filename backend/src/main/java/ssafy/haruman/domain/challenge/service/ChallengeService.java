package ssafy.haruman.domain.challenge.service;

import ssafy.haruman.domain.challenge.dto.response.AccumulatedAmountResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeHistoryResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeUserListResponseDto;
import ssafy.haruman.domain.challenge.dto.response.DailyChallengeResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

import java.util.Date;
import java.util.List;

public interface ChallengeService {

    DailyChallengeResponseDto startChallenge(Profile profile);

    DailyChallengeResponseDto selectDailyChallenge(Profile profile);

    void endChallenge();

    List<ChallengeUserListResponseDto> selectChallengeUserList();

    AccumulatedAmountResponseDto selectAccumulatedAmount(Profile profile);

    List<ChallengeHistoryResponseDto> selectChallengeHistory(Profile profile, Date yearAndMonth);
}
