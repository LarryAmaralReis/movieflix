package br.com.larrydev.movieflix.controller;

import br.com.larrydev.movieflix.entity.Category;
import br.com.larrydev.movieflix.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<Category> postCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok().body(createdCategory);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok().body(category))
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
