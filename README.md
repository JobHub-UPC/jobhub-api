# JobHub
## Introducción
**JobHub** es una aplicación web que busca brindar un espacio tanto para profesionales y empleadores, para mejorar relaciones profesionales en los distintos campos laborales. 
JobHub busca también proveer un entorno accesible y amigable al usuario para poder brindar comodidad y confianza al segmento de clientes que busca el empleo.

### Colaboradores del Proyecto

| **Nombre**                         | **Código** | **Rol** | **Perfil** |
|------------------------------------|------------|---------|--------|
| Jimena Alexsandra Quintana Noa     | U20201f576 | Team Líder | [Linkedin](https://www.linkedin.com/in/jimena-quintana-noa/) |
| Freddy Alejandro Cuadros Contreras | U20221C488 | Colaborador | [Linkedin](https://www.linkedin.com/in/freddy-alejandro-cuadros-contreras-65a9b228b/) |

###  Progreso del Proyecto Jobhub

| **Columna**    | **Descripción**                                                                                                                                    |
|----------------|----------------------------------------------------------------------------------------------------------------------------------------------------|     
| **Backlog**       | Contiene todas las historias de usuario, tareas y características que deben desarrollarse. Es el listado de todo el trabajo pendiente.              |
| **En Progreso**   | Muestra las tareas que están siendo desarrolladas en ese momento. Ayuda a visualizar el trabajo en curso y asegurar un flujo constante.                 |
| **En Revisión**   | 	Las tareas completadas pasan a esta columna para someterse a revisión de código y revisión por pares, lo que incluye la creación de *pull requests* para garantizar que el código cumpla con los estándares de calidad antes de ser integrado. |
| **En Pruebas**    | Contiene las tareas que han pasado la revisión de código y necesitan pruebas exhaustivas (unitarias, de integración y de aceptación) para garantizar su calidad. |
| **Hecho** | Aquí se colocan las tareas que han sido completamente desarrolladas, revisadas y están listas para considerarse finalizadas. |                              

El avance de nuestro trabajo se encuentra en Trello: [Tablero de Trello]([https://mcwanted123.atlassian.net/jira/software/projects/JOB/boards/1/backlog?atlOrigin=](https://trello.com/invite/b/66ea37b93fa7297db2a56ac8/ATTIb34f8b82cd6b85ffe25a42adfdb28dacD60A15E5/jobhub)).

### Funcionalidades del Aplicativo Web JobHub

#### *Módulo de Administración de Usuarios*
- **Registro e Inicio de Sesión:**
  - Habilitar el registro de nuevos usuarios en la plataforma.
  - Facilitar el acceso a cuentas personales mediante el inicio de sesión.
  - Garantizar la seguridad de las credenciales de los usuarios.

#### *Módulo de Networking*
- **Gestión de Grupos:**
  - Crear grupo.
  - Editar los detalles/reglas del grupo.
  - Eliminar un grupo.
  - Listar todos los grupos creados.
 
- **Gestión de Comentarios:**
  - Añadir un comentario en un grupo.
  - Editar un comentario existente.
  - ELiminar comentario.
  - Listar comentarios de un usuario.     

#### *Módulo de Postulación*
- **Gestión de Empleos:**
  -  Publicar un empleo en la plataforma.
  -  Editar información de un empleo previamente publicado.
  -  Eliminar un empleo de la base de datos.
  -  Mantener actualizada la información del empleo.
 
- **Gestión Postulaciones:**
  - Listar las postulaciones asociadas a un empleo.

#### *Módulo de Seguimiento*
- **Seguimiento de Postulación:**
  - Brindar información sobre el estado de la postulación.
  - Permitir a los usuarios(Empresas) cambiar el estado de la postulación.

## Diagramas de la Aplicación

Para entender mejor la estructura y diseño de la aplicación *"JobHub"*, revisa los siguientes diagramas:

### Diagrama de clases 

![Diagrama de clases](https://github.com/user-attachments/assets/55e10b66-d62b-4990-85a8-2f5ffc863eb2)

### Diagrama de Base de Datos
![Diagrama de Base de Datos](https://github.com/user-attachments/assets/bcceb05d-f0d0-460d-a63a-f3b08217dd5a)

### Descripción de Capas del Proyecto

| Capa        | descripción                                                                                  |
|-------------|----------------------------------------------------------------------------------------------|
| API         | 	Contiene los controladores REST responsables de gestionar las solicitudes HTTP y devolver las respuestas correspondientes. |
| Entity      | 	Define las clases del modelo de datos que están vinculadas a las tablas de la base de datos. |
| Repository  | Proporciona la interfaz para realizar operaciones CRUD y conectarse con la base de datos.  |
| Service     | Contiene la lógica de negocio y define las operaciones que se ejecutan sobre las entidades.  |
| Service impl| Implementa la lógica de negocio descrita en los servicios, utilizando los repositorios necesarios para ello. |


### Asignación de Historias de Usuario

**Sprint 1:** Funciones Básicas

|     Integrante    | Módulo   | Historia de Usuario | Descripción | Tipo |
|-------------------|----------|---------------------|-------------|------|
| Alejandro Cuadros |          |                     |             |      |
|  Jimena Quintana  |          |                     |             |      |





















