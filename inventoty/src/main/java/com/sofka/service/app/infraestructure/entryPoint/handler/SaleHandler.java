package com.sofka.service.app.infraestructure.entryPoint.handler;

import java.net.URI;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sofka.service.app.domain.useCase.ICreateSaleUseCase;
import com.sofka.service.app.domain.useCase.RegisterSaleUseCase;
import com.sofka.service.app.infraestructure.drivenAdapter.bus.ISenderQueue;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.Venta;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.VentaDetalle;
import com.sofka.service.app.infraestructure.entryPoint.dto.ErrorResponseDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductDetailDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseSaleCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.SaleDto;

import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SaleHandler {

	private final ICreateSaleUseCase iCreateSaleRetailUseCase;

	private final ICreateSaleUseCase iCreateSaleMayorUseCase;

	private final RegisterSaleUseCase registerSaleUseCase;

	private final Validator validator;

	private final ISenderQueue senderQueueSale;

	public SaleHandler(@Qualifier("CreateSaleRetailUseCase") ICreateSaleUseCase iCreateSaleRetailUseCase,
			Validator validator, @Qualifier("CreateSaleMayorUseCase") ICreateSaleUseCase iCreateSaleMayorUseCase,
			RegisterSaleUseCase registerSaleUseCase, @Qualifier("SenderQueueSale") ISenderQueue senderQueueSale) {
		this.iCreateSaleRetailUseCase = iCreateSaleRetailUseCase;
		this.iCreateSaleMayorUseCase = iCreateSaleMayorUseCase;
		this.registerSaleUseCase = registerSaleUseCase;
		this.validator = validator;
		this.senderQueueSale = senderQueueSale;

	}

	public Mono<ServerResponse> generateSaleRetail(ServerRequest request) {

		Flux<ProductDetailDto> product = request.bodyToFlux(ProductDetailDto.class);

		return product.collectList().flatMap(list -> {

			list.forEach(p -> {
				Errors errors = new BeanPropertyBindingResult(p, ProductCreateDto.class.getName());
				validator.validate(p, errors);
				if (errors.hasErrors())
					throw new RuntimeErrorException(null,
							"Articulo con dato errado [" + errors.getFieldError().getField() + "]");
			});

			return iCreateSaleRetailUseCase.generate(list).flatMap(sale -> {

				return ServerResponse.created(URI.create("/api/sale/")).contentType(MediaType.APPLICATION_JSON)
						.bodyValue(ResponseSaleCreateDto.building().codeResponse(HttpResponseStatus.CREATED.code())
								.message("Venta creada satisfactoriamente").sale(sale).build());
			});

		}).onErrorResume(e -> {
			return ServerResponse.badRequest()
					.bodyValue(ErrorResponseDto.building().codeResponse(HttpResponseStatus.BAD_REQUEST.code())
							.message("Error en la captura de datos").message(e.getMessage()).build());
		});

	}

	public Mono<ServerResponse> generateSaleMayor(ServerRequest request) {

		Flux<ProductDetailDto> product = request.bodyToFlux(ProductDetailDto.class);

		return product.collectList().flatMap(list -> {

			list.forEach(p -> {
				Errors errors = new BeanPropertyBindingResult(p, ProductCreateDto.class.getName());
				validator.validate(p, errors);
				if (errors.hasErrors())
					throw new RuntimeErrorException(null,
							"Articulo con dato errado [" + errors.getFieldError().getField() + "]");
			});

			return iCreateSaleMayorUseCase.generate(list).flatMap(sale -> {

				return ServerResponse.created(URI.create("/api/sale/")).contentType(MediaType.APPLICATION_JSON)
						.bodyValue(ResponseSaleCreateDto.building().codeResponse(HttpResponseStatus.CREATED.code())
								.message("Venta creada satisfactoriamente").sale(sale).build());
			});

		}).onErrorResume(e -> {
			return ServerResponse.badRequest()
					.bodyValue(ErrorResponseDto.building().codeResponse(HttpResponseStatus.BAD_REQUEST.code())
							.message("Error en la captura de datos").message(e.getMessage()).build());
		});

	}

	public Mono<ServerResponse> registerSale(ServerRequest request) {

		Mono<SaleDto> sale = request.bodyToMono(SaleDto.class);

		return sale.flatMap(s -> {

			Errors errors = new BeanPropertyBindingResult(s, SaleDto.class.getName());
			validator.validate(s, errors);

			if (errors.hasErrors()) {
				return Flux.fromIterable(errors.getFieldErrors())
						.map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList().flatMap(list -> {
							senderQueueSale.senderQueueError(s);
							return ServerResponse.badRequest()
									.bodyValue(ErrorResponseDto.building()
											.codeResponse(HttpResponseStatus.BAD_REQUEST.code())
											.message("Error en la captura de datos").message(list).build());
						});
			} else {

				Venta ventaToDataBase = Venta.building().total(s.getTotal()).tipo(s.getTipo())
						.detalles(s.getDatail().stream()
								.map(d -> VentaDetalle.building().name(d.getName()).descripcion(d.getDescription())
										.price(d.getPrice()).cantidad(d.getQuantity()).totalcost(d.getTotalCost()))
								.toList())
						.build();

				return registerSaleUseCase.register(ventaToDataBase).flatMap(saleDataBase -> {
					senderQueueSale.senderQueueSuccess(saleDataBase);
					return ServerResponse.created(URI.create("/api/sale/")).contentType(MediaType.APPLICATION_JSON)
							.bodyValue(ResponseSaleCreateDto.building().codeResponse(HttpResponseStatus.CREATED.code())
									.message("Venta registrada satisfactoriamente").sale(s).build());
				});
			}
		});
	}

}
