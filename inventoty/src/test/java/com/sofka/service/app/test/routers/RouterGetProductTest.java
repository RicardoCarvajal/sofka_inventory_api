package com.sofka.service.app.test.routers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseProductsDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouterGetProductTest {

	@Autowired
	private WebTestClient client;

	@Test
	void getProductsTest() {

		client.get().uri("/api/products?page=0&elements=6").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk().expectBody(ResponseProductsDto.class).consumeWith(response -> {
					ResponseProductsDto responseProduct = response.getResponseBody();
					assertEquals(responseProduct.getCodeResponse(), 200);
					assertEquals(responseProduct.getSize(), 6);
				});

	}

}
