# Implementación de metodologías BDD, TDD y DDD en un proyecto de banca

Como desarrollador senior en un equipo distribuido de una empresa de banca, debes implementar y enseñar las metodologías BDD, TDD y DDD en un proyecto real. El proyecto involucra la creación de un nuevo servicio para gestionar préstamos personales. Los actores involucrados son el solicitante del préstamo, el sistema de evaluación de riesgos y el sistema de gestión de préstamos. El servicio debe manejar un volumen de 10 000 solicitudes por hora con una latencia máxima de 200 ms. El sistema de evaluación de riesgos puede fallar con un timeout mayor a 2s, y el sistema de gestión de préstamos puede devolver errores 5xx.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Técnicas de desarrollo |
| **Nivel** | senior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración y definición del dominio

**Objetivo:** Comprender y definir el dominio del proyecto utilizando DDD

**Tiempo estimado:** 3 días

**Instrucciones:**

- Identificar y describir los agregados, entidades y valor-objetos del dominio de préstamos personales.
- Definir los límites del contexto y las relaciones entre los agregados.

**Entregable:** Modelo de dominio documentado con agregados, entidades y valor-objetos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo los agregados interactúan con otros componentes del sistema.
- Piensa en cómo los cambios en el dominio pueden afectar la estructura del modelo.

</details>

### Fase 2: Implementación de pruebas con BDD

**Objetivo:** Aplicar la metodología BDD para escribir pruebas que describan el comportamiento del sistema

**Tiempo estimado:** 4 días

**Instrucciones:**

- Escribir pruebas BDD para los casos de uso principales del servicio de gestión de préstamos.
- Utilizar Gherkin para describir los escenarios de prueba en lenguaje del negocio.

**Entregable:** Pruebas BDD documentadas y ejecutables para los casos de uso principales.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo describir los escenarios de prueba de manera clara y concisa.
- Considera cómo los escenarios de prueba pueden ayudar a comunicar el comportamiento del sistema al equipo y a los stakeholders.

</details>

### Fase 3: Implementación de pruebas unitarias con TDD

**Objetivo:** Aplicar la metodología TDD para escribir pruebas unitarias que garanticen la calidad del código

**Tiempo estimado:** 5 días

**Instrucciones:**

- Escribir pruebas unitarias TDD para las funcionalidades principales del servicio de gestión de préstamos.
- Implementar el código necesario para pasar las pruebas unitarias.

**Entregable:** Pruebas unitarias TDD documentadas y ejecutables para las funcionalidades principales.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo escribir pruebas unitarias que cubran los casos de borde y los errores comunes del dominio.
- Piensa en cómo el código implementado debe ser simple y fácil de mantener.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la metodología BDD y cómo se aplica en un proyecto de desarrollo?
- **paraQueSirve**: ¿Para qué sirve la metodología TDD en el desarrollo de software?
- **comoSeUsa**: ¿Cómo se usa la metodología DDD para modelar un dominio complejo?
- **erroresComunes**: ¿Cuáles son los errores comunes al aplicar las metodologías BDD, TDD y DDD?
- **queDecisionesImplica**: ¿Qué decisiones implica la aplicación de las metodologías BDD, TDD y DDD en un proyecto real?

## Criterios de Evaluacion

- Implementación correcta del modelo de dominio utilizando DDD.
- Escrituras de pruebas BDD que describen el comportamiento del sistema en lenguaje del negocio.
- Escrituras de pruebas unitarias TDD que garantizan la calidad del código.
- Aplicación efectiva de las metodologías BDD, TDD y DDD en un proyecto real.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
docker build -t reto:local . && terraform -chdir=terraform init -backend=false && terraform -chdir=terraform validate
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
