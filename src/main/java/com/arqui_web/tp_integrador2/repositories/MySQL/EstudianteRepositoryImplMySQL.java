package com.arqui_web.tp_integrador2.repositories.MySQL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.arqui_web.tp_integrador2.model.Carrera;
import com.arqui_web.tp_integrador2.model.Estudiante;
import com.arqui_web.tp_integrador2.model.TipoGenero;
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
	public Estudiante findById(Integer id) {
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

	@Override
	public Estudiante findByNumLibreta(int numLibreta) {
		TypedQuery<Estudiante> query = em
				.createQuery("SELECT e FROM Estudiante e WHERE e.num_libreta = :libreta", Estudiante.class)
				.setParameter("libreta", numLibreta);

		return query.getSingleResult();
	}

	@Override
	public List<Estudiante> getEstudiantesOrderByApellido() {
		TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e ORDER BY e.apellido ASC",
				Estudiante.class);

		return query.getResultList();
	}

	@Override
	public Estudiante getEstudianteByNumLibreta(int num_libreta) {
		try {
			TypedQuery<Estudiante> query = em
					.createQuery("SELECT e FROM Estudiante e WHERE e.num_libreta = :numLibreta", Estudiante.class);
			query.setParameter("numLibreta", num_libreta);
			return query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public List<Estudiante> getEstudiantesGenero(TipoGenero genero) {
		TypedQuery<Estudiante> query = em
				.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :tipoGenero", Estudiante.class)
				.setParameter("tipoGenero", genero);

		return query.getResultList();
	}

	@Override
	public List<Estudiante> getEstudiantesByCarrera(Carrera carrera) {
		TypedQuery<Estudiante> query = em.createQuery(
				"SELECT e FROM Estudiante e JOIN Estudiante_Carrera ec ON e.estudianteId = ec.estudianteId WHERE ec.carreraId = :carreraId",
				Estudiante.class).setParameter("carreraId", carrera.getId());

		return query.getResultList();
	}
}
