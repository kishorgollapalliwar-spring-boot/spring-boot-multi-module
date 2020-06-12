package com.kishor.home.core.dto;

import org.modelmapper.ModelMapper;

import com.kishor.home.core.entity.ProductItemEnt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductItemDTO implements BaseDTO {
	private Integer id;
	private String name;
	private String description;
	private ProductDTO product;

	public ProductItemDTO(final ModelMapper modelMapper, final ProductItemEnt productItemEnt) {
		this.setDto(modelMapper, productItemEnt);
	}
}
