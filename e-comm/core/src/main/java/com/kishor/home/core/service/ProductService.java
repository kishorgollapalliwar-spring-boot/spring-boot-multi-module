package com.kishor.home.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.repo.ProductRepo;

@Service
public class ProductService {
	private ProductRepo productRepo;

	@Autowired
	public ProductService(final ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	public ProductEnt create(final ProductEnt productEnt) {
		return productRepo.save(productEnt);
	}
	
	public List<ProductEnt> list() {
		return productRepo.findAll();
	}
	
	public void delete(final ProductEnt productEnt) {
		productRepo.delete(productEnt);
	}
	
	public Long totalProducts() {
		return productRepo.count();
	}
}
