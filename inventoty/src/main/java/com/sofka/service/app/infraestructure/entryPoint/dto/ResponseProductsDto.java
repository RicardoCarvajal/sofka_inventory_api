package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.util.List;

import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;

public class ResponseProductsDto {

	private Integer codeResponse;

	private String message;

	private List<Producto> products;

	private Integer size;

	public ResponseProductsDto() {
	}

	public Integer getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
	}

	public List<Producto> getProducts() {
		return products;
	}

	public void setProducts(List<Producto> products) {
		this.products = products;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/* Builder */

	public static ResponseProductsDto building() {
		return new ResponseProductsDto();
	}

	public ResponseProductsDto codeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
		return this;
	}

	public ResponseProductsDto message(String message) {
		this.message = message;
		return this;
	}

	public ResponseProductsDto size(Integer size) {
		this.size = size;
		return this;
	}

	public ResponseProductsDto products(List<Producto> products) {
		this.products = products;
		return this;
	}

	public ResponseProductsDto build() {
		return this;
	}

}
