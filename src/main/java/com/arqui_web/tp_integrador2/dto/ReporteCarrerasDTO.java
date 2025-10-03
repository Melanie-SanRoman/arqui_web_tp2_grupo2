
package com.arqui_web.tp_integrador2.dto;

import java.time.Year;

public class ReporteCarrerasDTO {
	private String nombreCarrera;
	private int incriptos;
	private int egresados;
	private Year año;

	public ReporteCarrerasDTO(String nombreCarrera, int incriptos, int egresados, Year año) {
		super();
		this.nombreCarrera = nombreCarrera;
		this.incriptos = incriptos;
		this.egresados = egresados;
		this.año = año;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public int getIncriptos() {
		return incriptos;
	}

	public void setIncriptos(int incriptos) {
		this.incriptos = incriptos;
	}

	public int getEgresados() {
		return egresados;
	}

	public void setEgresados(int egresados) {
		this.egresados = egresados;
	}

	public Year getAño() {
		return año;
	}

	public void setAño(Year año) {
		this.año = año;
	}
}
