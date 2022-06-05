
```
Curso       : 2022
Área        : Sistemas operativos
Descripción : Crear paquete MSI
Requisitos  : Windows
Tiempo      : 4 horas
```

# Crear paquete MSI

> **IMPORTANTE**: Vamos a crear un "snapshot" de la máquina virtual, por seguridad.

## Crear el paquete MSI con EMCO Software

* Ir a la MV WIndows10.
* Instalar el programa: https://emcosoftware.com/msi-package-builder
* Crear un paquete MSI con el programa EMCO Software. Tutoriales:
    * https://www.youtube.com/watch?v=Ak1z1iadfQw
    * https://www.youtube.com/watch?v=AZXhZDx2zSY&list=UUm3zqS2cJGK_Zdeq_-SzBKg&index=3 (Creado por JA Mora).

---
# ANEXO A

## Instalar WinINSTALL

> **OTROS PROGRAMAS**: Si tenemos problemas con el programa WinINSTALL, podemos usar [Advanced Installer](https://www.advancedinstaller.com/)

Ahora vamos a crear nuestro propio paquete de instalación MSI, usando la herramienta WinINSTALL. Si ya tenemos el software que queremos instalar en los clientes en formato MSI, nos podemos saltar este apartado.

**En el servidor**
* Descargar el programa WinINSTALL y lo instalamos.
    * http://www.downloadsource.es/3414/WinINSTALL-LE/
    * http://www.freewarefiles.com/downloads_counter.php?programid=52066
* Una vez instalada la aplicación hemos de asignar permisos de acceso al recurso compartido de WinINSTALL al usuario `Administrador` en modo lectura.

## 2.3 Crear paquete MSI

> Enlaces de interés:
> * [Crear paquetes MSI con WinINSTALL](http://www.ite.educacion.es/formacion/materiales/85/cd/windows/11Directivas/crear_paquetes_msi.html).
> * [Vídeo - Crear MSI con WinINSTALL](https://www.youtube.com/watch?v=cBzmcROYAcA)

Si disponemos del paquete MSI nos saltamos este apartado.

> **NOTA**: Podemos [descargar el programa Firefox en formato msi](https://support.mozilla.org/en-US/kb/deploy-firefox-msi-installers) directamente.

**En el cliente**
* Entramos con el usuario administrador del dominio.
* Descargar el instalador de Firefox. **¡OJO! Sólo descargar** (NO instalar todavía). El instalador de Firefox debe tener un tamaño de varios MBs. Si tiene pocos KBs no es el instalador, sino un programa para descargar el instalador.
* `Inicio -> Ejecutar -> \\ip-del-servidor\WinINSTALL\Bin\Discover.exe`,
para iniciar la aplicación WinINSTALL LE de forma remota,

![pdc-wininstall-discover.png](./files/pdc-wininstall-discover.png)

* Indicamos el nombre que vamos a asociar al paquete MSI (`firefoxXX.msi`).
* Ruta de red donde almacenaremos el MSI, en nuestro caso
`\\ip-del-servidor\softwareXX\firefox\firefoxXX.msi`.

![pdc-wininstall-select-target.png](./files/pdc-wininstall-select-target.png)

* Unidad donde se almacenarán los ficheros temporales => C:.
* Unidades que serán analizadas para realizar la foto inicial;
en nuestro caso sobre la unidad C: de nuestro equipo cliente.
* Indicar los ficheros que serán excluidos del análisis;
en nuestro caso aceptaremos las opciones propuestas por el asistente por defecto.
* Pulsamos `Finish` para comenzar la generación de la foto inicial del equipo.

> **ADVERTENCIA**: Durante el tiempo comprendido entre la ejecución de este proceso y la ejecución del proceso de la foto final, es crítico ejecutar únicamente el software de instalación del paquete MSI a generar.
> Cualquier modificación que se haga durante este proceso, se grabará en el paquete MSI obtenido, aunque no forme parte de las modificaciones realizadas de la aplicación durante su instalación.

* Una vez que la foto inicial haya sido realizada, pulsamos Aceptar, y
a continuación se nos mostrará otra ventana en el que seleccionaremos el fichero de instalación de la aplicación de la que vamos a generar el paquete MSI.En nuestro caso el fichero `firefox.exe` que nos habíamos descargado.
* Comienza la instalación de la aplicación de `firefox.exe` de modo manual.
* Volvemos a Inicio -> ejecutar -> `\\ip-del-servidor\WinINSTALL\Bin\Discover.exe`, para iniciar el proceso de creación de la foto final del sistema (_Este que puede durar varios minutos_).
* Podremos confirmar que el paquete ha sido creado correctamente en el equipo "SERVIDOR", yendo a la carpeta `E:\softwareXX\firefox`.
* Limpiamos el equipo cliente:
    * Eliminar el fichero `firefox.exe` que nos habíamos descargado.
    * Desinstalar el programa Firefox del cliente.

## Advanced Installer

Advanced Installer
New -> IT Pro
Convert
MSI from EXE

## Duda

Parece que la directiva siguiente no es compatible con la instalación de software:
* En la sección `Configuración de usuario / Directivas / Plantillas administrativas / Escritorio` ( User configuration / Administrative Templates / Active Desktop)
    * `Ocultar el icono Ubicaciones de red del escritorio`.

![pdc-wininstall-domain-user.png](./files/pdc-wininstall-domain-user.png)
