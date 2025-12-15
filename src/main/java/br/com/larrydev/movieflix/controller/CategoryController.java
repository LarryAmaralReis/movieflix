package br.com.larrydev.movieflix.controller;

import br.com.larrydev.movieflix.controller.request.CategoryRequest;
import br.com.larrydev.movieflix.controller.response.CategoryResponse;
import br.com.larrydev.movieflix.mapper.CategoryMapper;
import br.com.larrydev.movieflix.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponses = categoryService.getAllCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok().body(categoryResponses);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> postCategory(@RequestBody CategoryRequest request) {
        CategoryResponse createdCategory = CategoryMapper
                .toCategoryResponse(categoryService
                        .createCategory(CategoryMapper
                                .toCategory(request)
                        )
                );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok().body(CategoryMapper.toCategoryResponse(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> {
                    categoryService.deleteCategoryById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
