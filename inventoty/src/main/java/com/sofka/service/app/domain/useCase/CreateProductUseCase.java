package com.sofka.service.app.domain.useCase;

import org.springframework.stereotype.Service;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.drivenAdapter.repository.IProductoRepository;

import reactor.core.publisher.Mono;

@Service
public class CreateProductUseCase {

	private final IProductoRepository iProductoRepository;

	public CreateProductUseCase(IProductoRepository iProductoRepository) {
		this.iProductoRepository = iProductoRepository;
	}

	public Mono<Producto> create(Producto producto) {
		return iProductoRepository.save(producto);
	}

}
