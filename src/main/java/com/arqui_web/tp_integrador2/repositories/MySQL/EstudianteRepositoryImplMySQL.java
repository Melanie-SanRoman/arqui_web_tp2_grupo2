package com.arqui_web.tp_integrador2.repositories.MySQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.repositories.EstudianteRepository;

public class EstudianteRepositoryImplMySQL implements EstudianteRepository {

	private EntityManager em;

	public EstudianteRepositoryImplMySQL(EntityManager em) {
		this.em = em;
	}

	@Override
	public void insert(Estudiante estudiante) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(estudiante);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}

	}

	@Override
	public Estudiante findById(int id) {
		Estudiante e = em.find(Estudiante.class, id);
		return e;
	}

	@Override
	public List<Estudiante> findAll() {
		TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);

		return query.getResultList();
	}

	@Override
	public void delete(Estudiante estudiante) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if (!em.contains(estudiante)) {
				estudiante = em.merge(estudiante);
			}
			em.remove(estudiante);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(Estudiante estudiante) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(estudiante);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
	}

}
