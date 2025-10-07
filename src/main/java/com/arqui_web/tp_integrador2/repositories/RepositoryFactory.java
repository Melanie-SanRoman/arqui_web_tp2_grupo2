package com.arqui_web.tp_integrador2.repositories;

import javax.persistence.EntityManager;

import com.arqui_web.tp_integrador2.repositories.Derby.CarreraRepositoryImplDerby;
import com.arqui_web.tp_integrador2.repositories.Derby.EstudianteCarreraRepositoryImplDerby;
import com.arqui_web.tp_integrador2.repositories.Derby.EstudianteRepositoryImplDerby;
import com.arqui_web.tp_integrador2.repositories.MySQL.CarreraRepositoryImplMySQL;
import com.arqui_web.tp_integrador2.repositories.MySQL.EstudianteCarreraRepositoryImplMySQL;
import com.arqui_web.tp_integrador2.repositories.MySQL.EstudianteRepositoryImplMySQL;
import com.arqui_web.tp_integrador2.service.ConnectionFactory;

public class RepositoryFactory {

	private static RepositoryFactory instance = new RepositoryFactory();
	private EntityManager em;

	private RepositoryFactory() {
	};

	/**
	 * Devuelve la única instancia de RepositoryFactory (patrón Singleton).
	 *
	 * @return la instancia única de RepositoryFactory
	 */
	public static RepositoryFactory getInstance() {
		return instance;
	}

	public EstudianteRepository getEstudianteRepository(String type) {
		switch (type) {
		case ConnectionFactory.MYSQL:
			em = ConnectionFactory.getInstance().connection();
			return new EstudianteRepositoryImplMySQL(em);
		case ConnectionFactory.DERBY:
			em = ConnectionFactory.getInstance().connection();
			return new EstudianteRepositoryImplDerby(em);
		default:
			throw new IllegalArgumentException("Tipo de BD no soportado: " + type);
		}
	}

	public CarreraRepository getCarreraRepository(String type) {
		switch (type) {
		case ConnectionFactory.MYSQL:
			em = ConnectionFactory.getInstance().connection();
			return new CarreraRepositoryImplMySQL(em);
		case ConnectionFactory.DERBY:
			em = ConnectionFactory.getInstance().connection();
			return new CarreraRepositoryImplDerby(em);
		default:
			throw new IllegalArgumentException("Tipo de BD no soportado: " + type);
		}
	}

	public EstudianteCarreraRepository getEstudianteCarreraRepository(String type) {
		switch (type) {
		case ConnectionFactory.MYSQL:
			em = ConnectionFactory.getInstance().connection();
			return new EstudianteCarreraRepositoryImplMySQL(em);
		case ConnectionFactory.DERBY:
			em = ConnectionFactory.getInstance().connection();
			return new EstudianteCarreraRepositoryImplDerby(em);
		default:
			throw new IllegalArgumentException("Tipo de BD no soportado: " + type);
		}
	}
}
