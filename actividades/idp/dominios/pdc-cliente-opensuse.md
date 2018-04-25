
# 1. Introducción

El objetivo de esta práctica será el de configurar una MV GNU/Linux como cliente del dominio de la práctica anterior.
En este caso la unirá al PDC del Windows Server.

Vamos a aprovechar el PDC de la actividad anterior, para realizar esta práctica.

Además usaremos la herramienta YAST, un programa de entorno
gráfico que nos ayudará a realizar la unión al dominio de forma sencilla.

---

## 2. Preparar el cliente

Tener en cuenta los siguientes aspectos en la configuración del cliente.

* HORA: La fecha/hora del sistema debe sincronizarse con el PDC.
* VIRTUALBOX: GNU/Linux y PDC, deben estar en la misma red, por lo que es aconsejable configurar la red de las máquinas virtuales en modo `puente` (El modo "Red interna" también funcionará bien).
* Interfaz de RED: Recordar que las máquinas (Servidor y cliente) deben tener la configuración de red estática.
* Servidores DNS: Los clientes, para unirse al PDC, deben tener como `DNS1=ip-del-pdc`,
y `DNS2=8.8.4.4`.

* Realizar la comprobación del DNS mediante la ejecución de
    * `host www.google.es`
    * `host DOMINIO-DEL-PDC`
    * `host NOMBRE-EQUIPO-PDC`

> **Configuración "manual" de la resolución de nombres**
>
> Si la resolución de nombres fallara,  podemos en este caso, hacer
una configuración de nombres "manual".
> Para ello editamos el archivo `/etc/hosts` y añadimos la línea siguiente:
>
> `IP_DEL_PDC   vargas42dom.curso1516   vargas42s.vargas42dom.curso1516`
>
> Si tenemos problemas con la resolución de nombres, revisar el contenido
del fichero `/etc/resolv.conf`.

---

## 3. Unirse al dominio

* Usar Yast para unir la MV al dominio del PDC.
    * `Yast -> Pertenencia a dominio de Windows` (Unirse a un dominio)
    * Activar
        * Autenticación SMB
        * Crear home del usuario

> OJO: Hay que escribir usuario `Administrador` con la clave del dominio.

* Actualizar paquetes OpenSuse (`zypper update`)
* Comprobar en el servidor PDC, consultando los equipos del dominio.

---

## 4. Abrir sesión en el cliente

* Comprobar entrando con un usuario del dominio en el cliente:
    *  Desde el cliente, entramos al sistema con algún usuario del dominio (Ejemplos: `DOMAIN\username`, DOMAIN/username o username@DOMAIN).

> Vemos una imagen de ejemplo, con el dominio EZEQUIELW y el nombre de usuario ALU1.
> Si no conseguimos entrar a la primera, esperaremos 5 minutos y lo volvemos a intentar.
>
> ![pdc-dentro-dominio-win.jpg](./files/pdc-dentro-dominio-win.jpg)

* Una vez iniciada la sesión ejecutar los comandos de comprobación:
    * `whoami`, esto debe devolver `DOMINIO\USER` que ha iniciado sesión
    * `cat /etc/passwd | grep $(whoami)`, esto debe devolver vacío, indicando
    que el usuario no está definido como usuario local, por tanto, debe ser
    un usuario del dominio.

> **Otros comandos de comprobación**
>
> * `id USUARIO`, esto debería devolver que no existe el usuario en el sistema local.
> * `cat /etc/passwd |grep 'DOMINIO\USUARIO'`, esto es lo mismo que `cat /etc/passwd | grep $(whoami)`.

---

# ANEXO

Enlaces de interés:
* [Descagar guía OpenSUSE 13.1 con DA](http://www.mediafire.com/download/513w206qbg014bv/openSUSE+13.1+con+Active+Directory+Gu%C3%ADa+Ilustrada.zip)
* [Integración OpenSUSE a Directorio Activo](https://es.opensuse.org/Integraci%C3%B3n_de_Directorio_Activo)
