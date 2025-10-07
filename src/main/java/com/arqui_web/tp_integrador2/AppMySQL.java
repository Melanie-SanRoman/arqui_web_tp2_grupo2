package com.arqui_web.tp_integrador2;

import java.time.LocalDate;
import java.util.List;

import com.arqui_web.tp_integrador2.dto.ReporteCarrerasDTO;
import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.TipoGenero;
import com.arqui_web.tp_integrador2.service.CarreraService;
import com.arqui_web.tp_integrador2.service.ConnectionFactory;
import com.arqui_web.tp_integrador2.service.EstudianteService;

public class AppMySQL {
	public static void main(String[] args) {
		// Crear conexion a MySQL
		ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);

		// Instanciar servicios
		EstudianteService estudianteService = new EstudianteService(ConnectionFactory.MYSQL);
		CarreraService carreraService = new CarreraService(ConnectionFactory.MYSQL);

		// =========================
		// 1) CREAR ESTUDIANTES
		// =========================
		Estudiante juan = new Estudiante("Juan", "Pérez", LocalDate.of(2000, 5, 14), TipoGenero.MASCULINO, 40123456,
				"Tandil", 1001);
		Estudiante maria = new Estudiante("María", "García", LocalDate.of(1999, 9, 20), TipoGenero.FEMENINO, 39222333,
				"Olavarría", 1002);
		Estudiante ana = new Estudiante("Ana", "López", LocalDate.of(2001, 12, 1), TipoGenero.FEMENINO, 41111222,
				"Tandil", 1003);

		estudianteService.darDeAlta(juan);
		estudianteService.darDeAlta(maria);
		estudianteService.darDeAlta(ana);

		// =========================
		// 2) CREAR CARRERAS
		// =========================
		Carrera sistemas = new Carrera("Ingeniería en Sistemas");
		Carrera medicina = new Carrera("Medicina");
		Carrera derecho = new Carrera("Derecho");

		carreraService.darDeAlta(sistemas);
		carreraService.darDeAlta(medicina);
		carreraService.darDeAlta(derecho);

		// =========================
		// 3) MATRICULAR ESTUDIANTES
		// =========================
		carreraService.matricularEstudiante(estudianteService.buscarPorNumLibreta(1001),
				carreraService.recuperarCarreraPorId(1), LocalDate.of(2019, 3, 1));

		carreraService.matricularEstudiante(estudianteService.buscarPorNumLibreta(1002),
				carreraService.recuperarCarreraPorId(2), LocalDate.of(2018, 3, 1));
		carreraService.matricularEstudiante(estudianteService.buscarPorNumLibreta(1003),
				carreraService.recuperarCarreraPorId(3), LocalDate.of(2020, 3, 1));

		// Juan además se anota en medicina
		carreraService.matricularEstudiante(estudianteService.buscarPorNumLibreta(1001),
				carreraService.recuperarCarreraPorId(2), LocalDate.of(2020, 3, 1));
		
		// Maria se graduo en 2023
		estudianteService.registrarEgreso(maria, medicina, LocalDate.of(2022, 7, 14));

		// =========================
		// 4) CONSULTAS
		// =========================

		// a) Listar estudiantes por apellido
		System.out.println("\n=== Estudiantes por apellido ===");
		estudianteService.listarPorNombre().forEach(e -> System.out.println(e.getApellido() + ", " + e.getName()));

		// b) Buscar estudiante por libreta
		System.out.println("\n=== Buscar por número de libreta 1002 ===");
		Estudiante buscado = estudianteService.buscarPorNumLibreta(1002);
		System.out.println("Encontrado: " + buscado.getName() + " " + buscado.getApellido());

		// c) Listar estudiantes por género
		System.out.println("\n=== Estudiantes femeninas ===");
		estudianteService.listarPorGenero(TipoGenero.FEMENINO).forEach(e -> System.out.println(e.getName()));

		// d) Estudiantes en carrera y ciudad
		System.out.println("\n=== Estudiantes en Sistemas de Tandil ===");
		estudianteService.listarPorCarreraYCiudad(carreraService.recuperarCarreraPorId(1), "Tandil")
				.forEach(e -> System.out.println(e.getName()));

		// e) Carreras con más inscriptos
		System.out.println("\n=== Carreras ordenadas por inscripciones ===");
		carreraService.listarPorCantInscriptos()
				.forEach(c -> System.out.println(c.getNombre() + " (Inscriptos: " + c.getEstudiantes().size() + ")"));

		// Reporte de carreras:
		List<ReporteCarrerasDTO> reporte = carreraService.getReporte();

		System.out.println("Reporte de carreras (inscriptos y egresados por año):");
		for (ReporteCarrerasDTO r : reporte) {
			System.out.printf("%-25s | Año: %-5d | Inscriptos: %-4d | Egresados: %-4d%n", r.getNombreCarrera(),
					r.getAño(), r.getInscriptos(), r.getEgresados());
		}
	}
}
