package com.nexus.backend.service;

import com.nexus.backend.model.Category;
import com.nexus.backend.payload.CategoryDTO;
import com.nexus.backend.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
