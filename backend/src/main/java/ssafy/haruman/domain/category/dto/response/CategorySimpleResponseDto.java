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
public class CategorySimpleResponseDto {

    private String name;
    private String color;

    public static CategorySimpleResponseDto from(Category category) {
        return CategorySimpleResponseDto.builder()
                .name(category.getName())
                .color(String.valueOf(category.getColor()))
                .build();
    }

}
