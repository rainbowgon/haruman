package ssafy.haruman.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
import ssafy.haruman.domain.category.dto.response.MultiCategoryResponseDto;
import ssafy.haruman.domain.category.dto.response.SingleCategoryResponseDto;
import ssafy.haruman.domain.category.service.CategoryService;
import ssafy.haruman.global.response.JsonResponse;
import ssafy.haruman.global.response.ResponseWrapper;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 사용자가 카테고리를 생성합니다.
     */
    @PostMapping
    public ResponseEntity<ResponseWrapper<SingleCategoryResponseDto>> createCategory(
            @RequestBody CategoryCreateRequestDto createDto) {

        return JsonResponse.ok("카테고리 생성 성공", null);
    }

    /**
     * 사용자가 자신이 생성했던 카테고리를 수정합니다.
     */
    @PatchMapping
    public ResponseEntity<ResponseWrapper<SingleCategoryResponseDto>> updateCategory(
            @RequestBody CategoryUpdateRequestDto updateDto) {

        return JsonResponse.ok("카테고리 수정 성공", null);
    }

    /**
     * 사용자가 자신이 생성했던 카테고리를 삭제합니다.
     */
    @DeleteMapping("/{category-id}")
    public ResponseEntity<ResponseWrapper<Nullable>> deleteCategory(
            @PathVariable("category-id") Long id) {

        return JsonResponse.ok("카테고리 삭제 성공");
    }

    /**
     * 사용자가 생성한 카테고리 + 기본 카테고리 목록 전체를 조회합니다.
     */
    @GetMapping
    public ResponseEntity<ResponseWrapper<MultiCategoryResponseDto>> selectCategoryList() {

        return JsonResponse.ok("카테고리 목록 전체", null);
    }

    /**
     * 사용자가 생성한 카테고리 목록 전체를 조회합니다.
     */
    @GetMapping("/custom")
    public ResponseEntity<ResponseWrapper<MultiCategoryResponseDto>> selectCustomCategoryList() {

        return JsonResponse.ok("유저가 생성한 카테고리 목록 전체", null);
    }

    /**
     * 사용자가 자주 쓰는 카테고리 목록을 조회합니다.
     */
    @GetMapping("/often")
    public ResponseEntity<ResponseWrapper<MultiCategoryResponseDto>> selectOftenCategoryList() {

        return JsonResponse.ok("유저가 자주 쓰는 카테고리", null);
    }

}
