package com.arqui_web.tp_integrador2.model;

import java.time.LocalDate;
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
public class Estudiante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "estudianteId")
	private int id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String apellido;
	@Column(nullable = false)
	private LocalDate fecha_nacimiento;
	@Column(nullable = false)
	private TipoGenero genero;
	@Column(nullable = false)
	private int dni;
	@Column(nullable = false)
	private String ciudad;
	@Column(nullable = false)
	private int num_libreta;

	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Estudiante_Carrera> carreras = new ArrayList<>();

	public Estudiante() {
		super();
	}

	public Estudiante(String name, String apellido, LocalDate fecha_nacimiento, TipoGenero genero, int dni,
			String ciudad, int num_libreta) {
		super();
		this.name = name;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.num_libreta = num_libreta;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public TipoGenero getGenero() {
		return genero;
	}

	public void setGenero(TipoGenero genero) {
		this.genero = genero;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNum_libreta() {
		return num_libreta;
	}

	public void setNum_libreta(int num_libreta) {
		this.num_libreta = num_libreta;
	}

	public List<Estudiante_Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Estudiante_Carrera> carreras) {
		this.carreras = carreras;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", name=" + name + ", apellido=" + apellido + ", fecha_nacimiento="
				+ fecha_nacimiento + ", genero=" + genero + ", dni=" + dni + ", ciudadString=" + ciudad
				+ ", num_libreta=" + num_libreta + ", carreras=" + carreras + "]";
	}
}
