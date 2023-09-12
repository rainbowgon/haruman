package ssafy.haruman.domain.challenge.controller;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeResponseDto;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.Expense;
import ssafy.haruman.domain.challenge.service.ChallengeService;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.response.JsonResponse;
import ssafy.haruman.global.response.ResponseWrapper;

@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Challenge>> startChallenge() {
        Profile profile = null;

        Challenge challenge = challengeService.startChallenge(profile);
        return JsonResponse.ok("챌린지가 생성되었습니다.", challenge);
    }

    @PostMapping("/{challenge-id}")
    public ResponseEntity<ResponseWrapper<Expense>> createExpense(
            @PathVariable(name = "challenge-id") Long chellengeId,
            @RequestBody ExpenseCreateRequestDto createDto) {

        return JsonResponse.ok("지출내역이 입력되었습니다.", null);
    }

    @PatchMapping("/{challenge-id}")
    public ResponseEntity<ResponseWrapper<Expense>> updateExpense(
            @PathVariable(name = "challenge-id") Long chellengeId,
            @RequestBody ExpenseUpdateRequestDto updateDto) {

        return JsonResponse.ok("지출내역이 수정되었습니다.", null);
    }

    @DeleteMapping("/{challenge-id}")
    public ResponseEntity<ResponseWrapper<Nullable>> deleteExpense(
            @PathVariable(name = "challenge-id") Long chellengeId) {

        return JsonResponse.ok("지출내역이 삭제되었습니다.", null);
    }

    @GetMapping("/{date}")
    public ResponseEntity<ResponseWrapper<List<ChallengeResponseDto>>> selectDailyChallenge(
            @PathVariable(name = "date") LocalDateTime date) {

        return JsonResponse.ok("챌린지 상세내역을 불러왔습니다.", null);
    }


}
