
# Ramas o branches

> Antes podemos crear un release del proyecto.

## Crear nueva rama

Al crear nuestro primer proyecto/repositorio Git, éste tiene una rama/branch llamada `master`. Ahí es donde trabajamos por defecto. Para crear una nueva rama hacemos:

* `git branch devel`, crear la rama demo.
* `git checkout devel`, para cambiar de la rama actual a la rama devel

> Podemos hacer lo mismo con un sólo comando: `git checkout -b devel`

* `git branch --list`, nos muestra todas las ramas y nos marca en la que estamos actualmente.

## Trabajar en la nueva rama

* Hacemos cambios en la rama `devel`.
* Al hacer git push tenemos el siguiente mensaje:
```
$ git push
fatal: La rama actual devel no tiene una rama upstream.
Para realizar un push de la rama actual y configurar el remoto como upstream, use

	git push --set-upstream origin devel
```
* Seguimos la recomendación:
```
$ git push --set-upstream origin devel
...
remote: Create a pull request for 'devel' on GitHub by visiting:
...
 * [new branch]      devel -> devel
Rama 'devel' configurada para hacer seguimiento a la rama remota 'devel' de 'origin'.
```

## Fusionar los cambios

* `git checkout master`, vamos a la rama master para traernos los cambios de la rama devel.
* `git merge devel`, fusionar los cambios de la rama deme en master.
* Resolver los conflictos.
* `git branch -d devel`, para eliminar la rama cuando no haga falta.
