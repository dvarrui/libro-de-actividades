
#1. Samba PDC

En esta práctica nuestro objetivo será usar una máquina Debian, para que funcione como PDC (Controlador de Dominio Principal).

> Realizaremos las prácticas en MV's que pueden estar o no en el mismo PC.

## 1.1 Entrega

* Entregar un informe con los pasos realizados.
* Incluir en el informe:
    * El fichero `/etc/samba/smb.conf`.
    * Comentar al final los problemas que han tenido en la actividad, y cómo los han resuelto.
    * Impresiones al respecto.
    * Incluir las respuestas a las cuestiones planteadas en el apartado 1.7.
* Incluir las siguientes capturas de pantalla:
    * `image01-union.png`: El proceso de unión de los clientes al dominio.
    * `image02-login.png`: Capturas del pantalla del login en los clientes.
    * `imagen03-profile.png`: Contenido del directorio `/var/samba/profiles/nombre-del-usuario`.
    Ahí deben aparecer los ficheros/carpetas del perfil móvil de dicho usuario.

## 1.2 Configuración de las máquinas

* MV Server PDC
    * SO Debian
    * [Configurar la MV](../../global/configuracion/debian.md)
    * DNS1 127.0.0.1
    * DNS2 8.8.4.4
    * Nombre host `pdc-serverXX`
    * Nombre NETBIOS `pdc-serverXX`

* MV cliente A
    * SO Windows 7
    * [Configurar la MV](../../global/configuracion/windows.md)
    * DNS1 <IPservidorPDC>
    * DNS2 8.8.4.4
    * Nombre NETBIOS `pdc-clientXXa`

* MV cliente B
    * SO Debian
    * [Configurar la MV](../../global/configuracion/debian.md)
    * DNS1 <IPservidorPDC>
    * DNS2 8.8.4.4
    * Nombre de host `pdc-clientXXb`
    * Nombre NETBIOS `pdc-clientXXb`

> Comprobar la conectividad de red entre las máquinas, acceso a Internet y la resolución de nombres.

---

# 2. Instalar Samba PDC

* Consultar/leer URL Controlador de dominio con Samba.
* Instalar SAMBA.

## 2.1 Configuración del PDC

* Hacer copia de seguridad del fichero de configuración original `/etc/samba/smb.conf`.
* Crear el fichero `smb.conf` vacío y rellenarlo desde cero según se indica en la documentación.

Pondremos en la configuración del PDC
* Nombre de dominio = `1er-apellido-del-alumnoXX.dom`. Este nombre de dominio no debe ser muy largo (10 letras + .dom)
* Personalizar la descripción/comentarios del servidor Samba. Parámetro "server string".

> Asegurarnos de que existen todos los directorios nombrados en los recursos compartidos. Esto es, las variables path, de la configuración Samba. Tales como:
>
> * /var/sambaXX/netlogon
> * /var/sambaXX/profiles
> * /usr/local/sambaXX/var

## 2.2 Cuenta UNIX de máquina

* Crear el grupo `machines` en el servidor.
* Ahora vamos a crear las cuentas de máquina `pdc-clientXXa` y `pdc-clientXXb`.

> OJO: El dolar debe estar al final del nombre de máquina.

```
useradd -g machines -d /dev/null -c "Cliente XXa" -s /bin/false pdc-clientXXa$
useradd -g machines -d /dev/null -c "Cliente XX" -s /bin/false pdc-clientXXb$
```

* Bloqueamos las cuenta Unix de las máquinas para evitar el acceso vía shell.
```
passwd -l pdc-clientXXa$
passwd -l pdc-clientXXb$
```

## 2.3 Cuentas de Samba

* En el servidor, vamos a crear las cuentas  Samba de las máquinas.
> La opcion –m indica que se trata de una cuenta de tipo máquina.

```
smbpasswd -a -m pdc-clientXXa
smbpasswd -a -m pdc-clientXXb
```

* Crear el grupo `piratas`, con los usuarios `barba-negra`, `barba-roja` y `barba-azul`.
* Crear el grupo `soldados` con los usuarios `soldado1`, `soldado2`, y `soldado3`.

> Veamos ejemplo:
> ```
> useradd username
> passwd -l username
> ```

* Crear las cuentas Samba, para todos los usuarios anteriores, y para el usuario root.
    * Veamos un ejemplo: `smbpasswd -a username`
    * `pdbedit -L` para comprobar que lo tenemos hecho.
* Crear la carpeta del perfil de cada usuario y poner los permisos correspondientes. Veamos ejemplo:
```
mkdir /var/lib/sambaXX/profiles/pirata1
chown pirata1 /var/lib/sambaXX/profiles/pitata1
```

---

# 3. Unir equipos al dominio (PDC)

## 3.1 Unir cliente Windows al dominio (`pdc-clientXXa`)

Vamos a usar cliente Windows para unirse al nuevo PDC.

* El equipo cliente debe tener la misma hora y zona horaria del servidor.
* UNIR AL DOMINIO: Vamos a unir el cliente al dominio, para ello introduciremos el nombre del dominio, nos pedirá una contraseña con derechos para agregar máquinas al dominio, esta será la de root del PDC.

> Se supone que previamente habremos creado su cuenta samba con el comando smbpasswd -a root.

* PROBAR LOGIN: Probar la autenticación de dominio desde el cliente Windows hacia el servidor de dominio Samba.

## 3.2 Si hay problemas en la unión al dominio

Leer este apartado sólo si hay problemas en el apartado anterior. En caso contrario saltar al siguiente punto.

Si leemos esto es poruqe hay problemas. A continuación algunas sugerencias para solucionarlo:

* Revisar que hemos hecho correctamente todo lo anterior.
* Esperar 5 minutos y volver a intentarlo. Las redes SMB/CIFS suelen tardar
es tiempo en refrescar la información por toda la red. El tiempo depende del número de equipos en la red.
* En el servidor Samba ejecutar `tail -n 20 /var/log/syslog` para consultar el fichero de log. Esto nos puede dar pistas para solucionar el problema.
* Cambiar el nombre del dominio del PDC, reiniciar Samba, reintentar unir el cliente al nuevo dominio.
* Usar una red interna de VBox para las nuetras máquinas.
* Si al unir una máquina con Windows 7 a un dominio PDC Samba, tenemos un error del tipo
*"El dominio especificado no existe o no puede ser contactado"*, esto se debe a que el Windows 7 realiza algunos controles adicionales que provocan este error.
La solución consiste en

```
Modificar el registro de cada host con Windows 7 para agregar las siguientes claves, ubicadas en

"HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\services\LanmanWorkstation\Parameters":
"DomainCompatibilityMode"=dword:00000001, "DNSNameResolutionRequired"=dword:00000000

Y alterar las que se muestran debajo, ubicadas en

"HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\services\Netlogon\Parameters":
"RequireSignOnSeal"=dword:00000000, "RequireStrongKey"=dword:00000000
```

---

# 4. Perfiles Móviles

Vamos a trabajar con perfiles móviles y obligatorios en el nuevo PDC.
* El grupo `piratas` tendrá perfiles móviles.
* El grupo `soldados` tendrá el mismo perfil de tipo obligatorio.

> Si no recuerdas cómo se hace esto... consulta apuntes de perfiles para Windows Server. Es el mismo proceso.

* Los perfiles se guardarán en el servidor en:
```
/var/samba/profiles/pirata1
/var/samba/profiles/soldado1
```

# 5. Políticas de acceso

Buscar respuestas/ideas sobre:
* ¿Cómo podríamos establecer politícas de acceso en el nuevo PDC?
De modo que podamos definir el rango de horas a las que cada usuario del dominio
tiene permitido establecer conexión al servidor. ¿Existe alguna herramienta al respecto?
* Si un usuario usu1 tiene permitido el acceso al dominio en el rango 8-14,
y el usuario se conecta a las 13:55 y está trabajando... cuándo sean las 14 horas ¿qué pasará?

---

# ANEXO I: FAQ

Tutorial Samba en Dominio Perfiles Moviles y Obligatorios:
* Consultar [vídeo online]( http://www.youtube.com/watch?v=JHHmAuJY0nc)
* Descargar el [vídeo](http://www.mediafire.com/?ljmwoawpiniaxvq) (Se necesita K-lite, CCCP o reproductor VLC)

Otras dudas:
* El parámetro para el fichero de claves es: smb passwd file = /etc/samba/smbpasswd.
* Existen una herramienta llamada Zentyal (Autenticación centralizada con Zentyal),
que puede ser de ayuda para gestionar todo este proceso desde un entorno gráfico.
