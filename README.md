Proyecto Final Multimedia - Aplicación Android
Descripción
Este proyecto consiste en una aplicación Android desarrollada como parte del proyecto final del curso de Multimedia. La aplicación se encarga de interactuar con una API RESTful de raperos para mostrar información sobre ellos y permitir a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre la base de datos de raperos.

Características Principales
Visualización de lista de raperos con sus detalles.
Creación de nuevos raperos.
Modificación de información de raperos existentes.
Eliminación de raperos de la base de datos.
API de Raperos
La aplicación Android consume una API RESTful de raperos, la cual proporciona endpoints para realizar operaciones CRUD sobre la base de datos de raperos. Algunos detalles sobre la API incluyen:

Tecnología Utilizada: La API está desarrollada utilizando Spring Boot.
Funcionalidades: Ofrece endpoints para obtener la lista de raperos, crear nuevos raperos, actualizar la información de raperos existentes y eliminar raperos.
Documentación: La documentación de la API se puede encontrar en API de Raperos.
Proyecto Android
La aplicación Android se ha desarrollado utilizando el entorno de desarrollo Android Studio y está escrita en Java. A continuación, se detallan las principales funcionalidades y componentes del proyecto:

Actividades y Fragmentos: La interfaz de usuario se compone de actividades principales y fragmentos para mostrar diferentes vistas y funcionalidades.
Consumo de API: Se utiliza Retrofit para realizar llamadas a la API de raperos y obtener los datos necesarios para mostrar en la aplicación.
Adaptadores y ListViews: Se implementan adaptadores personalizados para mostrar la lista de raperos y sus detalles en ListViews.
Operaciones CRUD: Se implementan funciones para realizar operaciones CRUD sobre la base de datos de raperos, como crear, leer, actualizar y eliminar.
Capturas de Pantalla
A continuación se muestran algunas capturas de pantalla de la aplicación:

![Lista de Raperos](img/lo)


Detalles de Raperos

Instalación y Ejecución
Para instalar y ejecutar la aplicación en un dispositivo Android, siga estos pasos:

Clonar el repositorio del proyecto desde GitHub.
Abrir el proyecto en Android Studio.
Conectar un dispositivo Android o utilizar un emulador.
Compilar y ejecutar la aplicación desde Android Studio.
Contribución
Las contribuciones a este proyecto son bienvenidas. Si desea contribuir, siga estos pasos:

Realizar un fork del repositorio.
Crear una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realizar los cambios necesarios y realizar commits (git commit -am 'Agregar nueva funcionalidad').
Hacer push a la rama (git push origin feature/nueva-funcionalidad).
Crear un nuevo pull request.
Autor
Nombre: Ignacio Ruiz



Agradecimientos
Agradecemos a Jose Carlos por su orientación y apoyo durante el desarrollo de este proyecto.
