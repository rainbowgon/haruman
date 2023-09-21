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
import ssafy.haruman.domain.category.repository.CategoryRepository;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.global.error.exception.CategoryDuplicateException;
import ssafy.haruman.global.error.exception.CategoryNotFoundException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategorySimpleResponseDto createCategory(
            Member member, CategoryCreateRequestDto createDto) {

        Optional<Category> category = categoryRepository.findByName(member, createDto.getName());
        if (category.isPresent()) {
            throw new CategoryDuplicateException();
        }

        Category createdCategory = Category.builder()
                .member(member)
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

        // TODO 2) 수정 요청 category가 해당 회원의 custom category인지 확인

        // TODO 3) category 수정
        category.updateCategory(updateDto.getName(), updateDto.getColor());

        return CategorySimpleResponseDto.from(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Member member, Long categoryId) {

        Category category = findOneCategoryById(categoryId);

        // TODO 2) 삭제 요청 category가 해당 회원의 custom category인지 확인

        // TODO 3) 삭제 요청 category에 해당되는 지출 내역 처리 (기타 카테고리로 변경) ** 벌크연산

        // TODO 4) category 삭제
        categoryRepository.delete(category);

    }

    @Override
    public List<CategoryDetailResponseDto> selectCategoryList(Member member) {

        // TODO 요청 회원의 custom category + default category 조회
        List<Category> categoryList = categoryRepository.findAllByProfileAndStatus(member);

        // TODO 요청 회원의 지출 내역에서 가장 많은 상위 N개의 카테고리 조회 (null 제외)

        return categoryList.stream()
                .map(CategoryDetailResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDetailResponseDto> selectCustomCategoryList(Member member) {

        // TODO 요청 회원의 custom category 조회
        List<Category> customCategoryList = categoryRepository.findAllByProfile(member);

        return customCategoryList.stream()
                .map(CategoryDetailResponseDto::from)
                .collect(Collectors.toList());
    }

    private Category findOneCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
    }

}
