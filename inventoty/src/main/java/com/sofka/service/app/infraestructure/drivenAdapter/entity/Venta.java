package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ventas")
public class Venta {

	@Id
	private String id;

	private BigDecimal total;

	private List<VentaDetalle> detalles;

	private String tipo;

	public Venta() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<VentaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<VentaDetalle> detalles) {
		this.detalles = detalles;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static Venta building() {
		return new Venta();
	}

	public Venta id(String id) {
		this.id = id;
		return this;
	}

	public Venta total(BigDecimal total) {
		this.total = total;
		return this;
	}

	public Venta detalles(List<VentaDetalle> detalles) {
		this.detalles = detalles;
		return this;
	}

	public Venta tipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public Venta build() {
		return this;
	}

}
