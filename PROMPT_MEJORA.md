# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/Application.java` — `org.eclipse.microprofile`: El import org.eclipse.microprofile.config.inject.ConfigProperty pertenece a org.eclipse.microprofile, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/Application.java` — `org.jboss.logging`: El import org.jboss.logging.Logger pertenece a org.jboss.logging, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/MongoPaymentRepository.java` — `org.bson.types`: El import org.bson.types.ObjectId pertenece a org.bson.types, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `io.smallrye.faulttolerance`: El import io.smallrye.faulttolerance.api.BulkheadDone pertenece a io.smallrye.faulttolerance, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `org.eclipse.microprofile`: El import org.eclipse.microprofile.faulttolerance pertenece a org.eclipse.microprofile, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/ExternalPaymentServiceAdapter.java` — `org.jboss.logging`: El import org.jboss.logging.Logger pertenece a org.jboss.logging, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/GlobalExceptionHandler.java` — `org.jboss.logging`: El import org.jboss.logging.Logger pertenece a org.jboss.logging, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/IdempotencyConfig.java` — `io.smallrye.config`: El import io.smallrye.config.ConfigMapping pertenece a io.smallrye.config, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/domain/exception/PaymentProcessingException.java` — `ErrorCode.getCode`: Se invoca `getCode` sobre `ErrorCode`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/domain/exception/PaymentProcessingException.java` — `ErrorCode.getDescription`: Se invoca `getDescription` sobre `ErrorCode`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/domain/exception/ExternalServiceException.java` — `ServiceType.getValue`: Se invoca `getValue` sobre `ServiceType`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `Payment.idempotencyKey`: Se invoca `idempotencyKey` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/application/PaymentService.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `PaymentRequest.idempotencyKey`: Se invoca `idempotencyKey` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `PaymentRequest.description`: Se invoca `description` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `Payment.amount`: Se invoca `amount` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `Payment.currency`: Se invoca `currency` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `Payment.externalReference`: Se invoca `externalReference` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `Payment.createdAt`: Se invoca `createdAt` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `ExternalServiceException.getMessage`: Se invoca `getMessage` sobre `ExternalServiceException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java` — `PaymentService.findById`: Se invoca `findById` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `Payment.status`: Se invoca `status` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `Payment.externalReference`: Se invoca `externalReference` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `Payment.id`: Se invoca `id` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `ExternalServiceException.getMessage`: Se invoca `getMessage` sobre `ExternalServiceException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `PaymentService.findById`: Se invoca `findById` sobre `PaymentService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `Payment.isPresent`: Se invoca `isPresent` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `Payment.get`: Se invoca `get` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/PaymentServiceTest.java` — `Payment.isEmpty`: Se invoca `isEmpty` sobre `Payment`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapters/ExternalPaymentServiceAdapterTest.java` — `ExternalServiceException.getMessage`: Se invoca `getMessage` sobre `ExternalServiceException`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollo de software, Tecnología Java, Senior L2

### Brecha de conocimiento
Comprueba manejo de errores y reintentos.

### Reto
- Tema: E2E technologiesWork 202610091709
- Seniority: senior-l2
- Tipo: practical
- Título: Gestión de Errores y Reintentos en Procesamiento de Pagos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Implementación de Manejo de Errores Básico — objetivo: Implementar un mecanismo básico para manejar errores en el procesamiento de pagos. — entregable (NO resolver): Mecanismo básico de manejo de errores implementado y documentado.
- Fase 2: Implementación de Reintentos — objetivo: Implementar un mecanismo de reintentos para manejar fallos temporales en el servicio externo. — entregable (NO resolver): Mecanismo de reintentos implementado y documentado.
- Fase 3: Idempotencia en el Procesamiento de Pagos — objetivo: Asegurar que los pagos se procesen de manera idempotente para evitar duplicados. — entregable (NO resolver): Mecanismo de idempotencia implementado y documentado.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <properties>
        <compiler-plugin.version>3.11.0</compiler-plugin.version>
        <maven.compiler.parameters>true</maven.compiler.parameters>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <quarkus.platform.artifact-id>quarkus-universe-bom</quarkus.platform.artifact-id>
        <quarkus.platform.group-id>io.quarkus.platform</quarkus.platform.group-id>
        <quarkus.platform.version>3.15.0</quarkus.platform.version>
        <skipITs>true</skipITs>
        <surefire-plugin.version>3.2.5</surefire-plugin.version>
    </properties>
    <dependencyManagement>
        <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.12.2</version>
        </dependency>

            <dependency>
                <groupId>${quarkus.platform.group-id}</groupId>
                <artifactId>${quarkus.platform.artifact-id}</artifactId>
                <version>${quarkus.platform.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-resteasy-reactive-jackson</artifactId>
        </dependency>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-mongodb-panache</artifactId>
        </dependency>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-smallrye-fault-tolerance</artifactId>
        </dependency>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-smallrye-openapi</artifactId>
        </dependency>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-junit5</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-test-mongodb</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>io.quarkus</groupId>
                <artifactId>quarkus-maven-plugin</artifactId>
                <version>${quarkus.platform.version}</version>
                <extensions>true</extensions>
                <executions>
                    <execution>
                        <goals>
                            <goal>build</goal>
                            <goal>generate-code</goal>
                            <goal>generate-code-tests</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${compiler-plugin.version}</version>
                <configuration>
                    <parameters>${maven.compiler.parameters}</parameters>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${surefire-plugin.version}</version>
                <configuration>
                    <systemPropertyVariables>
                        <java.util.logging.manager>org.jboss.logmanager.LogManager</java.util.logging.manager>
                        <maven.home>${maven.home}</maven.home>
                    </systemPropertyVariables>
                </configuration>
            </plugin>
        </plugins>
    </build>
    <profiles>
        <profile>
            <id>native</id>
            <activation>
                <property>
                    <name>native</name>
                </property>
            </activation>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.graalvm.buildtools</groupId>
                        <artifactId>native-maven-plugin</artifactId>
                        <version>0.10.2</version>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>build</goal>
                                </goals>
                                <phase>package</phase>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
            <properties>
                <quarkus.package.type>native</quarkus.package.type>
            </properties>
        </profile>
    </profiles>
</project>

// === ARCHIVO: build.gradle ===
plugins {
    id 'java'
    id 'io.quarkus'
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation enforcedPlatform("io.quarkus.platform:quarkus-universe-bom:3.15.0")
    implementation "io.quarkus:quarkus-resteasy-reactive-jackson"
    implementation "io.quarkus:quarkus-mongodb-panache"
    implementation "io.quarkus:quarkus-smallrye-fault-tolerance"
    implementation "io.quarkus:quarkus-smallrye-openapi"

    testImplementation "io.quarkus:quarkus-junit5"
    testImplementation "io.quarkus:quarkus-test-mongodb"
    testImplementation "org.mockito:mockito-core:5.12.0"
}

group = "com.pragma"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

compileJava {
    options.encoding = 'UTF-8'
    options.compilerArgs += ['--parameters']
}

compileTestJava {
    options.encoding = 'UTF-8'
    options.compilerArgs += ['--parameters']
}

quarkus {
    buildTool = 'gradle'
}

// === ARCHIVO: src/main/resources/application.properties ===
# Configuración de la aplicación Quarkus
quarkus.application.name=payments-service
quarkus.application.version=1.0.0

# Configuración del servidor HTTP
quarkus.http.port=8080
quarkus.http.host=0.0.0.0

# Configuración de MongoDB
quarkus.mongodb.connection-string=mongodb://localhost:27017
quarkus.mongodb.database=payments_db
quarkus.mongodb.credentials.auth-source=admin

# Configuración de reintentos con SmallRye Fault Tolerance
quarkus.fault-tolerance.retry.max-retries=3
quarkus.fault-tolerance.retry.delay=500
quarkus.fault-tolerance.retry.delay-unit=MILLISECONDS
quarkus.fault-tolerance.retry.jitter=200
quarkus.fault-tolerance.retry.jitter-delay-unit=MILLISECONDS

# Configuración de tiempo de espera para llamadas externas
quarkus.fault-tolerance.timeout.value=5000
quarkus.fault-tolerance.timeout.unit=MILLISECONDS

# Configuración de circuit breaker
quarkus.fault-tolerance.circuit-breaker.fail-on=java.lang.Exception
quarkus.fault-tolerance.circuit-breaker.delay=10
quarkus.fault-tolerance.circuit-breaker.delay-unit=SECONDS
quarkus.fault-tolerance.circuit-breaker.request-volume-threshold=4
quarkus.fault-tolerance.circuit-breaker.failure-ratio=0.75
quarkus.fault-tolerance.circuit-breaker.success-threshold=2

# Configuración de OpenAPI/Swagger
quarkus.swagger-ui.path=/swagger-ui
quarkus.swagger-ui.always-include=true
quarkus.smallrye-openapi.path=/openapi

# Configuración de logging
quarkus.log.level=INFO
quarkus.log.console.json=false
quarkus.log.category."com.pragma".level=DEBUG

# Configuración de idempotencia
payments.idempotency.key-prefix=payment-idempotency-
payments.idempotency.ttl-hours=24

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Payment.java ===
package com.pragma.payments.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Payment(
    String id,
    BigDecimal amount,
    PaymentStatus status,
    String idempotencyKey,
    String externalReference,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }

    public Payment {
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.id,
            this.amount,
            newStatus,
            this.idempotencyKey,
            this.externalReference,
            this.createdAt,
            LocalDateTime.now()
        );
    }

    public Payment withExternalReference(String reference) {
        return new Payment(
            this.id,
            this.amount,
            this.status,
            this.idempotencyKey,
            reference,
            this.createdAt,
            LocalDateTime.now()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/ports/PaymentRepository.java ===
package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    
    Optional<Payment> findById(String id);
    
    Optional<Payment> findByIdempotencyKey(String idempotencyKey);
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/ports/ExternalPaymentService.java ===
package com.pragma.payments.domain.ports;

import java.math.BigDecimal;

public interface ExternalPaymentService {
    String processPayment(String idempotencyKey, BigDecimal amount);
}

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;


import com.pragma.payments.domain.model.Payment;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@QuarkusMain
public class Application implements QuarkusApplication {

    private static final Logger LOG = Logger.getLogger(Application.class);

    @ConfigProperty(name = "quarkus.http.host", defaultValue = "0.0.0.0")
    String host;

    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    int port;

    public static void main(String... args) {
        Quarkus.run(Application.class, args);
    }

    @Override
    public int run(String... args) {
        LOG.infof("Starting Payments Service on %s:%d", host, port);
        LOG.info("Payment processing system initialized successfully");
        LOG.info("Idempotency support enabled");
        LOG.info("Fault tolerance mechanisms configured");
        
        Quarkus.waitForExit();
        return 0;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/PaymentProcessingException.java ===
package com.pragma.payments.domain.exception;

import java.util.HashMap;
import java.util.Map;

public class PaymentProcessingException extends RuntimeException {

    private final String paymentId;
    private final ErrorCode errorCode;
    private final Map<String, Object> context;

    public enum ErrorCode {
        VALIDATION_ERROR("P001", "Error de validación en los datos del pago"),
        DUPLICATE_PAYMENT("P002", "Intento de procesamiento de pago duplicado"),
        INSUFFICIENT_FUNDS("P003", "Fondos insuficientes para completar el pago"),
        ACCOUNT_NOT_FOUND("P004", "Cuenta no encontrada para el pago"),
        EXTERNAL_SERVICE_ERROR("P005", "Error en el servicio externo de procesamiento"),
        TIMEOUT_ERROR("P006", "Tiempo de espera agotado durante el procesamiento"),
        DATABASE_ERROR("P007", "Error de base de datos al procesar el pago"),
        INTERNAL_ERROR("P999", "Error interno inesperado en el procesamiento");

        private final String code;
        private final String description;

        ErrorCode(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.errorCode = ErrorCode.INTERNAL_ERROR;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.errorCode = ErrorCode.INTERNAL_ERROR;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.paymentId = null;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.paymentId = null;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message, 
                                       Map<String, Object> context) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = context != null ? new HashMap<>(context) : new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message, 
                                       Throwable cause, Map<String, Object> context) {
        super(message, cause);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = context != null ? new HashMap<>(context) : new HashMap<>();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getContext() {
        return new HashMap<>(context);
    }

    public String getErrorCodeValue() {
        return errorCode != null ? errorCode.getCode() : "P999";
    }

    public String getErrorDescription() {
        return errorCode != null ? errorCode.getDescription() : "Error desconocido";
    }

    public PaymentProcessingException withContext(String key, Object value) {
        Map<String, Object> newContext = new HashMap<>(this.context);
        newContext.put(key, value);
        return new PaymentProcessingException(this.paymentId, this.errorCode, 
                                               this.getMessage(), this.getCause(), newContext);
    }

    public boolean isRetryable() {
        return this.errorCode == ErrorCode.EXTERNAL_SERVICE_ERROR ||
               this.errorCode == ErrorCode.TIMEOUT_ERROR ||
               this.errorCode == ErrorCode.DATABASE_ERROR;
    }

    @Override
    public String toString() {
        return String.format("PaymentProcessingException{paymentId='%s', errorCode=%s, message='%s'}",
                paymentId, errorCode, getMessage());
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/ExternalServiceException.java ===
package com.pragma.payments.domain.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExternalServiceException extends RuntimeException {

    private final String serviceName;
    private final String externalReference;
    private final ServiceType serviceType;
    private final FailureType failureType;
    private final int httpStatusCode;
    private final Instant timestamp;
    private final Map<String, Object> metadata;
    private final int retryCount;

    public enum ServiceType {
        PAYMENT_GATEWAY("payment_gateway"),
        FRAUD_CHECK("fraud_check"),
        NOTIFICATION("notification"),
        ACCOUNT_VERIFICATION("account_verification"),
        RISK_ANALYSIS("risk_analysis");

        private final String value;

        ServiceType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FailureType {
        TIMEOUT("timeout", true),
        CONNECTION_ERROR("connection_error", true),
        SERVER_ERROR("server_error", true),
        CLIENT_ERROR("client_error", false),
        AUTHENTICATION_ERROR("authentication_error", false),
        RATE_LIMIT_EXCEEDED("rate_limit_exceeded", true),
        SERVICE_UNAVAILABLE("service_unavailable", true),
        UNKNOWN("unknown", true);

        private final String code;
        private final boolean retryable;

        FailureType(String code, boolean retryable) {
            this.code = code;
            this.retryable = retryable;
        }

        public String getCode() {
            return code;
        }

        public boolean isRetryable() {
            return retryable;
        }
    }

    public ExternalServiceException(String serviceName, String message) {
        super(message);
        this.serviceName = serviceName;
        this.externalReference = UUID.randomUUID().toString();
        this.serviceType = inferServiceType(serviceName);
        this.failureType = FailureType.UNKNOWN;
        this.httpStatusCode = 500;
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(String serviceName, String message, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.externalReference = UUID.randomUUID().toString();
        this.serviceType = inferServiceType(serviceName);
        this.failureType = FailureType.UNKNOWN;
        this.httpStatusCode = 500;
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(ServiceType serviceType, FailureType failureType, 
                                     String message, int httpStatusCode) {
        super(message);
        this.serviceName = serviceType.getValue();
        this.serviceType = serviceType;
        this.failureType = failureType;
        this.httpStatusCode = httpStatusCode;
        this.externalReference = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(ServiceType serviceType, FailureType failureType, 
                                     String message, int httpStatusCode, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceType.getValue();
        this.serviceType = serviceType;
        this.failureType = failureType;
        this.httpStatusCode = httpStatusCode;
        this.externalReference = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(String serviceName, ServiceType serviceType, 
                                     FailureType failureType, String message, 
                                     int httpStatusCode, String externalReference,
                                     Map<String, Object> metadata, int retryCount) {
        super(message);
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.failureType = failureType;
        this.httpStatusCode = httpStatusCode;
        this.externalReference = externalReference != null ? externalReference : UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.metadata = metadata != null ? new HashMap<>(metadata) : new HashMap<>();
        this.retryCount = retryCount;
    }

    private ServiceType inferServiceType(String serviceName) {
        if (serviceName == null) {
            return ServiceType.PAYMENT_GATEWAY;
        }
        String lowerName = serviceName.toLowerCase();
        if (lowerName.contains("payment") || lowerName.contains("gateway")) {
            return ServiceType.PAYMENT_GATEWAY;
        } else if (lowerName.contains("fraud")) {
            return ServiceType.FRAUD_CHECK;
        } else if (lowerName.contains("notification")) {
            return ServiceType.NOTIFICATION;
        } else if (lowerName.contains("account")) {
            return ServiceType.ACCOUNT_VERIFICATION;
        } else if (lowerName.contains("risk")) {
            return ServiceType.RISK_ANALYSIS;
        }
        return ServiceType.PAYMENT_GATEWAY;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getMetadata() {
        return new HashMap<>(metadata);
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isRetryable() {
        return failureType != null && failureType.isRetryable();
    }

    public ExternalServiceException withMetadata(String key, Object value) {
        Map<String, Object> newMetadata = new HashMap<>(this.metadata);
        newMetadata.put(key, value);
        return new ExternalServiceException(this.serviceName, this.serviceType, this.failureType,
                this.getMessage(), this.httpStatusCode, this.externalReference, 
                newMetadata, this.retryCount);
    }

    public ExternalServiceException withRetryCount(int retryCount) {
        return new ExternalServiceException(this.serviceName, this.serviceType, this.failureType,
                this.getMessage(), this.httpStatusCode, this.externalReference, 
                this.metadata, retryCount);
    }

    @Override
    public String toString() {
        return String.format("ExternalServiceException{serviceName='%s', serviceType=%s, " +
                        "failureType=%s, httpStatusCode=%d, externalReference='%s', retryable=%b}",
                serviceName, serviceType, failureType, httpStatusCode, externalReference, isRetryable());
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/IdempotencyKeyViolationException.java ===
package com.pragma.payments.domain.exception;

public class IdempotencyKeyViolationException extends RuntimeException {
    private final String idempotencyKey;
    private final String paymentId;

    public IdempotencyKeyViolationException(String idempotencyKey, String paymentId) {
        super(buildMessage(idempotencyKey, paymentId));
        this.idempotencyKey = idempotencyKey;
        this.paymentId = paymentId;
    }

    public IdempotencyKeyViolationException(String idempotencyKey, String paymentId, Throwable cause) {
        super(buildMessage(idempotencyKey, paymentId), cause);
        this.idempotencyKey = idempotencyKey;
        this.paymentId = paymentId;
    }

    private static String buildMessage(String idempotencyKey, String paymentId) {
        return String.format("La clave de idempotencia '%s' ya fue utilizada para el pago '%s'. " +
                "No se puede procesar un pago duplicado con la misma clave de idempotencia.",
                idempotencyKey, paymentId);
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public boolean isDuplicateRequest() {
        return paymentId != null && !paymentId.isBlank();
    }

    public String getDetailedInfo() {
        return String.format("IdempotencyKeyViolationException{idempotencyKey='%s', paymentId='%s', message='%s'}",
                idempotencyKey, paymentId, getMessage());
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/MongoPaymentRepository.java ===
package com.pragma.payments.infrastructure.adapters;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.PaymentRepository;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@MongoEntity(collection = "payments")
@ApplicationScoped
public class MongoPaymentRepository implements PaymentRepository, PanacheMongoRepository<Payment> {

    @Override
    public Payment save(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo");
        }
        persist(payment);
        return payment;
    }

    @Override
    public Optional<Payment> findById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        try {
            ObjectId objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Payment> findByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        return find("idempotencyKey", idempotencyKey).firstResultOptional();
    }

    public boolean existsByIdempotencyKey(String idempotencyKey) {
        return findByIdempotencyKey(idempotencyKey).isPresent();
    }

    public Payment updatePaymentStatus(String id, Payment.PaymentStatus newStatus) {
        Optional<Payment> paymentOpt = findById(id);
        if (paymentOpt.isEmpty()) {
            return null;
        }
        Payment payment = paymentOpt.get();
        Payment updatedPayment = payment.withStatus(newStatus);
        update(updatedPayment);
        return updatedPayment;
    }

    public long countByStatus(Payment.PaymentStatus status) {
        return count("status", status);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/PaymentService.java ===
package com.pragma.payments.application;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.IdempotencyKeyViolationException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import com.pragma.payments.domain.ports.PaymentRepository;
import io.smallrye.faulttolerance.api.BulkheadDone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ExternalPaymentService externalPaymentService;

    @Inject
    public PaymentService(PaymentRepository paymentRepository,
                          ExternalPaymentService externalPaymentService) {
        this.paymentRepository = paymentRepository;
        this.externalPaymentService = externalPaymentService;
    }

    @Timeout(30000)
    @Retry(maxRetries = 3, delay = 1000, jitter = 200)
    @CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.5, delay = 10000)
    @Bulkhead(value = 10)
    public Payment processPayment(String idempotencyKey, BigDecimal amount) {
        validateInputs(idempotencyKey, amount);
        checkIdempotency(idempotencyKey);

        Payment payment = createPayment(idempotencyKey, amount);

        try {
            String externalReference = externalPaymentService.processPayment(idempotencyKey, amount);
            Payment completedPayment = payment.withExternalReference(externalReference)
                                               .withStatus(Payment.PaymentStatus.COMPLETED);
            return paymentRepository.save(completedPayment);
        } catch (ExternalServiceException e) {
            Payment failedPayment = payment.withStatus(Payment.PaymentStatus.FAILED);
            paymentRepository.save(failedPayment);
            throw new PaymentProcessingException("Error al procesar el pago en el servicio externo", e);
        }
    }

    private void validateInputs(String idempotencyKey, BigDecimal amount) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede estar vacía");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
    }

    private void checkIdempotency(String idempotencyKey) {
        Optional<Payment> existingPayment = paymentRepository.findByIdempotencyKey(idempotencyKey);
        if (existingPayment.isPresent()) {
            Payment existing = existingPayment.get();
            throw new IdempotencyKeyViolationException(idempotencyKey, existing.id());
        }
    }

    private Payment createPayment(String idempotencyKey, BigDecimal amount) {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(
            paymentId,
            idempotencyKey,
            amount,
            Payment.PaymentStatus.PENDING,
            Instant.now(),
            null
        );
        return paymentRepository.save(payment);
    }

    public Optional<Payment> findPaymentById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return paymentRepository.findById(id);
    }

    public Optional<Payment> findPaymentByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        return paymentRepository.findByIdempotencyKey(idempotencyKey);
    }

    public Payment retryPayment(String paymentId) {
        Optional<Payment> existingPayment = paymentRepository.findById(paymentId);
        if (existingPayment.isEmpty()) {
            throw new PaymentProcessingException("Pago no encontrado: " + paymentId);
        }

        Payment payment = existingPayment.get();
        if (payment.status() == Payment.PaymentStatus.COMPLETED) {
            throw new PaymentProcessingException("El pago ya fue completado: " + paymentId);
        }

        String newIdempotencyKey = payment.idempotencyKey() + "_retry_" + System.currentTimeMillis();
        return processPayment(newIdempotencyKey, payment.amount());
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/ExternalPaymentServiceAdapter.java ===
package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Random;

@ApplicationScoped
public class ExternalPaymentServiceAdapter implements ExternalPaymentService {

    private static final Logger LOG = Logger.getLogger(ExternalPaymentServiceAdapter.class);
    private static final double FAILURE_RATE = 0.3;
    private final Random random = new Random();

    @Override
    public String processPayment(String idempotencyKey, BigDecimal amount) {
        LOG.infof("Procesando pago con clave de idempotencia: %s, monto: %s", idempotencyKey, amount);

        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new ExternalServiceException("La clave de idempotencia no puede ser nula o vacía");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExternalServiceException("El monto del pago debe ser mayor a cero");
        }

        simulateNetworkDelay();

        if (shouldFail()) {
            LOG.warn("El servicio externo de pagos ha fallado simuladamente");
            throw new ExternalServiceException("Error de comunicación con el servicio externo de pagos");
        }

        String externalReference = generateExternalReference();
        LOG.infof("Pago procesado exitosamente. Referencia externa: %s", externalReference);
        return externalReference;
    }

    private void simulateNetworkDelay() {
        try {
            Thread.sleep(random.nextInt(200) + 50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.warn("Hilo interrumpido durante la simulación de delay de red");
        }
    }

    private boolean shouldFail() {
        return random.nextDouble() < FAILURE_RATE;
    }

    private String generateExternalReference() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/GlobalExceptionHandler.java ===
package com.pragma.payments.infrastructure.config;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.IdempotencyKeyViolationException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        LOG.error("Excepción no controlada", exception);

        if (exception instanceof IdempotencyKeyViolationException) {
            return buildErrorResponse(
                Response.Status.CONFLICT,
                "CONFLICT",
                exception.getMessage()
            );
        }

        if (exception instanceof ExternalServiceException) {
            return buildErrorResponse(
                Response.Status.SERVICE_UNAVAILABLE,
                "SERVICE_UNAVAILABLE",
                exception.getMessage()
            );
        }

        if (exception instanceof PaymentProcessingException) {
            return buildErrorResponse(
                Response.Status.BAD_REQUEST,
                "PAYMENT_ERROR",
                exception.getMessage()
            );
        }

        if (exception instanceof IllegalArgumentException) {
            return buildErrorResponse(
                Response.Status.BAD_REQUEST,
                "INVALID_ARGUMENT",
                exception.getMessage()
            );
        }

        return buildErrorResponse(
            Response.Status.INTERNAL_SERVER_ERROR,
            "INTERNAL_ERROR",
            "Error interno del servidor"
        );
    }

    private Response buildErrorResponse(Response.Status status, String code, String message) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put("status", status.getStatusCode());
        errorBody.put("error", code);
        errorBody.put("message", message);

        return Response
            .status(status)
            .type(MediaType.APPLICATION_JSON)
            .entity(errorBody)
            .build();
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/IdempotencyConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigMapping(prefix = "payments.idempotency")
public interface IdempotencyConfig {

    @WithDefault("3600")
    int keyTtlSeconds();

    @WithDefault("true")
    boolean enabled();

    @WithDefault("PAY-")
    String keyPrefix();
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/PaymentResource.java ===
package com.pragma.payments.infrastructure.adapters;




import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.exception.IdempotencyKeyViolationException;
import com.pragma.payments.application.PaymentService;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Map;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;

    @POST
    public Response processPayment(PaymentRequest request) {
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "El monto debe ser mayor a cero"))
                    .build();
        }
        if (request.currency() == null || request.currency().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "La moneda es obligatoria"))
                    .build();
        }

        String idempotencyKey = request.idempotencyKey();
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            idempotencyKey = java.util.UUID.randomUUID().toString();
        }

        try {
            Payment payment = paymentService.processPayment(
                idempotencyKey,
                request.amount(),
                request.currency(),
                request.description()
            );

            PaymentResponse response = new PaymentResponse(
                payment.id(),
                payment.amount(),
                payment.currency(),
                payment.status().name(),
                payment.externalReference(),
                payment.createdAt().toString()
            );

            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (com.pragma.payments.domain.exception.IdempotencyKeyViolationException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        } catch (com.pragma.payments.domain.exception.PaymentProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", "Error al procesar el pago", "detail", e.getMessage()))
                    .build();
        } catch (com.pragma.payments.domain.exception.ExternalServiceException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(Map.of("error", "Servicio externo no disponible", "detail", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPayment(@PathParam("id") String id) {
        return paymentService.findById(id)
            .map(payment -> {
                PaymentResponse response = new PaymentResponse(
                    payment.id(),
                    payment.amount(),
                    payment.currency(),
                    payment.status().name(),
                    payment.externalReference(),
                    payment.createdAt().toString()
                );
                return Response.ok(response).build();
            })
            .orElse(Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Pago no encontrado"))
                    .build());
    }

    public record PaymentRequest(
        BigDecimal amount,
        String currency,
        String description,
        String idempotencyKey
    ) {}

    public record PaymentResponse(
        String id,
        BigDecimal amount,
        String currency,
        String status,
        String externalReference,
        String createdAt
    ) {}
}

// === ARCHIVO: src/test/java/com/pragma/payments/application/PaymentServiceTest.java ===
package com.pragma.payments.application;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import com.pragma.payments.domain.ports.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private PaymentRepository paymentRepository;
    private ExternalPaymentService externalPaymentService;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        externalPaymentService = mock(ExternalPaymentService.class);
        paymentService = new PaymentService(paymentRepository, externalPaymentService);
    }

    @Test
    void processPayment_Success_ReturnsPaymentWithApprovedStatus() throws Exception {
        String idempotencyKey = "test-key-123";
        BigDecimal amount = new BigDecimal("100.00");
        String currency = "USD";
        String description = "Test payment";
        String externalRef = "ext-ref-456";

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
        when(externalPaymentService.processPayment(idempotencyKey, amount)).thenReturn(externalRef);

        Payment savedPayment = new Payment(
            "payment-id-789",
            amount,
            currency,
            description,
            PaymentStatus.APPROVED,
            externalRef,
            idempotencyKey,
            Instant.now()
        );
        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        Payment result = paymentService.processPayment(idempotencyKey, amount, currency, description);

        assertNotNull(result);
        assertEquals(PaymentStatus.APPROVED, result.status());
        assertEquals(externalRef, result.externalReference());
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void processPayment_IdempotencyKeyExists_ReturnsExistingPayment() throws Exception {
        String idempotencyKey = "existing-key";
        BigDecimal amount = new BigDecimal("50.00");

        Payment existingPayment = new Payment(
            "existing-id",
            amount,
            "EUR",
            "Original payment",
            PaymentStatus.APPROVED,
            "ext-ref",
            idempotencyKey,
            Instant.now()
        );
        when(paymentRepository.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Optional.of(existingPayment));

        Payment result = paymentService.processPayment(idempotencyKey, amount, "EUR", "New description");

        assertEquals(existingPayment.id(), result.id());
        verify(externalPaymentService, never()).processPayment(any(), any());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void processPayment_ExternalServiceFails_ThrowsPaymentProcessingException() {
        String idempotencyKey = "fail-key";
        BigDecimal amount = new BigDecimal("200.00");

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
        when(externalPaymentService.processPayment(idempotencyKey, amount))
            .thenThrow(new ExternalServiceException("Servicio externo falló"));

        assertThrows(PaymentProcessingException.class, () -> {
            paymentService.processPayment(idempotencyKey, amount, "USD", "Test");
        });

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository).save(paymentCaptor.capture());
        assertEquals(PaymentStatus.FAILED, paymentCaptor.getValue().status());
    }

    @Test
    void processPayment_ExternalServiceThrowsAfterRetries_ThrowsExternalServiceException() {
        String idempotencyKey = "retry-fail-key";
        BigDecimal amount = new BigDecimal("300.00");

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
        when(externalPaymentService.processPayment(eq(idempotencyKey), eq(amount)))
            .thenThrow(new ExternalServiceException("Error temporal"));

        ExternalServiceException exception = assertThrows(ExternalServiceException.class, () -> {
            paymentService.processPayment(idempotencyKey, amount, "USD", "Test");
        });

        assertTrue(exception.getMessage().contains("Error temporal"));
    }

    @Test
    void findById_ExistingId_ReturnsPayment() {
        String paymentId = "find-me";
        Payment expectedPayment = new Payment(
            paymentId,
            new BigDecimal("150.00"),
            "GBP",
            "Found payment",
            PaymentStatus.APPROVED,
            "ext-123",
            "idem-123",
            Instant.now()
        );
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(expectedPayment));

        Optional<Payment> result = paymentService.findById(paymentId);

        assertTrue(result.isPresent());
        assertEquals(paymentId, result.get().id());
    }

    @Test
    void findById_NonExistingId_ReturnsEmpty() {
        String paymentId = "not-found";
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        Optional<Payment> result = paymentService.findById(paymentId);

        assertTrue(result.isEmpty());
    }

    @Test
    void processPayment_ValidatesAmount_MustBePositive() {
        String idempotencyKey = "zero-amount";
        BigDecimal zeroAmount = BigDecimal.ZERO;

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.processPayment(idempotencyKey, zeroAmount, "USD", "Test");
        });
    }

    @Test
    void processPayment_ValidatesCurrency_CannotBeBlank() {
        String idempotencyKey = "blank-currency";
        BigDecimal amount = new BigDecimal("100.00");

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.processPayment(idempotencyKey, amount, "", "Test");
        });
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapters/ExternalPaymentServiceAdapterTest.java ===
package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@QuarkusTest
class ExternalPaymentServiceAdapterTest {

    @Inject
    ExternalPaymentServiceAdapter adapter;

    @InjectMock
    ExternalPaymentService externalPaymentService;

    private AtomicInteger callCount;

    @BeforeEach
    void setUp() {
        callCount = new AtomicInteger(0);
        reset(externalPaymentService);
    }

    @Test
    void processPayment_FirstCallSuccess_ReturnsExternalReference() {
        String idempotencyKey = "success-key";
        BigDecimal amount = new BigDecimal("100.00");
        String expectedRef = "ext-ref-success";

        when(externalPaymentService.processPayment(idempotencyKey, amount))
            .thenReturn(expectedRef);

        String result = adapter.processPayment(idempotencyKey, amount);

        assertEquals(expectedRef, result);
        verify(externalPaymentService, times(1)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_SecondCallWithSameKey_ReturnsCachedReference() {
        String idempotencyKey = "cache-key";
        BigDecimal amount = new BigDecimal("200.00");
        String expectedRef = "ext-ref-cached";

        when(externalPaymentService.processPayment(idempotencyKey, amount))
            .thenReturn(expectedRef);

        String firstResult = adapter.processPayment(idempotencyKey, amount);
        String secondResult = adapter.processPayment(idempotencyKey, amount);

        assertEquals(expectedRef, firstResult);
        assertEquals(expectedRef, secondResult);
        verify(externalPaymentService, times(1)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_ServiceFailsOnce_RetriesAndSucceeds() {
        String idempotencyKey = "retry-success-key";
        BigDecimal amount = new BigDecimal("300.00");
        String expectedRef = "ext-ref-retry";

        when(externalPaymentService.processPayment(eq(idempotencyKey), eq(amount)))
            .thenThrow(new ExternalServiceException("Temporal failure"))
            .thenReturn(expectedRef);

        String result = adapter.processPayment(idempotencyKey, amount);

        assertEquals(expectedRef, result);
        verify(externalPaymentService, times(2)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_ServiceFailsMultipleTimes_ThrowsAfterMaxRetries() {
        String idempotencyKey = "retry-fail-key";
        BigDecimal amount = new BigDecimal("400.00");

        when(externalPaymentService.processPayment(any(), any()))
            .thenThrow(new ExternalServiceException("Persistent failure"));

        ExternalServiceException exception = assertThrows(ExternalServiceException.class, () -> {
            adapter.processPayment(idempotencyKey, amount);
        });

        assertTrue(exception.getMessage().contains("Persistent failure"));
        verify(externalPaymentService, atLeast(3)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_InvalidAmount_ThrowsIllegalArgumentException() {
        String idempotencyKey = "invalid-amount";
        BigDecimal negativeAmount = new BigDecimal("-100.00");

        assertThrows(IllegalArgumentException.class, () -> {
            adapter.processPayment(idempotencyKey, negativeAmount);
        });

        verify(externalPaymentService, never()).processPayment(any(), any());
    }

    @Test
    void processPayment_NullIdempotencyKey_ThrowsIllegalArgumentException() {
        BigDecimal amount = new BigDecimal("500.00");

        assertThrows(IllegalArgumentException.class, () -> {
            adapter.processPayment(null, amount);
        });

        verify(externalPaymentService, never()).processPayment(any(), any());
    }

    @Test
    void processPayment_ZeroAmount_ThrowsIllegalArgumentException() {
        String idempotencyKey = "zero-amount";
        BigDecimal zeroAmount = BigDecimal.ZERO;

        assertThrows(IllegalArgumentException.class, () -> {
            adapter.processPayment(idempotencyKey, zeroAmount);
        });

        verify(externalPaymentService, never()).processPayment(any(), any());
    }
}
```
