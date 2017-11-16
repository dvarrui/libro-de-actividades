
# Servidor de Impresión en Windows

Vamos a ver principalmente cómo imprimir a través de la red utilizando Windows Server
como servidor de impresión.

[Enlaces de interés:](http://www3.gobiernodecanarias.org/medusa/eforma/campus/mod/page/view.php?id=748283)
    * [Impresoras virtuales PDF](http://www.genbeta.com/herramientas/impresoras-virtuales-pdf-tres-alternativas-gratuitas-en-espanol)
    * PDF Creator: http://pdfcreator.es/. Para activar el modo AUTOSAVE vamos a Ajustes -> Autosave. Ahí configuramos carpeta destino.
    * doPDF: www.dopdf.com/es/
    * BullZIP PDF Printer: http://www.bullzip.com/products/pdf/info.php#Introduction

---

# 1. Impresora Local compartida por red

Vamos a conectar e instalar localmente una impresora al servidor Windows Server,
de modo que estén disponibles para ser accedidas por los clientes del dominio.

En nuestro caso, dado que es posible de que no tengan una impresora física en casa
y no es de mucho interés forzar la instalación de una impresora que no se tiene,
vamos a instalar un programa llamado PDFCreator .

PDFCreator es una utilidad completamente gratuita con la que podrás crear archivos
PDF desde cualquier aplicación, desde el Bloc de notas hasta Word, Excel, etc.
Este programa funciona simulando ser una impresora, de esta forma, instalando
PDFCreator todas tus aplicaciones con opción para imprimir te permitirán crear
archivos PDF en cuestión de segundos.

impresora

Para crear un archivo PDF no hará falta que cambies la aplicación que estés usando,
simplemente ve a la opción de "imprimir" y selecciona "PDFCreator", en segundos
tendrás creado tu archivo PDF. Rápido y fácil. La instalación de este programa
no tiene dificultad simplemente elegir la opción "Instalación estándar".

> NOTA: PDFCreator puede requerir NET FrameWork v4.

Puedes probar la nueva impresora abriendo el Bloc de notas y creando un fichero luego selecciona imprimir y como impresora predeterminada el PDFCreator. Cuando finalice el proceso se abrirá un fichero PDF (requiere que instales el Acrobat reader) con el resultado de la impresión:

Captura de pantalla 1
pdf

Finalmente, comparte la impresora en tu servidor y como nombre del recurso compartido utiliza PDFTuNombreTusApellidos. La siguiente imagen muestra los recursos compartidos
en el servidor incluido la impresora:

Captura de pantalla 2
imprec

---

# 2. Acceso Web

Realizaremos una configuración para habilitar el acceso web a las impresoras del dominio.

Analizaremos el modo en que los clientes de nuestra red se pueden conectar a las impresoras que se "ofrecen" en nuestro "Active Directory" y que se encuentran instaladas y accesibles. Lo primero que realizaremos será configurar la impresión WEB en los clientes. Instalar el servicio "Impresión de Internet".

Ahora desde el equipo cliente Windows o el propio servidor, debemos acceder a la dirección "http://<nombre del servidor>/printers" para que aparezca en nuestro navegador un entorno que permite gestionar las impresoras de dicho equipo, previa autenticación como uno de los usuarios del dominio habilitados para dicho fin (por ejemplo el "Administrador"). Pincha en la opción propiedades y se muestra la siguiente pantalla:

Captura de pantalla 3
conectarimpresora

Configuramos ahora la posibilidad de imprimir desde la red en esa impresora compartida
utilizando la URL conocida, como se muestra en la siguiente pantalla:

Captura de pantalla 4:
imred3

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
