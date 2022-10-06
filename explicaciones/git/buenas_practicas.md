
# Buenas prácticas con GIT

> Enlace de interés:
> * http://nvie.com/posts/a-successful-git-branching-model/
> * https://docs.google.com/document/d/1eismCwKm-0JicS68kGtX8XbQhrhcS2t2vGc7SyEQkwM/edit#

A continuación mostraremos un modo de trabajo GIT.

Tendremos las siguientes ramas:

|   | Rama    | Padre  | Descripción     | Duración   |
| - | ------- | ------ | --------------- | ---------- |
| 1 | master  |        | Para producción | Permanente |
| 2 | devel   | master | Para desarrollo | Permanante |
| 3 | feature | devel  | Para añadir características | Temporal |
| 4 | hotfix  | master | Para corregir fallos | Temporal |

# 1. Rama master

* Es la rama principal y la que se pone en producción.
* Contiene LA ÚLTIMA VERSIÓN ESTABLE.
* Debe tener los tags de versionado.

# 2. Rama devel

* Se desarrolla en la rama `devel`.
* Cuando la rama `devel` es lo suficiente estable, se puede mezclar con la rama `master`.
* Cada empresa tendrá un/unos responsable/s de pasar la rama `devel` a `master`, ocasionando con ello un cambio de versión en la `master`.

# 3. Ramas features

```
Rama padre: DEVEL  
Merge con : DEVELOP
```

* Las ramas `features` se utilizan para desarrollar nuevas características para una nueva versión.  
* Esta rama existe mientras las nueva función está en desarrollo, pero se elimina después de fusionarse nuevamente con `devel`.

> **RECORDATORIO**: Las ramas `features` no deben existir en origin.

## 3.1 comandos

* `git checkout -b feature_notes devel`, creamos una rama feature. Esto nos crea una rama feature_notes y nos coloca directo para trabajar en ella.

> Es lo mismo que hacer desde la rama `devel`:
> ```
> git branch feature_notes
> git checkout feature_notes
> ```

* Implementaremos las mejoras correspondientes en nuestro código.

> En este punto se podria considerar necesario hacer un `git rebase -i`, para definir que historia de cambios vamos a mostrar en la rama `devel`.
>
> Es decir, si hemos realizado 12 commits en la rama `feature_notes`, no quiero que se muestren a la hora de fusionar, podemos agrupar todos esos commits en uno solo, mostrando la historia como un solo commit.


Incorporamos la nueva característica en `devel`
* `git checkout devel`, cambiamos a la rama `devel`.
* `git merge --no-ff feature_notes`, fusionamos los cambios.
* `git branch -d feature_notes`, borramos la rama `features_notas` ya que ha cumplido su misión y ya no nos es útil.
* `git push origin devel`, subimos los cambios al repositorio remoto.

## 4. Ramas hotfixes

```
Rams padre: Master
Merge con : Master y Devel
```

* Al detectar un bug se crea una rama `hotfix`. Si el bug es crítico debe ser resuelto inmediatamente.
* La nomenclatura debe ser hotfix.
* Las ramas `hotfix` se ramifican a partir de `master` y se deben combinar de nuevo en `devel` y `master`.

> La esencia de este método de trabajo es que los miembros del equipo (rama devel) pueden seguir trabajando mientras que otra persona soluciona el bug.

## 4.1 Se detecta el problema

Veamos un ejemplo.

Digamos que estamos en la versión de producción 1.2 y esta hay un bug bastante grave. Supongamos que los cambios en devel son todavía bastante inestable.
A continuación vamos a bifurcar la rama master para arreglar el problema.

* `git checkout -b hotfix-1.2.1 master`, creamos la rama "hotfix-1.2.1" y nos movemos a ella.
* Corregimos el error en el código.
* `git commit -a -m "Bumped version number to 1.2.1"`

> RECORDAR: modificar el número de versión después de la ramificación.

* `git commit -m "Fixed severe production problem"`, arreglar el fallo y confirmar la corrección con uno o más commits.

## 4.2 Fusionamos con master

Ahora hay que terminar la rama de hotfix con MERGE a master y devel

En primer lugar actualizamos master para marcar la liberación.

* `git checkout master`, nos posicionamos en la rama master.
* `git merge --no-ff hotfix-1.2.1`, fusionamos la rama hotfix-1.2.1 con la master (pero recordad, con la opción --no-ff).
* `git tag -a 1.2.1`, a continuación, y muy importante, es etiquetar la versión corregida.

## 4.3 Fusinamos con devel

Después incluimos este "parche" también en `devel`.

* `git checkout devel`
* `git merge --no-ff hotfix-1.2.1`

## 4.4 Eliminamos la rama temporal

Por último matamos la rama temporal, pues no servirá para nada.

* `git branch -d hotfix-1.2.1`
