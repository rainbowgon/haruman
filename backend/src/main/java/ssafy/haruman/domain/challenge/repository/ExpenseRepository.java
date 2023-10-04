package ssafy.haruman.domain.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.challenge.entity.Challenge;
import ssafy.haruman.domain.challenge.entity.Expense;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByChallenge(Challenge challenge);

    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE expense\n" +
                    "SET category_id = 1\n" +
                    "WHERE challenge_id IN (SELECT challenge_id FROM challenge WHERE profile_id = :profileId)\n" +
                    "AND category_id = :categoryId")
    Integer updateCategory(Long profileId, Long categoryId);

}
