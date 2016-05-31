
#Windows 7 desatendido

Vamos a crear instalación desatendida para Windows 7.

En el siguiente enlace tenemos la guía para la creación de una 
[instalación desatendida para Windows 7](http://informatica.iesvalledeljerteplasencia.es/wordpress/creacion-de-imagen-de-windows-7-con-instalacion-desatendida/).

Aclaración:
* En la sección "WindowsSetup" encontraremos los apartados para configurar los discos, particiones e ImageInstall.
* En la sección "ShellSetup" encontraremos los apartados para configurar OOBE, cuentas de usuario, y OEM Information.

Al terminar probamos la ISO en una máquina virtual. 
Si al iniciar la MV con la iso recién creada, aparece el error 225. 
Ver imagen:

[win-error-225](./files/win-error-225.jpg)

Una posible solución será activar APCI en la MV. Ver imagen:

[win-vbox-acpi](./files/win-vboc-acpi.png)

> NOTA:
>
> En siguiente enlace tenemos una guía para [crear disco de instalación Windows7 personalizado](http://computerhoy.com/paso-a-paso/software/crea-tu-propio-disco-instalacion-windows-7-desatendido-7294). 
>
> Esto no es lo mismo que una instalación desatendida. 
> En la instalación desatendida automatizamos las respuestas del proceso 
de instalación pero se hace una instalación estándar del sistema operativo. 
> En una instalación personalizada estaríamos modificando los ficheros/archivos/programas 
que se van a instalar, y por tanto no sería una instalación estandar del sistema operativo. 
>Hay que tener en cuenta si lo permite o no la licencia.

#Windows XP desatendido

> Esta parte NO hay que hacerla. Es meramente informativa.

* Enlaces de interés:[http://www.compuayuda.net/guia21-1.htm](http://www.compuayuda.net/guia21-1.htm)

NOTA:
* Si queremos evitar el usar disquetes podemos hacer esto otro.
* Al guardar los archivos txt que pone por defecto, sustituimos por el nombre de winnt.sif.
* Deberemos tener los archivos winnt.sif y winnt.bat. Estos dos archivos los meteremos en la iso, dentro de la carpeta i386.Nos ayudaremos de un programa especial para modificar las isos. Por ejemplo poweriso (Buscar en softonic)
* Con esto conseguiremos una iso de instalación desatendida personalizada.
