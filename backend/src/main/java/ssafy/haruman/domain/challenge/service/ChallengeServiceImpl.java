package ssafy.haruman.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.challenge.dto.response.*;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.ChallengeGroup;
import ssafy.haruman.domain.challenge.entity.ChallengeStatus;
import ssafy.haruman.domain.challenge.entity.ViewStatus;
import ssafy.haruman.domain.challenge.repository.ChallengeRepository;
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.error.exception.ChallengeAlreadyExistsException;
import ssafy.haruman.global.error.exception.ChallengeWrongDataException;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Override
    @Transactional
    public DailyChallengeResponseDto startChallenge(Profile profile) {

        Optional<Challenge> challenge = challengeRepository.findFirstChallenge(profile.getId());
        if (challenge.isPresent() && challenge.get().getChallengeStatus().equals(ChallengeStatus.PROGRESS)) {
            throw ChallengeAlreadyExistsException.EXCEPTION;
        }

//        if (LocalTime.now().isBefore(LocalTime.of(5, 0))
//                || LocalTime.now().isAfter(LocalTime.of(12, 0))) {
//            throw ChallengeNotAvailableException.EXCEPTION;
//        }

        Challenge createdChallenge = Challenge.builder()
                .profile(profile)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0))
                .challengeStatus(ChallengeStatus.PROGRESS)
                .targetAmount(10000)
                .usedAmount(0)
                .leftoverAmount(10000)
                .isViewed(ViewStatus.NOT_VIEWED)
                .build();

        return DailyChallengeResponseDto.from(challengeRepository.save(createdChallenge),
                                              challengeRepository.countByStatus() - 1);
    }

    @Override
    @Transactional
    public DailyChallengeResponseDto selectDailyChallenge(Profile profile) {

        Optional<Challenge> firstChallenge = challengeRepository.findFirstChallenge(profile.getId());
        Integer participantCount = challengeRepository.countByStatus() - 1;

        if (firstChallenge.isPresent()) {
            Challenge challenge = firstChallenge.get();
            if (challenge.getChallengeStatus().equals(ChallengeStatus.PROGRESS)) {
                if (challenge.getIsViewed().equals(ViewStatus.NOT_VIEWED)) {
                    // 1. 챌린지 진행 중인 경우
                    return DailyChallengeResponseDto.from(challenge, participantCount);
                }
            } else {
                if (challenge.getIsViewed().equals(ViewStatus.NOT_VIEWED)) {
                    // 2. 챌린지 진행 중이 아니며, 이전 챌린지의 결과를 아직 확인하지 않은 경우
                    challenge.updateViewStatus(ViewStatus.VIEWED);
                    return DailyChallengeResponseDto.from(challenge, participantCount);
                } else {
                    // 3. 챌린지 진행 중이 아니며, 이전 챌린지의 결과를 확인한 경우
                    return DailyChallengeResponseDto.from(participantCount);
                }
            }
        } else { // 4. 아직 한 번도 챌린지를 진행한 적이 없는 경우
            return DailyChallengeResponseDto.from(participantCount);
        }

        throw ChallengeWrongDataException.EXCEPTION;
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void endChallenge() {

        List<Challenge> list = challengeRepository.findAllByStatus();
        // 챌린지가 없을 때 빈리스트가 반환되어 아무 결과 처리 없음

        for (Challenge challenge : list) {
            challenge.updateChallengeStatus(challenge.getTargetAmount() - challenge.getUsedAmount() < 0
                                                    ? ChallengeStatus.FAIL : ChallengeStatus.SUCCESS);
        }
    }

    @Override
    public List<ChallengeUserListResponseDto> selectChallengeUserList() {

        List<ChallengeUserInfoMapping> challengeList = challengeRepository.findChallengeAndExpenseAndProfileByStatus();

        return challengeList.stream()
                .collect(Collectors.groupingBy(this::getGroupKey))
                .entrySet().stream()
                .map(entry -> ChallengeUserListResponseDto.from(entry.getKey(), convertToUserInfoDto(entry.getValue())))
                .collect(Collectors.toList());
    }

    @Override
    public AccumulatedAmountResponseDto selectAccumulatedAmount(Profile profile) {

        Integer accumulatedAmount = challengeRepository.sumByProfileAndStatus(profile.getId());

        return AccumulatedAmountResponseDto.builder().accumulatedAmount(accumulatedAmount).build();
    }

    @Override
    public List<ChallengeHistoryResponseDto> selectChallengeHistory(Profile profile, Date yearAndMonth) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String date = yearAndMonth == null ?
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM")) : dateFormat.format(yearAndMonth);

        List<Challenge> challengeList = challengeRepository.findAllByProfileAndDate(profile.getId(), date);

        return challengeList.stream()
                .map(ChallengeHistoryResponseDto::from)
                .collect(Collectors.toList());
    }

    private String getGroupKey(ChallengeUserInfoMapping challenge) {
        ChallengeGroup group = ChallengeGroup.getGroup(challenge.getUsedAmount());
        return group.getGroupKey();
    }

    private List<ChallengeUserInfoDto> convertToUserInfoDto(List<ChallengeUserInfoMapping> list) {
        return list.stream()
                .map(ChallengeUserInfoDto::from)
                .collect(Collectors.toList());
    }

}
