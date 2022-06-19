
```
Curso       : 202122, 202021, 201920, 201819, 201718
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

| ID  | Hostname | Rol |
| --- | -------- | --- |
| MV1 | serverXX | Servidor de actualizaciones |
| MV2 | clientXX | Cliente que instala las actualizaciones |

# 1. Servidor Web

Para compartir los paquetes de este repositorio con el resto de equipos de la red, se pueden usar diferentes protocolos (http, nfs, ftp/tftp, etc.). En esta práctica vamos a usar el protocolo HTTP. Por tanto, vamos a necesitar un servidor Web, de modo que los clientes se podrán conectar con el servidor de actualizaciones usando el protocolo HTTP.

## 1.1 Instalación

Ir a la MV1:
* Instalar el servidor web Apache (Paquete `apache2`).
* Activar apache2 al inicio (`systemctl enable ...`).
* Iniciar servicio apache2.
* Comprobamos el estado del servicio apache2.

## 1.2 Cortafuegos

Abrir el puerto http(80) en el cortafuegos por comandos:
* `firewall-cmd --add-service=http`
* `firewall-cmd --permanent --add-service=http`

> También se puede hacer por Yast:
> * `Yast -> Cortafuegos -> Servicios autorizados`, añadir servicio `HTTP` y `HTTPS`.

* Crear el fichero `/srv/www/htdocs/index.html`. Escribimos nuestro nombre dentro para personalizarlo.

## 1.3 Comprobar el acceso remoto

Ir a la MV2:
* Ejecutar `nmap -Pn ip-del-servidor`, para comprobar los servicios abiertos del servidor. Debe aparecer abierto el servicio Web (http 80).
* Abrimos navegador web y ponemos URL `http://ip-del-servidor`, para comprobar que accedemos vía HTTP a la otra MV. Si no se ve la página web, volver a revisar la conexión con el servidor y la configuración del cortafuegos en el servidor.

# 2. Preparar el repositorio local

## 2.1 Descargar ficheros rpm

Los ficheros RPM nos permiten instalar software en el sistema operativo.
A continuación vamos a descargar algunos paquetes (de los repositorios oficiales) en nuestra máquina servidor local.

Ir a la MV1
* Entrar como usuario root.
* `zypper clean`, para limpiar todo lo que se haya quedado en la caché de zypper.
* `tree /var/cache/zypp/packages | grep rpm`, vemos que no tenemos paquetes rpm descargados por ahora.
* Descargamos varios paquetes y sus dependencias. Por ejemplo descargar los siguientes paquetes: geany, tree, nmap e ipcalc.

| Acción | Comando |
| ------ | ------- |
| Descargar paquete sin instalarlo, cuando el paquete NO está instalado | `zypper in --download-only PACKAGENAME` |
| Descargar paquete sin instalarlo, cuando el software SI está instalado | `zypper -v in -f --download-only PACKAGENAME` |

* `tree /var/cache/zypp/packages | grep rpm`, vemos una estructura de directorios con los archivos de los paquetes descargados.

> INFO: Si quisiéramos descargar un repositorio remoto entero podríamos usar el comando `wget`. Por ejemplo `wget -r URL-DEL-REPOSITORIO`. Este proceso tarda mucho tiempo y no lo vamos a hacer.

## 2.2 Copiar ficheros a nuestro repositorio

Ir a la MV1:
* Como usuario root.
* Crear directorio local `/srv/www/htdocs/repo/nombre-alumnoXX`. Esta carpeta será nuestra carpeta para el REPOSITORIO LOCAL.
* Copiar toda la estructura (copia recursiva) de directorios y ficheros desde la caché de zypper (`/var/cache/zypp/packages/*`) hasta el directorio de nuestro REPOSITORIO LOCAL (`/srv/www/htdocs/repo/nombre-alumnoXX`).
* Comprobamos el contenido del repositorio: `tree /srv/www/htdocs/repo/nombre-alumnoXX/`

## 2.3 Crear el fichero índice

Ahora hay que convertir el directorio local en un repositorio. Para ello usaremos la herramienta **createrepo**.
* Como usuario root.
* Instalar la herramienta `createrepo`.
* `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, comprobamos el contenido actual del repositorio.
* `createrepo -v /srv/www/htdocs/repo/nombre-alumnoXX/`, crear los índices de nuestro repositorio. Se muestra una lista de todos los paquetes detectados en este repositorio local.
* `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, comprobamos el contenido final de nuestro repositorio. Se ha creado un subcarpeta `repodata` con ficheros XML dentro.

# 3. Cliente del repositorio

## 3.1 Comprobar acceso

Ir a MV2:
* Abrir navegador y poner URL `http://ip-del-servidor/repo/nombre-alumnoXX/repodata/repomd.xml`, para comprobar que que tenemos acceso desde MV2 a los ficheros de MV1.
* Debe verse el contenido del fichero XML.

## 3.2 Añadir nuevo repositorio

Vamos a añadir nuestro nuevo repositorio personal en los ficheros de configuración de la MV2.
* Ir a MV2.
* Ir a `Yast -> Repositorios`. Añadir nuevo repositorio.
* Seleccionar: HTTP y Descargar archivos de descripción de repositorio
* Nombre de repositorio: `nombre-alumnoXX`
* URL del repositorio: `http://ip-del-servidor/repo/nombre-alumnoXX/`
* Autenticación: Anónimo

> OJO: Hacer captura de la lista de repositorios actual. Esta lista la vamos a cambiar y guardamos la información para poder restaurar los valores si nos hace falta.

* Captura imagen mostrando el listado de todos los repositorios habilitados o no.
* Deshabilitar todos los repositorios y habilitar únicamente el repositorio `nombre-alumnoXX`. De esta forma nos aseguramos que MV2 únicamente podrá instalar paquetes desde nuestro repositorio en MV1.
* Aceptar y cerrar Yast.
* `cat /etc/zypp/repos.d/nombre-alumnoXX.repo`, comprobamos que la configuración del repositorio nuevo está en este fichero de texto.

> INFO: Podríamos haber hecho este apartado usando sólo el terminal. Esto es:
> 1. Creando el fichero `/etc/zypp/repo.d/nombre-alumnoXX.repo`, con lo valores requeridos.
> 2. `zypper addrepo http://ip-del-servidor/repo/nombre-alumnoXX nombre-alumnoXX`, añadiendo el repositorio nuevo.

## 3.3 Comprobamos el repositorio desde el cliente

* Ir a MV2.
* `zypper refresh`, refrescar los repositorios.
* Probar la instalación de algún paquete de nuestro repositorio personalizado. Por ejemplo: `zypper in geany`
* Probar la instalación de algún paquete que no esté en nuestro repositorio personalizado. Por ejemplo:
    * `zypper in audacity`
    * `zypper in chromium`

---
# ANEXO: Revisar

Configuración:
* Nombre del equipo y del usuario.
* IP's de las máquinas.

Informe:
* Sangrado uniforme.
* Revisar el aspecto después de subirlo al repositorio.
* Poner texto y a continuación imagen asociada al texto anterior y así con cada imagen. OJO: NO poner todo el texto y al final toda la secuencia de imágenes.

Imágenes:
* Capturar comando y su salida completa.

Comandos:
* Si se ejecuta un comando requerido por la práctica, y el resultado no es el correcto/esperado... hay que resolverlo porque se seguimos lo más probable es que el resto falle. Además que se percibe que NO tenemos idea de para qué estamos usando el comando.
