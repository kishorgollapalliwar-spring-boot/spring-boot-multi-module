package com.kishor.home.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.home.core.dto.ProductDTO;
import com.kishor.home.core.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = {"/rest"})
@Slf4j
public class ProductRestCntr {
	private ProductService productService;

	@Autowired
	public ProductRestCntr(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(path = {"/product", "/productlist"})
	public List<ProductDTO> list() {
		return productService.list();
	}

	@GetMapping(path = {"/product/{id}"})
	public ProductDTO listDto(@PathVariable("id") Integer id) {
		log.info("Fetching product with id {}.", id);
		return productService.getById(id);
	}
}
