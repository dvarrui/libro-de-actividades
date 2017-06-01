```
* Práctica utilizada en los cursos 201213, 201314, 201415
* Actualizada para el curso 201516
```

# Almacenamiento NAS

* Trabajar de forma individual.
* Entregar informe con capturas de pantalla.
* Esquema de la práctica:
    * Instalar y configurar NAS.
    * Montar 2 discos para guardar los datos en RAID1.
    * Crear 2 recursos compartidos CIFS/SMB en el servidor NAS.
        * Recurso1 compartido de lectura/escritura para el usuario1.
        * Recurso2 de sólo lectura para el usuario2.
    * Crear usuarios/clave para acceder al repositorio NAS.
    * Comprobar el acceso al servdidor NAS desde otra máquina.
* Elegir solamente, una de las siguientes opciones para realizar la práctica.

---

# 1. NAS Hardware

La práctica de NAS consisten en:
* Usar un dispositivo NAS Hardware proporcionado por el profesor.
* Montar 2 discos para guardar los datos en RAID1.
* Crear 2 recursos compartidos CIFS/SMB en el servidor NAS.
    * `profesores`: Recurso compartido de lectura/escritura para el usuario `profesor`
    * `alumnos`: Recurso de sólo lectura para el usuario `alumno`.
* Crear usuarios/clave para acceder al repositorio NAS.
    * Usuario `profesor`.
    * Usuario `alumno`.
* Comprobar el acceso al servdidor NAS desde otra máquina.

---

# 2. Otros NAS

Montar en una MV con otro sistema NAS a elegir por el alumno.
* Antes de empezar consultar el profesor el NAS elegido.
* Instalar y configurar NAS.
* Montar 2 discos para guardar los datos en RAID1.
* Crear 2 recursos compartidos CIFS/SMB en el servidor NAS.
    * `hobbitonXX`: Recurso compartido de lectura/escritura para el usuario `frodoXX`
    * `mordorXX`: Recurso de sólo lectura para el usuario `gandalfXX`.
* Crear usuarios/clave para acceder al repositorio NAS.
    * Usuario `frodoXX`.
    * Usuario `gandalfXX`.
* Comprobar el acceso al servidor NAS desde otra máquina.
