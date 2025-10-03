package com.arqui_web.tp_integrador2.repositories;

import java.util.List;

import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.TipoGenero;

public interface EstudianteRepository {
	void insert(Estudiante estudiante);

	Estudiante findById(Integer id);

	List<Estudiante> findAll();

	void delete(Estudiante estudiante);

	void update(Estudiante estudiante);
	
	Estudiante findByNumLibreta(int numLibreta);

	List<Estudiante> getEstudiantesOrderByApellido();

	Estudiante getEstudianteByNumLibreta(int num_libreta);

	List<Estudiante> getEstudiantesGenero(TipoGenero genero);

	List<Estudiante> getEstudiantesByCarrera(Carrera carrera);
}
