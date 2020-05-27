package com.kishor.home.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.kishor.home.core.entity.ProductEnt;

public interface ProductRepo extends JpaRepository<ProductEnt, Integer> {
	public ProductEnt findByNameAndBrand(@Param("name") final String name, @Param("brand") final String brand);
}
