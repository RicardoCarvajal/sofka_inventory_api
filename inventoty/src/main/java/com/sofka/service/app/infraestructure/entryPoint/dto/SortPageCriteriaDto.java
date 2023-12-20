package com.sofka.service.app.infraestructure.entryPoint.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SortPageCriteriaDto {

	@NotNull
	@Min(0)
	private Integer page;

	@NotNull
	@Min(1)
	private Integer elements;

	public SortPageCriteriaDto() {
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getElements() {
		return elements;
	}

	public void setElements(Integer elements) {
		this.elements = elements;
	}

}
