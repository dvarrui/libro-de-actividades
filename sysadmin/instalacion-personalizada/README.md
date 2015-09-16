
#Instalación Debian personalizada

Entregar un documento en formato OFT o PDF con los pasos realizados. 
Incluir capturas de pantalla del sistema operativo funcionando.

NOTA:
* Esta máquina virtual Debian la usaremos en las próximas unidades de trabajo.

#1. Máquina virtual
* Crear una nueva MV con: tamaño de disco de 10GB, tarjeta de red en modo bridge (Puente).
* INFO: Knoppix es una distribución de GNU/Linux que viene en CD-Live. Viene provista de un conjunto de herramientas muy útiles.
* Descargar del servidor la ISO de Knoppix y el fichero md5.
* Comprobar que la descarga de los ficheros se hizo de forma correcta, ejecutando el comando siguiente: `md5sum -c nombre-de-fichero-knoppix.iso.md5`
* Iniciar la MV con CDLive de Knoppix. Cuando aparezca el prompt "boot:" pulsar F3 y leer la pantalla. Para iniciar el SSOO en español, escribir: `knoppix lang=es`
* Abrir una consola, ponerse como root (comando su) y ejecutar gparted (comando gparted).
* INFO: Ahora vamos a usar gparted para crear una partición en el disco.
* Gparted -> Dispositivo -> Crear tabla de particiones tipo MSDOS (MBR).
* Crear una partición extendida que ocupe todo el disco (Consultar documentación de gparted). Aplicar los cambios.
* Cerrar gparted y apagar Knoppix.

#2. Instalando el Sistema Operativo
* Descargar del servidor la ISO de Debian y su fichero md5.
* Comprobar que la descarga fue correcta, ejecutando el comando siguiente: `md5sum -c nombre-de-fichero-debian.iso.md5`
* Montar la ISO en la MV para comenzar la instalación.
* Elegir idioma español.
* Poner como nombre del equipo el "1er-apellido-del-alumno" en minúsculas, sin tildes ni eñes.
* Como nombre de dominio el "2do-apellido-del-alumno" en minúsculas, sin tildes ni eñes.
* La clave de root (superusuario) será el DNI del alumno con la letra en minúsculas.
* Poner como nombre de usuario el "nombre-del-alumno" en minúsculas, sin tildes ni eñes.
* Zona horaria Canarias.
* Método de particionado manual, y crear el siguiente esquema de particiones:
  a) Partición lógica para la Swap de 1GB (Tipo Área de Intercambio)
  b) Partición lógica para la Raíz del sistema (Montar /) de 7GB tipo ext4.
  c) Partición lógica para el Home (Montar /home) de 500MB tipo ext3.
  d) Partición lógica sin usar (No se monta) de 100MB de tipo ext2.
  Dejar el resto sin usar.

![act1-debian-particiones] (./act1-debian-particiones.png)

* Elegimos una réplica de red de España. El valor de Proxy lo dejamos vacío.
* En la selección de programas poner sólo "Utilidades estándar del sistema". 
Para marcar y desmarcar usar la barra espaciadora. OJO. No vamos a instalar entorno gráfico, o entorno de escritorio. 
Por el momento queremos un sistema sólo en modo texto.

![act1-debian-paquetes] (./act1-debian-paquetes.png)

* ¿Instalar el cargador de arranque GRUB en el registro principal de arranque? SI.
* Instalación completa -> Continuar.

##Con el SO instalado
* Entrar al sistema con el usuario.
* Asegurarse de que la fecha/hora del sistema son los correctos. Ejecutar el comando `date`.
* Salir con el comando "exit"
* Entrar al sistema como root (superusuario)
* Ejecutar los comandos siguientes, y capturar su salida:

```
    date (Muestra la fecha/hora del sistema)
    hostname (Muestra nombre del sistema)
    dnsdomainname (Muestra nombre de dominio)
    uname -a (Muestra datos del kernel)
    ifconfig (Muestra información de red)
    df -hT (Muestra información de ocupación del disco)
    fdisk -l (Muestra información de particiones)
    lsblk -l
    ls -l /dev/disk/by-uuid (Muestra los códigos UUID)
```

##Para acabar:
* En la ventana de la MV, ir a panel superior de VirtualBox-> dispositivos -> montar CD de Debian.
* Vamos a instalar el programa openssh para que el profesor pueda acceder remotamente a la máquina. 
Ejecutar comando como superusuario: `apt-get install openssh-server`
* Cuando la instalación termine, volver a ir a Dispositivos -> desmontar el CD de Debian.
* Apagar el sistema con el comando: `halt`
