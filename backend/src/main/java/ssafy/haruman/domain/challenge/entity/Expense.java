package ssafy.haruman.domain.challenge.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.challenge.dto.request.ExpenseCreateRequestDto;
import ssafy.haruman.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    public Expense(Challenge challenge, Category category, LocalDateTime payTime, Integer payAmount,
            String content) {
        this.challenge = challenge;
        this.category = category;
        this.payTime = payTime;
        this.payAmount = payAmount;
        this.content = content;
    }

    public static Expense createExpense(Challenge challenge, Category category,
            ExpenseCreateRequestDto createRequestDto, LocalDateTime payTime) {
        Expense expense = Expense.builder()
                .challenge(challenge)
                .category(category)
                .payTime(payTime)
                .payAmount(createRequestDto.getPayAmount())
                .content(createRequestDto.getContent())
                .build();

        return expense;
    }

    public void updateExpense(Category category, Integer payAmount, String content) {
        this.category = category;
        this.payAmount = payAmount;
        this.content = content;
    }


}
