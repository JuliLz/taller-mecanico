# Sistema de Gestión para Taller Mecánico

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Descripción
Sistema de gestión backend desarrollado en Java puro con JPA (Java Persistence API). La aplicación proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) completas para la administración integral de un taller mecánico, centralizando la información en una base de datos relacional MySQL.

## Módulos y Funcionalidades
El sistema permite la gestión completa de las siguientes entidades:
- **Clientes:** Registro y administración de datos personales y de contacto.
- **Mecánicos:** Gestión del personal técnico del taller.
- **Vehículos:** Control del parque automotor ingresado, asociando cada vehículo a su respectivo cliente.
- **Tipos de Servicios:** Catálogo maestro de servicios ofrecidos.
- **Servicios Mecánicos:** Registro histórico y operativo de los trabajos realizados, relacionando el vehículo, el mecánico asignado y el tipo de servicio.

## Tecnologías Utilizadas
- **Lenguaje:** Java (JDK 17)
- **Persistencia:** JPA (Java Persistence API)
- **Base de Datos:** MySQL
- **Gestión de dependencias:** Maven

## Requisitos Previos
Para ejecutar este proyecto en un entorno local, se requiere:
- Java Development Kit (JDK) 17 o superior.
- MySQL Server (versión 8.0 o superior recomendada).
- IDE compatible (IntelliJ IDEA, Eclipse, NetBeans, etc.).

## Configuración de la Base de Datos
1. Iniciar el servidor MySQL.
2. Crear una base de datos dedicada para el proyecto:
   ```sql
   CREATE DATABASE taller_mecanico;
   ```
3. Configurar las credenciales en el archivo `persistence.xml` (o en la clase de configuración correspondiente):
   ```xml
   <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/taller_mecanico?serverTimezone=UTC"/>
   <property name="javax.persistence.jdbc.user" value="TU_USUARIO"/>
   <property name="javax.persistence.jdbc.password" value="TU_CONTRASEÑA"/>
   ```

Clonar el repositorio:
   ```bash
   git clone https://github.com/JuliLz/taller-mecanico.git
   ```
