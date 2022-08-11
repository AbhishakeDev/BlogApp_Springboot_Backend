package com.blog.controllers;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
import com.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer catId){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,catId);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

}
