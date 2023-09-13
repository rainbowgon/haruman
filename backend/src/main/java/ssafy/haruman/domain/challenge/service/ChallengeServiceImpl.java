package ssafy.haruman.domain.challenge.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.challenge.dto.response.ChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeUserListResponseDto;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.ChallengeStatus;
import ssafy.haruman.domain.challenge.entity.ViewStatus;
import ssafy.haruman.domain.challenge.repository.ChallengeRepository;
import ssafy.haruman.domain.challenge.repository.ChallengeUserListMapping;
import ssafy.haruman.domain.profile.entity.Profile;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Override
    @Transactional
    public ChallengeResponseDto startChallenge(Profile profile) {

        Challenge challenge = Challenge.builder()
                .profile(profile)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0))
                .challengeStatus(ChallengeStatus.PROGRESS)
                .targetAmount(10000)
                .usedAmount(0)
                .leftoverAmount(10000)
                .isViewed(ViewStatus.NOT_VIEWED)
                .build();

        challengeRepository.save(challenge);
        return ChallengeResponseDto.from(challenge);
    }

    @Override
    public List<ChallengeUserListResponseDto> selectDailyUserList() {

        List<ChallengeUserListMapping> challengeList = challengeRepository.findChallengesByStatus();

//        List<ChallengeUserListResponseDto> userList =
//                challengeList.stream()
//                        .collect(Collectors.groupingBy())
        return null;
    }
}
