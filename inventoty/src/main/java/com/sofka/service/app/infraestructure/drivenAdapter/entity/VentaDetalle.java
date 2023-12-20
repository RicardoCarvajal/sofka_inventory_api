package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VentaDetalle {

	private String id;

	private String name;

	private String descripcion;

	private BigDecimal price;

	private Integer cantidad;

	private BigDecimal totalcost;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(BigDecimal totalcost) {
		this.totalcost = totalcost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static VentaDetalle building() {
		return new VentaDetalle();
	}

	public VentaDetalle id(String id) {
		this.id = id;
		return this;
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
