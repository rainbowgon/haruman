package ssafy.haruman.domain.category.entity;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(length = 20)
    @NotBlank
    private String name;

    @Column(length = 10)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private CustomStatus isDefault;

    @Column(length = 15)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ColorCode color;

    @Builder
    public Category(Profile profile, String name, CustomStatus isDefault, ColorCode color) {
        this.profile = profile;
        this.name = name;
        this.isDefault = isDefault;
        this.color = color;
    }

    public void updateCategory(String name, ColorCode color) {
        this.name = name;
        this.color = color;
    }

}
