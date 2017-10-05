
# 1. Introducción

Entrega:
* URL con la ruta al archivo del informe dentro del repositorio del alumno.
* URl commit del repositorio con la versión entregada.
* Etiquetaremos la entrega en el repositorio Git con `vnc`.

---

# 2. Conexiones remotas con VNC

* Capturar imágenes de la instalación y configuración VNC para poder acceder a una máquina remota.
* Vamos a realizar las siguientes conexiones remotas VNC:
    1. Acceder a Windows - desde Windows 7/10
    1. Acceder a Windows - desde GNU/Linux OpenSUSE
    1. Acceder a GNU/Linux OpenSUSE - desde GNU/Linux OpenSUSE (A lo mejor no hay que instalar el software cliente VNC)
    1. Acceder a GNU/Linux OpenSUSE - desde Windows 7/10

---

# 3. Instalación en Windows

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).
* Descargar `TightVNC`. Esta es una herramienta libre disponible para Windows.

## 3.1 Ir al servidor

* En el servidor VNC instalaremos `TightVNC server`.
* Revisar la configuración del cortafuegos del servidor VNC Windows para permitir VNC.

## 3.2 Ir a la máquina real

* Ejecutar `nmap -Pn IP-VNC-SERVER`, desde la máquina real GNU/Linux para comprobar
que los servicios son visibles desde fuera de la máquina VNC-SERVER. Deben verse
los puertos 5801, 5901, etc.

## 3.3 Ir al cliente

* En el cliente usaremos `TightVNC viewer`.

> **NOTA**
>
> * Si usamos un servidor VNC "Marca-ACME", usar también el cliente "Marca-ACME".
> * Para esta práctica usaremos conexiones SIN cifrar.
> * Leer la documentación sobre conexiones VNC.

> **Problemas de conexión**
>
> * Refrescar las MAC de la MV.
> * Revisar en la configuración del servidor VNC Windows las opciones de "Access Control".

## 3.4 Comprobaciones finales

Para verificar que se han establecido las conexiones remotas:
* Capturar imagenes probando las conexiones remotas VNC.
* Ejecutar `netstat -n` en el servidor.

---

# 4. Instalación en OpenSUSE

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).

## 4.1 Ir al servidor

* Ir a `Yast -> VNC`
    * Permitir conexión remota. Esto configura el servicio `xinet`.
    * Abrir puertos VNC en el cortafuegos.
* Revisar la configuración del cortafuegos.
* Con nuestro usuario normal, ejecutar `vncserver` en el servidor para iniciar el servicio VNC.
    * Ponemos claves para las conexiones vnc a nuestro escritorio.
    * Al final se nos muestra el número de nuestro escritorio remoto.
* `vdir /home/nombrealumno/.vnc`, vemos que se nos han creado unos ficheros de configuración VNC asociados a nuestro usuario.
* Ejecutar `netstat -ntap` para comprobar que están los servicios en los puertos 5801 y 5901.
* Ejecutar `ps -ef|grep vnc` para comprobar que los servicios relacionados con vnc están en ejecución.

## 4.2 Ir a la máquina real

* Ejecutar `nmap -Pn IP-VNC-SERVER`, desde la máquina real GNU/Linux para comprobar
que los servicios son visibles desde fuera de la máquina VNC-SERVER. Deben verse
los puertos 5801, 5901, etc.

## 4.3 Ir al cliente

* `vncviewer` es un cliente VNC que viene con OpenSUSE.
* En la conexion remota, hay que especificar `IP:5901`, `IP:5902`, etc.
(Usar el número del escritorio remoto obtenido anteriormente).
* Hay varias formas de usar vncviewer:
    * `vncviewer IP-vnc-server:590N`
    * `vncviewer IP-vnc-server:N`
    * `vncviewer IP-vnc-server::590N`

## 4.4 Comprobaciones finales

Comprobaciones para verificar que se han establecido las conexiones remotas:
* Capturar imagenes probando las conexiones remotas VNC,
* Ejecutar `netstat -ntap` en el servidor.
* Ejecutar `vncserver -list` en el servidor.

> **NOTA**
>
> * Enlace sobre [VNC server OpenSUSE Leap 42.3](https://doc.opensuse.org/documentation/leap/reference/html/book.opensuse.reference/cha.vnc.html#sec.vnc.viewer)
> * Enlace sobre [VNC server OpenSUSE 13.2](https://www.howtoforge.com/tutorial/vnc-server-on-opensuse-13.2/)
> * Como cliente VNC podemos usar también `krdc`.
> * Además de `Yast`, podemos puede usar el comando `vncserver` para
gestionar el servidor VNC.

---

# 5. DISPLAY 0 en GNU/Linux

* [Enlace de interés](https://wiki.archlinux.org/index.php/TigerVNC_)

Cuando queremos ejecutar vncserver para controlar directamente la pantalla local
usaremos `x0vncserver` de tigervnc.

* `x0vncserver -display :0 -passwordfile /home/nombrealumno/.vnc/passwd`
* Para más información, véase `man x0vncserver`

---

# ANEXO A

## A.1 Instalacion en Debian

* En Debian se puede usar `tightvncserver` como VNC server.

> * [VNC GNU/Linux] (http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m5/servidor_vnc.html)
