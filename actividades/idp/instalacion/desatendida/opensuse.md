```
Instalación desatendida con OpenSUSE
* Creada en el curso 201415.
* Problemas en la lectura del fichero autoyast.xml
```

#Instalaciónes desatendidas

Una instalación desatendida del sistema operativo ejecuta el proceso completo d
e la instalación de forma automática, sin hacer preguntas al usuario.

* Vamos a crear 2 instalaciones desatendidas para el sistema operativo OpenSuse.
* Entrega:
    * Informe los pasos del paso 1.
    * Entregar URL a la distro creada en el paso2.
    
#1. Instalación desatendida de OpenSUSE con `autoyast`

Enlace de interés:
* [Instalación desatendida con autoyast](https://dtrinf.wordpress.com/2012/11/06/instalacion-de-suse-desatendida-con-autoyast/)  
* [Documentación de AutoYast](https://doc.opensuse.org/projects/autoyast/)   
* [Resumen de los comandos versión 13.1](https://es.opensuse.org/openSUSE:Vadem%C3%A9cum_comandos_13.1)   

##1.1 Creación del fichero XML

* Primero vamos a realizar una instalación de OpenSUSE en una MV.
    * Nos aseguraremos de que se guarda el fichero `autoyast.xml` durante el proceso.
    * Si todo es correcto, saltamos al paso siguiente.
* Si no se hubiera creado el fichero `autoyast.xml` durante la instalación entonces
lo creamos ahora:
    * A continuación, personalizaremos nuestra máquina, instalando software, creando usuarios, etc.
    * Instalamos la herramienta Autoyast: zypper in autoyast2
    * Iniciamos autoyast con: `/sbin/yast2 autoyast`
    Con esta herramienta podemos crearnos nuestro "Control File". 
    Esto es un fichero XML con las definiciones que elijamos para nuestra instalación desatendida.
    * Seleccionar los paquetes instalados yendo a la sección Software -> Selección de paquetes -> Clonar
    * Seleccionar las particiones yendo a la sección Hardware -> Partitioning -> Clonar
    * Seleccionar el boot loader yendo a la sección System -> BootLoader -> Clonar
    * Seleccionar fecha/hora yendo a la sección System -> Date and Time -> Clonar
    * Seleccionar el idioma yendo a la sección System -> Languages -> Clonar.
    * Seleccionar la configuración de red yendo a la sección Network Devices -> Network Setting -> Clonar
    * Seleccionar los usuarios y grupos yendo a la sección Security and Users -> User and Group Managent -> Clonar
    * Al terminar de "clonar" los datos que nos interesan vamos a grabarlos en un XML, 
    vamos a File -> Save as. Y lo grabamos con "nombre-del-alumno.xml".
    * Copiamos el fichero XML en un pendrive o en la máquina real.

##1.2 Crear el acceso al fichero XML

Elegiremos una de las siguientes formas para la instalación desatendida.
* ** ISO - Fichero de control dentro de la propia ISO **
    * Incluir el fichero XML dentro de la ISO de instalación. 
    * Para modificar la ISO podemos usar el programa isomaster. 
* ** USB - Fichero de control en USB **
    * Copiamos el fichero en un pendrive y al instalar el sistema operativo.
* ** HTTP - Fichero de control en un servidor Web (HTTP) **
    * Copiaremos el fichero XML en el servidor web proporcionado por el profesor, 
      para que se accesible a través de la red. El fichero tendrá el nombre `nombre_del_alumno.xml`.
    * Establer la configuración de red de forma manual, pulsando F4 -> Configuración de red. 
* ** CIFS - Fichero de control en carpeta compartida de Windows**

##1.3 Comenzar la instalación desatendida

* Vamos a otra MV y comenzamos una nueva instalación de OpenSUSE. 

Ver imagen de ejemplo:
![opensuse-boot-options-autoyast](./files/opensuse-boot-options-autoyast.jpg)

Elegiremos una de las siguientes formas para localizar el fichero XML.
* **ISO** - Fichero de control dentro de la propia ISO
        * En boot options ponemos `autoyast=file:///nombre-de-alumno.xml`
* **USB** - Fichero de control en USB
    * En boot opcions ponemos `autoyast=usb:///nombre-del-alumno.xml`
* **HTTP** - Fichero de control en un servidor Web (HTTP)
    * Luego en Boot options `autoyast=http://ip-del-servidor-web/autoyast/nombre-de-alumno.xml`.
    * Poner en Boot Options información de la configuración de red. Esto es: "hostip=172.16.109.31/16 gateway=172.16.1.1 autoyast=http://172.16.2.9/autoyast/nombre-de-alumno.xml"
* **SMB/CIFS** - Fichero de control en carpeta compartida de Windows
    * `autoyast=cifs://servidor/carpeta/control-file.xml`

* De esta forma, comenzará la instalación de forma desatendida con las opciones 
especificadas en el fichero XML.


#2. SuseStudio

Resumen del proceso:

* Ir a la web SuseStudio y registrarse.
* Crear una distro personalizada apropiada para 1ASIR. Usar el nombre `idp1516-nombre-del-alumno`.
* Vamos a crear nuestro proyecto a partir de una modelo base. 
    * Para eso elegiremos al plantilla KDE o Gnome. Esto nos crea un sistema de escritorio mínimo KDE o Gnome, y a partir de aquí seguimos con nuestra personalización.
* Incluir:
    * Programas/paquetes siguientes: tree, nmap, traceroute, vim, ruby, geany, putty, minicom.
    * Distro que se inicia en modo LIVE.
    * Opciones para instalar la distro en el disco duro.
    * Paquetes y programas necesarios.
    * Idioma español.
    * Autologin o en su defecto informar en el EULA de los usuarios/claves configurados en el sistema.
    * Elegir escritorio KDE o Gnome.
* De forma opcional se puede continuar con lo siguiente:
    * Clonar la distro y compartir la original (Share).
    * Tratar de hacer una segunda versión con escritorios ligeros (LXDE o XFCE).
