package com.sofka.service.app.domain.useCase;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.drivenAdapter.repository.IProductoRepository;
import com.sofka.service.app.infraestructure.entryPoint.dto.SortPageCriteriaDto;

import reactor.core.publisher.Flux;

@Service
public class GetProductsUseCase {

	private final IProductoRepository iProductoRepository;

	public GetProductsUseCase(IProductoRepository iProductoRepository) {
		this.iProductoRepository = iProductoRepository;
	}

	public Flux<Producto> get(SortPageCriteriaDto page) {

		Sort sort = Sort.by(Sort.Direction.fromString("DESC"), "id");
		Pageable pageable = PageRequest.of(page.getPage(), page.getElements(), sort);

		return iProductoRepository.findAllBy(pageable);
	}

}
