package ssafy.haruman.domain.category.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.member.entity.Member;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM (SELECT c FROM Category c WHERE c.isDefault = 'DEFAULT' OR c.member = :member) WHERE c.name = :name")
    Optional<Category> findByName(Member member, String name);

    @Query("SELECT c FROM Category c WHERE c.member = :member")
    List<Category> findAllByProfile(Member member);

    @Query("SELECT c FROM Category c WHERE c.isDefault = 'DEFAULT' OR c.member = :member")
    List<Category> findAllByProfileAndStatus(Member member);

}
