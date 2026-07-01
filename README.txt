================================================================================
  PREENTREGA-DYLANSALINAS - E-COMMERCE API + FRONTEND
  Autor: Dylan Salinas
================================================================================

DESCRIPCION
-----------
Proyecto de e-commerce con backend REST (Spring Boot) y frontend web sencillo
(HTML, CSS, JavaScript). Permite gestionar productos, categorias y carritos.

TECNOLOGIAS
-----------
- Java 17
- Spring Boot 4.0.6
- Spring Data JPA + Hibernate
- MySQL (produccion/entrega) o H2 en memoria (desarrollo rapido)
- Maven
- Frontend: HTML / CSS / JavaScript vanilla

REQUISITOS
----------
- Java 17 (Temurin recomendado: https://adoptium.net)
- Para modo MySQL: XAMPP con base de datos "ecommerce" creada

COMO EJECUTAR
-------------
Modo facil (sin XAMPP, base H2 en memoria):

  En PowerShell, desde la carpeta del proyecto:
    .\run.bat

  Luego abrir en el navegador:
    http://localhost:8080

Modo MySQL (XAMPP encendido + CREATE DATABASE ecommerce;):

    .\run-mysql.bat

FRONTEND
--------
La interfaz web se abre en http://localhost:8080 e incluye:
- Listado de productos en tarjetas
- Busqueda por nombre o ID
- Crear, editar y eliminar productos
- Validacion de precio y stock

API - ENDPOINTS PRINCIPALES
---------------------------
Productos:
  GET    /productos              Listar todos
  GET    /productos/{id}         Obtener por ID
  GET    /productos/nombre/{n}   Buscar por nombre
  POST   /productos              Crear producto
  PUT    /productos/{id}         Actualizar producto
  DELETE /productos/{id}         Eliminar producto

Categorias:
  GET    /categorias             Listar todas
  GET    /categorias/{id}        Obtener por ID
  POST   /categorias             Crear categoria
  PUT    /categorias/{id}         Actualizar
  DELETE /categorias/{id}         Eliminar

Carritos:
  GET    /carritos               Listar todos
  POST   /carritos               Crear carrito vacio
  POST   /carritos/{id}/productos/{productoId}   Agregar producto
  DELETE /carritos/{id}/vaciar   Vaciar carrito
  DELETE /carritos/{id}          Eliminar carrito

MODELO PRODUCTO
---------------
- id, nombre, descripcion, precio, stock, imagenUrl, categoria

ESTRUCTURA DEL PROYECTO
-----------------------
src/main/java/com/dylansalinas/ecommerce/   Codigo Java (API)
src/main/resources/static/                  Frontend (index.html, css, js)
src/main/resources/application*.properties  Configuracion de base de datos
run.bat / run-mysql.bat                     Scripts para ejecutar la app
pom.xml                                     Dependencias Maven

================================================================================
