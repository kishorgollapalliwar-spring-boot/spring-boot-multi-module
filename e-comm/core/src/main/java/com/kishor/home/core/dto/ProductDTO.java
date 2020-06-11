package com.kishor.home.core.dto;

import org.modelmapper.ModelMapper;

import com.kishor.home.core.entity.ProductEnt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO implements BaseDTO {
	private Integer id;
	private String name;
	private String brand;
	private String manufacturer;

	public ProductDTO(final ModelMapper modelMapper, final ProductEnt productEnt) {
		this.setDto(modelMapper, productEnt);
	}
}
