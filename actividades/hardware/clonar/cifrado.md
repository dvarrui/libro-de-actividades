
```
Módulos recomendados : Seguridad (2asir), Fundamentos Hardware (1asir)
Curso                : 201819
Softwatre            : OpenSUSE Leap 15.0
Tiempo estimado      : 6 sesiones (2 semanas)
Entrega por defecto  : Informe tipo tutorial
```
---

# Ocultación de la información

En prácticas anteriores habíamos comprobado que los ficheros, aunque se
borren se pueden recuperar. Para hacer un borrado real hay que usar determinadas herramientas de borrado seguro. De esta forma nos aseguramos que nadie puede acceder a los ficheros que habíamos borrado.

Pero si queremos tener ficheros con datos que sólo nosotros (o nuestros amigo) podamos acceder, entonces tenemos que usar técnicas de ocultación.

En este taller vamos a practicar diversas formas de ocultar nuestros datos.

* Nos podemos ayudar en parejas. Los llamaremos alumno1(yo) y alumno2(el compañero).
* Cada alumno entregará un informe propio.
* Entregaremos un informe con capturas de pantalla.

---

# 1. Encriptación

Enlaces de interés:
* Información sobre la herramienta [GPG](https://www.genbetadev.com/seguridad-informatica/manual-de-gpg-cifra-y-envia-datos-de-forma-segura)
* [Manual de GPG: cifra, firma y envía datos de forma segura](https://www.genbeta.com/desarrollo/manual-de-gpg-cifra-y-envia-datos-de-forma-segura)

## 1.1 Encriptado simétrico

* Asegurarnos de tener instalado GPG (`zypper info gpg2`).
* Trabajaremos con nuestro usuario habitual. No usar root.
* Crear un fichero de texto `/home/nombre-alumno/mensaje1-nombre-alumnoXX.txt`.
    * Escribir dentro el nombre del alumno, la fecha de hoy y un título de una película.
* Hacer una encriptación simétrica con GPG.
    * `gpg -c mensaje1-nombre-alumnoXX.txt`
* Enviar fichero encriptado al compañero (alumno2) para que lo desencripte.
    * `gpg -d mensaje1-nombre-alumnoXX.txt.gpg`

## 1.2 Encriptado asimétrico

Alumno1(Nosotros):
* Alumno1 genera un par de claves pública/privada.
    * `gpg --gen-key`, genera un par de claves pública/privada.
    * Consultar las claves públicas con alguno de los siguientes comandos
    `gpg --list-public-keys`, `gpg --list-keys` o `gpg -k`. La línea `pub 2048R/IDNUMBER`,
    muestra la información con el identificador de la clave pública.
    * `tree .gnupg`, Comprobaremos que se crea un directorio oculto, dentro del home de nuestro usuario con el nombre `.gnugp`. Ahí es donde se guarda la información de claves de GPG para nuestro usuario.
* Alumno1 exporta la clave pública para pasarla al compañero.
    * `gpg --output nombre-alumnoXX.pub.gpg --export PUBLIC_KEY_IDNUMBER`
    * El valor PUBLIC_KEY_IDNUMBER lo obtenemos al consultar la salida del comando anterior (gpg -k).

Alumno2(Nuestro compañero):
* Alumno2 crea un fichero de texto `/home/nombre-alumno/mensaje2-nombre-alumnoXX.txt`.
    * Escribir dentro el nombre del alumno, la fecha de hoy y una frase/mensaje.
* Alumno2 importa la clave pública del compañero (alumno1).
    * `gpg --import nombre-alumnoXX.pub.gpg`
* Alumno2 hace una encriptación asimétrica con GPG con la clave pública recibida.
    * `gpg --list-public-keys`, para ver las claves públicas que tenemos.
    * `gpg --encrypt --recipient PUBLIC_KEY_IDNUMBER mensaje2-nombre-alumnoXX.txt`
* Alumno2 envía el fichero a alumno1 para que lo desencripte.

Alumno1(Nosotros):
* Alumno1 desencripta el fichero `gpg -d mensaje2-nombre-alumnoXX.txt.gpg`.

> Se entiende que podemos desencriptar el fichero porque ha sido encriptado
con nuestra clave pública por parte del compañero que nos envía el archivo.
>
> Otros comandos de interés:
>
> * `gpg --export -a “Nombre de Usuario"`, muestra la clave pública para el "Nombre de Usuario" en la línea de comandos.
> * `gpg --export-secret-key -a "Nombre de Usuario" > private.key`, Esto creará un archivo llamado private.key con la representación ASCII de la clave privada para "Nombre de Usuario".
> * `gpg --import public.key`, Importar una clave pública.
> * `gpg --allow-secret-key-import --import private.key`, Importar una clave privada.
> * `gpg --delete-key "Nombre de Usuario"`, Esto elimina la clave pública de tu anillo de claves.

---

# 2. Firma

> Enlace de interés:
>
> * [Información sobre firma GPG](https://www.genbetadev.com/seguridad-informatica/que-son-y-para-que-sirven-los-hash-funciones-de-resumen-y-firmas-digitales)

Hacer lo siguiente:
* Crear documento `firma-nombre-alumnoXX.txt`.
    * Escribir dentro el nombre del alumno, la fecha de hoy y un grupo de música.
* Vamos a firmar digitalmente el documento en modo ASCII.
    * `gpg --clearsign firma-nombre-alumnoXX.txt`
* Consultar el fichero que se ha generado con la firma `firma-alumnoXX.txt.asc`
* Comprobar que la firma es correcta.
    * `gpg --verify firma-nombre-alumnoXX.txt.asc`
* Modificar el documento `firma-nombre-alumnoXX.txt.asc`.
* Comprobar que ahora el fichero tiene la firma incorrecta.

---

# 3. Esteganografía

> Enlaces de interés:
>
> * [De andar por casa (zip y cat)](http://www.linuxhispano.net/2014/07/03/ocultar-datos-en-imagenes-esteganografia-de-andar-por-casa/)
> * [Windows](http://www.taringa.net/posts/linux/19356036/Esconder-archivos-en-imagenes-Esteganografia.html)
> * [Programa específico `steghide`](http://www.reydes.com/d/?q=Ocultar_un_Archivo_de_Texto_dentro_de_un_Archivo_JPG_utilizando_Steghide)
> * [steghide](http://www.linuxadictos.com/steghide-esteganografia-para-ocultar-texto-en-imagenes.html)
> * [Esconder ficheros dentro de imágenes en Linux](http://www.ostechnix.com/hide-files-inside-images-linux/).

Hacemos lo siguiente:
* Consultar enlace sobre estenografía de "Andar por casa (zip y cat)".
* Crear un fichero de texto con un mensaje oculto (`secreto-nombre-alumnoXX.txt`).
    * Escribir dentro el nombre del alumno, la fecha de hoy y un refrán o frase famosa.
* Crear un fichero zip con el mensaje oculto.
* Descargar una `imagen1-nombre-alumnoXX.png` que nos guste.
* Incrustar el fichero zip dentro de la `imagen1-nombre-alumnoXX.png`, obteniendo un fichero `imagen2-nombre-alumnoXX.png`.
* Pasar el fichero `imagen2-nombre-alumnoXX.png` al compañero.
* Verificar el formato de las imágenes, bien usando el comando `file` o capturando vista de la misma.
* El compañero debe aplicar el proceso necesario para extraer el mensaje oculto dentro de la `imagen2-nombre-alumnoXX.png`.

---

# 4. Partición encriptada

## 4.1 Teoría: contenedor encriptado

Es posible crear un fichero encriptado, que a su vez puede contener directorios y ficheros manteniendo los datos de forma confidencial. La ventaja de usar un contenedor encriptado sobre encriptar particiones es que se pueden añadir sin tener que reparticionar el disco. Se montan en un dispositivo Loop y se comportan como si fueran particiones normales.

## 4.2 Proceso: partición encriptada

* Por seguridad, vamos a hacer una instantánea de la MV antes de empezar con este apartado.
* Crear directorio `/home/nombre-alumno/datos-cifradosXX.d`.
* Ir a `Yast -> Particionador`. Crear una partición encriptada.
* Montar partición encriptada en la carpeta anterior.
* Reiniciar la MV para que active los cambios que hemos realizado.
* Escribir el password del contenedor para poder activarlo.
* `df -hT | grep datos-cifradosXX.d`, comprobamos que hay un dispositivo montado.
* Poner archivos dentro del contenedor.
* Reiniciar la MV.
* Comprobar a acceder a los ficheros del contenedor cuando se pone la contraseña correcta y cuando no.

> Curiosidad: ¿Qué ocurre si iniciamos la MV con un CDLive como Knoppix e intentamos acceder a los datos que están encriptados...?
>
> * Configurar MV sin EFI para poder inciar Knoppix.

---

# ANEXO

* `gpg --export -a "user name" > public.key`, exportar la clave ṕublica.
* `gpg --export-secret-key -a "nombre del usuario" > private.key`, exportar la clave privada.

## Intercambiar claves

Enlaces de interés:
* https://www.gnupg.org/gph/es/manual/x75.html
* https://elbauldelprogramador.com/chuleta-de-comandos-para-gpg/#exportar-una-clave-p%C3%BAblica-dentro-del-archivo-public-key
* https://access.redhat.com/documentation/en-US/Red_Hat_Enterprise_Linux/4/html/Step_by_Step_Guide/s1-gnupg-export.html

Para poder comunicarse con otros, el usuario debe intercambiar las claves públicas.
Para obtener una lista de las claves en el fichero («anillo») de claves públicas, se puede usar la opción de la línea de órdenes --list-keys.

```
javier:~$ gpg --list-keys

/home/javier/.gnupg/pubring.gpg
--------------------------------
pub  1024D/D58711B7 1999-09-24 Javier (Paramo S.L.) <javier@casa.es>
sub  1024g/92F6C9E3 1999-09-24
```

## Exportar una clave pública

Para poder enviar una clave pública a un interlocutor, antes hay que exportarla. Para ello se usará la opción de la línea de órdenes --export. Es necesario un argumento adicional para poder identificar la clave pública que se va a exportar. Hay que usar el identificador de clave o cualquier parte del identificador de usuario para identificar la clave que se desea exportar.

```
javier:~$ gpg --output javi.gpg --export javier@casa.es
```

La clave se exporta en formato binario, y esto puede no ser conveniente cuando se envía la clave por correo electrónico o se publica en una página web. Por tanto, GnuPG ofrece una opción de la línea de órdenes --armor[1] que fuerza que la salida de la orden sea generada en formato armadura-ASCII, parecido a los documentos codificados con uuencode. Por regla general, cualquier salida de una orden de GnuPG, v.g.. claves, documentos cifrados y firmas, pueden ir en formato armadura-ASCII añadiendo a la orden la opción --armor.

```
javier:~$ gpg --armor --output javi.asc --export javier@casa.es
-----BEGIN PGP PUBLIC KEY BLOCK-----
Version: GnuPG v0.9.8 (GNU/Linux)
Comment: For info see http://www.gnupg.org

[...]
-----END PGP PUBLIC KEY BLOCK-----
```

## Importar una clave pública

Se puede añadir una clave pública al anillo de claves públicas mediante la opción --import.

```
javier:~$ gpg --import arancha.gpg
gpg: key B63E132C: public key imported
gpg: Total number processed: 1
gpg:               imported: 1

javier:~$ gpg --list-keys
/home/javier/.gnupg/pubring.gpg
--------------------------------
pub  1024D/D58711B7 1999-09-24 Javier (Paramo S.L.) <javier@casa.es>
sub  1024g/92F6C9E3 1999-09-24

pub  1024D/B63E132C 1999-09-24 Aranzazu (A.G.deZ.) <arancha@nav.es>
sub  1024g/581A915F 1999-09-24
```

Una vez que la clave haya sido importada, es necesario validarla. GnuPG usa un potente y flexible modelo de confianza que no requiere que el usuario dé validez personalmente a cada clave que importe. Sin embargo, algunas claves pueden necesitar que el usuario les dé validez de forma personal. Una clave se valida verificando la huella digital de la clave, y firmando dicha clave para certificar su validez. La huella digital se puede ver con la opción de la línea de órdenes --fingerprint, pero para certificar la clave hay que editarla.

```
javier:~$ gpg --edit-key arancha@nav.es

pub  1024D/B63E132C  created: 1999-09-24 expires: never      trust: -/q
sub  1024g/581A915F  created: 1999-09-24 expires: never
(1)  Aranzazu (A.G.deZ.) <arancha@nav.es>

Command> fpr
pub  1024D/B63E132C 1999-09-24 Aranzazu (A.G.deZ.) <arancha@nav.es>
             Fingerprint: 4203 82E2 448C BD30 A36A  9644 0612 8A0F B63E 132C
```

La huella digital de una clave se verifica con el propietario de la clave. Esto puede hacerse en persona o por teléfono, o por medio de otras maneras, siempre y cuando el usuario pueda garantizar que la persona con la que se está comunicando sea el auténtico propietario de la clave. Si la huella digital que se obtiene por medio del propietario es la misma que la que se obtiene de la clave, entonces se puede estar seguro de que se está en posesión de una copia correcta de la clave.

Después de comprobar la huella digital ya se puede firmar la clave con el fin de validarla. Debido a que la verificación es un punto débil en criptografía de clave pública, es aconsejable ser cuidadoso en extremo y siempre comprobar la huella digital de una clave con la que nos dé el propietario antes de firmar dicha clave.

```
Command> sign

pub  1024D/B63E132C  created: 1999-09-24 expires: never      trust: -/q
             Fingerprint: 4203 82E2 448C BD30 A36A  9644 0612 8A0F B63E 132C

     Aranzazu (A.G.deZ.) <arancha@nav.es>

Are you really sure that you want to sign this key
with your key: "Javier (Paramo S.L.) <javier@casa.es>"

Really sign? y

You need a passphrase to unlock the secret key for
user: "Javier (Paramo S.L.) <javier@casa.es>"
1024-bit DSA key, ID D58711B7, created 1999-09-24

Enter passphrase:

Una vez firmada, el usuario puede comprobar la clave para obtener un listado de las firmas que lleva y para ver la firma que le acaba de añadir. Cada identificador de usuario tendrá una o más autofirmas, así como una firma por cada usuario que haya validado la clave en cuestión.

Command> check

uid  Aranzazu (A.G.deZ.) <arancha@nav.es>
sig!       B63E132C 1999-09-24   [self-signature]
sig!       D58711B7 1999-09-24   Javier (Paramo S.L.) <javier@casa.es>

Command> quit
```
