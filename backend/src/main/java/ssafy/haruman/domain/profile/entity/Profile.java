package ssafy.haruman.domain.profile.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id", columnDefinition = "UNSIGNED INT")
    private Long id;

    @Column(columnDefinition = "VARCHAR(20)")
    private String nickname;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfileImage profileImage;

    @Builder
    public Profile(String nickname) {
        this.nickname = nickname;
    }

    public void uploadNewProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

}
