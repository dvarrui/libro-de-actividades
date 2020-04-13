
# A1. SuseStudio

Vamos a crear una distro personalizada apropiada para 1ASIR.

* Ir a la web SuseStudio, registrarse y entrar. Si tenemos problemas para validarnos
con una cuenta de Gmail, usar una cuenta de Twitter.
* `Actions -> Create new appliance...`

Vamos a crear nuestro proyecto a partir de un modelo base.
Para eso elegiremos la plantilla KDE o Gnome.
Esto nos crea un sistema de escritorio mínimo KDE o Gnome,
y a partir de aquí seguimos con nuestra personalización.

* `Choose a base template`
    * KDE4 desktop o
    * GNOME desktop
* Nombre `idp1516_nombre-del-alumno_ESCRITORIO_VERSIONdistroSUSE`.
* `Switch to software tab -> Search software -> Select software`. Añadir lo siguiente:
    * Paquetes de redes: tree, nmap, traceroute, ipcalc, putty, hexchat, hexchat-lang, wireshark, wireshark-ui-qt
    * Paquetes de edición: gvim, geany, geany-lang , git
    * Paquetes multimedia: simplescreenrecorder, gnome-screenshot, gnome-screenshot-lang, vlc, vlc-qt,
    gimp, gimp-lang, calligra-krita, audacity, audacity-lang
    * Paquetes ofimática: libroffice-l10n-es
    * Incluir paquetes idioma español: `kde-l10n-es`, `desktop-translations`
    * `virtualbox-guest-x11`, `virtualbox-guest-tools`
    * `patterns-openSUSE-mate`: Incluir Mate como desktop secundario.
* `Switch to configuration tab`
    * Idioma español y teclado español. Zona horaria Europa/Canarias.
    * Activar `Configuración -> Appliance -> Add live installer CD/DVD`.
    * Para crear el usuario elegir una de las siguientes opciones:
        * Crear usuario `alumno` con clave `alumno`.
        * Usuario `root` con clave `profesor`
    * Personalizar el aspecto.
    * Informar en el EULA de los usuarios/claves configurados en el sistema.

```
Creado por: Nombre y apellidos del alumno
==================
USUARIO / CLAVE
root    / profesor
alumno  / alumno
==================
IES Puerto de la Cruz - Telesforo Bravo
Curso 2015-2016
CFGS 1ASIR


                        ;i;;L.                          
                       .1.  i1                          
                      .t.    t1                         
                     .1,      f1                        
                    .t:        ft                       
                   .1:          ff                      
                  .t;            tf                     
                 .ti.             tL.                   
               :1t;.              .tCi                  
            .itttttt11111t:       .1i:tL:               
            ;f,                       ,iLt              
       .,,,:::::::::::::::::::::,;;,:::::t:..           
      ,::::,,...           ...,,:;LLtLLLLLLLLL1L.       
      ::,                        .1Lt         .Lt       
      ::                          ,LL;         ;C:      
     .::                          .1Lf.         fL      
     ,:,                           ,LLi         ;Ci     
     ::.           ...              tLf.         LL.    
    .::.       .i1ttttt:            ;LL:         iC1    
    .::        it1,   .:,           .LL1         .CC.   
    ,::       .1t,      .           .1LL.         fC1   
    ,::       ,tt.                   ;LL:         iCL.  
    ::,       :tt.                   .LLi         ;CC;  
    ::,       ;t1.                    fL1               
   .::,       iti                     tLt.              
   .::,       it,                     fLt               
   .:,.                              ,L1.               

```

> Para convertir una imagen en letras ASCII se usó el programa online
[photo2text](http://photo2text.com/index.php]

    * Activar autologin con el usuario `alumno`.
    * `Applience -> Add live installer`
    * `Scripts -> Run this script at the end of the build`    

```
#!/bin/bash -e

. /studio/profile # read in some variables
. /.kconfig # read in KIWI utility functions

FILE=/home/alumno/leeme.txt
touch $FILE
echo "Creado por" >> $FILE
echo "NOMBRE COMPLETO DEL ALUMNO" >> $FILE
date >> $FILE
```

* `Switch to Files tab`
    * Descargar los ficheros siguientes:
        * `https://downloads.tuxfamily.org/godotengine/2.0.3/Godot_v2.0.3_stable_x11.64.zip`
        * `https://downloads.tuxfamily.org/godotengine/2.0.3/Godot_v2.0.3_stable_demos.zip`
        * `https://downloads.tuxfamily.org/godotengine/2.0.3/Godot_v2.0.3_stable_export_templates.tpz`.
        Este archivo es muy grande pero se puede subir desde casa.
    * Añadirlos a la distro sin descomprimir.
    * Usar `Move/rename`, para definir la ruta `/opt/godot-engine` .
    * Activar `Extract`, para que automáticamente descomprima los ficheros cuando construya la ISO.
* `Switch to build tab`, para construir la distro:
    * Eligir `Live CD/DVD iso` y `USB Stick / Hard Disk Image`.
    * Pulsar `build`. Esto tardará 60 minutos o más.
    * Probamos la distro de forma remota con `Testdrive`
    * Si nos convence la podemos descargar a nuestro PC local.
    * En `Configuration...` podemos consultar la configuración de nuestra distro.
* Y si estamos contentos con el resultado, la publicamos con `Share`
     * Usar etiquetas `idp`, `ies`, `puertodelacruz`, `curso`, `1516`
* Entregar URL de la distro publicada al profesor.

> **Otros paquetes interesantes**
>
> * `yast2-users`, gestión de usuarios mediante yast.
