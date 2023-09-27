package ssafy.haruman.domain.category.repository;

import ssafy.haruman.domain.category.entity.ColorCode;
import ssafy.haruman.domain.category.entity.CustomStatus;

public interface CategoryCountInfoMapping {

    Long getId();

    String getName();

    CustomStatus getStatus();

    ColorCode getColor();

    Integer getCount();

}
