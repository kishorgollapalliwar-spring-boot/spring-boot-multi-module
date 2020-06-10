package com.kishor.home.core.dto;

import org.modelmapper.ModelMapper;

public interface BaseDTO {
	default<E> E getEntity(final ModelMapper modelMapper, Class<E> clazz) {
		return modelMapper.map(this, clazz);
	}

	default<E> void setDto(final ModelMapper modelMapper, E entity) {
		modelMapper.map(entity, this);
	}
}