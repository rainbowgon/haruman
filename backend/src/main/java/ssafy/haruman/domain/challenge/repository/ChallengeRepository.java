package ssafy.haruman.domain.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.haruman.domain.challenge.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

}
