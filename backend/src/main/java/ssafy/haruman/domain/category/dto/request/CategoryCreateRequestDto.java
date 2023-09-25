package ssafy.haruman.domain.category.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.entity.ColorCode;
import ssafy.haruman.domain.category.entity.CustomStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryCreateRequestDto {

    private String name;
    private ColorCode color;

}
