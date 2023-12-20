package com.sofka.service.app.infraestructure.entryPoint.dto;

public class ResponseSaleCreateDto {

	private Integer codeResponse;

	private String message;

	private SaleDto sale;

	public ResponseSaleCreateDto() {
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

	public SaleDto getSale() {
		return sale;
	}

	public void setSale(SaleDto sale) {
		this.sale = sale;
	}

	/* Builder */

	public static ResponseSaleCreateDto building() {
		return new ResponseSaleCreateDto();
	}

	public ResponseSaleCreateDto codeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
		return this;
	}

	public ResponseSaleCreateDto message(String message) {
		this.message = message;
		return this;
	}

	public ResponseSaleCreateDto sale(SaleDto sale) {
		this.sale = sale;
		return this;
	}

	public ResponseSaleCreateDto build() {
		return this;
	}

}
