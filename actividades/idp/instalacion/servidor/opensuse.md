

#Servidor de actualizaciones OpenSUSE

* Crear directorio local `/opt/repo/nombre-alumnoXX`. Nos movemos a dicho directorio.
* Descargar algunos paquetes de los repos de opensuse en nuestro directorio local
    * `zypper --download-only` para descargar solo los paquetes que necesitas y sus dependencias.
    * Para descargar un repositorio entero podemos usar `wget -r URL-DEL-REPOSITORIO`
* Mover los rpm descargados desde el cache de zypper (`/var/cache/zypp/packages`) al directorio de nuestro repo.

* Dos formas de hacerlo:
    * Usar zypper y/o yast para convertir cualquier carpeta en un repositorio.
    * Instalar createrepo y ejecutar `createrepo -v /opt/repo/nombre-alumnoXX`.

> Se puede compartir este directorio al resto de equipo de la red (http, nfs, etc.)



