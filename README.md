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
| **En Curso**   | Muestra las tareas que están siendo desarrolladas en ese momento. Ayuda a visualizar el trabajo en curso y asegurar un flujo constante.                 |
| **Revisión**   | 	Las tareas completadas pasan a esta columna para someterse a revisión de código y revisión por pares, lo que incluye la creación de *pull requests* para garantizar que el código cumpla con los estándares de calidad antes de ser integrado. |
| **Finalizada** | Aquí se colocan las tareas que han sido completamente desarrolladas, revisadas y están listas para considerarse finalizadas. |                              

El avance de nuestro trabajo se encuentra en Jira: [Tablero Jira](https://mcwanted123.atlassian.net/jira/software/projects/JOB/boards/1/backlog?atlOrigin=eyJpIjoiMjBjMTc4NGJmYTc3NDljZmFjZDkyYzY5MjUzZDgwNDYiLCJwIjoiaiJ9).

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

