
# Git Hooks

> Enlace de interés:
> * [Git hooks: How to automate actions in your Git repo](https://www.redhat.com/sysadmin/git-hooks?intcmp=7013a000002qLH8AAM&extIdCarryOver=true&sc_cid=701f2000001OH79AAG)

Los `git hooks` son scripts de shell que se encuentran en el directorio oculto `.git/hooks` del repositorio Git. Estos scripts desencadenan acciones en respuesta a eventos específicos, para ayudar a automatizar tareas.

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

## Escribir un Git Hook en Bash

Supongamos que queremos mejorar la seguridad. Para ello vamos a crear un Git Hook que le pida confirmación al usuario antes de envirar algo a una rama.

* Crear un nuevo archivo llamado `.git/hooks/pre-commit`.
* Añadir el siguiente contenido:

```bash
#!/bin/sh

echo "You are about to commit" $(git diff --cached --name-only --diff-filter=ACM)
echo "to" $(git branch --show-current)

while : ; do
    read -p "Do you really want to do this? [y/n] " RESPONSE < /dev/tty
    case "${RESPONSE}" in
        [Yy]* ) exit 0; break;;
        [Nn]* ) exit 1;;
    esac
done
```

Este script hace los siguiente:
1. Primero consulta a Git para obtener una lista de los archivos que se van a confirmar (commit) para la rama actual.
2. Luego entra en un bucle while esperando una respuesta del usuario.
3. Si la respuesta es `Y` se termina el script con `exit 0`. Esto es terminación correcta y se completa la acción `commit.`
3. Si la respuesta es `N` se termina el script con `exit 1`. Esto es terminación incorrecta y no se completa la acción `commit`.

Modificamos, por ejemplo, un fichero `README.md`, y si hacemos `commit` se debe ejecutar nuestro script `pre-commit` Git Hook. ;-)


## Escribir un Git Hook en Ruby

Vamos a intentar convertir el script anterior de Bash a Ruby.

_En construcción_
