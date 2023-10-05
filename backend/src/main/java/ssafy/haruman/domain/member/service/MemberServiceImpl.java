package ssafy.haruman.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.member.dto.request.DummyMemberProfileCreateRequestDto;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.member.repository.MemberRepository;
import ssafy.haruman.domain.profile.service.ProfileService;
import ssafy.haruman.global.utils.JwtUtil;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProfileService profileService;

    @Value("${jwt.secret}")
    private String secretKey;

    private final Long expiredMs = 1000 * 60 * 60L;

    private final String PROFILE_IMAGE_URL = "https://picsum.photos/200/300";

    @Override
    public String createDummyMemberProfile(DummyMemberProfileCreateRequestDto createRequestDto) {
        Member newMember = createRequestDto.toMemberEntity();
        Member savedMember = memberRepository.save(newMember);
        profileService.saveProfileFromOAuth(savedMember, createRequestDto.getNickname(), PROFILE_IMAGE_URL);
        return JwtUtil.createJwt(savedMember.getId(), secretKey, expiredMs);
    }
}
