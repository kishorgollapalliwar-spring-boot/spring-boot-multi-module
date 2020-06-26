package com.kishor.home.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.home.core.dto.ProductDTO;
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

	public void delete(final ProductItemDTO productItemDTO) {
		if (Objects.nonNull(productItemDTO)) {
			productItemRepo.delete(productItemDTO.getEntity(modelMapper, ProductItemEnt.class));
		}
	}

	public Long totalProductItems() {
		return productItemRepo.count();
	}

	public List<ProductItemDTO> searchByProduct(final ProductDTO productDTO) {
		List<ProductItemDTO> finalProductItemDTOList = Collections.emptyList();
		if (Objects.nonNull(productDTO)) {
			ProductEnt productEnt = productDTO.getEntity(modelMapper, ProductEnt.class);
			List<ProductItemEnt> productItemList = productItemRepo.findByProduct(productEnt);
			finalProductItemDTOList = productItemList.stream()
					.map(ent -> new ProductItemDTO(modelMapper, ent))
					.collect(Collectors.toList());
		}

		return finalProductItemDTOList;
	}

	public ProductItemDTO getById(final Integer id) {
		Optional<ProductItemEnt> productItem = productItemRepo.findById(id);
		return productItem.isPresent() ? new ProductItemDTO(modelMapper, productItem.get()) : null;
	}
}
