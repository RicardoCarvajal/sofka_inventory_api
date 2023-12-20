package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.math.BigDecimal;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(code, description, mayorPrice, name, retailPrice, stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCreateDto other = (ProductCreateDto) obj;
		return Objects.equals(code, other.code) && Objects.equals(description, other.description)
				&& Objects.equals(mayorPrice, other.mayorPrice) && Objects.equals(name, other.name)
				&& Objects.equals(retailPrice, other.retailPrice) && Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "ProductCreateDto [name=" + name + ", description=" + description + ", retailPrice=" + retailPrice
				+ ", mayorPrice=" + mayorPrice + ", code=" + code + ", stock=" + stock + "]";
	}

}
