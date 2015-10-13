# 1. Clientes ligeros con LTSP
Entrega de la práctica:
* Crear informe usando ficheros de texto en Markdown.
* Incluir breve explicación de clientes ligeros.
* Detallar los pasos realizados.
* Crear vídeo donde se muestra la práctica en funcionamiento.
* Incluir URL del vídeo subido a youtube.
* Entregar URL al informe con su HASH del repositorio GIT.

> Al terminar la actividad, y antes de realizar la entrega, etiquetamos el proyecto con "ltsp".
> `git tag ltsp`
> La etiqueta es un identificador que queda asociado a un instante de tiempo determinado del proyecto git.

# 2. Preparativos
Realizar las siguientes tareas:
* Trabajaremos de forma individual.
* Usaremos 2 MVs para montar clientes ligeros con LTSP.
* Atender a la explicación del profesor.
* Consultar/leer [web official de LTSP] (http://www.ltsp.org/), y los pdf 
  que proporciona el profesor.

Veamos el esquema:

![Esquema](./ltsp-diagram.png)

# 3. Servidor LTSP
## 3.1 Preparar la MV Server
La MV del servidor necesitará dos interfaces de red, una interfaz externa:
* para comunicarse con Internet.
* Configurarla en VBox como adaptador puente.
* IP estática 172.18.XX.41
y una interfaz interna
* para conectarse con los clientes ligeros.
* La IP de esta interfaz de red debe ser estática y debe estar en la misma red que los clientes (IP 192.168.0.1).
* Configurarla en VBox como "red interna".

## 3.2 Instalación del SSOO
* Instalar un SO GNU/Linux en la MV.
* Nombre de usuario: nombre-del-alumno, en minúsculas, sin tildes ni eñes. 
* Clave de root, poner como clave el DNI con la letra en minúsculas.
* Poner como nombre de equipo el primer apellido del alumno en minúsculas y sin tildes.
* Poner como nombre de dominio el segundo apellido del alumno en minúsculas y sin tildes.
* Incluir en el informe la salida de los comandos siguientes: 
```
ip a
route -n
hostname -a
hostname -f
uname -a
blkid
```

Veamos ejemplo de nombres de equipo y dominio en Debian/Ubuntu:

![names](./debian-host-domain-names.png)


[INFO] En OpenSUSE podemos usar la herramienta Yast2 para modificar cómodamente dichos valores.
* Crear 3 usuarios locales llamados: primer-del-apellido-alumno1, primer-del-apellido-alumno2,
primer-del-apellido-alumno3.

## 3.3 Instalar el servicio LTSP
* Instalar el servidor SSH `apt-get install openssh-server`.
* Instalar servidor de clientes ligeros, según la documentación para el SO elegido. 
En el caso de Debian/Ubuntu puede ser `apt-get install ltsp-server-standalone`.
* Ahora vamos a crear un imagen del SO a partir del sistema real haciendo `ltsp-build-client`.
La imagen del SO se cargará en la memoria de los clientes ligeros.

> **32 bits o 64 bits**
> 
> Si el servidor es de 64-bits pero los clientes tienen arquitectura de 32-bits 
entonces usar el comando siguiente `ltsp-build-client --arch i386` para crear una imagen
de 32 bits.
>

> **Comandos LTSP**
>
> * `ltsp-update-image`: Para volver a actualiza la imagen
> * `ltsp-info`: Para consultar información
>

Revisamos la configuración del servicio DHCP instalado junto con LTSP:
* Revisar la configuración de la tarjeta de red interna del servidor. 
IP estática compatible con la configuración dhcp.
* Consultamos el fichero de configuración `/etc/ltsp/dhcpd.conf`
* Por defecto, este fichero de configuración establece que la imagen que hemos creado 
debe estar en la ruta `/ltsp/i386/pxelinux.0` del servicio TFTP. 
* Como el servicio TFTP tiene configurado como directorio de trabajo `/var/lib/tftpboot/`
 entonces, el fichero de imagen debe estar en `/var/lib/tftpboot/ltsp/i386/pxelinux.0`
* Comprobarlo.

> **IP de la red interna**
> Si se desea usar una IP diferente en la red interna entonces será necesario
modificar también el fichero del servidor DHCP `/etc/ltsp/dhcpd.conf` y luego reiniciar el servicio.

* Reiniciamos el servidor, y comprobamos que los servicios están corriendo.
![ltsp-services-running](./ltsp-services-running.png)

> SERVICIO DHCP
>
> Otra forma de comprobar si el servicio `/etc/init.d/isc-dhcp-server status`.
> * Si el servidor DHCP no se ha iniciado automáticamente al reiniciar el equipo, entonces
vamos a intentar iniciarlo manualmente con `/etc/init.d/isc-dhcp-server start`.
> * Si hay algún error deberemos consultar syslog `tail /var/log/syslog`.
> * Para cambiar las opciones del arranque del servicio DHCP editamos fichero `/etc/default/isc-dhcp-server`.
Y establecemos la variable INTERFACES con el nombre del interfaz de red donde debe trabajar.
>

> SERVICIO TFTP
>
> Otra forma de comprobar si el servicio `/etc/init.d/tftpd-hpa status`.
> * Si el servicio TFTP DHCP no se ha iniciado automáticamente al reiniciar el equipo, entonces
vamos a intentar iniciarlo manualmente con `/etc/init.d/tftpd-hpa start`.
> * Si hay algún error deberemos consultar syslog `tail /var/log/syslog`.
> * Para cambiar las opciones del arranque del servicio TFTP editamos fichero `/etc/default/tftpd-hpa`.
Y establecemos la variable TFTP_ADDRESS al valor de la IP:PORT de la interfaz de trabajo, esto es `192.168.0.1:69`.
>

# 4. Preparar MV Cliente
Crear la MV cliente en VirtualBox:
* Sin disco duro y sin unidad de DVD.
* Sólo tiene RAM, flopy
* Tarjeta de red PXE en modo "red interna".

Con el servidor encendido, iniciar la MV cliente desde PXE:
* Comprobar que todo funciona correctamente.
* Si la tarjeta de red no inicia correctamente el protocolo PXE, 
conectar disquete Etherboot en la disquetera, tal y como se indica en la documentación de la web de LTSP.

En la imagen podemos ver un ejemplo de la ventana de login de un cliente ligero. 
Vemos como aparece la IP que proporciona el servidor DHCP del servidor LTSP al cliente.

![client](./ltsp-client-login.png)

Cuando el cliente se conecte
* entramos con los usuarios apellido-del-alumno[1-3]
* Ir al servidor, como superusuario y capturar la salida de los siguientes comandos:
```
arp
whoami
who
```
* Reiniciar la MV cliente y grabar vídeo mostrando el funcionamiento.

#5. ANEXOS
Los anexos siguientes son resúmenes de la documentación proporcionada por la página web de LTSP.

##5.1 Personalización de los clientes
En Debian/Ubuntu podemos personalizar la configuración de los clientes ligeros, 
modificando/añadiendo valores en /var/lib/tftpboot/ltsp/i386/ltsp.conf


##5.2 For openSUSE 12.3

Via commandline(following commands to be run in terminal as root "su -"):

    zypper ar -r http://r.opensu.se/server:ltsp.repo
    zypper refresh
    zypper in kiwi-ltsp-prebuilt

It is expected that minimum GNOME environment is installed on the server.

Check out Network configuration instructions before proceeding.

Launch Easy-LTSP GUI as root (open terminal, run "su -", provide root password, and then run "easy-ltsp" command). 
