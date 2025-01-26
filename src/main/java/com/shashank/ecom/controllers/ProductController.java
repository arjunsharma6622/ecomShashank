package com.shashank.ecom.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.Mapper.ProductMapper;
import com.shashank.ecom.Services.ProductService;


@RestController
public class ProductController {

	ProductService ProductService;
	ProductMapper productMapper;
	
	public ProductController(ProductService productService,ProductMapper productMapper) {
        this.ProductService = productService;
        this.productMapper = productMapper;
    }

	@GetMapping("/product/{id}")
	public ProductDTO GetProduct(@PathVariable("id") Long id)
	{

		ProductDTO prfdb = ProductService.GetProduct(id);
		
		return prfdb;
	}

	@GetMapping("/product")
	public List<ProductDTO> GetAllProduccts(
			@RequestParam("page") int page,
			@RequestParam("limit") int limit,
			@RequestParam("sortby") String sortby
			) {
		List<ProductDTO> allprfdb = ProductService.GetAllProducts(page-1, limit, sortby);
		return allprfdb;
		
	}


	@PostMapping("/product")
	public ProductDTO PostProduct(@RequestBody ProductDTO productfromuser) {
		ProductDTO saveProduct = ProductService.PostProduct(
				productfromuser.getName(),
				productfromuser.getPrice(),
				productfromuser.getImage(),
				productfromuser.getCategoryName());
		
		return saveProduct;
	}

	

	@PutMapping("/product/{id}")
	public ProductDTO UpdateProduct(
			@PathVariable("id") Long id,
			@RequestBody ProductDTO product
			){

		ProductDTO updatedProduct;
		updatedProduct = ProductService.UpdateProduct(
				id,
				product.getName(),
				product.getPrice(),
				product.getImage(),
				product.getCategoryName()
		);

		return updatedProduct;
	}

	
	@DeleteMapping("/product/{id}")
	public String DeleteProduct(@PathVariable("id") long id) {
		return ProductService.deleteProduct(id);
	}

}
