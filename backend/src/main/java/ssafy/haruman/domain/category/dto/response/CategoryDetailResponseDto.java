package ssafy.haruman.domain.category.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.category.entity.Category;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategoryDetailResponseDto {

    private Long categoryId;
    private String name;
    private String isDefault;

    public static CategoryDetailResponseDto from(Category category) {
        return CategoryDetailResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .isDefault(String.valueOf(category.getIsDefault()))
                .build();
    }

}
