# Car Management System

Este proyecto es una aplicación CRUD de gestión de automóviles desarrollada con Spring Boot y utiliza una base de datos en memoria (H2). Permite realizar operaciones básicas de crear, leer, actualizar y eliminar automóviles.

## Requisitos previos

Asegúrate de tener instalados los siguientes requisitos previos:

- Java Development Kit (JDK) 8 o superior
- Maven

## Pasos para ejecutar el proyecto

1. Clona este repositorio en tu máquina local o descárgalo como archivo ZIP.

2. Abre una terminal y navega hasta el directorio raíz del proyecto.

3. Ejecuta el siguiente comando para compilar y empaquetar el proyecto:

   ```shell
   mvn package
Esto generará un archivo JAR en el directorio target.

4. Ejecuta la aplicación Spring Boot con el siguiente comando:
   ```shell
   java -jar target/nombre-del-archivo.jar
   
Reemplaza `nombre-del-archivo.jar` con el nombre real del archivo JAR generado.

5. Accede a la consola de H2, ve a `http://localhost:8080/h2-console`. 
- Los detalles de conexión a la base de datos se encuentran en el archivo `application.properties`.
- Una vez en la pantalla de H2, en el campo JDBC URL asegurarse de que el valor es: `jdbc:h2:mem:testdb`. (No me conectaba porque generaba un valor aleatorio hasta que le puse eso).
- En principio no habría que tocar nada más y solo darle a Connect.
- En esta pantalla podremos cerciorar mediante consultas sql si la base de datos cambia cuando ejecutamos desde Postman.
- Ve a Postman y prueba los endpoints. La información está en el siguiente paso y también dejo la colección exportada en la raíz del proyecto. El archivo se llama `Spring boot cars crud.postman_collection.json`



## Endpoints disponibles
- GET `/cars`: Obtiene la lista de todos los automóviles.
- GET `/cars/{id}`: Obtiene un automóvil por su ID.
- POST `/cars`: Crea un nuevo automóvil.
- PUT `/cars/{id}`: Actualiza un automóvil existente por su ID.
- DELETE `/cars/{id}`: Elimina un automóvil por su ID.

## Información sobre el desarrollo

1. Hice uso de https://start.spring.io/ para generar el esqueleto de mi aplicación añadiendo las siguientes dependencias:
- **Spring Web** 
- **Spring Data JPA**
- **H2 Database**
- **JDBC API:**

2. Partiendo de este esqueleto, abro mi **IDE IntelliJ** y creo el archivo `application.properties` Donde va la información de:
- Configuración de la **BBDD** (Tuve que añadir esto `NON_KEYWORDS=YEAR` porque resulta que en H2, *year* es una palabra reservada)
- Configuración de la consola **H2** (Sin esto no podemos acceder a la BBDD por la URL)
- Configuración para **JPA**
- Puerto por defecto para nuestra aplicación **8080**

3. Creo el modelo **`Car`**:
 - Defino la tabla que se llamara `cars`
 - Defino 4 campos básicos e indico que el ID será de incremento automático y que sea la BBDD la que se encargue de gestionarlo.
 - Defino 2 constructores, uno con todos los campos y otro vacío.
 - Defino los setter y getters.

4. Creo la interfaz **`CarRepository`**, le indico que extienda de `JpaRepository` para poder hacer uso de JPA en el controller.

5. Creo el Controlador **`CarController`**:
- Uso las anotaciones `@RestController` para indicarle que será un servicio Restful y en `@RequestMapping` defino que la raíz será `/cars` 
- Mediante `@Autowired` inyecto las dependencias para que use JPA
- Hago uso de las anotaciones `@GetMapping`, `@PostMapping`, `@PutMapping` y `@DeleteMapping`, para poder gestionar las distintas peticiones al servidor.
- En cada una de estas peticiones hago uso de JPA para realizar las consultas a la Base de datos
