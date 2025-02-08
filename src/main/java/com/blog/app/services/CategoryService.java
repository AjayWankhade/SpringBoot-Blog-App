package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.Category;
import com.blog.app.payloads.CategoryDto;

public interface CategoryService {

	    CategoryDto createCategory(CategoryDto categoryDto);
	    CategoryDto getCategoryById(Integer categoryId);
	    List<CategoryDto> getAllCategories();
	    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
	    void deleteById(Integer id);
}
