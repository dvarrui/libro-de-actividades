
# Streaming con VLC

VLC es un programa de software libre multiplataforma que sirve principalmente
para visualizar vídeos o escuchar audios de distintos formatos. Pero además,
las versiones actuales de VLC nos permiten hacer Streaming de vídeo de forma
sencilla dentro de nuestra red. Veamos cómo.

Pasos:
1. Servidor VLC: Activar la función de streaming.
2. Cliente VLC: conectar con el servidor.

¡Sencillo!

---

# Instalar VLC en las máquinas

* Necesitamos tener instalado el programa VLC.
    * `sudo zypper in vlc`, instalar en OpenSUSE.
    * `sudo apt install vlc`, instalar en Debian/Ubuntu.
    * [Descargar VLC](https://www.videolan.org/vlc/) para otros SSOO.

---

# VLC Servidor Streaming

* Abrir el programa `VLC`
* Ir a `menú -> Medio -> Emitir`
* Pestaña `Dispositivo de captura -> Escritorio`
    * Tasa de fotogramas: `25 fps`
* Botón `Emitir`
* Fuente: `screen://` (Valor por defecto). Botón `Siguiente`.
* Destino: `RTP/MEPG Transport Stream`.
    * **Botón `Añadir`**
* Configuración de destino:
    * Dirección: `IP broadcast de nuestra red` (Por ejemplo: 172.AA.255.255)
    * Nombre: `Nombre de la asignatura o del profesor` (Por ejemplo: David)
    * Botón `Siguiente`.
* Opciones de transcodificación (Valor por defecto).
    * Botón `Siguiente`.
* Configuración de preferencias (Valor por defecto).
    * Botón `Emitir`.

> **Si hay problemas revisar el cortafuegos**. En el caso de OpenSUSE abrá que abrir el cortafuegos para poder emitir.

¡El servidor ya está emitiendo!

---

# VLC cliente de Streaming

* Los alumnos deben tener instalado el VLC en la máquina real y/o virtual.
    * En el caso de MV, asegurarse de que le red está en modo puente.
    * En el cado de OpenSUSE quizás abrá que abrir el cortafuegos.
* Abrir el programa `VLC`
* Ir a `menú -> Medio -> Abrir ubicación de red`
* Introducir una URL: `rtp://@` (Valor por defecto). Botón `reproducir`.

¡El cliente ya está viendo el streaming!

---

# ANEXO

## Averiguar la IP de broadcast de nuestra

En GNU/Linux ejecutamos el comando `ip a`:

```
david@camaleon:~/proy/tools/libro-de-actividades> ip a
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host
       valid_lft forever preferred_lft forever
2: enp2s0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 50:b7:c3:06:e2:d1 brd ff:ff:ff:ff:ff:ff
    inet 192.168.1.100/24 brd 192.168.1.255 scope global enp2s0
       valid_lft forever preferred_lft forever
    inet6 fe80::52b7:c3ff:fe06:e2d1/64 scope link
       valid_lft forever preferred_lft forever
```

Fijémonos solamente en las siguientes líneas:
```
2: enp2s0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 50:b7:c3:06:e2:d1 brd ff:ff:ff:ff:ff:ff
    inet 192.168.1.100/24 brd 192.168.1.255 scope global enp2s0
```

Más concretamente en:

`    inet 192.168.1.100/24 brd 192.168.1.255 scope global enp2s0`


¿Lo ves?... ¿Si?...

¡Eso es! **brd 192.168.1.255**
