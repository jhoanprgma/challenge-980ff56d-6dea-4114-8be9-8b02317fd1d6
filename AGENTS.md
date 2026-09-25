# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de metodologías BDD, TDD y DDD en un proyecto de banca**.

| | |
|---|---|
| Tema | Técnicas de desarrollo |
| Nivel | senior-l2 |
| Chapter | DevSecOps |
| Especialidad | DevSecOps |
| Stack | Java / Spring Boot 3.4 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 2 semanas |

## Receta del stack

Esqueleto obligatorio:

- `azure-pipelines.yml o .github/workflows/*.yml con stages reales`
- `Dockerfile multi-stage`
- `.dockerignore`
- `terraform/ con main.tf, variables.tf y outputs.tf`
- `terraform/environments/{env}/terraform.tfvars`
- `scripts/ con los scripts de build y healthcheck`
- `sonar-project.properties`

Trampas conocidas:

- `required_version` de Terraform va como RANGO (`>= 1.5`), nunca exacto: pineado, el proyecto no corre con otra version instalada.
- Los providers tambien con restriccion flexible (`~> 5.0`).
- El Dockerfile multi-stage necesita que la etapa final copie el artefacto de la etapa de build, no el codigo fuente.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.4.0
- org.springframework.boot:spring-boot-starter-data-jpa 3.4.0
- org.springframework.boot:spring-boot-starter-actuator 3.4.0
- io.cucumber:cucumber-java 7.15.0
- io.cucumber:cucumber-junit 7.15.0
- org.junit.jupiter:junit-jupiter-api 5.10.2
- org.mockito:mockito-core 5.11.0
- io.github.resilience4j:resilience4j-spring-boot2 2.1.0
- org.mapstruct:mapstruct 1.5.5.Final
- org.projectlombok:lombok 1.18.30
- hashicorp/azurerm ~> 3.0
- hashicorp/aws ~> 5.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `docker build -t reto:local . && terraform -chdir=terraform init -backend=false && terraform -chdir=terraform validate` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `docker build -t reto:local . && terraform -chdir=terraform init -backend=false && terraform -chdir=terraform validate` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración y definición del dominio**: Modelo de dominio documentado con agregados, entidades y valor-objetos.
- **Fase 2 — Implementación de pruebas con BDD**: Pruebas BDD documentadas y ejecutables para los casos de uso principales.
- **Fase 3 — Implementación de pruebas unitarias con TDD**: Pruebas unitarias TDD documentadas y ejecutables para las funcionalidades principales.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `Dockerfile` — El topic pide contenedores/orquestacion: este archivo es el ejercicio, no scaffolding.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/features/prestamo.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (60)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `Solicitante.getNombre`
      Se invoca `getNombre` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `PrestamoRepository.save`
      Se invoca `save` sobre `PrestamoRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/application/service/PrestamoService.java` — `Solicitante.getNumeroDocumento`
      Se invoca `getNumeroDocumento` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.nombre`
      Se invoca `nombre` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.apellido`
      Se invoca `apellido` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.tipoDocumento`
      Se invoca `tipoDocumento` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.numeroDocumento`
      Se invoca `numeroDocumento` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.email`
      Se invoca `email` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.telefono`
      Se invoca `telefono` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.monto`
      Se invoca `monto` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.plazoMeses`
      Se invoca `plazoMeses` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.tasaInteres`
      Se invoca `tasaInteres` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `RechazoRequest.motivo`
      Se invoca `motivo` sobre `RechazoRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getSolicitante`
      Se invoca `getSolicitante` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getMonto`
      Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getFechaSolicitud`
      Se invoca `getFechaSolicitud` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getEstado`
      Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getFechaAprobacion`
      Se invoca `getFechaAprobacion` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getInteresTasa`
      Se invoca `getInteresTasa` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java` — `Prestamo.getMotivoRechazo`
      Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getSolicitante`
      Se invoca `getSolicitante` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getMonto`
      Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getFechaSolicitud`
      Se invoca `getFechaSolicitud` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getEstado`
      Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getFechaAprobacion`
      Se invoca `getFechaAprobacion` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getInteresTasa`
      Se invoca `getInteresTasa` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `Prestamo.getMotivoRechazo`
      Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `EstadoPrestamo.toDomain`
      Se invoca `toDomain` sobre `EstadoPrestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java` — `EstadoPrestamo.name`
      Se invoca `name` sobre `EstadoPrestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `EvaluacionRiesgoResponse.nivelRiesgo`
      Se invoca `nivelRiesgo` sobre `EvaluacionRiesgoResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `EvaluacionRiesgoResponse.score`
      Se invoca `score` sobre `EvaluacionRiesgoResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java` — `EvaluacionRiesgoResponse.aprobable`
      Se invoca `aprobable` sobre `EvaluacionRiesgoResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getNombre`
      Se invoca `getNombre` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getApellido`
      Se invoca `getApellido` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getTipoDocumento`
      Se invoca `getTipoDocumento` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Solicitante.getNumeroDocumento`
      Se invoca `getNumeroDocumento` sobre `Solicitante`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getEstado`
      Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `EstadoPrestamo.name`
      Se invoca `name` sobre `EstadoPrestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getMonto`
      Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getInteresTasa`
      Se invoca `getInteresTasa` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getMotivoRechazo`
      Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java` — `Prestamo.getFechaSolicitud`
      Se invoca `getFechaSolicitud` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `PrestamoRepository.save`
      Se invoca `save` sobre `PrestamoRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getEstado`
      Se invoca `getEstado` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getMonto`
      Se invoca `getMonto` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getFechaAprobacion`
      Se invoca `getFechaAprobacion` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getMotivoRechazo`
      Se invoca `getMotivoRechazo` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java` — `Prestamo.getId`
      Se invoca `getId` sobre `Prestamo`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java` — `PrestamoController.crearSolicitud`
      Se invoca `crearSolicitud` sobre `PrestamoController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java` — `PrestamoController.obtenerPrestamo`
      Se invoca `obtenerPrestamo` sobre `PrestamoController`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (27)

- `providers.tf`
- `pom.xml`
- `src/main/java/com/banca/prestamos/PrestamosApplication.java`
- `src/main/java/com/banca/prestamos/application/port/in/PrestamoUseCase.java`
- `src/main/java/com/banca/prestamos/domain/model/Prestamo.java`
- `src/main/java/com/banca/prestamos/domain/model/Solicitante.java`
- `terraform/variables.tf`
- `terraform/main.tf`
- `terraform/outputs.tf`
- `terraform/environments/dev/terraform.tfvars`
- `src/main/java/com/banca/prestamos/domain/model/ValorObjeto.java`
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoRepository.java`
- `src/main/java/com/banca/prestamos/application/service/PrestamoService.java`
- `src/main/java/com/banca/prestamos/infrastructure/adapter/in/web/PrestamoController.java`
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/persistence/PrestamoEntity.java`
- `src/main/java/com/banca/prestamos/infrastructure/adapter/out/external/RiesgoClient.java`
- `src/main/java/com/banca/prestamos/infrastructure/config/ResilienceConfig.java`
- `src/test/java/com/banca/prestamos/bdd/PrestamoSteps.java`
- `src/test/resources/features/prestamo.feature`
- `src/test/java/com/banca/prestamos/tdd/PrestamoServiceTest.java`
- `src/test/java/com/banca/prestamos/tdd/PrestamoControllerTest.java`
- `scripts/build.sh`
- `scripts/healthcheck.sh`
- `Dockerfile`
- `azure-pipelines.yml`
- `sonar-project.properties`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/banca/prestamos`
- `src/main/java/com/banca/prestamos/application`
- `src/main/java/com/banca/prestamos/domain`
- `src/main/java/com/banca/prestamos/infrastructure`
- `src/test/java/com/banca/prestamos/bdd`
- `src/test/java/com/banca/prestamos/tdd`
- `src/test/resources/features`
- `terraform`
- `terraform/environments/dev`
- `scripts`

## Verificacion

```bash
docker build -t reto:local . && terraform -chdir=terraform init -backend=false && terraform -chdir=terraform validate
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter DevSecOps, Especialidad Ingeniería, Tecnología DevSecOps, Senior
- Brecha que el reto ataca: Conoce, aplica y enseña al equipo las siguientes metodologías BDD, TDD y DDD. Desarrollador experimentado en equipo distribuido buscando dominar prácticas modernas de desarrollo que mejoren la calidad y estructura del código.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
