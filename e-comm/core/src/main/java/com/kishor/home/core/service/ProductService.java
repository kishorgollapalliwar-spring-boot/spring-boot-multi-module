package com.kishor.home.core.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.home.core.dto.ProductDTO;
import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.repo.ProductRepo;

@Service
public class ProductService {
	private ProductRepo productRepo;
	private ModelMapper modelMapper;

	@Autowired
	public ProductService(final ProductRepo productRepo, final ModelMapper modelMapper) {
		this.productRepo = productRepo;
		this.modelMapper = modelMapper;
	}

	public ProductDTO create(final ProductDTO productDTO) {
		ProductDTO finalProductDTO = null;
		if (Objects.nonNull(productDTO)) {
			ProductEnt productEnt = productDTO.getEntity(modelMapper, ProductEnt.class);
			ProductEnt savedProductEnt = productRepo.save(productEnt);
			finalProductDTO = new ProductDTO(modelMapper, savedProductEnt);
		}
		return finalProductDTO;
	}

	public List<ProductDTO> list() {
		return productRepo.findAll()
				.stream()
				.map(ent -> new ProductDTO(modelMapper, ent))
				.collect(Collectors.toList());
	}

	public void delete(final ProductDTO productDTO) {
		if (Objects.nonNull(productDTO)) {
			productRepo.delete(productDTO.getEntity(modelMapper, ProductEnt.class));
		}
	}

	public Long totalProducts() {
		return productRepo.count();
	}

	public ProductEnt search(final String name, final String brand) {
		return productRepo.findByNameAndBrand(name, brand);
	}

	public ProductDTO getById(final Integer id) {
		Optional<ProductEnt> product = productRepo.findById(id);
		return product.isPresent() ? new ProductDTO(modelMapper, product.get()) : null;
	}

}
