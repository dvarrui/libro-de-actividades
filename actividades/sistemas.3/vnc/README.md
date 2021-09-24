
# 0. Introducción VNC

> En el caso de la entrega por GIT:
> * URL con la ruta al archivo del informe dentro del repositorio del alumno.
> * URl commit del repositorio con la versión entregada.
> * Etiquetaremos la entrega en el repositorio Git con `vnc`.

## 0.1 Propuesta de rúbrica

| Sección | Bien(2) | Regular(1) | Poco adecuado(0) |
| ------- | ------- | ---------- | ---------------- |
| (2.1) Comprobaciones ||||
| (4.1) Comprobaciones ||| |

## 0.2 Configuraciones

Conexiones remotas con VNC:

| MV | OS       | IP           | Rol        | Detalles              |
| -- | -------- | ------------ | ---------- | --------------------- |
|  1 | Windows  | 172.AA.XX.11 | Slave VNC  | Instalar servidor VNC |
|  2 | Windows  | 172.AA.XX.12 | Master VNC | Instalar cliente VNC  |
|  3 | OpenSUSE | 172.AA.XX.31 | Slave VNC  | Instalar servidor VNC |
|  4 | OpenSUSE | 172.AA.XX.32 | Master VNC | Instalar cliente VNC  |

> Instalar OpenSUSE con el escritorio XFCE

---

# 1. Windows: Slave VNC

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).
* Descargar `TightVNC`. Esta es una herramienta libre disponible para Windows.
* En el servidor VNC instalaremos `TightVNC -> Custom -> Server`. Esto es el servicio.
* Revisar la configuración del cortafuegos del servidor VNC Windows para permitir VNC.

## 1.2 Ir a una máquina con GNU/Linux

* Ejecutar `nmap -Pn IP-VNC-SERVER`, desde la máquina real GNU/Linux para comprobar
que los servicios son visibles desde fuera de la máquina VNC-SERVER. Deben verse los puertos 580X, 590X, etc.

---

# 2 Windows: Master VNC

* En el cliente Windows instalar `TightVNC -> Custom -> Viewer`.
* Usaremos `TightVNC Viewer`. Esto es el cliente VNC.

## 2.1 Comprobaciones finales

Para verificar que se han establecido las conexiones remotas:
* Conectar desde Window Master hacia el Windows Slave.
* Ir al servidor VNC y usar el comando `netstat -n` para ver las conexiones VNC con el cliente.

---

# 3. OpenSUSE: Slave VNC

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).
* Ir a `Yast -> VNC`
    * Permitir conexión remota. Esto configura el servicio `xinet`.
    * Abrir puertos VNC en el cortafuegos.
* Ir a `Yast -> Cortafuegos`
    * Revisar la configuración del cortafuegos.
    * Debe estar permitido las conexiones a `vnc-server`.

> NOTA: Podemos parar completamente el cortafuegos usando el comando `systemctl stop firewalld`.
Pero lo recomendable es tener el cortafuegos en ejecución y abrir solamente los puertos que vayamos a necesitar.

* Con nuestro usuario normal
    * Ejecutar `vncserver` en el servidor para iniciar el servicio VNC.
        * Otra opción `vncserver -interfaz [address]`.
    * Ponemos claves para las conexiones VNC a nuestro escritorio.
    * Al final se nos muestra el número de nuestro escritorio remoto.
    Apuntar este número porque lo usaremos más adelante.
* `vdir /home/nombrealumno/.vnc`, vemos que se nos han creado unos ficheros de configuración VNC asociados a nuestro usuario.
* Ejecutar `ps -ef|grep vnc` para comprobar que los servicios relacionados con vnc están en ejecución.
* Ejecutar `lsof -i -nP` para comprobar que están los servicios en los puertos VNC (580X y 590X).

> NOTA: En OpenSUSE GNU/Linux el comando `netstat -ntap` está obsoleto. Pero si aún insistimos en usarlo... tendremos que instalar el paquete `net-tools-deprecated`. Lo recomendado es usar el comando `lsof`.

## 3.1 Ir a una máquina GNU/Linux

* Ejecutar `nmap -Pn IP-VNC-SERVER`, desde una máquina GNU/Linux para comprobar que los servicios son visibles desde fuera de la máquina VNC-SERVER. Deben verse los puertos VNC (5801, 5901, etc).

---

# 4. OpenSUSE: Master VNC

* `vncviewer` es un cliente VNC que viene con OpenSUSE.
* En la conexión remota, hay que especificar `IP:5901`, `IP:5902`, etc.
(Usar el número del escritorio remoto obtenido anteriormente).
* Hay varias formas de usar vncviewer:
    * `vncviewer IP-vnc-server:590N`
    * `vncviewer IP-vnc-server:N`
    * `vncviewer IP-vnc-server::590N`


## 4.1 Comprobaciones finales

Comprobaciones para verificar que se han establecido las conexiones remotas:
* Conectar desde GNU/Linix Master hacia GNU/Linux Slave.
    * Si tenemos problemas, cerrar la sesión en la máquina Slave,
    antes de iniciar la sesión desde la máquina Master.
* Ejecutar como superusuario `lsof -i -nP` en el servidor para comprobar las conexiones VNC.
* Ejecutar `vncserver -list` en el servidor.

---

# 5. Comprobaciones con SSOO cruzados

* Conectar el cliente GNU/Linux con el Servidor VNC Windows.
Usaremos el comando `vncviewer IP-vnc-server` sin especificar puerto alguno.
* Ejecutar `netstat -n` en el servidor Windows.
* Conectar el cliente Windows con el servidor VNC GNU/Linux.
* Ejecutar en el servidor GNU/Linux `lsof -i -nP`.

---

# 6. DISPLAY 0 en GNU/Linux

> [Enlace de interés](https://wiki.archlinux.org/index.php/TigerVNC_)

Cuando queremos ejecutar VNC en GNU/Linux para controlar directamente la pantalla local usaremos el comando `x0vncserver`.
* Vamos a usar las 2 MV GNU/Linux.
* Ir al servidor.
* `x0vncserver -display :0 -passwordfile /home/nombre-alumno/.vnc/passwd`. Para más información, véase `man x0vncserver`
* Ir al cliente. Usar nmap para comprobar que el puerto 5900 del servidor está abierto.
* Probar a conectarnos con el servidor (`vncviewer IP-VNC-SERVER:5900`).
* `lsof -i -nP`.
