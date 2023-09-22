package ssafy.haruman.domain.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.challenge.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByChallenge_Id(Long challengeId);

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE expense\n"
                    + "SET category_id = 1\n"
                    + "WHERE challenge_id IN (SELECT challenge_id FROM challenge WHERE profile_id = :profileId)\n"
                    + "AND category_id = :categoryId")
    Integer updateCategory(Long profileId, Long categoryId);
}
