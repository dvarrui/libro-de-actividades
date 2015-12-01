

#Claves e instalación desde la fuentes

#1. SO Windows
Usaremos una MV Windows 7.

##1.1 Ocultación de usuarios
Vamos a modificar la configuración del sistema para que los usuarios `jedi1` y `jedi2`, 
NO aparezcan en la ventana de inicio del sistema.

> **REGISTRO DE WINDOWS**
> * En WXP para hacer esto mismo había que modificar valores en el registro del sistema.
> * Cuando tenemos que tocar el registro del sistema mucho cuidad.
> * Ejecutar comando regedit para abrir el registro del sistema, y lo primero,
 usar la opción de export, para hacer una copia de seguridad del registro. 
Un error puede hacer que el sistema completo deje de funcionar.
> * Leer bien cómo hacerlo, y hacerlo con mucha atención.
> Consultar enlace [¿Cómo esconder una cuenta de la pantalla de bienvenida?](http://www.computerperformance.co.uk/windows7/windows7_registry_hide_users.htm#Scenarios_for_Hiding_User_Accounts_From_Welcome_Screen_).

Iniciar sesión con una cuenta administrador:
* Ir a `Inicio -> Ejecutar`
* Entrar al registry (comando `regedit`)
* Ir a la llave: `HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Winlogon\` (OJO: HKLM = H Key Local Machine) 
Crear una llave (carpeta del registry) con el nombre: `SpecialAccounts`
* Dentro de `SpecialAccounts` crear otra llave (carpeta) llamada: `UserList`.
* Dentro de `UserList` crear un registro tipo `DWORD` con el nombre de la cuenta que se desea ocultar, 
por ejemplo: `jedi1` con un valor `0`.
* Reiniciar y comprobar.

> **MODO DE INICIO DE SESIÓN SEGURA**
>
> Con el programa `netplwiz` podemos cambiar al modo de inicio de sesión seguro.
>
> En este modo debemos escribir nombre usuario y clave para iniciar sesión.

##1.2 Claves seguras
* Modificar las claves de los usuarios de la siguiente forma:
    * sith1: 1234
    * sith2: casa
    * jedi1: frodoHOBBITbolson
    * jedi2: gandalfELGRIS
    * nombrealumno: clave a elegir por el alumno
    * administrador: DNI del alumno

> **RECOMENDACIONES PARA LAS CLAVES** 
>
> Para que una clave sea un poco segura debe tener:
> * longitud 14, 
> * incluir minúsculas y mayúsculas,
> * incluir números y algún carácter especial.

* Descargar la iso OphCrack para Windows7. Buscar primero en el servidor 
del departamento antes de descargarlo de Internet.
* Iniciar la máquina W7 con la iso OphCrack.
* Esperar y comprobar cómo aparecen las claves.
* REALIZAR CAPTURA DE PANTALLA para enseñar al profesor.

> **TABLAS DE DICCIONARIO**
>
> Si quisiéramos aumentar las efectividad del programa debemos incluir *"tablas de diccionario"*. Las podemos descargar del servidor del departamento o la web.
>
> * El fichero zip lo descomprimimos dentro de la máquina con OhpCrack.
> * En el programa vamos a tablas -> Instalar. Buscamos la ruta donde hemos puesto los ficheros.
> * A continuación en el programa -> Crack y comienza a buscar las claves usando las "tablas de diccionario" escogidas.

#2. SO GNU/Linux

Usaremos una MV GNU/Linux OpenSUSE 13.2.

##2.1 Ocultar usuarios
Vamos a modificar el sistema para que los usuarios `jedi1` y `sith1`, NO aparezcan en la ventana de inicio del sistema.

* Cuando nuestro sistema usa AccountsService, para ocultar un usuario llamado XXX, 
crear el fichero `/var/lib/AccountsService/users/XXX` con el siguiente contenido:

```
[User]
SystemAccount=true
```
* Si lo anterior no funciona porque nuestro sistema no usa AccountService, entonces
debemos localizar cúal es el gestor de inicio gráfico que está usando nuestra distro.
Veamos un ejemplo para consultar los procesos del sistema que tienen en su nombre las letras *dm*.
En el ejemplo podemos ver que estamos usando el programa ligthdm, el cual es un *Desktop Manager* (dm).
```
    asir@pc08:~$ ps -ef|grep dm
    root 881 1 0 11:17 ? 00:00:00 rpc.idmapd
    root 1125 1 0 11:17 ? 00:00:00 lightdm
    root 1206 1125 1 11:17 tty7 00:00:12 /usr/bin/X :0 -auth /var/run/lightdm/root/:0 -nolisten tcp vt7 -novtswitch -background none
    root 2157 1125 0 11:19 ? 00:00:00 lightdm --session-child 12 21
    asir 2805 2497 0 11:36 pts/1 00:00:00 grep --color=auto dm
```
* Consultar la información de nuestro gestor de inicio gráfico para cambiar
 la configuración y ocultar los usuarios. Gestores gráficos hay muchos: lightdm, gdm,
 gdm3, kdm, xdm, etc.

> **NO HACER LO SIGUIENTE**
>
> El siguiente comando modifica el ID númerico del nombre de usuario que especifiquemos: 
`sudo usermod -u 999 nombre-de-usuario`. Con esto podemos conseguir un efecto de "ocultación" 
en la ventana de inicio del sistema, porque los usuarios con valor ID inferior a 1000, 
se consideran usuarios especiales del sistema. Por tanto, el sistema no los identifica 
como usuarios-humanos que van a usar la interfaz gráfica, y no los muestra en la ventana de login.
>
> Pero NO LO HAGAN SE ESTA FORMA.

##2.2 Claves seguras
* Añadir nuestro usuario y los usuarios `jedi1` y `jedi2` al grupo `sudo`, 
para que puedan obtener privilegios administrativos.
* Modificar las claves de los usuarios de la siguiente forma:
    * sith1: 1234
    * sith2: casa
    * jedi1: frodoHOBBITbolson
    * jedi2: gandalfELGRIS
* Iniciar la máquina con un CDLIVE (Knoppix).
* Abrir una consola y entrar como superusuario.
* Para ver las particiones disponibles `fdisk -l`.
* Montar la partición del disco duro que corresponda al SO GNU/Linux, y ver el contenido. Ver ejemplo:
```
(En lugar de X poner el número de la partición donde está instalado el SO.)
mount /dev/sdaX /mnt 
cd /mnt
pdw
ls
```    
* Copia de seguridad del fichero de claves: `cp /mnt/shadow /mnt/shadow.000`.

> El fichero shadow tiene una fila por cada usuarios. 
Dentro de cada fila los campos se separan por 2 punto. 
El campo 1 es el nombre del usuario, el campo 2 es la clave escriptada del usuario.

* Editamos el fichero de claves: `nano /mnt/shadow`
* Quitar la clave encriptada para los usuarios `jedi2` y `sith2`.
Dejar el campo 2 vacío (sin espacios) y grabar el fichero. Con esto los dejamos sin clave.
* Reiniciar la MV sin el CDLIVE de Knoppix.
* Ahora podremos iniciar sesión con los usuarios `jedi2` y `sith2` sin clave.

##2.3 Desactivar el inicio gráfico

[Ocultar usuarios en el inicio de sesión OpenSUSE con lightdm](http://askubuntu.com/questions/92349/how-do-i-hide-a-particular-user-from-the-login-screen)

Además vamos a desactivar el inicio gráfico. Veamos unos ejemplos:
* Desactivar inicio gráfico si tenemos Lightdm:
    * nano /etc/init/lightdm.conf
    * Poner almohadillas (#) en todas las líneas de la acción start...
    * Grabar fichero y reiniciar el equipo.
* Desactivar inicio gráfico en GNOME:
    * mkdir /etc/rc2.d/DISABLED
    * mv /etc/rc2.d/S19gdm3 /etc/rc2.d/DISABLED
    * reboot
* El sistema iniciará sin entorno gráfico. Una vez dentro para iniciar 
entorno gráfico manuamente usaremos el comando: `startx`
* Para dejarlo como al principio haremos: `mv /etc/rc2.d/DISABLED/S19gdm3 /etc/rc2.d/S19gdm3`

##2.4 Modificar la apariencia
* Entrar al sistema con el usuario `jedi1`.
> Existen scripts que modifican la apariencia del GNOME-Debian para convertirlo en WXP o Windows7 
según queramos. Elegir sólo UNA opción.
>
> Podemos encontrar algún script en recursos del servidor del departamento, o buscando por Internet.
* Descargar fichero, descomprimirlo, leer documentación, seguir los pasos indicados.
* Reiniciar el sistema y comprobar el resultado.

##2.5 Instalación desde las fuentes
Realizar las siguientes tareas:
* Elegir un programa/software/aplicación para instalar desde las fuentes. Ejemplos:
    * [Jhonny_Simulator sources](http://sourceforge.net/projects/johnnysimulator/files/?source=navbar)
    * http://goonies.jorito.net/ (SO recomendado Debian7)
    * http://www.gameover.es/juegos-gratis/
    * http://www.juegoslibres.net/linux/ghouls-and-ghost-version-libre.html
    * Si elijen otro programa deben consultarlo con el profesor.
    * Geany código fuente.
* Descargar el código fuente desde internet.
* Realizar la instalación según se indique en el documento README, INSTALL o SETUP.

##2.6 Emulación
Realizar las siguientes tareas
* Instalar el emulador de Windows (`wine`)
* Instalar un programa de Windows. Usar el instalador de Windows.

#ANEXOS
##A1: Activar entorno gráfico
Para volver a poner la activación del entorno gráfico automático al inicio hacemos:
```
cd /etc/rc2.d/DISABLED
mv S20gdm3 ..
```

##A2: Poner clave de otro usuario
Otra opción es poner la clave de otro usuario que conozcamos.

Por ejemplo consultar `/etc/shadow` de otra instalación, buscar una clave que conozcamos y 
copiar dicho valor en los usuarios root y profesor1 del fichero /mnt/disco/etc/shadow 
de la máquina donde queremos acceder.

##A3: Precauciones en el registro de Windows
* PRECAUCIONES: Ojo, que si se ponen todas las cuentas en el registry, Windows no podrá entrar y tendrías que usar tu disco de instalación para modificar el registry (hay técnicas para cargar el registry desde un live cd, en google busca: load hive)
* Si escondes las cuentas de administrador se le pone un candado muy muy fuerte porque las opciones de administrador no te permiten indicar el usuario y contraseña, para ello hay que usar un comando desde cmd: runas /user:USUARIO_ADMIN “comando”
* runas /user:admin “userpasswords2″ -> Este comando permite entrar a la edicion de usuarios. Los permisos son con permisos del usuario “admin” si nuestro usuario se llama distinto, hay que sustituir la palabra admin por el usuario que tengamos registrado. Se recomienda usar admin, administrador o administrator para efecto de que no se nos olvide.
* runas /user:admin “registry” -> Este comando permite entrar al registry y desbloquear alguna cuenta para poder ingresar a ella la siguiente vez que reiniciemos.
* runas /user:admin “cmd” -> nos abre una ventana de linea de comandos con permisos del usuario “admin” y asi ejecutar otros comandos con ese usuario.

##A4 Instalación de GitHub app
* [Instalar node.js en Ubuntu](http://lobotuerto.com/blog/2013/02/19/como-instalar-node-js-en-ubuntu/)
* [Instalar el editor Atom desde las fuentes alojadas en GitHub](https://github.com/atom/atom/blob/master/docs/build-instructions/linux.md)

##A5 Modificar apariencia Lubuntu a MAC
* Primero debemos descargar e instalar las apariencias de las ventanas 
y demás en el siguiente [enlace](http://sourceforge.net/projects/mac4lin/)
* Una vez instalado procederemos a activarlas manualmente en el asistente de 
personalizacion del sistema. (Preferencias->Personalizar apariencia y comportamiento) 
En las pestañas control y borde de ventana
* Luego ponemos estos comandos en consola como root para instalar un software 
de personalizacion de barra de tarear llamado Cairo Dock.

    sudo add-apt-repository ppa:cairo-dock-team/ppa
    sudo apt-get update
    sudo apt-get install cairo-dock cairo-dock-plug-ins

* Y por ultimo buscamos el Cairo Dock modo gráfico y lo personalizamos. 
* Para personalizar el Cairo-Dock y agregar los iconos de Mac--> (click derecho sobre la barra-->Cairo-Dock-->configurar-->temas (Importamos el tema macOSX)


