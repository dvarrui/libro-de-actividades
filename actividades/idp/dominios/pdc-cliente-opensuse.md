```
* Práctica creada para el curso 201516
* Inspirada el la práctica de cliente GNU/Linux Ubuntu/Debian con PIBS/Likewise
* Cliente de dominio GNU/Linux con OpenSUSE/Yast
```

#1. Introducción

El objetivo de esta práctica será el de configurar una MV GNU/Linux, 
para comportarse como cliente del dominio de la práctica anterior. 
En este caso la unirá al PDC del Windows Server.

Vamos a aprovechar el PDC de la actividad anterior, para realizar esta práctica. 
Además usaremos la herramienta YAST, un programa de entorno 
gráfico que nos ayudará a realizar la unión al dominio de forma sencilla.

##2. Preparar el cliente

Tener en cuenta los siguientes aspectos en la configuración del cliente Ubuntu.

* HORA: La fecha/hora del sistema debe sincronizarse con el PDC. 
* VIRTUALBOX: GNU/Linux y PDC, deben estar en la misma red, por lo que es aconsejable 
configurar la red de las máquinas virtuales en modo `puente` las dos 
(El modo "Red interna" también funcionará bien).
* Interfaz de RED: Recordar que las máquinas (Servidor y cliente) deben tener 
la configuración de red estática.
* Servidores DNS: Los clientes, para unirse al PDC, deben tener como `DNS1=ip-del-pdc`, 
y `DNS2=8.8.4.4`.


> **Configuración "manual" de la resolución de nombres**
>
> Si la resolución de nombres fallara,  podemos para este caso, hacer 
> una configuración de nombres "manual". 
> Para ello editamos el archivo "/etc/hosts" y añadimos la línea siguiente:
>
> ```
> ...
> IP_DEL_PDC vargas1.vargas1w.idp vargas1w.idp
> ```

* Realizar la comprobación mediante la ejecución del comando `host www.google.es`.

##2. Unirse al dominio

* Usar Yast para unir la MV al dominio del PDC.
* Comprobar entrando con un usuario del dominio en el cliente:
    * `whoami`
    * `id USUARIO`
    * `cat /etc/passwd |grep USUARIO`
    * `cat /etc/passwd | grep $(whoami)`
