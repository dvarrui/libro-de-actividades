
# Git Hooks

> Enlace de interés:
> * [Git hooks: How to automate actions in your Git repo](https://www.redhat.com/sysadmin/git-hooks?intcmp=7013a000002qLH8AAM&extIdCarryOver=true&sc_cid=701f2000001OH79AAG)

Los `git hooks` son scripts de shell que se encuentran en el directorio oculto `.git/hooks` del repositorio Git. Estos scripts desencadenan acciones en respuesta a eventos específicos, por lo que pueden ayudarlo a automatizar tareas.

Cada repositorio de Git incluye 12 scripts "ocultos" de ejemplo.

## Repositorio

Creamos un repositorio Git de ejemplo:

```
> mkdir example
> cd example
> git init
```

Veamos el contenido de `.git/hooks`:

```
> tree .git/hooks

.git/hooks
├── applypatch-msg.sample
├── commit-msg.sample
├── fsmonitor-watchman.sample
├── post-update.sample
├── pre-applypatch.sample
├── pre-commit.sample
├── pre-merge-commit.sample
├── prepare-commit-msg.sample
├── pre-push.sample
├── pre-rebase.sample
├── pre-receive.sample
├── push-to-checkout.sample
└── update.sample
```

## Escribir un Git Hook

Supongamos que queremos mejorar la seguridad. Para ello vamos a crear un Git Hook que le pida confirmación al usuario antes de envirar algo a una rama.

* Crear un nuevo archivo llamado `.git/hooks/pre-commit`.
* Añadir el siguiente contenido:
 y ábralo en un editor de texto. Agregue el siguiente texto, que consulta a Git para obtener una lista de los archivos que se van a confirmar para el nombre de la rama actual y luego ingresa un ciclo while hasta que obtiene una respuesta del usuario:

 
