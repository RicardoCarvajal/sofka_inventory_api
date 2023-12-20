package com.sofka.service.app.domain.useCase;

import org.springframework.stereotype.Service;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Venta;
import com.sofka.service.app.infraestructure.drivenAdapter.repository.IVentaRepository;

import reactor.core.publisher.Mono;

@Service
public class RegisterSaleUseCase {

	private final IVentaRepository iVentaRepository;

	public RegisterSaleUseCase(IVentaRepository iVentaRepository) {
		this.iVentaRepository = iVentaRepository;

	}

	public Mono<Venta> register(Venta venta) {
		return iVentaRepository.save(venta);
	}

}
