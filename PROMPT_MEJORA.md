# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- `src/main/java/com/banca/prestamos/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/features/prestamo.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `Solicitante.getNombre`: Se invoca `getNombre` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `PrestamoRepository.save`: Se invoca `save` sobre `PrestamoRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `Solicitante.getNumeroDocumento`: Se invoca `getNumeroDocumento` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.nombre`: Se invoca `nombre` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.apellido`: Se invoca `apellido` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.tipoDocumento`: Se invoca `tipoDocumento` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.numeroDocumento`: Se invoca `numeroDocumento` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.email`: Se invoca `email` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.telefono`: Se invoca `telefono` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.monto`: Se invoca `monto` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.plazoMeses`: Se invoca `plazoMeses` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.tasaInteres`: Se invoca `tasaInteres` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.motivo`: Se invoca `motivo` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getSolicitante`: Se invoca `getSolicitante` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getMonto`: Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getFechaSolicitud`: Se invoca `getFechaSolicitud` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getEstado`: Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getFechaAprobacion`: Se invoca `getFechaAprobacion` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getInteresTasa`: Se invoca `getInteresTasa` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getMotivoRechazo`: Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getSolicitante`: Se invoca `getSolicitante` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getMonto`: Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getFechaSolicitud`: Se invoca `getFechaSolicitud` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getEstado`: Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getFechaAprobacion`: Se invoca `getFechaAprobacion` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getInteresTasa`: Se invoca `getInteresTasa` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getMotivoRechazo`: Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `EstadoPrestamo.toDomain`: Se invoca `toDomain` sobre `EstadoPrestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `EstadoPrestamo.name`: Se invoca `name` sobre `EstadoPrestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `EvaluacionRiesgoResponse.nivelRiesgo`: Se invoca `nivelRiesgo` sobre `EvaluacionRiesgoResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `EvaluacionRiesgoResponse.score`: Se invoca `score` sobre `EvaluacionRiesgoResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `EvaluacionRiesgoResponse.aprobable`: Se invoca `aprobable` sobre `EvaluacionRiesgoResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getNombre`: Se invoca `getNombre` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getApellido`: Se invoca `getApellido` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getTipoDocumento`: Se invoca `getTipoDocumento` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getNumeroDocumento`: Se invoca `getNumeroDocumento` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getEstado`: Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `EstadoPrestamo.name`: Se invoca `name` sobre `EstadoPrestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getMonto`: Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getInteresTasa`: Se invoca `getInteresTasa` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getMotivoRechazo`: Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getFechaSolicitud`: Se invoca `getFechaSolicitud` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `PrestamoRepository.save`: Se invoca `save` sobre `PrestamoRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getEstado`: Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getMonto`: Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getFechaAprobacion`: Se invoca `getFechaAprobacion` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getMotivoRechazo`: Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getId`: Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java` — `PrestamoController.crearSolicitud`: Se invoca `crearSolicitud` sobre `PrestamoController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java` — `PrestamoController.obtenerPrestamo`: Se invoca `obtenerPrestamo` sobre `PrestamoController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
docker build -t reto:local . && terraform -chdir=terraform init -backend=false && terraform -chdir=terraform validate
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter DevSecOps, Especialidad Ingeniería, Tecnología DevSecOps, Senior

### Brecha de conocimiento
Conoce, aplica y enseña al equipo las siguientes metodologías BDD, TDD y DDD. Desarrollador experimentado en equipo distribuido buscando dominar prácticas modernas de desarrollo que mejoren la calidad y estructura del código.

### Reto
- Tema: Técnicas de desarrollo
- Seniority: senior-l2
- Tipo: mixed
- Título: Implementación de metodologías BDD, TDD y DDD en un proyecto de banca
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración y definición del dominio — objetivo: Comprender y definir el dominio del proyecto utilizando DDD — entregable (NO resolver): Modelo de dominio documentado con agregados, entidades y valor-objetos.
- Fase 2: Implementación de pruebas con BDD — objetivo: Aplicar la metodología BDD para escribir pruebas que describan el comportamiento del sistema — entregable (NO resolver): Pruebas BDD documentadas y ejecutables para los casos de uso principales.
- Fase 3: Implementación de pruebas unitarias con TDD — objetivo: Aplicar la metodología TDD para escribir pruebas unitarias que garanticen la calidad del código — entregable (NO resolver): Pruebas unitarias TDD documentadas y ejecutables para las funcionalidades principales.

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

// === ARCHIVO: providers.tf ===
# Define los proveedores de Terraform necesarios para el despliegue de la infraestructura
# de la aplicación de préstamos personales.
# Se usan los proveedores de Azure y AWS con versiones flexibles para evitar bloqueos.

terraform {
  required_version = ">= 1.5.0"
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "~> 3.0"
    }
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.0"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.20"
    }
  }
}

# Configuración del proveedor Azure
provider "azurerm" {
  features {}
  skip_provider_registration = true
}

# Configuración del proveedor AWS
provider "aws" {
  region = "us-east-1"
}

# Configuración del proveedor Docker
provider "docker" {
  host = "unix:///var/run/docker.sock"
}

# Configuración del proveedor Kubernetes
provider "kubernetes" {
  config_path = "~/.kube/config"
}

# Backend para almacenar el estado de Terraform (se configura en el pipeline)
terraform {
  backend "azurerm" {
    resource_group_name  = "tfstate-rg"
    storage_account_name = "tfstatestorage"
    container_name       = "tfstate"
    key                  = "prestamos.tfstate"
  }
}

# Variables comunes para los proveedores
variable "environment" {
  description = "Ambiente de despliegue (dev, staging, prod)"
  type        = string
  default     = "dev"
}

variable "location" {
  description = "Región de Azure donde se desplegarán los recursos"
  type        = string
  default     = "eastus"
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <groupId>com.banca</groupId>
    <artifactId>prestamos</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>prestamos</name>
    <description>Servicio de gestión de préstamos personales con DDD, BDD y TDD</description>

    <properties>
        <java.version>21</java.version>
        <mapstruct.version>1.5.5.Final</mapstruct.version>
        <lombok.version>1.18.30</lombok.version>
        <cucumber.version>7.15.0</cucumber.version>
        <resilience4j.version>2.1.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Resilience4j para manejo de fallos -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot2</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- MapStruct para mapeo de objetos -->
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>${mapstruct.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${mapstruct.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- Lombok para reducir boilerplate -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- Base de datos H2 para desarrollo -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.11.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.11.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${mapstruct.version}</version>
                        </path>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/*Steps.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/banca/prestamos/PrestamosApplication.java ===
package com.banca.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

/**
 * Clase principal de la aplicación Spring Boot para el servicio de préstamos personales.
 * Configura los componentes centrales y activa las características necesarias para:
 * - Arquitectura hexagonal con aislamiento de dominio
 * - Integración con sistemas externos con resiliencia
 * - Ejecución asíncrona para manejar carga alta
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@ComponentScan(basePackages = {"com.banca.prestamos"})
public class PrestamosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }

    /**
     * Configura un RestTemplate con timeout para llamadas a servicios externos.
     * @return RestTemplate configurado con timeout de conexión y lectura.
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setConnectTimeout(Duration.ofMillis(1000));
        restTemplate.setReadTimeout(Duration.ofMillis(2000));
        return restTemplate;
    }

    /**
     * Configuración del CircuitBreaker para manejar fallos en el servicio de riesgo.
     * @return CircuitBreakerConfig con umbrales de fallo y tiempos de espera.
     */
    @Bean
    public CircuitBreakerConfig circuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(10)
                .build();
    }

    /**
     * Configuración del TimeLimiter para manejar timeouts en llamadas a servicios externos.
     * @return TimeLimiterConfig con timeout de 2 segundos.
     */
    @Bean
    public TimeLimiterConfig timeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
    }
}

// === ARCHIVO: src/main/java/com/banca/prestamos/application/port/in/PrestamoUseCase.java ===
package com.banca.prestamos.application.port.in;

import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Puerto de entrada para los casos de uso relacionados con préstamos.
 * Define las operaciones que el dominio expone a la capa de aplicación.
 */
public interface PrestamoUseCase {

    /**
     * Crea una nueva solicitud de préstamo.
     * @param solicitante Datos del solicitante del préstamo
     * @param monto Monto solicitado
     * @param plazoMeses Plazo en meses para la devolución
     * @param fechaSolicitud Fecha de solicitud
     * @return El préstamo creado con su identificador único
     * @throws IllegalArgumentException Si algún parámetro no cumple con las invariantes del dominio
     */
    Prestamo crearSolicitudPrestamo(Solicitante solicitante, BigDecimal monto, int plazoMeses, LocalDate fechaSolicitud);

    /**
     * Aprobar un préstamo existente.
     * @param prestamoId Identificador único del préstamo
     * @param fechaAprobacion Fecha en la que se aprueba el préstamo
     * @param interesTasa Tasa de interés aplicada (ej: 0.05 para 5%)
     * @return El préstamo aprobado con su nuevo estado
     * @throws IllegalStateException Si el préstamo no está en estado PENDIENTE
     * @throws IllegalArgumentException Si la tasa de interés es negativa
     */
    Prestamo aprobarPrestamo(UUID prestamoId, LocalDate fechaAprobacion, BigDecimal interesTasa);

    /**
     * Rechazar un préstamo existente.
     * @param prestamoId Identificador único del préstamo
     * @param motivo Motivo del rechazo
     * @return El préstamo rechazado con su nuevo estado
     * @throws IllegalStateException Si el préstamo no está en estado PENDIENTE
     * @throws IllegalArgumentException Si el motivo es nulo o vacío
     */
    Prestamo rechazarPrestamo(UUID prestamoId, String motivo);

    /**
     * Obtener los detalles de un préstamo por su identificador.
     * @param prestamoId Identificador único del préstamo
     * @return Detalles completos del préstamo
     * @throws IllegalArgumentException Si el préstamo no existe
     */
    Prestamo obtenerDetallesPrestamo(UUID prestamoId);

    /**
     * Calcular el cronograma de pagos para un préstamo aprobado.
     * @param prestamoId Identificador único del préstamo
     * @return Lista de cuotas con fechas y montos
     * @throws IllegalStateException Si el préstamo no está aprobado
     */
    // Método comentado intencionalmente para que el participante lo implemente en la fase 3
    // List<Cuota> calcularCronogramaPagos(UUID prestamoId);
}

// === ARCHIVO: src/main/java/com/banca/prestamos/domain/model/Prestamo.java ===
package com.banca.prestamos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Agregado raíz del dominio de préstamos.
 * Representa un préstamo personal con todas sus invariantes protegidas.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Prestamo {

    @NotNull
    private UUID id;

    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private Solicitante solicitante;

    @NotNull
    @DecimalMin(value = "100.00", message = "El monto mínimo es 100")
    private BigDecimal monto;

    @Positive(message = "El plazo debe ser positivo")
    private int plazoMeses;

    @NotNull
    private LocalDate fechaSolicitud;

    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private EstadoPrestamo estado;

    private LocalDate fechaAprobacion;

    @DecimalMin(value = "0.00", inclusive = false, message = "La tasa de interés debe ser positiva")
    private BigDecimal interesTasa;

    @Size(max = 255, message = "El motivo no puede exceder 255 caracteres")
    private String motivoRechazo;

    /**
     * Crea una nueva solicitud de préstamo.
     * @param solicitante Datos del solicitante
     * @param monto Monto solicitado
     * @param plazoMeses Plazo en meses
     * @param fechaSolicitud Fecha de solicitud
     * @return Nueva instancia de Prestamo en estado PENDIENTE
     */
    public static Prestamo crearSolicitud(Solicitante solicitante, BigDecimal monto, int plazoMeses, LocalDate fechaSolicitud) {
        if (solicitante == null) {
            throw new IllegalArgumentException("El solicitante no puede ser nulo");
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo debe ser positivo");
        }
        if (fechaSolicitud == null) {
            throw new IllegalArgumentException("La fecha de solicitud no puede ser nula");
        }

        return new Prestamo(
            UUID.randomUUID(),
            solicitante,
            monto,
            plazoMeses,
            fechaSolicitud,
            EstadoPrestamo.PENDIENTE,
            null,
            null,
            null
        );
    }

    /**
     * Aprobar el préstamo con una tasa de interés.
     * @param fechaAprobacion Fecha de aprobación
     * @param interesTasa Tasa de interés aplicada
     * @return El préstamo con estado APROBADO
     */
    public Prestamo aprobar(LocalDate fechaAprobacion, BigDecimal interesTasa) {
        if (this.estado != EstadoPrestamo.PENDIENTE) {
            throw new IllegalStateException("Solo se puede aprobar préstamos en estado PENDIENTE");
        }
        if (fechaAprobacion == null) {
            throw new IllegalArgumentException("La fecha de aprobación no puede ser nula");
        }
        if (interesTasa == null || interesTasa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasa de interés debe ser positiva");
        }

        this.estado = EstadoPrestamo.APROBADO;
        this.fechaAprobacion = fechaAprobacion;
        this.interesTasa = interesTasa;
        return this;
    }

    /**
     * Rechazar el préstamo con un motivo.
     * @param motivo Motivo del rechazo
     * @return El préstamo con estado RECHAZADO
     */
    public Prestamo rechazar(String motivo) {
        if (this.estado != EstadoPrestamo.PENDIENTE) {
            throw new IllegalStateException("Solo se puede rechazar préstamos en estado PENDIENTE");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo no puede ser nulo o vacío");
        }

        this.estado = EstadoPrestamo.RECHAZADO;
        this.motivoRechazo = motivo;
        return this;
    }

    /**
     * Enum que representa los posibles estados de un préstamo.
     */
    public enum EstadoPrestamo {
        PENDIENTE,
        APROBADO,
        RECHAZADO
    }
}

// === ARCHIVO: src/main/java/com/banca/prestamos/domain/model/Solicitante.java ===
package com.banca.prestamos.domain.model;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entidad del dominio que representa a un solicitante de préstamo.
 * Contiene información personal y de contacto del solicitante.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Solicitante {

    @NotNull
    private UUID id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String apellido;

    @NotBlank(message = "El tipo de documento no puede estar vacío")
    @Size(min = 2, max = 5, message = "El tipo de documento debe tener entre 2 y 5 caracteres")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento no puede estar vacío")
    @Pattern(regexp = "^[0-9]{5,20}$", message = "El número de documento debe contener solo dígitos (5-20)")
    private String numeroDocumento;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener solo dígitos (7-15)")
    private String telefono;

    /**
     * Crea un nuevo solicitante con los datos proporcionados.
     * @param nombre Nombre del solicitante
     * @param apellido Apellido del solicitante
     * @param tipoDocumento Tipo de documento (ej: DNI, PAS)
     * @param numeroDocumento Número de documento
     * @param email Email de contacto
     * @param telefono Teléfono de contacto
     * @return Nueva instancia de Solicitante
     */
    public static Solicitante crearSolicitante(String nombre, String apellido, String tipoDocumento,
            String numeroDocumento, String email, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
        }
        if (tipoDocumento == null || tipoDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo o vacío");
        }
        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede ser nulo o vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío");
        }

        return new Solicitante(
            UUID.randomUUID(),
            nombre.trim(),
            apellido.trim(),
            tipoDocumento.trim(),
            numeroDocumento.trim(),
            email.trim(),
            telefono.trim()
        );
    }
}

// === ARCHIVO: terraform/variables.tf ===
variable "aws_region" {
  description = "Región de AWS donde se desplegará la infraestructura"
  type        = string
  default     = "us-east-1"
}

variable "environment" {
  description = "Ambiente de despliegue (dev, staging, prod)"
  type        = string
  validation {
    condition     = contains(["dev", "staging", "prod"], var.environment)
    error_message = "El ambiente debe ser: dev, staging o prod."
  }
}

variable "project_name" {
  description = "Nombre del proyecto para identificar recursos"
  type        = string
  default     = "prestamos"
}

variable "vpc_cidr" {
  description = "CIDR block para la VPC"
  type        = string
  default     = "10.0.0.0/16"
}

variable "availability_zones" {
  description = "Zonas de disponibilidad para las subnets"
  type        = list(string)
  default     = ["us-east-1a", "us-east-1b", "us-east-1c"]
}

variable "public_subnet_cidrs" {
  description = "CIDR blocks para subnets públicas"
  type        = list(string)
  default     = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
}

variable "private_subnet_cidrs" {
  description = "CIDR blocks para subnets privadas (ECS tasks)"
  type        = list(string)
  default     = ["10.0.11.0/24", "10.0.12.0/24", "10.0.13.0/24"]
}

variable "database_subnet_cidrs" {
  description = "CIDR blocks para subnets de base de datos"
  type        = list(string)
  default     = ["10.0.21.0/24", "10.0.22.0/24", "10.0.23.0/24"]
}

variable "ecs_cluster_name" {
  description = "Nombre del cluster ECS"
  type        = string
  default     = "prestamos-cluster"
}

variable "ecs_service_name" {
  description = "Nombre del servicio ECS"
  type        = string
  default     = "prestamos-service"
}

variable "container_name" {
  description = "Nombre del contenedor Docker"
  type        = string
  default     = "prestamos"
}

variable "container_port" {
  description = "Puerto del contenedor"
  type        = number
  default     = 8080
}

variable "docker_image" {
  description = "Imagen Docker del servicio (ECR repository URL)"
  type        = string
}

variable "docker_image_tag" {
  description = "Tag de la imagen Docker"
  type        = string
  default     = "latest"
}

variable "fargate_cpu" {
  description = "CPU units para tareas Fargate (256, 512, 1024, 2048, 4096)"
  type        = number
  default     = 512
}

variable "fargate_memory" {
  description = "Memoria MB para tareas Fargate (1024, 2048, 3072, 4096, 8192)"
  type        = number
  default     = 1024
}

variable "desired_count" {
  description = "Número deseado de tareas ECS"
  type        = number
  default     = 2
}

variable "min_count" {
  description = "Número mínimo de tareas en auto-scaling"
  type        = number
  default     = 2
}

variable "max_count" {
  description = "Número máximo de tareas en auto-scaling"
  type        = number
  default     = 10
}

variable "autoscaling_target_value" {
  description = "Porcentaje de CPU target para auto-scaling"
  type        = number
  default     = 70
}

variable "db_instance_class" {
  description = "Clase de instancia RDS"
  type        = string
  default     = "db.t3.micro"
}

variable "db_allocated_storage" {
  description = "Almacenamiento allocated para RDS en GB"
  type        = number
  default     = 20
}

variable "db_name" {
  description = "Nombre de la base de datos"
  type        = string
  default     = "prestamosdb"
}

variable "db_username" {
  description = "Usuario maestro de la base de datos"
  type        = string
  default     = "prestamos_admin"
  sensitive   = true
}

variable "db_password" {
  description = "Password maestro de la base de datos"
  type        = string
  sensitive   = true
}

variable "rds_multi_az" {
  description = "Habilitar Multi-AZ para RDS"
  type        = bool
  default     = false
}

variable "rds_backup_retention_days" {
  description = "Días de retención de backups"
  type        = number
  default     = 7
}

variable "enable_deletion_protection" {
  description = "Habilitar protección contra eliminación"
  type        = bool
  default     = true
}

variable "allowed_cidr_blocks" {
  description = "Bloques CIDR permitidos para acceso al ALB"
  type        = list(string)
  default     = ["0.0.0.0/0"]
}

variable "health_check_path" {
  description = "Path para health checks del ALB"
  type        = string
  default     = "/actuator/health"
}

variable "health_check_interval" {
  description = "Intervalo de health checks en segundos"
  type        = number
  default     = 30
}

variable "health_check_timeout" {
  description = "Timeout de health checks en segundos"
  type        = number
  default     = 5
}

variable "health_check_healthy_threshold" {
  description = "Número de checks exitosos para marcar como healthy"
  type        = number
  default     = 2
}

variable "health_check_unhealthy_threshold" {
  description = "Número de checks fallidos para marcar como unhealthy"
  type        = number
  default     = 2
}

variable "certificate_arn" {
  description = "ARN del certificado ACM para HTTPS"
  type        = string
  default     = ""
}

variable "enable_waf" {
  description = "Habilitar AWS WAF para protección"
  type        = bool
  default     = false
}

variable "log_retention_days" {
  description = "Días de retención de logs en CloudWatch"
  type        = number
  default     = 30
}

variable "enable_detailed_monitoring" {
  description = "Habilitar monitoring detallado en ECS"
  type        = bool
  default     = false
}

// === ARCHIVO: terraform/main.tf ===
terraform {
  required_version = ">= 1.5"

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region

  default_tags {
    tags = {
      Project     = var.project_name
      Environment = var.environment
      ManagedBy   = "Terraform"
    }
  }
}

# ===========================================
# VPC y Networking
# ===========================================
resource "aws_vpc" "main" {
  cidr_block           = var.vpc_cidr
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "${var.project_name}-vpc-${var.environment}"
  }
}

resource "aws_internet_gateway" "main" {
  vpc_id = aws_vpc.main.id

  tags = {
    Name = "${var.project_name}-igw-${var.environment}"
  }
}

resource "aws_subnet" "public" {
  count             = length(var.public_subnet_cidrs)
  vpc_id            = aws_vpc.main.id
  cidr_block        = var.public_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  map_public_ip_on_launch = true

  tags = {
    Name = "${var.project_name}-public-${count.index + 1}"
    Type = "Public"
  }
}

resource "aws_subnet" "private" {
  count             = length(var.private_subnet_cidrs)
  vpc_id            = aws_vpc.main.id
  cidr_block        = var.private_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  tags = {
    Name = "${var.project_name}-private-${count.index + 1}"
    Type = "Private-ECS"
  }
}

resource "aws_subnet" "database" {
  count             = length(var.database_subnet_cidrs)
  vpc_id            = aws_vpc.main.id
  cidr_block        = var.database_subnet_cidrs[count.index]
  availability_zone = var.availability_zones[count.index]

  tags = {
    Name = "${var.project_name}-database-${count.index + 1}"
    Type = "Private-Database"
  }
}

resource "aws_nat_gateway" "main" {
  count         = length(var.availability_zones)
  allocation_id = aws_eip.nat[count.index].id
  subnet_id     = aws_subnet.public[count.index].id

  tags = {
    Name = "${var.project_name}-nat-${count.index + 1}"
  }

  depends_on = [aws_internet_gateway.main]
}

resource "aws_eip" "nat" {
  count  = length(var.availability_zones)
  domain = "vpc"

  tags = {
    Name = "${var.project_name}-eip-${count.index + 1}"
  }
}

resource "aws_route_table" "private" {
  count  = length(var.availability_zones)
  vpc_id = aws_vpc.main.id

  route {
    cidr_block     = "0.0.0.0/0"
    nat_gateway_id = aws_nat_gateway.main[count.index].id
  }

  tags = {
    Name = "${var.project_name}-rt-private-${count.index + 1}"
  }
}

resource "aws_route_table_association" "private" {
  count          = length(var.private_subnet_cidrs)
  subnet_id      = aws_subnet.private[count.index].id
  route_table_id = aws_route_table.private[count.index % length(var.availability_zones)].id
}

resource "aws_route_table" "public" {
  vpc_id = aws_vpc.main.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.main.id
  }

  tags = {
    Name = "${var.project_name}-rt-public"
  }
}

resource "aws_route_table_association" "public" {
  count          = length(var.public_subnet_cidrs)
  subnet_id      = aws_subnet.public[count.index].id
  route_table_id = aws_route_table.public.id
}

# ===========================================
# Security Groups
# ===========================================
resource "aws_security_group" "alb" {
  name        = "${var.project_name}-alb-sg-${var.environment}"
  description = "Security group para Application Load Balancer"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = var.allowed_cidr_blocks
    description = "HTTPS from anywhere"
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = var.allowed_cidr_blocks
    description = "HTTP from anywhere"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    description = "Allow all outbound"
  }

  tags = {
    Name = "${var.project_name}-alb-sg"
  }
}

resource "aws_security_group" "ecs" {
  name        = "${var.project_name}-ecs-sg-${var.environment}"
  description = "Security group para tareas ECS"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port       = var.container_port
    to_port         = var.container_port
    protocol        = "tcp"
    security_groups = [aws_security_group.alb.id]
    description     = "Traffic from ALB"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    description = "Allow all outbound"
  }

  tags = {
    Name = "${var.project_name}-ecs-sg"
  }
}

resource "aws_security_group" "rds" {
  name        = "${var.project_name}-rds-sg-${var.environment}"
  description = "Security group para RDS instance"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [aws_security_group.ecs.id]
    description     = "PostgreSQL from ECS tasks"
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
    description = "Allow all outbound"
  }

  tags = {
    Name = "${var.project_name}-rds-sg"
  }
}

# ===========================================
# RDS PostgreSQL
# ===========================================
resource "aws_db_subnet_group" "main" {
  name       = "${var.project_name}-db-subnet-${var.environment}"
  subnet_ids = aws_subnet.database[*].id

  tags = {
    Name = "${var.project_name}-db-subnet-group"
  }
}

resource "aws_db_instance" "main" {
  identifier             = "${var.project_name}-${var.environment}"
  engine                 = "postgres"
  engine_version         = "15.4"
  instance_class         = var.db_instance_class
  allocated_storage      = var.db_allocated_storage
  storage_type           = "gp3"
  storage_encrypted      = true
  db_name                = var.db_name
  username               = var.db_username
  password               = var.db_password
  db_subnet_group_name   = aws_db_subnet_group.main.id
  vpc_security_group_ids = [aws_security_group.rds.id]
  multi_az               = var.rds_multi_az
  backup_retention_period = var.rds_backup_retention_days
  backup_window          = "03:00-04:00"
  maintenance_window     = "mon:04:00-mon:05:00"
  deletion_protection    = var.enable_deletion_protection
  skip_final_snapshot    = var.environment == "dev"
  final_snapshot_identifier = var.environment != "dev" ? "${var.project_name}-final-${var.environment}" : null

  enabled_cloudwatch_logs_exports = ["postgresql", "upgrade"]

  tags = {
    Name = "${var.project_name}-rds"
  }
}

# ===========================================
# ECS Cluster y Task Definition
# ===========================================
resource "aws_ecs_cluster" "main" {
  name = var.ecs_cluster_name

  setting {
    name  = "containerInsights"
    value = var.enable_detailed_monitoring ? "enabled" : "disabled"
  }

  tags = {
    Name = "${var.project_name}-cluster"
  }
}

data "aws_ecs_task_definition" "latest" {
  count = var.environment == "prod" ? 1 : 0
  task_definition = "${var.ecs_service_name}:${aws_ecs_task_definition.main.revision}"
}

resource "aws_ecs_task_definition" "main" {
  family                   = var.ecs_service_name
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = tostring(var.fargate_cpu)
  memory                   = tostring(var.fargate_memory)
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn

  container_definitions = jsonencode([{
    name      = var.container_name
    image     = "${var.docker_image}:${var.docker_image_tag}"
    essential = true
    portMappings = [{
      containerPort = var.container_port
      protocol      = "tcp"
    }]
    environment = [
      { name = "SPRING_PROFILES_ACTIVE", value = var.environment },
      { name = "SPRING_DATASOURCE_URL", value = "jdbc:postgresql://${aws_db_instance.main.endpoint}/prestamosdb" },
      { name = "SPRING_DATASOURCE_USERNAME", value = var.db_username },
      { name = "SPRING_DATASOURCE_PASSWORD", value = var.db_password },
      { name = "JAVA_OPTS", value = "-Xmx512m -Xms256m -XX:+UseG1GC" }
    ]
    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "/ecs/${var.ecs_service_name}"
        "awslogs-region"        = var.aws_region
        "awslogs-stream-prefix" = "ecs"
      }
    }
    healthCheck = {
      command     = ["CMD-SHELL", "curl -f http://localhost:8080/actuator/health || exit 1"]
      interval    = var.health_check_interval
      timeout     = var.health_check_timeout
      retries     = var.health_check_healthy_threshold
      startPeriod = 60
    }
  }])

  tags = {
    Name = "${var.project_name}-task-def"
  }
}

# ===========================================
# IAM Roles
# ===========================================
resource "aws_iam_role" "ecs_execution_role" {
  name = "${var.project_name}-ecs-execution-role-${var.environment}"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_execution_role_policy" {
  role       = aws_iam_role.ecs_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_iam_role" "ecs_task_role" {
  name = "${var.project_name}-ecs-task-role-${var.environment}"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
    }]
  })
}

# ===========================================
# Application Load Balancer
# ===========================================
resource "aws_lb" "main" {
  name               = "${var.project_name}-alb-${var.environment}"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb.id]
  subnets            = aws_subnet.public[*].id

  enable_deletion_protection = var.enable_deletion_protection

  tags = {
    Name = "${var.project_name}-alb"
  }
}

resource "aws_lb_target_group" "main" {
  name     = "${var.project_name}-tg-${var.environment}"
  port     = var.container_port
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id

  health_check {
    enabled             = true
    path                = var.health_check_path
    matcher             = "200"
    interval            = var.health_check_interval
    timeout             = var.health_check_timeout
    healthy_threshold   = var.health_check_healthy_threshold
    unhealthy_threshold = var.health_check_unhealthy_threshold
  }

  target_type = "ip"

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.main.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.main.arn
  }
}

resource "aws_lb_listener" "https" {
  count             = var.certificate_arn != "" ? 1 : 0
  load_balancer_arn = aws_lb.main.arn
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-2016-08"
  certificate_arn   = var.certificate_arn

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.main.arn
  }
}

# ===========================================
# ECS Service
# ===========================================
resource "aws_ecs_service" "main" {
  name            = var.ecs_service_name
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.main.arn
  desired_count   = var.desired_count
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = aws_subnet.private[*].id
    security_groups  = [aws_security_group.ecs.id]
    assign_public_ip = false
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.main.arn
    container_name   = var.container_name
    container_port   = var.container_port
  }

  deployment_controller {
    type = "ECS"
  }

  deployment_minimum_healthy_percent = 50
  deployment_maximum_percent         = 200

  health_check_grace_period_seconds = 60

  lifecycle {
    create_before_destroy = true
  }

  depends_on = [aws_lb_listener.http]
}

# ===========================================
# Auto Scaling
# ===========================================
resource "aws_appautoscaling_target" "ecs_target" {
  max_capacity       = var.max_count
  min_capacity       = var.min_count
  resource_id        = "service/${aws_ecs_cluster.main.name}/${aws_ecs_service.main.name}"
  scalable_dimension = "ecs:service:DesiredCount"
  role_arn           = aws_iam_role.ecs_autoscaling_role.arn
}

resource "aws_iam_role" "ecs_autoscaling_role" {
  name = "${var.project_name}-ecs-autoscaling-role-${var.environment}"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [{
      Action = "sts:AssumeRole"
      Effect = "Allow"
      Principal = {
        Service = "application-autoscaling.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_autoscaling_role_policy" {
  role       = aws_iam_role.ecs_autoscaling_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSAutoScalingRolePolicy"
}

resource "aws_appautoscaling_policy" "cpu_scaling" {
  name               = "${var.project_name}-cpu-scaling-${var.environment}"
  policy_type        = "TargetTrackingScaling"
  resource_id        = aws_appautoscaling_target.ecs_target.resource_id
  scalable_dimension = aws_appautoscaling_target.ecs_target.scalable_dimension

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageCPUUtilization"
    }
    target_value       = var.autoscaling_target_value
    scale_in_cooldown  = 300
    scale_out_cooldown = 60
  }
}

resource "aws_appautoscaling_policy" "memory_scaling" {
  name               = "${var.project_name}-memory-scaling-${var.environment}"
  policy_type        = "TargetTrackingScaling"
  resource_id        = aws_appautoscaling_target.ecs_target.resource_id
  scalable_dimension = aws_appautoscaling_target.ecs_target.scalable_dimension

  target_tracking_scaling_policy_configuration {
    predefined_metric_specification {
      predefined_metric_type = "ECSServiceAverageMemoryUtilization"
    }
    target_value       = var.autoscaling_target_value
    scale_in_cooldown  = 300
    scale_out_cooldown = 60
  }
}

# ===========================================
# CloudWatch Log Group
# ===========================================
resource "aws_cloudwatch_log_group" "ecs" {
  name              = "/ecs/${var.ecs_service_name}"
  retention_in_days = var.log_retention_days

  tags = {
    Name = "${var.project_name}-log-group"
  }
}

// === ARCHIVO: terraform/outputs.tf ===
output "vpc_id" {
  description = "ID de la VPC creada"
  value       = aws_vpc.main.id
}

output "vpc_cidr" {
  description = "CIDR block de la VPC"
  value       = aws_vpc.main.cidr_block
}

output "public_subnet_ids" {
  description = "IDs de las subnets públicas"
  value       = aws_subnet.public[*].id
}

output "private_subnet_ids" {
  description = "IDs de las subnets privadas para ECS"
  value       = aws_subnet.private[*].id
}

output "database_subnet_ids" {
  description = "IDs de las subnets de base de datos"
  value       = aws_subnet.database[*].id
}

output "ecs_cluster_name" {
  description = "Nombre del cluster ECS"
  value       = aws_ecs_cluster.main.name
}

output "ecs_cluster_arn" {
  description = "ARN del cluster ECS"
  value       = aws_ecs_cluster.main.arn
}

output "ecs_service_name" {
  description = "Nombre del servicio ECS"
  value       = aws_ecs_service.main.name
}

output "ecs_task_definition_arn" {
  description = "ARN de la task definition"
  value       = aws_ecs_task_definition.main.arn
}

output "ecs_task_definition_family" {
  description = "Familia de la task definition"
  value       = aws_ecs_task_definition.main.family
}

output "ecs_task_definition_revision" {
  description = "Revisión de la task definition"
  value       = aws_ecs_task_definition.main.revision
}

output "rds_endpoint" {
  description = "Endpoint de la base de datos RDS"
  value       = aws_db_instance.main.endpoint
  sensitive   = true
}

output "rds_arn" {
  description = "ARN de la base de datos RDS"
  value       = aws_db_instance.main.arn
}

output "rds_instance_id" {
  description = "ID de la instancia RDS"
  value       = aws_db_instance.main.identifier
}

output "alb_arn" {
  description = "ARN del Application Load Balancer"
  value       = aws_lb.main.arn
}

output "alb_name" {
  description = "Nombre del ALB"
  value       = aws_lb.main.name
}

output "alb_dns_name" {
  description = "DNS name del ALB"
  value       = aws_lb.main.dns_name
}

output "alb_zone_id" {
  description = "Zone ID del ALB paraRoute53"
  value       = aws_lb.main.zone_id
}

output "alb_target_group_arn" {
  description = "ARN del target group del ALB"
  value       = aws_lb_target_group.main.arn
}

output "alb_target_group_name" {
  description = "Nombre del target group del ALB"
  value       = aws_lb_target_group.main.name
}

output "ecs_execution_role_arn" {
  description = "ARN del rol de ejecución de ECS"
  value       = aws_iam_role.ecs_execution_role.arn
}

output "ecs_task_role_arn" {
  description = "ARN del rol de tarea de ECS"
  value       = aws_iam_role.ecs_task_role.arn
}

output "ecs_autoscaling_role_arn" {
  description = "ARN del rol de auto-scaling de ECS"
  value       = aws_iam_role.ecs_autoscaling_role.arn
}

output "security_group_alb_id" {
  description = "ID del security group del ALB"
  value       = aws_security_group.alb.id
}

output "security_group_ecs_id" {
  description = "ID del security group de ECS"
  value       = aws_security_group.ecs.id
}

output "security_group_rds_id" {
  description = "ID del security group de RDS"
  value       = aws_security_group.rds.id
}

output "cloudwatch_log_group_name" {
  description = "Nombre del Log Group de CloudWatch"
  value       = aws_cloudwatch_log_group.ecs.name
}

output "service_url" {
  description = "URL pública del servicio"
  value       = "http://${aws_lb.main.dns_name}"
}

output "service_url_https" {
  description = "URL HTTPS del servicio"
  value       = var.certificate_arn != "" ? "https://${aws_lb.main.dns_name}" : ""
}

output "database_connection_string" {
  description = "String de conexión JDBC para la base de datos"
  value       = "jdbc:postgresql://${aws_db_instance.main.endpoint}/${var.db_name}"
  sensitive   = true
}

output "environment_info" {
  description = "Información del ambiente"
  value = {
    environment   = var.environment
    aws_region    = var.aws_region
    project_name  = var.project_name
    fargate_cpu   = var.fargate_cpu
    fargate_memory = var.fargate_memory
    desired_count = var.desired_count
    min_count     = var.min_count
    max_count     = var.max_count
  }
}

// === ARCHIVO: terraform/environments/dev/terraform.tfvars ===
# Valores de configuración para el ambiente de desarrollo
# Servicio de Gestión de Préstamos Personales

# ============================================
# Configuración de Azure Resource Group
# ============================================
environment                     = "dev"
location                        = "eastus"
resource_group_name             = "rg-prestamos-dev"

# ============================================
# Configuración de Azure Kubernetes Service
# ============================================
aks_cluster_name                = "aks-prestamos-dev"
aks_dns_prefix                  = "prestamos-dev"
aks_kubernetes_version          = "1.28"
aks_node_count                  = 1
aks_vm_size                      = "Standard_B2s"
aks_enable_role_assignment      = true

# ============================================
# Configuración de Base de Datos Azure SQL
# ============================================
sql_server_name                 = "sql-prestamos-dev"
sql_database_name               = "prestamos_dev"
sql_admin_login                 = "prestamosadmin"
sql_sku_name                    = "Basic"
sql_capacity                    = 5
sql_max_capacity                = 10
sql_auto_pause_delay_minutes    = 60
sql_min_capacity                = 0.5

# ============================================
# Configuración de Application Insights
# ============================================
app_insights_name               = "appi-prestamos-dev"
app_insights_sampling_percentage = 100

# ============================================
# Configuración del Servicio (Spring Boot)
# ============================================
service_name                    = "prestamos"
service_port                    = 8080
service_replicas                = 1
service_cpu_request             = "250m"
service_cpu_limit               = "500m"
service_memory_request          = "512Mi"
service_memory_limit            = "1Gi"

# ============================================
# Configuración de Resiliencia (Resilience4j)
# ============================================
resilience4j_circuit_breaker_instances = {
  riesgo = {
    failure_rate_threshold        = 50
    wait_duration_in_open_state   = 30
    permitted_number_of_calls_in_half_open_state = 3
    sliding_window_size           = 10
    minimum_number_of_calls       = 5
  }
}

resilience4j_rate_limiter_instances = {
  default = {
    limit_for_period             = 100
    limit_refresh_period         = "1s"
    timeout_duration             = 500
  }
}

resilience4j_retry_instances = {
  default = {
    max_attempts                 = 3
    wait_duration                = 1000
    enable_exponential_backoff   = true
    exponential_backoff_multiplier = 2
  }
}

# ============================================
# Configuración de Timeouts (ms)
# ============================================
timeout_riesgo_client           = 2000
timeout_gestion_prestamos       = 5000
timeout_database_query          = 3000

# ============================================
# Configuración de Logging
# ============================================
log_level                       = "DEBUG"
log_pattern                     = "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"

# ============================================
# Configuración de Health Checks
# ============================================
health_check_interval           = 30
health_check_timeout            = 10
health_check_failure_threshold  = 3

# ============================================
# Configuración de Variables de Entorno para el Contenedor
# ============================================
container_env_vars = {
  SPRING_PROFILES_ACTIVE        = "dev"
  SPRING_DATASOURCE_URL         = "jdbc:sqlserver://sql-prestamos-dev.database.windows.net:1433;databaseName=prestamos_dev;encrypt=true;trustServerCertificate=false;"
  SPRING_DATASOURCE_USERNAME    = "prestamosadmin"
  MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE = "health,info,metrics,prometheus"
  MANAGEMENT_METRICS_EXPORT_PROMETHEUS_ENABLED = "true"
}

# ============================================
# Configuración de Red y Seguridad
# ============================================
allowed_ip_ranges              = ["10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16"]
enable_ssl                      = false
enable_mtls                     = false

# ============================================
# Configuración de Escalado (HPA)
# ============================================
hpa_enabled                     = false
hpa_min_replicas                = 1
hpa_max_replicas                = 3
hpa_cpu_percentage              = 70
hpa_memory_percentage           = 80

# ============================================
# Configuración de Volumes (Azure Files)
# ============================================
azure_file_share_name          = "prestamos-dev"
storage_account_name           = "stprestamosdev"

// === ARCHIVO: src/main/java/com/banca/prestamos/domain/model/ValorObjeto.java ===
package com.banca.prestamos.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ValorObjeto {

    public static class TasaInteres {
        private final BigDecimal valor;
        private final String tipo;

        private TasaInteres(BigDecimal valor, String tipo) {
            this.valor = Objects.requireNonNull(valor, "El valor de la tasa no puede ser nulo");
            this.tipo = Objects.requireNonNull(tipo, "El tipo de tasa no puede ser nulo");
            validarRango(valor);
            validarTipo(tipo);
        }

        private void validarRango(BigDecimal valor) {
            if (valor.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
            }
            if (valor.compareTo(new BigDecimal("1.0")) > 0) {
                throw new IllegalArgumentException("La tasa de interés no puede exceder el 100%");
            }
        }

        private void validarTipo(String tipo) {
            if (!tipo.equals("FIJA") && !tipo.equals("VARIABLE")) {
                throw new IllegalArgumentException("Tipo de tasa inválido: " + tipo + ". Debe ser FIJA o VARIABLE");
            }
        }

        public static TasaInteres crear(BigDecimal valor, String tipo) {
            return new TasaInteres(valor, tipo);
        }

        public static TasaInteres tasaFija(BigDecimal porcentaje) {
            return new TasaInteres(porcentaje.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP), "FIJA");
        }

        public static TasaInteres tasaVariable(BigDecimal porcentaje) {
            return new TasaInteres(porcentaje.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP), "VARIABLE");
        }

        public BigDecimal getValor() {
            return valor;
        }

        public String getTipo() {
            return tipo;
        }

        public BigDecimal getValorPorcentual() {
            return valor.multiply(BigDecimal.valueOf(100));
        }

        public boolean esFija() {
            return "FIJA".equals(tipo);
        }

        public boolean esVariable() {
            return "VARIABLE".equals(tipo);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TasaInteres that = (TasaInteres) o;
            return valor.compareTo(that.valor) == 0 && Objects.equals(tipo, that.tipo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(valor, tipo);
        }

        @Override
        public String toString() {
            return String.format("TasaInteres{valor=%s, tipo=%s}", valor, tipo);
        }
    }

    public static class Moneda {
        private final String codigo;
        private final String simbolo;
        private final String nombre;

        private Moneda(String codigo, String simbolo, String nombre) {
            this.codigo = Objects.requireNonNull(codigo, "El código de moneda no puede ser nulo");
            this.simbolo = Objects.requireNonNull(simbolo, "El símbolo no puede ser nulo");
            this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
            validarCodigo(codigo);
        }

        private void validarCodigo(String codigo) {
            if (codigo.length() != 3) {
                throw new IllegalArgumentException("El código de moneda debe tener 3 caracteres");
            }
            if (!codigo.equals(codigo.toUpperCase())) {
                throw new IllegalArgumentException("El código de moneda debe estar en mayúsculas");
            }
        }

        public static Moneda de(String codigo) {
            return switch (codigo.toUpperCase()) {
                case "PEN" -> new Moneda("PEN", "S/.", "Sol Peruano");
                case "USD" -> new Moneda("USD", "$", "Dólar Estadounidense");
                case "EUR" -> new Moneda("EUR", "€", "Euro");
                default -> new Moneda(codigo, codigo, "Moneda Desconocida");
            };
        }

        public static Moneda soles() {
            return new Moneda("PEN", "S/.", "Sol Peruano");
        }

        public static Moneda dolares() {
            return new Moneda("USD", "$", "Dólar Estadounidense");
        }

        public String getCodigo() {
            return codigo;
        }

        public String getSimbolo() {
            return simbolo;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Moneda moneda = (Moneda) o;
            return Objects.equals(codigo, moneda.codigo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codigo);
        }

        @Override
        public String toString() {
            return String.format("Moneda{codigo='%s', simbolo='%s', nombre='%s'}", codigo, simbolo, nombre);
        }
    }

    public static class Monto {
        private final BigDecimal valor;
        private final Moneda moneda;

        private Monto(BigDecimal valor, Moneda moneda) {
            this.valor = Objects.requireNonNull(valor, "El monto no puede ser nulo");
            this.moneda = Objects.requireNonNull(moneda, "La moneda no puede ser nula");
            validarPositivo(valor);
        }

        private void validarPositivo(BigDecimal valor) {
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor a cero");
            }
        }

        public static Monto crear(BigDecimal valor, Moneda moneda) {
            return new Monto(valor, moneda);
        }

        public static Monto soles(BigDecimal valor) {
            return new Monto(valor, Moneda.soles());
        }

        public static Monto dolares(BigDecimal valor) {
            return new Monto(valor, Moneda.dolares());
        }

        public BigDecimal getValor() {
            return valor;
        }

        public Moneda getMoneda() {
            return moneda;
        }

        public boolean esMayorQue(Monto otro) {
            return this.valor.compareTo(otro.valor) > 0;
        }

        public boolean tieneMismaMoneda(Monto otro) {
            return this.moneda.equals(otro.moneda);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Monto monto = (Monto) o;
            return valor.compareTo(monto.valor) == 0 && Objects.equals(moneda, monto.moneda);
        }

        @Override
        public int hashCode() {
            return Objects.hash(valor, moneda);
        }

        @Override
        public String toString() {
            return String.format("%s %s", moneda.getSimbolo(), valor);
        }
    }
}

// === ARCHIVO: src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoRepository.java ===
package com.banca.prestamos.infrastructure.adapter.out.persistence;


import com.banca.prestamos.domain.model.EstadoPrestamo;
import com.banca.prestamos.domain.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, UUID> {

    Optional<Prestamo> findById(UUID id);

    List<Prestamo> findBySolicitanteId(UUID solicitanteId);

    List<Prestamo> findByEstado(Prestamo.EstadoPrestamo estado);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaSolicitud BETWEEN :fechaInicio AND :fechaFin")
    List<Prestamo> findByRangoFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                      @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT p FROM Prestamo p WHERE p.solicitante.numeroDocumento = :numeroDocumento")
    List<Prestamo> findByNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.estado = :estado")
    long countByEstado(@Param("estado") Prestamo.EstadoPrestamo estado);

    @Query("SELECT p FROM Prestamo p WHERE p.estado = 'APROBADO' AND p.fechaAprobacion <= :fechaLimite")
    List<Prestamo> findAprobadosAntesDe(@Param("fechaLimite") LocalDate fechaLimite);

    boolean existsBySolicitanteIdAndEstado(UUID solicitanteId, Prestamo.EstadoPrestamo estado);
}

// === ARCHIVO: src/main/java/com/banca/prestamos/application/service/PrestamoService.java ===
package com.banca.prestamos.application.service;

import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import com.banca.prestamos.infrastructure.adapter.out.persistence.PrestamoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PrestamoService implements PrestamoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoService.class);

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo crearSolicitudPrestamo(Solicitante solicitante, BigDecimal monto, 
                                           int plazoMeses, LocalDate fechaSolicitud) {
        logger.info("Creando solicitud de préstamo para solicitante: {} por monto: {} plazo: {} meses",
                    solicitante.getNombre(), monto, plazoMeses);

        validarMonto(monto);
        validarPlazo(plazoMeses);
        validarSolicitante(solicitante);

        Prestamo prestamo = Prestamo.crearSolicitud(solicitante, monto, plazoMeses, fechaSolicitud);
        Prestamo guardado = prestamoRepository.save(prestamo);

        logger.info("Préstamo creado exitosamente con ID: {}", guardado.getId());
        return guardado;
    }

    @Override
    public Prestamo aprobarPrestamo(UUID prestamoId, LocalDate fechaAprobacion, BigDecimal interesTasa) {
        logger.info("Aprobando préstamo ID: {} con tasa de interés: {}", prestamoId, interesTasa);

        Prestamo prestamo = obtenerPrestamoOThrow(prestamoId);
        validarTasaInteres(interesTasa);

        Prestamo aprobado = prestamo.aprobar(fechaAprobacion, interesTasa);
        Prestamo guardado = prestamoRepository.save(aprobado);

        logger.info("Préstamo ID: {} aprobado exitosamente", prestamoId);
        return guardado;
    }

    @Override
    public Prestamo rechazarPrestamo(UUID prestamoId, String motivo) {
        logger.info("Rechazando préstamo ID: {} motivo: {}", prestamoId, motivo);

        Prestamo prestamo = obtenerPrestamoOThrow(prestamoId);
        validarMotivoRechazo(motivo);

        Prestamo rechazado = prestamo.rechazar(motivo);
        Prestamo guardado = prestamoRepository.save(rechazado);

        logger.info("Préstamo ID: {} rechazado exitosamente", prestamoId);
        return guardado;
    }

    @Override
    @Transactional(readOnly = true)
    public Prestamo obtenerDetallesPrestamo(UUID prestamoId) {
        logger.debug("Obteniendo detalles del préstamo ID: {}", prestamoId);
        return obtenerPrestamoOThrow(prestamoId);
    }

    private Prestamo obtenerPrestamoOThrow(UUID prestamoId) {
        return prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Préstamo no encontrado con ID: " + prestamoId));
    }

    private void validarMonto(BigDecimal monto) {
        if (monto == null) {
            throw new IllegalArgumentException("El monto del préstamo no puede ser nulo");
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (monto.compareTo(new BigDecimal("500000")) > 0) {
            throw new IllegalArgumentException("El monto máximo permitido es 500,000");
        }
    }

    private void validarPlazo(int plazoMeses) {
        if (plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo debe ser mayor a cero meses");
        }
        if (plazoMeses > 360) {
            throw new IllegalArgumentException("El plazo máximo permitido es 360 meses (30 años)");
        }
    }

    private void validarSolicitante(Solicitante solicitante) {
        if (solicitante == null) {
            throw new IllegalArgumentException("El solicitante no puede ser nulo");
        }
        if (solicitante.getNumeroDocumento() == null || solicitante.getNumeroDocumento().isBlank()) {
            throw new IllegalArgumentException("El número de documento es obligatorio");
        }
    }

    private void validarTasaInteres(BigDecimal interesTasa) {
        if (interesTasa == null) {
            throw new IllegalArgumentException("La tasa de interés no puede ser nula");
        }
        if (interesTasa.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (interesTasa.compareTo(new BigDecimal("1.0")) > 0) {
            throw new IllegalArgumentException("La tasa de interés no puede exceder el 100%");
        }
    }

    private void validarMotivoRechazo(String motivo) {
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo de rechazo es obligatorio");
        }
        if (motivo.length() < 10) {
            throw new IllegalArgumentException("El motivo de rechazo debe tener al menos 10 caracteres");
        }
    }
}

// === ARCHIVO: src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java ===
package com.banca.prestamos.infrastructure.adapter.in.web;

import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    private final PrestamoUseCase prestamoUseCase;

    public PrestamoController(PrestamoUseCase prestamoUseCase) {
        this.prestamoUseCase = prestamoUseCase;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> crearSolicitudPrestamo(
            @Valid @RequestBody SolicitudPrestamoRequest request) {
        Solicitante solicitante = Solicitante.crearSolicitante(
                request.nombre(),
                request.apellido(),
                request.tipoDocumento(),
                request.numeroDocumento(),
                request.email(),
                request.telefono()
        );

        Prestamo prestamo = prestamoUseCase.crearSolicitudPrestamo(
                solicitante,
                request.monto(),
                request.plazoMeses(),
                LocalDate.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapToResponse(prestamo));
    }

    @PostMapping("/{id}/aprobar")
    public ResponseEntity<PrestamoResponse> aprobarPrestamo(
            @PathVariable UUID id,
            @Valid @RequestBody AprobacionRequest request) {
        Prestamo prestamo = prestamoUseCase.aprobarPrestamo(
                id,
                LocalDate.now(),
                request.tasaInteres()
        );
        return ResponseEntity.ok(mapToResponse(prestamo));
    }

    @PostMapping("/{id}/rechazar")
    public ResponseEntity<PrestamoResponse> rechazarPrestamo(
            @PathVariable UUID id,
            @Valid @RequestBody RechazoRequest request) {
        Prestamo prestamo = prestamoUseCase.rechazarPrestamo(id, request.motivo());
        return ResponseEntity.ok(mapToResponse(prestamo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> obtenerDetallesPrestamo(@PathVariable UUID id) {
        Prestamo prestamo = prestamoUseCase.obtenerDetallesPrestamo(id);
        return ResponseEntity.ok(mapToResponse(prestamo));
    }

    private PrestamoResponse mapToResponse(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getSolicitante().getNombre(),
                prestamo.getSolicitante().getApellido(),
                prestamo.getMonto(),
                prestamo.getPlazoMeses(),
                prestamo.getFechaSolicitud().toString(),
                prestamo.getEstado().name(),
                prestamo.getFechaAprobacion() != null ? prestamo.getFechaAprobacion().toString() : null,
                prestamo.getInteresTasa() != null ? prestamo.getInteresTasa().toString() : null,
                prestamo.getMotivoRechazo()
        );
    }

    public record SolicitudPrestamoRequest(
            @NotBlank String nombre,
            @NotBlank String apellido,
            @NotBlank String tipoDocumento,
            @NotBlank String numeroDocumento,
            @NotBlank @Email String email,
            @NotBlank String telefono,
            @NotNull @Positive BigDecimal monto,
            @NotNull @Positive Integer plazoMeses
    ) {}

    public record AprobacionRequest(@NotNull @Positive BigDecimal tasaInteres) {}

    public record RechazoRequest(@NotBlank String motivo) {}

    public record PrestamoResponse(
            UUID id,
            String nombre,
            String apellido,
            BigDecimal monto,
            int plazoMeses,
            String fechaSolicitud,
            String estado,
            String fechaAprobacion,
            String interesTasa,
            String motivoRechazo
    ) {}
}

// === ARCHIVO: src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java ===
package com.banca.prestamos.infrastructure.adapter.out.persistence;


import com.banca.prestamos.domain.model.EstadoPrestamo;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestamos")
@Component
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String solicitanteNombre;

    @Column(nullable = false)
    private String solicitanteApellido;

    @Column(nullable = false)
    private String solicitanteTipoDocumento;

    @Column(nullable = false, unique = true)
    private String solicitanteNumeroDocumento;

    @Column(nullable = false)
    private String solicitanteEmail;

    @Column(nullable = false)
    private String solicitanteTelefono;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Column(nullable = false)
    private Integer plazoMeses;

    @Column(nullable = false)
    private LocalDate fechaSolicitud;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPrestamoEntity estado;

    private LocalDate fechaAprobacion;

    @Column(precision = 19, scale = 4)
    private BigDecimal interesTasa;

    private String motivoRechazo;

    public PrestamoEntity() {}

    public static PrestamoEntity fromDomain(Prestamo prestamo) {
        PrestamoEntity entity = new PrestamoEntity();
        entity.setId(prestamo.getId());
        entity.setSolicitanteNombre(prestamo.getSolicitante().getNombre());
        entity.setSolicitanteApellido(prestamo.getSolicitante().getApellido());
        entity.setSolicitanteTipoDocumento(prestamo.getSolicitante().getTipoDocumento());
        entity.setSolicitanteNumeroDocumento(prestamo.getSolicitante().getNumeroDocumento());
        entity.setSolicitanteEmail(prestamo.getSolicitante().getEmail());
        entity.setSolicitanteTelefono(prestamo.getSolicitante().getTelefono());
        entity.setMonto(prestamo.getMonto());
        entity.setPlazoMeses(prestamo.getPlazoMeses());
        entity.setFechaSolicitud(prestamo.getFechaSolicitud());
        entity.setEstado(EstadoPrestamoEntity.valueOf(prestamo.getEstado().name()));
        entity.setFechaAprobacion(prestamo.getFechaAprobacion());
        entity.setInteresTasa(prestamo.getInteresTasa());
        entity.setMotivoRechazo(prestamo.getMotivoRechazo());
        return entity;
    }

    public Prestamo toDomain() {
        Solicitante solicitante = Solicitante.reconstruir(
                this.id,
                this.solicitanteNombre,
                this.solicitanteApellido,
                this.solicitanteTipoDocumento,
                this.solicitanteNumeroDocumento,
                this.solicitanteEmail,
                this.solicitanteTelefono
        );

        Prestamo prestamo = Prestamo.reconstruir(
                this.id,
                solicitante,
                this.monto,
                this.plazoMeses,
                this.fechaSolicitud,
                this.estado.toDomain(),
                this.fechaAprobacion,
                this.interesTasa,
                this.motivoRechazo
        );

        return prestamo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSolicitanteNombre() {
        return solicitanteNombre;
    }

    public void setSolicitanteNombre(String solicitanteNombre) {
        this.solicitanteNombre = solicitanteNombre;
    }

    public String getSolicitanteApellido() {
        return solicitanteApellido;
    }

    public void setSolicitanteApellido(String solicitanteApellido) {
        this.solicitanteApellido = solicitanteApellido;
    }

    public String getSolicitanteTipoDocumento() {
        return solicitanteTipoDocumento;
    }

    public void setSolicitanteTipoDocumento(String solicitanteTipoDocumento) {
        this.solicitanteTipoDocumento = solicitanteTipoDocumento;
    }

    public String getSolicitanteNumeroDocumento() {
        return solicitanteNumeroDocumento;
    }

    public void setSolicitanteNumeroDocumento(String solicitanteNumeroDocumento) {
        this.solicitanteNumeroDocumento = solicitanteNumeroDocumento;
    }

    public String getSolicitanteEmail() {
        return solicitanteEmail;
    }

    public void setSolicitanteEmail(String solicitanteEmail) {
        this.solicitanteEmail = solicitanteEmail;
    }

    public String getSolicitanteTelefono() {
        return solicitanteTelefono;
    }

    public void setSolicitanteTelefono(String solicitanteTelefono) {
        this.solicitanteTelefono = solicitanteTelefono;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoPrestamoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamoEntity estado) {
        this.estado = estado;
    }

    public LocalDate getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDate fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public BigDecimal getInteresTasa() {
        return interesTasa;
    }

    public void setInteresTasa(BigDecimal interesTasa) {
        this.interesTasa = interesTasa;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public enum EstadoPrestamoEntity {
        SOLICITADO,
        APROBADO,
        RECHAZADO;

        public Prestamo.EstadoPrestamo toDomain() {
            return Prestamo.EstadoPrestamo.valueOf(this.name());
        }

        public static EstadoPrestamoEntity fromDomain(Prestamo.EstadoPrestamo estado) {
            return EstadoPrestamoEntity.valueOf(estado.name());
        }
    }
}

// === ARCHIVO: src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java ===
package com.banca.prestamos.infrastructure.adapter.out.external;



import com.banca.prestamos.domain.model.Monto;
import com.banca.prestamos.domain.model.Solicitante;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Component
public class RiesgoClient {

    private static final Logger logger = LoggerFactory.getLogger(RiesgoClient.class);

    private final RestTemplate restTemplate;
    private final String servicioRiesgoUrl;

    public RiesgoClient(
            RestTemplate restTemplate,
            @Value("${servicio.riesgo.url:http://localhost:8081/api/v1/riesgo}") String servicioRiesgoUrl) {
        this.restTemplate = restTemplate;
        this.servicioRiesgoUrl = servicioRiesgoUrl;
    }

    @CircuitBreaker(name = "riesgoService", fallbackMethod = "evaluarRiesgoFallback")
    @Retry(name = "riesgoServiceRetry")
    public EvaluacionRiesgoResponse evaluarRiesgo(UUID solicitanteId, BigDecimal monto) {
        logger.info("Evaluando riesgo para solicitante: {} con monto: {}", solicitanteId, monto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
                "solicitanteId", solicitanteId.toString(),
                "monto", monto
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            EvaluacionRiesgoResponse response = restTemplate.postForObject(
                    servicioRiesgoUrl + "/evaluar",
                    request,
                    EvaluacionRiesgoResponse.class
            );

            if (response == null) {
                logger.warn("Respuesta nula del servicio de riesgo para solicitante: {}", solicitanteId);
                return crearRespuestaDefault();
            }

            logger.info("Evaluación de riesgo completada. Nivel: {}, Score: {}",
                    response.nivelRiesgo(), response.score());
            return response;

        } catch (Exception e) {
            logger.error("Error al evaluar riesgo para solicitante: {}. Error: {}",
                    solicitanteId, e.getMessage());
            throw new RuntimeException("Error en servicio de evaluación de riesgo", e);
        }
    }

    private EvaluacionRiesgoResponse evaluarRiesgoFallback(UUID solicitanteId, BigDecimal monto, Throwable t) {
        logger.warn("Fallback activado para evaluacion de riesgo. Solicitante: {}, Monto: {}, Error: {}",
                solicitanteId, monto, t.getMessage());
        return crearRespuestaDefault();
    }

    private EvaluacionRiesgoResponse crearRespuestaDefault() {
        return new EvaluacionRiesgoResponse("MEDIO", 50, true, "Evaluación por defecto - servicio no disponible");
    }

    public boolean esAprobable(EvaluacionRiesgoResponse evaluacion) {
        return evaluacion.aprobable() &&
                (evaluacion.nivelRiesgo().equals("BAJO") ||
                 (evaluacion.nivelRiesgo().equals("MEDIO") && evaluacion.score() >= 60));
    }

    public record EvaluacionRiesgoResponse(
            String nivelRiesgo,
            Integer score,
            boolean aprobable,
            String mensaje
    ) {}
}

// === ARCHIVO: src/main/java/com/banca/prestamos/infrastructure/config/ResilienceConfig.java ===
package com.banca.prestamos.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(60))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .waitDurationInOpenState(Duration.ofSeconds(60))
                .slowCallRateThreshold(100)
                .slowCallDurationThreshold(Duration.ofSeconds(2))
                .build();
        
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public TimeLimiterRegistry timeLimiterRegistry() {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .cancelRunningFuture(true)
                .build();
        
        return TimeLimiterRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .retryExceptions(Exception.class)
                .build();
        
        return RetryRegistry.of(config);
    }

    @Bean
    public CircuitBreakerConfig riesgoCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .build();
    }

    @Bean
    public TimeLimiterConfig riesgoTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
    }
}

// === ARCHIVO: src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java ===
package com.banca.prestamos.bdd;



import com.banca.prestamos.domain.model.Monto;
import com.banca.prestamos.domain.model.EstadoPrestamo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.platform.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.jupiter.api.Disabled;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;

@Cucumber
@SelectPackages("com.banca.prestamos.bdd")
@SelectClasspathResource("features")
@ConfigurationParameters({
    @ConfigurationParameter(key = "plugin", value = "pretty"),
    @ConfigurationParameter(key = "glue", value = "com.banca.prestamos.bdd")
})
@Disabled("Superficie de práctica - implementar los steps según los escenarios BDD")
public class PrestamoSteps {

    private PrestamoUseCase prestamoUseCase;
    private Prestamo prestamoActual;
    private Solicitante solicitanteActual;
    private BigDecimal montoSolicitado;
    private int plazoMesesSolicitado;
    private LocalDate fechaSolicitud;
    private String resultadoOperacion;
    private Exception exceptionCapturada;

    @Given("un solicitante con nombre {string}, apellido {string}, documento {string} numero {string}")
    public void unSolicitanteConNombreApellidoDocumentoNumero(String nombre, String apellido, String tipoDoc, String numeroDoc) {
        solicitanteActual = Solicitante.crearSolicitante(nombre, apellido, tipoDoc, numeroDoc, "test@email.com", "123456789");
    }

    @Given("el solicitante tiene email {string} y telefono {string}")
    public void elSolicitanteTieneEmailYTelefono(String email, String telefono) {
        if (solicitanteActual != null) {
            solicitanteActual = Solicitante.crearSolicitante(
                solicitanteActual.getNombre(),
                solicitanteActual.getApellido(),
                solicitanteActual.getTipoDocumento(),
                solicitanteActual.getNumeroDocumento(),
                email,
                telefono
            );
        }
    }

    @When("solicita un prestamo de {double} pesos con plazo de {int} meses")
    public void solicitaUnPrestamoDePesosConPlazoDeMeses(Double monto, Integer plazo) {
        montoSolicitado = BigDecimal.valueOf(monto);
        plazoMesesSolicitado = plazo;
        fechaSolicitud = LocalDate.now();
        try {
            prestamoActual = prestamoUseCase.crearSolicitudPrestamo(
                solicitanteActual,
                montoSolicitado,
                plazoMesesSolicitado,
                fechaSolicitud
            );
            resultadoOperacion = "EXITO";
        } catch (Exception e) {
            exceptionCapturada = e;
            resultadoOperacion = "ERROR";
        }
    }

    @When("el sistema aprueba el prestamo con tasa de interes {double}")
    public void elSistemaApruebaElPrestamoConTasaDeInteres(Double tasaInteres) {
        if (prestamoActual != null) {
            try {
                prestamoActual = prestamoUseCase.aprobarPrestamo(
                    prestamoActual.getId(),
                    LocalDate.now(),
                    BigDecimal.valueOf(tasaInteres)
                );
                resultadoOperacion = "APROBADO";
            } catch (Exception e) {
                exceptionCapturada = e;
                resultadoOperacion = "ERROR";
            }
        }
    }

    @When("el sistema rechaza el prestamo con motivo {string}")
    public void elSistemaRechazaElPrestamoConMotivo(String motivo) {
        if (prestamoActual != null) {
            try {
                prestamoActual = prestamoUseCase.rechazarPrestamo(
                    prestamoActual.getId(),
                    motivo
                );
                resultadoOperacion = "RECHAZADO";
            } catch (Exception e) {
                exceptionCapturada = e;
                resultadoOperacion = "ERROR";
            }
        }
    }

    @Then("el prestamo debe estar en estado {string}")
    public void elPrestamoDebeEstarEnEstado(String estadoEsperado) {
        if (prestamoActual != null) {
            Prestamo.EstadoPrestamo estado = prestamoActual.getEstado();
            assert estado.name().equals(estadoEsperado) : "Estado esperado: " + estadoEsperado + ", actual: " + estado.name();
        }
    }

    @Then("el prestamo debe tener un identificador unico")
    public void elPrestamoDebeTenerUnIdentificadorUnico() {
        if (prestamoActual != null) {
            UUID id = prestamoActual.getId();
            assert id != null : "El préstamo debe tener un ID asignado";
        }
    }

    @Then("la solicitud debe ser exitosa")
    public void laSolicitudDebeSerExitosa() {
        assert "EXITO".equals(resultadoOperacion) : "La operación debería ser exitosa";
    }

    @Then("la aprobación debe ser exitosa")
    public void laAprobacionDebeSerExitosa() {
        assert "APROBADO".equals(resultadoOperacion) : "La aprobación debería ser exitosa";
    }

    @Then("el rechazo debe ser exitoso")
    public void elRechazoDebeSerExitoso() {
        assert "RECHAZADO".equals(resultadoOperacion) : "El rechazo debería ser exitoso";
    }

    @And("el monto del prestamo debe ser {double}")
    public void elMontoDelPrestamoDebeSer(Double montoEsperado) {
        if (prestamoActual != null) {
            BigDecimal monto = prestamoActual.getMonto();
            assert monto.compareTo(BigDecimal.valueOf(montoEsperado)) == 0 : 
                "Monto esperado: " + montoEsperado + ", actual: " + monto;
        }
    }

    @And("el plazo del prestamo debe ser {int} meses")
    public void elPlazoDelPrestamoDebeSerMeses(Integer plazoEsperado) {
        if (prestamoActual != null) {
            int plazo = prestamoActual.getPlazoMeses();
            assert plazo == plazoEsperado : "Plazo esperado: " + plazoEsperado + ", actual: " + plazo;
        }
    }

    @And("la tasa de interes debe ser {double}")
    public void laTasaDeInteresDebeSer(Double tasaEsperada) {
        if (prestamoActual != null && prestamoActual.getInteresTasa() != null) {
            BigDecimal tasa = prestamoActual.getInteresTasa();
            assert tasa.compareTo(BigDecimal.valueOf(tasaEsperada)) == 0 :
                "Tasa esperada: " + tasaEsperada + ", actual: " + tasa;
        }
    }

    @And("el motivo de rechazo debe ser {string}")
    public void elMotivoDeRechazoDebeSer(String motivoEsperado) {
        if (prestamoActual != null) {
            String motivo = prestamoActual.getMotivoRechazo();
            assert motivo != null && motivo.equals(motivoEsperado) :
                "Motivo esperado: " + motivoEsperado + ", actual: " + motivo;
        }
    }

    @And("la fecha de solicitud debe ser hoy")
    public void laFechaDeSolicitudDebeSerHoy() {
        if (prestamoActual != null) {
            LocalDate fecha = prestamoActual.getFechaSolicitud();
            assert fecha.equals(LocalDate.now()) : "La fecha de solicitud debe ser hoy";
        }
    }
}

// === ARCHIVO: src/test/resources/features/prestamo.feature ===
Feature: Gestión de Préstamos Personales

  Background:
    Given un solicitante con nombre "Juan", apellido "Pérez", documento "DNI" numero "12345678"

  Scenario: Solicitud de préstamo exitosa
    When solicita un prestamo de 50000.00 pesos con plazo de 12 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    And el prestamo debe tener un identificador unico
    And el monto del prestamo debe ser 50000.00
    And el plazo del prestamo debe ser 12 meses
    And la fecha de solicitud debe ser hoy

  Scenario: Aprobación de préstamo exitosa
    Given un solicitante con nombre "María", apellido "Gómez", documento "DNI" numero "87654321"
    And el solicitante tiene email "maria@test.com" y telefono "987654321"
    When solicita un prestamo de 100000.00 pesos con plazo de 24 meses
    And el sistema aprueba el prestamo con tasa de interes 0.15
    Then la aprobación debe ser exitosa
    And el prestamo debe estar en estado "APROBADO"
    And la tasa de interes debe ser 0.15

  Scenario: Rechazo de préstamo por riesgo crediticio
    Given un solicitante con nombre "Carlos", apellido "López", documento "DNI" numero "11223344"
    When solicita un prestamo de 200000.00 pesos con plazo de 36 meses
    And el sistema rechaza el prestamo con motivo "Riesgo crediticio elevado"
    Then el rechazo debe ser exitoso
    And el prestamo debe estar en estado "RECHAZADO"
    And el motivo de rechazo debe ser "Riesgo crediticio elevado"

  Scenario: Solicitud de préstamo con monto mínimo
    When solicita un prestamo de 1000.00 pesos con plazo de 3 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    And el monto del prestamo debe ser 1000.00
    And el plazo del prestamo debe ser 3 meses

  Scenario: Solicitud de préstamo con monto máximo
    When solicita un prestamo de 500000.00 pesos con plazo de 60 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    And el monto del prestamo debe ser 500000.00
    And el plazo del prestamo debe ser 60 meses

  Scenario: Múltiples solicitudes del mismo solicitante
    Given un solicitante con nombre "Ana", apellido "Martínez", documento "PASAPORTE" numero "AA123456"
    When solicita un prestamo de 30000.00 pesos con plazo de 6 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    When solicita un prestamo de 25000.00 pesos con plazo de 6 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe tener un identificador unico

// === ARCHIVO: src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java ===
package com.banca.prestamos.tdd;


import com.banca.prestamos.domain.model.EstadoPrestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import com.banca.prestamos.application.service.PrestamoService;
import com.banca.prestamos.infrastructure.adapter.out.persistence.PrestamoRepository;
import com.banca.prestamos.infrastructure.adapter.out.external.RiesgoClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas TDD del Servicio de Préstamos")
@org.junit.jupiter.api.Disabled("Superficie de práctica - implementar las pruebas según el ciclo TDD")
class PrestamoServiceTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @Mock
    private RiesgoClient riesgoClient;

    @InjectMocks
    private PrestamoService prestamoService;

    private Solicitante solicitanteValido;
    private Prestamo prestamoExistente;
    private UUID prestamoIdValido;

    @BeforeEach
    void setUp() {
        prestamoIdValido = UUID.randomUUID();
        solicitanteValido = Solicitante.crearSolicitante(
            "TEST_NOMBRE",
            "TEST_APELLIDO",
            "DNI",
            "12345678",
            "test@test.com",
            "123456789"
        );

        prestamoExistente = Prestamo.crearSolicitud(
            solicitanteValido,
            new BigDecimal("50000.00"),
            12,
            LocalDate.now()
        );
    }

    @Test
    @DisplayName("Crear solicitud de préstamo - caso exitoso")
    void testCrearSolicitudPrestamo_CasoExitoso() {
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(invocation -> {
            Prestamo p = invocation.getArgument(0);
            return p;
        });

        Prestamo resultado = prestamoService.crearSolicitudPrestamo(
            solicitanteValido,
            new BigDecimal("50000.00"),
            12,
            LocalDate.now()
        );

        assertNotNull(resultado, "El préstamo no debe ser null");
        assertEquals(Prestamo.EstadoPrestamo.SOLICITADO, resultado.getEstado(),
            "El estado debe ser SOLICITADO");
        assertEquals(new BigDecimal("50000.00"), resultado.getMonto(),
            "El monto debe coincidir");
        assertEquals(12, resultado.getPlazoMeses(),
            "El plazo debe coincidir");

        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Aprobar préstamo - caso exitoso")
    void testAprobarPrestamo_CasoExitoso() {
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoExistente));
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Prestamo resultado = prestamoService.aprobarPrestamo(
            prestamoIdValido,
            LocalDate.now(),
            new BigDecimal("0.12")
        );

        assertNotNull(resultado, "El préstamo aprobado no debe ser null");
        assertEquals(Prestamo.EstadoPrestamo.APROBADO, resultado.getEstado(),
            "El estado debe ser APROBADO");
        assertNotNull(resultado.getFechaAprobacion(),
            "La fecha de aprobación debe estar establecida");

        verify(prestamoRepository, times(1)).findById(prestamoIdValido);
        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Rechazar préstamo - caso exitoso")
    void testRechazarPrestamo_CasoExitoso() {
        String motivoRechazo = "Riesgo crediticio elevado";

        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoExistente));
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Prestamo resultado = prestamoService.rechazarPrestamo(
            prestamoIdValido,
            motivoRechazo
        );

        assertNotNull(resultado, "El préstamo rechazado no debe ser null");
        assertEquals(Prestamo.EstadoPrestamo.RECHAZADO, resultado.getEstado(),
            "El estado debe ser RECHAZADO");
        assertEquals(motivoRechazo, resultado.getMotivoRechazo(),
            "El motivo de rechazo debe coincidir");

        verify(prestamoRepository, times(1)).findById(prestamoIdValido);
        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Obtener detalles de préstamo existente")
    void testObtenerDetallesPrestamo_Existente() {
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoExistente));

        Prestamo resultado = prestamoService.obtenerDetallesPrestamo(prestamoIdValido);

        assertNotNull(resultado, "El préstamo encontrado no debe ser null");
        assertEquals(prestamoIdValido, resultado.getId(),
            "El ID debe coincidir");

        verify(prestamoRepository, times(1)).findById(prestamoIdValido);
    }

    @Test
    @DisplayName("Obtener detalles de préstamo inexistente debe lanzar excepción")
    void testObtenerDetallesPrestamo_Inexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(prestamoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            prestamoService.obtenerDetallesPrestamo(idInexistente);
        }, "Debe lanzar excepción cuando el préstamo no existe");

        verify(prestamoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Aprobar préstamo inexistente debe lanzar excepción")
    void testAprobarPrestamo_Inexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(prestamoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            prestamoService.aprobarPrestamo(idInexistente, LocalDate.now(), new BigDecimal("0.10"));
        }, "Debe lanzar excepción cuando el préstamo no existe");

        verify(prestamoRepository, times(1)).findById(idInexistente);
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Rechazar préstamo inexistente debe lanzar excepción")
    void testRechazarPrestamo_Inexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(prestamoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            prestamoService.rechazarPrestamo(idInexistente, "Motivo de prueba");
        }, "Debe lanzar excepción cuando el préstamo no existe");

        verify(prestamoRepository, times(1)).findById(idInexistente);
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Crear solicitud con monto cero debe fallar")
    void testCrearSolicitudPrestamo_MontoCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            prestamoService.crearSolicitudPrestamo(
                solicitanteValido,
                BigDecimal.ZERO,
                12,
                LocalDate.now()
            );
        }, "No debe permitir monto cero");
    }

    @Test
    @DisplayName("Crear solicitud con plazo cero debe fallar")
    void testCrearSolicitudPrestamo_PlazoCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            prestamoService.crearSolicitudPrestamo(
                solicitanteValido,
                new BigDecimal("50000.00"),
                0,
                LocalDate.now()
            );
        }, "No debe permitir plazo cero");
    }

    @Test
    @DisplayName("Aprobar préstamo ya aprobado debe fallar")
    void testAprobarPrestamo_YAaprobado() {
        Prestamo prestamoYaAprobado = prestamoExistente.aprobar(LocalDate.now(), new BigDecimal("0.10"));
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoYaAprobado));

        assertThrows(IllegalStateException.class, () -> {
            prestamoService.aprobarPrestamo(prestamoIdValido, LocalDate.now(), new BigDecimal("0.12"));
        }, "No debe permitir aprobar un préstamo ya aprobado");
    }

    @Test
    @DisplayName("Rechazar préstamo ya rechazado debe fallar")
    void testRechazarPrestamo_YARechazado() {
        Prestamo prestamoYaRechazado = prestamoExistente.rechazar("Motivo original");
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoYaRechazado));

        assertThrows(IllegalStateException.class, () -> {
            prestamoService.rechazarPrestamo(prestamoIdValido, "Nuevo motivo");
        }, "No debe permitir rechazar un préstamo ya rechazado");
    }
}

// === ARCHIVO: src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java ===
package com.banca.prestamos.tdd;


import com.banca.prestamos.domain.model.EstadoPrestamo;
import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import com.banca.prestamos.infrastructure.adapter.in.web.PrestamoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas TDD del PrestamoController")
class PrestamoControllerTest {

    @Mock
    private PrestamoUseCase prestamoUseCase;

    @InjectMocks
    private PrestamoController prestamoController;

    private UUID prestamoId;
    private Solicitante solicitanteValido;
    private Prestamo prestamoPendiente;
    private Prestamo prestamoAprobado;

    @BeforeEach
    void setUp() {
        prestamoId = UUID.randomUUID();
        solicitanteValido = Solicitante.crearSolicitante(
            "Juan",
            "Pérez",
            "DNI",
            "12345678",
            "juan.perez@email.com",
            "+5491134567890"
        );
    }

    @Test
    @DisplayName("Crear solicitud de préstamo retorna código 201 cuando es exitoso")
    void testCrearSolicitudPrestamo_Retorna201_CuandoExitoso() {
        // Given
        BigDecimal monto = new BigDecimal("50000");
        int plazoMeses = 12;
        LocalDate fechaSolicitud = LocalDate.now();
        
       Prestamo prestamoCreado = Prestamo.crearSolicitud(solicitanteValido, monto, plazoMeses, fechaSolicitud);
        when(prestamoUseCase.crearSolicitudPrestamo(any(Solicitante.class), eq(monto), eq(plazoMeses), eq(fechaSolicitud)))
            .thenReturn(prestamoCreado);

        // When
        ResponseEntity<Prestamo> response = prestamoController.crearSolicitud(
            solicitanteValido, monto, plazoMeses, fechaSolicitud
        );

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(monto, response.getBody().getMonto());
        assertEquals(plazoMeses, response.getBody().getPlazoMeses());
    }

    @Test
    @DisplayName("Aprobar préstamo retorna código 200 cuando es exitoso")
    void testAprobarPrestamo_Retorna200_CuandoExitoso() {
        // Given
        BigDecimal tasaInteres = new BigDecimal("0.25");
        LocalDate fechaAprobacion = LocalDate.now();
        
        prestamoAprobado = Prestamo.crearSolicitud(solicitanteValido, new BigDecimal("50000"), 12, LocalDate.now())
            .aprobar(fechaAprobacion, tasaInteres);
        
        when(prestamoUseCase.aprobarPrestamo(eq(prestamoId), eq(fechaAprobacion), eq(tasaInteres)))
            .thenReturn(prestamoAprobado);

        // When
        ResponseEntity<Prestamo> response = prestamoController.aprobarPrestamo(
            prestamoId.toString(), fechaAprobacion, tasaInteres
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Prestamo.EstadoPrestamo.APROBADO, response.getBody().getEstado());
    }

    @Test
    @DisplayName("Rechazar préstamo retorna código 200 cuando es exitoso")
    void testRechazarPrestamo_Retorna200_CuandoExitoso() {
        // Given
        String motivoRechazo = "Perfil de riesgo no autorizado";
        LocalDate fechaRechazo = LocalDate.now();
        
        prestamoPendiente = Prestamo.crearSolicitud(solicitanteValido, new BigDecimal("50000"), 12, LocalDate.now());
        Prestamo prestamoRechazado = prestamoPendiente.rechazar(motivoRechazo);
        
        when(prestamoUseCase.rechazarPrestamo(eq(prestamoId), eq(motivoRechazo)))
            .thenReturn(prestamoRechazado);

        // When
        ResponseEntity<Prestamo> response = prestamoController.rechazarPrestamo(
            prestamoId.toString(), motivoRechazo
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Prestamo.EstadoPrestamo.RECHAZADO, response.getBody().getEstado());
    }

    @Test
    @DisplayName("Obtener detalles del préstamo retorna código 200 cuando existe")
    void testObtenerDetallesPrestamo_Retorna200_CuandoExiste() {
        // Given
        prestamoPendiente = Prestamo.crearSolicitud(solicitanteValido, new BigDecimal("50000"), 12, LocalDate.now());
        when(prestamoUseCase.obtenerDetallesPrestamo(eq(prestamoId))).thenReturn(prestamoPendiente);

        // When
        ResponseEntity<Prestamo> response = prestamoController.obtenerPrestamo(prestamoId.toString());

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(prestamoId, response.getBody().getId());
    }

    @Test
    @DisplayName("Obtener detalles del préstamo retorna código 404 cuando no existe")
    void testObtenerDetallesPrestamo_Retorna404_CuandoNoExiste() {
        // Given
        UUID prestamoIdInexistente = UUID.randomUUID();
        when(prestamoUseCase.obtenerDetallesPrestamo(eq(prestamoIdInexistente)))
            .thenReturn(null);

        // When
        ResponseEntity<Prestamo> response = prestamoController.obtenerPrestamo(prestamoIdInexistente.toString());

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

// === ARCHIVO: scripts/build.sh ===
#!/bin/bash

set -e

echo "=========================================="
echo " Build Script - Préstamos Banca"
echo "=========================================="

PROJECT_VERSION="0.0.1-SNAPSHOT"
IMAGE_NAME="banca/prestamos"
IMAGE_TAG="latest"

echo ""
echo "[1/3] Compilando proyecto Maven..."
mvn clean package -DskipTests -q
if [ $? -eq 0 ]; then
    echo "✓ Compilación Maven exitosa"
else
    echo "✗ Error en compilación Maven"
    exit 1
fi

echo ""
echo "[2/3] Verificando que el artefacto existe..."
ARTIFACT_PATH="target/prestamos-${PROJECT_VERSION}.jar"
if [ -f "$ARTIFACT_PATH" ]; then
    echo "✓ Artefacto encontrado: $ARTIFACT_PATH"
else
    echo "✗ Artefacto no encontrado: $ARTIFACT_PATH"
    exit 1
fi

echo ""
echo "[3/3] Construyendo imagen Docker..."
docker build -t "${IMAGE_NAME}:${IMAGE_TAG}" .
if [ $? -eq 0 ]; then
    echo "✓ Imagen Docker construida exitosamente"
    docker images | grep "${IMAGE_NAME}"
else
    echo "✗ Error al construir imagen Docker"
    exit 1
fi

echo ""
echo "=========================================="
echo " Build completado exitosamente"
echo " Imagen: ${IMAGE_NAME}:${IMAGE_TAG}"
echo "=========================================="

// === ARCHIVO: scripts/healthcheck.sh ===
#!/bin/bash

set -e

CONTAINER_NAME="prestamos-banca"
HEALTH_ENDPOINT="http://localhost:8080/actuator/health"
MAX_RETRIES=30
RETRY_INTERVAL=2

echo "=========================================="
echo " Healthcheck - Préstamos Banca"
echo "=========================================="

echo ""
echo "[1/3] Verificando que el contenedor esté corriendo..."
if docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
    echo "✓ Contenedor '${CONTAINER_NAME}' está corriendo"
else
    echo "✗ Contenedor '${CONTAINER_NAME}' no está corriendo"
    echo "  Iniciando contenedor..."
    docker run -d --name "${CONTAINER_NAME}" -p 8080:8080 "banca/prestamos:latest"
    echo "✓ Contenedor iniciado"
fi

echo ""
echo "[2/3] Esperando a que la aplicación esté disponible..."
RETRY_COUNT=0
while [ $RETRY_COUNT -lt $MAX_RETRIES ]; do
    if curl -s -f "${HEALTH_ENDPOINT}" > /dev/null 2>&1; then
        echo "✓ Aplicación respondiendo en endpoint de salud"
        break
    fi
    RETRY_COUNT=$((RETRY_COUNT + 1))
    echo "  Intento ${RETRY_COUNT}/${MAX_RETRIES}..."
    sleep $RETRY_INTERVAL
done

if [ $RETRY_COUNT -eq $MAX_RETRIES ]; then
    echo "✗ La aplicación no respondió después de ${MAX_RETRIES} intentos"
    docker logs "${CONTAINER_NAME}"
    exit 1
fi

echo ""
echo "[3/3] Verificando estado de salud completo..."
HEALTH_RESPONSE=$(curl -s "${HEALTH_ENDPOINT}")
HEALTH_STATUS=$(echo "$HEALTH_RESPONSE" | grep -o '"status":"[^"]*"' | cut -d'"' -f4)

if [ "$HEALTH_STATUS" = "UP" ]; then
    echo "✓ Estado de salud: UP"
    echo "  Respuesta: $HEALTH_RESPONSE"
else
    echo "✗ Estado de salud: $HEALTH_STATUS"
    echo "  Respuesta: $HEALTH_RESPONSE"
    exit 1
fi

echo ""
echo "=========================================="
echo " Healthcheck completado exitosamente"
echo " La aplicación está saludable"
echo "=========================================="

// === ARCHIVO: Dockerfile ===
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN apk add --no-cache maven && mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

// === ARCHIVO: azure-pipelines.yml ===
trigger:
  branches:
    include:
      - main
      - develop
  paths:
    exclude:
      - '*.md'
      - 'docs/**'

pr:
  branches:
    include:
      - main
      - develop

variables:
  mavenCacheFolder: $(Pipeline.Workspace)/.m2
  dockerRegistryServiceConnection: 'azure-container-registry'
  dockerImageName: 'prestamos-app'
  dockerImageTag: '$(Build.BuildId)'
  sonarOrganization: 'banca-org'
  sonarProjectKey: 'com.banca:prestamos'
  javaVersion: '21'

stages:
  - stage: Build
    displayName: 'Compilar proyecto'
    jobs:
      - job: MavenBuild
        displayName: 'Ejecutar Maven Build'
        pool:
          vmImage: 'ubuntu-latest'
        steps:
          - task: Cache@2
            displayName: 'Cache de dependencias Maven'
            inputs:
              key: 'maven | "$(Agent.OS)" | pom.xml'
              path: $(mavenCacheFolder)
              cacheHitVar: 'CACHE_RESTORED'
          - task: Maven@3
            displayName: 'Maven build'
            inputs:
              mavenPomFile: 'pom.xml'
              mavenOptions: '-Xmx2048m'
              javaHomeOption: 'JDKVersion'
              jdkVersionOption: $(javaVersion)
              mavenAuthenticateFeed: false
          - task: PublishPipelineArtifact@1
            displayName: 'Publicar artefacto JAR'
            inputs:
              targetPath: 'target/prestamos-0.0.1-SNAPSHOT.jar'
              artifact: 'prestamos-jar'
              publishLocation: 'pipeline'

  - stage: Test
    displayName: 'Ejecutar pruebas'
    dependsOn: Build
    jobs:
      - job: MavenTest
        displayName: 'Ejecutar pruebas unitarias y BDD'
        pool:
          vmImage: 'ubuntu-latest'
        steps:
          - task: Maven@3
            displayName: 'Ejecutar tests'
            inputs:
              mavenPomFile: 'pom.xml'
              goals: 'test'
              mavenOptions: '-Xmx2048m'
              javaHomeOption: 'JDKVersion'
              jdkVersionOption: $(javaVersion)
              publishJUnitResults: true
              testResultsFiles: '**/surefire-reports/*.xml'
          - task: PublishPipelineArtifact@1
            displayName: 'Publicar informes de tests'
            inputs:
              targetPath: 'target/surefire-reports'
              artifact: 'test-reports'
              publishLocation: 'pipeline'

  - stage: StaticAnalysis
    displayName: 'Análisis estático'
    dependsOn: Test
    jobs:
      - job: SonarCloudAnalysis
        displayName: 'Análisis SonarCloud'
        pool:
          vmImage: 'ubuntu-latest'
        steps:
          - task: SonarCloudPrepare@1
            displayName: 'Preparar SonarCloud'
            inputs:
              SonarCloud: 'sonar-cloud-connection'
              organization: $(sonarOrganization)
              scannerMode: 'Other'
              extraProperties: |
                sonar.projectKey=$(sonarProjectKey)
                sonar.java.source=$(javaVersion)
                sonar.sourceEncoding=UTF-8
                sonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
          - task: Maven@3
            displayName: 'Ejecutar análisis Sonar'
            inputs:
              mavenPomFile: 'pom.xml'
              goals: 'verify sonar:sonar'
              mavenOptions: '-Xmx2048m'
              javaHomeOption: 'JDKVersion'
              jdkVersionOption: $(javaVersion)
          - task: SonarCloudPublish@1
            displayName: 'Publicar resultados SonarCloud'
            inputs:
              pollingTimeoutSec: '300'

  - stage: SecurityScan
    displayName: 'Escaneo de seguridad'
    dependsOn: StaticAnalysis
    jobs:
      - job: DependencyCheck
        displayName: 'OWASP Dependency Check'
        pool:
          vmImage: 'ubuntu-latest'
        steps:
          - task: Maven@3
            displayName: 'Dependency Check'
            inputs:
              mavenPomFile: 'pom.xml'
              goals: 'org.owasp:dependency-check-maven:check'
              mavenOptions: '-Xmx2048m'
              javaHomeOption: 'JDKVersion'
              jdkVersionOption: $(javaVersion)
          - task: PublishPipelineArtifact@1
            displayName: 'Publicar informe Dependency Check'
            inputs:
              targetPath: 'target/dependency-check-report.html'
              artifact: 'security-report'
              publishLocation: 'pipeline'

  - stage: BuildDocker
    displayName: 'Construir imagen Docker'
    dependsOn: SecurityScan
    condition: succeeded()
    jobs:
      - job: DockerBuild
        displayName: 'Build y push imagen Docker'
        pool:
          vmImage: 'ubuntu-latest'
        steps:
          - task: Docker@2
            displayName: 'Login ACR'
            inputs:
              containerRegistry: $(dockerRegistryServiceConnection)
              command: 'login'
          - task: Docker@2
            displayName: 'Build imagen'
            inputs:
              command: 'build'
              dockerfile: '$(Build.SourcesDirectory)/Dockerfile'
              tags: |
                $(dockerImageTag)
                latest
              imageName: $(dockerImageName)
          - task: Docker@2
            displayName: 'Push imagen'
            inputs:
              command: 'push'
              containerRegistry: $(dockerRegistryServiceConnection)
              imageName: $(dockerImageName)
              tags: |
                $(dockerImageTag)
                latest

  - stage: Deploy
    displayName: 'Desplegar a entorno'
    dependsOn: BuildDocker
    condition: succeeded()
    jobs:
      - deployment: DeployDev
        displayName: 'Desplegar a Development'
        environment: 'prestamos-dev'
        pool:
          vmImage: 'ubuntu-latest'
        strategy:
          runOnce:
            deploy:
              steps:
                - task: AzureCLI@2
                  displayName: 'Terraform init dev'
                  inputs:
                    azureSubscription: 'azure-service-connection'
                    scriptType: 'bash'
                    scriptLocation: 'inlineScript'
                    inlineScript: |
                      cd terraform/environments/dev
                      terraform init -backend=false
                - task: AzureCLI@2
                  displayName: 'Terraform apply dev'
                  inputs:
                    azureSubscription: 'azure-service-connection'
                    scriptType: 'bash'
                    scriptLocation: 'inlineScript'
                    inlineScript: |
                      cd terraform/environments/dev
                      terraform apply -auto-approve
                - task: AzureCLI@2
                  displayName: 'Healthcheck'
                  inputs:
                    azureSubscription: 'azure-service-connection'
                    scriptType: 'bash'
                    scriptLocation: 'inlineScript'
                    inlineScript: |
                      sleep 30
                      curl -f https://prestamos-dev.banca.com/actuator/health || exit 1

// === ARCHIVO: sonar-project.properties ===
sonar.projectKey=com.banca:prestamos
sonar.projectName=prestamos
sonar.projectVersion=0.0.1-SNAPSHOT

sonar.sources=src/main/java
sonar.tests=src/test/java

sonar.java.source=21
sonar.java.target=21
sonar.sourceEncoding=UTF-8

sonar.coveragePlugin=jacoco
sonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml

sonar.exclusions=**/target/**,**/*.jar,**/dist/**,**/node_modules/**
sonar.test.exclusions=**/target/**

sonar.cpd.exclusions=**/target/**

sonar.issuesService.url=https://sonarcloud.io
sonar.issuesService.token=${SONAR_TOKEN}

sonar.links.homepage=https://github.com/banca/prestamos
sonar.links.scm=github.com/banca/prestamos
sonar.links.ci=https://dev.azure.com/banca-org/pramos/_build

sonar.codeCoveragePlugin=jacoco
sonar.scala.coveragePlugin=scoverage

sonar.analysis.mode=publish
sonar.github.repository=banca/prestamos
sonar.github.pullRequest=${PULL_REQUEST}
sonar.github.oauth=${GITHUB_TOKEN}

// === ARCHIVO: README.md ===
# Servicio de Gestión de Préstamos Personales

## Descripción del Proyecto

Microservicio desarrollado con Spring Boot 3.4 y Java 21 para la gestión de préstamos personales en una entidad bancaria. El proyecto implementa las metodologías DDD (Domain-Driven Design), BDD (Behavior-Driven Development) y TDD (Test-Driven Development) como parte de la cultura de desarrollo del equipo.

### Características Principales

- **Gestión de Préstamos**: Creación, aprobación y rechazo de solicitudes de préstamo
- **Evaluación de Riesgo**: Integración con servicio externo de evaluación de riesgos con resiliencia
- **Alta Disponibilidad**: Manejo de fallos con circuit breaker y timeouts configurables
- **APIs REST**: Endpoints RESTful para interacción con el sistema
- **Persistencia**: Base de datos H2 para desarrollo, compatible con PostgreSQL para producción

## Tecnologías y Versiones

| Tecnología | Versión |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.4.0 |
| Maven | 3.9+ |
| H2 Database | - |
| Resilience4j | 2.1.0 |
| Cucumber | 7.15.0 |
| JUnit 5 | 5.10.2 |

## Estructura del Proyecto

```
prestamos/
├── src/
│   ├── main/
│   │   ├── java/com/banca/prestamos/
│   │   │   ├── PrestamosApplication.java
│   │   │   ├── domain/
│   │   │   │   └── model/
│   │   │   │       ├── Prestamo.java
│   │   │   │       ├── Solicitante.java
│   │   │   │       └── ValorObjeto.java
│   │   │   ├── application/
│   │   │   │   ├── port/in/
│   │   │   │   │   └── PrestamoUseCase.java
│   │   │   │   └── service/
│   │   │   │       └── PrestamoService.java
│   │   │   └── infrastructure/
│   │   │       ├── adapter/in/web/
│   │   │       │   └── PrestamoController.java
│   │   │       ├── adapter/out/
│   │   │       │   ├── persistence/
│   │   │       │   │   ├── PrestamoEntity.java
│   │   │       │   │   └── PrestamoRepository.java
│   │   │       │   └── external/
│   │   │       │       └── RiesgoClient.java
│   │   │       └── config/
│   │   │           └── ResilienceConfig.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/com/banca/prestamos/
│       │   ├── bdd/
│       │   │   └── PrestamoSteps.java
│       │   └── tdd/
│       │       ├── PrestamoServiceTest.java
│       │       └── PrestamoControllerTest.java
│       └── resources/
│           └── features/
│               └── prestamo.feature
├── scripts/
│   ├── build.sh
│   └── healthcheck.sh
├── terraform/
│   ├── environments/
│   │   └── dev/
│   │       └── terraform.tfvars
│   ├── main.tf
│   ├── variables.tf
│   └── outputs.tf
├── Dockerfile
├── azure-pipelines.yml
├── sonar-project.properties
└── pom.xml
```

## Arquitectura Hexagonal (Ports & Adapters)

El proyecto sigue el patrón de arquitectura hexagonal para mantener el dominio aislado de la infraestructura:

### Capas

1. **Domain (domain/model)**: Entidades, agregados y objetos de valor del negocio
   - `Prestamo`: Agregado raíz que representa un préstamo
   - `Solicitante`: Entidad que representa al solicitante del préstamo

2. **Application (application)**: Casos de uso y servicios de aplicación
   - `PrestamoUseCase`: Puerto de entrada con la lógica de negocio
   - `PrestamoService`: Implementación del caso de uso

3. **Infrastructure (infrastructure)**: Adaptadores externos
   - `PrestamoController`: Adaptador de entrada (API REST)
   - `PrestamoRepository`: Adaptador de salida (persistencia)
   - `RiesgoClient`: Adaptador externo (servicio de riesgo)

### Metodologías Implementadas

#### DDD (Domain-Driven Design)

- **Agregados**: Prestamo como agregado raíz con invariantes protegidas
- **Entidades**: Solicitante con identidad propia
- **Objetos de Valor**: EstadoPrestamo como enum con comportamiento
- **Repositorios**: Abstracción de la persistencia mediante puertos

#### BDD (Behavior-Driven Development)

- **Escenarios en Gherkin**: Features escritos en lenguaje de negocio
- **Cucumber**: Framework para ejecutar pruebas BDD
- **Pasos reutilizables**: Step definitions organizados por contexto

#### TDD (Test-Driven Development)

- **Ciclo Red-Green-Refactor**: Pruebas antes del código
- **JUnit 5**: Framework de testing con extensiones
- **Mockito**: Mocking de dependencias para pruebas unitarias

## Configuración del Entorno

### Prerrequisitos

- Java Development Kit (JDK) 21
- Maven 3.9 o superior
- Docker (opcional, para contenedores)
- Terraform 1.5+ (para infraestructura)

### Variables de Entorno

```bash
export SPRING_PROFILES_ACTIVE=dev
export DATABASE_URL=jdbc:h2:mem:prestamosdb
```

### Configuración de Base de Datos

El proyecto usa H2 en memoria para desarrollo. La configuración se encuentra en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:prestamosdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

## Compilación y Ejecución

### Compilar el Proyecto

```bash
mvn clean compile
```

### Ejecutar Tests

```bash
# Todos los tests
mvn test

# Solo tests unitarios (TDD)
mvn test -Dtest=*Test

# Solo tests BDD
mvn test -Dtest=*Steps
```

### Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

### Endpoints Disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/prestamos | Crear solicitud de préstamo |
| GET | /api/prestamos/{id} | Obtener detalles de un préstamo |
| POST | /api/prestamos/{id}/aprobar | Aprobar un préstamo |
| POST | /api/prestamos/{id}/rechazar | Rechazar un préstamo |
| GET | /actuator/health | Health check |

### Health Check

```bash
./scripts/healthcheck.sh
```

## Construcción de Imagen Docker

```bash
docker build -t prestamos:local .
```

## Infraestructura como Código

### Inicializar Terraform

```bash
cd terraform
terraform init
```

### Validar Configuración

```bash
terraform validate
```

### Plan de Infraestructura

```bash
terraform plan -var-file=environments/dev/terraform.tfvars
```

## Pipeline CI/CD

El proyecto incluye un pipeline Azure DevOps configurado en `azure-pipelines.yml` que ejecuta:

1. **Build**: Compilación del código fuente
2. **Test**: Ejecución de pruebas unitarias y BDD
3. **Analysis**: Análisis estático con SonarCloud
4. **Package**: Empaquetado de la aplicación
5. **Deploy**: Despliegue a ambiente destino

## Métricas de Calidad

- **Cobertura de Código**: Objetivo mínimo 80%
- **Quality Gate**: Bloquea despliegue si no cumple estándares
- **Análisis Estático**: SonarCloud para detección de code smells

## Contribuir al Proyecto

1. Crear una rama desde `main`
2. Implementar la funcionalidad siguiendo TDD
3. Escribir escenarios BDD para el comportamiento esperado
4. Ejecutar todos los tests localmente
5. Crear Pull Request para revisión

## Licencia

Proprietario - Banca S.A.
```
