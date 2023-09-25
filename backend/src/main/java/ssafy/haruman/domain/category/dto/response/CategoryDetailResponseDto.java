package ssafy.haruman.domain.category.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.repository.CategoryCountInfoMapping;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategoryDetailResponseDto {

    private Long categoryId;
    private String name;
    private String isDefault;
    private String color;
    private Integer cnt;

    public static CategoryDetailResponseDto from(Category category) {
        return CategoryDetailResponseDto.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .isDefault(String.valueOf(category.getIsDefault()))
                .color(String.valueOf(category.getColor()))
                .build();
    }

    public static CategoryDetailResponseDto fromCountInfo(CategoryCountInfoMapping category) {
        return CategoryDetailResponseDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .isDefault(String.valueOf(category.getIsDefault()))
                .color(String.valueOf(category.getColor()))
                .cnt(category.getCount())
                .build();
    }

}
