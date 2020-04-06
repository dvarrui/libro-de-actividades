
```
Curso       : 201920, 201819, 201718
Area        : Sistemas operativos, monitorización, seguridad.
Descripción : Configuración de AppArmor en modo monitorización.
              Configuración de AppArmor en modo seguridad.
Requisitos  : GNU/Linux OpenSUSE Leap 15
              Es recomendable trabajar antes la actividad de
              "Audit" (eventos locales).
Tiempo      : 5 sesiones
```

---
# AppArmor (OpenSUSE)

Para esta práctica vamos a usar una MV con SO OpenSUSE.

Propuesta de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (2.3) Comprobamos | | | |
| (3.2) Comprobamos | | | |
| (4.2) Comprobamos | | |. |

---
# 1. AppArmor: Teoría/explicación

Enlaces de interés:
* [1.ESP Manual SuseLinux](http://guidalinux.altervista.org/suselinux-manual_es-10.1-10/bx5bmky.html)
* [2.ENG Documentation](https://www.suse.com/documentation/apparmor/)
    * [QuickStart](https://www.suse.com/documentation/apparmor/book_opensuse_aaquick21_start/data/article_book_book_opensuse_aaquick_start.html)
    * [AdministrationGuide](https://www.suse.com/documentation/apparmor/book_apparmor21_admin/data/book_apparmor_admin.html)
* [3.ENG AppArmor Geeks](https://en.opensuse.org/SDB:AppArmor_geeks)
* [4.ENG Wiki AppArmor](http://wiki.apparmor.net/index.php/Main_Page)

## 1.1 Modos de trabajo

La herramienta AppArmor tiene dos modos de trabajo. Estos son:
* **complain/learning**: Modo de queja/aprendizaje.
* **enforce**: Fuerza la aplicación de las políticas/reglas.

Para controlar el servicio AppArmor tenemos los siguientes comandos:
* systemctl status apparmor, ver el estado del servicio
* systemctl start apparmor, iniciar el servicio
* systemctl stop apparmor, parar el servicio
* systemctl enable apparmor, activar inicio automático
* systemctl disable apparmor, desactivar inicio automático

> Otras herramientas CLI de AppArmor
> * autodep, Guess basic AppArmor profile requirements.
> * enforce, Set an AppArmor profile to enforce mode from complain mode.
> * logprof, Manage AppArmor profiles.
> * unconfined, Output a list of processes with open tcp or udp ports that do not have AppArmor profiles loaded.

## 1.2 Yast

* Gestión de los perfiles AppArmor la podemos hacer también con Yast: `Yast -> Configuración AppArmor`.
* Yast Permite
    1. Cambiar la configuración de AppArmor.
    1. Gestionar los perfiles existentes.
    1. Crear un perfil manualmente.

## 1.3 Los perfiles

El perfil es una configuración de seguridad que establece que permisos tiene un determinado ejecutable.

Herramientas como `aa-genprof`, nos pueden ayudar a crear el perfil:
* Crear un perfil con `aa-genprof` [Saltar al minuto 1:14 del vídeo](https://www.youtube.com/watch?v=2x8_76rFcM4)
* [Crear un perfil con `aa-autodep`](https://www.digitalocean.com/community/tutorials/how-to-create-an-apparmor-profile-for-nginx-on-ubuntu-14-04)

> Enlaces de interés:
> * Veamos un [perfil de ejemplo](./images/home.david.temp.aa.copy.rb)
> * https://gitlab.com/apparmor/apparmor/wikis/Profiles

---
# 2. AppArmor: Práctica

Ver el siguiente [vídeo de 9min](https://youtu.be/Yiw0pG0dl0I?list=PLFBBr-1czYNuLH6yN2dqX4Znz2fexFmAq), que explica cómo usar el comando `aa-genprof` de AppArmor para crear un perfil de seguridad a un programa concreto.

## 2.1 Preparativos

* Abrimos una sesión de comandos (consola1) con nuestro usuario `nombre-alumno`.
* Crear el directorio `/home/nombre-alumno/aa/`.
* Copiar el programa(fichero) `/bin/cp` con el nuevo nombre `/home/nombre-alumno/aa/mycopy`.
* Crear lo siguiente:
    * Crear directorio DIRNAME1 (`/home/nombre-alumno/aa/elhalcon/`)
    * `DIRNAME1/han.txt`: Escribir en el contenido "nombre-del-alumnoXX".
    * `DIRNAME1/chewaka.txt`: Escribir en el contenido "nombre-del-alumnoXX".
    * `DIRNAME1/leia.txt`: Escribir en el contenido "nombre-del-alumnoXX".
    * Crear directorio DIRNAME2 (`/home/nombre-alumno/aa/ciudad-nube/`)
* Probamos a copiar archivos con nuestro comando `mycopy`.
    * `cd /home/nombre-alumno/aa/`
    * `./mycopy DIRNAME1/* DIRNAME2`
    * `tree`, comprobamos el resultado.
* `rm DIRNAME2/*`, limpiamos el directorio.

## 2.2 Generar el perfil

* Abrimos una sesión de comandos (consola2) con el usuario `root`.
    * `cd /etc/apparmor.d`, nos movemos al directorio donde se guardan los perfiles.
    * `ls`, para ver los perfiles que hay.
    * `aa-genprof /home/nombre-alumno/aa/mycopy`, para iniciar la generación
    de un perfil. Este programa se queda en espera.

Volvemos a la "consola1":
* Ejecutamos el comando de copia `./mycopy DIRNAME1/* DIRNAME2`. Mientras hacemos esta acción, se está registrando toda su actividad.

> El objetivo es hacer las acciones con este programa, mientras está siendo auditado por aa-genprof.

Vamos a la "consola2".
* Pulsamos "S" para comenzar el Scan.
* Permitir acceso de lectura a la ruta `DIRNAME1/*`
* Permitir acceso de escritura a la ruta `DIRNAME2/*`
* Pulsamos S para grabar el perfil.
* `ls`, debemos ver el nuevo perfil creado.

## 2.3 Comprobamos

* `cat home.nombre-alumno.aa.mycopy`, veamos el contenido del fichero para ver el contenido del perfil. El perfil es un fichero de texto que se puede modificar si es necesario. Lo importante tener las siguientes reglas:
    1. Permitir ejecución de mycopy (mr).
    2. Permitir lectura `DIRNAME1/*` (r).
    3. Permitir escritura en `DIRNAME2/*` (w).

## 2.4 Revisar Audit

Revisar la configuración de audit para que permita registrar eventos.
* Editar `/etc/audit/rules.d/audit.rules`.
* Comentar la línea `-a never,task`.
* Reiniciar la máquina.

---
# 3. Forzamos el perfil

## 3.1 Preparativos

Seguimos en la "consola2".
* `aa-enforce home.nombre-alumno.aa.mycopy`, para forzar el cumplimiento del perfil para el programa mycopy.

Volvemos a la "consola1"
* `rm DIRNAME2/*; tree`, para limpiar y comprobar.
* `./mycopy DIRNAME1/* DIRNAME2`. Se supone que el perfil permite realizar esta acción.
* `tree`, comprobamos el resultado.
* Comprobamos que todo funciona igual de bien que siempre.
* Crear directorio DIRNAME3 (`/home/nombre-alumno/aa/nave-imperial`)
* `./mycopy DIRNAME1/* DIRNAME3`, debemos tener un problema de permisos. Esto es correcto, porque nuestro perfil de seguridad no lo permite.
* `tree`, comprobamos que no se han copiado los archivos.

## 3.2 Comprobamos

Vamos a "consola2".
* `apparmor_status | grep "profiles are in" -A 6` para consultar el estado de los perfiles.
* `ausearch -x mycopy | grep DENIED | wc -l`, este comando cuenta todas las líneas del fichero de log (eventos) denegados de "mycopy".
* `ausearch -x mycopy | grep ALLOWED | wc -l`, este comando cuenta todas las líneas del fichero de log (eventos) permitidos de "mycopy".

> `ausearch -x mycopy | aureport -u`, consultamos los eventos registrados asociados a nuestro ejecutable.

---
# 4. Modo queja

## 4.1 Perfil en modo queja

Vamos a "consola2".
* `aa-complain home.nombre-alumno.aa.mycopy`, ponemos el perfil en modo queja. De esta forma no se prohíbe ninguna acción, pero sí se quedan registradas (auditoría).

Volvemos a la "consola1".
* `tree`, comprobamos el contenido de los directorios.
* `./mycopy DIRNAME1/* DIRNAME3`, ahora sí debe funcionar el ejecutable.
* `tree`, comprobamos que se han copiado los archivos.    

## 4.2 Comprobamos

Vamos a "consola2".
* `apparmor_status | grep "profiles are in" -A 6` para consultar el estado de los perfiles.
* `ausearch -x mycopy | grep DENIED | wc -l`, este comando cuenta todas las líneas del fichero de log (eventos) denegados de "mycopy".
* `ausearch -x mycopy | grep ALLOWED | wc -l`, este comando cuenta todas las líneas del fichero de log (eventos) permitidos de "mycopy".

> * `ausearch -x mycopy | aureport -u`, consultamos los eventos registrado asociados a nuestro ejecutable.

---
# ANEXO

## A.1 SELinux

* [SELinux](https://theurbanpenguin.com/product/selinux-fundamentals-in-red-hat-enterprise-linux-8)

## A.2 Ideas para usar con Teuton

* Revisar la fecha dentro del fichero perfil (Last modified).
* Crear dos comandos: uno complain y otro enforce. O usar la futura modalidad de evaluación con estados dinámicos de teuton.
