package com.arqui_web.tp_integrador2.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Estudiante_Carrera {
	@EmbeddedId
	private EstudianteCarreraId id;

	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("estudianteId")
	@JoinColumn(name = "estudianteId")
	private Estudiante estudiante;

	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("carreraId")
	@JoinColumn(name = "carreraId")
	private Carrera carrera;

	@Column(nullable = false)
	private LocalDate fecha_ingreso;
	@Column(nullable = false)
	private boolean graduado;

	public Estudiante_Carrera() {
		super();
	}

	public Estudiante_Carrera(LocalDate fecha_ingreso, boolean graduado) {
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

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
}
