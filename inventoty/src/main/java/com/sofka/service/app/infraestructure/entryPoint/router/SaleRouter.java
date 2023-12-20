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

import com.sofka.service.app.infraestructure.entryPoint.dto.ErrorResponseDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductDetailDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseSaleCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.handler.SaleHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class SaleRouter {

	@Bean
	@RouterOperation(path = "/api/sale/retail", produces = MediaType.APPLICATION_JSON_VALUE, beanClass = SaleHandler.class, beanMethod = "generateSaleRetail", method = RequestMethod.POST, operation = @Operation(operationId = "generateSaleRetail", tags = "Caso de uso crear venta", requestBody = @RequestBody(required = true, description = "Producto a ser creado", content = @Content(schema = @Schema(implementation = ProductDetailDto.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Venta creada satisfactoriamente", content = @Content(schema = @Schema(implementation = ResponseSaleCreateDto.class))),
			@ApiResponse(responseCode = "400", description = "Data de entrada errada", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) }))
	public RouterFunction<ServerResponse> generateSaleRetail(SaleHandler saleHandler) {
		return route(POST("/api/sale/retail"), saleHandler::generateSaleRetail);
	}

}
