package com.arqui_web.tp_integrador2.repositories.Derby;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.repositories.CarreraRepository;

public class CarreraRepositoryImplDerby implements CarreraRepository {

	private EntityManager em;

	public CarreraRepositoryImplDerby(EntityManager em) {
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
}
