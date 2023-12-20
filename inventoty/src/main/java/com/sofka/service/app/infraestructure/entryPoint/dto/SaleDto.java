package com.sofka.service.app.infraestructure.entryPoint.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SaleDto {

	@NotNull
	@Min(1)
	private BigDecimal total;

	@Valid
	@NotNull
	private List<SaleDetailTotalDto> Datail;

	@NotNull
	@NotEmpty
	private String tipo;

	public SaleDto() {
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<SaleDetailTotalDto> getDatail() {
		return Datail;
	}

	public void setDatail(List<SaleDetailTotalDto> datail) {
		Datail = datail;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static SaleDto building() {
		return new SaleDto();
	}

	public SaleDto total(BigDecimal total) {
		this.total = total;
		return this;
	}

	public SaleDto datail(List<SaleDetailTotalDto> datail) {
		Datail = datail;
		return this;
	}

	public SaleDto tipo(String tipo) {
		this.tipo = tipo;
		return this;
	}

	public SaleDto build() {
		return this;
	}

}
