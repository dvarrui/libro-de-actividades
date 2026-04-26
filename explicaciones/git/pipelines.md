
```
EN CONSTRUCCIÓN!!!
```

# GitLab Pipelines

Enlaces de interés:
* https://blog.develat.io/pipelines-ci-cd/
* https://blog.develat.io/pipelines-en-gitlab-el-camino-hacia-el-ci-cd-parte-i/

Teoría:
* Integración contínua
* Despliegue contínuo
* Test unitarios

# GitLab Runners

Gitlab Runner es un complemento de Gitlab que envia tareas para su ejecución y recibe los resultados.

Un Runner es la puerta a un entorno donde podemos ejecutar nuestras tareas. Dicho entorno puede ser:
* el mismo servidor donde se está ejecutando nuestro Runner («shell»),
* algún tipo de maquina virtual dentro del servidor («Parallels», «VirtualBox», «Docker», «Kubernetes»)
* o un servidor remoto («SSH»).

Nosotros configuramos las tareas a realizar en un fichero `.gitlab-ci.yml` de nuestro proyecto. Gitlab interpreta las órdenes del pipelines, se las envía al Runner, éste las ejecuta y devuelve los resultados de las tareas a Gitlab, que a su vez los representa de manera gráfica sobre los mismos pipelines.

## Instalación y configuración

* Instalar GitLab Runner en OpenSUSE (https://software.opensuse.org/package/gitlab-runner).
* Añadir el usuario `gitlab` al grupo `sudoers`.
* Necesitamos un Token de acceso para nuestro Runner. Esto lo hacemos desde el servidor GitLab.
    * shared: Podrá ejecutar tareas de cualquier grupo y proyecto.
    * group: Podrá ejecutar tareas de cualquier proyecto dentro del grupo en el que lo hemos registrado.
    * specific: Podrá ejecutar únicamente tareas del proyecto al que lo hemos asignado.
* `sudo gitlab-runner register`, registrar el Runner con nuestro servidor GitLab.
* Comprobamos que esté todo correcto yendo al `Servidor GitLab -> Area de administración -> Overview -> Runners`.

Ya tenemos el servidor GitLab y el Runner. Ahora nos falta el Pipeline.

## Pipeline

Un pipeline nos permite automatizar:
1. Creación de un entorno para la ejecución de nuestro proyecto
2. La ejecución del proyecto en sí.

Un pipeline está compuesto de etapas, y a su vez cada etapa tiene uno o más tareas.

**Ejemplo**: Programa hecho con la librería Pandas que hace operaciones sobre una serie de datos e imprime los resultados.

```python
# main.py
import pandas as pd

data = {
    "country": ["Brazil", "Russia", "India", "China", "South Africa"],
    "capital": ["Brasilia", "Moscow", "New Dehli", "Beijing", "Pretoria"],
    "area": [8.516, 17.10, 3.286, 9.597, 1.221],
    "population": [200.4, 143.5, 1252, 1357, 52.98]
}

frame = pd.DataFrame(data)
print(frame)

print('------------------------------')

print("Sum of all the populations", sum(frame['population']))
```


```yaml
# .gitlab-ci.yml

stages:
  - Preparación
  - Ejecución

Instalar_dependencias:
  stage: Preparación
  allow_failure: false
  script:
    - sudo apt update
    - sudo apt install -y python3-pip
    - pip3 install pandas

Ejecutar_programa:
  stage: Ejecución
  allow_failure: false
  script:
    - python3 main.py
```

De ahora en adelante nuestro pipeline se ejecutará cada vez que hagamos un cambio en nuestro proyecto y lo subamos a Gitlab.

## Problemas de los Runner tipo shell

En el ejemplo anterior, los comandos de las tareas se ejecutan directamente sobre el servidor, lo cual puede generar conflictos con otros proyectos. Si tenemos varios proyectos y varios Runners, todos del tipo «shell», nos encontraremos con situaciones de conflicto y fallos.

Veremos otros tipos de Runners para solucionar este problema.
