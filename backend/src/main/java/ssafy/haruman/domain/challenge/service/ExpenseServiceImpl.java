package ssafy.haruman.domain.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.ExpenseResponseDto;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.Expense;
import ssafy.haruman.domain.challenge.repository.ExpenseRepository;
import ssafy.haruman.domain.profile.entity.Profile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

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

        updateChallengeAmount(challenge, challenge.getUsedAmount() - expense.getPayAmount(), updateRequestDto.getPayAmount());

        expense.updateExpense(category, updateRequestDto.getPayAmount(), updateRequestDto.getContent());

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
    public List<ExpenseResponseDto> selectDailyExpenseList(Profile profile, Long challengeId) {

        Challenge challenge = getChallenge(challengeId);
        if (!challenge.getProfile().getId().equals(profile.getId())) {
            throw
        }

        return expenseRepository.findAllByChallenge_Id(challengeId)
                .stream()
                .map(expense -> ExpenseResponseDto.from(expense)).collect(Collectors.toList());
    }
}
