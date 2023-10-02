package ssafy.haruman.domain.deposit.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE deposit SET is_valid = 'DELETED' WHERE deposit_id = ?")
@Where(clause = "is_valid = 'VALID'")
public class Deposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(length = 15)
    @NotBlank
    private String bank;

    @Column(length = 25)
    @NotBlank
    private String name;

    @Column(length = 255)
    @NotBlank
    private String description;

    @Column(precision = 4, scale = 2) // 총 4자리 중 소수점 2자리를 위한 예약. 예: 10.99
    private BigDecimal interestRate;

    @Builder
    public Deposit(Profile profile, String bank, String name, String description,BigDecimal interestRate) {
        this.profile = profile;
        this.bank = bank;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
    }

    public void updateDeposit(String bank,String name, String description,BigDecimal interestRate) {
        this.bank = bank;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
    }
}
