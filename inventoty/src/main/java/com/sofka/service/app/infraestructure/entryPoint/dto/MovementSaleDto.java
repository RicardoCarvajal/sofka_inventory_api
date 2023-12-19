package com.sofka.service.app.infraestructure.entryPoint.dto;

public class MovementSaleDto extends MovementDto {

	private String typeSale;

	public MovementSaleDto() {
	}

	public String getTypeSale() {
		return typeSale;
	}

	public void setTypeSale(String typeSale) {
		this.typeSale = typeSale;
	}

	public static MovementSaleDto building() {
		return new MovementSaleDto();
	}

	public MovementSaleDto idObject(String idObject) {
		this.setIdObject(idObject);
		return this;
	}

	public MovementSaleDto typeProcess(String typeProcess) {
		this.setTypeProcess(typeProcess);
		return this;
	}

	public MovementSaleDto description(String description) {
		this.setDescription(description);
		return this;
	}

	public MovementSaleDto object(Object object) {
		this.setObjects(object);
		return this;
	}

	public MovementSaleDto isError(Boolean isError) {
		this.setIsError(isError);
		return this;
	}

	public MovementSaleDto typeSale(String typeSale) {
		this.typeSale = typeSale;
		return this;
	}

	public MovementSaleDto build() {
		return this;
	}

}
