
# Instalar aplicaciones y actualizar el sistema (Windows + Debian)

En esta actividad vamos a practicar diversas formas de realizar instalaciones 
de aplicaciones en varios sistemas operativos, así como la forma de 
mantener nuestros sistemas actualizados.

---

# 1. Windows - Usando el GUI

> Enlaces de interés:
> * [Chocolatey NuGet](https://chocolatey.org/) is a Machine Package Manager, somewhat like apt-get, but built with Windows in mind.
> * [Ninite](https://ninite.com/): Instala y actualiza varios programas en un paso.

* Capturar imagen del proceso final.

### Actualización del sistema:
* Usar el usuario `jedi1` (Debe tener privilegios de administrador del equipo)
* Aplicar las actualizaciones del SO (Panel de control -> Windows Update). Si hay muchas actualizaciones pendientes NO las actualizacen todas. Elijan sólo algunas para probar.

### Instalar características del sistema operativo:
* Vamos a las herramientas de Windows (`Panel de control -> Programas y características -> Activar o desactivar características de Windows`).
* Instalar Cliente Telnet y Juegos/Buscaminas. Comprobar que funcionan correctamente.
> La herramienta telnet sirve para conectarse a equipos remotos. 
En este caso le estamos dando un uso poco común, porque la estamos usando para consultar 
la página web del servidor 172.20.1.2. Lo suyo es usar un navegador web.
> Una forma de comprobar el cliente telnet:
>     Abrir terminal de comandos y escribir: "telnet 192.168.1.3 80"
>     Escribir "hola" y pulsar enter
>     Debes ver algo como... etiquetas HTML ¿te suenan de algo?

### Vamos a instalar aplicaciones
Capturar imagenes de los pasos realizados. 
* Descargar Geany para SO Windows de las páginas oficiales. 
Otras opciones serían Gimp o LibreOffice, pero son más "pesadas", y se tarda más tiempo.
* Comprobar el código MD5 del fichero descargado, para verificar que la descarga es correcta. 
En Windows podemos usar por ejemplo el programa HashCalc para realizar dicha verificación.
* Realizar la instalación de la aplicación.
* Comprobar su funcionamiento.
* Desinstalar el programa.

---

# 1.2 Windows - Usando los comandos
Capturar imágenes de los pasos realizados.

## Instalar programas:
* Descargar el programa GIT desde la web oficial (http://git-scm.com/).
* Abrir consola cmd, e instalar por comandos el programa GIT.
* `Git-*.exe /?` (Con el argumento /? vemos todas las opciones del programa)
* `Git-*.exe /SILENT` (Hacemos una instalación sin preguntas al usuario)
* Comprobar (por la consola cmd) que lo tenemos instalado haciendo:
    cd c:\Program Files\Git\bin
    git --version
* Descargar e instalar el programa Evince en formato MSI desde el URL https://wiki.gnome.org/Apps/Evince/Downloads.
          
## Desinstalar programas:
* Vamos a desinstalar el programa por comandos usando la consola wmic.
* Abrir consola PowerShell como Administrador
* `wmic` (Abrir consola)
* `product get name` (Localizar los programas MSI instalados)
* `product where name="Evince 2.30.3" call uninstall` (Desintalar el programa)
* Comprobarlo.

> Estos comandos PowerShell sirven para desinstalar programas instalados como MSI, no como .EXE. https://social.technet.microsoft.com/Forums/es-ES/b56a8e37-0840-45fb-b9ea-4ece77af1ebe/script-para-desinstalar-un-programa-con-powershell?forum=powershelles
> Otra forma para desinstalar programas MSI mediante comandos de PowerShell.
> Abrir consola PowerShell como Administrador
> `$programa = Get-WmiObject -Class Win32_Product -Filter "Name = 'Nombre-mostrado-en Agregar/Quitar programas' "`
> `$programa.Uninstall()`

---

# 3. GNU/Linux - Usando el GUI

Vamos a usar SO Debian/Ubuntu.

> El gestor de paquetes es un programa para instalar/desinstalar software como un AppStore.
* Iniciar el gestor de paquetes ( Por ejemplo synaptic). 
> Si no tienes el programa synaptic instalado en el sistema, puedes abrir una consola y ejecutar: `apt-get install synaptic`

## Instalar paquetes
* Iniciar Synaptic -> refrescar los repositorios.
* Instalar por ejemplo el editor geany, git, gkrellm o recordmydesktop.
* Comprobar que funciona el programa instalado.
* Localizar/consutlar los paquetes descargados que estarán en la caché ( /var/cache/apt/archives )

## Desinstalar paquetes
* Desinstalar la aplicación con el gestor de paquetes.
* Ir al directorio de la caché. Hacer click derecho -> Instalar Software o doble click 
sobre el paquete para ver cómo se vuelve a instalar o probar con `dpkg -i NOMBRE-PAQUETE.deb`.

---

# 4. GNU/Linux usando los comandos

###Actualización del sistema
* Vamos a actualizar el catálogo de productos: apt-get update
* Ahora actualizar todas las aplicaciones: apt-get upgrade
* Ver el tamaño que ocupan los ficheros descargados en la cache: du -sh /var/cache/apt/archives

Limpiar la caché:
* Ver los ficheros que hay en la caché: vdir /var/cache/apt/archives
* Para vaciar la caché podemos hacer:
    apt-get clean, o
    aptitude clean
* Verificamos: vdir /var/cache/apt/archives
* Comprobamos el tamaño de la caché: du -sh /var/cache/apt/archives

Descargar paquetes y luego instalalos:
> Vamos a instalar el paquete tree, traceroute, o openssh-server.
> Nos aseguramos de que no está instalado. 
> Si estuviera, por ejemplo, tree instalado, lo podemos desinstalar con el comando: apt-get remove tree
* Descargar el paquete pero sin instalarlo. Veamos varias formas de hacerlo:
    apt-get download tree
    apt-get --download-only tree
    apt-get -d tree
    aptitude download tree
* Ya tenemos hemos descargado el fichero del paquete tree, en nuestro directorio actual. Comprobar: vdir tree*.deb. 
> Sustituir [...] por el nombre de versión del paquete descargado.
* Ver información del paquete: dpkg --info tree-[...].deb
* Ver el contenido del paquete: dpkg -c tree-[...].deb
* Instalar el paquete: dpkg -i tree-[...].deb
* Ya tenemos el paquete instalado. Comprobarlo ejecutando "tree".

> **Entorno gráfico**
>
> Si quieres, puedes aprovechar en este momento para instalar un entorno gráfico a nuestra MV Debian en modo texto.
> Existen varios entornos gráficos . Elige el que prefieras.
> Ejemplos de entornos gráficos ordenados de más ligero a más sofisticado: LXDE, XFCE, Cinnamon, GNOME, KDE, Unity.
>
> Veamos un ejemplo:
> * Para instalar el escritorio, lo primero que tenemos que instalar es el gestor de ventanas. 
Este gestor de ventanas se llama Xorg.
Así que tecleamos el siguiente comando: `apt-get install -y xserver-xorg-video-all xorg`
> * Ahora vamos a instalar el escritorio LXDE, por ejemplo: `apt-get install lxde`
> * Reiniciar la máquina. Ejecutar `startx` para iniciar el modo gráfico.

---

# ANEXO: SO GNU/Linux OpenSUSE

##A.1 Usando el GUI
* Usar el usuario `jedi1` (Administrador del sistema)
* Vamos a Inicio -> Herramientas del Sistema -> YAST -> Instalación de aplicaciones. 
También podríamos iniciarlo abriendo un terminal -> nos convertimos en superusuario -> ejecutamos yast2.
> Si nos da un error por no tener montado el disco de OpenSUSE, dentro del gestor de paquetes (yast) vamos a Configuración -> Repositorios y desactivamos el correspondiente al disco. Suele ser la primera línea.

![suse-yast-repo](./imagens/suse-yast-repo.png)

* Procedemos a instalar por ejemplo el editor geany, git, gkrellm o recordmydesktop.
* Probar a desinstalar una aplicación con el gestor de paquetes.

#ANEXO 2: Instalar desde la terminal Windows al estilo de Linux
* URL: http://chocolatey.org/
* Probado en Windows 7 64bits.

Para instalar esta herramienta ejecutamos en una terminal (cmd.exe) lo siguiente:
`@powershell -NoProfile -ExecutionPolicy unrestricted -Command "iex ((new-object net.webclient).DownloadString('http://chocolatey.org/install.ps1'))" && SET PATH=%PATH%;%systemdrive%\chocolatey\bin`

Una vez realizado este paso ya esta instalado el "gestor" de instalaciones para la terminal,
 por ejemplo si queremos instalar el Notepad++ podemos hacerlo desde la terminal tecleando lo siguiente: 
 `cinst notepadplusplus`

![chcolatey-org](./images/chcolatey-org.jpg)

En http://chocolatey.org/packages podemos ver todas las aplicaciones disponibles.
