
# Submódulos de Git

> Enlaces de interés:
> * https://www.atlassian.com/git/tutorials/git-submodule

Los submódulos de Git nos permiten tener un repositorio de Git como un subdirectorio de otro repositorio de Git.

## ¿Qué es un submódulo de git?

A menudo, un repositorio de código dependerá de código externo. Este código externo se puede incorporar de diferentes maneras.

1. El código externo se puede **copiar y pegar** directamente en el repositorio principal. Este método tiene la desventaja de perder cualquier cambio realizado en el repositorio externo.
2. Otro método para incorporar código externo es mediante el uso de un **sistema de gestión de paquetes** de lenguaje como Ruby Gems o NPM. Este método tiene la desventaja de que requiere instalación y administración de versiones en todos los lugares donde se implementa el código de origen.
3. **Usar submódulos**. Esto es, usar un repositorio externo dentro de nuestro repositorio principal.

> Los métodos 1 y 2 no permiten el seguimiento de ediciones y cambios en el repositorio externo.

## Ejemplo

Al agregar un submódulo a un repositorio, se creará un nuevo archivo .gitmodules. El archivo .gitmodules contiene metadatos sobre la asignación entre la URL del proyecto del submódulo y el directorio local.

Veamos un ejemplo. Tenemos 3 repositorios: `repo_A`, `repo_B` y `repo_C`. Además el repo_C hace uso(reutiliza) del contenido de los repos A y B. Para ello se han creado los directorios `dir.a` y `dir.b`. Al navegar dentro de `dir.a` es como si estuviéramos en el `repo_A` y al navegar por `dir.b` es como si estuviéramos dentro del `repo_B`

```
.
├── CONTRIBUTING.md
├── dir.a
├── dir.b
├── .git
├── .gitmodules
└── README.md
```

Entonces tenemos que `repo_C` tiene 2 submódulos.

```
❯ more .gitmodules
[submodule "repo_A"]
	path = dir.a
	url = git@gitlab.com:dvarrui_test/repo_A.git
[submodule "repo_B"]
	path = dir.b
	url = git@gitlab.com:dvarrui_test/repo_B.git
```

## ¿Cuándo se debería usar un submódulo git?

Si se necesita mantener una gestión de versiones estricta sobre sus dependencias externas, puede tener sentido usar submódulos de git. Por ejemplo:

* Cuando un componente externo o un subproyecto cambia demasiado rápido o los próximos cambios romperán la API, puede bloquear el código en una confirmación específica por su propia seguridad.
* Cuando tiene un componente que no se actualiza con mucha frecuencia y desea rastrearlo como una dependencia del proveedor.
* Cuando está delegando una parte del proyecto a un tercero y desea integrar su trabajo en un momento o lanzamiento específico. Nuevamente, esto funciona cuando las actualizaciones no son demasiado frecuentes.

## Comandos más frecuentes para los submódulos

**Clonar un repositorio externo como un submódulo**

```
git clone /url/to/repo/with/submodules
git submodule init
git submodule update
```
