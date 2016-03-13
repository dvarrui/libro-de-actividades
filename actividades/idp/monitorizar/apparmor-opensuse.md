*(Creado el curso 2015-2016)*

#AppArmor

#1. Teoría/explicación

Enlaces de interés:
* [1.ESP Manual SuseLinux](http://guidalinux.altervista.org/suselinux-manual_es-10.1-10/bx5bmky.html)
* [2.ENG Documentation](https://www.suse.com/documentation/apparmor/)
    * [QuickStart](https://www.suse.com/documentation/apparmor/book_opensuse_aaquick21_start/data/article_book_book_opensuse_aaquick_start.html)
    * [AdministrationGuide](https://www.suse.com/documentation/apparmor/book_apparmor21_admin/data/book_apparmor_admin.html)
* [3.ENG AppArmor Geeks](https://en.opensuse.org/SDB:AppArmor_geeks)
* [4.ENG Wiki AppArmor](http://wiki.apparmor.net/index.php/Main_Page)

##1.1 Modos de trabajo

* Modos de trabajo:
    * **complain/learning**: Modo de queja/aprendizaje.
    * **enforce**: Fuerza la aplicación de las políticas/reglas.
* Control del servicio AppArmor
    * systemctl status apparmor
    * systemctl start apparmor
    * systemctl stop apparmor
    * systemctl enable apparmor
    * systemctl disable apparmor

> En versiones antiguas se usaban estos otros comandos:
>
> * rcapparmor status 
> * rcapparmor start
> * rcapparmor stop
> * rcapparmor reload

> Herramientas CLI
> * autodep, Guess basic AppArmor profile requirements. 
> * enforce, Set an AppArmor profile to enforce mode from complain mode.
> * logprof, Manage AppArmor profiles.
> * unconfined, Output a list of processes with open tcp or udp ports that do not have AppArmor profiles loaded. 

##1.2 Yast

* Gestión de los perfiles AppArmor con Yast: `Yast -> Configuración AppArmor`
* Permite
    1. Cambiar la configuración de AppArmor.
    1. Gestionar los perfiles existentes.
    1. Crear un perfil manualmente.

##1.3 Los perfiles

El perfil es una configuración de seguridad que establece que permisos tiene un determinado ejecutable.

Herramientas como `aa-genprof`, nos pueden ayudar a crear el perfil:
* Crear un perfil con `aa-genprof` [Saltar al minuto 1:14 del vídeo](https://www.youtube.com/watch?v=2x8_76rFcM4) 
* [Crear un perfil con `aa-genprof`](http://wiki.apparmor.net/index.php/Profiling_with_tools)
* [Crear un perfil con `aa-autodep`](https://www.digitalocean.com/community/tutorials/how-to-create-an-apparmor-profile-for-nginx-on-ubuntu-14-04)

> Ver un [perfil de ejemplo](./images/home.david.temp.aa.copy.sh)

#2. Práctica

Ver el siguiente [vídeo de 9min](https://youtu.be/Yiw0pG0dl0I?list=PLFBBr-1czYNuLH6yN2dqX4Znz2fexFmAq),
que explica cómo usar el comando `aa-genprof` de AppArmor para crear un perfil de seguridad
a un programa concreto.

##2.1 Nuestro programa/comando

* Abrimos una sesión de comandos (consola1) con nuestro usuario `nombre-alumno`.
* Copiamos el programa `/bin/cp` a la ruta `/home/nombre-alumno/aa/mycopy`.
* Crear los directorios y ficheros siguientes:
    * `/home/nombre-alumno/aa/olimpo/zeus.txt`: Escribir en el contenido "curso1516alumnoXX".
    * `/home/nombre-alumno/aa/olimpo/hera.txt`: Escribir en el contenido "curso1516alumnoXX".
    * `/home/nombre-alumno/aa/olimpo/apolo.txt`: Escribir en el contenido "curso1516alumnoXX".
    * `/home/nombre-alumno/aa/tierra`
* Probamos a copiar archivos con nuestro comando `mycopy`.
    * `cd /home/nombre-alumno/aa/`
    * `./mycopy olimpo/* tierra`
    * `tree`, comprobamos el resultado.
* `rm tierra/*`, limpiamos el diretorio.

##2.2 Generar el perfil

* Abrimos una sesión de comandos (consola2) con el usuario `root`.
    * `cd /etc/apparmor.d`, nos movemos al directorio donde se guardan los perfiles.
    * `ls`, para ver los perfiles que hay.
    * `aa-genprof /home/nombre-alumno/aa/mycopy`, para iniciar la generación 
    de un perfil. Este programa se queda en espera.

* Volvemos a la "consola1" y ejecutamos el comando de copia `./mycopy olimpo/* tierra`
> El objetivo es generar actividad con este programa, mientras está siendo auditado por
aa-genprof.

* Vamos a la "consola2".
    * Pulsamos "S" para comenzar el Scan.
    * Permitir acceso de lectura a la ruta `/home/nombre-alumno/aa/olimpo/*`
    * Permitir acceso de escritura a la ruta `/home/nombre-alumno/aa/tierra/*`
    * Pulsamos S para grabar el perfil.
    * `ls`, debemos ver el nuevo perfil creado.
    * `more home.nombre-alumno.aa.mycopy`, vemos el contenido del fichero.

##2.3 Forzamos el perfil

* Seguimos en la "consola2".
    * `aa-enforce home.nombre-alumno.aa.mycopy`, para forzar el cumplimiento 
    del perfil para el programa mycopy.
    * `aaparmor_status` para consultar el estado de los perfiles.
* Volvemos a la "consola1"
    * `rm tierra/*; tree`, para limpiar y comprobar.
    * `./mycopy olimpo/* tierra`
    * `tree`, comprobamos el resultado.
    * Comprobamos que todo funciona igual de bien que siempre.
    * `mkdir aderno`
    * `./mycopy olimpo/* aderno`, debemos tener un problema de permisos.
    Esto es correcto, así es como ha funcionado nuestro perfil de seguridad.
    * `tree`, comprobamos que no se han copiado los archivos.
    
##2.4 Perfil en modo queja

* Vamos a "consola2".
    * `aa-complain home.nombre-alumno.aa.mycopy`, ponemos el perfil en 
    modo queja. De esta forma no se prohibe ninguna acción, pero si
    queda auditada.
    * `aaparmor_status` para consultar el estado de los perfiles.
* Volvemos a la "consola1".
    * `tree`, comprobamos el contenido de los directorios.
    * `./mycopy olimpo/* aderno`, ahora sí debe funcionar el ejecutable.
    * `tree`, comprobamos que no se han copiado los archivos.    
* Vamos a "consola2".
    * `cat /var/log/audit/audit.log`, Echemos un vistazo al fichero de auditoría.

#ANEXO

##A1. Ejemplo para perfilar el programa leafpad

A modo de prueba vamos a crear un perfil AppArmor para el programa `leafpad` (Leafpad es un
editor de texto). ¿Dónde se encuentra este programa en mi sistema? `whereis leafpad`.

Vamos a realizar las siguientes acciones:
* Ejecutar en un terminal `sudo aa-genprof /usr/bin/leafpad`, para iniciar la herramienta que va a generar el perfil.
> No cerrar el programa anterior y seguimos.
* Iniciar programa `leadpaf`:
    * Abrir el programa
    * Crear un archivo /home/nombre-alumno/Documentos/jedis.txt
    * Escribir dentro: `obiwan y yoda`.
    * Grabar archivo.
    * Cerrar
    * Añadir al archivo `quigon`
    * Crear un archivo /home/nombre-alumno/Descargas/siths.txt
    * Escribir dentro: `vader y emperador`.
    * Grabar archivo.
    * Grabar archivo.
    * Cerrar
    * Añadir al archivo `maul`
* Volver a la ventana donde tenemos `aa-genprof` en ejecución.
* Elegimos la opción `First, (S)can system logs` y seguimos las instrucciones para configurar nuestro perfil.

> *  `aa-complain /etc/apparmor.d/usr.bin.leafpad`: Poner el perfil en modo `complain`
> * Usar `aa-logprof` para afinar más nuestro perfil.
