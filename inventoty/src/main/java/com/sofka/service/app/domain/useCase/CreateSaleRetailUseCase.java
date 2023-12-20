package com.sofka.service.app.domain.useCase;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sofka.service.app.infraestructure.drivenAdapter.repository.IProductoRepository;
import com.sofka.service.app.infraestructure.entryPoint.dto.ProductDetailDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.SaleDetailTotalDto;
import com.sofka.service.app.infraestructure.entryPoint.dto.SaleDto;

import reactor.core.publisher.Mono;

@Service("CreateSaleRetailUseCase")
public class CreateSaleRetailUseCase implements ICreateSaleUseCase {

	private static final String TYPERETAIL = "DETAL";

	private final IProductoRepository iProductoRepository;

	public CreateSaleRetailUseCase(IProductoRepository iProductoRepository) {
		this.iProductoRepository = iProductoRepository;
	}

	@Override
	public Mono<SaleDto> generate(List<ProductDetailDto> products) {

		return iProductoRepository
				.findAllById(products.stream().map(ProductDetailDto::getIdProduct).distinct().toList()).map(p -> {

					Integer quantity = products.stream().filter(product -> product.getIdProduct().equals(p.getId()))
							.findAny().get().getQuantity();

					return SaleDetailTotalDto.building().name(p.getNombre()).description(p.getDescripcion())
							.price(p.getPrecioDetal()).quantity(quantity)
							.totalCost(p.getPrecioDetal().multiply(new BigDecimal(quantity))).id(p.getId()).build();

				}).collectList().map(list -> {

					BigDecimal totalSale = list.stream().map(SaleDetailTotalDto::getTotalCost).reduce(BigDecimal.ZERO,
							BigDecimal::add);

					return SaleDto.building().total(totalSale).datail(list).tipo(TYPERETAIL);

				});

	}

}
