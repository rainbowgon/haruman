package ssafy.haruman.domain.member.service;

import ssafy.haruman.domain.member.dto.request.DummyMemberProfileCreateRequestDto;

public interface MemberService {

    String createDummyMemberProfile(DummyMemberProfileCreateRequestDto createRequestDto);
}
