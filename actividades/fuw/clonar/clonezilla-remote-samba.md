
# Clonezilla remote Samba

Vamos a hacer una práctica de clonación del tipo `device-image`, usando
como repositorio remoto una carpeta compartida de red SMB/CIFS (Samba Server).

# 1. Entrega

* Trabajar de forma individual.
* Entregar un PDF con el informe del trabajo realizado acompañado de capturas
de pantalla/fotos.

---

# 2. Preparativos

Necesitaremos 2 máquinas virtuales:
* MV1 con SO Windows7 ([Configuración](../../global/configuracion/windows.md)).
* MV2 con SO Windows Server ([Configuración](../../global/configuracion/windows-server.md)).

## 2.1 Prepara la MV1

* Añadir un 2º disco de la MV Windows7 de 100MB.
* Iniciar la MV Windows7.
* Ir a `Equipos -> botón derecho -> administrar -> gestión de almacenamiento -> Añadir nuevo disco`.
    * Elegir MBR.
    * Asignar letra de unidad `E:`
    * Formatear el 2º disco (100MB) NTFS.
* Grabar en dicho disco:
    * Un fichero de texto (`file1-XX`).
    * Una imagen (`file2-XX`).
    * Un vídeo y/o canción (`file3-XX`).

## 2.2 Preparar la MV2

* Crear el usuario `nombre-del-alumno`.
* Añadir un 2º disco de la MV Windows Server de 1GB.
* Ir a `Equipos -> botón derecho -> administrar -> gestión de almacenamiento -> Añadir nuevo disco`.
    * Elegir MBR.
    * Asignar letra de unidad `E:`
    * Formatear el 2º disco (1GB) NTFS.
* Crear una carpeta `E:\sambaXX`.

Vamos a configurar dicha carpeta como recurso compartido de red:
* Botón derecho sobre la carpeta -> Compartir.
* Uso compartido avanzado.
* Poner `imagenesXX` como nombre del recurso compartido.
* Dicho recurso compartido debe estar en modo lectura/escritura para el usuario `nombre-del-alumno`.

## 2.3 Comprobar el recurso de red

Comprobamos el acceso al recurso remoto:
* Vamos al equipo Windows7.
* Explorador de archivos -> Accedemos al recurso compartido `\\172.19.XX.21\\imagenesXX`
* Para autenticarnos ponemos usuario/clave que hayamos creado en el servidor.
* A modo de prueba escribir un archivo de texto en dicho recurso.
* Si funciona, pasamos al siguiente apartado.

---

# 3. Clonación

* Obtener una ISO de Clonezilla (Descargar ISO de Leela)
* Iniciar la MV Windows7 con la distro Clonezilla.
* Realizar clonación:
    * Tipo *device-image*.
    * Elegir `samba_server` como repositorio de almacenamiento. Para esto usaremos el recurso compartido del Window Server, con al usuario/clave que hemos creado.
    * Podemos configurar la tarjeta de red en modo DHCP.
    * Modo *beginner*.
    * `saveparts`, para guardar la partición.
    * Elegir para grabar sólo la partición del disco 2.

Comprobamos que hay fichero de imagen en el servidor:
* Vamos al servidor
* Consultamos el directorio `E:\sambaXX\`.
* Tiene que estar la imagen de clonación hecha con Clonezilla.
* En caso contrario, comprobar permisos de lectura/escritura del usuario del recurso compartido.

# 4. Restauración

Ahora vamos a restaurar.
* Capturar imagenes de los pasos que vamos a realizar.
* Eliminar los ficheros clonados del Windows7 (file1-XX, file2-XX y file3-XX).
* Realizar el proceso de restauración de la imagen (creada en el recurso compartido SMB/CIFS)
para recuperar los ficheros eliminados.
* Comprobar que se han restaurado los ficheros que se esperaban.
