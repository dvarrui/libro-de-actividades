```
* Práctica realizada en los cursos 201314, 201415
* Cliente de dominio GNU/Linux con PIBS/Likewise
```

#1. Clientes de Dominio

##1.1 Introducción

El OBJETIVO de esta práctica será el de configurar una MV GNU/Linux, 
para comportarse como clientes del dominio anterior. 
En este caso la unirá al PDC del Windows Server.

Vamos a aprovechar el PDC de la actividad anterior, para realizar esta práctica. 
Además usaremos la herramienta PBIS, que es un programa de entorno 
gráfico que nos ayudará a realizar la unión al dominio de forma sencilla.

##1.2 Lecturas

LECTURA: Para realizar esta tarea, consultar la documentación proporcionada por el profesor. 
En el documentos se muestran dos formas de hacer este paso:
* (a) Usando comandos (Páginas 1-4 del documento "dirac-01.pdf"),
* (b) Usando LikeWise (Páginas 4 y 5 del documento "dirac-01.pdf"). Éste es el modo recomendado.

> Si se usa la distribución Ubuntu como cliente para unirse al dominio de un Windows Server 
el proceso es más sencillo. 
> Otra distribución GNU/Linux podría funcionar, pero quizás la instalación del software 
LikeWise sería diferente o habría que usar los comandos.
> Cada versión de Ubuntu tiene un nombre diferente. Es importante tener esto en cuenta 
si modificamos los repositorios de instalación de paquetes. Veamos algunos ejemplos:
> * Versión 14.04, nombre Trusty Thar
> * Versión 13.10, nombre Saucy
> * Versión 13.04, nombre Raring Ringtail

##1.3 Preparar el cliente

Tener en cuenta los siguientes aspectos en la configuración del cliente Ubuntu.

* HORA: La fecha/hora del sistema debe sincronizarse con el PDC. 
Si hiciera falta cambiar la zona horaria podemos usar el comando `dpkg-reconfigure tzdata`.
* VIRTUALBOX: GNU/Linux y PDC, deben estar en la misma red, por lo que es aconsejable 
configurar la red de las máquinas virtuales en modo "puente" las dos 
(El modo "Red interna" también funcionará bien).
* Interfaz de RED: Recordar que las máquinas (Servidor y cliente) deben tener 
la configuración de red estática.
* Servidores DNS: Los clientes, para unirse al PDC, deben tener como DNS1=ip-del-pdc, y DNS2=8.8.4.4.

Veamos un ejemplo de configuración del interfaz de red Ubuntu (`/etc/network/interfaces`):
```
auto lo
iface lo inet loopback

auto ethY
iface ethY inet dhcp

auto ethX
iface ethX inet static
address 172.16.108.240
netmask 255.255.0.0
gateway 172.16.1.1

dns-nameservers ip-del-pdc 172.16.1.1
dns-search vargas1w.idp
dns-domain vargas1w.idp
```

* Después de modificar los ficheros de configuración de red, es necesario 
reiniciar el servicio de red para que se lean los cambios. Hay varias formas:
    * service networking restart
    * /etc/init.d/networking restart
    * o bien, reiniciar la máquina.
* Para configurar los servidores DNS en Debian, tenemos otra forma. 
Que es editar el archivo /etc/resolv.conf, añadiendo lo siguiente:
```
search vargas1w.idp
domain vargas1w.idp
nameserver ip-del-pdc
nameserver 172.16.1.1
```

* Comprobar configuración DNS: Para comprobar si la resolución de nombres está funcionando correctamente hacemos las siguientes pruebas:
```
$ nslookup www.iespuertodelacruz.es
Server: 172.16.1.1
Address: 172.16.1.1#53

Non-authoritative answer:
Name: www.iespuertodelacruz.es
Address: 88.198.18.148

$ nslookup vargas1w.idp
Server: 172.16.108.40
Address: 172.16.108.40#53

Non-authoritative answer:
Name: vargas1w.idp
Address: 172.16.108.40
```

* Configuración "manual" de la resolución de nombres: Si la resolución de nombres fallara, 
podemos para este caso, hacer una configuración de nombres "manual". 
ara ello editamos el archivo "/etc/hosts" y añadimos la línea siguiente:

```
...
172.16.108.40 vargas1.vargas1w.idp vargas1w.idp
```

* Volvemos a realizar la comprobación mediante la ejecución del comando nslookup.

##1.4 Unirse al dominio

* Vamos a unir el cliente Ubuntu al PDC Windows Server usando una aplicación de
 entorno gráfico, llamada Likewise.
* Enlaces de interés: LikewiseOpen Ubuntu Documentation, Download PowerBroker Identity Service Edition

Instalar Likewise:
Para instalar Likewise en Ubuntu hay varias formas:

    (A) Instalar la herramienta PBIS (PowerBroker Identity Services PBIS)
        Descargar el instalador de PowerBroker Identity Services, Open Edition y lo hacemos ejecutable (chmod a+x). Lo ejecutamos.
        Si tenemos el error "Unable to find ssh binary" podemos (a) instalar el paquete del servidor SSH: "apt-get install openssh-server", o (b) Desactivar el uso de ssh "domainjoin-cli join --disable ssh DOMAIN administrador"
    (B) Instalar los paquetes: likewise-open y likewise-open-gui.
    (C) Descargar el paquete/instalador desde la web de Likewise, para nuestra distro.
    (D) Descargar el código fuente, y realizar la instalación del programa (http://debian-base.blogspot.com.es/2014/01/installing-active-directory-client.html).

Ejecutar el programa:

    Una vez instalado el programa Likewise, puede ser que no tengamos creado un acceso directo en los menús del entorno gráfico. Si queremos saber dónde está el programa podemos ejecutando una búsqueda con "find / -name domainjoin-gui" o "whereis domainjoin-gui".
    Para ejecutar el programa en modo gráfico, abrimos un terminal y ponemos: domainjoin-gui
    Completamos la siguiente información de configuración:
        Computer name: nombre de nuestra máquina (Aparece por defecto)
        Domain: el dominio de nuestro PDC.
    Luego nos pedirá usuario y contraseña. Aquí ponemos el nombre de nuestro usuario administrador del PDC, y su contraseña. [OJO] Revisar bien al escribir el nombre de usuario. No es lo mismo "AdministraTor" que "AdministraDor". El administrador del domino en Windows suele llamarse "Administrador", y en GNU/Linux es "root"
    Luego saldrá una pantalla indicando si el proceso ha sido correcto, y se pide reiniciar.
    Para comprobar que nos hemos unido al dominio podemos ejecutar el siguiente comando de consulta: domainjoin-cli query. Además, en el PDC WindowServer debe parecer, dentro de la carpeta "Computers" del Directorio Activo", nuestra máquina GNU/Linux.

Otra forma de ejecutar el programa de unión al dominio:

    Otra forma de unirnos al dominio es ejecutando el comando: "domainjoin-cli join DOMINIO Administrador".
    [NOTA] Si tenemos un error DNS a la hora de encontrar el servidor de dominio desde la máquina GNU/Linux, podemos incorporar la IP y el NOMBRE-DEL-DOMINIO al fichero /etc/hosts de cada máquina cliente. Esto lo hacemos para facilitar la búsqueda de la resolución de nombre e IP.


1.5 Comprobación

    Desde el cliente, entramos al sistema con algún usuario del dominio (Ejemplo username, username@DOMAIN, DOMAIN\username).
    En SO Ubuntu veremos una imagen de ejemplo, con el dominio EZEQUIELW y el nombre de usuario ALU1. Si no conseguimos entrar a la primera, esperaremos 5 minutos y lo volvemos a intentar.
    Abrir consola y poner el comando: "cat /etc/passwd | grep $(whoami)". Este comando busca si nuestro usuario actual está configurado dentro del fichero de usuario locales del sistema.

dentro-dominio-win

    Si hemos conseguido unirnos al dominio pero tuviéramos problemas para entrar en la máquina GNU/Linux con los usuarios del dominio, debemos probar a quitar el cliente del dominio, volver a unirla al dominio e intentarlo de nuevo.


#ANEXO

##A1. Enlaces de Interés

* http://cerowarnings.blogspot.com.es/2011/11/how-to-linux-en-dominio-windows.html
* Una web que puede servir de ayuda para el ejercicio: http://alternativalibre.wordpress.com/2010/09/15/likewise-open-linux-y-active-directory/
* Para iniciar sesión en un likewise
* http://www.likewise.com/resources/documentation_library/manuals/lwe/likewise-enterprise-53-guide.html#AboutLoggingOn

##A2. PowerBroker Identity Services PBIS

Download the PowerBroker Identity Services, Open Edition installer for your type of computer and operating system from the list and then make it executable with chmod a+x. Or, right-click the installer, click Properties, click the Permissions tab, select Execute for Owner, and then click Close. Then double-click the installer to run it.

After installing PowerBroker Identity Services, Open Edition, run the following command as root. Your AD account must have privileges to join computers to the domain:
Linux: /opt/pbis/bin/domainjoin-cli join domainName ADjoinAccount

Log on with your AD credentials by using the form DOMAIN\username. On the command line, you must include an escape character: DOMAIN\username.

Note:If you are copying the installer file from your Windows system to your non-Windows target system using a utility such as WinSCP, please be sure to set the file type to binary instead of text or default in your copy options. Not doing so may result in a checksum error. 
