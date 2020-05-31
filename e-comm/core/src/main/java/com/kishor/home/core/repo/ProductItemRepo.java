package com.kishor.home.core.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.entity.ProductItemEnt;

public interface ProductItemRepo extends JpaRepository<ProductItemEnt, Integer> {
	public List<ProductItemEnt> findByProduct(@Param("product") final ProductEnt product);
}
