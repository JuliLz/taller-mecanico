# Taller mecánico

Aplicación de escritorio para gestionar la información y los servicios de un
taller mecánico. Permite centralizar clientes, vehículos, mecánicos y trabajos
realizados, utilizando MySQL para guardar los datos.

## ¿Qué hace?

La aplicación permite:

- Registrar, consultar, editar y eliminar clientes.
- Asociar vehículos a cada cliente.
- Gestionar los mecánicos del taller.
- Crear un catálogo de tipos de servicio.
- Registrar servicios mecánicos con mecánico, vehículo, tipo de servicio,
  importe, descripción y fecha.
- Consultar, editar, eliminar y marcar servicios como finalizados.
- Mantener las relaciones entre los registros para proteger el historial de
  servicios.

## Tecnologías

- Java 17
- Java Swing
- JPA con EclipseLink
- MySQL 8+
- Maven

## Instalación

### Requisitos

Instala:

- JDK 17 o superior
- Maven
- MySQL Server 8 o superior

Comprueba que estén disponibles:

```bash
java -version
mvn -version
mysql --version
```

### 1. Descargar el proyecto

```bash
git clone https://github.com/JuliLz/taller-mecanico.git
cd taller-mecanico
```

### 2. Configurar MySQL

Crea la base de datos:

```sql
CREATE DATABASE taller_mecanico;
```

Después, abre
`src/main/resources/META-INF/persistence.xml` y configura la URL, el usuario y
la contraseña de tu servidor MySQL:

```xml
<property name="javax.persistence.jdbc.url"
          value="jdbc:mysql://localhost:3306/taller_mecanico?serverTimezone=UTC&amp;useSSL=false&amp;allowPublicKeyRetrieval=true"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value=""/>
```

El proyecto espera que el esquema y sus tablas ya existan en la base de datos;
no las crea automáticamente al iniciar.

### 3. Compilar

Desde la carpeta del proyecto ejecuta:

```bash
mvn clean package
```

### 4. Ejecutar

Abre el proyecto en un IDE compatible con Maven y ejecuta la clase principal:

```text
com.mycompany.mavenproject.Main.Main
```

También puedes ejecutar las pruebas con:

```bash
mvn test
```

Al iniciar, se abrirá la pantalla principal del taller. Desde allí podrás
crear primero los clientes, vehículos, mecánicos y tipos de servicio, y luego
registrar los trabajos realizados.
