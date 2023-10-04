package ssafy.haruman.domain.challenge.service;

import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.ExpenseResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

import java.util.List;

public interface ExpenseService {

    ExpenseResponseDto createExpense(Profile profile, Long challengeId, ExpenseCreateRequestDto createRequestDto);

    ExpenseResponseDto updateExpense(Profile profile, ExpenseUpdateRequestDto updateRequestDto);

    void deleteExpense(Profile profile, Long expenseId);

    List<ExpenseResponseDto> selectDailyExpenseList(Profile profile, Long challengeId);
}
