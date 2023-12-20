package com.sofka.service.app.domain.useCase;

import java.util.List;

import com.sofka.service.app.infraestructure.entryPoint.dto.ProductDetailDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.SaleDto;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ICreateSaleUseCase {

	public Mono<SaleDto> generate(List<ProductDetailDto> products);

}
