package com.backend.ecommerce.controller;

import com.backend.ecommerce.ApiResponse;
import com.backend.ecommerce.models.Category;
import com.backend.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "created a new category"), HttpStatus.CREATED );

    }

    @GetMapping("/list") //to list all the categories
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId")int categoryId, @RequestBody Category category){
        System.out.println("categoryId: " +categoryId );
        if (!categoryService.findById(categoryId)){
           return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category does not exist"),HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId,category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"category has been updated"),HttpStatus.OK);

    }

    @PostMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId")int categoryId ) {
        if (!categoryService.findById(categoryId)){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category does not exist"),HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"category deleted successfully!"),HttpStatus.OK);


    }


}
