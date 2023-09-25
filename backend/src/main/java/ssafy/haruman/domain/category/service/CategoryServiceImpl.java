package ssafy.haruman.domain.category.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;
import ssafy.haruman.domain.category.entity.Category;
import ssafy.haruman.domain.category.entity.CustomStatus;
import ssafy.haruman.domain.category.repository.CategoryCountInfoMapping;
import ssafy.haruman.domain.category.repository.CategoryRepository;
import ssafy.haruman.domain.challenge.repository.ExpenseRepository;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.global.error.exception.CategoryDuplicateException;
import ssafy.haruman.global.error.exception.CategoryNotFoundException;
import ssafy.haruman.global.error.exception.CategoryUnauthorizedException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    @Transactional
    public CategorySimpleResponseDto createCategory(
            Member member, CategoryCreateRequestDto createDto) {

        Optional<Category> category = categoryRepository.findByProfileAndStatusAndName(
                member.getProfile(), createDto.getName());

        if (category.isPresent()) {
            throw CategoryDuplicateException.EXCEPTION;
        }

        Category createdCategory = Category.builder()
                .profile(member.getProfile())
                .name(createDto.getName())
                .isDefault(CustomStatus.CUSTOM)
                .color(createDto.getColor())
                .build();

        return CategorySimpleResponseDto.from(categoryRepository.save(createdCategory));
    }

    @Override
    @Transactional
    public CategorySimpleResponseDto updateCategory(
            Member member, CategoryUpdateRequestDto updateDto) {

        Category category = findOneCategoryById(updateDto.getCategoryId());

        if (!category.getProfile().equals(member.getProfile())) {
            throw CategoryUnauthorizedException.EXCEPTION;
        }

        category.updateCategory(updateDto.getName(), updateDto.getColor());

        return CategorySimpleResponseDto.from(category);
    }

    @Override
    @Transactional
    public Integer deleteCategory(Member member, Long categoryId) {

        Category category = findOneCategoryById(categoryId);

        if (!category.getProfile().equals(member.getProfile())) {
            throw CategoryUnauthorizedException.EXCEPTION;
        }

        Integer updatedExpenseCnt =
                expenseRepository.updateCategory(member.getProfile().getId(), category.getId());

        categoryRepository.delete(category);

        return updatedExpenseCnt;
    }

    @Override
    public List<CategoryDetailResponseDto> selectCategoryList(Member member) {

//        List<Category> categoryList =
//                categoryRepository.findAllByProfileAndStatus(member.getProfile());

        // TODO 요청 회원의 지출 내역에서 가장 많은 상위 N개의 카테고리 조회 (null 제외)
        List<CategoryCountInfoMapping> categoryList =
                categoryRepository.findAllByProfileOrderByCount(member.getProfile().getId());

        return categoryList.stream()
                .map(CategoryDetailResponseDto::fromCountInfo)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDetailResponseDto> selectCustomCategoryList(Member member) {

        List<Category> customCategoryList =
                categoryRepository.findAllByProfile(member.getProfile());

        return customCategoryList.stream()
                .map(CategoryDetailResponseDto::from)
                .collect(Collectors.toList());
    }

    private Category findOneCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
    }

}
