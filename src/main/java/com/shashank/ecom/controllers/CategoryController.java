package com.shashank.ecom.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.ecom.DTO.CategoryDTO;
import com.shashank.ecom.Mapper.CategoryMapper;
import com.shashank.ecom.Services.CategoryService;
import com.shashank.ecom.models.Category;

@RestController
public class CategoryController {
	
	CategoryService CategoryService;
	CategoryMapper CategoryMapper;
	
	public CategoryController(CategoryService CategoryService,CategoryMapper CategoryMapper) {
		this.CategoryService = CategoryService;
		this.CategoryMapper = CategoryMapper;
		
	}

	@GetMapping("/category/{id}")
	public Category GetSingleCat(@PathVariable("id") int id) {
		Category cat = CategoryService.GetSingleCat(id);
		
		return cat;
	}

	@GetMapping("/category")
	public List<Category> GetAllCat()
	{
		List<Category> allcat = CategoryService.GetAllCat();
		
		return allcat;
	}

	@PostMapping("/category")
	public Category SaveCategory(@RequestBody Category catbyuser) {
		Category savecat = CategoryService.SaveCategory(catbyuser);
		return savecat;
	}
	
	@PutMapping("/category/{id}")
	public CategoryDTO UpdateCategory(@PathVariable("id") long id,@RequestBody Category category) {
		
		
		Category updateCategory = CategoryService.UpdateCategory(id,
				category.getName());
		
		
		return CategoryMapper.toCategoryDTO(updateCategory);
	}
	@DeleteMapping("/category/{id}")
	public String DeleteProduct(@PathVariable("id") Long id) {
		return CategoryService.deletecategory(id);
	}
}
