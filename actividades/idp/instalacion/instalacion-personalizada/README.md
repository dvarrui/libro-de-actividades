
#Instalación Knoppix-Debian personalizada

Entregar un documento en formato ODT o PDF con los pasos realizados.

> NOTA:
> Esta máquina virtual Debian la usaremos en las próximas unidades de trabajo.

#1. Preparar la Máquina virtual

Capturar imagenes de los siguientes pasos:
* Crear una nueva MV con:
    * tamaño de disco de 10GB y
    * tarjeta de red en modo bridge (Puente).

#2. Knoppix

> Knoppix es una distribución de GNU/Linux que viene en CD-Live.
> Viene provista de un conjunto de herramientas muy útiles.

* Descargar del servidor la ISO de Knoppix y el fichero md5.
* Comprobar que la descarga de los ficheros se hizo de forma correcta, ejecutando el comando siguiente: `md5sum -c nombre-de-fichero-knoppix.iso.md5`
* Iniciar la MV con CDLive de Knoppix.
    * Cuando aparezca el prompt `boot:` pulsar F3.
    * Leer la pantalla.
    * Para iniciar el SSOO en español, escribir: `knoppix lang=es`
* Abrir una consola, ponerse como root (comando su) y ejecutar gparted (comando gparted).

>Ahora vamos a usar gparted para crear una partición en el disco.

* `Gparted -> Dispositivo -> Crear tabla de particiones tipo MSDOS (MBR)`
* Vamos a crear una partición extendida que ocupe todo el disco (Consultar documentación de gparted). Aplicar los cambios.
* Cerrar gparted y apagar Knoppix.

#3. Instalando el Sistema Operativo

Capturar imágenes de los siguientes pasos:
* Descargar del servidor la ISO de Debian y su fichero md5.
* Comprobar que la descarga fue correcta, ejecutando el comando siguiente: `md5sum -c nombre-de-fichero-debian.iso.md5`

NO hace falta captar imagen de los siguientes pasos:
* Montar la ISO en la MV para comenzar la instalación.
* Elegir idioma español.
* Poner como nombre del equipo el `1er-apellido-del-alumno+XX+g` en minúsculas, sin tildes ni eñes.
* Como nombre de dominio el `curso1617` en minúsculas, sin tildes ni eñes.
* La clave de root (superusuario) será el DNI del alumno con la letra en minúsculas.
* Poner como nombre de usuario el `nombre-del-alumno` en minúsculas, sin tildes ni eñes.
* Zona horaria Canarias.

* Método de particionado manual, y crear el siguiente esquema de particiones:
    1. Partición lógica para la Swap de 1GB (Tipo Área de Intercambio)
    1. Partición lógica para la Raíz del sistema (Montar /) de 7GB tipo ext4.
    1. Partición lógica para el Home (Montar /home) de 500MB tipo ext3.
    1. Partición lógica sin usar (No se monta) de 100MB de tipo ext2.
    1. Dejar el resto sin usar.
* Capturar imagen del esquema de particionado final. Veamos un ejemplo:

![act1-debian-particiones] (./images/act1-debian-particiones.png)

NO hace falta capturar imágenes de lo siguiente:
* Elegimos una réplica de red de España. El valor de Proxy lo dejamos vacío.

> Para marcar y desmarcar usar la barra espaciadora. OJO. No vamos a instalar entorno gráfico, o entorno de escritorio.
> Por el momento queremos un sistema sólo en modo texto.

* En la selección de programas marcamos *Utilidades estándar del sistema* y *SSH Server*.

![act1-debian-paquetes] (./images/act1-debian-paquetes.png)

* ¿Instalar el cargador de arranque GRUB en el registro principal de arranque? SI.
* Instalación completa -> Continuar.

##Con el SO instalado
NO hace falta capturar imágenes de lo siguiente:
* Entrar al sistema como root (superusuario)
* Configurar la tarjeta de red:
    * Enlace sobre [configurar la tarjeta de red]
(http://www.driverlandia.com/configurar-tarjeta-de-red-con-ip-estatica-en-debian-sin-interfaz-grafica/) con:
    * [Configuración de la MV](../../../globa/configuracion-aula109.md)
* Entrar al sistema como root (superusuario)
* Capturar imagen de los siguientes comandos:
```
    date (Muestra la fecha/hora del sistema)
    hostname -a (Muestra nombre del sistema)
    hostname -d (Muestra nombre de dominio)
    uname -a (Muestra datos del kernel)
    ip a (Muestra información de red)
    df -hT (Muestra información de ocupación del disco)
    fdisk -l (Muestra información de particiones)
    lsblk (Muestra información de las particiones)
    blkid (Muestra los códigos UUID de las particiones)
```
* Salir con el comando `exit`.

##Para acabar:

NO hace falta capturar imagen de lo siguiente:
* En la ventana de la MV, ir a panel superior de VirtualBox-> dispositivos -> montar CD de Debian.
* Configuración del [Acceso remoto SSH](../../../globa/acceso-remoto.md)

> Vamos a instalar el programa openssh para que el profesor pueda acceder remotamente a la máquina.
> * Ejecutar comando como superusuario: `apt-get install openssh-server` y
> * Configurar `/etc/ssh/sshd_config` con `PermitRootLogin yes`

* Cuando la instalación termine, volver a ir a Dispositivos -> desmontar el CD de Debian.
* Apagar el sistema con el comando: `halt`
