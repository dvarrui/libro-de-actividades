
# 1. Introducción VNC

Entrega a determinar por el profesor:
* (a) Correccción remota con TEUTON.
* (b) Entraga informe por GIT.

> En el caso de la entrega por GIT:
> * URL con la ruta al archivo del informe dentro del repositorio del alumno.
> * URl commit del repositorio con la versión entregada.
> * Etiquetaremos la entrega en el repositorio Git con `vnc`.

Conexiones remotas con VNC:
* Capturar imágenes de la instalación y configuración VNC para poder acceder a una máquina remota.
* Vamos a realizar las siguientes conexiones remotas VNC:
    * Acceder a Windows - desde Windows 7/10
    * Acceder a Windows - desde GNU/Linux OpenSUSE
    * Acceder a GNU/Linux OpenSUSE - desde GNU/Linux OpenSUSE (A lo mejor no hay que instalar el software cliente VNC)
    * Acceder a GNU/Linux OpenSUSE - desde Windows 7/10

---

# 1. Instalación en Windows

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).
* Descargar `TightVNC`. Esta es una herramienta libre disponible para Windows.

## 1.1 Ir al servidor VNC en Windows

* En el servidor VNC instalaremos `TightVNC -> Custom -> Server`. Esto es el servicio.
* Revisar la configuración del cortafuegos del servidor VNC Windows para permitir VNC.

## 1.2 Ir a la máquina GNU/Linux

* Ejecutar `nmap -Pn IP-VNC-SERVER`, desde la máquina real GNU/Linux para comprobar
que los servicios son visibles desde fuera de la máquina VNC-SERVER. Deben verse los puertos 580X, 590X, etc.

## 1.3 Ir al cliente Windows

* En el cliente Windows instalar `TightVNC -> Custom -> Viewer`.
* Usaremos `TightVNC Viewer`. Esto es el cliente VNC.

> **NOTA**
>
> * Si usamos un servidor VNC "Marca-ACME", usar también el cliente "Marca-ACME".
> * Para esta práctica usaremos conexiones SIN cifrar.
> * Leer la documentación sobre conexiones VNC.

> **Problemas de conexión**
>
> * Refrescar las MAC de la MV.
> * Revisar en la configuración del servidor VNC Windows las opciones de "Access Control".

## 1.4 Comprobaciones finales

Para verificar que se han establecido las conexiones remotas:
* Ir al servidor VNC y usar el comando `netstat -n` para ver las conexiones VNC cob el cliente.

---

# 2. Instalación en OpenSUSE

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).

## 2.1 Ir al servidor VNC OpenSUSE

* Ir a `Yast -> VNC`
    * Permitir conexión remota. Esto configura el servicio `xinet`.
    * Abrir puertos VNC en el cortafuegos.
* Ir a `Yast -> Cortafuegos`
    * Revisar la configuración del cortafuegos.
    * Debe estar permitido las conexiones a `vnc-server`.
* Con nuestro usuario normal
    * Ejecutar `vncserver` en el servidor para iniciar el servicio VNC.
        * Otra opción `vncserver -interfaz [address]`.
    * Ponemos claves para las conexiones VNC a nuestro escritorio.
    * Al final se nos muestra el número de nuestro escritorio remoto.
    Apuntar este número porque lo usaremos más adelante.
* `vdir /home/nombrealumno/.vnc`, vemos que se nos han creado unos ficheros de configuración VNC asociados a nuestro usuario.
* Ejecutar `ps -ef|grep vnc` para comprobar que los servicios relacionados con vnc están en ejecución.
* Ejecutar `lsof -i -n` para comprobar que están los servicios en los puertos VNC (580X y 590X).

> El comando `netstat -ntap` está obsoleto. Pero si aún insistimos en usarlo hay que instalar
el paquete `net-tools-deprecated`.

## 2.2 Ir a la máquina GNU/Linux

* Ejecutar `nmap -Pn IP-VNC-SERVER`, desde la máquina real GNU/Linux para comprobar
que los servicios son visibles desde fuera de la máquina VNC-SERVER. Deben verse
los puertos VNC (5801, 5901, etc).

## 2.3 Ir al cliente GNU/Linux

* `vncviewer` es un cliente VNC que viene con OpenSUSE.
* En la conexion remota, hay que especificar `IP:5901`, `IP:5902`, etc.
(Usar el número del escritorio remoto obtenido anteriormente).
* Hay varias formas de usar vncviewer:
    * `vncviewer IP-vnc-server:590N`
    * `vncviewer IP-vnc-server:N`
    * `vncviewer IP-vnc-server::590N`

> * Además de `Yast`, podemos puede usar el comando `vncserver` para gestionar el servidor VNC.
> * Enlace sobre [VNC server OpenSUSE Leap 42.3](https://doc.opensuse.org/documentation/leap/reference/html/book.opensuse.reference/cha.vnc.html#sec.vnc.viewer)
> * Enlace sobre [VNC server OpenSUSE 13.2](https://www.howtoforge.com/tutorial/vnc-server-on-opensuse-13.2/)
> * Como cliente VNC podemos usar también `krdc`.

## 2.4 Comprobaciones finales

Comprobaciones para verificar que se han establecido las conexiones remotas:
* Ejecutar `lsof -i -n` en el servidor para comprobar las conexiones VNC.
* Ejecutar `vncserver -list` en el servidor.

---

# 3. Comprobaciones con SSOO cruzados

* Conectar el cliente GNU/Linux con el Servidor VNC Windows.
* Ejecutar `netstat -n` en el servidor Windows.
* Conectar el cliente Windows con el servidor VNC GNU/Linux.
* Ejecutar en el servidor GNU/Linux `lsof -i`.

---

# 4. DISPLAY 0 en GNU/Linux

> [Enlace de interés](https://wiki.archlinux.org/index.php/TigerVNC_)

Cuando queremos ejecutar vncserver para controlar directamente la pantalla local
usaremos `x0vncserver` de tigervnc.

* Ir al servidor
* `x0vncserver -display :0 -passwordfile /home/nombre-alumno/.vnc/passwd`
* Para más información, véase `man x0vncserver`
* `lsof -i -n`
* Ir al cliente y probamos a conectarnos con el servidor.

---

# ANEXO A

## Debian

* En Debian se puede usar `tightvncserver` como VNC server.

> * [VNC GNU/Linux] (http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m5/servidor_vnc.html)

## Activar Escritorio Remoto en Ubuntu

En Ubuntu 12:

    Ir a Aplicaciones -> Herramientas del Sistema -> Preferencias -> Compartición del Escritorio.

En Xubuntu:

    Instalar software: apt-get install vino
    Activar permisos: vino-preferences
    Iniciar servicio: /usr/lib/vino/vino-server


## Habilitar escritorio remoto de Xubuntu

A diferencia de otros sistemas operativos basados en Linux, no puedes habilitar el uso compartido del escritorio remoto, accediendo a las preferencias del menú del sistema. En su lugar, debes usar "vino", un servidor de Computación de Red Virtual, o VNC (por sus siglas en inglés), nativo de Xubuntu.

    Haz clic en el menú "Aplicaciones" y selecciona "Accesorios" y "Terminal", para abrir la aplicación de terminal.
    Instalar vino: $ sudo apt-get install vino
    Configurar vino: $ vino-preferences
    Sigue las instrucciones en pantalla para hacer cambios a tus preferencias de uso compartido del escritorio remoto.
    Escribe la siguiente línea para ingresar el editor Autostart Apps: xfce4-autostart-editor
    Comando en Autostarted Apps para iniciar el servidor vino: /usr/lib/vino/vino-server
