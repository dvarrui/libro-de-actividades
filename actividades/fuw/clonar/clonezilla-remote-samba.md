
#Clonezilla remote Samba

Vamos a hacer una práctica de clonación del tipo device-image, usando 
como repositorio remoto un Samba Server (SMB/CIFS server).

##1. Entrega

* Trabajar de forma individual.
* Entregar un PDF con el informe del trabajo realizado acompañado de capturas
de pantalla/fotos.

##2. Preparativos
Necesitaremos 2 máquinas virtuales:
* MV1 con SO Windows7
    * IP: 172.19.XX.11
    * Gateway: 172.19.0.1
    * DNS: 8.8.4.4
    * Nombre NetBios: win7aluXX (donde XX es el número del puesto del alumno)
    * Grupo de trabajo: aula108
* MV2 con SO Windows 2012 Server
    * IP: 172.19.XX.21
    * Gateway: 172.19.0.1
    * DNS: 8.8.4.4
    * Nombre NetBios: w2012aluXX (donde XX es el número del puesto del alumno)
    * Grupo de trabajo: aula108
* Abrir los cortafuegos de ambas máquinas para permitir hacer `ping` entre ellas.

##3. Windows7
* Añadir un 2º disco de la MV Windows7 de 100MB.
* Iniciar la MV Windows7
* Formatear el 2º disco (100MB) NTFS.
* Grabar en dicho disco:
    * Un fichero de texto.
    * Una imagen.
    * Un vídeo y/o canción.

##4. Windows 2012 server
* Crear el usuario `nombre-del-alumno` con DNI como clave.
* Añadir un 2º disco de la MV Windows Server 2008 de 1GB.
* Formatear el 2º disco (1GB) NTFS.
* Crear una carpeta `D:\sambaXX`.
* Establecer dicha carpeta la como recurso compartido de red con el nombre `imagenesXX`.
* Dicho recurso compartido debe estar en modo lectura/escritura para el usuario `nombre-del-alumno`.

##5. Clonación
* Obtener una ISO de Clonezilla (Descargar ISO de Leela)
* Iniciar la MV Windows7 con la distro Clonezilla.
* Realizar clonación:
    * Tipo *device-image*.
    * Elegir `samba_server` como repositorio de almacenamiento. Para esto usaremos el recurso
    compartido del Window Server, con al usuario/clave que hemos creado.
    * Modo *beginner*.
    * `saveparts`, para guardar la partición.
    * Elegiar para grabar sólo la partición del disco 2.
     
##6. Restauración
Comprobar que la restauración de la imagen creada en el recurso compartido SMB/CIFS es correcta.
