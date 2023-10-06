package ssafy.haruman.domain.category.dto.response;

import lombok.*;
import ssafy.haruman.domain.category.entity.ColorCode;
import ssafy.haruman.domain.category.entity.CustomStatus;
import ssafy.haruman.domain.category.repository.CategoryCountInfoMapping;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategoryWithCountResponseDto {

    private Long categoryId;
    private String name;
    private CustomStatus isDefault;
    private ColorCode color;
    private Integer cnt;

    public static CategoryWithCountResponseDto from(CategoryCountInfoMapping category) {
        return CategoryWithCountResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .isDefault(category.getStatus())
                .color(category.getColor())
                .cnt(category.getCount())
                .build();
    }
}
