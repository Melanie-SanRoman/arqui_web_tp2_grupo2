# Proyecto anotaciones JPA y consultas JPQL con MySQL y Derby

---

## Descripción
Este proyecto implementa una aplicación Java para la **gestión de carreras universitarias, estudiantes e inscripciones**, utilizando el patrón Repository sobre **JPA**.
El sistema es compatible con **MySQL** y **Apache Derby**, permitiendo alternar entre motores de base de datos mediante una configuración centralizada.

---

## Características
- Gestión completa de **entidades académicas**:
  `Carrera`, `Estudiante`, `EstudianteCarrera`.
- Implementación del patrón **Repository** para el acceso a datos, desacoplando la lógica de persistencia.
- **Compatibilidad con múltiples bases de datos** mediante configuración flexible de `persistence.xml` mediante *ConnectionFactory* (patrón Singleton).
- Consultas y reportes avanzados, como:
  - Carreras con **mayor cantidad de inscriptos.**
  - Reporte de **inscriptos y graduados por año y carrera.**
  - Estudiantes por **carrera y ciudad especificos.**
- Creación de Repositories con **RepositoryFactory** (patrón Factory).  
- Arquitectura escalable, que separa responsabilidades entre **Repository**, **DTO**, **entidades** y **servicios**.

---

## Consultas principales

### EstudianteService
- `darDeAta(Estudiante e)` → Persiste el estudiante en la BD tenga o no carreras asociadas.
- `listarPorNombre()` → Retorna una lista con los estudiantes ordenados en orden alfabetico.
- `buscarPorNumLibreta(int num_libreta)` → Retorna un Estudiante por su numero de libreta.
- `listarPorGenero(TipoGenero genero)` → Retorna una lista con los estudiantes coincidentes con el genero pasado.
- `listarPorCarreraYCiudad(Carrera carrera, String ciudad)` → Retorna una lista de estudiantes que coincidad con la carrera y ciudad pasadas.

### CarreraService
- `listarPorCantInscriptos()` → Retorna una lista con las carreras con mayor cantidad de inscriptos.
- `getReporte()` →  Genera un reporte con la cantidad de inscriptos y egresados por carrera y año.
  
---

## Tecnologías utilizadas
- Java 21  
- JPA (Jakarta Persistence API)
- Hibernate 
- MySQL  
- Apache Derby  
- Maven  

---

## Patrones de diseño aplicados
- Repository → Aísla la lógica de acceso a datos.
- Factory → Crea Repositories según el tipo de base de datos.
- DTO (Data Transfer Object) → Transfiere datos agregados en los reportes.
- Singleton → Para el manejo centralizado de la conexión y configuración del EntityManager.
  
---

## Estructura del proyecto

```
src/ main/ java
 ┣ AppMySQL.java    → Punto de entrada MySQL
 ┣ AppDerby.java    → Punto de entrada Derby
 ┣ repositories     → Interfaces Repository y RepositoryFactory
 ┃ ┗ MySQL          → Implementaciones Repository MySQL
 ┃ ┗ Derby          → Implementaciones Repository Derby
 ┣ model            → Entidades
 ┣ service          → ConnectionFactory y servicios

src/ main/ resources
 ┣ META-INF
 ┃ ┗ persistence.xml 
```

---

## Configuración y ejecución
1. Clonar el repositorio:
   ```
   git clone https://github.com/Melanie-SanRoman/arqui_web_tp2_grupo2.git
   ```
   
2. Configurar la base de datos:
  - Crear la base de datos tp_integrador2 en MySQL (Derby se crea automaticamente).
  - Ajustar credenciales en persistence.xml si es necesario.

3.  Ejecutar:
    ```
    AppMySQL.java
    AppDerby.java
    ```

---

## Colaboradores
> * Langenheim, Geronimo V.
> * Lopez, Micaela N.
> * San Roman, Emanuel.
> * San Roman, Melanie.
