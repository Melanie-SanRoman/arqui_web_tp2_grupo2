package com.arqui_web.tp_integrador2.repositories;

import com.arqui_web.tp_integrador2.service.ConnectionFactory;

public class RepositoryFactory {

	private static RepositoryFactory instance = new RepositoryFactory();

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

	public static EstudianteRepository getRepository(String type) {
		switch (type) {
		case ConnectionFactory.MYSQL:
			return new EstudianteRepositoryMySQL();
		case ConnectionFactory.DERBY:
			return new EstudianteRepositoryPostgres();
		default:
			throw new IllegalArgumentException("Tipo de BD no soportado: " + type);
		}
	}
}
