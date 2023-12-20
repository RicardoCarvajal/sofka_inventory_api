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
import com.sofka.service.app.infraestructure.entryPoint.dto.SaleDto;
import com.sofka.service.app.infraestructure.entryPoint.handler.SaleHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class SaleRouter {

	@Bean
	@RouterOperation(path = "/api/sale/retail", produces = MediaType.APPLICATION_JSON_VALUE, beanClass = SaleHandler.class, beanMethod = "generateSaleRetail", method = RequestMethod.POST, operation = @Operation(operationId = "generateSaleRetail", tags = "Caso de uso crear venta", requestBody = @RequestBody(required = true, description = "Venta a ser creada", content = @Content(schema = @Schema(implementation = ProductDetailDto.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Venta creada satisfactoriamente", content = @Content(schema = @Schema(implementation = ResponseSaleCreateDto.class))),
			@ApiResponse(responseCode = "400", description = "Data de entrada errada", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) }))
	public RouterFunction<ServerResponse> generateSaleRetail(SaleHandler saleHandler) {
		return route(POST("/api/sale/retail"), saleHandler::generateSaleRetail);
	}

	@Bean
	@RouterOperation(path = "/api/sale/mayor", produces = MediaType.APPLICATION_JSON_VALUE, beanClass = SaleHandler.class, beanMethod = "generateSaleMayor", method = RequestMethod.POST, operation = @Operation(operationId = "generateSaleMayor", tags = "Caso de uso crear venta", requestBody = @RequestBody(required = true, description = "Venta a ser creada", content = @Content(schema = @Schema(implementation = ProductDetailDto.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Venta creada satisfactoriamente", content = @Content(schema = @Schema(implementation = ResponseSaleCreateDto.class))),
			@ApiResponse(responseCode = "400", description = "Data de entrada errada", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) }))
	public RouterFunction<ServerResponse> generateSaleMayor(SaleHandler saleHandler) {
		return route(POST("/api/sale/mayor"), saleHandler::generateSaleMayor);
	}

	@Bean
	@RouterOperation(path = "/api/sale/register", produces = MediaType.APPLICATION_JSON_VALUE, beanClass = SaleHandler.class, beanMethod = "registerSale", method = RequestMethod.POST, operation = @Operation(operationId = "registerSale", tags = "Caso de uso registrar venta", requestBody = @RequestBody(required = true, description = "Venta a ser registrada", content = @Content(schema = @Schema(implementation = SaleDto.class))), responses = {
			@ApiResponse(responseCode = "201", description = "Venta registrada satisfactoriamente", content = @Content(schema = @Schema(implementation = SaleDto.class))),
			@ApiResponse(responseCode = "400", description = "Data de entrada errada", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) }))
	public RouterFunction<ServerResponse> registreSale(SaleHandler saleHandler) {
		return route(POST("/api/sale/register"), saleHandler::registerSale);
	}
}
