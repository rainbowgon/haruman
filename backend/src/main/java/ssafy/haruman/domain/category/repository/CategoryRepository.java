package ssafy.haruman.domain.category.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // 쿼리 추후 수정
    @Query(nativeQuery = true,
            value = "SELECT * "
                    + "FROM (SELECT * FROM category WHERE is_default = 'DEFAULT' OR profile_id = :profileId) "
                    + "WHERE name = :name")
    Optional<Category> findByName(String name, Long profileId);

    @Query(nativeQuery = true, value = "SELECT * FROM category WHERE profile_id = :profileId")
    List<Category> findAllByProfile(Long profileId);

    @Query(nativeQuery = true, value = "SELECT * FROM category WHERE profile_id = :profileId OR is_default = :isDefault")
    List<Category> findAllByProfileAndStatus(Long profileId, String isDefault);


}
