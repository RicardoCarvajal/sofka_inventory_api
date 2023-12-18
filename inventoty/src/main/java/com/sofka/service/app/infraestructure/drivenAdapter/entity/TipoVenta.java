package com.sofka.service.app.infraestructure.drivenAdapter.entity;

public enum TipoVenta {

	MAYOR, DETAL;

	public String value() {
		String tipo = "";

		switch (this) {
		case MAYOR:
			tipo = "MAYOR";
			break;
		case DETAL:
			tipo = "DETAL";
			break;
		}

		return tipo;
	}

}
