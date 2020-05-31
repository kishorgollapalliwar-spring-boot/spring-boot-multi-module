package com.kishor.home.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.entity.ProductItemEnt;
import com.kishor.home.core.repo.ProductItemRepo;

@Service
public class ProductItemService {
	private ProductItemRepo productItemRepo;

	@Autowired
	public ProductItemService(ProductItemRepo productItemRepo) {
		this.productItemRepo = productItemRepo;
	}

	public List<ProductItemEnt> list() {
		return productItemRepo.findAll();
	}

	public ProductItemEnt create(final ProductItemEnt productItemEnt) {
		return productItemRepo.save(productItemEnt);
	}

	public void delete(final ProductItemEnt productItemEnt) {
		productItemRepo.delete(productItemEnt);
	}

	public Long totalProductItems() {
		return productItemRepo.count();
	}

	public List<ProductItemEnt> searchByProduct(final ProductEnt product) {
		return productItemRepo.findByProduct(product);
	}
}
