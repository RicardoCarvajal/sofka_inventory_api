package com.sofka.service.app.infraestructure.entryPoint.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.handler.ProductHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class ProductRouter {

	@Bean
	@RouterOperation(path = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE, beanClass = ProductHandler.class, beanMethod = "createProduct", method = RequestMethod.POST, operation = @Operation(operationId = "createProduct", tags = "Caso de uso crear producto", requestBody = @RequestBody(required = true, description = "Producto a ser creado", content = @Content(schema = @Schema(implementation = ProductCreateDto.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Producto creado satisfactoriamente", content = @Content(schema = @Schema(implementation = Producto.class))),
			@ApiResponse(responseCode = "400", description = "Data de entrada errada", content = @Content()) }))
	public RouterFunction<ServerResponse> createAccount(ProductHandler productHandler) {
		return route(POST("/api/product"), productHandler::createProduct);
	}

}