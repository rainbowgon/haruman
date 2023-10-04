package ssafy.haruman.domain.deposit.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.entity.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @Column
    @NotBlank
    private float interestRate;

    @Builder
    public Deposit(Profile profile, String bank, String name, String description, float interestRate) {
        this.profile = profile;
        this.bank = bank;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
    }

    public void updateDeposit(String bank, String name, String description, float interestRate) {
        this.bank = bank;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
    }
}
