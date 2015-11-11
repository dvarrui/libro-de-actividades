(En construcción)


# Samba - Debian

* Vídeo [LPIC-2 202 Samba Server Configuration](http://www.youtube.com/embed/Gkhl0mHpm1E")

#1. SO GNU/Linux
Configurar el equipo con:
* SO Debian
* IP: 172.18.XX.2XX (Donde XX corresponde al nº de cada puesto).
* Máscara de red: 255.255.0.0
* Gateway: 172.16.1.1
* Servidor DNS: 172.16.1.1
* Nombre de equipo: primer-apellido-del-alumno3.
* Tarjeta de red VBox en modo puente.
* Instalar openssh-server.

##1.1 Preparativos Servidor Samba
Vamos a GNU/Linux, y creamos los siguientes grupos y usuarios:
* Grupo informaticos con info1, info2 y supersamba
* Grupo panaderos con pan1 y pan2 y supersamba
* Crear el usuario smbguest. Para asegurarnos que nadie puede usar smbguest para 
entrar en nuestra máquina mediante login, vamos a modificar en el fichero /etc/passwd de la 
siguiente manera: "smbguest:x:1001:1001:,,,:/home/smbguest:**/bin/false**".
* Crear el grupo usuariosamba, y dentro de este poner a todos los panaderos, informaticos, supersamba y a smbguest.

#1.2 Instalar y configurar Samba
* Instalar el servidor Samba en Linux: `apt-get install -y samba samba-common smbclient samba-doc cifs-utils`
* Configurar el servidor samba
    * Vamos a renombrar el fichero de configuración existente, y trabajaremos a partir de un fichero de configuración nuevo vacío: "mv /etc/samba/smb.conf /etc/samba/smb.conf.000".
    * Crea un fichero vacío /etc/samba/smb.conf, y rellénalo con el contenido siguiente:

```
[global]
netbios name = PRIMER-APELLIDO-ALUMNO
workgroup = AULA108
server string = Servidor Samba del PC XX
security = user
map to guest = bad user
guest account = smbguest

[cdrom]
path = /dev/cdrom
guest ok = yes
read only = yes

[public]
path = /var/samba/public
guest ok = yes
read only = yes

[panaderos]
path = /var/samba/panaderos
read only = no
valid users = @panaderos, @informaticos

[informaticos]
path = /var/samba/informaticos
read only = no
valid users = info1, info2
```

Crear las rutas a los recursos compartidos
* Vamos a crear las carpetas de los recursos compartidos con los permisos siguientes:
    * /var/samba/public: Usuario propietario "supersamba", grupo propietario "usuariosamba". Poner permisos 775.
    * /var/samba/panaderos: Usuario propietario "supersamba", grupo propietario "panaderos". Poner permisos 770.
    * /var/samba/informaticos: Usuario propietario "supersamba", grupo propietario "informaticos". Poner permisos 770.

> Información:
>
> * public, es un recurso compartido accesible para todos los usuarios en modo lectura.
> * cdrom, es el recurso dispositivo cdrom de la máquina donde está instalado el servidor samba.

* Añadir usuarios a Samba. Después de crear los usuarios en el sistema, hay que añadirlos a Samba.
    * Para eso hay que usar el comando siguiente para cada usuario de Samba: `smbpasswd -a nombreusuario`
    * Al terminar comprobamos nuestra lista de usuarios Samba con el comando: `pdbedit -L`

Ahora que hemos terminado con el servidor, hay que reiniciar el servicio para que se lean los cambios de configuración (Consultar los apuntes):

    En Debian7: "service samba stop", "service samba start",
    "service samba status"
    En OpenSuSe13: "service smb stop", "service smb start",
    "service samba status"
    En Debian6: "/etc/init.d/smbd restart", "/etc/init.d/nmbd restart".

Comprobar

    testparm: Verifica la sintaxis del fichero de configuración del servidor Samba.
    netstat -tap: Vemos que el servicio SMB/CIF está a la escucha.


1.3 Cliente Windows GUI

Desde un cliente Windows trataremos de acceder a los recursos compartidos del servidor Samba.

samba-win7-cliente-gui

    Comprobar los accesos de todas las formas posibles. Como si fuéramos un panadero, un informático y un invitado.
    [OJO] Después de cada conexión se quedan guardada la información en el cliente (Ver comando "net use"). Para cerrar las conexión SMB/CIFS que ha realizado el cliente al servidor, usamos el comando: "C:>net use * /d /y".
    Para comprobar resultados, desde el servidor Samba ejecutamos:
        smbstatus
        netstat -ntap


1.4 Cliente Windows comandos

    En el cliente Windows, para consultar todas las conexiones/recursos conectados hacemos "C:>net use". Si hubiera alguna conexión abierta, para cerrar las conexión SMB al servidor, podemos usar el siguiente comando "C:>net use * /d /y". Si ahora ejecutamos el comando "net use", debemos comprobar que NO hay conexiones establecidas.
    Abrir una shell de windows. Usar el comando "net use /?", para consultar la ayuda del comando.
    Con el comando "net view", vemos las máquinas (con recursos CIFS) accesibles por la red.
    Vamos a conectarnos desde la máquina Windows al servidor Samba usando los comandos net. Por ejemplo el comando "net use P: \\ip-servidor-samba\panaderos /USER:pan1" establece una conexión del rescurso panaderos en la unidad P. Ahora podemos entrar en la unidad P ("p:") y crear carpetas, etc.
    Para comprobar resultados, desde el servidor Samba ejecutamos:
        smbstatus
        netstat -ntap


##1.5 Cliente GNU/Linux GUI
Desde en entorno gráfico, podemos comprobar el acceso a recursos compartidos SMB/CIFS. 
Estas son algunas herramientas:
* Yast en OpenSUSE
* Nautilus en GNOME
* Konqueror en KDE
* En Ubuntu podemos ir a "Lugares -> Conectar con el servidor..."
* También podemos instalar "smb4k".
* existen otras para otros entornos gráficos. Busca en tu GNU/Linux la forma de acceder vía GUI.

La siguiente imagen es un ejemplo de cómo se vería en Konqueror. 
Pulsamos CTRL+L y escribimos `smb://ip-del-servidor-samba`:

Ejemplo accediendo al recurso prueba del servidor Samba:

![samba2](./images/samba2)

* Probar a crear carpetas/archivos en [corusant] y en  [tatooine].
* Comprobar que [public] es de sólo lectura.
* Para comprobar resultados, desde el servidor Samba ejecutamos: `smbstatus`, `netstat -ntap`

##1.6. Cliente GNU/Linux comandos
Existen comandos (`smbclient`, `mount` , `smbmount`, etc.) para ayudarnos 
a acceder vía comandos al servidor Samba desde el cliente. Puede ser que 
con las nuevas actualizaciones y cambios de las distribuciones alguno 
haya cambiado de nombre. ¡Ya lo veremos!

* Vamos a un equipo GNU/Linux que será nuestro cliente Samba. Desde este 
equipo usaremos comandos para acceder a la carpeta compartida.
* Primero comprobar el uso de las siguientes herramientas:
```
smbtree                              (Muestra todos los equipos/recursos de la red SMB/CIFS)
smbclient --list ip-servidor-samba   (Muestra los recursos SMB/CIFS de un equipo concreto)
```
* Ahora crearemos en local la carpeta `/mnt/samba-remoto/courusant`.
* MONTAJE: Con el usuario root, usamos el siguiente comando para montar un recurso 
compartido de Samba Server, como si fuera una carpeta más de nuestro sistema:
`mount -t cifs //172.18.XX.55/courusant /mnt/samba-remoto/courusant -o username=sith1`

> En versiones anteriores de GNU/Linux se usaba el comando 
`smbmount //172.16.108.XX/public /mnt/samba-remoto/public/ -o -username=smbguest`.

* COMPROBAR: Ejecutar el comando `df -hT`. Veremos que el recurso ha sido montado.

![samba-linux-mount-cifs](./images/samba-linux-mount-cifs.png)

> * Si montamos la carpeta de courusat, lo que escribamos en `/mnt/samba-remoto/courusant` 
debe aparecer en la máquina del servidor Samba. ¡Comprobarlo!
> * Para desmontar el recurso remoto usamos el comando `umount`.

* Para comprobar resultados, desde el servidor Samba ejecutamos:
```
smbstatus
netstat -ntap
```

##1.7. Montaje automático

Acabamos de acceder a los recursos remotos, realizando un montaje de forma manual (comandos mount/umount). 
Si reiniciamos el equipo cliente, podremos ver que los montajes realizados de forma manual ya no están (`df -hT`).
Si queremos volver a acceder a los recursos remotos debemos repetir el proceso de  montaje manual, 
a no ser que hagamos una configuración de  montaje permanente o automática.

* Para configurar acciones de montaje automáticos cada vez que se inicie el equipo, 
debemos configurar el fichero `/etc/fstab`. Veamos un ejemplo:

```
...
//ip-del-servidor-samba/public /mnt/samba-remoto/public cifs username=sith1,password=clave 0 0
```

* Reiniciar el equipo y comprobar que se realiza el montaje automático al inicio.
* Incluir contenido del fichero `/etc/fstab` en la entrega.

##1.8. Preguntas para resolver

* ¿Las claves de los usuarios en GNU/Linux deben ser las mismas que las que usa Samba?
* ¿Puedo definir un usuario en Samba llamado sith3, y que no exista como usuario del sistema?
* ¿Cómo podemos hacer que los usuarios sith1 y sith2 no puedan acceder al sistema pero sí al samba? 
(Consultar `/etc/passwd`)
* Añadir el recurso `[homes]` al fichero `smb.conf` según los apuntes. ¿Qué efecto tiene?
