package com.shashank.ecom.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.shashank.ecom.DTO.ProductDTO;
import com.shashank.ecom.Exceptions.ProductNotFoundException;
import com.shashank.ecom.Mapper.ProductMapper;
import com.shashank.ecom.Repository.CategoryRepository;
import com.shashank.ecom.Repository.ProductRepository;
import com.shashank.ecom.models.Category;
import com.shashank.ecom.models.Product;
@Service
public class ProductService {
	
	CategoryService categoryService;
	ProductRepository productRepository;
	CategoryRepository categoryRepository;
	ProductMapper productMapper;
	RedisTemplate<String, Object> redisTemplate;
	
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, CategoryService categoryService, RedisTemplate<String, Object> redisTemplate) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productMapper = productMapper;
		this.categoryService = categoryService;
		this.redisTemplate = redisTemplate;
	}

	public Product GetProduct(Long id) throws ProductNotFoundException{

		System.out.println("Enterred get prod");
		// go to redis first
		Product pFromCache = (Product) redisTemplate.opsForHash().get("PRODUCT_"+id, id);
		System.out.println("done with pfromcache");

		// cache hit
		if(pFromCache != null){
			return pFromCache;
		}

		// cache miss

		Optional<Product> GetASingleProductById;
		GetASingleProductById = productRepository.findById(id);
		
		Product SingleProduct = null;
		
		if(GetASingleProductById.isEmpty()) {
			throw new ProductNotFoundException("no product of that type in database");
		}
		else {
			SingleProduct = GetASingleProductById.get();
		}

		// first save in cache, and then return
		System.out.println("got product from DB");
		redisTemplate.opsForHash().put("PRODUCT_"+id, id, SingleProduct);
		System.out.println("saved in cache");

		return SingleProduct;
	}
	
	public List<ProductDTO> GetAllProducts(int page, int limit, String sortby){
		Page<Product> ProductFromDB ;
		ProductFromDB = productRepository.findAll(
				PageRequest.of(
						page,
						limit,
						Sort.by(sortby).descending()
				)
		);

		System.out.println(ProductFromDB.getPageable().toString());

		List<ProductDTO> productDTOS = new ArrayList<>();
		
		for(Product p : ProductFromDB) {
			ProductDTO productDTO = new ProductDTO();
			
			productDTO.setId(p.getId());
			productDTO.setName(p.getName());
			productDTO.setPrice(p.getPrice());
			productDTO.setImage(p.getImage());
			productDTO.setCategoryName(p.getCategory().getName());
			
			productDTOS.add(productDTO);
		}
		return productDTOS;
	}

	public ProductDTO PostProduct(String name, Double price, String image, String category) {

		Product saveProduct = new Product();
		
		saveProduct.setName(name);
		saveProduct.setImage(image);
		saveProduct.setPrice(price);
		Optional<Category> optionalcategory = categoryRepository.getCategoryByName(category);
		
		if(optionalcategory.isPresent()) {
			saveProduct.setCategory(optionalcategory.get());
		}
		else {
			Category c = new Category();
			c.setName(category);
			
			Category savedCategory = categoryRepository.save(c);
			
			saveProduct.setCategory(savedCategory);
			
		}
		
		return productMapper.toProductDTO(productRepository.save(saveProduct));
	}

	public ProductDTO UpdateProduct(long id ,String name, Double price, String image, String category) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isEmpty()) {
			throw new ProductNotFoundException("no product to update");
		}
		else {
			Product ProductToUpdate = optionalProduct.get();
		
			if(name!=null) {
				ProductToUpdate.setName(name);
			}
			if(price!=null) {
				ProductToUpdate.setPrice(price);
			}
			if(image!=null) {
				ProductToUpdate.setImage(image);
			}
			
			
			Optional<Category> optionalcategory = categoryRepository.getCategoryByName(category);
			
			if(optionalcategory.isPresent()) {
				ProductToUpdate.setCategory(optionalcategory.get());
			}
			else {
				Category c = new Category();
				c.setName(category);
				
				Category savedCategory = categoryRepository.save(c);
				
				ProductToUpdate.setCategory(savedCategory);
				
			}
			Product updaetdProduct = ProductToUpdate;
			ProductDTO updatedProductDTO = productMapper.toProductDTO(updaetdProduct);
			
			return updatedProductDTO;
		}
	}

	/// handled exception
	public String deleteProduct(long id) throws ProductNotFoundException {
		
		Optional<Product> productById = productRepository.findById(id);
		if(productById.isEmpty())
			throw new ProductNotFoundException("no such product with id" + id);
		else
			productRepository.deleteById(id);
		
		return "Deleted product " + id;
	}

}
