package com.arqui_web.tp_integrador2.repositories;

import java.time.LocalDate;
import java.util.List;

import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.EstudianteCarrera;

public interface EstudianteCarreraRepository {
	void insert(EstudianteCarrera estudiante_carrera);

	EstudianteCarrera findById(Integer id);

	List<EstudianteCarrera> findAll();

	void delete(EstudianteCarrera estudiante_carrera);

	void update(EstudianteCarrera estudiante_carrera);

	void saveEgreso(Estudiante estudiante, Carrera carrera, LocalDate fecha_egreso);
}
