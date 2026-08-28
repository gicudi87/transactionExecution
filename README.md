Servicio para realizar transacciones financieras, y realiza consulta de las transacciones.

# Transacciones financieras

Servicio de transacciones financieras construido con **Java 17**, **Spring WebFlux** y **PostgreSQL**, orientado a alto rendimiento.

## Tecnologías

- **Java 17**
- **Spring Boot 4.1** (WebFlux)
- **Gradle 9.0.0** (Groovy DSL)
- **PostgreSQL** (vía R2DBC para acceso reactivo)
- **JUnit 5 / Mockito / Reactor Test** para pruebas

## Requisitos previos

- JDK 17 
- Gradle 9.0.0 
- Docker (para levantar PostgreSQL en local)
- PostgreSQL 14+ (si no se usa contenedor)

## Configuración

### `application.yml` (ejemplo)

```yaml
spring:
  r2dbc:
    url: r2dbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:transactions_db}
    username: ${DB_USER:postgres}
    password: ${DB_PASSWORD:postgres}

server:
  port: ${SERVER_PORT:8080}
```

## Levantar PostgreSQL con Docker

```bash
docker run --name transactions-postgres \
  -e POSTGRES_DB=transactions_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:16
```

## Ejecución del proyecto

```bash
# Clonar el repositorio
git clone https://github.com/gicudi87/transactionExecution.git
cd transactionExecution

# Ejecutar migraciones y levantar la aplicación
./gradlew bootRun
```

La aplicación quedaría disponible en `http://localhost:8080`.

## Tests

El proyecto incluye pruebas unitarias y de integración.

```bash
# Ejecutar todos los tests
./gradlew test

```

Los reportes de tests se generan en:

```
build/reports/tests/test/index.html
```

## Build

```bash
# Compilar y generar el jar
./gradlew build

# Ejecutar el jar generado
java -jar build/libs/transactionExecution-0.0.1-SNAPSHOT.jar
```

## Estructura del proyecto

```
transactionExecution/
|- src/
|	|- main/
|	|	|-java/
|	|	|	|-com/transaction/spin
|	|	|	|	|-controller/ 			# Endpoints (WebFlux)
|	|	|	|	|-dtos/					# Objetos de datos
|	|	|	|	|-entity/				# Entidades
|	|	|	|	|-repository/			# Repositorios R2DBC
|	|	|	|	|-service/				# Logica del negocio
|	|	|	|	|-utils/				# Utilidades y mapeos
|	|	|-resources/
|	|-test/
|	|	|-java/
|	|	|	|-com/transaction/test/
|	|	|	|	|-dtos					# Testeo de objetos de datos
|	|	|	|	|-service				# Testeo de logica del negocio
|-build.gradle
|-settings.gradle
|-README.md

```

## Endpoints

| Método | Endpoint                  | Descripción                              |
|--------|----------------------------|-----------------------------------------|
| POST   | '/api/transacciones'       | Transacciona con el provedor y almacena |
| GET    | '/api/transacciones'       | Obtiene todas las transacciones         |

## Diseño de proyecto
	Java:
		-Se uso java por su estabilidad, su capacidad de ejecutarce en cualquier sistema operativo y su presencia
		 en las empresas.
	Spring WebFlux:
		-Se realizo la construccion del proyecto con Spring webFlux para soportar mayor cantidad de peticiones
		 ya que a diferencia de Spring MVC que maneja un modo bloqueante de un hilo, el webFlux tiene la capacidad 
		 de manejar multiples peticiones ya que maneja mas hilos.
	PostgreSQL:
		-Es una base de datos relacional potente, confiable y flexible, usado mucho por bancos por capacidad
		 de concurrencia.
    Docker:
		-Se uso docker para la creacion de la DB de postgresql, por su facilidad y utilidad de contenedores.
	
## Uso de IA
	Se uso la IA para resolver dudas y debugging.
