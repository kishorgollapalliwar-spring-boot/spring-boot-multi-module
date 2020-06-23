package com.kishor.home.core.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.home.core.dto.ProductItemDTO;
import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.entity.ProductItemEnt;
import com.kishor.home.core.repo.ProductItemRepo;

@Service
public class ProductItemService {
	private ProductItemRepo productItemRepo;
	private ModelMapper modelMapper;

	@Autowired
	public ProductItemService(final ProductItemRepo productItemRepo, final ModelMapper modelMapper) {
		this.productItemRepo = productItemRepo;
		this.modelMapper = modelMapper;
	}

	public List<ProductItemDTO> list() {
		return productItemRepo.findAll()
				.stream()
				.map(ent -> new ProductItemDTO(modelMapper, ent))
				.collect(Collectors.toList());
	}

	public ProductItemDTO create(final ProductItemDTO productItemDTO) {
		ProductItemDTO finalProductItemDTO = null;
		if (Objects.nonNull(productItemDTO)) {
			ProductItemEnt productItemEnt = productItemDTO.getEntity(modelMapper, ProductItemEnt.class);
			ProductItemEnt savedProductItemEnt = productItemRepo.save(productItemEnt);
			finalProductItemDTO = new ProductItemDTO(modelMapper, savedProductItemEnt);
		}
		return finalProductItemDTO;
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

	public ProductItemDTO getById(final Integer id) {
		Optional<ProductItemEnt> productItem = productItemRepo.findById(id);
		return productItem.isPresent() ? new ProductItemDTO(modelMapper, productItem.get()) : null;
	}
}
