package ssafy.haruman.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ssafy.haruman.domain.profile.dto.request.ProfileCreateRequestDto;
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

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ResponseWrapper<SingleProfileResponseDto>> createProfile(
            @RequestPart(value = "profile-value") ProfileCreateRequestDto profileCreateRequestDto,
            @RequestPart(required = false) MultipartFile profileImage) throws IOException {
        SingleProfileResponseDto singleProfileResponseDto = profileService.createProfile(profileCreateRequestDto, profileImage);
        return JsonResponse.ok("프로필이 반환되었습니다.", singleProfileResponseDto);
    }

}
