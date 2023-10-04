package ssafy.haruman.domain.challenge.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.global.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE expense SET is_valid = 'DELETED' WHERE expense_id = ?")
@Where(clause = "is_valid = 'VALID'")
public class Expense extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime payTime;

    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    private Integer payAmount;

    private String content;

    @Builder
    public Expense(Challenge challenge, Category category, LocalDateTime payTime, Integer payAmount, String content) {
        this.challenge = challenge;
        this.category = category;
        this.payTime = payTime;
        this.payAmount = payAmount;
        this.content = content;
    }

    public void updateExpense(Category category, Integer payAmount, String content) {
        this.category = category;
        this.payAmount = payAmount;
        this.content = content;
    }

}
