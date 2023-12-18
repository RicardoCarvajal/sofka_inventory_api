package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ventas")
public class Venta {

	@Id
	private String id;

	private TipoVenta tipo;

	private List<DetalleVenta> detalle;

	private Integer renglones;

	private BigDecimal total;

	public Venta(String id, TipoVenta tipo, List<DetalleVenta> detalle, Integer renglones, BigDecimal total) {
		this.id = id;
		this.tipo = tipo;
		this.detalle = detalle;
		this.renglones = renglones;
		this.total = total;
	}

	public Venta() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoVenta getTipo() {
		return tipo;
	}

	public void setTipo(TipoVenta tipo) {
		this.tipo = tipo;
	}

	public List<DetalleVenta> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleVenta> detalle) {
		this.detalle = detalle;
	}

	public Integer getRenglones() {
		return renglones;
	}

	public void setRenglones(Integer renglones) {
		this.renglones = renglones;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
