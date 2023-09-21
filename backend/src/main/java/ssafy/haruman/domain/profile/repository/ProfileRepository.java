package ssafy.haruman.domain.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.haruman.domain.profile.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
