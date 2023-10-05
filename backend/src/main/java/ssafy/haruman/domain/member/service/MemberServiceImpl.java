package ssafy.haruman.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.member.dto.request.DummyMemberProfileCreateRequestDto;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.member.repository.MemberRepository;
import ssafy.haruman.domain.profile.service.ProfileService;
import ssafy.haruman.global.utils.JwtUtil;
import ssafy.haruman.global.utils.UrlUtil;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProfileService profileService;

    @Value("${jwt.secret}")
    private String secretKey;

    private final Long expiredMs = 1000 * 60 * 60L;

    private final String RANDOM_PROFILE_IMAGE_URL = "https://source.unsplash.com/random/300x300/?portrait,user";

    @Override
    public String createDummyMemberProfile(DummyMemberProfileCreateRequestDto createRequestDto) {
        Member newMember = createRequestDto.toMemberEntity();
        Member savedMember = memberRepository.save(newMember);
        String finalURL = UrlUtil.getFinalURL(RANDOM_PROFILE_IMAGE_URL);
        profileService.saveProfileFromOAuth(savedMember, createRequestDto.getNickname(), finalURL);
        return JwtUtil.createJwt(savedMember.getId(), secretKey, expiredMs);
    }
}
