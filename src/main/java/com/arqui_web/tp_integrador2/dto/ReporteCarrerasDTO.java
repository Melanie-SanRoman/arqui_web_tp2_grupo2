
package com.arqui_web.tp_integrador2.dto;

import java.time.Year;

public class ReporteCarrerasDTO {
	private String nombreCarrera;
	private int inscriptos;
	private int egresados;
	private Year año;

	public ReporteCarrerasDTO(String nombreCarrera, int inscriptos, int egresados, Year año) {
		super();
		this.nombreCarrera = nombreCarrera;
		this.inscriptos = inscriptos;
		this.egresados = egresados;
		this.año = año;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public int getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(int inscriptos) {
		this.inscriptos = inscriptos;
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
