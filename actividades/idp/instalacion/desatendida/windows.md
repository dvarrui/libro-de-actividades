
# Instalación desatendida de Windows 7

Vamos a crear instalación desatendida para Windows 7.

Entregar:
* Informe de los pasos realizados.
* Fichero `autounattend.xml`.

---

# 1. Introducción

Enlaces de interés:
* [Instalación desatendida para Windows - IES Valle del Jerte 7](http://informatica.iesvalledeljerteplasencia.es/wordpress/creacion-de-imagen-de-windows-7-con-instalacion-desatendida/).
* [Instalación desatendida de Windows- David del Río Pascual](http://www.daviddelrio.es/instalacion-desatendida-de-windows/)

Vamos a crear una imagen ISO de windows 7 con instalación desatendida.
El sistema operativo se instalará en la máquina sin necesidad de que un usuario supervise la instalación ya que todos los parámetros configurables son configuradas anteriormente en un archivo que incluiremos en la ISO llamado `autounattend.xml`.

Requisitos:
* ISO de Windows 7.
* La herramienta WAIK "Kit de instalación Automatizada de Windows 7".

---

# 2. Instalar WAIK

## 2.1 Copiar ficheros

* Crear la carpeta `C:\W7`.
* Montar la ISO Windows 7 de 64 bits en la unidad CD de la MV
* Copiar el contenido de la unidad de CD a la carpeta `C:\W7`.

## 2.2 Desacargar e Instalar WAIK

* Descargar el [Kit de instalación automatizada de Windows (AIK) para Windows 7](https://www.microsoft.com/es-es/download/details.aspx?id=5753)
* Instalamos la herramienta WAIK.
* Ir `Inicio -> Todos los programas -> Microsoft Windows AIK -> Administrador de imágenes del sistema de Windows`. Ejecutar como administrador.

---

# 3. Crear fichero de respuestas

Ahora deberemos crear un catálogo que es el que nos dirá que tiene, que se puede y no se puede hacer dentro de la imagen seleccionada de Windows 7.

* Ir a `Archivo -> Seleccionar imagen de Windows` y buscamos el archivo siguiente (dependiendo de nuestra versión) y lo abrimos:
    * `C:\W7\Sources\install_Windows 7 PROFESSIONAL.clg.`
    * `C:\W7\Sources\install_Windows 7 ENTERPRISE.clg.`
    * Si falla la carga probar con `C:\W7\Sources\install.wim`
    * OJO: Elegir la versión de Windows 7 para la que queremos crear el archivo de autorespuesta. Debe corresponder con la versión de la ISO que usamos inicialemente (Apartado 2.1).
* Nos saldrá en la esquina inferior izquierda una lista que podemos desplegar con diferentes componentes y paquetes.
* Crear el archivo de autorespuesta que configuraremos posteriormente. Ir a `Archivo -> Nuevo archivo de respuesta`.

Ver ejemplo:

![w7-tabla-componentes.jpg](./files/w7-tabla-componentes.jpg)

> ZONA HORARIA: Para conocer nuestra zona horaria tan sólo tenemos que abrir una consola de comandos (`Inicio -> Ejecutar -> CMD`) y escribir el comando `tzutil /g`. El texto que se muestre lo escribiremos en el archivo de respuestas.

* El campo con la información `Serial del producto` lo vamos a dejar en blanco.
* Buscar los elementos/componentes en la parte izquierda. Los añadimos, y configuramos los parámetros según la tabla anterior.
    * En la sección `WindowsSetup` encontraremos los apartados para configurar los discos, particiones e ImageInstall.
    * En la sección `ShellSetup` encontraremos los apartados para configurar OOBE, cuentas de usuario, y OEM Information.
* Agregar componente `Microsoft International Core -> SetupUILanguage`
    * UILanguage: es-ES
    * WillShowUI. OnError
* Agregar componente `Windows Setup -> LocalAccount`
    * Name: nombre-del-alumno
* Validar el archivo de respuesta. Ir a `herramientas -> validar archivo de respuesta`.
* Guardar el archivo de respuesta en `Archivo -> Guardar archivo de respuesta como`. Elegir la ruta `C:\W7\autounattend.xml`.

---

# 4. Configurar aplicaciones

* Crear la carpeta `C:\W7\applications`. Dentro pondremos un programa de instalación MSI.
* Para que la instalación automática de las aplicaciones que queramos al iniciarse el sistema después de su instalación deberemos agregar el componente:
`Microsoft-Windows-Shell-Setup_neutral -> FirstLogonCommands -> Synchronous Command`
* Deberemos agregar el componentes tantas veces como aplicaciones queramos que se instalen al inicio y configurarlos de la siguiente manera:
    * CommandLine: Ubicación del ejecutable de la aplicación. Ejemplo: `C:\W7\applications\ejecutable.msi`.
    * Description: Una descripción del programa que se va a instalar.
    * Order: Orden en el que se instalará la aplicación.
    * RequiresUserInput: Si la aplicación necesita interacción del usuario.

---

# 5. Crear la ISO

Después de configurar esta última entrada en el archivo de respuesta, debemos compilar los archivos en una ISO para después grabarla en un DVD o memoria USB y poder usarla para su instalación en cualquier equipo.

* Crear la carpeta `C:\W7desatendido`.
* Para crear la ISO, `Abrimos desde  Inicio -> Todos los programas -> Microsoft Windows AIK -> Línea de comandos de las herramientas de implementación` y se nos abrirá una consola de comandos.
* Escribir `oscdimg –n –m –bC:\W7\boot\etfsboot.com  C:\W7 c:\W7desatendido\W7desatendido.iso`
* Si todo es correcto comenzará la creación de nuestra nueva ISO desatendida.

---

# 6. Comprobamos la ISO

* Al terminar probamos a instalar el SO con la nueva ISO en una máquina virtual.
* Si al iniciar la MV con la iso recién creada, aparece el error 225.

Ver imagen:

![win-error-225](./files/win-error-225.jpg)

* Una posible solución será activar APCI en la MV. Ver imagen:

![win-vbox-acpi](./files/win-vbox-acpi.png)

> NOTA: Instalación personalizada
>
> En siguiente enlace tenemos una guía para [crear disco de instalación Windows7 personalizado](http://computerhoy.com/paso-a-paso/software/crea-tu-propio-disco-instalacion-windows-7-desatendido-7294).
>
> Esto no es lo mismo que una instalación desatendida.
> En la instalación desatendida automatizamos las respuestas del proceso
de instalación pero se hace una instalación estándar del sistema operativo.
> En una instalación personalizada estaríamos modificando los ficheros/archivos/programas
que se van a instalar, y por tanto no sería una instalación estandar del sistema operativo.
>Hay que tener en cuenta si lo permite o no la licencia.

---

# ANEXO A

## A.1 /IMAGE/INDEX

Teniendo seleccionado el “Metadata” vamos a configurar los parámetros “Key” y “Value”. Para rellenar el parámetro “Key” debemos de usar la herramienta “Línea de comandos de implementacion“, incluida en la instalación de WAIK. Para localizar dicha herramienta, basta con buscarla en el buscador de Windows.

Una vez abierta la herramienta debemos introducir el comando `imagex /info C:\W7\sources\install.wim`.

Una vez ejecutado el comando, buscaremos los datos correspondientes a la instalación que deseamos realizar. En nuestro caso la de Windows 7 Professional

## A.2 Instalación desatendida de Windows XP

> Esta parte NO hay que hacerla. Es meramente informativa.

* Enlaces de interés:[http://www.compuayuda.net/guia21-1.htm](http://www.compuayuda.net/guia21-1.htm)

NOTA:
* Si queremos evitar el usar disquetes podemos hacer esto otro.
* Al guardar los archivos txt que pone por defecto, sustituimos por el nombre de winnt.sif.
* Deberemos tener los archivos winnt.sif y winnt.bat. Estos dos archivos los meteremos en la iso, dentro de la carpeta i386.Nos ayudaremos de un programa especial para modificar las isos. Por ejemplo poweriso (Buscar en softonic)
* Con esto conseguiremos una iso de instalación desatendida personalizada.
