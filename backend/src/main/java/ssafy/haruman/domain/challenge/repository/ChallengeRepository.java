package ssafy.haruman.domain.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ssafy.haruman.domain.challenge.entity.Challenge;

import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select count(*) from Challenge c where c.challengeStatus='PROGRESS'")
    Integer countByStatus();

    @Query(value = "SELECT * FROM challenge c JOIN profile USING (profile_id) WHERE c.profile_id =:id ORDER BY c.start_time DESC LIMIT 1;", nativeQuery = true)
    Challenge findFirstChallenge(@Param(value = "id") Long profileId);

    @Query("select c from Challenge c where c.challengeStatus='PROGRESS'")
    List<Challenge> findAllByStatus();

    @Query(nativeQuery = true,
            value = "SELECT p.nickname nickname, f.saved_path profileImage, c.start_time startTime, c.used_amount usedAmount, e.created_at createdAt\n" +
                    "FROM challenge c\n" +
                    "LEFT JOIN (SELECT challenge_id, MAX(created_at) created_at FROM expense WHERE is_valid = 'VALID' GROUP BY challenge_id) e\n" +
                    "ON c.challenge_id = e.challenge_id\n" +
                    "LEFT JOIN profile p ON p.profile_id = c.profile_id\n" +
                    "LEFT JOIN profile_image i ON p.profile_id = i.profile_id\n" +
                    "LEFT JOIN file f ON f.file_id = i.file_id\n" +
                    "WHERE c.challenge_status = 'PROGRESS' AND c.is_valid = 'VALID' AND p.is_valid = 'VALID' AND f.is_valid = 'VALID'\n" +
                    "ORDER BY e.created_at IS NULL ASC, c.start_time DESC")
    List<ChallengeUserInfoMapping> findChallengeAndExpenseAndProfileByStatus();

    @Query("SELECT SUM(c.leftoverAmount) FROM Challenge c WHERE c.profile.id = :profileId AND c.challengeStatus = 'SUCCESS'")
    Integer sumByProfileAndStatus(Long profileId);

    @Query("SELECT c FROM Challenge c WHERE c.profile.id = :profileId AND SUBSTR(c.startTime, 1, 7) = :date ORDER BY c.startTime")
    List<Challenge> findAllByProfileAndDate(Long profileId, String date);

}
