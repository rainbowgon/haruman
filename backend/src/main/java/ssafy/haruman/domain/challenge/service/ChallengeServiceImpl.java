package ssafy.haruman.domain.challenge.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.repository.CategoryRepository;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.AccumulatedAmountResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeHistoryResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeUserInfoDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeUserListResponseDto;
import ssafy.haruman.domain.challenge.dto.response.DailyChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ExpenseResponseDto;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.ChallengeGroup;
import ssafy.haruman.domain.challenge.entity.ChallengeStatus;
import ssafy.haruman.domain.challenge.entity.Expense;
import ssafy.haruman.domain.challenge.entity.ViewStatus;
import ssafy.haruman.domain.challenge.repository.ChallengeRepository;
import ssafy.haruman.domain.challenge.repository.ChallengeUserInfoMapping;
import ssafy.haruman.domain.challenge.repository.ExpenseRepository;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.error.exception.CategoryNotFoundException;
import ssafy.haruman.global.error.exception.ChallengeAlreadyExistsException;
import ssafy.haruman.global.error.exception.ChallengeNotAvailableException;
import ssafy.haruman.global.error.exception.ChallengeNotFoundException;
import ssafy.haruman.global.error.exception.ExpenseNotFoundException;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    @Transactional
    public ChallengeResponseDto startChallenge(Profile profile) {

        if (challengeRepository.findFirstChallenge(profile.getId()) != null) {
            throw ChallengeAlreadyExistsException.EXCEPTION;
        }

        if (LocalTime.now().isBefore(LocalTime.of(5, 0))
                || LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            throw ChallengeNotAvailableException.EXCEPTION;
        }

        Challenge challenge = Challenge.builder()
                .profile(profile)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0))
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
    @Transactional
    public ExpenseResponseDto createExpense(
            Long challengeId, ExpenseCreateRequestDto createRequestDto) {

        Challenge challenge = getChallenge(challengeId);
        Category category = getCategory(createRequestDto.getCategoryId());

        LocalDateTime payTime = createRequestDto.getPayTime() == null ? LocalDateTime.now()
                : createRequestDto.getPayTime();

        Expense expense = Expense.createExpense(challenge, category, createRequestDto, payTime);
        expenseRepository.save(expense);

        // 목표금액과 사용금액이 기준이 됨 -> 남은 금액은 매번 변경
        updateChallengeAmount(challenge, challenge.getUsedAmount(), expense.getPayAmount());

        return ExpenseResponseDto.from(expense);
    }

    @Override
    @Transactional
    public ExpenseResponseDto updateExpense(ExpenseUpdateRequestDto updateRequestDto) {

        Expense expense = getExpense(updateRequestDto.getExpenseId());
        Challenge challenge = expense.getChallenge();
        Category category = getCategory(updateRequestDto.getCategoryId());

        updateChallengeAmount(challenge, challenge.getUsedAmount() - expense.getPayAmount(),
                updateRequestDto.getPayAmount());

        expense.updateExpense(category, updateRequestDto.getPayAmount(),
                updateRequestDto.getContent());

        return ExpenseResponseDto.from(expense);
    }

    @Override
    @Transactional
    public void deleteExpense(Long expenseId) {

        Expense expense = getExpense(expenseId);
        Challenge challenge = expense.getChallenge();

        updateChallengeAmount(challenge, challenge.getUsedAmount() - expense.getPayAmount(), 0);

        expenseRepository.delete(expense);
    }

    @Override
    @Transactional
    public DailyChallengeResponseDto selectDailyChallenge(Profile profile) {
        Challenge challenge = challengeRepository.findFirstChallenge(profile.getId());
        // 챌린지가 없다면 return null
        Integer participantCount = challengeRepository.countByStatus();

        if (challenge == null || challenge.getIsViewed().equals(ViewStatus.VIEWED)) {
            return null;
        } else {
            if (!challenge.getChallengeStatus().equals(ChallengeStatus.PROGRESS)) {
                challenge.updateViewStatus(ViewStatus.VIEWED);
            }
            List<ExpenseResponseDto> list = expenseRepository.findAllByChallenge_Id(
                            challenge.getId())
                    .stream()
                    .map(expense -> ExpenseResponseDto.from(expense)).collect(Collectors.toList());
            return DailyChallengeResponseDto.from(challenge, participantCount, list);
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

        return challengeRepository.findById(challengeId)
                .orElseThrow(() -> ChallengeNotFoundException.EXCEPTION);
    }

    private Expense getExpense(Long expenseId) {

        return expenseRepository.findById(expenseId)
                .orElseThrow(() -> ExpenseNotFoundException.EXCEPTION);
    }

    private Category getCategory(Long categoryId) {

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }

    private void updateChallengeAmount(Challenge challenge, Integer beforeUsedAmount,
            Integer payAmount) {
        Integer usedAmount = beforeUsedAmount + payAmount;
        Integer leftOverAmount = Math.max(challenge.getTargetAmount() - usedAmount, 0);
        challenge.updateChallengeAmount(usedAmount, leftOverAmount);
    }


    @Override
    public List<ChallengeUserListResponseDto> selectDailyUserList() {

        List<ChallengeUserInfoMapping> challengeList = challengeRepository.findChallengeAndExpenseAndProfileByStatus();

        return challengeList.stream()
                .collect(Collectors.groupingBy(this::getGroupKey))
                .entrySet().stream()
                .map(entry -> ChallengeUserListResponseDto.from(entry.getKey(),
                        convertToUserInfoDto(entry.getValue())))
                .collect(Collectors.toList());
    }

    @Override
    public AccumulatedAmountResponseDto selectAccumulatedAmount() {

        // TODO 프로필 유효성 검증
        Long profileId = null;

        Integer accumulatedAmount = challengeRepository.findAllByProfileAndStatus(profileId);

        return AccumulatedAmountResponseDto.builder()
                .accumulatedAmount(accumulatedAmount)
                .build();
    }

    @Override
    public List<ChallengeHistoryResponseDto> selectChallengeHistory(Date yearAndMonth) {

        // TODO 프로필 유효성 검증
        Long profileId = null;

        String date = yearAndMonth == null ?
                LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-DD")) :
                String.valueOf(yearAndMonth);

        List<Challenge> challengeList =
                challengeRepository.findAllByProfileAndDate(profileId, date);

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
