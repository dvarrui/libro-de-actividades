
#VirtualBox: Configuraciones

##1. Instalar las Guest Additions en Debian

1. Iniciamos la máquina virtual. Entramos con nuestro usuario.
1. Vamos a `VirtualBox -> Dispositivos -> Instalar GuestAdditions`.
1. Tenemos un error de ejecución por problemas de permisos.
1. Abrir un Terminal y nos ponemos como superusuario (Comando su).
1. Ejecutamos: `apt-get update`
1. Ejecutamos: `apt-get install -y gcc make linux-headers-$(uname -r)`
1. Ejecutamos: `cp -R /media/cdrom /mnt`.
1. Ejecutamos: `/mnt/cdrom/VBoxLinuxGuestAdditions.run`
1. Respondemos `yes`
1. Terminamos.

> Si hay problemas por falta de alguna herramienta de compilación, 
entonces consultar el fichero de log.

