package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ventas")
public class Venta {

	@Id
	private String id;

	private Integer renglones;

	private BigDecimal total;

	public Venta() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
