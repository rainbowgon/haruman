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
    @Column(name = "category_id", columnDefinition = "INT UNSIGNED")
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
    private int targetAmount;

    @Column(columnDefinition = "INT UNSIGNED")
    private int usedAmount;

    @Column(columnDefinition = "MEDIUMINT UNSIGNED")
    private int leftoverAmount;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NOT_VIEWED'")
    private ViewStatus isViewed;


}
