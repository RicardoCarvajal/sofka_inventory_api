package com.sofka.service.app.infraestructure.entryPoint.dto;

public class ResponseDto {

	private Integer codeResponse;

	private String message;

	private Object data;

	public ResponseDto(Integer codeResponse, String message, Object data) {
		this.codeResponse = codeResponse;
		this.message = message;
		this.data = data;
	}

	public ResponseDto() {
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/* Builder */

	public static ResponseDto createResponseDto() {
		return new ResponseDto();
	}

	public ResponseDto codeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
		return this;
	}

	public ResponseDto message(String message) {
		this.message = message;
		return this;
	}

	public ResponseDto data(Object data) {
		this.data = data;
		return this;
	}

	public ResponseDto build() {
		return this;
	}

}
