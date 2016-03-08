*(Creado el curso 2015-2016)*

#AppArmor

#1. Introducción

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
    * rcapparmor status 
    * rcapparmor start
    * rcapparmor stop
    * rcapparmor reload
* Herramientas CLI
    * autodep, Guess basic AppArmor profile requirements. 
    * enforce, Set an AppArmor profile to enforce mode from complain mode.
    * logprof, Manage AppArmor profiles.
    * unconfined, Output a list of processes with open tcp or udp ports that do not have AppArmor profiles loaded. 

##1.2 Yast

Gestión de los perfiles AppArmor con Yast.
* `Yast -> Configuración AppArmor`

##1.3 Los perfiles

El perfil es una configuración de seguridad que establece que permisos tiene un determinado ejecutable.

Vamos a ver dos herramientas que nos pueden ayudar a crear el perfil:
* Creaar un perfil con `aa-genprof` [Saltar al minuto 1:14 del vídeo](https://www.youtube.com/watch?v=2x8_76rFcM4) 
* [Crear un perfil con `aa-genprof`](http://wiki.apparmor.net/index.php/Profiling_with_tools)
* [Crear un perfil con `aa-autodep`](https://www.digitalocean.com/community/tutorials/how-to-create-an-apparmor-profile-for-nginx-on-ubuntu-14-04)

#2. Práctica

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
