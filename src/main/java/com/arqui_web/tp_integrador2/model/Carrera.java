package com.arqui_web.tp_integrador2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carreraId")
	private Integer id;
	@Column(nullable = false)
	private String nombre;

	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Estudiante_Carrera> estudiantes = new ArrayList<>();

	public Carrera() {
		super();
	}

	public Carrera(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + "]";
	}

	public List<Estudiante_Carrera> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante_Carrera> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	
}
