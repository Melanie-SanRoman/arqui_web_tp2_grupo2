package com.arqui_web.tp_integrador2.service;

import java.util.List;

import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.TipoGenero;
import com.arqui_web.tp_integrador2.repositories.EstudianteRepository;
import com.arqui_web.tp_integrador2.repositories.RepositoryFactory;

public class EstudianteService {

	private EstudianteRepository eRepo;

	public EstudianteService(String type) {
		eRepo = RepositoryFactory.getInstance().getEstudianteRepository(type);
	}
	
	public void darDeAlta(Estudiante e) {
	    Estudiante existente = eRepo.getEstudianteByNumLibreta(e.getNum_libreta());
	    if (existente != null) {
	        throw new IllegalArgumentException("El estudiante ya se encuentra registrado.");
	    }
	    eRepo.insert(e);
	}

	public List<Estudiante> listarPorNombre() {
		return eRepo.getEstudiantesOrderByApellido();
	}

	public Estudiante buscarPorNumLibreta(int num_libreta) {
		return eRepo.getEstudianteByNumLibreta(num_libreta);
	}

	public List<Estudiante> listarPorGenero(TipoGenero genero) {
		return eRepo.getEstudiantesGenero(genero);
	}

	public List<Estudiante> listarPorCarreraYCiudad(Carrera carrera, String ciudad) {

		List<Estudiante> estudiantes = eRepo.getEstudiantesByCarrera(carrera);

		return estudiantes.stream().filter(e -> e.getCiudad().equals(ciudad)).toList();
	}
}
