

```
Pendiente de probar
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

---

# 2# Preparar el repositorio local

* Vamos al servidor
* Crear directorio local `/srv/www/htdocs/repo/nombre-alumnoXX`. Nos movemos a dicho directorio.
* Descargar algunos paquetes de los repos de OpenSUSE en nuestro directorio local
    * `zypper --download-only PAQUETE` para descargar solo los paquetes que necesitas y sus dependencias.

> Para descargar un repositorio entero podemos usar `wget -r URL-DEL-REPOSITORIO`.
> Pero esto tardaría mucho tiempo.

* Mover los ficheros rpm descargados desde la cache de zypper (`/var/cache/zypp/packages`)
al directorio de nuestro repositorio local.

* Ahora hay que convertir el directorio local en un repositorio. Tenemos dos formas de hacerlo:
    * Usar zypper y/o yast para convertir cualquier carpeta en un repositorio.
    * Instalar `createrepo` y ejecutar `createrepo -v /srv/www/htdocs/repo/nombre-alumnoXX`.

> Se puede compartir este directorio al resto de equipo de la red (http, nfs, etc.)

# Cliente del repositorio

* Ir a otra MV OpenSUSE
* Añadir en repositorios nuestro servidor
* Desactivar el resto de repositorios
* Probar la instalación de los paquetes
