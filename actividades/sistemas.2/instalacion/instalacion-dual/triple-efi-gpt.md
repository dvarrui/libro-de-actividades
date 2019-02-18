
`EN CONSTRUCCIÓN!!!`

# Instalación Triple-UEFI-GPT

* Vamos a instalar 3 SSOO en una MV UEFI con GPT:
    * Windows 7
    * GNU/Linux OpenSUSE
    * GNU/Linux Arch
* Entregar un documento en formato ODT o PDF con las capturas solicitadas.
Incluir breves comentarios de cada captura de pantalla.

---

# 1. Preparar la máquina virtual

* Crear una máquina virtual (VirtualBox).
* Configurar con:
    * Activar tipo UEFI-BIOS
    * Tipo Windows 7 (64 bits)
    * RAM 1024MB
    * Disco duro de 60GB
    * Tarjeta de red en modo puente (bridge).

## 1.1 Particionado

* Usaremos un CD-LIVE (Knoppix) para crear las particiones.
    * Recuerda `knoppix lang=es` para iniciarlo en español.
* `Gparted -> Dispositivo -> Crear tabla de particiones -> GPT`.
* Crear las siguientes particiones:

| id | Partición | Tamaño | Formato |
| -- | --------- | ------ | ------- |
| 01 | BootEFI   | 100 MB | FAT     |
| 02 | Windows 7 |  20 GB | NFTS    |
| 03 | Swap      |   2 GB | swap    |
| 04 | OpenSUSE  |  14 GB | Btrfs   |
| 05 | Arch      |  14 GB | Ext4    |
| 06 | Home      |  10 GB | XFS     |

* Capturar pantalla del gparted con las particiones solicitadas, y apagar MV.

---

# 2. Instalación del primer SO

> Enlace de interés:
> * [Guía de instalación ArchLinux 2019](http://denovatoanovato.net/instalar-arch-linux/)

* Instalar Arch GNU/Linux con:
    * Partición 01 para BootEFI
    * Partición 03 para la Swap
    * Partición 05 para raíz del sistema (/)
    * Partición 06 para el home (/home)
* Nombre de usuario: `nombre-del-alumno`
* Configuración de la MV:
    * Nombre equipo: `1er-apellidoXXq1`
    * IP estática 172.AA.XX.51
* Reiniciar el sistema.
* Ejecutar comando su para convertirnos en superusuario (clave de root).
* Como superusuario (root) ejecutar los comandos siguientes y capturar su salida:
```
    date             # Muestra la fecha/hora del sistema
    uname -a         # Muestra datos del kernel
    ip a             # Muestra información de red
    hostname         # Muestra nombre del sistema
    id nombre-alumno
    ping 8.8.4.4
    host www.nba.com
    df -hT           # Muestra información de ocupación del disco
    fdisk -l         # Muestra información de particiones
    lsblk            # Muestra UUID de las particiones
```
* Capturar imagen como la siguiente donde se muestra el arranque inicial
donde se ve un menú para eligir el sistema operativo a iniciar.

---

# 3. Segunda instalación

Vamos a instalar el SO Windows.
* Montamos ISO de instalación de Windows en la MV y la iniciamos.
* Idioma español. Leer licencia antes de aceptarla.
* Instalación personalizada.
    * En la partición 02 instalar el SO.
    * En la partición 01 irá el BootEFI.
Producto/Licencia:
* Clave de producto: La dejamos vacía por esta vez.
* Si aparece la opción *Activar Windows automáticamente*, elegiremos que NO.
* [Configuración de la MV](../../../global/configuracion/windows.md)
    * Nombre equipo: `1er-apellidoXXw1`
    * IP estática 172.AA.XX.12
* Comprobar la conexión de red haciendo `ping www.google.es`.
* Capturar imagen como la siguiente. Mostrando las particiones del disco duro (`Ir a miEquipo -> Btn Derecho -> Administrar -> Almacenamiento`).
* Modificar la configuración de Windows Update y ponerla como Deshabilitada
(Sin descargas ni notificaciones).
* Ir a `miEquipo -> Btn Derecho -> Propiedades -> Cambiar conf. equipo`.
Poner nombre grupo de trabajo indicado. Reiniciar
* Ir a `miEquipo -> Btn derecho -> Propiedades`. Capturar imagen nombre de equipo y grupo de trabajo.
* Cuando terminen la instalación de Windows debemos acordarnos de desmontar
la ISO (CD de instalación) de la MV.
* Comandos de comprobación: date, ipconfig, hostname, whoami, ping 8.8.4.4, nslookup www.nba.com.
* Reiniciar el sistema.
* Capturar imagen como la siguiente donde se muestra el arranque inicial
donde se ve un menú para eligir el sistema operativo a iniciar.
    * ¿Hemos perdido algo?

---

# 4. Instalación del tercer SO

A continuación vamos a instalar un SO GNU/Linux (OpenSUSE)
* [Configurar MV OpenSUSE](../../../global/configuracion/opensuse.md)
* [Acceso remoto MV OpenSUSE](../../../global/acceso-remoto/opensuse.md)

## 4.1 Empezamos con la ISO

* Hacer una instantánea de la MV antes de continuar.
* Ponemos ISO en la MV y la iniciamos.
* Pulsar F2 para cambiar el idioma a Español.
* Leer licencia y aceptar si corresponde.

## 4.2 Particionado

* Elegir instalación nueva, y DESACTIVAR la configuración automática. No vamos a usar la configuración automática porque la vamos a personalizar según las especificaciones de esta práctica.
* Entrar en el modo experto.
* Si no se ven las particiones que habíamos creado pulsar en `Volver a explorar dispositivos`.
* Esquema de uso de las particiones:
    * Partición 03 para la Swap
    * Partición 04 para la raíz (/)
    * Partición 06 para el home (/home).
    * Montar la partición 02 en `/mnt/windows`.
    * Montar la partición 05 en `/mnt/arch`

## 4.3 Entorno, usuario y SSH

* Elegir zona horaria (Canarias)
* Selección de entorno gráfico: MATE

> En teoría podríamos elegir el entorno gráfico que quisiéramos.
> * Gnome y KDE son bonitos pero recargados y pesados. Es probable que no quepan en el espacio disponible.
> * MATE, XFCE y LXDE son escritorios ligeros y ocupan poco espacio en disco.

* Crear usuario con `nombre-del-alumno`.
* Desmarcar inicio de sesión automático.
* Habilitar y abrir el Servicio SSH. NOTA: Esto lo activamos para permitir el acceso remoto a esta máquina virtual ([Configurar acceso remoto](../../../global/acceso-remoto/opensuse.md))

## 4.4 Instalar

* Comprobar que todo es correcto y procedemos a "Instalar".
* ¿Desea actualización en línea? -> OMITIR actualización.
No vamos a actualizar el SO en este momento. Esto lo hacemos para minimizar el consumo de ancho de banda que se produce en las actualizaciones.
* Entrar al sistema.

## 4.5 Con el SO instalado

* Entrar al sistema con nuestro usuario.
* Vamos al la herramienta `YAST -> Ajustes de red` para poner los siguientes valores:
    * [Configuración de la MV](../../../global/configuracion/opensuse.md)
    * IP estática 172.AA.XX.31
    * Poner como nombre del host o equipo `1er-apellidoXXg1`.
    * Poner NO a "Modificar nombre de HOST mediante DHCP". En caso contrario el nombre del equipo puede cambiar en cada reinicio.
* Abrir un terminal.
* Comprobar con: date, ip a, hostname, whoami, ping 8.8.4.4, host www.nba.es.
* Reiniciar el sistema.
* Capturar imagen como la siguiente donde se muestra el arranque inicial
donde se ve un menú para eligir el sistema operativo a iniciar.
* Ejecutar comando su para convertirnos en superusuario (clave de root).
* Como superusuario (root) ejecutar los comandos siguientes y capturar su salida:
```
    date             # Muestra la fecha/hora del sistema
    uname -a         # Muestra datos del kernel
    ip a             # Muestra información de red
    hostname         # Muestra nombre del sistema
    id nombre-alumno
    ping 8.8.4.4
    host www.nba.com
    df -hT           # Muestra información de ocupación del disco
    fdisk -l         # Muestra información de particiones
    lsblk            # Muestra UUID de las particiones
```
* Al iniciarse la MV deben aparecer todos SSOO en el menú de inicio (Boot Loader).
