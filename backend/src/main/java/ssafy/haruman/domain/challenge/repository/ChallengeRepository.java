package ssafy.haruman.domain.challenge.repository;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.challenge.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select c from Challenge c join fetch c.profile where c.profile.id=:profileId and  cast(c.startTime as date)=:date")
    Challenge findByProfileAndDate(Long profileId, Date date);
}
