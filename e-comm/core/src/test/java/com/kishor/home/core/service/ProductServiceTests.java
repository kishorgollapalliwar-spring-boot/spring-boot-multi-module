package com.kishor.home.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.repo.ProductRepo;

@SpringBootTest
public class ProductServiceTests {
	@Autowired private ProductService productService;
	
	@MockBean private ProductRepo productRepo;
	
	@BeforeAll
	static void startup() {
		//Steps common for all tests
		
	}

	@Test
	void createTest() {
		List<ProductEnt> productList = new ArrayList<>();
		int initialSize = productList.size();
		
		doAnswer(invocation -> {
			productList.add(invocation.getArgument(0));
			return invocation.getArgument(0);
		}).when(productRepo).save(Mockito.any(ProductEnt.class));
		
		ProductEnt productSaved = productService.create(createProduct("Rin", "Godrej"));
		assertThat(productSaved).isNotNull();
		assertThat(productSaved).isEqualTo(productList.get(0));
		assertThat(initialSize+1).isEqualTo(productList.size());
	}

	private ProductEnt createProduct(final String name, final String manuf) {
		ProductEnt productEnt = new ProductEnt();
		productEnt.setName(name);
		productEnt.setManufactorer(manuf);
		
		return productEnt;
	}
}
