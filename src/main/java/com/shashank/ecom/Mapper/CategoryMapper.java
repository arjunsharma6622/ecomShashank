package com.shashank.ecom.Mapper;

import org.springframework.stereotype.Component;

import com.shashank.ecom.DTO.CategoryDTO;
import com.shashank.ecom.models.Category;

@Component
public class CategoryMapper {
	public CategoryDTO toCategoryDTO(Category c) {
		CategoryDTO categoryDTO = new CategoryDTO();
		
		
		categoryDTO.setName(c.getName());
		
		
		
		return categoryDTO;
		
	}
	
	
}
