package ssafy.haruman.domain.deposit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ssafy.haruman.domain.deposit.dto.request.DepositCreateRequestDto;
import ssafy.haruman.domain.deposit.dto.request.DepositUpdateRequestDto;
import ssafy.haruman.domain.deposit.dto.response.DepositDetailResponseDto;
import ssafy.haruman.domain.deposit.dto.response.DepositSimpleResponseDto;
import ssafy.haruman.domain.deposit.service.DepositService;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.global.response.JsonResponse;
import ssafy.haruman.global.response.ResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DeopositController {

    private final DepositService depositService;

    /**
     * 유저에게 적합한 적금을 받아와 저장합니다.
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper<DepositSimpleResponseDto>> createDeposit(
            @AuthenticationPrincipal Member member,
            @RequestBody DepositCreateRequestDto createDto) {

        DepositSimpleResponseDto createdDeposit = depositService.createDeposit(member.getProfile(), createDto);

        return JsonResponse.of(HttpStatus.CREATED, "적금이 성공적으로 저장되었습니다.", createdDeposit);
    }

    /**
     * 유저에게 적합한 적금을 받아와 저장합니다.(한꺼번에)
     */
    @PostMapping("/list")
    public ResponseEntity<ResponseWrapper<List<DepositSimpleResponseDto>>> createDepositList(
//    public ResponseEntity<ResponseWrapper<Nullable>> createDepositList(
            @AuthenticationPrincipal Member member,
            @RequestBody List<DepositCreateRequestDto> createDto) {

        List<DepositSimpleResponseDto> createdDeposit = depositService.createDepositList(member.getProfile(), createDto);
        depositService.createDepositList(member.getProfile(), createDto);

//        return JsonResponse.of(HttpStatus.CREATED, "적금들이 성공적으로 저장되었습니다.");
        return JsonResponse.of(HttpStatus.CREATED, "적금들이 성공적으로 저장되었습니다.", createdDeposit);
    }

    /**
     * 만약 이전에 추천받은 적금이 있다면, 기존에 저장된 적금 정보를 새로 추천받은 적금 정보로 업데이트합니다.
     */
    @PatchMapping
    public ResponseEntity<ResponseWrapper<DepositSimpleResponseDto>> updateDeposit(
            @AuthenticationPrincipal Member member,
            @RequestBody DepositUpdateRequestDto updateDto) {

        DepositSimpleResponseDto updatedDeposit = depositService.updateDeposit(member.getProfile(), updateDto);

        return JsonResponse.ok("적금이 성공적으로 수정되었습니다.", updatedDeposit);
    }

    /**
     * 추천받았던 적금 정보를 삭제합니다.
     */
    @DeleteMapping("/{deposit-id}")
    public ResponseEntity<ResponseWrapper<Nullable>> deleteDeposit(
            @AuthenticationPrincipal Member member,
            @PathVariable("deposit-id") Long depositId) {

        depositService.deleteDeposit(member.getProfile(), depositId);

        return JsonResponse.ok("적금을 성공적으로 삭제했습니다.");
    }


    /**
     * 적금 목록 전체를 조회합니다.
     */
    @GetMapping
    public ResponseEntity<ResponseWrapper<List<DepositSimpleResponseDto>>> selectDepositSimpleList(
            @AuthenticationPrincipal Member member) {

        List<DepositSimpleResponseDto> depositSimpleList = depositService.selectDepositSimpleList(member.getProfile());

        return JsonResponse.ok("적금 목록(simple)을 성공적으로 가져왔습니다.", depositSimpleList);
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseWrapper<List<DepositDetailResponseDto>>> selectDepositDetailList(
            @AuthenticationPrincipal Member member) {

        List<DepositDetailResponseDto> depositDetailList = depositService.selectDepositDetailList(member.getProfile());

        return JsonResponse.ok("적금 목록(detail)을 성공적으로 가져왔습니다.", depositDetailList);
    }

}