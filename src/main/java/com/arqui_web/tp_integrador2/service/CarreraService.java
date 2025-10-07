package com.arqui_web.tp_integrador2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arqui_web.tp_integrador2.dto.ReporteCarrerasDTO;
import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.EstudianteCarrera;
import com.arqui_web.tp_integrador2.model.EstudianteCarreraId;
import com.arqui_web.tp_integrador2.repositories.CarreraRepository;
import com.arqui_web.tp_integrador2.repositories.EstudianteCarreraRepository;
import com.arqui_web.tp_integrador2.repositories.RepositoryFactory;

public class CarreraService {

	private CarreraRepository cRepo;
	private EstudianteCarreraRepository ecRepo;

	public CarreraService(String type) {
		cRepo = RepositoryFactory.getInstance().getCarreraRepository(type);
		ecRepo = RepositoryFactory.getInstance().getEstudianteCarreraRepository(type);
	}

	public void darDeAlta(Carrera carrera) {
		cRepo.insert(carrera);
	}

	public void matricularEstudiante(Estudiante e, Carrera c, LocalDate fechaInscripcion) {

		if (cRepo.findById(c.getId()) != null) {
			if (!estaMatriculado(e, c)) {
				// crear y persistir instancia de tabla intermedia
				EstudianteCarrera ec = new EstudianteCarrera(e, c, fechaInscripcion);
				ec.setId(new EstudianteCarreraId(e.getId(), c.getId()));
				ecRepo.insert(ec);

				// mantener constancia en lista de entidades
				e.getCarreras().add(ec);
				c.getEstudiantes().add(ec);

				// actualizar carrera
				cRepo.update(c);
			} else {
				throw new IllegalArgumentException(
						"El alumno " + e.getName() + " " + e.getApellido() + " ya se encuentra matriculado.");
			}
		} else {
			throw new IllegalArgumentException("La carrera no existe.");
		}
	}

	private boolean estaMatriculado(Estudiante e, Carrera c) {

		return e.getCarreras().stream().anyMatch(carreraEst -> carreraEst.getId().getCarreraId() == c.getId());
	}

	public List<Carrera> listarPorCantInscriptos() {
		return cRepo.getCarrerasMayorInscripciones();
	}

	public Carrera recuperarCarreraPorId(Integer id) {
		return cRepo.findById(id);
	}

	// Unir consultas
	public List<ReporteCarrerasDTO> getReporte() {
		List<ReporteCarrerasDTO> inscriptos = cRepo.getInscriptosPorCarrera();
		List<ReporteCarrerasDTO> egresados = cRepo.getGraduadosPorCarrera();

		// Usamos un mapa para combinar los resultados por (carrera + año)
		Map<String, ReporteCarrerasDTO> reporteMap = new HashMap<>();

		// Agregar inscriptos
		for (ReporteCarrerasDTO r : inscriptos) {
			String clave = r.getNombreCarrera() + "_" + r.getAño();
			reporteMap.put(clave, r);
		}

		// Agregar egresados
		for (ReporteCarrerasDTO r : egresados) {
			String clave = r.getNombreCarrera() + "_" + r.getAño();

			if (reporteMap.containsKey(clave)) {
				// Si ya existe la carrera/año, sumamos los egresados
				ReporteCarrerasDTO existente = reporteMap.get(clave);
				existente.setEgresados(r.getEgresados());
			} else {
				// Si no existía, lo agregamos con inscriptos = 0
				reporteMap.put(clave, r);
			}
		}

		// Convertir el mapa a lista y la ordenamos
		List<ReporteCarrerasDTO> reporteFinal = new ArrayList<>(reporteMap.values());

		reporteFinal.sort(
				Comparator.comparing(ReporteCarrerasDTO::getNombreCarrera).thenComparing(ReporteCarrerasDTO::getAño));

		return reporteFinal;
	}

}
