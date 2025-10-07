package com.arqui_web.tp_integrador2.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "estudiante_carrera")
public class EstudianteCarrera {
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
	@Column(nullable = true)
	private LocalDate fecha_egreso;

	public EstudianteCarrera() {
		super();
	}

	public EstudianteCarrera(LocalDate fecha_ingreso, LocalDate fecha_egreso) {
		super();
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = fecha_egreso;
	}

	public EstudianteCarrera(Estudiante estudiante, Carrera carrera, LocalDate fecha_ingreso) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_egreso = null;
		this.id = new EstudianteCarreraId(estudiante.getId(), carrera.getId());
	}

	public void setId(EstudianteCarreraId id) {
		this.id = id;
	}

	public LocalDate getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(LocalDate fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public LocalDate getFecha_egreso() {
		return fecha_egreso;
	}

	public void setFecha_egreso(LocalDate fecha_egreso) {
		this.fecha_egreso = fecha_egreso;
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
