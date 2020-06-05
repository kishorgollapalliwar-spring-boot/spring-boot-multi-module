package com.kishor.home.core.dto;

import com.kishor.home.core.entity.ProductEnt;

import lombok.Data;

@Data
public class ProductDTO {
	private Integer id;
	private String name;
	private String brand;
	private String manufacturer;

	public ProductDTO(final ProductEnt productEnt) {
		this.id = productEnt.getId();
		this.name = productEnt.getName();
		this.brand = productEnt.getBrand();
		this.manufacturer = productEnt.getManufacturer();
	}
}
