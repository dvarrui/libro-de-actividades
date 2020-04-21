
```
Curso       : 201819, 201718
Área        : Sistemas operativos, servidor, instalar, software
Descripción : Servidor de actualizaciones para OpenSUSE
Requisitos  : GNU/Linux OpenSUSE Leap
Tiempo      : 5 horas
```

# Servidor de actualizaciones con OpenSUSE

Necesitaremos las siguientes máquinas:
* MV1: Un servidor con OpenSUSE.
* MV2: Un cliente con OpenSUSE.

# 1. Servidor Web

Vamos a necesitar un servidor Web para que los clientes se puedan conectar
con el servidor de actualizaciones usando el protocolo HTTP.

## 1.1 Instalación

* Ir a la MV1 (servidor de actualizaciones).
* Instalamos el servidor web Apache `zypper in apache2`
* `systemctl enable apache2`, activar apache2 al inicio.
* `systemctl start apache2`, iniciar servicio apache2.
* `systemctl status apache2`, comprobar el estado del servicio apache2.

## 1.2 Cortafuegos

Abrir el servicio web en el cortafuegos:
* `Yast -> Cortafuegos -> Servicios autorizados`, añadir servicio `HHTP Server`
* Ir al cliente y ejecur `nmap -Pn ip-del-servidor` parar comprobar los servicios abiertos en el servidor (Debe aparecer abierto el servicio Web).

## 1.3 Comprobamos

* Crear el fichero `/srv/www/htdocs/index.html`. Escribimos nuestro nombre dentro.
* Desde la MV cliente abrimos navegador web y ponemos URL `http://ip-del-servidor`, para comprobar que accedemos vía HTTP a la otra MV. Si no se ve la página web, volver a revisar la conexión con el servidor y la configuración del cortafuegos en el servidor.

# 2. Preparar el repositorio local

## 2.1 Descargar ficheros rpm

* Ir a la MV1.
* Ahora vamos a descargar algunos paquetes (de los repositorios oficiales) en nuestra máquina local.
* `tree /var/cache/zypp/packages | grep rpm`, vemos una estructura de directorios sin archivos.
* Ejecutar los siguientes comandos para descargar algunos paquetes y sus dependencias. (Descargar por ejemplo: geany, nano, dia, nmap y/o ipcalc):
    * `zypper in --download-only PACKAGENAME`, para descargar paquete sin instalarlo,
    * `zypper -v in -f --download-only PACKAGENAME`, para descargar paquete sin
    instalarlo, cuando el software ya está instalado en nuestro sistema local.
* `tree /var/cache/zypp/packages | grep rpm`, vemos una estructura de directorios con los archivos de los paquetes descargados.

> INFO: Si quisiéramos descargar un repositorio remoto entero podríamos hacer `wget -r URL-DEL-REPOSITORIO`. Este proceso tarda mucho tiempo y no lo vamos a hacer.

## 2.2 Copiar a nuestro repositorio

* Crear directorio local `/srv/www/htdocs/repo/nombre-alumnoXX`. Esta carpeta será nuestro REPOSITORIO LOCAL.
* Copiar toda la estructura de directorios y ficheros desde la caché de zypper (`/var/cache/zypp/packages/*`) hasta el directorio de nuestro REPOSITORIO LOCAL.
    * Comprobamos `tree /srv/www/htdocs/repo/nombre-alumnoXX/`

## 2.3 Crear el fichero índice

Ahora hay que convertir el directorio local en un repositorio. Para ello vamos a usar la herramienta `createrepo`.
* Instalar la herramienta `createrepo`.
* `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, comprobamos el contenido actual del repositorio.
* `createrepo -v /srv/www/htdocs/repo/nombre-alumnoXX/`, crear los índices de nuestro repositorio. Se muestra una lista de todos los paquetes detectados en este repositorio local.
* `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, comprobamos el contenido final de nuestro repositorio. Se ha creado un subcarpeta `repodata` con ficheros xml dentro.

> Se pueden compartir los paquetes de este repositorio al resto de equipo de la red usando diferentes protocolos (http, nfs, ftp/tftp, etc.). Nosotros hemos elegido usar el protocolo HTTP (Servidor Web).

# 3. Cliente del repositorio

## 3.1 Comprobar acceso

* Ir a otra MV2 (Cliente OpenSUSE)
* Abrir navegador y poner URL `http://ip-del-servidor/repo/nombre-alumnoXX/repodata/repomd.xml`, para comprobar que que tenemos acceso desde MV2 a los ficheros de MV1.
* Debe verse el contenido del fichero XML.

## 3.2 Añadir nuevo repositorio

Vamos a añadir nuestro repositorio en la MV2.

* Ir a MV2.
* Ir a `Yast -> Repositorios`. Añadir nuevo repositorio.
* Seleccionar: HTTP y Descargar archivos de descripción de repositorio
* Nombre de repositorio: `nombre-alumnoXX`
* URL del repositorio: `http://ip-del-servidor/repo/nombre-alumnoXX/`
* Autenticación: Anónimo
* OJO: Hacer captura de la lista de repositorios actual. Esta lista la vamos a cambiar y guardamos la información para poder restaurar los valores si nos hace falta.
* Deshabilitar todos los repositorios.
* Habilitar sólo el `nombre-alumnoXX`. De esta forma nos aseguramos que MV2 únicamente podrá instalar paquetes desde nuestro repositorio en MV1.
* Aceptar y cerrar Yast.
* `cat /etc/zypp/repos.d/nombre-alumnoXX.repo`, comprobamos que la configuración del repositorio nuevo está en este fichero de texto.

> INFO: Podríamos haber hecho este apartado usando sólo el terminal. Esto es:
> 1. Creando el fichero `/etc/zypp/repo.d/nombre-alumnoXX.repo`, con lo valores requeridos.
> 2. `zypper addrepo http://ip-del-servidor/repo/nombre-alumnoXX nombre-alumnoXX`, añadiendo el repositorio nuevo.

## 3.3 Comprobamos el repositorio desde el cliente

* Ir a MV2.
* `zypper refresh`, refrescar los repositorios.
* Probar la instalación de algún paquete de nuestro repositorio personalizado. Por ejemplo: `zypper in geany`
* Probar la instalación de algún paquete que no esté en nuestro repositorio personalizado. Por ejmplo: `zypper in chromium`
