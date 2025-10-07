package com.arqui_web.tp_integrador2.repositories.MySQL;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.EstudianteCarrera;
import com.arqui_web.tp_integrador2.repositories.EstudianteCarreraRepository;

public class EstudianteCarreraRepositoryImplMySQL implements EstudianteCarreraRepository {

	private EntityManager em;

	public EstudianteCarreraRepositoryImplMySQL(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void insert(EstudianteCarrera estudiante_carrera) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(estudiante_carrera);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public EstudianteCarrera findById(Integer id) {
		EstudianteCarrera ec = em.find(EstudianteCarrera.class, id);
		return ec;
	}

	@Override
	public List<EstudianteCarrera> findAll() {
		TypedQuery<EstudianteCarrera> query = em.createQuery("SELECT ec FROM Estudiante_Carrera ec",
				EstudianteCarrera.class);

		return query.getResultList();
	}

	@Override
	public void delete(EstudianteCarrera estudiante_carrera) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if (!em.contains(estudiante_carrera)) {
				estudiante_carrera = em.merge(estudiante_carrera);
			}
			em.remove(estudiante_carrera);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(EstudianteCarrera estudiante_carrera) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(estudiante_carrera);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void saveEgreso(Estudiante estudiante, Carrera carrera, LocalDate fecha_egreso) {
		TypedQuery<EstudianteCarrera> query = em.createQuery(
				"SELECT ec FROM EstudianteCarrera ec "
						+ "WHERE ec.carrera.id = :carreraId AND ec.estudiante.id = :estudianteId",
				EstudianteCarrera.class);
		query.setParameter("carreraId", carrera.getId());
		query.setParameter("estudianteId", estudiante.getId());

		EstudianteCarrera ec = query.getSingleResult();
		ec.setFecha_egreso(fecha_egreso);
		this.update(ec);
	}
}
