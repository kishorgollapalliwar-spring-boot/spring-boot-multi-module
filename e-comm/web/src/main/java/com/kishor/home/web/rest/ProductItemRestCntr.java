package com.kishor.home.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.home.core.dto.ProductItemDTO;
import com.kishor.home.core.service.ProductItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = {"/rest"})
@Slf4j
public class ProductItemRestCntr {
	private ProductItemService productItemService;

	@Autowired
	public ProductItemRestCntr(final ProductItemService productItemService) {
		this.productItemService = productItemService;
	}

	@GetMapping(path = {"/product/item", "/product/item/list"})
	public List<ProductItemDTO> list() {
		log.info("Request to get all product item is received.");
		return productItemService.list();
	}

	@GetMapping(path = {"/product/item/{id}"})
	public ProductItemDTO getById(@PathVariable("id")final Integer id) {
		log.info("Request to get product item with id[{}] is received.", id);
		return productItemService.getById(id);
	}
}
