package ssafy.haruman.domain.member.entity;

import ssafy.haruman.domain.profile.entity.Profile;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile_id")
    private Profile profile;

}
