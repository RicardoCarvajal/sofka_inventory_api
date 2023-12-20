package com.sofka.service.app.test.routers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.sofka.service.app.infraestructure.entryPoint.dto.ErrorResponseDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouterCreateProductsTest {

	@Autowired
	private WebTestClient client;

	@Test
	void createProductTest() {

		client.post().uri("/api/products").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(null).exchange().expectStatus().isBadRequest().expectBody(ErrorResponseDto.class)
				.consumeWith(response -> {
					ErrorResponseDto responseProduct = response.getResponseBody();
					assertEquals(responseProduct.getCodeResponse(), 400);
					assertEquals(responseProduct.getMessage().size(), 2);
				});

	}

}
