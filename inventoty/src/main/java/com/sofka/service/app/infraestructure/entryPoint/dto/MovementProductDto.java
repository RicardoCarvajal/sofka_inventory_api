package com.sofka.service.app.infraestructure.entryPoint.dto;

public class MovementProductDto extends MovementDto {

	private Integer quantity;

	public MovementProductDto() {
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/* Builder */

	public static MovementProductDto building() {
		return new MovementProductDto();
	}

	public MovementProductDto idObject(String idObject) {
		this.setIdObject(idObject);
		return this;
	}

	public MovementProductDto typeProcess(String typeProcess) {
		this.setTypeProcess(typeProcess);
		return this;
	}

	public MovementProductDto description(String description) {
		this.setDescription(description);
		return this;
	}

	public MovementProductDto object(Object object) {
		this.setObjects(object);
		return this;
	}

	public MovementProductDto isError(Boolean isError) {
		this.setIsError(isError);
		return this;
	}

	public MovementProductDto quantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public MovementProductDto build() {
		return this;
	}

}
