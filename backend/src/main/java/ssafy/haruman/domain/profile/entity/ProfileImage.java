package ssafy.haruman.domain.profile.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.global.entity.File;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue(value = "ProfileImage")
public class ProfileImage extends File {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public ProfileImage(String savedFilename, String savedPath, Profile profile) {
        super(savedFilename, savedPath);
        this.profile = profile;
    }
}
