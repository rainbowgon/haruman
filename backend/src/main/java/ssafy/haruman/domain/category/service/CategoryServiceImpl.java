package ssafy.haruman.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;
import ssafy.haruman.domain.category.dto.response.CategoryWithCountResponseDto;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.entity.CustomStatus;
import ssafy.haruman.domain.category.repository.CategoryCountInfoMapping;
import ssafy.haruman.domain.category.repository.CategoryRepository;
import ssafy.haruman.domain.challenge.repository.ExpenseRepository;
import ssafy.haruman.domain.profile.entity.Profile;
import ssafy.haruman.global.error.exception.CategoryDuplicateException;
import ssafy.haruman.global.error.exception.CategoryNotFoundException;
import ssafy.haruman.global.error.exception.CategoryUnauthorizedException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public CategorySimpleResponseDto createCategory(
            Profile profile, CategoryCreateRequestDto createDto) {

        Optional<Category> category =
                categoryRepository.findByProfileAndStatusAndName(profile, createDto.getName());

        if (category.isPresent()) {
            throw CategoryDuplicateException.EXCEPTION;
        }

        Category createdCategory = Category.builder()
                .profile(profile)
                .name(createDto.getName())
                .isDefault(CustomStatus.CUSTOM)
                .color(createDto.getColor())
                .build();

        return CategorySimpleResponseDto.from(categoryRepository.save(createdCategory));
    }

    @Override
    @Transactional
    public CategorySimpleResponseDto updateCategory(
            Profile profile, CategoryUpdateRequestDto updateDto) {

        Category category = findOneCategoryById(updateDto.getCategoryId());

        if (category.getIsDefault() == CustomStatus.DEFAULT || !category.getProfile().getId().equals(profile.getId())) {
            throw CategoryUnauthorizedException.EXCEPTION;
        }

        category.updateCategory(updateDto.getName(), updateDto.getColor());

        return CategorySimpleResponseDto.from(category);
    }

    @Override
    @Transactional
    public Integer deleteCategory(Profile profile, Long categoryId) {

        Category category = findOneCategoryById(categoryId);

        if (!category.getProfile().getId().equals(profile.getId())) {
            throw CategoryUnauthorizedException.EXCEPTION;
        }

        Integer updatedExpenseCnt =
                expenseRepository.updateCategory(profile.getId(), category.getId());

        categoryRepository.delete(category);

        return updatedExpenseCnt;
    }

    @Override
    public List<CategoryWithCountResponseDto> selectCategoryList(Profile profile) {

        List<CategoryCountInfoMapping> categoryList =
                categoryRepository.findAllByProfileOrderByCount(profile.getId());

        return categoryList.stream()
                .map(CategoryWithCountResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDetailResponseDto> selectCustomCategoryList(Profile profile) {

        List<Category> customCategoryList = categoryRepository.findAllByProfile(profile);

        return customCategoryList.stream()
                .map(CategoryDetailResponseDto::from)
                .collect(Collectors.toList());
    }

    private Category findOneCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
    }

}
