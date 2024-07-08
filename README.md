## TASK MANAGER

**Proyecto de Gestión de Tareas**

Este proyecto proporciona una aplicación de gestión de tareas que permite a los usuarios crear, organizar y completar tareas. La aplicación utiliza una base de datos PostgreSQL para almacenar y recuperar datos de tareas.

**Herramientas y versiones:**

* **Lenguaje de programación:** Java 17
* **Framework:** Spring Boot 3.3.1
* **Marco de persistencia:** Spring Data JPA 2.6.2
* **Base de datos:** PostgreSQL 14.2
* **Gestor de dependencias:** Maven 3.8.9

**Instalación:**

1. **Clonar el repositorio:**

```bash
git clone https://github.com/yvillarreal/gestor_tareas_spring.git
```

2. **Cambiar al directorio del proyecto:**

```bash
cd your-project-directory
```

3. **Instalar dependencias:**

```bash
mvn install
```

4. **Ejecutar la aplicación:**

```bash
mvn spring-boot:run
```

**Acceso a la aplicación:**

La aplicación se ejecuta en el puerto 8080. Puede acceder a la aplicación en su navegador web utilizando la siguiente URL:

```
http://localhost:8080
```

## Commits

**Convención de commits para el proyecto de gestión de tareas**

Para mantener un historial de commits claro y consistente en el proyecto de gestión de tareas, se recomienda seguir las siguientes convenciones de commits:

**Formato de commit:**

```
<tipo> <scope>: <asunto>

[Cuerpo del mensaje]
```

**Componentes del commit:**

* **Tipo:** Indica el tipo de cambio realizado. Se utilizan los siguientes tipos:
    * **feat:** [🎉] Nueva característica
    * **fix:** [🐛] Corrección de error
    * **docs:** [📝] Cambio en la documentación
    * **style:** [🎨] Cambio de estilo (formato, código, etc.)
    * **refactor:** [🔨] Refactorización de código
    * **perf:** [🐎] Mejora de rendimiento
    * **test:** [✅] Cambio en las pruebas
    * **depend:** [📦] Cambio de tareas de mantenimiento (configuración, dependencias, etc.)
* **Alcance:** Opcionalmente, indica el alcance del cambio. Puede ser un nombre de componente, módulo o característica específica.
* **Asunto:** Describe concisamente el cambio realizado. Debe ser claro y específico.
* **Cuerpo del mensaje:** Proporciona una explicación más detallada del cambio. Puede incluir información sobre el problema que se soluciona, la motivación del cambio y las pruebas realizadas.

**Ejemplos de commits:**

```
feat [🎉]: Implementar nueva funcionalidad de filtrado de tareas

Permite a los usuarios filtrar las tareas por estado (completadas o pendientes) o por fecha límite.
```

```
fix [🐛]: Corregir error al eliminar tareas

Se solucionó un error que impedía eliminar correctamente las tareas de la base de datos.
```

```
docs [📝]: Actualizar documentación de la API de tareas

Se actualizó la documentación para reflejar los cambios en la API de tareas realizados en el commit anterior.
```

