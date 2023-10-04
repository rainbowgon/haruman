package ssafy.haruman.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.profile.dto.response.SingleProfileResponseDto;
import ssafy.haruman.domain.profile.service.ProfileService;
import ssafy.haruman.global.response.JsonResponse;
import ssafy.haruman.global.response.ResponseWrapper;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<SingleProfileResponseDto>> createProfile(
            @AuthenticationPrincipal Member member,
            @RequestParam String nickname,
            @RequestParam(required = false) MultipartFile profileImage) throws IOException {
        SingleProfileResponseDto singleProfileResponseDto = profileService.createProfile(member, nickname, profileImage);
        return JsonResponse.of(HttpStatus.CREATED, "프로필이 성공적으로 생성되었습니다.", singleProfileResponseDto);
    }

    @PatchMapping
    public ResponseEntity<ResponseWrapper<SingleProfileResponseDto>> updateProfile(
            @AuthenticationPrincipal Member member,
            @RequestParam String nickname,
            @RequestParam(required = false) MultipartFile profileImage) throws IOException {
        SingleProfileResponseDto singleProfileResponseDto = profileService.updateProfile(member.getProfile(), nickname, profileImage);
        return JsonResponse.ok("프로필이 성공적으로 수정되었습니다.", singleProfileResponseDto);
    }

    @GetMapping("/mine")
    public ResponseEntity<ResponseWrapper<SingleProfileResponseDto>> selectMyProfile(
            @AuthenticationPrincipal Member member) {
        SingleProfileResponseDto singleProfileResponseDto = profileService.selectOneProfile(member.getProfile());
        return JsonResponse.ok("프로필을 성공적으로 가져왔습니다.", singleProfileResponseDto);
    }

    @GetMapping("/{profile-id}")
    public ResponseEntity<ResponseWrapper<SingleProfileResponseDto>> selectOneProfile(
            @PathVariable("profile-id") Long profileId) {
        SingleProfileResponseDto singleProfileResponseDto = profileService.selectOneProfileById(profileId);
        return JsonResponse.ok("프로필을 성공적으로 가져왔습니다.", singleProfileResponseDto);
    }
}
