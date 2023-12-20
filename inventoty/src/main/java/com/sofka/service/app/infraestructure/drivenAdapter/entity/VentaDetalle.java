package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VentaDetalle {

	private String name;

	private String descripcion;

	private BigDecimal price;

	private Integer cantidad;

	private BigDecimal totalcost;

	public static VentaDetalle building() {
		return new VentaDetalle();
	}

	public VentaDetalle name(String name) {
		this.name = name;
		return this;
	}

	public VentaDetalle descripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public VentaDetalle price(BigDecimal price) {
		this.price = price;
		return this;
	}

	public VentaDetalle cantidad(Integer cantidad) {
		this.cantidad = cantidad;
		return this;
	}

	public VentaDetalle totalcost(BigDecimal totalcost) {
		this.totalcost = totalcost;
		return this;
	}

	public VentaDetalle build() {
		return this;
	}

}
