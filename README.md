# Gestión de Errores y Reintentos en Procesamiento de Pagos

En un sistema de procesamiento de pagos para una entidad financiera, los pagos deben ser procesados de manera idempotente para evitar duplicados y manejar adecuadamente los errores. El sistema interactúa con un servicio externo que a veces falla debido a problemas de red o alta carga. El objetivo es asegurar que los pagos se procesen correctamente a pesar de estos errores y que se realicen reintentos automáticos cuando sea necesario.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | E2E technologiesWork 202610091709 |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

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

### Fase 1: Implementación de Manejo de Errores Básico

**Objetivo:** Implementar un mecanismo básico para manejar errores en el procesamiento de pagos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los posibles errores que pueden ocurrir durante el procesamiento de pagos.
- Implementar un manejo básico de errores que registre los fallos y devuelva una respuesta adecuada al cliente.

**Entregable:** Mecanismo básico de manejo de errores implementado y documentado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes tipos de errores que pueden ocurrir (por ejemplo, errores de red, errores del servicio externo).
- Piensa en cómo registrar estos errores para facilitar la depuración y el seguimiento.

</details>

### Fase 2: Implementación de Reintentos

**Objetivo:** Implementar un mecanismo de reintentos para manejar fallos temporales en el servicio externo.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Diseñar un mecanismo de reintentos que intente procesar el pago nuevamente si el servicio externo falla.
- Establecer un límite máximo de reintentos para evitar bucles infinitos.

**Entregable:** Mecanismo de reintentos implementado y documentado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cuántos reintentos son razonables antes de declarar un fallo permanente.
- Piensa en cómo manejar la latencia adicional que pueden introducir los reintentos.

</details>

### Fase 3: Idempotencia en el Procesamiento de Pagos

**Objetivo:** Asegurar que los pagos se procesen de manera idempotente para evitar duplicados.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar un mecanismo para garantizar que cada pago se procese solo una vez, incluso en caso de reintentos.
- Utilizar una clave de idempotencia para identificar pagos duplicados.

**Entregable:** Mecanismo de idempotencia implementado y documentado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo generar y utilizar una clave de idempotencia única para cada pago.
- Piensa en cómo almacenar y verificar esta clave para evitar duplicados.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es el manejo de errores y reintentos en el contexto del procesamiento de pagos?
- **paraQueSirve**: ¿Para qué sirve implementar un mecanismo de reintentos en el procesamiento de pagos?
- **comoSeUsa**: ¿Cómo se utiliza una clave de idempotencia para evitar duplicados en el procesamiento de pagos?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir durante el procesamiento de pagos y cómo se pueden manejar?
- **queDecisionesImplica**: ¿Qué decisiones implica implementar un mecanismo de idempotencia en el procesamiento de pagos?

## Criterios de Evaluacion

- Implementación de un mecanismo básico de manejo de errores.
- Implementación de un mecanismo de reintentos con límite máximo.
- Implementación de un mecanismo de idempotencia para evitar duplicados.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
