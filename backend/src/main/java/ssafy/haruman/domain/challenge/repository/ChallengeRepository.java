package ssafy.haruman.domain.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.challenge.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query(nativeQuery = true,
            value = "SELECT c.challenge_id, c.profile_id, c.start_time, c.used_amount, MAX(e.created_at)"
                    + "FROM challenge c LEFT JOIN expense e"
                    + "ON c.challenge_id = e.challenge_id"
                    + "WHERE c.challenge_status = 'PROGRESS'"
                    + "GROUP BY c.challenge_id"
                    + "ORDER BY e.created_at, c.start_time")
    List<ChallengeUserListMapping> findChallengesByStatus();
}
