
# Clonezilla device-device BIOS

Vamos a hacer una práctica de clonación usando el software Clonezilla.

# 1. Entrega

* Trabajar de forma individual.
* Entregar un informe del trabajo realizado acompañado de capturas
de pantalla/fotos de los pasos realizados.

---

# 2. Clonación

## 2.1 Preparamos el segundo disco

* Usaremos una MV BIOS con OpenSUSE para esta práctica.
* Añadir un 2º disco duro del mismo tamaño que el disco original (Puede ser un poco mayor, pero nunca menor).
    * VirtualBox -> Seleccionar MV.
    * Configuración -> Almacenamiento -> SATA.
    * Añadir disco duro.
* Descargar la ISO con Clonezilla del servidor Leela
(Descargar la más reciente).

## 2.2 Iniciamos Clonezilla

![vbox-add-hdd.png](./images/vbox-add-hdd.png)

Iniciar la MV con la distro Clonezilla para realizar una clonación del tipo *device-device*.
* Elegimos la resolución.
* Elegimos idioma.
* Elegir mapa teclado -> querty -> Estándar -> Estándar.
* Start Clonezilla
* `device-device`
* `Beginner`
* `disco-local a disco-local`

## 2.3 Configurar el gestor de arranque

Vamos a modificar el gestor de arranque para que detecte los dos SO instalados.

* Iniciar SO1.
* `cp /boot/grub2/grub.cfg /boot/grub2/grub.cfg.000`
* `grub2-mkconfig -o /boot/grub2/grub.cfg`
* Crear algunos archivos en /home/profesor/Documentos.
* Ejecutar el comando siguiente en el SO1: `df -hT`. Para comprobar el disco que estamos usando.

---

# 3. Comprobamos

* Reiniciar la MV.
* Iniciar el SO2,
* Ejecutar el comando siguiente en el SO2: `df -hT`. Para comprobar el disco que estamos usando.
* Comprobar que no hay ficheros en `/home/profesor/Documentos`.

---

# Anexo

## Guía para clonar en la partición sda7

    Apagar el PC. Reiniciar y entrar en el SO de la partición sda7.
    Reiniciar equipo con CD Clonezilla.
    es_ES idioma español
    No tocar teclado
    Start clonezilla
    device-image
    local_dev
    enchfar pendrive y esperar a que aparezcan los mensajes [sdb]...
    Pulsat intro y elegir sdb1 (es donde está la imagen)
    Elegir directorio / y pulsar enter.
    expert mode
    restoreparts
    elegir el archivo y pulsar enter
    elegir sda7 (es la partición donde queremos clonar)
    Usar la tecla espacio para quitar las opciones: -g auto, y -j2.
    Elegir opción -k
    Elegir opción -p poweroff
    y
    y
    Esperamos y cuando termine se apaga el equipo.
    Quitar CD y pendrive del PC
    Iniciar SO sda7 para comprobar el proceso.
