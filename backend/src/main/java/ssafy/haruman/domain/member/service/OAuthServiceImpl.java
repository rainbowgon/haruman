package ssafy.haruman.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.domain.member.repository.MemberRepository;
import ssafy.haruman.domain.profile.service.ProfileService;
import ssafy.haruman.global.oauth.authcode.AuthCodeRequestUrlProviderComposite;
import ssafy.haruman.global.oauth.client.OAuthMemberClientComposite;
import ssafy.haruman.global.oauth.dto.OAuthResponseDto;
import ssafy.haruman.global.utils.JwtUtil;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    @Value("${jwt.secret}")
    private String secretKey;
    private final Long expiredMs = 1000 * 60 * 60L;

    private final MemberRepository memberRepository;
    private final ProfileService profileService;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OAuthMemberClientComposite oauthMemberClientComposite;

    public String getAuthCodeRequestUrl(OAuthServerType oAuthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oAuthServerType);
    }

    public String oauthLogin(OAuthServerType oauthServerType, String authCode) {
        OAuthResponseDto oauthResponseDto = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        Member member = memberRepository.findByOauthId(oauthResponseDto.getOAuthId())
                .orElseGet(() -> {
                    Member newMember = Member.builder().oauthId(oauthResponseDto.getOAuthId()).build();
                    Member savedMember = memberRepository.save(newMember);
                    profileService.saveProfileFromOAuth(savedMember, oauthResponseDto.getNickname(), oauthResponseDto.getProfileImageUrl());
                    return savedMember;
                });
        return JwtUtil.createJwt(member.getId(), secretKey, expiredMs);
    }
}
