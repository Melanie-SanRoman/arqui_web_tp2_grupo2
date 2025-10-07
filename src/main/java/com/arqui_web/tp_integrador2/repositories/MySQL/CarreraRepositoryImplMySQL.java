package com.arqui_web.tp_integrador2.repositories.MySQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.arqui_web.tp_integrador2.dto.ReporteCarrerasDTO;
import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.repositories.CarreraRepository;

public class CarreraRepositoryImplMySQL implements CarreraRepository {

	private EntityManager em;

	public CarreraRepositoryImplMySQL(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void insert(Carrera carrera) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(carrera);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Carrera findById(Integer id) {
		Carrera c = em.find(Carrera.class, id);
		return c;
	}

	@Override
	public List<Carrera> findAll() {
		TypedQuery<Carrera> query = em.createQuery("SELECT c FROM Carrera c", Carrera.class);

		return query.getResultList();
	}

	@Override
	public void delete(Carrera carrera) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if (!em.contains(carrera)) {
				carrera = em.merge(carrera);
			}
			em.remove(carrera);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(Carrera carrera) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(carrera);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<Carrera> getCarrerasMayorInscripciones() {
		TypedQuery<Carrera> query = em.createQuery(
				"SELECT c FROM Carrera c JOIN c.estudiantes ec GROUP BY c ORDER BY COUNT(ec) DESC", Carrera.class);

		return query.getResultList();
	}

	// Consulta 1 - Inscriptos
	@Override
	public List<ReporteCarrerasDTO> getInscriptosPorCarrera() {
		TypedQuery<ReporteCarrerasDTO> query = em
				.createQuery("SELECT new com.arqui_web.tp_integrador2.dto.ReporteCarrerasDTO("
						+ "c.nombre, COUNT(ec), 0L, FUNCTION('YEAR', ec.fecha_ingreso)) " + "FROM EstudianteCarrera ec "
						+ "JOIN ec.carrera c " + "WHERE ec.fecha_ingreso IS NOT NULL "
						+ "GROUP BY c.nombre, FUNCTION('YEAR', ec.fecha_ingreso)", ReporteCarrerasDTO.class);

		return query.getResultList();
	}

	// Consulta 2 - Graduados

	@Override
	public List<ReporteCarrerasDTO> getGraduadosPorCarrera() {
		TypedQuery<ReporteCarrerasDTO> query = em
				.createQuery("SELECT new com.arqui_web.tp_integrador2.dto.ReporteCarrerasDTO("
						+ "c.nombre, 0L, COUNT(ec), FUNCTION('YEAR', ec.fecha_egreso)) " + "FROM EstudianteCarrera ec "
						+ "JOIN ec.carrera c " + "WHERE ec.fecha_egreso IS NOT NULL "
						+ "GROUP BY c.nombre, FUNCTION('YEAR', ec.fecha_egreso)", ReporteCarrerasDTO.class);

		return query.getResultList();
	}
}
