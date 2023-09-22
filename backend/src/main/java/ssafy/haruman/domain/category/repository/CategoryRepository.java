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

    @Query(nativeQuery = true,
            value = "SELECT c.category_id, c.name, c.is_default, c.color, e.cnt\n"
                    + "FROM \n"
                    + "(SELECT * FROM category WHERE is_default = 'DEFAULT' OR profile_id = :profileId) c\n"
                    + "LEFT JOIN \n"
                    + "(SELECT category_id, COUNT(*) cnt FROM expense WHERE challenge_id IN (SELECT challenge_id FROM challenge WHERE profile_id = :profileId) GROUP BY category_id) e\n"
                    + "USING (category_id)\n"
                    + "ORDER BY e.cnt")
    List<CategoryCountInfoMapping> findAllByProfileOrderByCount(Long profileId);

}
