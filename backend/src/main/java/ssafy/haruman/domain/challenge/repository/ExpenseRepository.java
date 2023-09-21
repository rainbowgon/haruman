package ssafy.haruman.domain.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.haruman.domain.challenge.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByChallenge_Id(Long challengeId);
}
