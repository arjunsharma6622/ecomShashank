package com.shashank.ecom.Services;

import java.util.List;

import java.util.Optional;

import com.shashank.ecom.Exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import com.shashank.ecom.Exceptions.CategoryNotFoundException;
import com.shashank.ecom.Repository.CategoryRepository;
import com.shashank.ecom.models.Category;
import com.shashank.ecom.models.Product;
@Service
public class CategoryService {
	CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category GetSingleCat(long id) throws CategoryNotFoundException{
		Optional<Category> getCatById = categoryRepository.findById(id);
		
		Category cat;
		
		if(getCatById.isEmpty())
		{
			throw new CategoryNotFoundException("Categoruy not Found in the DB");
		}
		else
		{
			cat = getCatById.get();
		}
		
		return cat;
	}

	public Category GetSingleCatByName(String name) throws CategoryNotFoundException{
		Optional<Category> getCatById = categoryRepository.getCategoryByName(name);
		
		Category cat;
		if(getCatById.isEmpty())
		{
			throw new CategoryNotFoundException("Category not found in DB with ID : " + name );
		}
		else
		{
			cat = getCatById.get();
		}
		return cat;
	}
	
	public List<Category> GetAllCat() {
		List<Category> allcat = categoryRepository.findAll();
		return allcat;
	}

	public Category SaveCategory(Category category) {
		Category savecat = categoryRepository.save(category);
		
		return savecat;
		
	}

	// handle the excp handln correctly // done handling
	public Category UpdateCategory(long id,String name) throws CategoryNotFoundException {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isEmpty()) {
			throw new CategoryNotFoundException("no such category");
		}
		if(optionalCategory.isPresent());
		{
			Category categoryToUpdate = optionalCategory.get();
			if(name!=null) {
				categoryToUpdate.setName(name);
			}
			
			return categoryRepository.save(categoryToUpdate);
		}
		
		
	}

	// handle exception handln // done handling
	public String deletecategory(Long id) throws CategoryNotFoundException {
		
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isEmpty()) {
			throw new CategoryNotFoundException("no such category to delete");
		}
		else {
		categoryRepository.deleteById(id);
		
		return "Deleted";
		}
	}
 
}
