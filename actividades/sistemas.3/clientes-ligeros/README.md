
# 1. Clientes ligeros con LTSP/Ubuntu

Entrega de la práctica:
* Crear informe usando ficheros de texto en Markdown e imágenes.
* Incluir breve explicación de clientes ligeros.
* Detallar los pasos realizados.
* Crear pequeño vídeo donde se muestra la práctica en funcionamiento. Esto es,
el momento es que arrancamos un cliente e iniciamos sesión.
* Incluir URL del vídeo subido a youtube.
* Entregar URL(Repositorio Git) del informe.
* Entregar URL(Repositorio Git) commit del informe.

> Al terminar la actividad, y antes de realizar la entrega, etiquetamos el proyecto con "ltsp".
> La etiqueta es un identificador que queda asociado a un instante de tiempo determinado del proyecto git.

Enlaces de interés:
* [Hardware de cliente ligero](https://www.youtube.com/watch?v=MgOX63SIl9I)
* [Conectar Knoppix7 con servidor LTSP de sistema operativo ](https://www.youtube.com/watch?v=UpNUHsXSxA4)

---

# 2. Preparativos

Realizar las siguientes tareas:
* Trabajaremos de forma individual.
* Atender a la explicación del profesor.
* Consultar/leer [web official de LTSP] (http://www.ltsp.org/), y los pdf que proporciona el profesor.
* [Configurar un servidor LTSP](https://trisquel.info/es/wiki/configurar-un-servidor-ltsp)

Veamos el esquema:

![Esquema](./images/ltsp-diagram.png)

---

# 3. Servidor LTSP

## 3.1 Preparar la MV Server

Crear la MV del servidor con dos interfaces de red.
* La 1º interfaz será la externa:
    * para comunicarse con Internet.
    * Configurarla en VBox como adaptador puente.
    * Consultar [configuraciones](../../global/configuracion/debian.md).
* La 2º interfaz será la interna
    * para conectarse con los clientes ligeros.
    * La IP de esta interfaz de red debe ser estática y debe estar en la misma red que los clientes
        * IP 192.168.67.1
        * Máscara de clase C
        * NO necesita puerta de enlace
    * Configurarla en VBox como "red interna".

## 3.2 Instalación del SSOO

* Instalar un SO GNU/Linux Ubuntu en la MV del servidor (El curso1718 funcionó con Xubuntu).
* Consultar [configuraciones](../../global/configuracion/debian.md).
* Incluir en el informe la salida de los comandos siguientes:
```
ip a
route -n
hostname -a
hostname -f
uname -a
sudo blkid
```

Veamos ejemplo de nombres de equipo y dominio en Debian/Ubuntu:

![names](./images/debian-host-domain-names.png)

* Crear 3 usuarios locales llamados: primer-apellido1, primer-apellido2, primer-apellido3.
* Comprobar que se puede entrar en el servidor con los tres usuarios anteriores. En caso de error, revisar que se haya creado el directorio home de cada uno.

## 3.3 Instalar el servicio SSH

[Instalar y configurar SSH Server en Ubuntu](https://github.com/dvarrui/libro-de-actividades/blob/master/actividades/global/acceso-remoto/debian.md)

## 3.4 Instalar el servicio LTSP

* Instalar servidor de clientes ligeros, según la documentación para el SO elegido.
En el caso de Debian/Ubuntu puede ser `apt-get install ltsp-server-standalone`.
* `time ltsp-build-client --arch i386` para crear una imagen de 32 bits del SO.
    * Esta imagen del SO se cargará en la memoria de los clientes ligeros cuando se inicien.
    * Ejecutamos este comando junto con el comando time para cronometrar lo que tarda.
    * Hay que tener paciencia en este punto. Tarda 40 minutos o más.
    * Para crear imágenes de 64 bits usaríamos el comando `ltsp-build-client`.
* Ejecutar `ltsp-info`, para consultar información.

## 3.5 Revisamos la configuración del servicio DHCP

Revisamos la configuración del servicio DHCP instalado junto con LTSP:
* Revisar la configuración de la tarjeta de red interna del servidor.
IP estática compatible con la configuración dhcp.
* Consultamos el fichero de configuración `/etc/ltsp/dhcpd.conf`.

Comprobar las rutas son correctas:
* Para 32 bits: `option root-path /opt/ltsp/i386`, `filename /ltsp/i386/pxelinux.0`, etc.

![dhcpdconf](./images/ltsp-dhcpdconf.png)

> Para imágenes de 64 bits: `option root-path /opt/ltsp/amd64`, `filename /ltsp/amd64/pxelinux.0`, etc.

* En el fichero `/etc/ltsp/dhcpd.conf` modificar el valor `range 192.168.67.1XX 192.168.67.2XX;`.
Donde XX es el número de puesto de cada alumno.

> **IP de la red interna**: Si se desea usar una IP diferente en la red interna entonces será necesario
modificar también el fichero del servidor DHCP `/etc/ltsp/dhcpd.conf` y luego reiniciar el servicio.

* Reiniciamos el servidor, y comprobamos que los servicios están corriendo.
![ltsp-services-running](./images/ltsp-services-running.png)

> SERVICIOS
>
> * En OpenSUSE
>     * `systemtl status isc-dhcp-server`, consultar estado del servicio.
>     * se usa el comando `systemctl` para iniciar y parar servicios.
>     * Si hay algún error deberemos consultar los logs con `journalctl`.
> * En SSOO con el antiguo System V
>     * usan `/etc/init.d/isc-dhcp-server status|start|stop`.
>     * usan `/etc/init.d/tftpd-hpa status|start|stop`.
>     * Si hay algún error deberemos consultar syslog `tail /var/log/syslog`.
> * Para cambiar las opciones del arranque por defecto, editamos fichero
>     * `/etc/default/isc-dhcp-server` para DHCP. Modificar INTERFACES
>     * `/etc/default/tftpd-hpa` para TFTP. Modificar TFTP_ADDRESS con IP:PORT. Por ejemplo `192.168.67.1:69`.

---

# 4. Preparar MV Cliente

* Crear la MV cliente1 en VirtualBox:
    * Sin disco duro y sin unidad de DVD.
    * Sólo tiene RAM, floppy
    * Tarjeta de red PXE en modo "red interna".
    * Configurar memoria gráfica a 128MB y habilitar el soporte 3D.
* Con el servidor encendido, iniciar la MV cliente1 desde red/PXE:
    * Comprobar que todo funciona correctamente.
    * Si la tarjeta de red no inicia correctamente el protocolo PXE,
    conectar disquete Etherboot en la disquetera, tal y como se indica
    en la documentación de la web de LTSP.

En la imagen podemos ver un ejemplo de la ventana de login de un cliente ligero.
Vemos como aparece la IP que proporciona el servidor DHCP del servidor LTSP al cliente.

![client](./images/ltsp-client-login.png)

* Cuando el cliente1 se conecte. Entrar con el usuario primer-apellido1.
* Ir al servidor, como superusuario y capturar la salida de los siguientes comandos:
```
who                          # Muestra los usuarios conectados al sistema
arp                          # Muestra la tabla ARP (Asociaciones de IP con MAC)
netstat -ntap | grep 192.168 # Muestras las conexiones entre los clientes y el servidor
```
* Repetir el proceso con la MV cliente2 y el usuario primer-apellido2.
* Grabar en vídeo el proceso de iniciar MV cliente2 y entrar con usuario2 mostrando el funcionamiento.

---

# 5. Personalizar los clientes

En Debian/Ubuntu podemos personalizar la configuración de los clientes ligeros,
modificando/añadiendo valores en `/opt/ltsp/i386/etc/lts.conf`.

* Vamos al servidor.
* Configurar lts.conf para permitir que una de la MV (Especificar por su MAC) pueda
acceder a un dispositivo USB conectado en local ([Ejemplo](http://manpages.ubuntu.com/manpages/artful/man5/lts.conf.5.html)).
Añadir las siguientes líneas al fichero:
```
[default]
LOCALDEV = true

[mac addres client1 separated by :]
LDM_USER_ALLOW = primer-apellido1
[mac address client2 seprated by :]
LDM_USER_ALLOW = primer-apellido2
```

* `time ltsp-update-image`, actualizar la imagen. Ejecutamos este comando junto con el
comando time para cronometrar lo que tarda.
* Hacer un pequeño vídeo granbando lo siguiente:
    * Entrar en MV cliente2 usando el usuario primer-apellido1 y primerapellido2.

---

# A. ANEXOS

En el caso de tenemos problemas con la imagen, estos son los comandos LTSP
podemos volver a actualizar la imagen.

* `ltsp-update-kernel`
* `ltsp-update-sshkeys`
* `ltsp-update-image`

> PENDIENTE DE REVISAR
>
> * Una vez iniciado el cliente ligero, tendremos el fichero /etc/lts.conf en el cliente.
Se puede consultar sus valores ejecutando el comando `getltscfg -a`.
>
> INFO Por ejemplo, `LDM_AUTOLOGIN=true` se usa en combinación con LDM_USERNAME y LDM_PASSWORD.

## A.1 Clientes ligeros con OpenSUSE

* Para el curso próximo, probar a usar OpenSUSE y/o Debian en lugar de Ubuntu.
* WtWare es una herramienta para clientes ligeros.

Via commandline(following commands to be run in terminal as root "su -"):
````
    zypper ar -r http://r.opensu.se/server:ltsp.repo
    zypper refresh
    zypper in kiwi-ltsp-prebuilt
````
* It is expected that minimum GNOME environment is installed on the server.
* Check out Network configuration instructions before proceeding.
* Launch `Easy-LTSP` GUI as root (open terminal, run "su -", provide root
password, and then run "easy-ltsp" command).
