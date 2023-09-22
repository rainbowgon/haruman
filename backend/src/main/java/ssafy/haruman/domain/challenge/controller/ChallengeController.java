package ssafy.haruman.domain.challenge.controller;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.AccumulatedAmountResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeHistoryResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeUserListResponseDto;
import ssafy.haruman.domain.challenge.dto.response.DailyChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ExpenseResponseDto;
import ssafy.haruman.domain.challenge.service.ChallengeService;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.response.JsonResponse;
import ssafy.haruman.global.response.ResponseWrapper;

@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<ChallengeResponseDto>> startChallenge(
            @AuthenticationPrincipal Member member) {
        Profile profile = member.getProfile();

        ChallengeResponseDto responseDto = challengeService.startChallenge(profile);
        return JsonResponse.ok("챌린지가 생성되었습니다.", responseDto);
    }

    @PostMapping("/{challenge-id}")
    public ResponseEntity<ResponseWrapper<ExpenseResponseDto>> createExpense(
            @PathVariable(name = "challenge-id") Long challengeId,
            @RequestBody ExpenseCreateRequestDto createRequestDto) {

        ExpenseResponseDto reponseDto = challengeService.createExpense(challengeId,
                createRequestDto);
        return JsonResponse.ok("지출내역이 입력되었습니다.", reponseDto);
    }

    @PatchMapping
    public ResponseEntity<ResponseWrapper<ExpenseResponseDto>> updateExpense(
            @RequestBody ExpenseUpdateRequestDto updateRequestDto) {

        ExpenseResponseDto reponseDto = challengeService.updateExpense(updateRequestDto);
        return JsonResponse.ok("지출내역이 수정되었습니다.", reponseDto);
    }

    @DeleteMapping("/{expense-id}")
    public ResponseEntity<ResponseWrapper<Nullable>> deleteExpense(
            @PathVariable(name = "expense-id") Long expenseId) {

        challengeService.deleteExpense(expenseId);
        return JsonResponse.ok("지출내역이 삭제되었습니다.");
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<DailyChallengeResponseDto>> selectDailyChallenge(
            @AuthenticationPrincipal Member member) {
        Profile profile = member.getProfile();

        DailyChallengeResponseDto responseDto = challengeService.selectDailyChallenge(profile);
        return JsonResponse.ok("챌린지 상세내역을 불러왔습니다.", responseDto);
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public ResponseEntity<ResponseWrapper<Nullable>> endChallenge() {

        challengeService.endChallenge();
        return JsonResponse.ok("챌린지가 종료되고 사용자 정보가 업데이트되었습니다.");
    }

    @GetMapping("/people")
    public ResponseEntity<ResponseWrapper<List<ChallengeUserListResponseDto>>> selectChallengeUserList() {

        List<ChallengeUserListResponseDto> userList = challengeService.selectDailyUserList();

        return JsonResponse.ok("챌린지 중인 회원 목록을 성공적으로 가져왔습니다.", userList);
    }

    @GetMapping("/amount")
    public ResponseEntity<ResponseWrapper<AccumulatedAmountResponseDto>> selectAccumulatedAmount() {

        AccumulatedAmountResponseDto accumulatedAmount = challengeService.selectAccumulatedAmount();

        return JsonResponse.ok("챌린지 누적 잔액을 성공적으로 가져왔습니다.", accumulatedAmount);
    }

    @GetMapping("/history")
    public ResponseEntity<ResponseWrapper<List<ChallengeHistoryResponseDto>>> selectChallengeHistory(
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM") Date yearAndMonth) {

        List<ChallengeHistoryResponseDto> challengeHistory
                = challengeService.selectChallengeHistory(yearAndMonth);

        return JsonResponse.ok("챌린지 월별 내역을 성공적으로 가져왔습니다.", challengeHistory);
    }
}
