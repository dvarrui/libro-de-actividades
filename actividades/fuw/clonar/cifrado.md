

Trabajaremos en parejas.

# Encriptación

Enlace de interés:
* [GPG](https://www.genbetadev.com/seguridad-informatica/manual-de-gpg-cifra-y-envia-datos-de-forma-segura)

* Comprobar que tenemos instalado GPG.
* Crear un fichero de texto `/home/nombre-alumno/mensaje-secreto1.txt`.
* Hacer una encriptación simétrica con GPG.
* Enviar fichero al compañero para que lo desencripte.

* Crear un fichero de texto `/home/nombre-alumno/mensaje-secreto2.txt`.
* Generar un par de claves pública/privada.
* Hacer una encriptación asimétrica con GPG.
* Enviar fichero al compañero para que lo desencripte. No podrá porque falta la clave pública.
* Exportar la clave pública y pasarla al compañero.
* El compañero debe desencriptar el fichero.

# Firma

https://www.genbetadev.com/seguridad-informatica/que-son-y-para-que-sirven-los-hash-funciones-de-resumen-y-firmas-digitales

# OpenSUSE

https://doc.opensuse.org/documentation/leap/security/html/book.security/cha.security.cryptofs.html#sec.security.cryptofs.y2

## 11.1.3 Creating an Encrypted File as a Container

Instead of using a partition, it is possible to create an encrypted file, which can hold other files or directories containing confidential data. Such container files are created from the YaST Expert Partitioner dialog. Select Crypt Files › Add Crypt File and enter the full path to the file and its size. If YaST should create the container file, activate the check box Create Loop File. Accept or change the proposed formatting settings and the file system type. Specify the mount point and make sure that Encrypt Device is checked.

Click Next, enter your password for decrypting the file, and confirm with Finish.

The advantage of encrypted container files over encrypted partitions is that they can be added without re-partitioning the hard disk. They are mounted with a loop device and behave like normal partitions.

# Estenografía

De andar por casa (zip y cat):

http://www.linuxhispano.net/2014/07/03/ocultar-datos-en-imagenes-esteganografia-de-andar-por-casa/

Windows:

http://www.taringa.net/posts/linux/19356036/Esconder-archivos-en-imagenes-Esteganografia.html

Programa específico `steghide`:

http://www.reydes.com/d/?q=Ocultar_un_Archivo_de_Texto_dentro_de_un_Archivo_JPG_utilizando_Steghide

http://www.linuxadictos.com/steghide-esteganografia-para-ocultar-texto-en-imagenes.html
> Nueva actividad
> Creación Septiembre 2016

#1. Esconder ficheros

How to hide files inside images in Linux.

* [Enlace de interés](www.ostechnix.com/hide-files-inside-images-linux/)
