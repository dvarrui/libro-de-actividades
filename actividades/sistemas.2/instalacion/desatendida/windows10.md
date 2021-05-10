
```
Autor       : Francisco Vargas Ruiz
Estado      : PENDIENTE DE PROBAR!
Curso       :
Area        : Sistemas operativos, instalaciones
Descripción : Instalación desatendida con Windows 10
Requisitos  : Windows 10, ImgBurn
Tiempo      :
```

# Instalación desatendida de Windows 10

**Entrega:**
* Informe detallado con capturas de pantalla, de la creación de una ISO de instalación desatendida de Windows 10.
* Demostrar al profesor en el aula el correcto funcionamiento de la imagen ISO en una máquina virtual.

**Seguir los pasos indicados por en el enlace siguiente**
* https://www.windowscentral.com/how-create-unattended-media-do-automated-installation-windows-10

---

# Aclaraciones al tutorial del enlace

## FASE: Instalar el software Windows ADK.

* Importante: sólo marcar la característica 2 "Deployment tools" ("Herramientas de implementación") a la hora de instalar el software.

## FASE: Crear proyecto para el fichero de respuestas.

* En nuestro caso no tenemos el fichero "install.esd" en su lugar ya disponemos del fichero "install.win" y por tanto no hay que crearlo.
* En nuestro caso no necesitamos usar el programa Rufus.

## FASE: Preparando el entorno para el fichero de respuestas.

## FASE: Crear el fichero de respuestas para Windows 10.

* Este este paso se define el contenido para el archivo "autounattend.xml".

## FASE: Grabar el proyecto del fichero de respuestas.

* Este este paso se crea el archivo "autounattend.xml".

## FASE: Añadir el fichero de respuestas a la iSO de instalación.

* Ir la MV de Windows.
* Montar la ISO de Windows 10 y copiar el contenido en C:\ficheros-ISO.
* Copiar el `autounattend.xml` en C:\ficheros-ISO.
* Instalar ImgBurn.
* Ejecutar ImgBurn -> Crear imagen desde carpeta.
* Elegir la carpeta C:\ficheros-ISO
* Advance -> Bootable Disk. Vamos a crear una ISO de arranque
* Guardar la nueva ISO como `windows10-alumnoXX.iso`
* Comprobar el resultado en una nueva máquina virtual.
