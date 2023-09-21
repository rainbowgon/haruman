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
import ssafy.haruman.domain.category.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategorySimpleResponseDto createCategory(CategoryCreateRequestDto createDto) {

        Long profileId = null;

        // TODO 1) 같은 이름의 default category가 존재하는지 확인
        // TODO 2) 같은 이름의 회원 custom category가 존재하는지 확인
        Optional<Category> category = categoryRepository.findByName(createDto.getName(), profileId);
        if (category.isPresent()) {
            // TODO 이미 존재하는 카테고리 에러 반환
        }

        // TODO 3) category 생성
        Category createdCategory = categoryRepository.save(createDto.toEntity());

        return CategorySimpleResponseDto.from(createdCategory);
    }

    @Override
    @Transactional
    public CategorySimpleResponseDto updateCategory(CategoryUpdateRequestDto updateDto) {

        // TODO 1) 수정 요청 category 유효성 검사
        Category category = categoryRepository.findById(updateDto.getCategoryId()).orElseThrow();

        // TODO 2) 수정 요청 category가 해당 회원의 custom category인지 확인

        // TODO 3) category 수정
        category.updateCategory(updateDto.getName(), updateDto.getColor());

        return CategorySimpleResponseDto.from(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {

        // TODO 1) 수정 요청 category 유효성 검사
        Category category = categoryRepository.findById(categoryId).orElseThrow();

        // TODO 2) 삭제 요청 category가 해당 회원의 custom category인지 확인

        // TODO 3) 삭제 요청 category에 해당되는 지출 내역 처리 (기타 카테고리로 변경) ** 벌크연산

        // TODO 4) category 삭제
        categoryRepository.delete(category);

    }

    @Override
    public List<CategoryDetailResponseDto> selectCategoryList() {

        Long profileId = null;

        // TODO 요청 회원의 custom category + default category 조회
        List<Category> categoryList = categoryRepository.findAllByProfileAndStatus(profileId);

        // TODO 요청 회원의 지출 내역에서 가장 많은 상위 N개의 카테고리 조회 (null 제외)

        return categoryList.stream()
                .map(CategoryDetailResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDetailResponseDto> selectCustomCategoryList() {

        Long profileId = null;

        // TODO 요청 회원의 custom category 조회
        List<Category> customCategoryList = categoryRepository.findAllByProfile(profileId);

        return customCategoryList.stream()
                .map(CategoryDetailResponseDto::from)
                .collect(Collectors.toList());
    }

}
