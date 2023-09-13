package ssafy.haruman.domain.challenge.service;

import java.time.LocalDateTime;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.DailyChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ExpenseResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

public interface ChallengeService {

    ChallengeResponseDto startChallenge(Profile profile);

    ExpenseResponseDto createExpense(Long challengeId, ExpenseCreateRequestDto createRequestDto);

    ExpenseResponseDto updateExpense(ExpenseUpdateRequestDto updateRequestDto);

    void deleteExpense(Long expenseId);

    DailyChallengeResponseDto selectDailyChallenge(Profile profile, LocalDateTime date);
}
