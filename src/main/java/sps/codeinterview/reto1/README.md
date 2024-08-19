RETO 1. CRUD REPOSITORY Y EXCEPCIONES

>Sección 1: CRUD Repository

  Topic: Implementa un servicio RESTful en Spring Boot.

    Descripción: Crea un servicio RESTful que maneje operaciones CRUD para una entidad Product con atributos id, name, label, price y createdAt.
			Requisitos:
				Define la entidad Product.
				Crea un repositorio JPA para Product.
				Implementa un controlador REST con endpoints para crear, consultar, actualizar y eliminar productos.
  
  Topic: Consulta con filtros.
		
    Descripción: Mejora el servicio RESTful anterior para la búsqueda por name.
			Requisitos:
				Añade un parámetro de consulta para obtener todos los Products por orden descendente en el mismo endpoint.

>Sección 2: Validaciones

  Topic: Crea validaciones y manejo de excepciones para los métodos y campos de la aplicación
		
    Descripción: Utiliza anotaciones para realizar validaciones de tipo y datos no nulos en cada campo.
				Genera validaciones y manejo de excepciones para cada método y campo de la aplicación