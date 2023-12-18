package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("lotes")
public class Lote {

	@Id
	private String id;

	private String idProducto;

	private Integer existencia;

	private Date fecha;

	public Lote(String id, String idProducto, Integer existencia, Date fecha) {
		this.id = id;
		this.idProducto = idProducto;
		this.existencia = existencia;
		this.fecha = fecha;
	}

	public Lote() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getExistencia() {
		return existencia;
	}

	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
