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
import com.kishor.home.core.entity.ProductItemEnt;
import com.kishor.home.core.repo.ProductItemRepo;

@SpringBootTest
public class ProductItemServiceTests {
	@Autowired private ProductItemService productItemService;

	@MockBean private ProductItemRepo productItemRepo;

	@BeforeAll
	static void startup() {
		//Steps common for all tests
	}

	@Test
	void createTest() {
		List<ProductItemEnt> productItemList = new ArrayList<>();
		int initialSize = productItemList.size();

		doAnswer(invocation -> {
			productItemList.add(invocation.getArgument(0));
			return invocation.getArgument(0);
		}).when(productItemRepo).save(Mockito.any(ProductItemEnt.class));

		ProductItemEnt productItemSaved = productItemService.create(createProductItem("Care", "Unilever", "Lifebuoy", "50gm", "50gm + 12% extra"));
		assertThat(productItemSaved).isNotNull();
		assertThat(productItemSaved).isEqualTo(productItemList.get(0));
		assertThat(initialSize + 1).isEqualTo(productItemList.size());

		compareProductItem(productItemList.get(0), productItemSaved);
	}

	@Test
	void searchTest() {
		ProductItemEnt productItem = createProductItem("Care", "Unilever", "Lifebuoy", "50gm", "50gm + 12% extra");
		List<ProductItemEnt> productItemList = new ArrayList<>();
		productItemList.add(productItem);

		doReturn(productItemList).when(productItemRepo).findByProduct(Mockito.any(ProductEnt.class));

		List<ProductItemEnt> productItemListReturned = productItemService.searchByProduct(new ProductEnt());
		compareProductItem(productItem, productItemListReturned.get(0));
	}

	private void compareProductItem(final ProductItemEnt productItem1, final ProductItemEnt productItem2) {
		assertThat(productItem1).isNotNull();
		assertThat(productItem2).isNotNull();

		assertThat(productItem1.getName()).isNotNull();
		assertThat(productItem2.getName()).isNotNull();

		assertThat(productItem1.getDescription()).isNotNull();
		assertThat(productItem2.getDescription()).isNotNull();

		assertThat(productItem1.getName()).isEqualTo(productItem2.getName());
		assertThat(productItem1.getDescription()).isEqualTo(productItem2.getDescription());
		compareProduct(productItem1.getProduct(), productItem2.getProduct());
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

	private ProductItemEnt createProductItem(final String name, final String manufacturer, final String brand, final String productItemName, final String productItemDesc) {
		ProductItemEnt productItemEnt = new ProductItemEnt();
		productItemEnt.setDescription(productItemDesc);
		productItemEnt.setName(productItemName);
		productItemEnt.setProduct(createProduct(name, manufacturer, brand));

		return productItemEnt;
	}

	private ProductEnt createProduct(final String name, final String manufacturer, final String brand) {
		ProductEnt productEnt = new ProductEnt();
		productEnt.setName(name);
		productEnt.setManufacturer(manufacturer);
		productEnt.setBrand(brand);

		return productEnt;
	}
}
