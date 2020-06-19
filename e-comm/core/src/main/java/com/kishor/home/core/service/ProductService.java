package com.kishor.home.core.service;

import java.util.List;
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

	public ProductEnt create(final ProductEnt productEnt) {
		return productRepo.save(productEnt);
	}

	public List<ProductDTO> list() {
		return productRepo.findAll()
				.stream()
				.map(ent -> new ProductDTO(modelMapper, ent))
				.collect(Collectors.toList());
	}

	public void delete(final ProductEnt productEnt) {
		productRepo.delete(productEnt);
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
