package ssafy.haruman.domain.category.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;
import ssafy.haruman.domain.category.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategorySimpleResponseDto createCategory(CategoryCreateRequestDto createDto) {

        // TODO 1) 같은 이름의 default category가 존재하는지 확인
        // TODO 2) 같은 이름의 회원 custom category가 존재하는지 확인
        // TODO 3) category 생성

        return null;
    }

    @Override
    public CategorySimpleResponseDto updateCategory(CategoryUpdateRequestDto updateDto) {

        // TODO 1) 수정 요청 category가 해당 회원의 custom category인지 확인
        // TODO 2) category 수정

        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {

        // TODO 1) 삭제 요청 category가 해당 회원의 custom category인지 확인
        // TODO 2) *** 해당 category에 포함된 지출 내역 처리 (기타 카테고리로 변경 or null 처리)
        // TODO 3) category 삭제

    }

    @Override
    public List<CategoryDetailResponseDto> selectCategoryList() {

        // TODO 1) 요청 회원의 custom category + default category 조회

        return null;
    }

    @Override
    public List<CategoryDetailResponseDto> selectCustomCategoryList() {

        // TODO 1) 요청 회원의 custom category 조회

        return null;
    }

    @Override
    public List<CategoryDetailResponseDto> selectOftenCategoryList() {

        // TODO 1) 요청 회원의 지출 내역에서 가장 많은 상위 N개의 카테고리 조회 (null 제외)
        
        return null;
    }

}
