package ssafy.haruman.domain.category.dto.response;

import lombok.*;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.entity.ColorCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategorySimpleResponseDto {

    private String name;
    private ColorCode color;

    public static CategorySimpleResponseDto from(Category category) {
        return CategorySimpleResponseDto.builder()
                .name(category.getName())
                .color(category.getColor())
                .build();
    }

}
