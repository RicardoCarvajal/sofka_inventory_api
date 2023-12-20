package com.sofka.service.app.test.routers;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.sofka.service.app.infraestructure.entryPoint.dto.ProductCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseProductCreateDto;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouterCreateProductTest {

	@Autowired
	private WebTestClient client;

	@Test
	void createProductTest() {

		ProductCreateDto productoDto = new ProductCreateDto();

		productoDto.setCode("LA-86514A");
		productoDto.setDescription("laptop dell 32G RAM, 1TB de ROM, i7");
		productoDto.setName("laptop dell 14");
		productoDto.setMayorPrice(new BigDecimal(1000));
		productoDto.setRetailPrice(new BigDecimal(1000));
		productoDto.setStock(40);

		client.post().uri("/api/product").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(productoDto), ProductCreateDto.class).exchange().expectStatus().isCreated()
				.expectBody(ResponseProductCreateDto.class).consumeWith(response -> {
					ResponseProductCreateDto responseProduct = response.getResponseBody();

					assertEquals(responseProduct.getCodeResponse(), 201);
					assertNotNull(responseProduct.getIdProduct());

				});

	}

}
