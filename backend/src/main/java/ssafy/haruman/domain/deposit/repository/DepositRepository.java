package ssafy.haruman.domain.deposit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.deposit.entity.Deposit;
import ssafy.haruman.domain.profile.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

    @Query("SELECT d FROM Deposit d WHERE d.profile = :profile AND d.name = :name")
    Optional<Deposit> findByProfileAndName(Profile profile, String name);

    @Query("SELECT d FROM Deposit d WHERE d.profile = :profile")
    List<Deposit> findAllByProfile(Profile profile);


}
