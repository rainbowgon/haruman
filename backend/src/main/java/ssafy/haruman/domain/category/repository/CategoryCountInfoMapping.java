package ssafy.haruman.domain.category.repository;

import ssafy.haruman.domain.category.entity.ColorCode;
import ssafy.haruman.domain.category.entity.CustomStatus;

public interface CategoryCountInfoMapping {

    Long getCategoryId();

    String getName();

    CustomStatus getIsDefault();

    ColorCode getColor();

    Integer getCount();

}
