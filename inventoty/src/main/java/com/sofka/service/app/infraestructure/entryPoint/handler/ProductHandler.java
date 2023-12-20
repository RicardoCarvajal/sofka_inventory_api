package com.sofka.service.app.infraestructure.entryPoint.handler;

import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sofka.service.app.domain.useCase.CreateProductUseCase;
import com.sofka.service.app.domain.useCase.CreateProductsUseCase;
import com.sofka.service.app.infraestructure.drivenAdapter.bus.ISenderQueue;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ErrorResponseDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseProductCreateDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.ResponseProductsCreateDto;

import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

	private final CreateProductUseCase createProductUseCase;

	private final CreateProductsUseCase createProductsUseCase;

	private final Validator validator;

	private final ISenderQueue senderQueueProduct;

	public ProductHandler(CreateProductUseCase createProductUseCase, Validator validator,
			@Qualifier("SenderQueueProduct") ISenderQueue senderQueueProduct,
			CreateProductsUseCase createProductsUseCase) {
		this.createProductUseCase = createProductUseCase;
		this.createProductsUseCase = createProductsUseCase;
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

	public Mono<ServerResponse> createProducts(ServerRequest request) {

		return request.multipartData().map(multipartData -> multipartData.toSingleValueMap().get("file"))
				.cast(FilePart.class).flatMap(f -> {
					return f.content().collectList();
				}).map(list -> {
					return handlerFile(list);
				}).flatMap(list -> {
					return createProductsUseCase.create(list).map(p -> {
						senderQueueProduct.senderQueueSuccess(p);
						return p.getId();
					}).collectList();

				})
				.flatMap(list -> ServerResponse.created(URI.create("/api/products/"))
						.contentType(MediaType.APPLICATION_JSON)
						.bodyValue(ResponseProductsCreateDto.building().codeResponse(HttpResponseStatus.CREATED.code())
								.message("productos creados de manera exitosa").idProduct(list).build()))
				.onErrorResume(e -> {
					senderQueueProduct.senderQueueError("Carga de archivos no efectuada");
					return ServerResponse.badRequest()
							.bodyValue(ErrorResponseDto.building().codeResponse(HttpResponseStatus.BAD_REQUEST.code())
									.message("No se pudo cargar el archivo").message(e.getMessage()).build());

				});

	}

	private List<Producto> handlerFile(List<DataBuffer> list) {
		List<Producto> porductos = new ArrayList<Producto>();
		list.forEach(data -> {
			byte[] bytes = new byte[data.readableByteCount()];
			data.read(bytes);
			DataBufferUtils.release(data);
			String document = new String(bytes, StandardCharsets.UTF_8);
			String[] lineas = document.split("\n");
			for (int i = 0; i < lineas.length; i++) {
				String[] parte = lineas[i].split(";");
				if (i != 0) {

					porductos.add(Producto.cerateProducto().codigo(parte[0]).existencia(Integer.valueOf(parte[1]))
							.nombre(parte[2]).descripcion(parte[3]).precioDetal(new BigDecimal(parte[4]))
							.precioMayor(new BigDecimal(parte[5])).build());
				} else {
					if (!(parte[0].equalsIgnoreCase("codigo") && parte[1].equalsIgnoreCase("existencia")
							&& parte[2].equalsIgnoreCase("nombre") && parte[3].equalsIgnoreCase("descripcion")
							&& parte[4].equalsIgnoreCase("percio_detal")
							&& parte[5].equalsIgnoreCase("percio_mayor"))) {
						throw new RuntimeException("El archivo es invalido");
					}

				}
			}
		});
		return porductos;
	}
}
