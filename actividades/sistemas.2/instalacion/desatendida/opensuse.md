
```
Curso           : 201920, 201819, 201718
Area            : Sistemas operativos, instalaciones
Descripción     : Instalación desatendida OpenSUSE
Requisitos      : OpenSUSE Leap 15.0
Tiempo estimado : 4 horas
```

# Instalación desatendida

Una instalación desatendida del sistema operativo ejecuta el proceso completo
de la instalación del sistema operativo de forma automática, sin hacer preguntas al usuario.

Entregar:
* Informe, capturas de imágenes o vídeo.
* Entregar XML utilizado.

---
# 1. Instalación desatendida con **autoyast**

Enlace de interés:
* ES - [Instalación desatendida con autoyast](https://dtrinf.wordpress.com/2012/11/06/instalacion-de-suse-desatendida-con-autoyast/)  
* EN - [AutoYaST Guide](https://doc.opensuse.org/projects/autoyast/)   
* ES - [Resumen de los comandos versión 13.1](https://es.opensuse.org/openSUSE:Vadem%C3%A9cum_comandos_13.1)   

# 2. Preparativos

Escogemos una MV1 con el sistema operativo OpenSUSE. Si no se hubiera creado el fichero `/root/autoinst.xml` durante la instalación entonces tenemos que crearlo como se indica a continuación.

# 2.1 Personalizamos la MV

* Crear una MV1 nueva o usar una que ya tengamos.
    * OJO: La MV deben tener configurada la opción de BIOS. NO UEFI.
    * El proceso de instalación desatendida con UEFI debe revisarse porque es diferente.
* Personalizamos nuestra máquina con los siguientes cambios:
    * Nombre de máquina `1er-apellidoXXy`.
    * Instalamos paquetes que no vengan por defecto preinstalados. Por ejemplo: `geany`, `nano`, `vim`, `git`, `dia`.
    * Creamos usuario `nombre-del-alumno`.

## 2.2 Configurar USB en la MV de VirtualBox

* Abrir VirtualBox. Ir a `Ayuda -> Acerca de` para consultar la versión que tenemos instalada. Por ejemplo: "5.2.38".
* Descargar "Oracle Extension Pack" correspondiente a mi versión de VirtualBox (https://download.virtualbox.org/virtualbox/).
Este paquete sirve para incluir los siguiente controladores: USB 2.0 and USB 3.0 Host Controller, Host Webcam, VirtualBox RDP, PXE ROM, Disk Encryption, NVMe.

![](images/virtualbox-extpack.png)

* Aceptar e instalar.
* Seleccionamos nuestra MV1 y `configuración -> USB -> Añadir`. Elegimos nuestro USB y aceptamos.

![](images/virtualbox-usb.png)

* Iniciar la MV1 y ya podemos usar el USB desde dentro de la MV1.

# 3. Crear el fichero de respuestas

Necesitamos crear el fichero `autoinst.xml`, con las respuestas a las preguntas del instalador.

Vamos a crear un fichero XML que clona la configuración de nuestro sistema actual.

* Instalamos la herramienta Autoyast (Paquetes `autoyast2`, `autoyast2-installation`).

> INFO: La Opción de `Autoinstallation Configuration` de Yast, sirve para editar/modificar un fichero de configuración XML ya existente.

* Ir a `Yast -> Crear fichero de configuración Autoyast (Autoinstallation Cloning System)`
(o por consola con `/sbin/yast2 clone_system`).
* El perfil clonado se guarda en `/root/autoinst.xml`.
* `cp /root/autoinst.xml nombre-alumnoXX.xml`. Hacemos una copia de seguridad del perfil.
* Copiamos el fichero `nombre-alumnoXX.xml` en un pendrive o en la máquina real.

# 4. Modos de acceso al fichero de respuestas (XML)

Elegir una de las siguientes formas para la instalación desatendida. Se recomienda (para empezar) escoger USB y/o ISO.

## 4.1 USB

Fichero de control en USB
* Copiamos el fichero en un pendrive para usarlo más adelante.

> Ejemplo: Copiar fichero al pendrive por comandos.
>
> * `df -hT |grep media`, consultar la ruta donde está montado el USB.
> * `cp /root/autoinst.yaml /run/media/...`, copiar el archivo XML a la ruta del USB.

## 4.2 ISO

Fichero de control dentro de la propia ISO.
* Iniciamos un programa para modificar ficheros ISO(Por ejemplo `isomaster`).
* Abrimos el fichero ISO de OpenSUSE.
* Incluir el fichero XML dentro de la ISO de instalación.
* Grabar ISO modificada.

## 4.3 CIFS

Fichero de control en una carpeta compartida de Windows de una equipo de nuestra red LAN.

## 4.4 HTTP

Fichero de control en un servidor Web (HTTP)

* Copiaremos el fichero XML en el servidor web proporcionado por el profesor, para que se accesible a través de la red. El fichero tendrá el nombre `nombre_del_alumnoXX.xml`.
* Establecer la configuración de red de forma manual, pulsando F4 -> Configuración de red.

# 5. Comenzar la instalación desatendida

* Creamos una MV2 nueva con un tamaño de disco duro similar a la MV de donde se creó el XML.
* Ponemos el DVD (ISO) de instalación de OpenSUSE en la MV2.

![](images/opensuse-boot-options.png)

* Completar `Boot Options` seleccionado la misma opción que elegimos en el apartado 4. Veamos:

## 5.1 USB

1. Ponemos pendrive con el fichero de control XML.
1. En opciones de arranque ponemos `autoyast=usb:///nombre-del-alumnoXX.xml`

> OJO que son 3 barras seguidas después de los dos puntos.

## 5.2 ISO

1. Iniciar MV con la ISO que tiene el fchero de control dentro.
1. En opciones de arranque ponemos `autoyast=file:///nombre-de-alumnoXX.xml`

> OJO que son 3 barras seguidas después de los dos puntos.

## 5.3 SMB/CIFS

Fichero de control en carpeta compartida de Windows
* `autoyast=cifs://servidor/carpeta/nombre-del-alumnoXX.xml`

## 5.4 HTTP

Fichero de control en un servidor Web (HTTP)
* Luego en Boot options `autoyast=http://ip-del-servidor-web/autoyast/nombre-de-alumnoXX.xml`.
* Poner en Boot Options información de la configuración de red. Esto es: `hostip=172.19.XX.31/16 gateway=172.19.0.1 autoyast=http://172.20.1.2/autoyast/nombre-de-alumnoXX.xml`

A continuación debe comenzar la instalación de forma desatendida con las opciones especificadas en el fichero XML.

---
# ANEXO A

> IDEA: Buscar dentro de la ISO, los ficheros RPM del software que queremos instalar. Si no están, descargarlos de Internet y grabarlos dentro de la ISO.
