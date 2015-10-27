
#Instalación Dual
Vamos a realizar una instalación dual Windows y GNU/Linux.

* Usaremos en esta práctica las versiones Windows7 y OpenSUSE 13.2.
* Entregar un documento en formato ODT o PDF con las capturas solicitadas. Incluir breves comentarios de cada captura.

> Recuerda: Pasado una semana si no vamos a usar más dicha MV, procederemos a eliminarla completamente para liberar espacio en disco.

#1. Preparar la máquina virtual:

* Crear una máquina virtual (VirtualBox).
* Configurar con: tipo Windows 7 (64 bits), RAM 1024MB, disco duro de 18GB y tarjeta de red en modo puente (bridge).

##1.1 Particionado

* Usaremos un CD-LIVE (Knoppix) para crear las particiones. Cuando inicia Knoppix 
y aparece el prompt "boot:", pondremos "knoppix lang=es" para iniciarlo en español. 
*OJO* la tecla "=" puede estar en "¡".
* Para hacer las particiones en Knoppix, abrimos un terminal. Nos ponemos como superusuario (comando "sudo bash" o "su"). Iniciamos la herramienta de particionado con el usuario root ejecutando el comando "gparted".
* Gparted -> Dispositivo -> Crear tabla de particiones -> MSDOS.
* Crear las siguientes particiones:
    * Una partición primaria, tipo NTFS para Windows (12GB),
    * Una primaria FAT32 para datos (100MB).
    * Crearemos una partición extendida que coja todo el disco restante.

* Dentro de la extendida haremos las siguientes particiones lógicas:
    * Área de intercambio o SWAP (500MB),
    * Partición home (montar /home) de tamaño 100MB y con formato ext3.
    * Partición del sistema (montar /) de tamaño 5GB y con formato ext4.
    * Quedarán libres 300 MB más o menos. Lo dejamos sin usar.

* Capturar pantalla del gparted con las particiones solicitadas, y apagar MV.

#2. Instalación del primer SO
Vamos a instalar primero Windows.
* Montamos ISO de instalación de Windows en la MV y la iniciamos.
* Idioma español. Leer licencia antes de aceptarla.
* Instalación personalizada. Elegir la partición 1 para instalar el SO.
Configuración:
* Poner nombre de usuario igual que la práctica anterior.
* Poner como nombre del equipo **DUALWprimer-apellido-del-alumno**, en minúsculas y sin espacios.
Producto/Licencia:
* Clave de producto: La dejamos vacía por esta vez.
* Desactivar la opción *Activar Windows automáticamente*
* Configuración de Red:
    * Red de trabajo y nombre del grupo de trabajo AULA108.
    * IP estática 172.19.XX.12
    * Máscara de red 255.255.0.0
    * Gateway 172.19.0.1
    * DNS 8.8.4.4
* Comprobar la conexión de red haciendo `ping www.google.es`.
* Capturar imagen como la siguiente. Mostrando las particiones del disco duro 
(`Ir a miEquipo -> Btn Derecho -> Administrar -> Almacenamiento`). 

![dual-win7-particiones] (./dual-win7-particiones.png)

* Modificar la configuración de Windows Update y ponerla como Deshabilitada (Sin descargas ni notificaciones).
* Ir a miEquipo -> Btn Derecho -> Propiedades -> Cambiar conf. equipo. Poner nombre grupo de trabajo AULA108. Reiniciar
* Ir a miEquipo -> Btn derecho -> Propiedades. Capturar imagen nombre de equipo y grupo de trabajo.
* Capturar imagen com ola siguiente:

![dual-win7-nombres] (./dual-win7-nombres.png)

> OJO: Cuando terminen la instalación de Windows debemos acordarnos de "quitar" la ISO (CD de instalación) de la MV.

#3. Instalación del segundo SO
A continuación vamos a instalar GNU/Linux.
* Ponemos ISO en la MV y la iniciamos.
* Pulsar F2 para cambiar el idioma a Español.
* Leer licencia y aceptar si corresponde.
* Elegir instalación nueva, y DESACTIVAR la configuración automática. No vamos a usar la configuración automática porque la vamos a personalizar según las especificaciones de esta práctica.
* Elegir zona (Canarias)
* Selección de entorno gráfico: Elegir el entorno gráfico que queramos.
    * Gnome y KDE son bonitos pero recargados y pesados. Es probable que no quepan en el espacio disponible.
    * XFCE y LXDE son escritorios ligeros y ocupan poco espacio en disco. 

Particionado:
* DESMARCAR "Proponer partición home independiente"
* Editar configuración para asegurarnos de que es correcta según el enunciado.
* Capturar imagen como la siguiente:

![dual-suse-particiones1] (./dual-suse-particiones1.png)

* Botón derecho sobre la partición ext3 -> para montar /home

![dual-suse-home] (./dual-suse-home.png)

* Botón derecho sobre partición ext4 -> para montar /

![dual-suse-raiz] (./dual-suse-raiz.png)

* **IMPORTANTE**. Montar la partición donde tenemos instalado el SO Windows en la ruta /mnt/windows.
* Aceptar.
* Capturar imagen como la siguiente:

![dual-suse-particiones2] (./dual-suse-particiones2.png)

* Verificar y siguiente.

![dual-suse-particiones3] (./dual-suse-particiones3.png)

* Nombre de usuario y la clave igual que la práctica anterior.
* Desmarcar inicio de sesión automático.
* Habilitar y abrir el Servicio SSH. NOTA: Esto lo activamos para permitir el acceso remoto a esta máquina virtual.
* Comprobar que todo es correcto y procedemos a "Instalar".

![dual-suse-verificar] (./dual-suse-verificar.png)

* Poner como nombre del host o equipo DUALXprimer-apellido.
* Poner como nombre de dominio el 1er apellido en minúsculas sin tildes.
* Poner NO a "Modificar nombre de HOST mediante DHCP". En caso contrario el nombre del equipo puede cambiar en cada reinicio.

![dual-suse-equipo] (./dual-suse-equipo.png)

* ¿Desea actualización en línea? -> OMITIR actualización.
No vamos a actualizar el SO en este momento. Esto lo hacemos para minimizar el consumo 
de ancho de banda que se produce en las actualizaciones.
* Entrar al sistema.
* Comprobar que la conexión de red está bien haciendo `ping www.google.es`
* Reiniciar el sistema.
* Capturar imagen como la siguiente donde se muestra el arranque inicial 
donde se ve un menú para eligir el sistema operativo a iniciar.

![dual-menu-final] (./dual-menu-final.png)

Con el SO instalado:
* Entrar al sistema con nuestro usuario.
* Vamos al la herramienta `YAST -> Ajustes de red` para poner los siguientes valores:
    * IP estática 172.19.XX.52
    * Máscara 255.255.0.0
    * Gateway 172.19.0.1
    * DNS 8.8.4.4
    * Nombre de equipo: DUALX1er-apellido-del-alumno
* Abrir un terminal.
* Ejecutar `ping www.google` para comprobar la conexión de red.
* Ejecutar comando su para convertirnos en superusuario (clave de root).
* Como superusuario (root) ejecutar los comandos siguientes y capturar su salida:
```
        date (Muestra la fecha/hora del sistema)
        hostname (Muestra nombre del sistema)
        uname -a (Muestra datos del kernel)
        ip a (Muestra información de red)
        df -hT (Muestra información de ocupación del disco)
        fdisk -l (Muestra información de particiones)
        lsblk
```

#ANEXO
##A1. Servicio SSH en OpenSUSE

* En la ventana de la MV, ir a panel superior de VirtualBox-> dispositivos -> montar CD de GNU/Linux.
* Ejecutar como superusuario:
```
    ifdown eth0
    ifup eth0
    yast2
```

* Ir a Configuración del contafuegos -> Servicios Autorizados -> Añadir Servicio SSH.
* Ir a Servicios del sistema -> sshd -> Activar
* Cuando la instalación termine, volver a ir a Dispositivos -> desmontar el CD de GNU/Linux.
* Cerrar terminal y apagar el sistema

##A2. Configuración de red en OpenSUSE
Fichero de configuración de red de OpenSUSE `/etc/sysconfig/network/ifcfg-eth0`

Contenido:
```
    BOOTPROTO='dhcp'
    STARTMODE='auto'
```

##A3. Menu de arranque Windows

Si al iniciarse la MV no aparece Windows en el menú de arranque, entonces 
lo podemos solucionar haciendo los siguientes paso:

* `su` (Convertirnos en superusuario)
* `cd /etc/grub.d` (Cambiamos de directorio)
* `zypper install nano` (Para instalar el programa nano)
* `nano 11_windows` (Creamos archivo nuevo con el siguiente contenido)

```
#!/bin/sh -e
echo "Adding Windows 7" >&2
cat<<EOF
menuentry "Windows 7 (david)" {
set root=(hd0,1)
chainloader +1
}
EOF
```
* Grabar el archivo y salir de nano
* `chmod +x 11_windows`
* `grub2-mkconfig -o /boot/grub2/grub.cfg` (Actualizamos el GRUB2 con el nuevo cambio)
* `reboot` (Reiniciamos la MV)

