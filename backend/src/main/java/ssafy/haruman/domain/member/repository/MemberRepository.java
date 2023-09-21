package ssafy.haruman.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.domain.member.entity.OAuthId;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOAuthId(OAuthId oauthId);

}
