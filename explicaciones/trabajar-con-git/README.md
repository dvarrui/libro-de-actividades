

#1. Trabajando con GIT
#1.1. La primera vez

Servidor GIT en Internet:
* Crear una cuenta en GitHub.
* Crear un repositorio "curso-nombre-del-alumno". Por ejemplo, si el alumno se llama David Vargas, 
nombrar el repositorio como add1516-david-vargas. En minúsculas y usando los guiones (-).

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
* Copiar la clave pública en GitHub (Configuración -> SSH Keys).

> Para ver la clave pública puedo hacer `geany /ruta-al-home-del-usuario/.ssh/id_rsa.pub`: 

* `git clone git@github.com:usuario-git/curso-nombre-del-alumno.git`: Clonar el repositorio remoto en el equipo local.
* `cd curso-nombre-del-alumno`: Nos movemos al directorio controlado por git
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
* A continuación nos ponemos a trabajar con los ficheros del directorio controlado por git.
* `git status`: Consultar los nuevos cambios locales.
* `git add nombre-de-fichero-o-carpeta`: Para añadir un fichero al control de git. 

> Si quiero anular esta acción hacemos `git checkout nombre-de-fichero`.
>
> Para eliminar un fichero del control de git `git rm nombre-de-fichero-o-carpeta`

* `git commit -m "Mensaje informativo"`: Grabar los cambios en este instante.
* `git push`: Subir los cambios al repositorio remoto.

Vídeo: [GIT 02 - Trabajando cada día con git] (https://youtu.be/_IFpfdeUor0)

##1.3. Entregas
Cuando se vaya a realziar la entrega del trabajo vía repositorio GIT, hay que tener en cuenta lo siguiente:
* El último "commit" de la entrega debe tener una mensaje como "Entrega de la Actividad X de la Unidad X". (Ver imagen).
* Crearemos una etiqueta en git, para marcar la entrega: `git tag etiqueta`.

> La etiqueta la establece el profesor y cambiará con cada entrega.

* La URL del trabajo a entregar debe ser algo como "usuario-git/idp1314nombrealumno/trim3/unit5/actividad1.txt".

Imagen que muestra el texto del *commit* asociado a la entrega:
![git-repo-tarea-commit] (./git-repo-tarea-commit.png)

Imagen que muestra la ruta del trabajo/actividad dentro del repositorio GIT:
![git-repo-tarea-url] (./git-repo-tarea-url.png)
