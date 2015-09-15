# 1. Clientes ligeros con LTSP
Entrega de la práctica:
* Crear informe usando la plantilla HTML.
* Incluir breve explicación de clientes ligeros.
* Detallar los pasos realizados.
* Crear vídeo donde se muestra la práctica en funcionamiento.
* Incluir URL del vídeo subido a youtube.
* Entregar URL al informe con su HASH del repositorio GIT.

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
* IP estática 172.19.XX.11
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
* Incluir en el informe la salida de los comandos siguientes: `ifconfig`, `route -n`, `hostname -a`, y `hostname -f`

Veamos ejemplo de nombres de equipo y dominio en Debian/Ubuntu:

![names](./debian-host-domain-names.png)


[INFO] En OpenSUSE podemos usar la herramienta Yast2 para modificar cómodamente dichos valores.
* Crear 3 usuarios locales llamados: primer-del-apellido-alumno1, primer-del-apellido-alumno2,
primer-del-apellido-alumno3.

## 3.3 Instalar el servicio LTSP
Instalar servidor de clientes ligeros, según la documentación para el SO elegido. En el caso de Debian/Ubuntu puede ser

    apt-get install ltsp-server-standalone
    ltsp-build-client

Instalar el servidor SSH `apt-get install openssh-server`.

Revisar la configuración de la tarjeta de red interna del servidor. 
IP estática compatible con la configuración dhcp (/etc/ltsp/dhcpd.conf)

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
* Ir al servidor, ejecutar comando "arp" como superusuario y capturar la salida.
* Reiniciar la MV cliente y grabar vídeo mostrando el funcionamiento.

#5. ANEXOS
Los anexos siguientes son resúmenes de la documentación proporcionada por la página web de LTSP.

##5.1 Ubuntu - Installing on top of an already running desktop system

You need to set up one static network interface where you will attach the thin clients, 
install two packages and run one command.

Configure your spare interface for the thin clients to have the IP 192.168.0.1 (and make sure it is up and running).

    sudo apt-get install ltsp-server-standalone

Now create your Thin Client environment on the server with.

    sudo ltsp-build-client

[WARNING] If you are on a 64-bit system but your clients have another architecture use 
the --arch option eg. sudo ltsp-build-client --arch i386

After that, you will be able to boot your first thin client. If it doesn't boot try rebooting the server.

Note that if you want to use another IP than the above, you need to edit the /etc/ltsp/dhcpd.conf file to match the IP values and restart the dhcp server.

##5.2 Personalización de los clientes
En Debian/Ubuntu podemos personalizar la configuración de los clientes, 
modificando/añadiendo valores en /var/lib/tftpboot/ltsp/i386/ltsp.conf

Comandos:
* `ltsp-update-image`: Para volver a actualiza la imagen
* `ltsp-info`: Para consultar información

##5.3 For openSUSE 12.3

Via commandline(following commands to be run in terminal as root "su -"):

    zypper ar -r http://r.opensu.se/server:ltsp.repo
    zypper refresh
    zypper in kiwi-ltsp-prebuilt

It is expected that minimum GNOME environment is installed on the server.
Configuration

Check out Network configuration instructions before proceeding.

Launch Easy-LTSP GUI as root (open terminal, run "su -", provide root password, and then run "easy-ltsp" command). 
