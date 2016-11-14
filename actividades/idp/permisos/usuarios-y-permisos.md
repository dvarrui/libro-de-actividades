# Usuarios y permisos

* Entregar documento en formato ODT o PDF, con el informe de la actividad. Incluir las acciones realizadas y las capturas solicitadas.

# 1. SO Windows 7

* [Configurar la MV](../../global/configuracion/windows.md).
* Para la creación de usuarios podemos ir por `Panel de Control`, pero esa
herramienta está limitada. Nosotros vamos a ir a
`miPC -> Botón derecho administrar -> Gestión de usuarios`.
* ¿Cuántos usuarios hay en el sistema que no aparecen en la ventana de inicio? ¿Por qué?

## 1.1 Usando el GUI Windows

* Crear el grupo `jedis` y dentro los usuarios `jedi1` y `jedi2`.
* Los miembros del grupo `jedis` incluirlos además como miembreos del grupo
administradores, para que puedan actuar como superusuarios.
* Para comprobar que los usuarios y grupos se han creado correctamente vamos a
`Equipo -> Botón Derecho -> Administrar -> Usuarios y grupos`.
* Capturar imagen.
* Para cada miembro del grupo `jedis`:
    * Crear la carpeta `C:\Users\jedi1\private`.
    * Crear la carpeta `C:\Users\jedi1\group`.
    * Crear la carpeta `C:\Users\jedi1\public`.

> **INFORMACIÓN sobre Permisos NTFS**
>
> En Windows las carpetas HEREDAN los permisos de su carpeta padre. Para desactivar esta función
en una carpeta determinada, haremos lo siguiente:
>
> * Botón derecho sobre la carpeta -> propiedades -> seguridad.
> * Opciones avanzadas -> cambiar permisos.
> * Desactivar herencia `Incluir permisos heredables -> Quitar -> Aceptar`.
> * Aplicar y Aceptar.
>
> Para modificar los permisos de una carpeta vamos a `Botón derecho -> Propiedades -> Seguridad -> Editar`.

* Vamos a modificar los permisos de la siguiente forma:
    * `private`: El usuario propietario tendrá control total y nadie más tendrá permisos.
    * `group`: grupo `jedis` permisos de lectura, y el usuario propietario control total.
    * `public`: todos tienen permiso de lectura, y el usuario propietario control total.
* Capturar imagen del resultado de la asignación de permisos.

Veamos un ejemplo de permisos para la carpeta public:

![win-permisos-gui-public-dir](./images/win-permisos-gui-public-dir.png)

## 1.2 Ejemplo/Información sobre PowerShell

Vamos a pronar los comandos de PowerShell. Para ello buscamos en el
`menú -> PowerShell -> (botón derecho) -> Iniciar como Administrador`.

Si no lo hacemos como administrador, no tendremos los privilegios necesarios,
y no podremos crear los usuarios.

**EJEMPLO Shell CMD**

* `net user nombre-usuario clave-usuario /add`, crear usuario.
* `net localgroup nombre-grupo nombre-usuario /add` para añadir un usuario a un grupo

**EJEMPLO shell PowerShell**

Veamos un ejemplo para **crear grupo**:
* Cambiar nombre-pc por el nombre del PC de cada uno.
* Cambiar "alumnos" por el nombre del grupo que deseamos crear.

```
PS C:\> [ADSI]$equipo="WinNT://nombre-pc"
PS C:\> $grupo=$equipo.Create("Group","alumnos")
PS C:\> $grupo
PS C:\> $grupo.SetInfo()
```

Veamos un ejemplo de **creación de usuarios** en PowerShell:
* Cambiar nombre-pc por el nombre del PC de cada uno.
* Cambiar "alumno1" por el nombre del usuario que deseamos crear.
* Cambiar "123456" por el valor de password que querramos.

```
PS C:\> [ADSI]$equipo="WinNT://nombre-pc"
PS C:\> $usuario=$equipo.Create("User","alumno1")
PS C:\> $usuario
distinguishedName :
Path : WinNT://AULA109/nombre-pc/alumno1
PS C:\> $usuario.SetPassword("123456")
PS C:\> $usuario.SetInfo()
```

Veamos un ejemplo para **añadir usuario a un grupo ya existente**:
```
PS> $grupo = [adsi]"WinNT://nombre-pc/nombre-del-grupo,group"
PS> $grupo
PS> $usuario = [adsi]"WinNT://nombre-PC/nombre-del-usuario,user"
PS> $grupo.Add($usuario.path)
```

## 1.3 Usando los comandos Windows

* Capturar imagen de las acciones finales.
* Ahora vistos los ejemplos, vamos a crear el grupo `siths`.
* Pondremos a los usuarios `sith1` y `sith2`, dentro de los grupos `siths` y `usuarios`.

> Al incluir a un usuario como miembro del grupo Usuarios conseguimos que se muestre los
iconos de la ventana de inicio de sesión del sistema.
>
> [Más información sobre la creación de usuarios con PowerShell](https://www.petri.com/create-local-accounts-with-powershell)

* Para cada usuario del grupo `siths` hay que:
    * Crear la carpeta `C:\Users\sith\private`
    * Crear la carpeta `C:\Users\sith1\group`
    * Crear la carpeta `C:\Users\sith1\public`

> Veamos un ejemplo de permisos por comandos:
>
> ```
> (Quitar los permisos heredados a la carpeta public)
> PS C:\> icacls public /inheritance:r
>
> (Consultar los permisos actuales de public)
> PS C:\> icacls public
>
> (Dar permisos de lecturas al grupo todos)
> PS C:\> icacls public /grant todos:R
>
> (Dar permisos control total al usuario alumno1)
> PS C:\> icacls public /grant alumno1:F
>
> (Ver la ayuda del comando icacls)
> PS C:\> icacls /?
>
> (Para quitar el acceso al usuario USERNAME)
> PS C:\> icacls directorio /remove USERNAME
> ```

Modificar los permisos de la siguiente forma:
* `private`: Sólo el usuario propietario tendrá control total.
* `group`: grupo `siths` permisos de lectura, y usuario propietario permisos de control total.
* `public`: todos tienen permiso de lectura, y el usuario propietario tiene permisos de control total.

# 2. SO GNU/Linux OpenSUSE

## 2.1 Preparar la MV

* [Configurar la MV](../../global/configuracion/opensuse.md).
* Ir al gestor de usuarios de OpenSUSE: `Ir a Yast -> Gestión de Usuarios`.
* ¿Cuántos usuarios hay que no aparecen en la ventana de inicio al sistema? ¿Por qué?

> En Debian/Ubuntu iremos a
>
> * `Aplicaciones -> Herramientas -> Configuración del sistema -> Preferencias -> Cuentas de usuarios`,
> * `Sistemas -> Administración -> Usuarios y Grupos`.

## 2.2 Usando el GUI GNU/Linux

* Capturar imagen del resultado final.
* Crear el grupo `jedis` y dentro los usuarios `jedi1` y `jedi2`.
* Para cada usuario del grupo profesores:
    * Crear la carpeta `/home/jedi1/private`.
    * Crear la carpeta `/home/jedi1/group`.
    * Crear la carpeta `/home/jedi1/public`.

> Veamos un ejemplo de permisos por el entorno GUI, donde:
> * Permiso R = Ver contenido
> * Permiso W = Cambiar contenido
> * Permido X = Access content

![linux-permisos-gui](./images/linux-permisos-gui.png)

* Capturar imagen del resultado final.
* Modificar los permisos de las carpetas de la siguiente forma:
    * `private`: Sólo el usuario propietario tendrá todos los permisos.
    * `group`: grupo `jedis` permisos de lectura/ejecución, y usuario propietario todos los permisos.
    * `public`: todos tienen permiso de lectura/ejecución, y el usuario propietario tiene todos los permisos.

## 2.3 Sudoers (Grupo privilegiado)

> El comando `sudo` nos permite ejecutar comandos como si fuéramos el administrador del equipo.
Pero dicho comando sólo lo pueden ejecutar algunos elegidos.

Vamos a dar privilegios de superusuario a los miembros del grupo `jedis` usando
las configuraciones del comando sudo.

> Para permitir que los usuarios del grupo `jedis` puedan usar el comando sudo, añadimos la línea siguiente
`%jedis ALL = (root) NOPASSWD:ALL` al fichero de configuración de sudoers.
>
> Esto quiere decir:
> * `Usuarios -> %jedis`, a todos los usuarios jedis
> * `Host -> ALL`
> * `Ejecutar como -> (root)`, puede ejecutar como usuario root.
> * `Sin contraseña -> Si`
> * `Comandos -> ALL`, todos los comandos están permitidos.

![opensuse-sudoers](./images/opensuse-sudoers.png)

* Dos formas de hacerlo:
    1. **GUI**: Usar Yast en OpenSUSE (Ver ejemplo en la imagen anterior).
    2. **CLI**: Usar el comando `visudo`para editar el fichero de configuración `/etc/sudoers` (Se puede usar nano).
* Guardar y salir
* Ahora los usuarios del grupo profesores ya pueden ejecutar el comando sudo, para realizar todas las tareas administrativas (de superusuario). Comprobarlo. Por ejemplo: `fdisk -l`, `ifconfig`, etc.

> Veamos un ejemplo de un usuario sin privilegios que intenta usar el comando sudo:
>
> ![linux-sudo-error](./images/linux-sudo-error.png)

* Configurar al grupo `sith` en sudoers con
`%siths ALL = (root) NOPASSWD:/sbin/shutdown, /sbin/fdisk -l, /sbin/ifconfig`.
* Comprobar los nuevos permisos.

## 2.4 Usando los comandos

> Vídeo sobre [permisos en GNU/Linux](https://www.youtube.com/embed/Lq0UMXujGyc)

Capturar imagen del resultado final.

* Crear el grupo `siths`.
* Crear los usuarios `sith1` y `sith2` dentro del grupo anterior.
* Ejecutar el comando `cat /etc/passwd`. Así vemos todos los usuarios definidos el el sistema. Algunos son usados por personas físicas, y otros
son internos para uso de aplicaciones o del sistema operativo.
* Para cada usuario del grupo `siths` hacer:
    * Crear la carpeta `/home/sith1/private`.
    * Crear la carpeta `/home/sith1/group`.
    * Crear la carpeta `/home/sith1/public`.

```
* chown (Cambiar propietario)
* chgrp (Cambiar grupo propietario),
* chmod (Cambiar permisos de acceso).
```

Modificar los permisos de las carpetas:
* `private`: Sólo el usuario propietario tendrá todos los permisos.
* `group`: grupo `siths` permisos de lectura/ejecución, y usuario propietario todos los permisos.
* `public`: todos tienen permiso de lectura/ejecución, y el usuario propietario tiene todos los permisos.

# ANEXO

El anexo contiene información complementaria. No hay que hacerlo.

## A.1 Personalización de usuarios GNU/Linux

En OpenSUSE vemos que cuando queremos invocar el comando `ifconfig` con
un usuario normal debemos hacerlo con la ruta absoluta `/sbin/ifconfig`.

![opensuse-path-ifconfig](./images/opensuse-path-ifconfig.png)

Existe una variable llamada PATH, configurada para cada usuario de forma difierente.
Dicha variable de entorno contiene las rutas de los ejecutables/comandos.

No es necesario escribir la ruta completa para invocar a los comandos/programas
que estén en alguna de estas rutas.

Para cambiarlo añadimos las siguientes líneas al final del fichero `/home/nombre-de-usuario/.profile`:
```
PATH=$PATH:/sbin
export PATH
```

Para que los cambios tengan efecto debemos cerrar la sesión.

En otras distribuciones se usa el fichero de configuración `/home/nombre-de-usuario/.bashrc`.

## A.2 Emulador de consola portable para Windows

Cmder (http://bliker.github.io/cmder/) is a software package created out of pure
frustration over the absence of nice console emulators on Windows.

It is based on amazing software, and spiced up with the Monokai color scheme and
a custom prompt layout. Looking sexy from the start.
