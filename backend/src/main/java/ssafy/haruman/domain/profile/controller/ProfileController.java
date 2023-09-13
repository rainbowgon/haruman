package ssafy.haruman.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping
    public String hello() {
        System.out.println("ProfileController.hello");
        return "hello";
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<SingleProfileResponseDto>> createProfile(
            @RequestParam String nickname,
            @RequestParam MultipartFile profileImage) throws IOException {
        SingleProfileResponseDto singleProfileResponseDto = profileService.createProfile(nickname, profileImage);
        return JsonResponse.ok("프로필이 반환되었습니다.", singleProfileResponseDto);
    }

    @PatchMapping("/{profile-id}")
    public ResponseEntity<ResponseWrapper<SingleProfileResponseDto>> updateProfile(
            @PathVariable("profile-id") Long profileId,
            @RequestParam String nickname,
            @RequestParam MultipartFile profileImage) throws IOException {
        SingleProfileResponseDto singleProfileResponseDto = profileService.updateProfile(profileId, nickname, profileImage);
        return JsonResponse.ok("프로필이 성공적으로 수정되었습니다.", singleProfileResponseDto);
    }


}
