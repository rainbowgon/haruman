package ssafy.haruman.domain.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.haruman.domain.deposit.entity.GPTAnswer;

public interface GPTAnswerRepository extends JpaRepository<GPTAnswer, Integer> {
}
