package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SaleDetailTotalDto {

	private String id;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String description;

	@NotNull
	@Min(1)
	private BigDecimal price;

	@NotNull
	@Min(1)
	private Integer quantity;

	@NotNull
	@Min(1)
	private BigDecimal totalCost;

	public SaleDetailTotalDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripcion) {
		this.description = descripcion;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public static SaleDetailTotalDto building() {
		return new SaleDetailTotalDto();
	}

	public SaleDetailTotalDto id(String id) {
		this.id = id;
		return this;
	}

	public SaleDetailTotalDto name(String name) {
		this.name = name;
		return this;
	}

	public SaleDetailTotalDto description(String descripcion) {
		this.description = descripcion;
		return this;
	}

	public SaleDetailTotalDto price(BigDecimal price) {
		this.price = price;
		return this;
	}

	public SaleDetailTotalDto quantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public SaleDetailTotalDto totalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
		return this;
	}

	public SaleDetailTotalDto build() {
		return this;
	}

}
