
# Ramas o branches

## Crear nueva rama

Al crear nuestro primer proyecto/repositorio Git, éste tiene una rama/branch llamada `master`. Ahí es donde trabajamos por defecto. Para crear una nueva rama hacemos:

* `git branch demo`, crear la rama demo.
* `git checkout demo`, para cambiar de la rama actual a la rama demo

> Podemos hacer lo mismo con un sólo comando: `git checkout -b demo`

* `git branch --list`, nos muestra todas las ramas y nos marca en la que estamos actualmente.

## Trabajar en la nueva rama

* Hacemos cambios en la rama `demo`.
* Al hacer git push tenemos el siguiente mensaje:
```
$ git push
fatal: La rama actual demo no tiene una rama upstream.
Para realizar un push de la rama actual y configurar el remoto como upstream, use

	git push --set-upstream origin demo
```
* Seguimos la recomendación:
```
$ git push --set-upstream origin demo
...
remote: Create a pull request for 'demo' on GitHub by visiting:
...
 * [new branch]      demo -> demo
Rama 'demo' configurada para hacer seguimiento a la rama remota 'demo' de 'origin'.
```

## Fusionar los cambios

* `git checkout master`, vamos a la rama master para traernos los cambios de la rama demo.
* `git merge demo`, fusionar los cambios de la rama deme en master.
* Resolver los conflictos.
* `git branch -d demo`, para eliminar la rama cuando no haga falta.
