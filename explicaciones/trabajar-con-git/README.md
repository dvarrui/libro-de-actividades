

#1. Trabajando con GIT
##1.1. La primera vez

###Servidor GIT en Internet:
* Crear una cuenta en GitHub.
* Crear un repositorio "curso-nombre-del-alumno". Por ejemplo, si el alumno se llama David Vargas, 
nombrar el repositorio como add1516-david-vargas. En minúsculas y usando los guiones (-).

> [OJO] Marcar la opción "Initialize this repository with a README", para crear un repositorio que contenga al menos un fichero.
> La creación del repositorio en GitHub, sólo hay que hacerla una vez.

###En el equipo local (La primera vez):
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
> **RECORDATORIO** 
>
> Cada vez que vayamos a trabajar en un equipo local por primera vez hay que:
> * Instalar git y configurar el usuario
> * Crear el par de claves y subir la clave pública al servidor
> * y clonar el repositorio en el nuevo equipo local

##1.2. Cada día de trabajo

En el equipo local cada día de trabajo haremos la siguiente secuencia:
* `git pull`: Antes de empezar a trabajar para obtener los cambios del repositorio remoto.
* A continuación nos ponemos a trabajar con los ficheros del directorio controlado por git.
Y cuando terminemos de trabajar y vayamos a irnos seguimos con lo siguiente.
* `git status`: Para consultar y comprobar los nuevos cambios locales.
* `git add nombre-de-fichero-o-carpeta`: Para añadir un fichero al control de git. 

> **DESHACER**
>
> * `git checkout -- nombre-de-fichero`: Para deshacer los últimos cambios realizados a un fichero y devolverlo a su estado anterior.
> * `git reset HEAD nombre-de-fichero`: Para deshacer la acción `git add nombre-de-fichero`
> * `git reset --hard HEAD-1`: Para deshacer el último `git commit ...`
> * `git rm nombre-de-fichero-o-carpeta`: Para eliminar un fichero que está controlado por git 
> * `git mv nombre-actul nombre-nuevo`: Para renombrar un fichero que está controlado por git 
>

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
