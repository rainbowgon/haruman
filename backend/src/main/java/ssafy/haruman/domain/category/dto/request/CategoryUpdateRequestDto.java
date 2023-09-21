package ssafy.haruman.domain.category.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.category.entity.ColorCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryUpdateRequestDto {

    private Long categoryId;
    private String name;
    private ColorCode color;

}
