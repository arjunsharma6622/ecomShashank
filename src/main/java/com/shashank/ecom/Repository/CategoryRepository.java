package com.shashank.ecom.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shashank.ecom.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Optional<Category> getCategoryByName(String name);

}
