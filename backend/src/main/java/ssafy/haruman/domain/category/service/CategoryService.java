package ssafy.haruman.domain.category.service;

import java.util.List;
import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

public interface CategoryService {

    CategorySimpleResponseDto createCategory(Profile profile, CategoryCreateRequestDto createDto);

    CategorySimpleResponseDto updateCategory(Profile profile, CategoryUpdateRequestDto updateDto);

    Integer deleteCategory(Profile profile, Long categoryId);

    List<CategoryDetailResponseDto> selectCategoryList(Profile profile);

    List<CategoryDetailResponseDto> selectCustomCategoryList(Profile profile);

}
