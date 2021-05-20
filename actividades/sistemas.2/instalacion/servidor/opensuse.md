
```
Curso       : 202021, 201920, 201819, 201718
Área        : Sistemas operativos, servidor, instalar, software
              Servicio Web
Descripción : Servidor de actualizaciones OpenSUSE
              Instalar un repositorio OpenSUSE
Requisitos  : GNU/Linux OpenSUSE Leap
              Paquetes RPM
              Herramienta createrepo
Tiempo      : 5 horas
```

# Servidor de actualizaciones con OpenSUSE

**Propuesta de rúbrica:**

| ID  | Criterio               | Bien(2) | Regular(1) | Poco adecuado(0) |
| --- | ---------------------- | ------- | ---------- | ---------------- |
| 3.1 | Comprobar acceso al fichero XML ||||
| 3.2 | Añadir repositorio ||||
| 3.3 | Comprobar instalaciones desde el cliente ||||

**Preparativos**. Necesitaremos las siguientes máquinas:

| ID  | Sistema Operativo | Rol |
| --- | ----------------- | --- |
| MV1 | OpenSUSE          | Servidor de actualizaciones |
| MV2 | OpenSUSE          | Cliente que instala las actualizaciones |

# 1. Servidor Web

Para compartir los paquetes de este repositorio con el resto de equipos de la red, podríamos usar diferentes protocolos (http, nfs, ftp/tftp, etc.).

Para esta práctica vamos a usar el protocolo HTTP. Por tanto, vamos a necesitar un servidor Web, de modo que los clientes se podrán conectar con el servidor de actualizaciones usando el protocolo HTTP.

## 1.1 Instalación

* Ir a la MV1 (servidor de actualizaciones).
* Instalar el servidor web Apache `zypper in apache2`
* `systemctl enable apache2`, activar apache2 al inicio.
* `systemctl start apache2`, iniciar servicio apache2.
* `systemctl status apache2`, comprobar el estado del servicio apache2.

## 1.2 Cortafuegos

* Abrir el puerto http(80) en el cortafuegos por comandos:
    * `firewall-cmd --add-service=http`
    * `firewall-cmd --permanent --add-service=http`

> También se puede hacer por Yast:
> * `Yast -> Cortafuegos -> Servicios autorizados`, añadir servicio `HTTP` y `HTTPS`.

* Ir al cliente y ejecutar `nmap -Pn ip-del-servidor`, para comprobar los servicios abiertos en el servidor. Debe aparecer abierto el servicio Web (http 80).

## 1.3 Comprobar

* Ir a la MV1 servidor.
* Crear el fichero `/srv/www/htdocs/index.html`. Escribimos nuestro nombre dentro.
* Desde la MV2 cliente abrimos navegador web y ponemos URL `http://ip-del-servidor`, para comprobar que accedemos vía HTTP a la otra MV. Si no se ve la página web, volver a revisar la conexión con el servidor y la configuración del cortafuegos en el servidor.

# 2. Preparar el repositorio local

## 2.1 Descargar ficheros rpm

Los ficheros RPM nos permiten instalar software en el sistema operativo.
A continuación vamos a descargar algunos paquetes (de los repositorios oficiales) en nuestra máquina local.

* Ir a la MV1. Entrar como usuario root.
* `zypper clean`, para limpiar todo lo que se haya quedado en la caché.
* `tree /var/cache/zypp/packages | grep rpm`, vemos una estructura de directorios sin archivos. No tenemos paquetes descargados por ahora.
* Ejecutar los siguientes comandos para descargar algunos paquetes y sus dependencias. (Descargar por ejemplo: geany, tree, nmap y/o ipcalc):
    * `zypper in --download-only PACKAGENAME`, para descargar paquete sin instalarlo,
    * `zypper -v in -f --download-only PACKAGENAME`, para descargar paquete sin
    instalarlo, cuando el software ya está instalado en nuestro sistema local.
* `tree /var/cache/zypp/packages | grep rpm`, vemos una estructura de directorios con los archivos de los paquetes descargados.

> INFO: Si quisiéramos descargar un repositorio remoto entero podríamos hacer `wget -r URL-DEL-REPOSITORIO`. Este proceso tarda mucho tiempo y no lo vamos a hacer.

## 2.2 Copiar ficheros a nuestro repositorio

* Crear directorio local `/srv/www/htdocs/repo/nombre-alumnoXX`. Esta carpeta será nuestra carpeta para el REPOSITORIO LOCAL.
* Copiar toda la estructura (copia recursiva) de directorios y ficheros desde la caché de zypper (`/var/cache/zypp/packages/*`) hasta el directorio de nuestro REPOSITORIO LOCAL (`/srv/www/htdocs/repo/nombre-alumnoXX`).
* Comprobamos el contenido del repositorio: `tree /srv/www/htdocs/repo/nombre-alumnoXX/`

## 2.3 Crear el fichero índice

Ahora hay que convertir el directorio local en un repositorio. Para ello usaremos la herramienta **createrepo**.
* Instalar la herramienta `createrepo`.
* `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, comprobamos el contenido actual del repositorio.
* `createrepo -v /srv/www/htdocs/repo/nombre-alumnoXX/`, crear los índices de nuestro repositorio. Se muestra una lista de todos los paquetes detectados en este repositorio local.
* `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, comprobamos el contenido final de nuestro repositorio. Se ha creado un subcarpeta `repodata` con ficheros XML dentro.

# 3. Cliente del repositorio

## 3.1 Comprobar acceso

* Ir a otra MV2 (Cliente OpenSUSE).
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
* Captura imagen mostrando el listado de todos los repositorios habilitados o no.
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
* Probar la instalación de algún paquete que no esté en nuestro repositorio personalizado. Por ejemplo: `zypper in chromium`
