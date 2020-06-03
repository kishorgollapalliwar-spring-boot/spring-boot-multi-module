package com.kishor.home.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.home.core.entity.ProductItemEnt;
import com.kishor.home.core.service.ProductItemService;

@RestController
@RequestMapping(path = {"/product-item"})
public class ProductItemRest {
	@Autowired private ProductItemService productItemService;

	@GetMapping(path = {"/", "/list"})
	public List<ProductItemEnt> list() {
		return productItemService.list();
	}
}
