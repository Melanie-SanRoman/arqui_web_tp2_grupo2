package com.arqui_web.tp_integrador2.repositories;

import java.util.List;

import com.arqui_web.tp_integrador2.model.Estudiante;

public interface EstudianteRepository {
	void insert(Estudiante estudiante);

	Estudiante findById(int id);

	List<Estudiante> findAll();

	void delete(Estudiante estudiante);

	void update(Estudiante estudiante);
}
