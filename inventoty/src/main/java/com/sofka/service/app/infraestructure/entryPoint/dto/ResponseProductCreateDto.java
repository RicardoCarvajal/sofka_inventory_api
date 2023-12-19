package com.sofka.service.app.infraestructure.entryPoint.dto;

public class ResponseProductCreateDto {

	private Integer codeResponse;

	private String message;

	private String idProduct;

	public ResponseProductCreateDto(Integer codeResponse, String message, String idProduct) {
		this.codeResponse = codeResponse;
		this.message = message;
		this.idProduct = idProduct;
	}

	public ResponseProductCreateDto() {
	}

	public Integer getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	/* Builder */

	public static ResponseProductCreateDto building() {
		return new ResponseProductCreateDto();
	}

	public ResponseProductCreateDto codeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
		return this;
	}

	public ResponseProductCreateDto message(String message) {
		this.message = message;
		return this;
	}

	public ResponseProductCreateDto idProduct(String idProduct) {
		this.idProduct = idProduct;
		return this;
	}

	public ResponseProductCreateDto build() {
		return this;
	}

}
