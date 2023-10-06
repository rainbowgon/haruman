package ssafy.haruman.domain.category.dto.response;

import lombok.*;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.entity.ColorCode;
import ssafy.haruman.domain.category.entity.CustomStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategoryDetailResponseDto {

    private Long categoryId;
    private String name;
    private CustomStatus isDefault;
    private ColorCode color;

    public static CategoryDetailResponseDto from(Category category) {
        return CategoryDetailResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .isDefault(category.getIsDefault())
                .color(category.getColor())
                .build();
    }

}
