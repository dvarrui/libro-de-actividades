
```
Nuevo Curso 201617
```

# Servidor de actualizaciones con OpenSUSE

Necesitaremos 2 MV con OpenSUSE.
* Un servidor
* Un cliente

---

# 1. Servidor Web

Vamos a necesitar un servidor Web para que los clientes se puedan conectar
con elservidor de actualizaciones usando el protocolo HTTP.

* Ir a la MV servidor de actualizaciones.
* Instalamos el servidor web Apache `zypper in apache2`
* `systemctl enable apache2`, activar apache2 al inicio.
* `systemctl start apache2`, iniciar servicio apache2.
* `systemctl status apache2`, comprobar el estado del servicio apache2.
* Crear el fichero `/srv/www/htdocs/index.html`. Escribimos nuestro nombre dentro.
* Desde la MV cliente abrimos navegador web y ponemos URL `http://ip-del-servidor`,
para comprobar que accedemos vía HTTP a la otra MV.

> Si no se ve la página web, comprobar el cortafuegos en el servidor.
> Ejecutando `nmap -Pn ip-del-servidor` desde el cliente, comprobamos los servicios
abiertos en el servidor.
>
> Para abrir el servicio web en el cortafuegos:
> * `Yast -> Cortafuegos -> Servicios autorizados`, añadir servicio `HHTP Server`

---

# 2. Preparar el repositorio local

* Vamos al servidor
* Crear directorio local `/srv/www/htdocs/repo/nombre-alumnoXX`.

Vamos a descargar algunos paquetes de los repos de OpenSUSE en nuestra máquina local.
    * `tree /var/cache/zypp/packages`, vemos una estructura de directorios sin archivos.
    * `zypper -v --download-only NOMBRE-PAQUETE` para descargar solo los paquetes que necesitas y sus dependencias. Ejemplo:
    * `zypper in --download-only geany tree nano dia`
    * `tree /var/cache/zypp/packages`, vemos una estructura de directorios con
    los archivos de los paquetes descargados.

> Para descargar un repositorio entero podemos usar `wget -r URL-DEL-REPOSITORIO`.
> Pero esto tardaría mucho tiempo.

* Copiar los directorios/ficheros descargados desde la cache de zypper (`/var/cache/zypp/packages`)
al directorio de nuestro repositorio local.
    * Comprobamos `tree /srv/www/htdocs/repo/nombre-alumnoXX/`

Ahora hay que convertir el directorio local en un repositorio.
* Instalar `createrepo`.
* Usar createrepo:
    * `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, ver estado actual.
    * `createrepo -v /srv/www/htdocs/repo/nombre-alumnoXX/`, crear índices.
        * Tiene que mostrar una lista de todos los paquetes detectados en este repositorio local.
    * `vdir /srv/www/htdocs/repo/nombre-alumnoXX/`, comprobar.

> Se tiene que crear un subcarpeta `repodata` con ficheros xml dentro.

Se pueden compartir los paquetes de este repositorio al resto de equipo de la red
usando diferentes protocolos (http, nfs, ftp/tftp, etc.). Nosotros hemos elegido usar
el protocolo HTTP (Servidor Web).

---

# 3. Cliente del repositorio

* Ir a otra MV OpenSUSE
Comprobar acceso:
* Abrir navegador y poner URL `http://ip-del-servidor/repo/nombre-alumnoXX/repodata/reponmd.xml`
    * Debe verse el contenido del fichero XML.

Vamos a añadir nuestro repositorio a esta MV.
* Ir a `Yast -> Repositorios`
* Añadir.
* Seleccionar: HTTP y Descargar archivos de descripción de repositorio
* Siguiente.
* Editar partes de la URL.
    * Nombre de repositorio: `Repositorio de NOMBRE-ALUMNO`
    * Protocolo: HTTP
    * Nombre del servidor: ip-del-servidor
    * Puerto: 80
    * Directorio: `/repo/nombre-alumnoXX/repodata/`
    * Autenticación: Anónimo

> * `zypper addrepo http://hostname/repo alias`

* Deshabilitar(propiedad activar OFF) el resto de repositorios:
    * OpenSUSE Leap 42.2-Update-Non-Oss,
    * OpenSuse Leap 42.2-Non-Oss
    * OpenSuse Leap 42.2-Oss
    * OpenSuse Leap 42.2-Update
* Habilitar(porpiedad activar ON) sólo el `Repositorio de NOMBRE ALUMNO`
* Aceptar y cerrar Yast.
* `zypper refresh`, refrescar los repositorios.
* Probar la instalación de algún paquete de nuestro repositorio.
* Probar la instalación de algún paquete que no esté en nuestro repositorio.
