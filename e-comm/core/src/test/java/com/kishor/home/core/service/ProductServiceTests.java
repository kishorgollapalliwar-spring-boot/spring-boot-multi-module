package com.kishor.home.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

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

		ProductEnt productSaved = productService.create(createProduct("New Rin", "Hindustan Uniliver Limited", "Rin"));
		assertThat(productSaved).isNotNull();
		assertThat(productSaved).isEqualTo(productList.get(0));
		assertThat(initialSize+1).isEqualTo(productList.size());

		compareProduct(productList.get(0), productSaved);
	}

	@Test
	void searchTest() {
		ProductEnt productEnt1 = createProduct("New Rin", "Hindustan Uniliver Limited", "Rin");
		doReturn(productEnt1).when(productRepo).findByNameAndBrand(Mockito.anyString(), Mockito.anyString());

		ProductEnt productEnt2 = productService.search("New Rin", "Rin");
		compareProduct(productEnt1, productEnt2);
	}

	private void compareProduct(final ProductEnt productEnt1, final ProductEnt productEnt2) {
		assertThat(productEnt1).isNotNull();
		assertThat(productEnt2).isNotNull();

		assertThat(productEnt1.getName()).isNotNull();
		assertThat(productEnt2.getName()).isNotNull();

		assertThat(productEnt1.getManufacturer()).isNotNull();
		assertThat(productEnt2.getManufacturer()).isNotNull();

		assertThat(productEnt1.getName()).isEqualTo(productEnt2.getName());
		assertThat(productEnt1.getManufacturer()).isEqualTo(productEnt2.getManufacturer());
		assertThat(productEnt1.getBrand()).isEqualTo(productEnt2.getBrand());
	}

	private ProductEnt createProduct(final String name, final String manufacturer, final String brand) {
		ProductEnt productEnt = new ProductEnt();
		productEnt.setName(name);
		productEnt.setManufacturer(manufacturer);
		productEnt.setBrand(brand);

		return productEnt;
	}
}
