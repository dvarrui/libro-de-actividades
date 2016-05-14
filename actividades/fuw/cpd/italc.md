```
* Actividad creada en el 201213
* Utilizada en los cursos 201213, 201314, 201415, 201516
```

#1. Control remoto

* Trabajaremos de forma individual.

#2. Preparación

* Consultar documentación de iTalc.
* Vamos a necesitar 3 máquinas que pueden ser virtuales o reales:
    * MV1 italc Master (Debian)
    * MV2 italc Slave (Windows7)
    * MV3 italc Slave (Debian)
    
#3. iTalc Master (versión 1.x)

* Instalar iTalc Master (Maestro) en un equipo GNU/Linux o Windows.
* En GNU/Linux la instalación de italc-master crea los siguientes ficheros:
    * `/etc/italc/keys/private/teacher/key` (Este es el fichero de clave privada del master)
    * `/etc/italc/keys/public/teacher/key` (Este es el fichero de clave pública del master)
* Si las claves anteriores no existieran después de instalar el programa habrá que generarlas. 
    * Para crear las claves de iTalc ejecutamos el comando `ica -createkeypair`
    * Creamos el grupo `italc`, y añadimos nuestro usuario al grupo.
        * Pistas `addgroup...`, y `usermod ...`
        * Comprobar con `id nombre-usuario`

> Sólo los usuarios que añadamos al grupo italc serán los que podrán hacer uso de dicha aplicación.

* Damos permisos al grupo `italc`, para tener acceso a las claves de iTalc:
    * `cd /etc/italc; chgrp -R italc keys`
* Verificar que el servicio que require Italc está iniciado:
    * `ps -ef |grep ica`. 
* Si el programa "ica" no está iniciado, lo podemos iniciar manualmente. 
    * Para ello abrimos un terminal y escribimos: `/usr/bin/ica &`

> Si el programa "ica" no está en ejecución italc no va a funcionar.
> Para que el servicio ica se inicie automáticamente al iniciar sesión con el usuario "nombre-usuario":
> * Editar fichero /home/nombre-usuario/.profile
> * Añadir "/usr/bin/ica &" al final del fichero.
> Otra forma de crear el inicio automático usando el gestor gdm para ello (Consultar).

#4. Slaves(versión 1.X)

Vamos a instalar iTalc Slave (Esclavo), para monitorizar dos equipos distintos.

##4.1 Cliente Windows

* [Descargar e instalar iTalc en Windows](https://sourceforge.net/projects/italc/files/italc/)
* Asegurarse de que nuestro usuario de Windows tiene una clave NO vacía.
* Instalar una versión de Italc cliente "similar" a la del master.

> ¿Cómo podemos consultar la versión de italc en el Master? 
> Vamos al equipo master y hacemos:
> * Ir a synaptic y consultar la versión en el nombre del paquete.
> * Por comandos, ejecutando "dpkg -l italc*". Esto nos muestra los paquetes instalados y su versión cuando el nombre comienza por italc.
> * Por comandos, ejecutando "ica -v", nos muestra la versión del programa (ica es el programa que da el servicio de italc).

Instalación ITALC 1.0.13 en Windows

* Copiar el fichero de clave pública del maestro para poder importarlo en los clientes.
    * FORMA 1: Cambiar el nombre del fichero de clave pública del master, por el de "italc_dsa_key.pub". Este cambio es para facilitar la localización del fichero ya que la herramienta en windows busca un fichero con extensión ".pub".
    * FORMA 2: Crear las claves, buscar el fichero "key" dentro de C:\Archivos de programas\italc\key\public\teacher. Sustituirlo por el que nos da el master (El fichero de clave del master debe tener el nombre "key").
* Reiniciar el programa.

##4.2 Cliente GNU/Linux

* Instalar en GNU/Linux:
    * Pista: `apt-g.. i..... italc-client`
    * Comprobar: `dpkg -l italc-client`
* Copiar el fichero key del master directamente en /etc/italc/keys/public/teacher.
* Creamos el grupo italc, y añadimos nuestro usuario al grupo:
    * Pista `addgroup ...` y `usermod...`
    * Comprobar: `id nombre-usuario`
* Damos permisos al grupo italc, para tener acceso a las claves de italc:
    * `cd /etc/italc; chgrp -R italc keys`
* Verificar que el servicio ICA está iniciado.
          
#5. Comprobación desde el master

Ahora desde el Master debemos poder monitorizar/controlar los slaves.

    Iniciar el programa gráfico de control, para comprobar que está todo correctamente instalado y se pueden monitorizar remotamente a los "slaves" (Clientes, esclavos)):
        Primero buscar el icono en los menús del entorno gráfico.
        Si no aparece el icono en Debian, usar el comando italc
        Si no aparece el icono en Ubuntu, usar el comando italc-launcher

Si al monitorizar el equipo Windows vemos una pantalla en negro, probar lo siguiente:

    Ir al equipo Windows -> Administrar -> Servicios.
    Detener completamente el servicio iTalc.
    Buscar el programa ica en el equipo.
    Iniciar el en una consola el programa ica igual que lo hicimos en GNU/Linux.
    Volver a probar la monitorización remota.


ANEXO
Indicaciones para iTalc versión 2.X.
A1. iTalc Master (V 2.X)

    Consultar la información para Instalar ITalc 2 en Ubuntu 12.04

    Vamos a instalar iTalc 2 en GNU/Linux. Primero vamos a actualizar la lista de paquetes: sudo apt-get update
    Ahora instalar italc2 en el Master: sudo apt-get install -y italc-master

configuring-italc-client

    En este momento, podemos elegir que se generen las claves automáticamente o realizar este proceso manualmente. En nuestro caso elegimos generarlas manualmente. Para generar manualmente el par de claves para los roles "admin" y "teacher" hacemos:
        imc -role teacher -createkeypair
        imc -role admin -createkeypair
    Los pares de claves pública/privada se crearán dentro de las siguientes carpetas:
        /etc/italc/keys/public/teacher/
        /etc/italc/keys/private/teacher/
        /etc/italc/keys/public/admin/
        /etc/italc/keys/private/admin/
    Creamos el grupo italc. Añadimos dentro del grupo a los usuarios que vayan a hacer uso de iTalc:
        addgroup italc
        adduser [your user] italc
    Podremos los permisos apropiados en las claves. De este modo todos los miembros del grupo tendrán acceso de lectura a las claves:
        chmod -R 640 /etc/italc/keys
        chgrp -R italc /etc/italc/keys

A2. Inicio automático del servicio iTalc

Para que el servicio de iTalc se ejecute automáticamente al iniciar la máquina debemos hacer un par de cosas más.

    Creamos un script en "/usr/local/bin/run-ica.sh" con las órdenes para iniciar el programa "ica", el cual da el servicio de iTalc. Antes de iniciar el servicio "ica", "matamos" (finalizamos) todos los procesos "ica" que pudieran estar en ejecución.Le ponemos permisos de ejecución al script (Importante). Normalmente los scripts creados por nosotros se suelen guardar en la ruta "/usr/local/bin", como en este caso.
    El script contendrá las siguientes líneas:

#!/bin/bash
killall ica
/usr/bin/ica &

    Modificamos el entorno gráfico para que inicie el script anterior cuando se inicie la máquina. Para ello añadimos la siguiente línea al fichero de configuración /etc/lightdm/lightdm.conf: greeter-setup-script=/usr/local/bin/run-ica.sh
    Reiniciamos la máquina.
    Para comprobar que el proceso "ica" está en ejecución hacemos: "ps -ef | grep ica". Esto nos lista todos los procesos en ejecución que contengan las letras "ica".

A.3 iTalc Client (V2.x) en GNU/Linux

      

Instalación
Ahora vamos a instalar y configurar el client iTalc 2 en GNU/Linux.

    Lo primero es actualizar la lista de paquetes: sudo apt-get update
    Ahora instalar italc2 en el cliente: sudo apt-get install italc-client
    Elegimos generar las claves manualmente.

Configuración

    Tenemos que crear los directorios para guardar las claves públicas de iTalc:
        mkdir -p /etc/italc/keys/public/teacher/
        mkdir -p /etc/italc/keys/public/admin/

    Ahora copiamos la clave públic de "teacher" de la máquina master en la carpeta correspondiente del equipo cliente. Las claves pública/privada se utilizan para mantener una comunicación segura entre los clientes y el master.
    Modificamos el equipo cliente de modo que cuando se conecte el master no pida confirmación manual al usuario de la máquina. Para ello modificamos el fichero /etc/italc/italc.conf con: PermissionRequiredWithKeyAuthentication=0
    Otra forma de modificarlo sería a través de la consola de iTalc (escribir imc en la shell) y desmarcar la casilla "key file authentication" en la sección de autenticación.

imc

    Repetir el paso A2 (Inicio automático del servicio iTalc) en el equipo cliente GNU/Linux.


A4. Instalación ITALC 2.XX en Windows

    Una vez instalado el cliente italc, iniciamos "ITALC Management Console".
    Vamos a Authentication -> Access Key Management -> Launch Key file assistant.
        Import public key (En este paso importaremos la clave del master).
        Profesor
        Directories -> Buscamos nuestro archivo de clave.
    Vamos a Authentication -> Loggon Settings -> ManagePermissions. Y damos todos los permisos a nuestro usuario de Windows.
    Vamos a Authentication -> Loggon Settings -> Test. Debe dar correcto.
    Copiar el fichero de clave pública del maestro para poder importarlo en los clientes.
    Cambiar el nombre del fichero de clave pública del master, por el de "profesor.key.txt". Este cambio es para facilitar la localización del fichero ya que la herramienta en windows busca un fichero con extensión "key.txt".


A.5 Comprobación desde el master

Ahora desde el Master debemos poder monitorizar/controlar los slaves.

    Iniciar el programa gráfico de control, para comprobar que está todo correctamente instalado y se pueden monitorizar remotamente a los "slaves" (Clientes, esclavos)):
        Primero buscar el icono en los menús del entorno gráfico.
        Si no aparece el icono en Debian, usar el comando italc
        Si no aparece el icono en Ubuntu, usar el comando italc-launcher

Si al monitorizar el equipo Windows vemos una pantalla en negro, probar lo siguiente:

    Ir al equipo Windows -> Administrar -> Servicios.
    Detener completamente el servicio iTalc.
    Buscar el programa ica en el equipo.
    Iniciar el en una consola el programa ica igual que lo hicimos en GNU/Linux.
    Volver a probar la monitorización remota.
