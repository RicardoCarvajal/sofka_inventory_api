package com.sofka.service.app.domain.useCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.drivenAdapter.repository.IProductoRepository;

import reactor.core.publisher.Flux;

@Service
public class CreateProductsUseCase {

	private final IProductoRepository iProductoRepository;

	public CreateProductsUseCase(IProductoRepository iProductoRepository) {
		this.iProductoRepository = iProductoRepository;
	}

	public Flux<Producto> create(List<Producto> producto) {
		return iProductoRepository.saveAll(producto);
	}

}
