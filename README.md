# Sistema de gestión para taller mecánico

Aplicación de escritorio para administrar la información y las operaciones
diarias de un taller mecánico. Permite registrar clientes, vehículos,
mecánicos, tipos de servicio y servicios mecánicos, relacionando la
información mediante una base de datos MySQL.

La interfaz está desarrollada con Java Swing y la persistencia se realiza con
JPA y EclipseLink.

## Funcionalidades

### Clientes

- Registrar clientes con nombre, apellido, documento y teléfono.
- Consultar la lista de clientes.
- Editar los datos de un cliente.
- Eliminar un cliente desde la lista.
- Validar que los campos obligatorios estén completos y que documento y
  teléfono sean mayores que cero.

### Vehículos

- Registrar vehículos asociados a un cliente.
- Consultar la lista de vehículos.
- Editar los datos del vehículo.
- Eliminar vehículos desde la lista.
- Registrar marca, modelo y capacidad de asientos.
- Validar patentes en mayúsculas, eliminando espacios automáticamente.

La aplicación acepta los siguientes formatos de patente:

| Tipo | Formato | Ejemplo |
| --- | --- | --- |
| Automóvil, formato anterior | `LLLNNN` | `ABC123` |
| Automóvil, formato Mercosur | `LLNNNLL` | `AB123CD` |
| Moto, formato Mercosur | `LNNNLLL` | `A123ABC` |

La patente se guarda en mayúsculas y debe ser única en la base de datos.

### Mecánicos

- Registrar mecánicos con nombre, apellido y fecha de nacimiento.
- Consultar la lista de mecánicos.
- Editar sus datos.
- Eliminar mecánicos que no tengan servicios asociados.
- Mostrar una alerta cuando se intenta eliminar un mecánico que todavía está
  relacionado con servicios mecánicos.

### Tipos de servicio

- Registrar tipos de servicio con nombre y descripción.
- Consultar el catálogo de tipos de servicio.
- Editar tipos existentes.
- Eliminar tipos de servicio que no estén siendo utilizados.
- Mostrar una alerta cuando se intenta eliminar un tipo de servicio asociado a
  uno o más servicios mecánicos.

### Servicios mecánicos

- Registrar un servicio seleccionando:
  - Mecánico responsable.
  - Vehículo atendido.
  - Tipo de servicio.
  - Importe.
  - Descripción del trabajo.
  - Fecha de inicio.
- Consultar la lista de servicios.
- Editar servicios existentes.
- Marcar un servicio como finalizado.
- Eliminar servicios desde la lista.
- Validar que se seleccionen las relaciones necesarias, que la descripción no
  esté vacía y que el importe sea mayor que cero.

## Relaciones principales

Las entidades se relacionan de la siguiente manera:

```text
Cliente 1 ──── N Auto
Mecanico 1 ─── N ServicioMecanico
Auto 1 ─────── N ServicioMecanico
TipoServicio 1 ─ N ServicioMecanico
```

Un servicio mecánico conserva la referencia al mecánico, vehículo y tipo de
servicio seleccionados. Por eso la aplicación bloquea el borrado de
mecánicos y tipos de servicio que estén siendo utilizados, evitando dejar
relaciones inválidas o perder información del historial.

## Tecnologías

- Java 17.
- Java Swing para la interfaz gráfica.
- JPA 2.2.
- EclipseLink 2.7.12 como proveedor JPA.
- MySQL Connector/J 8.0.33.
- MySQL 8 o superior recomendado.
- Maven.

## Requisitos previos

Instalar:

1. JDK 17 o superior.
2. Maven.
3. MySQL Server 8 o superior.
4. Un IDE compatible con proyectos Maven, como NetBeans, IntelliJ IDEA o
   Eclipse.

Comprobar las instalaciones:

```bash
java -version
mvn -version
mysql --version
```

## Instalación

Clonar el repositorio:

```bash
git clone https://github.com/JuliLz/taller-mecanico.git
cd taller-mecanico
```

## Configuración de MySQL

1. Iniciar el servidor MySQL.
2. Crear la base de datos:

```sql
CREATE DATABASE taller_mecanico;
```

3. Abrir el archivo
   `src/main/resources/META-INF/persistence.xml`.
4. Configurar la URL, el usuario y la contraseña:

```xml
<property name="javax.persistence.jdbc.url"
          value="jdbc:mysql://localhost:3306/taller_mecanico?serverTimezone=UTC&amp;useSSL=false&amp;allowPublicKeyRetrieval=true"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value=""/>
```

La configuración incluida utiliza el usuario `root` sin contraseña como
ejemplo. En un entorno real se recomienda utilizar un usuario específico para
la aplicación y una contraseña segura. No se deben publicar credenciales
reales en el repositorio.

### Esquema de la base de datos

La aplicación utiliza:

```xml
<property name="eclipselink.ddl-generation" value="none"/>
```

Esto significa que EclipseLink no crea ni modifica automáticamente las tablas
al iniciar la aplicación. El esquema de `taller_mecanico` debe existir antes
de ejecutar el programa y sus tablas deben ser compatibles con las entidades
JPA del proyecto.

## Ejecución

### Desde un IDE

1. Abrir el proyecto como proyecto Maven.
2. Esperar a que Maven descargue las dependencias.
3. Verificar la conexión y las credenciales de MySQL.
4. Ejecutar la clase:

```text
com.mycompany.mavenproject.Main.Main
```

La aplicación abrirá la pantalla principal **Taller mecánico**.

### Desde Maven

Compilar y ejecutar las pruebas:

```bash
mvn clean test
```

Generar el JAR:

```bash
mvn clean package
```

El artefacto se genera en `target/mavenproject-1.0-SNAPSHOT.jar`. Como las
dependencias no están empaquetadas dentro de un JAR ejecutable único, para el
uso habitual se recomienda ejecutar la clase principal desde el IDE o
configurar el classpath de Maven.

## Guía rápida de uso

1. Abrir la aplicación.
2. Crear al menos un cliente.
3. Crear uno o más vehículos y asociarlos a un cliente.
4. Registrar los mecánicos del taller.
5. Crear los tipos de servicio disponibles.
6. Seleccionar **Nuevo servicio**.
7. Elegir mecánico, vehículo y tipo de servicio.
8. Completar el importe y la descripción.
9. Confirmar el servicio.
10. Consultar o editar los registros desde las opciones **Ver servicios**,
    **Ver clientes**, **Ver vehículos**, **Ver mecánicos** y **Ver tipos**.

Las altas y ediciones muestran mensajes de confirmación. Si faltan datos
obligatorios, la pantalla muestra una alerta y no guarda la operación.

## Estructura del proyecto

```text
src/
├── main/
│   ├── java/com/mycompany/mavenproject/
│   │   ├── Main/             # Punto de entrada de la aplicación
│   │   ├── interfaz/         # Ventanas y formularios Swing
│   │   ├── logica/           # Entidades y lógica de negocio
│   │   └── persistencia/     # Controladores JPA y acceso a datos
│   └── resources/
│       └── META-INF/
│           └── persistence.xml
└── test/
    └── java/                 # Pruebas automatizadas
```

La aplicación utiliza una separación simple por capas:

- **Interfaz:** captura las acciones del usuario y muestra alertas.
- **Lógica:** coordina las operaciones del negocio.
- **Persistencia:** ejecuta las operaciones CRUD mediante JPA.

## Consideraciones de borrado

Los mecánicos y tipos de servicio utilizados por registros de servicios
mecánicos no se eliminan físicamente. La aplicación muestra una alerta para
evitar perder la integridad del historial.

Antes de borrar información relacionada, verificar si el registro forma parte
del historial del taller. En una futura ampliación se podría implementar una
baja lógica para conservar los registros sin permitir que aparezcan en nuevas
operaciones.

## Licencia

Este proyecto no declara actualmente una licencia de distribución específica.
