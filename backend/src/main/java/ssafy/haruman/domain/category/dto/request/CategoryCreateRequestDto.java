package ssafy.haruman.domain.category.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.entity.CustomStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryCreateRequestDto {

    private String name;

    public Category toEntity() {
        return Category.builder()
                // TODO 회원 프로필 추가
                .name(name)
                .isDefault(CustomStatus.CUSTOM)
                .build();
    }


}
