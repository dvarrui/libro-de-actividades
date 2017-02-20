

Trabajaremos en parejas.

---

# 1. Encriptación

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

> * `gpg --export -a "user name" > public.key`, exportar la clave ṕublica.
> * `gpg --export-secret-key -a "nombre del usuario" > private.key`, exportar la clave privada.

---

# 2. Firma

Enlace de interés:
* [Firma](https://www.genbetadev.com/seguridad-informatica/que-son-y-para-que-sirven-los-hash-funciones-de-resumen-y-firmas-digitales)

* Crear documentos firma.txt.
* Firmar digitalmente el documento.
* Comprobar que la firma es correcta.
* Modificar el documento.
* Comprobar que la firma es incorrecta.

---

# 3. Estenografía

Enlaces de interés:

* [De andar por casa (zip y cat)](http://www.linuxhispano.net/2014/07/03/ocultar-datos-en-imagenes-esteganografia-de-andar-por-casa/)
* [Windows](http://www.taringa.net/posts/linux/19356036/Esconder-archivos-en-imagenes-Esteganografia.html)
* [Programa específico `steghide`](http://www.reydes.com/d/?q=Ocultar_un_Archivo_de_Texto_dentro_de_un_Archivo_JPG_utilizando_Steghide)
* [steghide](http://www.linuxadictos.com/steghide-esteganografia-para-ocultar-texto-en-imagenes.html)
* [Esconder ficheros dentro de imágenes en Linux](www.ostechnix.com/hide-files-inside-images-linux/).

---

# 4. Contenedor encriptado

Enlace de interés:
* [Security](https://doc.opensuse.org/documentation/leap/security/html/book.security/cha.security.cryptofs.html#sec.security.cryptofs.y2)

En lugar de usar una partición, es posible crear un fichero encriptado, que a su vez puede contener directorios y ficheros manteniendo los datos de forma confidencial. La ventaja de usar un contenedor encriptado sobre encriptar particiones es que se pueden añadir sin tener que reparticionar el disco.
Se montan en un dispositivo Loop y se comportan como si fueran particiones normales.

* Crear un contenedor encriptado.

Estos ficheros contenedores se pueden crear usando la ventana de particionamiento de Yast.
* Elegir Crypt Files › Add Crypt File
* Poner la ruta al fichero contenedor y su tamaño.
* Activar el check box para crear el fichero Loop.
* Aceptar la configuración propuesta y el tipo de sistema de fichero.
* Especificar el punto de montaje y asegurarse de que el dispositivo encrpitado está marcado.
* Siguiente.
* Escribir la clave para desencriptar el contenedor.

* Poner archivos dentro del contenedor.
* Comprobar a acceder a los ficheros del contenedor cuando se pone la contraseña correcta y cuando no.
