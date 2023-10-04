package ssafy.haruman.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.member.entity.OAuthId;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

    Optional<Member> findByOauthId(OAuthId oauthId);
}
