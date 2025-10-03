package com.arqui_web.tp_integrador2.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EstudianteCarreraId implements Serializable {
	@Column
	private Integer estudianteId;
	@Column
	private Integer carreraId;
	
	public EstudianteCarreraId() {
		super();
	}

	public EstudianteCarreraId(Integer estudianteId, Integer carreraId) {
		super();
		this.estudianteId = estudianteId;
		this.carreraId = carreraId;
	}

	public Integer getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(Integer estudianteId) {
		this.estudianteId = estudianteId;
	}

	public Integer getCarreraId() {
		return carreraId;
	}

	public void setCarreraId(Integer carreraId) {
		this.carreraId = carreraId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof EstudianteCarreraId))
			return false;
		EstudianteCarreraId that = (EstudianteCarreraId) o;
		return Objects.equals(estudianteId, that.estudianteId) && Objects.equals(carreraId, that.carreraId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(estudianteId, carreraId);
	}
}
