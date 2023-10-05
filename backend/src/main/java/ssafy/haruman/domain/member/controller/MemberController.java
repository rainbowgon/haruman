package ssafy.haruman.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.haruman.domain.member.dto.request.DummyMemberProfileCreateRequestDto;
import ssafy.haruman.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<?> createMemberAndProfile(@RequestBody DummyMemberProfileCreateRequestDto createRequestDto) {
        System.out.println("MemberController.createMemberAndProfile");
        String jwtToken = memberService.createDummyMemberProfile(createRequestDto);
        return ResponseEntity.ok(jwtToken);
    }
}
