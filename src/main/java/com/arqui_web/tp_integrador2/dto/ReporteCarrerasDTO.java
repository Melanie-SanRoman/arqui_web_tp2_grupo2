
package com.arqui_web.tp_integrador2.dto;

import java.time.Year;
import java.util.List;

import com.arqui_web.tp_integrador2.model.Estudiante;

public class ReporteCarrerasDTO {
	private String nombreCarrera;
	private List<Estudiante> incriptos;
	private List<Estudiante> egresados;
	private Year año;

	public ReporteCarrerasDTO(String nombreCarrera, List<Estudiante> incriptos, List<Estudiante> egresados, Year año) {
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

	public List<Estudiante> getIncriptos() {
		return incriptos;
	}

	public void setIncriptos(List<Estudiante> incriptos) {
		this.incriptos = incriptos;
	}

	public List<Estudiante> getEgresados() {
		return egresados;
	}

	public void setEgresados(List<Estudiante> egresados) {
		this.egresados = egresados;
	}

	public Year getAño() {
		return año;
	}

	public void setAño(Year año) {
		this.año = año;
	}
}
