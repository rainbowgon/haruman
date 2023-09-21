package ssafy.haruman.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.profile.entity.Profile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "oauth_id_unique",
                columnNames = {
                        "oauth_server_id",
                        "oauth_server"
                }
        ),
})
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "member_name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(columnDefinition = "VARCHAR(320)")
    private String email;

    private LocalDateTime brithDate;

    private String password;

    @Embedded
    private OAuthId oAuthId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public Member(String name, String email, LocalDateTime brithDate, String password, OAuthId oAuthId) {
        this.name = name;
        this.email = email;
        this.brithDate = brithDate;
        this.password = password;
        this.oAuthId = oAuthId;
    }

    public Profile createProfile(String nickname) {
        this.profile = Profile.builder()
                .member(this)
                .nickname(nickname)
                .build();
        return this.profile;
    }
}
