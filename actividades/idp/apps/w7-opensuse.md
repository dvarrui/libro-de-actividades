
*(Modificado para el curso 2015-16)*

#Instalar aplicaciones y actualizar el sistema

En esta actividad vamos a practicar diversas formas de realizar instalaciones 
de aplicaciones en varios sistemas operativos, así como la forma de 
mantener nuestros sistemas actualizados.

# Windows 7 

Enlaces de interés:
* [Chocolatey NuGet](https://chocolatey.org/) is a Machine Package Manager, somewhat like apt-get, but built with Windows in mind.
* [Ninite](https://ninite.com/): Instala y actualiza varios programas en un paso.

##1.1 Usando el GUI
Capturar imagen del proceso final.

###Actualización del sistema:
* Usar el usuario `jedi1` (Debe tener privilegios de administrador del equipo)
* Aplicar las actualizaciones del SO (Panel de control -> Windows Update). Si hay muchas actualizaciones pendientes NO las actualizacen todas. Elijan sólo algunas para probar.

###Instalar características del sistema operativo:
* Vamos a las herramientas de Windows (`Panel de control -> Programas y características -> Activar o desactivar características de Windows`).
* Instalar Cliente Telnet y Juegos/Buscaminas. Comprobar que funcionan correctamente.
> La herramienta telnet sirve para conectarse a equipos remotos. 
En este caso le estamos dando un uso poco común, porque la estamos usando para consultar 
la página web del servidor 172.20.1.2. Lo suyo es usar un navegador web.
> Una forma de comprobar el cliente telnet:
>     Abrir terminal de comandos y escribir: "telnet 192.168.1.3 80"
>     Escribir "hola" y pulsar enter
>     Debes ver algo como... etiquetas HTML ¿te suenan de algo?

###Vamos a instalar aplicaciones
Capturar imagenes de los pasos realizados. 
* Descargar Geany para SO Windows de las páginas oficiales. 
Otras opciones serían Gimp o LibreOffice, pero son más "pesadas", y se tarda más tiempo.
* Comprobar el código MD5 del fichero descargado, para verificar que la descarga es correcta. 
En Windows podemos usar por ejemplo el programa HashCalc para realizar dicha verificación.
* Realizar la instalación de la aplicación.
* Comprobar su funcionamiento.
* Desinstalar el programa.

##1.2 Usando los comandos
Capturar imágenes de los pasos realizados.

###Instalar programas:
* Descargar el programa GIT desde la web oficial (http://git-scm.com/).
* Abrir consola cmd, e instalar por comandos el programa GIT.
* `Git-*.exe /?` (Con el argumento /? vemos todas las opciones del programa)
* `Git-*.exe /SILENT` (Hacemos una instalación sin preguntas al usuario)
* Comprobar (por la consola cmd) que lo tenemos instalado haciendo:
    cd c:\Program Files\Git\bin
    git --version
* Descargar e instalar el programa Evince en formato MSI desde el URL https://wiki.gnome.org/Apps/Evince/Downloads.
          
###Desinstalar programas:
* Vamos a desinstalar el programa por comandos usando la consola wmic.
* Abrir consola PowerShell como Administrador
* `wmic` (Abrir consola)
* `product get name` (Localizar los programas MSI instalados)
* `product where name="Evince 2.30.3" call uninstall` (Desintalar el programa)
* Comprobarlo.

> **INFORMACIÓN - PowerShell**
>
> Estos comandos PowerShell sirven para desinstalar programas instalados como MSI, no como .EXE. https://social.technet.microsoft.com/Forums/es-ES/b56a8e37-0840-45fb-b9ea-4ece77af1ebe/script-para-desinstalar-un-programa-con-powershell?forum=powershelles
> Otra forma para desinstalar programas MSI mediante comandos de PowerShell.
> Abrir consola PowerShell como Administrador
> `$programa = Get-WmiObject -Class Win32_Product -Filter "Name = 'Nombre-mostrado-en Agregar/Quitar programas' "`
> `$programa.Uninstall()`

> **INFORMACIÓN - Instalar desde la terminal Windows al estilo de Linux**
> * URL: http://chocolatey.org/
> * Probado en Windows 7 64bits.
>
> Para instalar esta herramienta ejecutamos en una terminal (cmd.exe) lo siguiente:
`@powershell -NoProfile -ExecutionPolicy unrestricted -Command "iex ((new-object net.webclient).DownloadString('http://chocolatey.org/install.ps1'))" && SET PATH=%PATH%;%systemdrive%\chocolatey\bin`
>
> Una vez realizado este paso ya esta instalado el "gestor" de instalaciones para la terminal,
 por ejemplo si queremos instalar el Notepad++ podemos hacerlo desde la terminal tecleando lo siguiente: 
 `cinst notepadplusplus`
>
> ![chcolatey-org](./images/chcolatey-org.jpg)
>
> En http://chocolatey.org/packages podemos ver todas las aplicaciones disponibles.


# GNU/Linux - OpenSUSE

Vamos a usar SO OpenSUSE 13.2.

##2.1 Usando el GUI
Capturar imagen del resultado final.

> El gestor de paquetes es un programa para instalar/desinstalar software como un AppStore.
* Iniciar el gestor de paquetes ( `yast -> Inst. Software`). 

###Instalar paquetes
* Iniciar Yast -> refrescar los repositorios.
* Instalar por ejemplo el editor geany, git, gkrellm o recordmydesktop.
* Comprobar que funciona el programa instalado.

###Desinstalar paquetes
* Desinstalar la aplicación con el gestor de paquetes.

##2.2 Usando los comandos
Capturar imágenes de los pasos realizados.

###Actualización del sistema
* Entramos en la consola como `root`.
* Vamos a actualizar el catálogo de productos: `zypper refresh`
* Ahora actualizar todas las aplicaciones: `zypper update`

###Instalar/desinstalar software
* Entramos en la consola como `root`.
* Instalar algún programa con el comando `zypper` (`man zypper` para consultar ayuda).
* Comprobar que el programa funciona.
* Buscar el programa en el sistema de ficheros: `whereis nombre-programa`
* Desinstalar el programa.
* Buscar el programa en el sistema de ficheros: `whereis nombre-programa`


