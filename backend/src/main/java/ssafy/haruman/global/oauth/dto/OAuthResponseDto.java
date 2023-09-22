package ssafy.haruman.global.oauth.dto;

import lombok.*;
import ssafy.haruman.domain.member.entity.OAuthId;
import ssafy.haruman.domain.member.entity.OAuthServerType;
import ssafy.haruman.global.oauth.kakao.dto.KakaoMemberResponse;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthResponseDto {

    private OAuthId oAuthId;
    private String nickname;
    private String profileImageUrl;

    public static OAuthResponseDto fromKakao(KakaoMemberResponse kakaoMemberResponse) {
        return OAuthResponseDto.builder()
                .oAuthId(new OAuthId(String.valueOf(kakaoMemberResponse.getId()), OAuthServerType.KAKAO))
                .nickname(kakaoMemberResponse.getKakaoAccount().getProfile().getNickname())
                .profileImageUrl(kakaoMemberResponse.getKakaoAccount().getProfile().getProfileImageUrl())
                .build();
    }
}
