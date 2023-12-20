package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseDto {

	private Integer codeResponse;

	private List<String> message = new ArrayList<String>();

	public ErrorResponseDto(Integer codeResponse, List<String> message) {
		this.codeResponse = codeResponse;
		this.message = message;
	}

	public ErrorResponseDto() {
	}

	public Integer getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	/* Builder */

	public static ErrorResponseDto building() {
		return new ErrorResponseDto();
	}

	public ErrorResponseDto codeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
		return this;
	}

	public ErrorResponseDto message(List<String> message) {
		this.message = message;
		return this;
	}

	public ErrorResponseDto message(String message) {
		this.message.add(message);
		return this;
	}

	public ErrorResponseDto build() {
		return this;
	}

}
