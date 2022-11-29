
# Submódulos de Git

> Enlaces de interés:
> * https://www.atlassian.com/git/tutorials/git-submodule

_Los submódulos de Git nos permiten tener un repositorio de Git como un subdirectorio de otro repositorio de Git._

## ¿Qué es un submódulo de git?

Diferentes maneras de incorporar código externo a nuestro propio repositorio:

1. El código externo se puede **copiar y pegar** directamente en el repositorio principal. No se actualiza con los cambios realizados en el repositorio externo.
2. Otro método sería usar un **sistema de gestión de paquetes** como Gems de Ruby, NPM de Node o Teuton. Requiere instalación y administración de las versiones.
3. **Usar submódulos**. Esto es, usar un repositorio externo dentro de nuestro repositorio principal. Los cambios en el repositorio externo se ven inmediatamente en nuestro repositorio. Si el repositorio externo se "rompe", también lo hará el nuestro.

> Los métodos 1 y 2 no permiten el seguimiento de ediciones y cambios en el repositorio externo.

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
git submodukle init
git submodule update
```

Al agregar un submódulo a un repositorio, se creará un nuevo archivo `.gitmodules`. El cual contiene metadatos sobre la asignación entre la URL del proyecto del submódulo y el directorio local.


```
❯ more .gitmodules
[submodule "repo_A"]
	path = repo_A
	url = git@gitlab.com:dvarrui_test/repo_A.git
```

# Flujos de trabajo de submódulos

Una vez que los submódulos se inicializan y actualizan correctamente dentro de un repositorio principal, se pueden utilizar exactamente como repositorios independientes. Esto significa que los submódulos tienen sus propias ramas e historial. Al realizar cambios en un submódulo, es importante publicar los cambios del submódulo y luego actualizar la referencia de los repositorios principales al submódulo.
