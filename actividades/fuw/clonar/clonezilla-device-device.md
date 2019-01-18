
# Clonezilla device-device EFI

Vamos a hacer una práctica de clonación usando el software Clonezilla.

# 1. Entrega

* Trabajar de forma individual.
* Entregar secuencia de imágenes de cada uno de los pasos del trabajo realizado.

---

# 2. Preparativos

* Escojamos una MV que ya tengamos instalada y que estemos usando.
* Añadir un 2º disco duro del mismo tamaño que el disco original (Puede ser un poco mayor, pero nunca menor).
    * VirtualBox -> Seleccionar MV.
    * Configuración -> Almacenamiento -> SATA.
    * Añadir disco duro con el nombre `target-nombrealumnoXX.vdi`.
* Capturar imagen con los tamaños de los dicos VirtualBox.
* Descargar la ISO con Clonezilla del servidor Leela
(Descargar la más reciente).

---

## 3. Iniciamos Clonezilla

![vbox-add-hdd.png](./images/vbox-add-hdd.png)

Iniciar la MV con la distro Clonezilla para realizar una clonación del tipo *device-device*.
* Capturar pantalla de las opciones seleccionadas en cada paso.
* Elegimos la resolución.
* Elegimos idioma.
* Elegir mapa teclado -> querty -> Estándar -> Estándar.
* Start Clonezilla
* `device-device`
* `Beginner`
* `disco-local a disco-local`
* `Elegir abrir una sesión` cuando termine la clonación.
* Capturar imagen del comando con parámetros (Texto de color verde que se muestra en la pantalla) y que aparece en la consola antes de confirmar del inicio del proceso.
* Confirmamos para que inicie la clonación.
* Al terminar la clonación se abre un teminal.
* Consultar archivo `/tmp/ocs-onthefly-...`. Este fichero contiene el comando y los parámetros necesarios para realizar la tarea de clonación.
* Ahora ya podemos apagar la MV y quitar CDLive.
---

# 4. Comprobamos

* Quitamos el disco `clonado`. Capturar imagen VBox de los discos.
* Iniciar el sistema.
* Crear archivo `/home/NOMBREUSUARIO/Documentos/disco-original.txt`.
* Quitamos el disco `original`, añadimos el disco `clonado`. Capturar imagen VBox de los discos.
* Iniciamos el sistema.
* Comprobamos que no están los archivos que habíamos creado.

---

# ANEXO

Información sobre BootEFI, fstab y los UUID

## A.1 Modificar UUID en el fichero fstab

* Entramos en el sistema como superusuario.
* `df -hT`, para averiguar el nombre de nuestro disco (`sda`)
* Hacemos copia de seguridad del fichero `/etc/fstab`.
* Modificar el fichero, cambiando cada `UUID=XXX` por:
    * `/dev/sda1` para la boot EFI,
    * `/dev/sda2` para la swap y
    * `/dev/sda3` para el raíz.

## A.2 Configurar el gestor de arranque

Vamos a modificar el gestor de arranque para que detecte los dos SO instalados.

* Iniciar SO1.
* `cp /boot/grub2/grub.cfg /boot/grub2/grub.cfg.000`
* `grub2-mkconfig -o /boot/grub2/grub.cfg`
* Crear algunos archivos en /home/profesor/Documentos.
* Ejecutar el comando siguiente en el SO1: `df -hT`. Para comprobar el disco que estamos usando.

Comprobamos:

* Reiniciar la MV.
* Iniciar el SO2,
* Ejecutar el comando siguiente en el SO2: `df -hT`. Para comprobar el disco que estamos usando.
* Comprobar que no hay ficheros en `/home/profesor/Documentos`.

## A.3 partclone EXAMPLES

* `partclone.ext3 -c -d -s /dev/hda1 -o hda1.img`, clone /dev/hda1 to hda1.img and display debug information.
* `partclone.extfs -r -d -s hda1.img -o /dev/hda1`, restore /dev/hda1 from hda1.img and display debug information.
* `cat sda1.ext3-ptcl-img.gz.a* | gunzip -c | partclone.ext3 -d -r -s - -o /dev/sda1`, restore image from clonezilla(split, gzip,) with stdin source.

http://xarx.es/deries/2013/02/21/clonezilla-clonando-disco-o-particiones-al-vuelo/

## A.4 Grub2 + EFI

* Reconfigurar el Grub2 cuando tenemos MV EFI.

## A.5 Clonezilla

* `/usr/sbin/ocs-onthefly -g auto -e1 auto -e2 -r -j2 -f sda -t sdb`, clonar disco
sda en disco sdb.

http://www.ajpdsoft.com/modules.php?name=News&file=article&sid=524

Clonezilla nos permite elegir varios programas de clonación.

```
-q2 Prioridad: partclone > partimage > dd.
-q1 Prioridad: sólo dd (soporta todos los sistemas de archivos, pero ineficiente).
-q Prioridad: ntfsclone > partimage > dd
```

Configuraremos los siguientes parámetros para Clonezilla:

```
-c: el cliente espera confirmación antes de la clonación.
-j2: clonar los datos ocultos entre el MBR y la 1a partición.
-nogui: usar únicamente el modo texto, no TUI/GUI.
-a: NO forzar el activar el DMA en el HD.
-rm-win-swap-hib: eliminar archivos de página e hibernación en Win si existen.
-ntfs-ok: omitir la comprobación de integridad NTFS, incluso sectores erróneo.
-rescue: continuar leyendo el siguiente bloque cuando se lea un bloque de disco erróneo.
-fsck-src-part: chequear y reparar el sistema de ficheros raíz antes de guardar.
-gm: generar checksum MD5 de la imagen.
-gs: generar checksum SHA1 de la imagen.
```
En nuestro caso marcaremos las opciones -c, -j2 y -rescue.

Método de compresión, a mayor compresión se requerirá más tiempo aunque el fichero de imagen será de menor tamaño.

```
-z1: usar compresión gzip (rápida pero imagen pequeña).
-z2: usar compresión bzip2 (muy lenta pero imagen pequeña).
-z3: usar compresión lzo (rápida, tamaño de la imagen mayor).
-z4: compresión lzma (más lenta pero imagen más pequeña).
-z5: compresión xz (más lenta pero imagen más pequeña).
-z6: compresión lzip (más lenta pero imagen más pequeña).
-z0: sin compresión (muy rápida pero imagen muy grande).
```

Acción a realizar cuando Clonezilla finalice la clonación:
```
-p true: no hacer nada cuando la clonación termine.
-p reboot: reiniciar el cliente cuando la clonación termine.
-p poweroff: apagar el cliente cuando la clonación termine.
```

Clonezilla nos mostrará el comando Linux que se ejecutará para realizar la copia de seguridad en imagen de la partición seleccionada. Además, podremos hacer futuras clonaciones o generaciones de imágenes ejecutando este comando directamente desde el shell (prompt), el comando:

```
/opt/drb1/sbin/ocs-sr -q2 -c -j2 -rescue -z2 -i 3000 -p true saveparts 2011-03-09-01-img sda1
```

La ventaja de poder usar el comando es que la próxima vez sólo tendremos que iniciar con el CD de Clonezilla y seleccionar en esta ventana.
