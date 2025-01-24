package com.shashank.ecom.Mapper;

import org.springframework.stereotype.Component;

import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.models.Category;
import com.shashank.ecom.models.Product;
@Component
public class ProductMapper {
	public ProductDTO toProductDTO(Product p) {
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setId(p.getId());
		productDTO.setName(p.getName());
		productDTO.setPrice(p.getPrice());
		productDTO.setImage(p.getImage());
		productDTO.setCategoryName(p.getCategory().getName());
		
		return productDTO;
		
	}
	
	public Product toProduct(ProductDTO p) {
		Product product = new Product();
		
		product.setId(p.getId());
		product.setName(p.getName());
		product.setPrice(p.getPrice());
		product.setImage(p.getImage());
		product.setCategory(new Category(p.getCategoryName()));
		
		return product;
	}

}
