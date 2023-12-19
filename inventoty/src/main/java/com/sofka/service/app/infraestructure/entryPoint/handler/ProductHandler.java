package com.sofka.service.app.infraestructure.entryPoint.handler;

import java.net.URI;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sofka.service.app.domain.useCase.CreateProductUseCase;
import com.sofka.service.app.infraestructure.drivenAdapter.bus.ISenderQueue;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ErrorResponseDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseProductCreateDto;

import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

	private final CreateProductUseCase createProductUseCase;

	private final Validator validator;

	private final ISenderQueue senderQueueProduct;

	public ProductHandler(CreateProductUseCase createProductUseCase, Validator validator,
			@Qualifier("SenderQueueProduct") ISenderQueue senderQueueProduct) {
		this.createProductUseCase = createProductUseCase;
		this.validator = validator;
		this.senderQueueProduct = senderQueueProduct;
	}

	public Mono<ServerResponse> createProduct(ServerRequest request) {

		Mono<ProductCreateDto> product = request.bodyToMono(ProductCreateDto.class);

		return product.flatMap(p -> {

			Errors errors = new BeanPropertyBindingResult(p, ProductCreateDto.class.getName());
			validator.validate(p, errors);

			if (errors.hasErrors()) {
				return Flux.fromIterable(errors.getFieldErrors())
						.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList().flatMap(list -> {
							senderQueueProduct.senderQueueError(p);
							return ServerResponse.badRequest()
									.bodyValue(ErrorResponseDto.building()
											.codeResponse(HttpResponseStatus.BAD_REQUEST.code())
											.message("Error en la captura de datos").message(list).build());
						});
			} else {
				return createProductUseCase.create(Producto.cerateProducto().descripcion(p.getDescription())
						.codigo(p.getCode()).precioMayor(p.getMayorPrice()).precioDetal(p.getRetailPrice())
						.nombre(p.getName()).existencia(p.getStock()).build()).flatMap(productDataBase -> {
							senderQueueProduct.senderQueueSuccess(productDataBase);
							return ServerResponse.created(URI.create("/api/product/"))
									.contentType(MediaType.APPLICATION_JSON)
									.bodyValue(ResponseProductCreateDto.building()
											.codeResponse(HttpResponseStatus.CREATED.code())
											.message("Producto creado satisfactoriamente")
											.idProduct(productDataBase.getId()).build());
						});
			}
		});

	}

}
