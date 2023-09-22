package ssafy.haruman.domain.category.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.profile.entity.Profile;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM (SELECT c FROM Category c WHERE c.isDefault = 'DEFAULT' OR c.profile = :profile) WHERE c.name = :name")
    Optional<Category> findByName(Profile profile, String name);

    @Query("SELECT c FROM Category c WHERE c.profile = :profile")
    List<Category> findAllByProfile(Profile profile);

    @Query("SELECT c FROM Category c WHERE c.isDefault = 'DEFAULT' OR c.profile = :profile")
    List<Category> findAllByProfileAndStatus(Profile profile);

}
