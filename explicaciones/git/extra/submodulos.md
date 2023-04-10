
# Submódulos de Git

> Enlaces de interés:
> * https://www.atlassian.com/git/tutorials/git-submodule

_Los submódulos de Git nos permiten tener un repositorio de Git como un subdirectorio de otro repositorio de Git._

## ¿Qué es un submódulo de git?

Hay diferentes formas de incorporar código externo a nuestro propio repositorio, por ejemplo:

1. El modo "clásico" es **copiar y pegar** el código externo directamente en el repositorio principal. El principal inconveniente es que no se actualizan los cambios realizados en el código del repositorio externo.
2. Otro modo sería usar un **sistema de gestión de paquetes** como "apt" de SO Debian, "Gem" de Ruby, "NPM" de Node o "teuton-get" de Teuton, etc. Como invonveniente, se requiere la instalación del software de gestión de paquetes.
3. **Usar submódulos** de Git. Esto es, usaremos los propios comandos de Git para "enlazar" a un repositorio externo desde de nuestro repositorio principal. Los cambios en el repositorio externo se ven inmediatamente en nuestro repositorio. El principal inconveniente es que si el repositorio externo se "rompe", también lo hará el nuestro.

> En los métodos 1 y 2 no permiten el seguimiento de ediciones, ni de los  cambios en el repositorio externo.

## Clonar un repositorio externo como un submódulo

```
git clone /url/to/repo/with/submodules
git submodule init
git submodule update
```

## Ejemplo

* Supongamos que tenemos el siguiente repositorio `repo_C`

```
repo.c
└── README.md
```

* Ahora queremos agregar `repo_A` como submódulo del `repo_C`.

```
cd repo.c
git clone git@gitlab.com:dvarrui_test/repo_A.git
git submodule init
git submodule update
```

Al agregar un submódulo a un repositorio, se creará un nuevo archivo `.gitmodules`. El cual contiene metadatos sobre la asignación entre la URL del proyecto del submódulo y el directorio local.

```
❯ more .gitmodules
[submodule "repo_A"]
	path = repo_A
	url = git@gitlab.com:dvarrui_test/repo_A.git
```

# Flujos de trabajo con submódulos

Una vez que los submódulos se inicializan y actualizan correctamente dentro de un repositorio principal, se pueden utilizar exactamente como repositorios independientes. Esto significa que los submódulos tienen sus propias ramas e historial. Al realizar cambios en un submódulo, es importante publicar los cambios del submódulo y luego actualizar la referencia de los repositorios principales al submódulo.
