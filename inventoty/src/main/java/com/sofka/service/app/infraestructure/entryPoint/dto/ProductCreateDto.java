package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductCreateDto {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String description;

	@NotNull
	@Min(1)
	private BigDecimal retailPrice;

	@NotNull
	@Min(1)
	private BigDecimal mayorPrice;

	@NotNull
	@NotEmpty
	private String code;

	@NotNull
	@Min(1)
	private Integer stock;

	public ProductCreateDto(String name, String description, BigDecimal retailPrice, BigDecimal mayorPrice, String code,
			Integer stock) {
		this.name = name;
		this.description = description;
		this.retailPrice = retailPrice;
		this.mayorPrice = mayorPrice;
		this.code = code;
		this.stock = stock;
	}

	public ProductCreateDto() {
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

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getMayorPrice() {
		return mayorPrice;
	}

	public void setMayorPrice(BigDecimal mayorPrice) {
		this.mayorPrice = mayorPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
