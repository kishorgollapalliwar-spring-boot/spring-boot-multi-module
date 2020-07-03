package com.kishor.home.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kishor.home.core.dto.ProductDTO;
import com.kishor.home.core.dto.ProductItemDTO;
import com.kishor.home.core.entity.ProductEnt;
import com.kishor.home.core.entity.ProductItemEnt;
import com.kishor.home.core.repo.ProductItemRepo;

@SpringBootTest
public class ProductItemServiceTests {
	@Autowired private ProductItemService productItemService;
	@Autowired private ModelMapper modelMapper;

	@MockBean private ProductItemRepo productItemRepo;

	@BeforeAll
	static void startup() {
		//Steps common for all tests
	}

	@Test
	void listTest() {
		List<ProductItemEnt> productItemList = new ArrayList<>();
		ProductItemDTO productLifeBuoyCare = createProductItem("Care", "Unilever", "Lifebuoy", "50gm", "50gm + 12% extra");
		ProductItemDTO productLifeBuoyNature= createProductItem("Nature", "Unilever", "Lifebuoy", "50gm", "50gm + 12% extra");

		productItemList.add(productLifeBuoyCare.getEntity(modelMapper, ProductItemEnt.class));
		productItemList.add(productLifeBuoyNature.getEntity(modelMapper, ProductItemEnt.class));

		doAnswer(invocation -> {
			return productItemList;
		}).when(productItemRepo).findAll();

		List<ProductItemDTO> fetchedProductItemList = productItemService.list();
		assertThat(fetchedProductItemList).isNotNull();
		assertThat(fetchedProductItemList.isEmpty()).isFalse();

		for (int index = 0; index < fetchedProductItemList.size(); index++) {
			compareProductItem(fetchedProductItemList.get(index), new ProductItemDTO(modelMapper, productItemList.get(index)));
		}
	}

	@Test
	void createTest() {
		List<ProductItemEnt> productItemList = new ArrayList<>();
		int initialSize = productItemList.size();

		doAnswer(invocation -> {
			productItemList.add(invocation.getArgument(0));
			return invocation.getArgument(0);
		}).when(productItemRepo).save(Mockito.any(ProductItemEnt.class));

		ProductItemDTO productItemSaved = productItemService.create(createProductItem("Care", "Unilever", "Lifebuoy", "50gm", "50gm + 12% extra"));
		assertThat(productItemSaved).isNotNull();
		assertThat(productItemSaved).isEqualTo(new ProductItemDTO(modelMapper, productItemList.get(0)));
		assertThat(initialSize + 1).isEqualTo(productItemList.size());

		compareProductItem(new ProductItemDTO(modelMapper, productItemList.get(0)), productItemSaved);
	}

	@Test
	void searchTest() {
		ProductItemDTO productItem = createProductItem("Care", "Unilever", "Lifebuoy", "50gm", "50gm + 12% extra");
		List<ProductItemDTO> productItemList = new ArrayList<>();
		productItemList.add(productItem);

		doReturn(productItemList).when(productItemRepo).findByProduct(Mockito.any(ProductEnt.class));

		List<ProductItemDTO> productItemListReturned = productItemService.searchByProduct(new ProductDTO());
		compareProductItem(productItem, productItemListReturned.get(0));
	}

	private void compareProductItem(final ProductItemDTO productItemDTO, final ProductItemDTO productItemDTO2) {
		assertThat(productItemDTO).isNotNull();
		assertThat(productItemDTO2).isNotNull();

		assertThat(productItemDTO.getName()).isNotNull();
		assertThat(productItemDTO2.getName()).isNotNull();

		assertThat(productItemDTO.getDescription()).isNotNull();
		assertThat(productItemDTO2.getDescription()).isNotNull();

		assertThat(productItemDTO.getName()).isEqualTo(productItemDTO2.getName());
		assertThat(productItemDTO.getDescription()).isEqualTo(productItemDTO2.getDescription());
		compareProduct(productItemDTO.getProduct(), productItemDTO2.getProduct());
	}

	private void compareProduct(final ProductDTO productDTO, final ProductDTO productDTO2) {
		assertThat(productDTO).isNotNull();
		assertThat(productDTO2).isNotNull();

		assertThat(productDTO.getName()).isNotNull();
		assertThat(productDTO2.getName()).isNotNull();

		assertThat(productDTO.getManufacturer()).isNotNull();
		assertThat(productDTO2.getManufacturer()).isNotNull();

		assertThat(productDTO.getName()).isEqualTo(productDTO2.getName());
		assertThat(productDTO.getManufacturer()).isEqualTo(productDTO2.getManufacturer());
		assertThat(productDTO.getBrand()).isEqualTo(productDTO2.getBrand());
	}

	private ProductItemDTO createProductItem(final String name, final String manufacturer, final String brand, final String productItemName, final String productItemDesc) {
		ProductItemDTO productItemDTO = new ProductItemDTO();
		productItemDTO.setDescription(productItemDesc);
		productItemDTO.setName(productItemName);
		productItemDTO.setProduct(createProduct(name, manufacturer, brand));

		return productItemDTO;
	}

	private ProductDTO createProduct(final String name, final String manufacturer, final String brand) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName(name);
		productDTO.setManufacturer(manufacturer);
		productDTO.setBrand(brand);

		return productDTO;
	}
}
