

```
Pendiente de probar
```

# Servidor de actualizaciones OpenSUSE

Necesitaremos 2 MV con OpenSUSE.
* Un servidor
* Un cliente

## Preparar el repositorio local

* Vamos al servidor
* Crear directorio local `/opt/repo/nombre-alumnoXX`. Nos movemos a dicho directorio.
* Descargar algunos paquetes de los repos de OpenSUSE en nuestro directorio local
    * `zypper --download-only` para descargar solo los paquetes que necesitas y sus dependencias.
    * Para descargar un repositorio entero podemos usar `wget -r URL-DEL-REPOSITORIO`. Pero esto
    tardaría mucho tiempo.
* Mover los ficheros rpm descargados desde la cache de zypper (`/var/cache/zypp/packages`)
al directorio de nuestro repositorio local.

* Ahora hay que convertir el directorio local en un repositorio. Tenemos dos formas de hacerlo:
    * Usar zypper y/o yast para convertir cualquier carpeta en un repositorio.
    * Instalar `createrepo` y ejecutar `createrepo -v /opt/repo/nombre-alumnoXX`.

> Se puede compartir este directorio al resto de equipo de la red (http, nfs, etc.)

# Cliente del repositorio

* Ir a otra MV OpenSUSE
* Añadir en repositorios nuestro servidor
* Desactivar el resto de repositorios
* Probar la instalación de los paquetes
