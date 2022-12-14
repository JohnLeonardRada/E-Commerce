package com.project.ecommerce.service;

import com.project.ecommerce.model.Category;
import com.project.ecommerce.repository.CatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    // INJECTING INSTANCE OF CategoryRepository
    @Autowired
    private CatagoryRepository categoryRepository;

    // LIST ALL CATEGORY ITEMS
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    // CREATE A CATEGORY ITEM
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    // RETRIEVE SPECIFIC CATEGORY ITEM
    public Category readCategory(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    // RETRIEVE SPECIFIC CATEGORY ITEM BY ID
    public Optional<Category> readCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    // UPDATE THE VALUES OF THE SELECTED CATEGORY ITEM
    public void updateCategory(Integer categoryId, Category newCategory) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setCategoryName(newCategory.getCategoryName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepository.save(category);
    }
}
