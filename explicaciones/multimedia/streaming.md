

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

* Necesitamos tener instalado el programa VLC (versión > 3.0.4).
    * `sudo zypper in vlc`, instalar en OpenSUSE.

---

# VLC Servidor Streaming

* Abrir el programa `VLC`
* Ir a `menú -> Medio -> Emitir`
* Pestaña `Dispositivo de captura -> Escritorio`
    * Tasa de fotogramas: `25 fps`
* Botón `Emitir`
* Fuente: `screen://` (Valor por defecto). Botón `Siguiente`.
* Destino: `RTP/MEPG Transport Stream`. Botón `Añadir`
* Configuración de destino:
    * Dirección: `IP broadcast de nuestra red` (Por ejemplo: 172.18.255.255)
    * Nombre: `Nombre de la asignatura` (Por ejemplo: FUW)
    * Botón `Siguiente`.
* Opciones de transcodificación (Valor por defecto). Botón `Siguiente`.
* Configuración de preferencias (Valor por defecto). Botón `Emitir`.

¡El servidor ya está emitiendo!

---

# VLC cliente de Streaming

* Abrir el programa `VLC`
* Ir a `menú -> Medio -> Abrir ubicación de red`
* Introducir una URL: `rtp://@` (Valor por defecto). Botón `reproducir`.

¡El cliente ya está viendo el streaming!
