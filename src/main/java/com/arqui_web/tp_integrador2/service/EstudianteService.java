package com.arqui_web.tp_integrador2.service;

import java.time.LocalDate;
import java.util.List;

import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.TipoGenero;
import com.arqui_web.tp_integrador2.repositories.EstudianteCarreraRepository;
import com.arqui_web.tp_integrador2.repositories.EstudianteRepository;
import com.arqui_web.tp_integrador2.repositories.RepositoryFactory;

public class EstudianteService {

	private EstudianteRepository eRepo;
	private EstudianteCarreraRepository ecRepo;

	public EstudianteService(String type) {
		eRepo = RepositoryFactory.getInstance().getEstudianteRepository(type);
		ecRepo = RepositoryFactory.getInstance().getEstudianteCarreraRepository(type);
	}

	public void darDeAlta(Estudiante e) {
		if (e.getId() == null) {
			eRepo.insert(e);
		} else {
			throw new IllegalArgumentException("El estudiante ya se encuentra registrado.");
		}
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

	public void registrarEgreso(Estudiante e, Carrera c, LocalDate fecha_egreso) {
		ecRepo.saveEgreso(e, c, fecha_egreso);
	}
}
