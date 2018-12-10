
# Claves y ocultación

# 1. SO Windows

Vamos a usar una MV Windows 7.

> **PRECAUCIONES al tocar el registro de Windows**
>
> * Si se ponen todas las cuentas en el registry, Windows no podrá entrar y
tendrías que usar tu disco de instalación para modificar el registry (hay técnicas para cargar el registry desde un live cd, en google busca: load hive)
> * Si escondes las cuentas de administrador se le pone un candado muy fuerte porque las opciones de administrador no te permiten indicar el usuario y contraseña, para ello hay que usar un comando desde cmd: `runas /user:USUARIO_ADMIN “comando”``
> * `runas /user:admin "userpasswords2″` -> Este comando permite entrar a la edicion de usuarios. Los permisos son con permisos del usuario “admin” si nuestro usuario se llama distinto, hay que sustituir la palabra admin por el usuario que tengamos registrado. Se recomienda usar admin, administrador o administrator para efecto de que no se nos olvide.
> * `runas /user:admin "registry"` -> Este comando permite entrar al registry y desbloquear alguna cuenta para poder ingresar a ella la siguiente vez que reiniciemos.
> * `runas /user:admin "cmd"` -> nos abre una ventana de linea de comandos con permisos del usuario “admin” y asi ejecutar otros comandos con ese usuario.

## 1.1 Ocultación de usuarios

Vamos a modificar la configuración del sistema para que los usuarios `jedi1` y `jedi2`, NO aparezcan en la ventana de inicio del sistema.
* Iniciar sesión con una cuenta administrador.
* Ir a `Inicio -> Ejecutar`
* Entrar al registry (comando `regedit`)
* Hacer una copia de seguridad del registro (exportar) antes de hacer cualquier cambio.

> Cuando tenemos que tocar el registro del sistema hay que ser muy precavidos. Un error puede hacer que el sistema completo deje de funcionar.
> * Ejecutar comando regedit para abrir el registro del sistema, y lo primero,
 usar la opción de export, para hacer una copia de seguridad del registro.
> * Leer bien cómo hacerlo, y hacerlo con mucha atención.
> * Consultar enlace [¿Cómo esconder una cuenta de la pantalla de bienvenida?](http://www.computerperformance.co.uk/windows7/windows7_registry_hide_users.htm#Scenarios_for_Hiding_User_Accounts_From_Welcome_Screen_).

* Ir a la llave: `HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Winlogon\` (OJO: HKLM = H Key Local Machine)
* Crear una llave (carpeta del registry) con el nombre: `SpecialAccounts`
* Dentro de `SpecialAccounts` crear otra llave (carpeta) llamada: `UserList`.
* Dentro de `UserList` crear un registro tipo `DWORD` con el nombre de la cuenta que se desea ocultar. Por ejemplo: `jedi1` con un valor `0`.
* Reiniciar y comprobar.

## 1.2 Modo de inicio de sesión seguro

Con este modo de inicio se ocultan los nombres de todos los usuarios.
* [Enlace de interés](http://www.zonasystem.com/2012/04/no-mostrar-nombres-de-usuarios-en-el.html)
* Inciar consola CMD con usuario administrador.
* Ejecutar `secpol.msc`
* `Directivas locales -> Seguridad -> Inicio de sesión...: No mostrar último nombre... -> Habilitar`.
* En este modo de inicio de sesión, debemos escribir nombre usuario y clave para iniciar sesión. Comprobarlo.

## 1.3 Claves seguras

Para que una clave sea un poco segura debe tener:
* longitud 14,
* incluir minúsculas y mayúsculas,
* incluir números y algún carácter especial.

Vamos a modificar las claves de los usuarios de la siguiente forma:
* sith1: 1234 (También se puede probar con un número de 4 dígitos)
* sith2: casa (También se puede probar con una palabra de 4 letras del diccionario)
* jedi1: frodoHOBBITbolson
* jedi2: gandalfELGRIS
* nombrealumno: clave a elegir por el alumno
* administrador: DNI del alumno

¿Podemos descubrir las claves?
* Descargar la iso OphCrack para Windows7. Buscar primero en el servidor
del departamento antes de descargarlo de Internet.
* Iniciar la máquina W7 con la iso OphCrack.
* En el caso de que no se inicie la herramienta en entorno gráfico, hacer lo siguiente:
    * Pulsar F12
    * Elegir la opción de CDROM (c)
    * Elegir arranque manual

> Otra opción es usar CDLive de Kali Linux
>
> * Iniciar con CDLive de Kali Linux
> * Abrir terminal, buscar partición del sistema Windows (`fdisk -l`)
> * Montar la partición de Windows en `/mnt` (`mount /dev/sdaX /mnt`)
> * Iniciar aplicación OphCrack
> * Botón `Load -> Encrypted SAM -> /mnt/Windows/System32/config`
> * Botón `Crack`

* Esperar y comprobar cómo aparecen las claves.
* Realizar captura de pantalla.

> **TABLAS DE DICCIONARIO**
>
> Si quisiéramos aumentar la velocidad del programa, deberíamos incluir *"tablas de diccionario"*. Las podemos descargar del servidor del departamento o la web.
>
> * El fichero zip lo descomprimimos dentro de la máquina con OhpCrack.
> * En el programa vamos a tablas -> Instalar. Buscamos la ruta donde hemos puesto los ficheros.
> * A continuación en el programa -> Crack y comienza a buscar las claves usando las "tablas de diccionario" escogidas.

---

# 2. SO GNU/Linux

Usaremos una MV GNU/Linux OpenSUSE.

> Enlaces de interés sobre  [Cuentas de usuario](https://es.opensuse.org/Cuentas_de_usuario)

## 2.1 Ocultar usuarios

Vamos a modificar el sistema para que los usuarios `jedi1` y `sith1`,
NO aparezcan en la ventana de inicio del sistema.

* Si nuestro entorno gráfico actual ya oculta los usuario, no hay que hacer nada. En caso contrario seguimos con este apartado.

### Escritorio XFCE

Procedimiento de ocultación de usuarios para el escritorio XFCE.
* Cuando nuestro sistema usa AccountsService, para ocultar un usuario llamadopor ejemplo, USERNAME, hay que modificar el fichero `/var/lib/AccountsService/users/USERNAME`
con el siguiente contenido:

```
[User]
SystemAccount=true
```

### Escritorio KDE (Antonio y Giovanni)

Procedimiento de ocultación de usuarios para el escritorio XFCE.
* Editamos el archivo `/etc/sddm.conf`. Puede estar vacío.
* Añadimos las siguientes líneas:

```
[Users]

HideUsers=USERNAME1,USERNAME2....
```

> Donde USERNAME es el nombre del usuario a ocultar.

## 2.2 Claves seguras

* Configurar nuestro usuario y los usuarios `jedi1` y `jedi2` en el fichero `/etc/sudoers`,
para que puedan obtener privilegios administrativos.
* Modificar las claves de los usuarios de la siguiente forma:
    * sith1: 1234
    * sith2: casa
    * jedi1: frodoHOBBITbolson
    * jedi2: gandalfELGRIS
* Iniciar la máquina con un CDLIVE (Knoppix).
* Abrir una consola y entrar como superusuario.
* Usar el comando `fdisk -l` para ver las particiones MBR disponibles.

> En el caso de GPT debemos usar el comando `gdisk` en lugar de `fdisk`.

* Montar la partición del disco duro que corresponda al SO GNU/Linux, y ver el contenido. Ver ejemplo:
```
(En lugar de X poner el número de la partición donde está instalado el SO.)
mount /dev/sdaX /mnt
cd /mnt
pwd
ls
```    
* Copia de seguridad del fichero de claves: `cp /mnt/etc/shadow /mnt/etc/shadow.bak`.
* Crear usuario `c3po` en knoppix con la clave `123456`.

> El fichero shadow tiene una fila por cada usuarios.
Dentro de cada fila los campos se separan por 2 puntos.
El campo nº 1 es el nombre del usuario, el campo nº 2 es la clave escriptada del usuario.

* Copiar la clave del usuario `c3po` del fichero `/etc/shadow` a los usuarios
`jedi2` y `sith2` del fichero `/mnt/etc/shadow`.

> Podemos usar el editor `geany` que ya viene preinstalado en Knoppix.

* Grabamos el fichero.

> Con este cambio hemos dejamos los usuarios con una clave conocida por nosotros.

* Reiniciar la MV sin el CDLIVE de Knoppix.
* Ahora podremos iniciar sesión con los usuarios `jedi2` y `sith2`, usando la
clave 123456.

## 2.3 Desactivar el inicio gráfico

Vamos a desactivar el inicio gráfico al inicio.
* Ir a `Yast -> Administración de Servicios`
* Cambiar `Estado predeterminado` de `Graphical Interface` a `Multi-User System`
* Reiniciar
* Entramos en el sistema sin entorno gráfico.

Ahora vamos a restaurar el inicio gráfico automático al inicio.
* Ejecutamos `yast`

> Usaremos:
>
> * la tecla tabulador para movernos por los campos, y
> * la tecla enter para entrar/aceptar opciones

* Vamos a `Sistema -> Administrador de Servicios`
* Cambiamos `Estado predeterminado` a `Graphical Interface`.

---

# ANEXO


## A.2 OpenSUSE 13.2. Usuarios de tipo sistema

> Mostrar/Ocultar los usuarios en el menú contextual `ligthdm (XFCE y LXDE)`
>
> * `sudo gedit /etc/lightdm/lightdm.conf`, para editar la configuración del menú contextual.
> * `greeter-hide-users=false`, para ocultar los usuarios en el menú contextual.
> * `greeter-hide-users=true`, para mostrar los usuarios en el menú contextual.
> * Guardamos el fichero.
> * Al reiniciar veremos los cambios.

* Si nuestro sistema no usa AccountService, entonces
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
 gdm3, kdm, xdm, etc. (*Consultar ANEXO o buscar información en Internet*).
