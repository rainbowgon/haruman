package ssafy.haruman.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private UUID id;
}
