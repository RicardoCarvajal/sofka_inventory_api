package com.sofka.service.app.infraestructure.drivenAdapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Venta;

public interface IVentaRepository extends ReactiveMongoRepository<Venta, String> {

}
