package ssafy.haruman.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.repository.CategoryRepository;
import ssafy.haruman.domain.challenge.dto.response.*;
import ssafy.haruman.domain.challenge.entity.*;
import ssafy.haruman.domain.challenge.repository.ChallengeRepository;
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;
import ssafy.haruman.domain.challenge.repository.ExpenseRepository;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.error.exception.CategoryNotFoundException;
import ssafy.haruman.global.error.exception.ChallengeAlreadyExistsException;
import ssafy.haruman.global.error.exception.ChallengeNotFoundException;
import ssafy.haruman.global.error.exception.ExpenseNotFoundException;

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

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    @Transactional
    public DailyChallengeResponseDto startChallenge(Profile profile) {

        Optional<Challenge> challenge = challengeRepository.findByProfileAndStatus(profile.getId());
        if (challenge.isPresent()) {
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

        return DailyChallengeResponseDto.from(challengeRepository.save(createdChallenge), challengeRepository.countByStatus());
    }

    @Override
    @Transactional
    public DailyChallengeResponseDto selectDailyChallenge(Profile profile) {

        Optional<Challenge> challenge = challengeRepository.findFirstChallenge(profile.getId());

        if (challenge.isEmpty() || )

        Integer participantCount = challengeRepository.countByStatus();

        if (challenge == null || challenge.getIsViewed().equals(ViewStatus.VIEWED)) {
            return DailyChallengeResponseDto.from(participantCount);
        } else {
            if (!challenge.getChallengeStatus().equals(ChallengeStatus.PROGRESS)) {
                challenge.updateViewStatus(ViewStatus.VIEWED);
            }
            return DailyChallengeResponseDto.from(challenge, participantCount);
        }
    }

    @Override
    @Transactional
    public void endChallenge() {
        List<Challenge> list = challengeRepository.findAllByStatus();
        // 챌린지가 없을 때 빈리스트가 반환되어 아무 결과 처리 없음

        for (Challenge challenge : list) {
            challenge.updateChallengeStatus(
                    challenge.getTargetAmount() - challenge.getUsedAmount() < 0
                            ? ChallengeStatus.FAIL : ChallengeStatus.SUCCESS);
        }
    }

    private Challenge getChallenge(Long challengeId) {
        return challengeRepository.findById(challengeId).orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }

    private Expense getExpense(Long expenseId) {
        return expenseRepository.findById(expenseId).orElseThrow(() -> ExpenseNotFoundException.EXCEPTION);
    }

    private Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }

    private void updateChallengeAmount(Challenge challenge, Integer beforeUsedAmount, Integer payAmount) {
        Integer usedAmount = beforeUsedAmount + payAmount;
        Integer leftOverAmount = Math.max(challenge.getTargetAmount() - usedAmount, 0);
        challenge.updateChallengeAmount(usedAmount, leftOverAmount);
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
