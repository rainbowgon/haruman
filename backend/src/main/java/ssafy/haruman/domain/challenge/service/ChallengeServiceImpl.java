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
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;
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

        List<ChallengeUserInfoMapping> challengeList = challengeRepository.findChallengesByStatus();

//        List<ChallengeUserListResponseDto> userList = challengeList.stream()
//                .collect(Collectors.groupingBy(challenge -> getGroupKey(challenge)))
//                .entrySet().stream().map(entry -> )

        return null;
    }


    private String getGroupKey(ChallengeUserInfoMapping challenge) {
        if (challenge.getUsedAmount() == 0) {
            return "0원";
        } else if (challenge.getUsedAmount() <= 2000) {
            return "2000원 이하";
        } else if (challenge.getUsedAmount() <= 4000) {
            return "4000원 이하";
        } else if (challenge.getUsedAmount() <= 6000) {
            return "6000원 이하";
        } else if (challenge.getUsedAmount() <= 8000) {
            return "8000원 이하";
        } else if (challenge.getUsedAmount() <= 10000) {
            return "10000원 이하";
        } else if (challenge.getUsedAmount() > 10000) {
            return "챌린지 실패";
        } else {
            return "이상값"; // 에러
        }
    }

}
