package ssafy.haruman.domain.challenge.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
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
import ssafy.haruman.domain.challenge.dto.response.ChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ChallengeUserListResponseDto;
import ssafy.haruman.domain.challenge.dto.response.DailyChallengeResponseDto;
import ssafy.haruman.domain.challenge.dto.response.ExpenseResponseDto;
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
    public ResponseEntity<ResponseWrapper<ChallengeResponseDto>> startChallenge() {
        Profile profile = null;

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
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        Profile profile = null;

        DailyChallengeResponseDto responseDto = challengeService.selectDailyChallenge(profile,
                date);
        return JsonResponse.ok("챌린지 상세내역을 불러왔습니다.", responseDto);
    }

    @GetMapping("/people")
    public ResponseEntity<ResponseWrapper<List<ChallengeUserListResponseDto>>> selectChallengeUserList() {

        List<ChallengeUserListResponseDto> userList = challengeService.selectDailyUserList();

        return JsonResponse.ok("챌린지 중인 회원 목록을 성공적으로 가져왔습니다.", userList);
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void test() {
        System.out.println("now: " + LocalDateTime.now());
    }


}
