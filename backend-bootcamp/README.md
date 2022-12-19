# Back-end's bootcamp

La idea de este bootcamp es poder aplicar los conocimientos adquiridos a lo largo del curso mediante la simulación de un escenario real. Se provee una estructura inicial de un proyecto de back-end Spring boot con algunos módulos adicionales con clases utilitarias.
Se recomienda seguir las siguientes [convenciones]() para implementar la solución.

El proyecto se encuentra desarrollado bajo el framework [Spring](https://spring.io/) por lo que se recomienda leer información general a través de las [guías oficiales](https://spring.io/guides).

## Requisitos

* Java (*OpenJDK*) en la versión definida en el archivo [POM principal](./back-end/pom-parent.xml) del proyecto. Se puede descargar de la siguiente [página](https://adoptopenjdk.net/), o para [linux](https://linuxize.com/post/install-java-on-ubuntu-18-04/) 
* Maven 3.8: en [windows](https://maven.apache.org/download.cgi), en [linux](https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/)
* Base de datos: Postgresql utilizando el container definido en el siguiente [archivo](./docker-compose.yml).
* IDE: Se puede elegir entre [eclipse](https://www.eclipse.org/downloads/) e [intellij](https://www.jetbrains.com/es-es/idea/download/#section=linux). Recomendamos intellij para mejor soporte.
* GIT: [Guía de instalación](https://www.atlassian.com/es/git/tutorials/install-git)

## Puesta en Marcha

**Fork del repositorio**

Realizar un fork del proyecto principal para que quede asociado a cada usuario.


**Clonar el repositorio a la maquina local**

Determinar una carpeta local en la maquina donde se va a trabajar para bajar el código.


>```git clone https://git.pladema.net/sgxcapacitacion/username/backendbootstrap.git```

**Build**

Buildear el proyecto por línea de comandos desde la carpeta `/back-end`:

>```mvn clean install```

**Build without running tests** (faster):

>```mvn clean install -DskipTests```

**Run application**

- Correr la aplicación por línea de comandos desde la carpeta `/back-end/app`:
> `java -Dspring.profiles.active=dev -jar target/app*.jar`

- Desde un IDE de preferencia:

1. Importar el módulo **backend** en el IDE.

2. Ubicar la clase principal del proyecto definida en el módulo [App](app/pom.xml#L20).
3. Correr la Aplicación:

- **Eclipse**
    - Ejecutar `Run application` sobre la clase principal.
- **IntelliJ**
    - Ejecutar `Run` sobre la clase principal.

Tras la ejecución de todos los pasos, podrá accederse a la [página principal](http://localhost:8080/api/swagger-ui/index.html#) de Swagger para invocar la API con los endpoints definidos.


## Ambiente de desarrollo

Características relevantes sobre el entorno de desarrollo:

- Propiedades de ambiente para configuración del entorno. Por defecto, las propiedades necesarias para desarrollo se encuentran definidas en el archivo `application-dev.properties`. Para consultar todas las propiedades definidas leer el [documento específico](../properties.md).

Configurar archivo de propiedades externalizado por línea de comandos o desde el IDE:

>```--spring.config.additional-location=file:/$PATH```


## Material de Lectura

Les aconsejamos leer este [documento](convenciones.md) de convenciones para tener en cuenta a la hora de desarrollar.


## Persistencia

Cuando se haga uso de la base de datos se debe correr la aplicación con el profile **bd**, para esto realizar los siguientes pasos:

- **Eclipse**
  - Agregar `-Dspring.profiles.active=bd` en el campo **"VM Options"** del Run Configuration.
- **IntelliJ**
  - Agregar `spring.profiles.active=bd` en el campo **"Program Arguments"** del Run Configuration. 
    
