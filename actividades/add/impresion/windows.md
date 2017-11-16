
# Servidor de Impresión en Windows

Necesitaremos 2 MV:
* 1 Windows Server
* 1 Windows cliente

---

# 1. Impresora compartida

## 1.1 Rol impresión

* Vamos al servidor
* Instalar rol/función de servidor de impresión. Incluir impresión por Internet.
> DUDA: Instalar rol/función de cliente de impresión por Internet.

## 1.2 Instalar impresora

Vamos a conectar e instalar localmente una impresora al servidor Windows Server,
de modo que estén disponibles para ser accedidas por los clientes del dominio.

En nuestro caso, dado que es posible de que no tengan una impresora física en casa
y no es de mucho interés forzar la instalación de una impresora que no se tiene,
vamos a instalar un programa que simule una impresora de PDF.

> [Enlaces de interés:](http://www3.gobiernodecanarias.org/medusa/eforma/campus/mod/page/view.php?id=748283)
>
> * [Impresoras virtuales PDF](http://www.genbeta.com/herramientas/impresoras-virtuales-pdf-tres-alternativas-gratuitas-en-espanol)
> * PDF Creator: http://pdfcreator.es/. Para activar el modo AUTOSAVE vamos a Ajustes -> Autosave. Ahí configuramos carpeta destino.
> * doPDF: www.dopdf.com/es/
> * BullZIP PDF Printer: http://www.bullzip.com/products/pdf/info.php#Introduction

* Vamos a instalar PDFCreator.
* En PDFCreator, activar el modo AUTOSAVE vamos a Ajustes -> Autosave. Ahí configuramos carpeta destino.

PDFCreator es una utilidad completamente gratuita con la que podrás crear archivos
PDF desde cualquier aplicación, desde el Bloc de notas hasta Word, Excel, etc.
Este programa funciona simulando ser una impresora, de esta forma, instalando
PDFCreator todas tus aplicaciones con opción para imprimir te permitirán crear
archivos PDF en cuestión de segundos.

[impresora]

La instalación de este programa
no tiene dificultad simplemente elegir la opción "Instalación estándar".

> NOTA: PDFCreator puede requerir NET FrameWork v4.

## 1.3 Probar la impresora en local

Para crear un archivo PDF no hará falta que cambies la aplicación que estés usando,
simplemente ve a la opción de "imprimir" y selecciona "PDFCreator", en segundos
tendrás creado tu archivo PDF.

Puedes probar la nueva impresora abriendo el Bloc de notas y creando un fichero luego selecciona imprimir y como impresora predeterminada el PDFCreator. Cuando finalice el proceso
se abrirá un fichero PDF con el resultado de la impresión.

Captura de pantalla 1
pdf

## 1.4 Compartir por red

Vamos al servidor.
* `Botón derecho -> Propiedades -> Compartir`
* Como nombre del recurso compartido utilizar `PDFnombrealumnoXX`.

La siguiente imagen muestra los recursos compartidos
en el servidor incluido la impresora:

Captura de pantalla 2
imprec

Vamos al cliente:
* Buscar recursos de red del servidor. Si tarda en aparecer ponemos `\\ip-del-servidor`
en la barra de navegación.
* Seleccionar impresora -> botón derecho -> conectar.
* Probar la impresora remota.

---

# 2. Acceso Web

Realizaremos una configuración para habilitar el acceso web a las impresoras del dominio.

## 2.1 Instalar característica impresión WEB

* Vamos al servidor.
* Nos aseguramos de tener instalado el servicio "Impresión de Internet".

## 2.1 Configurar impresión WEB

* Vamos al cliente.
* Debemos acceder a la dirección `http://<nombre-del-servidor>/printers` para que aparezca en nuestro navegador un entorno que permite gestionar las impresoras de dicho equipo, previa autenticación como uno de los usuarios del habilitados para dicho fin (por ejemplo el "Administrador"). Pincha en la opción propiedades y se muestra la siguiente pantalla:

Captura de pantalla 3
conectarimpresora

* Configuramos ahora la posibilidad de imprimir desde la red en esa impresora compartida
utilizando la URL conocida, como se muestra en la siguiente pantalla:

Captura de pantalla 4:
imred3

## 2.3 Comprobar desde el navegador

Vamos a realizar seguidamente una prueba sencilla en tu impresora de red a través
del navegador pausa todos los trabajos en la impresora. Luego envía a imprimir en tu impresora compartida un documento del Bloc de notas. La siguiente pantalla muestra que la
impresora esta en pausa y con el trabajo en cola de impresión.

Captura de pantalla 5:
otraimp4

Finalmente pulsa en reanudar el trabajo para que tu documento se convierta a PDF.
Comprobar que se puede imprimir desde un cliente Windows.

> Si tenemos problemas para acceder a la impresora de red desde el cliente Windows:
> * Revisar la configuración de red de la máquina (Incluido la puerta de enlace)
> * Reiniciar el servidor Windows Server que contiene la impresora compartida de red.

---

[EN CONSTRUCCIÓN]

# 3. Servidor de impresión en el servidor

* Configurar colas/usuarios/prioridades.
