package ssafy.haruman.domain.category.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.haruman.domain.category.dto.request.CategoryCreateRequestDto;
import ssafy.haruman.domain.category.dto.request.CategoryUpdateRequestDto;
import ssafy.haruman.domain.category.dto.response.CategoryDetailResponseDto;
import ssafy.haruman.domain.category.dto.response.CategorySimpleResponseDto;
import ssafy.haruman.domain.category.service.CategoryService;
import ssafy.haruman.domain.member.entity.Member;
import ssafy.haruman.global.response.JsonResponse;
import ssafy.haruman.global.response.ResponseWrapper;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<CategorySimpleResponseDto>> createCategory(
            @AuthenticationPrincipal Member member,
            @RequestBody CategoryCreateRequestDto createDto) {

        CategorySimpleResponseDto createdCategory =
                categoryService.createCategory(member.getProfile(), createDto);

        return JsonResponse.of(HttpStatus.CREATED, "카테고리가 성공적으로 생성되었습니다.", createdCategory);
    }

    @PatchMapping
    public ResponseEntity<ResponseWrapper<CategorySimpleResponseDto>> updateCategory(
            @AuthenticationPrincipal Member member,
            @RequestBody CategoryUpdateRequestDto updateDto) {

        CategorySimpleResponseDto updatedCategory =
                categoryService.updateCategory(member.getProfile(), updateDto);

        return JsonResponse.ok("카테고리가 성공적으로 수정되었습니다.", updatedCategory);
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<ResponseWrapper<Nullable>> deleteCategory(
            @AuthenticationPrincipal Member member,
            @PathVariable("category-id") Long categoryId) {

        categoryService.deleteCategory(member.getProfile(), categoryId);

        return JsonResponse.ok("카테고리 삭제에 성공했습니다.");
    }

    /**
     * 사용자가 생성한 카테고리 + 기본 카테고리 목록 전체를 조회합니다. 사용자가 자주 쓰는 카테고리 + 나머지 카테고리를 반환합니다.
     */
    @GetMapping
    public ResponseEntity<ResponseWrapper<List<CategoryDetailResponseDto>>> selectCategoryList(
            @AuthenticationPrincipal Member member) {

        List<CategoryDetailResponseDto> categoryList =
                categoryService.selectCategoryList(member.getProfile());

        return JsonResponse.ok("카테고리 목록을 성공적으로 가져왔습니다.", categoryList);
    }

    /**
     * 사용자가 생성한 카테고리 목록 전체를 조회합니다.
     */
    @GetMapping("/custom")
    public ResponseEntity<ResponseWrapper<List<CategoryDetailResponseDto>>> selectCustomCategoryList(
            @AuthenticationPrincipal Member member) {

        List<CategoryDetailResponseDto> categoryCustomList =
                categoryService.selectCustomCategoryList(member.getProfile());

        return JsonResponse.ok("회원 커스텀 카테고리 목록을 성공적으로 가져왔습니다.", categoryCustomList);
    }

}
