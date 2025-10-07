
package com.arqui_web.tp_integrador2.dto;

public class ReporteCarrerasDTO {
	private String nombreCarrera;
	private long inscriptos;
	private long egresados;
	private int año;

	public ReporteCarrerasDTO(String nombreCarrera, long inscriptos, long egresados, int año) {
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

	public long getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(long inscriptos) {
		this.inscriptos = inscriptos;
	}

	public long getEgresados() {
		return egresados;
	}

	public void setEgresados(long egresados) {
		this.egresados = egresados;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}
}
