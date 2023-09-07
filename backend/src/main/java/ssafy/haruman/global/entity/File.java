package ssafy.haruman.global.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id", columnDefinition = "UNSIGNED INT")
    private Long id;

    private String originalFilename;

    private String savedFilename;

    private String savedPath;

    public File(String originalFilename, String savedFilename, String savedPath) {
        this.originalFilename = originalFilename;
        this.savedFilename = savedFilename;
        this.savedPath = savedPath;
    }
}
