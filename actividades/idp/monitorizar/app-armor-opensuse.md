

#AppArmor



#1. Introducción

Enlaces de interés:
* [1.ESP](http://guidalinux.altervista.org/suselinux-manual_es-10.1-10/bx5bmky.html)
* [2.ENG](https://www.suse.com/documentation/apparmor/)
    * [QuickStart](https://www.suse.com/documentation/apparmor/book_opensuse_aaquick21_start/data/article_book_book_opensuse_aaquick_start.html)
    * [AdministrationGuide](https://www.suse.com/documentation/apparmor/book_apparmor21_admin/data/book_apparmor_admin.html)
* [3.ENG](https://en.opensuse.org/SDB:AppArmor_geeks)

##1.1 Modos de trabajo

* Modos de trabajo:
    * **complain/learning**: Modo de queja/aprendizaje.
    * **enforce**: Fuerza la aplicación de las políticas/reglas.
* Acciones
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

##1.2 Los perfiles

* Enlace de interés: [Crear un perfil con aa-genprof](http://wiki.apparmor.net/index.php/Profiling_with_tools)

##1.3. Yast

* `Yast -> Configuración AppArmor`

#2. Práctica

* Vídeo sobre la creación de un perfil apparmor [Saltar al minuto 1:14](https://www.youtube.com/watch?v=2x8_76rFcM4) 
mediante el comando `aa-genprof`

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
