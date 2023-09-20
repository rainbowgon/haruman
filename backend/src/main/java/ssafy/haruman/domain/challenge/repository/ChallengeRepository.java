package ssafy.haruman.domain.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.challenge.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select count(*) from Challenge c where c.challengeStatus='PROGRESS'")
    Integer countByStatus();

    Challenge findFirstChallenge();

    @Query("select c from Challenge c where c.challengeStatus='PROGRESS'")
    List<Challenge> findAllByStatus();

    @Query(nativeQuery = true,
            value = "SELECT p.nickname, f.saved_path, c.start_time, c.used_amount, MAX(e.created_at)\n"
                    + "FROM challenge c\n"
                    + "JOIN expense e USING (challenge_id)\n"
                    + "JOIN profile p USING (profile_id)\n"
                    + "LEFT JOIN profile_image i USING (profile_id)\n"
                    + "LEFT JOIN file f ON (f.file_id = i.file_id)\n"
                    + "WHERE c.challenge_status = 'PROGRESS'\n"
                    + "GROUP BY e.challenge_id\n"
                    + "ORDER BY e.created_at, c.start_time")
    List<ChallengeUserInfoMapping> findChallengeAndExpenseAndProfileByStatus();
}
