package ssafy.haruman.domain.challenge.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ChallengeResponseDto startChallenge(Profile profile) {

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
        Category category =
                categoryRepository.findById(createRequestDto.getCategoryId()).orElseThrow();
        LocalDateTime payTime = createRequestDto.getPayTime() == null ? LocalDateTime.now()
                : createRequestDto.getPayTime();

        Expense expense = Expense.createExpense(challenge, category, createRequestDto, payTime);
        expenseRepository.save(expense);

        // 목표금액과 사용금액이 기준. 남은 금액은 매번 변경
        updateChallengeAmount(challenge, challenge.getUsedAmount(), expense.getPayAmount());

        ExpenseResponseDto responseDto = ExpenseResponseDto.from(expense);
        return responseDto;
    }

    @Override
    @Transactional
    public ExpenseResponseDto updateExpense(ExpenseUpdateRequestDto updateRequestDto) {
        Expense expense = getExpense(updateRequestDto.getExpenseId());
        Challenge challenge = expense.getChallenge();
        Category category
                = categoryRepository.findById(updateRequestDto.getCategoryId()).orElseThrow();

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
    public DailyChallengeResponseDto selectDailyChallenge(Profile profile, Date date) {
        Challenge challenge = challengeRepository.findByProfileAndDate(profile.getId(), date);
        // TODO 챌린지 반환이 1개가 아니면 에러
        // TODO 페이지네이션

        List<ExpenseResponseDto> list = expenseRepository.findAllByChallenge_Id(challenge.getId())
                .stream()
                .map(expense -> ExpenseResponseDto.from(expense)).collect(Collectors.toList());
        return DailyChallengeResponseDto.from(challenge, list);
    }


    private Challenge getChallenge(Long challengeId) {
        return challengeRepository.findById(challengeId).orElseThrow();
    }

    private Expense getExpense(Long expenseId) {
        return expenseRepository.findById(expenseId).orElseThrow();
    }

    private void updateChallengeAmount(Challenge challenge, Integer beforeUsedAmount,
            Integer payAmount) {
        Integer usedAmount = beforeUsedAmount + payAmount;
        Integer leftOverAmount = Math.max(challenge.getTargetAmount() - usedAmount, 0);
        challenge.updateChallengeAmount(usedAmount, leftOverAmount);
    }

    @Override
    public List<ChallengeUserListResponseDto> selectDailyUserList() {

        List<ChallengeUserInfoMapping> challengeList = challengeRepository.findChallengesByStatus();

        List<ChallengeUserListResponseDto> userList =
                challengeList.stream()
                        .collect(Collectors.groupingBy(this::getGroupKey))
                        .entrySet().stream()
                        .map(entry -> ChallengeUserListResponseDto.from(entry.getKey(),
                                convertToUserInfoDto(entry.getValue())))
                        .collect(Collectors.toList());

        return userList;
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
