package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.math.BigDecimal;

public class DetalleVenta {

	private String idProducto;

	private String idLote;

	private Integer cantidad;

	private BigDecimal precio;

	public DetalleVenta(String idProducto, String idLote, Integer cantidad, BigDecimal precio) {
		this.idProducto = idProducto;
		this.idLote = idLote;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public DetalleVenta() {
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getIdLote() {
		return idLote;
	}

	public void setIdLote(String idLote) {
		this.idLote = idLote;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}
