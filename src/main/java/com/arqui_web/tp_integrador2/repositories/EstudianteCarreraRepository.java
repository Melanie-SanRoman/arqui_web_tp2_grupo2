package com.arqui_web.tp_integrador2.repositories;

import java.util.List;

import com.arqui_web.tp_integrador2.model.Estudiante_Carrera;

public interface EstudianteCarreraRepository {
	void insert(Estudiante_Carrera estudiante_carrera);

	Estudiante_Carrera findById(int id);

	List<Estudiante_Carrera> findAll();

	void delete(Estudiante_Carrera estudiante_carrera);

	void update(Estudiante_Carrera estudiante_carrera);
}
