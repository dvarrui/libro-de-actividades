

#1. Trabajando con GIT
#1.1. La primera vez

Servidor GIT en Internet:
* Crear una cuenta en GitHub.
* Crear un repositorio "idp1314-nombre-del-alumno". Por ejemplo, si el alumno se llama David Vargas, 
nombrar el repositorio como idp1314-david-vargas. En minúsculas y usando los guiones (-).
> [OJO] Marcar la opción "Initialize this repository with a README", para crear un repositorio que contenga al menos un fichero.

En el equipo local (La primera vez):
* Instalar GIT (`apt-get install git`)
* Establecer algunas configuraciones:
```
git config --global user.email "email-del-alumno"
git config --global user.name "nombre-del-alumno"
git config --global push.default simple
```
* `ssh-keygen`: Generar par de claves pública/privada
* geany /ruta-al-home-del-usuario/.ssh/id_rsa.pub: Ver la clave pública.
* Copiar la clave pública en GitHub (Configuración -> SSH Keys).
* `git clone git@github.com:usuario-git/idp1314-nombre-del-alumno.git`: Clonar el repositorio remoto en el equipo local.
* `cd idp1314-nombre-del-alumno`: Nos movemos al directorio controlado por git
* Ya podemos a trabajar, creando ficheros/carpetas en el directorio del proyecto git.

En el equipo local, al terminar de trabajar para subir los cambios al servidor GitHub:
```
    cd idp1314-nombre-del-alumno
    git status
    git add nombre-de-fichero-o-carpeta
    git commit -m "Mensaje informativo"
    git push -u origin master
```

##1.2. Cada día de trabajo

En el equipo local cada día de trabajo:
* `git pull`: Antes de empezar a trabajar para obtener los cambios del repositorio remoto.
* Nos ponemos a trabajar.
* `git status`: Consultar los nuevos cambios locales
* `git add nombre-de-fichero-o-carpeta`: Para añadir un fichero al control de git. Si quiero anular esta acción hacemos "git checkout nombre-de-fichero".
> [OJO] `git rm nombre-de-fichero-o-carpeta`: Para eliminar un fichero del control de git. 
> Si quiero anular esta acción hacemos `git checkout nombre-de-fichero`.
* `git commit -m "Mensaje informativo"`: Grabar los cambios en este instante.
* `git push`: Subir los cambios al repositorio remoto.


Vídeo: GIT 02 - Trabajando cada día con git:

##1.3. Entregas
Cuando se haga la entrega del trabajo vía repositorio GIT, hay que enviar la siguiente información:
* El "commit" de la entrega debe tener una mensaje como "Entrega de la Actividad X de la Unidad X". (Ver imagen).
* La URL del trabajo a entregar debe ser algo como "idp1314profesor/trim3/unit5/actividad1.txt".

Imagen que muestra el texto del "commit" asociado a la entrega:

git-repo-tarea-commit


Imagen que muestra la ruta del trabajo/actividad dentro del repositorio GIT:

git-repo-tarea-url
