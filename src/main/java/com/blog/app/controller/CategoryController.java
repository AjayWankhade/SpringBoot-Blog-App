package com.blog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.services.CategoryService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		   CategoryDto categoryDto2= this.categoryService.createCategory(categoryDto);
		    
		   return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto catDto) {
	
		CategoryDto categoryDto=this.categoryService.updateCategory(catDto, id);
		
		return ResponseEntity.ok(categoryDto);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		
		List<CategoryDto> categories=this.categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public CategoryDto getCategoryById(@PathVariable Integer id) {
		
		CategoryDto categoryDto=this.categoryService.getCategoryById(id);
		return categoryDto;
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
		this.categoryService.deleteById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Delted successfully..", true),HttpStatus.OK);
	}
	
	
}
