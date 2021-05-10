
```
Curso       : 202021
Area        : Sistemas operativos, dominios
Descripción : Unir client GNU/Linux a un dominio Windows
Requisitos  : Windows 2016 Server, OpenSUSE
Tiempo      : 1 sesión
```

# 1. Introducción

El objetivo de esta práctica será el de configurar una MV GNU/Linux como cliente del dominio de la práctica anterior.
En este caso la unirá al PDC del Windows Server.

Vamos a aprovechar el PDC de la actividad anterior, para realizar esta práctica.

Además usaremos la herramienta YAST, un programa de entorno
gráfico que nos ayudará a realizar la unión al dominio de forma sencilla.

## 2. Preparar el cliente

Tener en cuenta los siguientes aspectos en la configuración del cliente.
* HORA: La fecha/hora y la zona horaria de la máquina debe ser igual que la del Windows Server (PDC). que tenemos en el PDC (Windows Server).
* NOBRE DEL EQUIPO: "1er-apellidoXXg".
* VIRTUALBOX: GNU/Linux y PDC, deben estar en la misma red, por lo que es aconsejable configurar la red de las máquinas virtuales en modo `puente` (El modo "Red interna" también funcionará bien).
* Interfaz de RED: Recordar que las máquinas (Servidor y cliente) deben tener la configuración de red estática. Configurar la red con IP estática.
* Servidores DNS: Configurar el cliente GNU/Linux para tener dos servidores DNS.
Esto es, `DNS1=ip-del-pdc`, y `DNS2=1.1.1.1`.
* Realizar la comprobación del DNS mediante la ejecución de
    * `host DOMINIO-DEL-PDC`, por ejemplo "host ruiz42dom.curso2021".
    * `host NOMBRE-EQUIPO-PDC.NOMBRE-DOMINIO`, por ejemplo "host vargas42s.ruiz42dom.curso2021"
    * `host www.nba.com`

## 3. Unirse al dominio

* Usar Yast para unir la MV al dominio del PDC.
    * `Yast -> Pertenencia a dominio de Windows` (Unirse a un dominio)
    * Poner el **nombre largo del dominio**. Por ejemplo "ruiz42dom.curso2021".
    * Activar la **Autenticación SMB**.
    * Activar **Crear home del usuario**.
* Pulsamos "Aceptar". Es posible que se instalen algunos paquetes que hacen falta para seguir.
* **OJO**: Hay que reescribir el nombre del usuario y poner `Administrador` (con d).
* Poner la clave del Administrador del dominio.
* Debe aparecer un mensaje de que se ha realizado la unión al dominio.
* Comprobar en el servidor PDC, consultando los equipos del dominio.

## 4. Abrir sesión en el cliente

> Asegurarse que los usuarios del dominio no tienen restricciones de inicio de sesión.

Iniciar sesión en el equipo GNU/Linux usando un usuario del dominio.
* Desde el cliente, entramos al sistema con algún usuario del dominio de la siguiente forma **nombre-de-dominio-corto\usuario-del-dominio**. Por ejemplo: ruiz42dom\obiwan. Se recomienda usar el entorno gráfico XFCE.

> Si tenemos problema para iniciar sesión desde el entorno gráfico, podemos iniciar
sesión por el terminal:
> * Abrir un terminal
> * `su -l USERNAME@DOMAIN`. por ejemplo `su -l yoda@ruiz42dom`.

Vemos una imagen de ejemplo, con el dominio "EZEQUIELW" y el nombre de usuario "ALU1".
Si no conseguimos entrar a la primera, esperaremos 5 minutos y lo volvemos a intentar.

![pdc-dentro-dominio-win.jpg](./files/pdc-dentro-dominio-win.jpg)

Una vez iniciada la sesión ejecutar los comandos de comprobación:
* `whoami`, esto debe devolver `DOMINIO\USERNAME` que ha iniciado sesión.
* `pwd`, para consultar el directorio actual.
* `cat /etc/passwd | grep USERNAME`, esto debe devolver vacío, indicando
que el usuario no está definido como usuario local, por tanto, debe ser
un usuario del dominio.

## NOTA

Podemos acceder al recurso compartido del Window Server (PDC) de la siguiente forma:
* Iniciar un explorador de archivos.
* Escribir lo siguiente en la barra de ruta que está en la parte superior, por ejemplo: `smb:\\ip-del-pdc\perfiles$\yoda.V5`.

---

# ANEXO

Enlaces de interés:
* [Descargar guía OpenSUSE 13.1 con DA](http://www.mediafire.com/download/513w206qbg014bv/openSUSE+13.1+con+Active+Directory+Gu%C3%ADa+Ilustrada.zip)
* [Integración OpenSUSE a Directorio Activo](https://es.opensuse.org/Integraci%C3%B3n_de_Directorio_Activo)

## Configuración "manual" de la resolución de nombres**

Si la resolución de nombres fallara, podemos en este caso, hacer
una configuración de nombres "manual".
Para ello editamos el archivo `/etc/hosts` y añadimos la línea siguiente:

`IP-DEL-PDC   vargas42dom.curso1516   vargas42s.vargas42dom.curso1516`

Si tenemos problemas con la resolución de nombres, revisar el contenido
del fichero `/etc/resolv.conf`.
