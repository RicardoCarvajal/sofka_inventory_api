package com.sofka.service.app.infraestructure.drivenAdapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;

public interface IProductoRepository extends ReactiveMongoRepository<Producto, String> {

}
