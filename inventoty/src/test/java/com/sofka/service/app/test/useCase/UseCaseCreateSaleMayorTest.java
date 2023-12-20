package com.sofka.service.app.test.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sofka.service.app.domain.useCase.CreateSaleRetailUseCase;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductDetailDto;

import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UseCaseCreateSaleMayorTest {

	@Autowired
	private CreateSaleRetailUseCase createSaleRetailUseCase;

	@Test
	void create() {

		List<ProductDetailDto> products = new ArrayList<>();

		ProductDetailDto productsDto1 = new ProductDetailDto();
		ProductDetailDto productsDto2 = new ProductDetailDto();

		productsDto1.setIdProduct("6582ff3301a3873eb1bdff7e");
		productsDto1.setQuantity(2);

		productsDto2.setIdProduct("6582ff3301a3873eb1bdff7d");
		productsDto2.setQuantity(3);

		products.add(productsDto1);
		products.add(productsDto2);

		var result = createSaleRetailUseCase.generate(products);

		StepVerifier.create(result).assertNext(p -> {
			assertNotNull(p);
			assertEquals(p.getTotal(), new BigDecimal(1303.05).setScale(2, RoundingMode.HALF_UP));
			System.out.println(p.getTotal());
		}).verifyComplete();

	}

}
