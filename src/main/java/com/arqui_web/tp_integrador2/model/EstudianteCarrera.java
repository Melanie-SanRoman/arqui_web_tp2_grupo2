package com.arqui_web.tp_integrador2.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EstudianteCarrera {
	@EmbeddedId
	private EstudianteCarreraId id;
	
	@Column(nullable = false)
	private LocalDate fecha_ingreso;
	@Column(nullable = false)
	private boolean graduado;

	public EstudianteCarrera() {
		super();
	}

	public EstudianteCarrera(LocalDate fecha_ingreso, boolean graduado) {
		super();
		this.fecha_ingreso = fecha_ingreso;
		this.graduado = graduado;
	}

	public LocalDate getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(LocalDate fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public boolean isGraduado() {
		return graduado;
	}

	public void setGraduado(boolean graduado) {
		this.graduado = graduado;
	}

	public EstudianteCarreraId getId() {
		return id;
	}
}
