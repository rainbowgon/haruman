package ssafy.haruman.domain.category.service;

import java.util.List;
import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;

public interface CategoryService {

    CategorySimpleResponseDto createCategory(CategoryCreateRequestDto createDto);

    CategorySimpleResponseDto updateCategory(CategoryUpdateRequestDto updateDto);

    void deleteCategory(Long categoryId);

    List<CategoryDetailResponseDto> selectCategoryList();

    List<CategoryDetailResponseDto> selectCustomCategoryList();

    List<CategoryDetailResponseDto> selectOftenCategoryList();

}
