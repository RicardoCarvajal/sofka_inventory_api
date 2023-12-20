package com.sofka.service.app.infraestructure.entryPoint.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductDetailDto {

	@NotNull
	@NotEmpty
	private String idProduct;

	@NotNull
	@Min(1)
	private Integer quantity;

	public ProductDetailDto() {
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
