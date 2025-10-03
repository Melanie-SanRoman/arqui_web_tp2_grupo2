package com.arqui_web.tp_integrador2.repositories;

import java.util.List;

import com.arqui_web.tp_integrador2.dto.ReporteCarrerasDTO;
import com.arqui_web.tp_integrador2.model.Carrera;

public interface CarreraRepository {
	void insert(Carrera carrera);

	Carrera findById(Integer id); 

	List<Carrera> findAll();

	void delete(Carrera carrera);

	void update(Carrera carrera);

	List<Carrera> getCarrerasMayorInscripciones();
	
	List<ReporteCarrerasDTO> getReporteCarreras();
}
