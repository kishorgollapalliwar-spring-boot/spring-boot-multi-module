package com.kishor.home.core.dto;

import com.kishor.home.core.entity.ProductItemEnt;

import lombok.Data;

@Data
public class ProductItemDTO {
	private Integer id;
	private String name;
	private String description;
	private ProductDTO product;

	public ProductItemDTO(final ProductItemEnt productItemEnt) {
		this.id = productItemEnt.getId();
		this.name = productItemEnt.getName();
		this.description = productItemEnt.getDescription();
		this.product = new ProductDTO(productItemEnt.getProduct());
	}
}
