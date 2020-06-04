package com.kishor.home.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.service.ProductService;

@RestController
@RequestMapping(path = {"/product"})
public class ProductRestCntr {
	@Autowired private ProductService productService;

	@GetMapping(path = {"/", "/list"})
	public List<ProductEnt> list() {
		return productService.list();
	}
}
