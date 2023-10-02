package ssafy.haruman.domain.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.domain.challenge.dto.request.ExpenseUpdateRequestDto;
import ssafy.haruman.domain.challenge.dto.response.*;
import ssafy.haruman.domain.challenge.service.ChallengeService;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.response.JsonResponse;
import ssafy.haruman.global.response.PageInfo;
import ssafy.haruman.global.response.ResponseWrapper;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<DailyChallengeResponseDto>> startChallenge(
            @AuthenticationPrincipal Member member) {
        Profile profile = member.getProfile();

        DailyChallengeResponseDto responseDto = challengeService.startChallenge(profile);
        return JsonResponse.ok("챌린지가 생성되었습니다.", responseDto);
    }

    @PostMapping("/{challenge-id}")
    public ResponseEntity<ResponseWrapper<ExpenseResponseDto>> createExpense(
            @PathVariable(name = "challenge-id") Long challengeId,
            @RequestBody ExpenseCreateRequestDto createRequestDto) {

        ExpenseResponseDto reponseDto = challengeService.createExpense(challengeId, createRequestDto);
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

    @GetMapping("/{challenge-id}")
    public ResponseEntity<ResponseWrapper<List<ExpenseResponseDto>>> selectDailyExpenseList(
            @PathVariable(name = "challenge-id") Long challengeId) {

        List<ExpenseResponseDto> responseDto = challengeService.selectDailyExpenseList(challengeId);
        return JsonResponse.ok("지출내역 리스트를 불러왔습니다.", responseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<DailyChallengeResponseDto>> selectDailyChallenge(
            @AuthenticationPrincipal Member member) {
        Profile profile = member.getProfile();

        DailyChallengeResponseDto responseDto = challengeService.selectDailyChallenge(profile);
        return JsonResponse.ok("챌린지 상세내역을 불러왔습니다.", responseDto);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public ResponseEntity<ResponseWrapper<Nullable>> endChallenge() {

        challengeService.endChallenge();
        return JsonResponse.ok("챌린지가 종료되고 사용자 정보가 업데이트되었습니다.");
    }

    @GetMapping("/people")
    public ResponseEntity<ResponseWrapper<List<ChallengeUserListResponseDto>>> selectChallengeUserList() {

        List<ChallengeUserListResponseDto> userList = challengeService.selectDailyUserList();

        int size = 0;
        for (ChallengeUserListResponseDto group : userList) {
            size += group.getGroup().size();
        }
        
        PageInfo listSize = PageInfo.builder().size(size).build();

        return JsonResponse.ok("챌린지 중인 회원 목록을 성공적으로 가져왔습니다.", userList, listSize);
    }

    @GetMapping("/amount")
    public ResponseEntity<ResponseWrapper<AccumulatedAmountResponseDto>> selectAccumulatedAmount(
            @AuthenticationPrincipal Member member) {

        AccumulatedAmountResponseDto accumulatedAmount = challengeService.selectAccumulatedAmount(member.getProfile());

        return JsonResponse.ok("챌린지 누적 잔액을 성공적으로 가져왔습니다.", accumulatedAmount);
    }

    @GetMapping("/history")
    public ResponseEntity<ResponseWrapper<List<ChallengeHistoryResponseDto>>> selectChallengeHistory(
            @AuthenticationPrincipal Member member,
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(pattern = "yyyy-MM") Date yearAndMonth) {

        List<ChallengeHistoryResponseDto> challengeHistory =
                challengeService.selectChallengeHistory(member.getProfile(), yearAndMonth);

        PageInfo listSize = PageInfo.builder().size(challengeHistory.size()).build();

        return JsonResponse.ok("챌린지 월별 내역을 성공적으로 가져왔습니다.", challengeHistory, listSize);
    }
}
