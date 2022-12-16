package com.project.ecommerce.controller;

import com.project.ecommerce.common.ApiResponse;
import com.project.ecommerce.model.Category;
import com.project.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    // INJECTING INSTANCE OF CategoryService
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.listCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // @Valid = TO VALIDATE THE BEAN
    // @RequestBody = USED TO INDICATE A METHOD PARAMETER SHOULD BIND TO THE BODY OF THE HTTP REQUEST
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if(Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }

        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category created"), HttpStatus.CREATED);
    }

    // @PathVariable = DIFFERENT KINDS OF PARAMETERS IN THE INCOMING REQUEST
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer categoryId, @Valid @RequestBody Category category) {
        // CHECK TO SEE IF CATEGORY ITEM EXISTS
        if(Objects.nonNull(categoryService.readCategory(categoryId))) {
            //IF CATEGORY ITEM EXISTS, THEN UPDATE
            categoryService.updateCategory(categoryId, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category updated"), HttpStatus.OK);
        }

        // IF CATEGORY ITEM DOESN'T EXIST, RETURN UNSUCCESSFUL RESPONSE
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "catogry does not exist"), HttpStatus.NOT_FOUND);
    }

}
