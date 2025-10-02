package com.arqui_web.tp_integrador2.repositories.MySQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.arqui_web.tp_integrador2.model.Estudiante_Carrera;
import com.arqui_web.tp_integrador2.repositories.EstudianteCarreraRepository;

public class EstudianteCarreraRepositoryImplMySQL implements EstudianteCarreraRepository {

	private EntityManager em;

	public EstudianteCarreraRepositoryImplMySQL(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void insert(Estudiante_Carrera estudiante_carrera) {
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
	public Estudiante_Carrera findById(int id) {
		Estudiante_Carrera ec = em.find(Estudiante_Carrera.class, id);
		return ec;
	}   

	@Override
	public List<Estudiante_Carrera> findAll() {
		TypedQuery<Estudiante_Carrera> query = em.createQuery("SELECT ec FROM Estudiante_Carrera ec",
				Estudiante_Carrera.class);

		return query.getResultList();
	}

	@Override
	public void delete(Estudiante_Carrera estudiante_carrera) {
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
	public void update(Estudiante_Carrera estudiante_carrera) {
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

}
