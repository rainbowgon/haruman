package ssafy.haruman.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.repository.CategoryRepository;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.ExpenseResponseDto;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.Expense;
import ssafy.haruman.domain.challenge.repository.ChallengeRepository;
import ssafy.haruman.domain.challenge.repository.ExpenseRepository;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.error.exception.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ChallengeRepository challengeRepository;

    @Override
    @Transactional
    public ExpenseResponseDto createExpense(Profile profile, Long challengeId, ExpenseCreateRequestDto createRequestDto) {

        Challenge challenge = getChallenge(challengeId);
        if (!challenge.getProfile().getId().equals(profile.getId())) {
            throw ChallengeUnauthorizedException.EXCEPTION;
        }

        Category category = getCategory(createRequestDto.getCategoryId());
        LocalDateTime payTime = createRequestDto.getPayTime() == null ? LocalDateTime.now() : createRequestDto.getPayTime();

        Expense expense = Expense.builder()
                .challenge(challenge)
                .category(category)
                .payTime(payTime)
                .payAmount(createRequestDto.getPayAmount())
                .content(createRequestDto.getContent())
                .build();

        Expense createdExpense = expenseRepository.save(expense);

        // 목표금액과 사용금액이 기준이 됨 -> 남은 금액은 매번 변경
        updateChallengeAmount(challenge, expense.getPayAmount());

        return ExpenseResponseDto.from(createdExpense);
    }

    @Override
    @Transactional
    public ExpenseResponseDto updateExpense(Profile profile, ExpenseUpdateRequestDto updateRequestDto) {

        Expense expense = getExpense(updateRequestDto.getExpenseId());
        Challenge challenge = expense.getChallenge();
        if (!challenge.getProfile().getId().equals(profile.getId())) {
            throw ExpenseUnauthorizedException.EXCEPTION;
        }

        Category category = getCategory(updateRequestDto.getCategoryId());

        updateChallengeAmount(challenge, updateRequestDto.getPayAmount() - expense.getPayAmount());
        expense.updateExpense(category, updateRequestDto.getPayAmount(), updateRequestDto.getContent());

        return ExpenseResponseDto.from(expense);
    }

    @Override
    @Transactional
    public void deleteExpense(Profile profile, Long expenseId) {

        Expense expense = getExpense(expenseId);
        Challenge challenge = expense.getChallenge();
        if (!challenge.getProfile().getId().equals(profile.getId())) {
            throw ExpenseUnauthorizedException.EXCEPTION;
        }

        updateChallengeAmount(challenge, -expense.getPayAmount());

        expenseRepository.delete(expense);
    }

    @Override
    public List<ExpenseResponseDto> selectDailyExpenseList(Profile profile, Long challengeId) {

        Challenge challenge = getChallenge(challengeId);
        if (!challenge.getProfile().getId().equals(profile.getId())) {
            throw ChallengeUnauthorizedException.EXCEPTION;
        }

        return expenseRepository.findAllByChallenge(challenge).stream()
                .map(ExpenseResponseDto::from).collect(Collectors.toList());
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

    private void updateChallengeAmount(Challenge challenge, Integer payAmount) {
        Integer usedAmount = challenge.getUsedAmount() + payAmount;
        Integer leftOverAmount = Math.max(challenge.getTargetAmount() - usedAmount, 0);
        challenge.updateChallengeAmount(usedAmount, leftOverAmount);
    }
}
