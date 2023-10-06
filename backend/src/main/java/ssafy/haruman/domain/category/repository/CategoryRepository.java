package ssafy.haruman.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.profile.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE (c.profile = :profile OR c.isDefault = 'DEFAULT') AND c.name = :name")
    Optional<Category> findByProfileAndStatusAndName(Profile profile, String name);

    @Query("SELECT c FROM Category c WHERE c.profile = :profile")
    List<Category> findAllByProfile(Profile profile);

    @Query("SELECT c FROM Category c WHERE c.profile = :profile OR c.isDefault = 'DEFAULT'")
    List<Category> findAllByProfileAndStatus(Profile profile);

    @Query(nativeQuery = true,
            value = "SELECT c.category_id id, c.name name, c.is_default status, c.color color, e.cnt count\n" +
                    "FROM\n" +
                    "(SELECT * FROM category WHERE (is_default = 'DEFAULT' OR profile_id = :profileId) AND is_valid = 'VALID') c\n" +
                    "LEFT JOIN\n" +
                    "(SELECT category_id, COUNT(*) cnt FROM expense WHERE challenge_id IN (SELECT challenge_id FROM challenge WHERE profile_id = :profileId) GROUP BY category_id) e\n" +
                    "ON c.category_id = e.category_id\n" +
                    "ORDER BY e.cnt")
    List<CategoryCountInfoMapping> findAllByProfileOrderByCount(Long profileId);

}
