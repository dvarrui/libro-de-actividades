
```
Curso       : 202021, 201819, 201718
Area        : Sistemas operativos, seguridad
Requisitos  : SO Windows y SO GNU/Linux OpenSUSE
Descripción : Técnicas para mejorar la seguridad del SO
Tiempo      :
```

---

# Claves y ocultación

Ejemplo de rúbrica:

| Sección                   | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| ------------------------- | ----------- | ---------- | ---------------- |
| W Ocultar usuarios        | | | |
| W Inicio sesión seguro    | | | |
| W Claves seguras          | | | |
| G Ocultar usuarios        | | | |
| G Claves seguras          | | | |
| G Desactivar modo gráfico | | |.|


# 1. SO Windows

Vamos a usar una MV Windows 10.

> **PRECAUCIONES al tocar el registro de Windows**
>
> * Si se ponen todas las cuentas en el registry, Windows no podrá entrar y
tendrías que usar tu disco de instalación para modificar el registry (hay técnicas para cargar el registry desde un live cd, en google busca: load hive)
> * Si escondes las cuentas de administrador se le pone un candado muy fuerte porque las opciones de administrador no te permiten indicar el usuario y contraseña, para ello hay que usar un comando desde cmd: `runas /user:USUARIO_ADMIN “comando”``
> * `runas /user:admin "userpasswords2″` -> Este comando permite entrar a la edición de usuarios. Los permisos son con permisos del usuario “admin” si nuestro usuario se llama distinto, hay que sustituir la palabra admin por el usuario que tengamos registrado. Se recomienda usar admin, administrador o administrator para efecto de que no se nos olvide.
> * `runas /user:admin "registry"` -> Este comando permite entrar al registry y desbloquear alguna cuenta para poder ingresar a ella la siguiente vez que reiniciemos.
> * `runas /user:admin "cmd"` -> nos abre una ventana de línea de comandos con permisos del usuario “admin” y así ejecutar otros comandos con ese usuario.

## 1.1 Ocultación de usuarios

Vamos a modificar la configuración del sistema para que los usuarios `jedi1` y `jedi2`, NO aparezcan en la ventana de inicio del sistema.
* Iniciar sesión con una cuenta administrador.
* Ir a `Inicio -> Ejecutar`
* Entrar al registro del sistema (comando `regedit`)
* Hacer una copia de seguridad del registro (exportar) antes de hacer cualquier cambio.

> IMPORTANTE: Cuando tenemos que tocar el registro del sistema hay que ser muy precavidos. Un error puede hacer que el sistema completo deje de funcionar.
> * Ejecutar comando regedit para abrir el registro del sistema, y lo primero, usar la opción de export, para hacer una copia de seguridad del registro.
> * Leer bien cómo hacerlo, y hacerlo con mucha atención.
> * Consultar enlace [¿Cómo esconder una cuenta de la pantalla de bienvenida?](http://www.computerperformance.co.uk/windows7/windows7_registry_hide_users.htm#Scenarios_for_Hiding_User_Accounts_From_Welcome_Screen_).

* Ir a la llave: `HKLM\SOFTWARE\Microsoft\Windows NT\CurrentVersion\Winlogon\` (HKLM = H Key Local Machine).
* Crear una llave (carpeta) con el nombre: `SpecialAccounts`
* Dentro de `SpecialAccounts` crear otra llave (carpeta) llamada: `UserList`.
* Dentro de `UserList` crear un registro tipo `DWORD` con el nombre de la cuenta que se desea ocultar. Por ejemplo: `jedi1` con un valor `0`.
* Reiniciar y comprobar.

## 1.2 Modo de inicio de sesión seguro

Con este modo de inicio se ocultan los nombres de todos los usuarios.
* [Enlace de interés](http://www.zonasystem.com/2012/04/no-mostrar-nombres-de-usuarios-en-el.html)
* Inciar consola CMD con usuario administrador.
* Ejecutar el programa `secpol.msc`.
* `Directivas locales -> Seguridad -> Inicio de sesión interactivo: No mostrar último nombre... -> Habilitar`.
* En este modo de inicio de sesión, debemos escribir nombre usuario y clave para iniciar sesión. Comprobarlo.

## 1.3 Claves seguras

Para que una clave sea un poco segura debe tener:
* Longitud 14,
* Incluir minúsculas y mayúsculas,
* Incluir números y algún carácter especial.

Vamos a modificar las claves de los usuarios de la siguiente forma:
* sith1: 1234 (También se puede probar con un número de 4 dígitos)
* sith2: casa (También se puede probar con una palabra de 4 letras del diccionario)
* jedi1: frodoHOBBITbolson
* jedi2: gandalfELGRIS
* nombrealumno: clave a elegir por el alumno
* administrador: DNI del alumno

¿Podremos descubrir las claves?

* Descargar la iso OphCrack para Windows. Buscar primero en el servidor
del departamento antes de descargarlo de Internet. OphCrack es una distribución LIVE de GNU/Linux que ejecuta un programa para descubrir las claves al iniciarse.
* Iniciar la máquina Windows con la iso OphCrack.
* En el caso de que no se inicie la herramienta en entorno gráfico, hacer lo siguiente:
    * Pulsar F12
    * Elegir la opción de CDROM (c)
    * Elegir arranque manual

> Otra opción es usar **CDLive de Kali Linux**
> * Iniciar con CDLive de Kali Linux
> * Abrir terminal y  buscar partición del sistema Windows (`fdisk -l`)
> * Montar la partición de Windows en `/mnt` (`mount /dev/sdaX /mnt`)
> * Iniciar aplicación OphCrack
> * Botón `Load -> Encrypted SAM -> /mnt/Windows/System32/config`
> * Botón `Crack`

* Esperar y comprobar cómo se descifran las claves sencillas.
* Realizar captura de pantalla.

> **TABLAS DE DICCIONARIO**
>
> Si quisiéramos aumentar la velocidad del programa, deberíamos incluir *"tablas de diccionario"*. Las podemos descargar del servidor del departamento o la web.
> * El fichero zip lo descomprimimos dentro de la máquina con OhpCrack.
> * En el programa vamos a tablas -> Instalar. Buscamos la ruta donde hemos puesto los ficheros.
> * A continuación en el programa -> Crack y comienza a buscar las claves usando las "tablas de diccionario" escogidas.


# 2. SO GNU/Linux

Usaremos una MV GNU/Linux OpenSUSE.

> Enlaces de interés sobre  [Cuentas de usuario](https://es.opensuse.org/Cuentas_de_usuario)

## 2.1 Ocultar usuarios

Vamos a modificar el sistema para que los usuarios `jedi3` y `sith3`,
NO aparezcan en la ventana de inicio del sistema.

* Si nuestro entorno gráfico actual ya oculta los usuario, no hay que hacer nada. En caso contrario seguimos con este apartado.

| Escritorio    | XFCE | KDE |
| ------------- | ---- | --- |
| Fichero       | `/var/lib/AccountsService/users/USERNAME` | `/etc/sddm.conf`. Puede estar vacío. |
| Contenido     | Sección `[User]` añadir `SystemAccount=true` | En la seccion `[Users]` añadimos la siguiente línea `HideUsers=USERNAME1,USERNAME2` |
> Donde USERNAME es el nombre del usuario a ocultar.

## 2.2 Claves seguras

* Modificar las claves de los usuarios de la siguiente forma:
    * sith3: 1234
    * sith4: casa
    * jedi3: frodoHOBBITbolson
    * jedi4: gandalfELGRIS
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
* Crear usuario `prueba` en Knoppix con la clave `123456`.

> El fichero shadow tiene una fila por cada usuarios.
Dentro de cada fila los campos se separan por 2 puntos.
El campo nº 1 es el nombre del usuario, el campo nº 2 es la clave encriptada del usuario.
>
> Enlaces de interés
> * [¿Cómo cifra linux las contraseñas?](http://www.nexolinux.com/como-cifra-linux-las-contrasenas/)
> * [Descifrando password encriptadas con shadow (md5 + salt)](https://blog.zerial.org/seguridad/descifrando-password-encriptadas-con-shadow-md5-salt/)

* Copiar la clave del usuario `prueba` del fichero `/etc/shadow` a los usuarios
`jedi3` y `sith3` del fichero `/mnt/etc/shadow`. Podemos usar el editor `geany` que ya viene preinstalado en Knoppix.
* Capturar imagen de la clave del usuario prueba dentro del fichero /etc/shadow.
* Capturar imagen de las claves cambiadas de los usuarios jedi3 y sith3 en el fichero /mnt/etc/shadow.
* Grabamos el fichero. Con este cambio hemos dejamos los usuarios con una clave conocida por nosotros.
* Reiniciar la MV sin el CDLIVE de Knoppix.
* Ahora podremos iniciar sesión con los usuarios `jedi3` y `sith3`, usando la
clave 123456.

## 2.3 Desactivar el inicio gráfico

Vamos a desactivar el inicio gráfico al inicio.
* Ir a `Yast -> Administración de Servicios`
* Cambiar `Estado predeterminado` de `Graphical Interface` a `Multi-User System`.

> NOTA: Desactivar el inicio gráfico por comandos sería `systemctl set-default multi-user.target`.

* Reiniciar
* Entramos en el sistema sin entorno gráfico.

Ahora vamos a restaurar el inicio gráfico automático al inicio.
* Ejecutamos `yast`

> Usaremos:
> * la tecla tabulador para movernos por los campos, y
> * la tecla enter para entrar/aceptar opciones

* Vamos a `Sistema -> Administrador de Servicios`
* Cambiamos `Estado predeterminado` a `Graphical Interface`.
* Reiniciar la MV y comprobamos.

# ANEXO

## Modificar claves de Windows con el comando chntpw de GNU/Linux

* Enlace de interés: [Modifying Windows local accounts with Fedora and chntpw](https://fedoramagazine.org/modifying-windows-local-accounts-with-fedora-and-chntpw)

### XUbuntu 20

Realizar los siguientes pasos:
* Iniciar MV Windows con ISO de XUbuntu 20 (que funciona también como CDLIVE).
    * Elegir idioma español.
    * Probar el sistema lo inicia en modo LIVE.

Localizar la partición de Windows:
* Abrir un terminal como usuario root.
* `fdisk -l`, para ver los discos/particiones y localizar la partición de Windows.
* Montar el disco de Windows, `mount /dev/sda2 /mnt`.

Instalar la herramienta:
* `apt install chntpw`, instalar la utilidad "chntpwd".

Usar la herramienta:
* `cd /mnt/Windows/System32/config`, nos movemos a la ruta donde están los archivos SAM, que contienen las claves encriptadas de los usuarios de Windows.
* `chntpw -l SAM`, listar los usuarios que se han creado en el Windows.
* `chntpw -u NOMBRE-DE-USUARIO SAM` , para cambiar la contraseña del usuario USUARIO.
    * Pulsar `1` para poner dejar la contraseña vacía.
* Comprobamos `chntpw -l SAM`.

### OpenSUSE

Montar disco:
* Ir a VirtualBox -> Configuración de la MV OpenSUSE
* Añadir un nuevo disco y escoger la ruta el fichero VDI de la MV Windows.
* Iniciar MV OpenSUSE.

Instalar la herramienta:
* Abrir un terminal como usuario root.
* Instalar la herramienta "chntpw", `zypper install chntpw`.

Localizar la partición de Windows:
* `fdisk -l`, para ver los discos/particiones y localizar la partición de Windows.
* Montar el disco de Windows, `mount /dev/sdb2 /mnt`.

Usar la herramienta:
* `cd /mnt/Windows/System32/config`, nos movemos a la ruta donde están los archivos SAM, que contienen las claves encriptadas de los usuarios de Windows.
* `chntpw -l SAM`, listar los usuarios que se han creado en el Windows.
* `chntpw -u USUARIO SAM` , para cambiar la contraseña del usuario USUARIO.
    * Pulsar `1` para poner dejar la contraseña vacía.
* Comprobamos `chntpw -l SAM`.
