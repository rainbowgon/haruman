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
    @Column(name = "file_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    private String savedFilename;

    private String savedPath;

    public File(String savedFilename, String savedPath) {
        this.savedFilename = savedFilename;
        this.savedPath = savedPath;
    }
}
