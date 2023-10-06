package ssafy.haruman.domain.profile.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.global.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE profile SET is_valid = 'DELETED' WHERE profile_id = ?")
@Where(clause = "is_valid = 'VALID'")
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @Column(columnDefinition = "VARCHAR(20)")
    private String nickname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileImage profileImage;

    @Builder
    public Profile(Member member, String nickname) {
        this.member = member;
        this.nickname = nickname;
    }

    public void updateProfile(String nickname) {
        this.nickname = nickname;
    }

    public void deleteProfileImage() {
        this.profileImage = null;
    }

    public void uploadNewProfileImage(String savedPath, String savedFilename) {
        this.profileImage = ProfileImage.builder()
                .savedPath(savedPath)
                .savedFilename(savedFilename)
                .profile(this)
                .build();
    }
}
