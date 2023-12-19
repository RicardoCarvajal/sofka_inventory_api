package com.sofka.service.app.infraestructure.entryPoint.handler;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sofka.service.app.domain.useCase.CreateProductUseCase;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseDto;

import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

	private final CreateProductUseCase createProductUseCase;

	private final Validator validator;

	public ProductHandler(CreateProductUseCase createProductUseCase, Validator validator) {
		this.createProductUseCase = createProductUseCase;
		this.validator = validator;
	}

	public Mono<ServerResponse> createProduct(ServerRequest request) {

		Mono<ProductDto> product = request.bodyToMono(ProductDto.class);

		return product.flatMap(p -> {

			Errors errors = new BeanPropertyBindingResult(p, ProductDto.class.getName());
			validator.validate(p, errors);

			if (errors.hasErrors()) {
				return Flux.fromIterable(errors.getFieldErrors())
						.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList().flatMap(list -> {
							return ServerResponse.badRequest()
									.bodyValue(ResponseDto.createResponseDto()
											.codeResponse(HttpResponseStatus.BAD_REQUEST.code())
											.message("Error en la captura de datos").data(list).build());
						});
			} else {
				return createProductUseCase.create(Producto.cerateProducto().descripcion(p.getDescription())
						.codigo(p.getCode()).precio(p.getCost()).nombre(p.getName()).build())
						.flatMap(productDataBase -> {
							return ServerResponse.created(URI.create("/api/product/"))
									.contentType(MediaType.APPLICATION_JSON)
									.bodyValue(ResponseDto.createResponseDto()
											.codeResponse(HttpResponseStatus.CREATED.code())
											.message("Producto creado satisfactoriamente").data(productDataBase)
											.build());
						});
			}
		});

	}

}
