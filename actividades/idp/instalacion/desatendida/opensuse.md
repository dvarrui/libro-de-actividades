
# Instalaciónes desatendidas

Una instalación desatendida del sistema operativo ejecuta el proceso completo
de la instalación del sistema operativo de forma automática, sin hacer preguntas al usuario.

* Vamos a crear 2 instalaciones desatendidas para el sistema operativo OpenSuse.
* Entregas:
    * (a) Entregar URL apuntando a la distro creada en el apartado 1.
    * (b) Informe los pasos del apartado 2.

---

# 1. SuseStudio

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
    * `patterns-openSUSE-mate`: INcluir Mate como desktop secundario.
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

---

# 2. Instalación desatendida de OpenSUSE con `autoyast`

Enlace de interés:
* [Instalación desatendida con autoyast](https://dtrinf.wordpress.com/2012/11/06/instalacion-de-suse-desatendida-con-autoyast/)  
* [Documentación de AutoYast](https://doc.opensuse.org/projects/autoyast/)   
* [Resumen de los comandos versión 13.1](https://es.opensuse.org/openSUSE:Vadem%C3%A9cum_comandos_13.1)   

## 2.1 Personalizamos la MV OpenSUSE Leap

Vamos a usar una MV con el sistema operativo ya instalado. Si no se hubiera creado el fichero `/root/autoinst.xml` durante la instalación entonces tenemos que crearlo como se indoca a continuación.

* A continuación, personalizamos nuestra máquina con los siguientes cambios:
    * Nombre de máquina `1er-apellidoXXy`.
    * Instalamos paquetes que no vengan por defecto preinstaldos. Por ejemplo: `geany`, `nano`, `tree`, `vim`, `git`, `dia`.
    * Creamos usuario `nombre-del-alumno`.

## 2.2 Creamos el fichero `autoinst.xml`

Necesitamos el fichero `autoinst.xml`, con las respuestas a las preguntas del instalador.

Vamos a crear un fichero XML que clona la configuración de nuestro sistema actual.

* Instalamos la herramienta Autoyast (Paquetes `autoyast2`, `autoyast2-installation`).
 Ir a `Yast -> Crear fichero de configuración Autoyast (Autoinstallation Cloning System)`
(o por consola con `/sbin/yast2 clone_system`).

> INFO: La Opción de `Autoinstallation Configuration` de Yast, parece que sirve para editar/modificar un fichero de configuración XML ya existente.

* El perfil clonado se guarda en `/root/autoinst.xml`.
* `cp /root/autoinst.xml nombre-alumnoXX.xml`. Hacemos una copia de seguridad del perfil.
* Copiamos el fichero `nombre-alumnoXX.xml` en un pendrive o en la máquina real.

## 2.3 Modos de acceso al fichero XML

Elegir una de las siguientes formas para la instalación desatendida.
* **USB** - Fichero de control en USB
    * Copiamos el fichero en un pendrive y al instalar el sistema operativo.
* **ISO** - Fichero de control dentro de la propia ISO
    * Incluir el fichero XML dentro de la ISO de instalación.
    * Para modificar la ISO podemos usar el programa `isomaster`.

> **Otras opciones**
>
> * **CIFS** - Fichero de control en carpeta compartida de Windows
> * **HTTP** - Fichero de control en un servidor Web (HTTP)
>    * Copiaremos el fichero XML en el servidor web proporcionado por el profesor,
para que se accesible a través de la red. El fichero tendrá el nombre `nombre_del_alumnoXX.xml`.
>    * Establecer la configuración de red de forma manual, pulsando F4 -> Configuración de red.

## 2.4 Comenzar la instalación desatendida

* Creamos una MV nueva.
* Ponemos el DVD de instalación de OpenSUSE.
* Completamos `Boot Options` para iniciar el proceso de instalación desatendida.

Elegiremos una de las siguientes formas para localizar el fichero XML.
* **USB** - Fichero de control en USB
    * En boot opcions ponemos `autoyast=usb:///nombre-del-alumnoXX.xml`
    * OJO que son 3 barras seguidas después de los dos puntos.
* **ISO** - Fichero de control dentro de la propia ISO
    * En boot options ponemos `autoyast=file:///nombre-de-alumnoXX.xml`
    * OJO que son 3 barras seguidas después de los dos puntos.

> * **SMB/CIFS** - Fichero de control en carpeta compartida de Windows
>     * `autoyast=cifs://servidor/carpeta/nombre-del-alumnoXX.xml`
> * **HTTP** - Fichero de control en un servidor Web (HTTP)
>     * Luego en Boot options `autoyast=http://ip-del-servidor-web/autoyast/nombre-de-alumnoXX.xml`.
>     * Poner en Boot Options información de la configuración de red. Esto es: `hostip=172.19.XX.31/16 gateway=172.19.0.1 autoyast=http://172.20.1.2/autoyast/nombre-de-alumnoXX.xml`

A continuación debe comenzar la instalación de forma desatendida con las opciones
especificadas en el fichero XML.

---

# ANEXO A

## A.2 OpenSUSE 13.2 (modo 1)

`Documentación antigua (curso1516), sobre el proceso de creación del fichero XML en OpenSUSE 13.2`.

En este caso actualizamos XML con la siguiente información:

* Seleccionar los paquetes instalados yendo a la sección Software -> Selección de paquetes -> Clonar
* Seleccionar las particiones yendo a la sección Hardware -> Partitioning -> Clonar
* Seleccionar el boot loader yendo a la sección System -> BootLoader -> Clonar
* Seleccionar fecha/hora yendo a la sección System -> Date and Time -> Clonar
* Seleccionar el idioma yendo a la sección System -> Languages -> Clonar.
* Seleccionar la configuración de red yendo a la sección Network Devices -> Network Setting -> Clonar
* Seleccionar los usuarios y grupos yendo a la sección Security and Users -> User and Group Managent -> Clonar
* Al terminar de "clonar" los datos que nos interesan vamos a grabarlos en un XML,
vamos a File -> Save as. Y lo grabamos con "nombre-del-alumno.xml".

## A.2 OpenSUSE 13.2 (modo 2)

`Documentación antigua (curso1516), sobre el proceso de creación del fichero XML en OpenSUSE 13.2`.

Con OpenSUSE 13.2 podemos hacer una nueva instalación en MV y guardar el fichero `autoyast.xml` durante el proceso.

* Incluir los programas/paquetes siguientes: tree, nmap, traceroute, vim, ruby, geany, putty, minicom, gtk-recordmydesktop.
* Crear el usuario `nombre-alumnoXX`.
* Configurar el nombre de máquina con `primer-apellido-alumnoXX`.
* Configurar dominio con `curso1516`.
* Asegurarse de que se guarda el fichero `autoyast.xml` durante el proceso.
Este fichero guarda las decisiones que tomamos sobre la configuración de nuestra instalación.

`autoyast.xml`  es  nuestro "Control File".
Esto es, un fichero XML con las definiciones que elijamos para nuestra instalación desatendida.
