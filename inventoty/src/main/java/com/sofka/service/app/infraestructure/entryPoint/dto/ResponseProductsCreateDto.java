package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.util.List;

public class ResponseProductsCreateDto {

	private Integer codeResponse;

	private String message;

	private List<String> idProduct;

	public ResponseProductsCreateDto() {
	}

	public Integer getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
	}

	public List<String> getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(List<String> idProduct) {
		this.idProduct = idProduct;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/* Builder */

	public static ResponseProductsCreateDto building() {
		return new ResponseProductsCreateDto();
	}

	public ResponseProductsCreateDto codeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
		return this;
	}

	public ResponseProductsCreateDto message(String message) {
		this.message = message;
		return this;
	}

	public ResponseProductsCreateDto idProduct(List<String> idProduct) {
		this.idProduct = idProduct;
		return this;
	}

	public ResponseProductsCreateDto build() {
		return this;
	}

}
