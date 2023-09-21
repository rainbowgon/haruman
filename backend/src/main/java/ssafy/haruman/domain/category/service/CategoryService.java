package ssafy.haruman.domain.category.service;

import java.util.List;
import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;
import ssafy.haruman.domain.member.entity.Member;

public interface CategoryService {

    CategorySimpleResponseDto createCategory(Member member, CategoryCreateRequestDto createDto);

    CategorySimpleResponseDto updateCategory(Member member, CategoryUpdateRequestDto updateDto);

    void deleteCategory(Member member, Long categoryId);

    List<CategoryDetailResponseDto> selectCategoryList(Member member);

    List<CategoryDetailResponseDto> selectCustomCategoryList(Member member);

}
