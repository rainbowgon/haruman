package ssafy.haruman.domain.challenge.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.hibernate.annotations.ColumnDefault;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'PROGRESS'")
    private ChallengeStatus challengeStatus;

    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    private Integer targetAmount;

    @Column(columnDefinition = "INT UNSIGNED")
    private Integer usedAmount;

    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    private Integer leftoverAmount;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NOT_VIEWED'")
    private ViewStatus isViewed;

    @Builder
    public Challenge(Profile profile, LocalDateTime startTime, LocalDateTime endTime,
            ChallengeStatus challengeStatus, Integer targetAmount, Integer usedAmount,
            Integer leftoverAmount, ViewStatus isViewed) {
        this.profile = profile;
        this.startTime = startTime;
        this.endTime = endTime;
        this.challengeStatus = challengeStatus;
        this.targetAmount = targetAmount;
        this.usedAmount = usedAmount;
        this.leftoverAmount = leftoverAmount;
        this.isViewed = isViewed;
    }

    public void updateChallengeAmount(Integer usedAmount, Integer leftoverAmount) {
        this.usedAmount = usedAmount;
        this.leftoverAmount = leftoverAmount;
    }

    public void updateViewStatus(ViewStatus isViewed) {
        this.isViewed = isViewed;
    }

    public void updateChallengeStatus(ChallengeStatus challengeStatus) {
        this.challengeStatus = challengeStatus;
    }


}
