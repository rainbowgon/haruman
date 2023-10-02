package ssafy.haruman.domain.challenge.service;

import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.*;
import ssafy.haruman.domain.profile.entity.Profile;

import java.util.Date;
import java.util.List;

public interface ChallengeService {

    DailyChallengeResponseDto startChallenge(Profile profile);

    ExpenseResponseDto createExpense(Long challengeId, ExpenseCreateRequestDto createRequestDto);

    ExpenseResponseDto updateExpense(ExpenseUpdateRequestDto updateRequestDto);

    void deleteExpense(Long expenseId);

    List<ExpenseResponseDto> selectDailyExpenseList(Long challengeId);

    DailyChallengeResponseDto selectDailyChallenge(Profile profile);

    void endChallenge();

    List<ChallengeUserListResponseDto> selectDailyUserList();

    AccumulatedAmountResponseDto selectAccumulatedAmount(Profile profile);

    List<ChallengeHistoryResponseDto> selectChallengeHistory(Profile profile, Date yearAndMonth);

}
