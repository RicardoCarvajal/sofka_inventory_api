package com.sofka.service.app.infraestructure.drivenAdapter.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("productos")
public class Producto {

	@Id
	private String id;

	private String nombre;

	private String descripcion;

	private BigDecimal precio;

	private String codigo;

	public Producto(String id, String nombre, String descripcion, BigDecimal precio, String codigo) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.codigo = codigo;
	}

	public Producto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/* Builder */

	public static Producto cerateProducto() {
		return new Producto();
	}

	public Producto id(String id) {
		this.id = id;
		return this;
	}

	public Producto nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}

	public Producto descripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public Producto precio(BigDecimal precio) {
		this.precio = precio;
		return this;
	}

	public Producto codigo(String codigo) {
		this.codigo = codigo;
		return this;
	}

	public Producto build() {
		return this;
	}

}
