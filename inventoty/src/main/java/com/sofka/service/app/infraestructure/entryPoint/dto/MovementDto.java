package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.util.Date;

public class MovementDto {

	private String idObject;

	private String typeProcess;

	private String description;

	private Date date = new Date();

	private Object object;

	private Boolean isError;

	public MovementDto() {
	}

	public String getIdObject() {
		return idObject;
	}

	public void setIdObject(String idObject) {
		this.idObject = idObject;
	}

	public String getTypeProcess() {
		return typeProcess;
	}

	public void setTypeProcess(String typeProcess) {
		this.typeProcess = typeProcess;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getObjects() {
		return object;
	}

	public void setObjects(Object objects) {
		this.object = objects;
	}

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public Date getDate() {
		return date;
	}

}
