package com.sofka.service.app.infraestructure.drivenAdapter.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;

import reactor.core.publisher.Flux;

public interface IProductoRepository extends ReactiveMongoRepository<Producto, String> {

	Flux<Producto> findAllBy(Pageable pageable);

}
