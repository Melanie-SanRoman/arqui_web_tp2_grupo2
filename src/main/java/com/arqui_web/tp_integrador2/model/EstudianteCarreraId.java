package com.arqui_web.tp_integrador2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EstudianteCarreraId implements Serializable {
	@Column
	private int estudiante_id;
	@Column
	private int carrera_id;
	
	public int getEstudiante_id() {
		return estudiante_id;
	}
	public void setEstudiante_id(int estudiante_id) {
		this.estudiante_id = estudiante_id;
	}
	public int getCarrera_id() {
		return carrera_id;
	}
	public void setCarrera_id(int carrera_id) {
		this.carrera_id = carrera_id;
	}
}
