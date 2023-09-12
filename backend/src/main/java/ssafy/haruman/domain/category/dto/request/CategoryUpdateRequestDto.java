package ssafy.haruman.domain.category.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryUpdateRequestDto {

    private Long categoryId;
    private String name;

}
