package ssafy.haruman.domain.category.service;

import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;
import ssafy.haruman.domain.category.dto.response.CategoryWithCountResponseDto;
import ssafy.haruman.domain.profile.entity.Profile;

import java.util.List;

public interface CategoryService {

    CategorySimpleResponseDto createCategory(Profile profile, CategoryCreateRequestDto createDto);

    CategorySimpleResponseDto updateCategory(Profile profile, CategoryUpdateRequestDto updateDto);

    Integer deleteCategory(Profile profile, Long categoryId);

    List<CategoryWithCountResponseDto> selectCategoryList(Profile profile);

    List<CategoryDetailResponseDto> selectCustomCategoryList(Profile profile);

}
