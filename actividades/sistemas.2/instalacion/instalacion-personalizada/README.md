
```
Curso           : 201920, 201819
Requisitios     : Knoppix y Debian
Tiempo estimado : 4 sesiones
```
---

# Instalación personalizada

Ejemplo de rúbrica:

| Sección                      | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| ---------------------------- | ------------ | ----------- | ----------------- |
| (2.1) Checksum de las 2 ISOs | | | |
| Esquema de particionado  | | | |
| (4) Comandos de comprobación | | | |
| (5.2) SSH                    | | |.|

## Entrega

* Entregar un documento en formato ODT o PDF con los pasos realizados.

## Requisitos

* Usaremos un CD-Live de Knoppix y otro de instalación de Debian.

> NOTA: No borrar esta máquina virtual Debian al terminar, porque la vamos a necesitar en las próximas actividades.

---

# 1. Preparar la Máquina virtual

> Capturar imágenes de los siguientes pasos.

* Crear una nueva MV VirtualBox con:
    * Nombre: idp-debian-personalizado
    * Tipo: Debian 64 bits
    * Tamaño de disco: 10GB
    * Tarjeta de red: modo bridge (Puente)

---

# 2. Knoppix

> Knoppix es una distribución de GNU/Linux que viene en CD-Live.
> Por defecto, viene provista de un conjunto de herramientas muy útiles.

## 2.1 Descargar

* Descargar del servidor indicado por el profesor:
    * la ISO de Knoppix y
    * el fichero md5.
* Vamos a comprobar que la descarga de los ficheros se hizo de forma correcta.
    * Abrir un terminal en la máquina real.
    * Movernos a la carpeta donde tengamos los ficheros que nos hemos descargado. Por ejemplo `cd Descargas`
    * `vdir`, para asegurarnos que los ficheros que vamos a usar están en nuestra carpeta actual.
    * `md5sum -c nombre-fichero.md5`, ejecutamos el comando de comprobación.
    * Debe aparecer mensaje `la suma coincide`.

## 2.2 Iniciar Knoppix

* Iniciar la MV con CDLive de Knoppix.
    * Cuando aparezca el prompt `boot:` pulsar F3.
    * Leer la pantalla.
    * Para iniciar el SSOO en español, escribir: `knoppix lang=es` (El símbolo `=` puede estar en la tecla `¿`).

> Ahora vamos a usar gparted para crear una partición en el disco.

## 2.3 Crear particiones con Gparted

* Abrir una consola
    * ponerse como root (comando su) y
    * ejecutar gparted (comando gparted).
* Se ha iniciado `Gparted`. Ir a `Dispositivo -> Crear tabla de particiones tipo MSDOS (MBR)`
* Vamos a crear una partición extendida que ocupe todo el disco (Consultar documentación de gparted). Aplicar los cambios.
* Cerrar gparted y apagar Knoppix.

---

# 3. Instalar SO Debian

## 3.1 Descargar los ficheros

> Capturar imágenes de los siguientes pasos

* Descargar del servidor la ISO de Debian y su fichero md5.
* Comprobar que la descarga fue correcta, ejecutando el comando siguiente:
    * `md5sum -c nombre-fichero.md5`
    * Debe aparecer mensaje `la suma coincide`.

## 3.2 Instalar SO

> NO hace falta capturar imagen de los siguientes pasos

* Montar la ISO en la MV para comenzar la instalación.
* Elegir idioma español.
* Configurar los siguientes parámetros durante el proceso de instalación según se especifican en [¿Cómo configurar la MV?](../../../global/configuracion/debian.md):
    * Nombre del equipo: `1er-apellidoXXd`
    * Nombre de dominio: `curso1920`
    * La clave de root
    * Nombre de usuario (`nombre-del-alumno`) y su clave.
* Zona horaria: elegir la de Canarias.
* Método de particionado manual. Aquí es donde vamos a empezar a personalizar nuestra instalación.
* Crear el siguiente esquema de particiones:

| Partición | Tamaño | Uso  | Montar | Tipo                |
| --------- | ------ | ---- | ------ | ------------------- |
| Lógica    | 1 GB   | Swap | No     | Área de Intercambio |
| Lógica    | 7 GB   | Raíz | /      | ext4 |
| Lógica    | 500 M  | Home | /home  | ext3 |
| Lógica    | 100 MB | Sin usar | No | ext2 |
| -         | Resto (1.4 GB) | Sin usar | No | -    |

* Capturar imagen del esquema de particionado final.

Veamos un ejemplo:

![debian-particiones](./images/debian-particiones.png)

* Elegir una réplica de red de España. El valor de Proxy lo dejamos vacío.

> Para marcar y desmarcar usar la barra espaciadora. OJO. No vamos a instalar entorno gráfico, o entorno de escritorio.
> Por el momento queremos un sistema sólo en modo texto.

* En la selección de programas (Usar la tecla ESPACIO para marcar/desmarcar):
    * NO seleccionar entorno gráfico
    * Marcar *Utilidades estándar del sistema* y
    * Marcar *SSH Server*

Veamos imagen de ejemplo:

![debian-paquetes](./images/debian-paquetes.png)

* ¿Instalar el cargador de arranque GRUB en el registro principal de arranque? SI. Esto es el disco `/dev/sda`.
* Instalación completa -> Continuar.

---

# 4. Con el SO instalado

> NO hace falta capturar imágenes de lo siguiente

* Entrar al sistema como root (superusuario)
* Vamos a configurar la tarjeta de red con la siguiente [Configuración de la MV](../../../global/configuracion/debian.md).

> Enlace de interés:
> * Información sobre [configurar la tarjeta de red](http://www.driverlandia.com/configurar-tarjeta-de-red-con-ip-estatica-en-debian-sin-interfaz-grafica/) en Debian.

* Entrar al sistema como root (superusuario)

Capturar imagen de los siguientes comandos:

```
    date         # Muestra la fecha/hora del sistema
    hostname -a  # Muestra nombre del sistema
    hostname -d  # Muestra nombre de dominio
    uname -a     # Muestra datos del kernel
    ip a         # Muestra información de red
    df -hT       # Muestra información de ocupación del disco
    fdisk --list # Muestra información de particiones (Ejecutar como superusuario)
    lsblk        # Muestra información de las particiones
    blkid        # Muestra los códigos UUID de las particiones
```
* Salir con el comando `exit`.

---

# 5. Acceso externo

## 5.1 Instalar servidor SSH
* `systemctl status sshd`, comprobamos que el servidor SSH está activo.
* En caso contrario, seguir los siguientes pasos para [instalar y configurar Servidor SSH en la MV Debian](../../../global/acceso-remoto/debian.md).

## 5.2 Comprobar que funciona el acceso SSH

* Desde la máquina real hacer `ssh root@ip-de-la-máquina-virtual`, para
comprobar que funciona bien el acceso desde fuera.
* Apagar el sistema con el comando: `halt`

---

# ANEXO

* [Linux: Should You Use Twice the Amount of Ram as Swap Space? - 201708](https://www.cyberciti.biz/tips/linux-swap-space.html)
