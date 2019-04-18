
```
EN CONSTRUCCIÓN!!!
```

# systemd

Enlaces de interés:
* EN - [Use systemd to Start a Linux Service at Boot](https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/)
* EN - [Systemd System and Service Manager](https://www.freedesktop.org/wiki/Software/systemd/)

---

# 1. Crear un servicio

* Vamos a crear un script para gestionar nuestro servicio.
Por ejemplo usando el siguiente script Bash (`service_alumnoXX.sh`):
```
DATE=`date '+%Y-%m-%d %H:%M:%S'`
echo "[nombre-alumnoXX] service started at ${DATE}" | systemd-cat -p info

while :
do
  echo "Looping...";
  sleep 30;
done
```
> Este script registra el instante cuando se inicia y luego entra en un bucle infinito.

* Copiar el script a `/usr/local/bin` y hacerlo ejecutable.
* Crear el `Unit file` para definir el servicio de systemd. Creamos el fichero `/lib/systemd/system/alumnoXX.service`:
```
    [Unit]
    Description=Servicio de alumnoXX.

    [Service]
    Type=simple
    ExecStart=/bin/bash /usr/local/bin/service_alumnoXX.sh

    [Install]
    WantedBy=multi-user.target
```
> Así se define un servicio sencilla. La directiva ExecStart especifica el comando que se ejecutará para iniciar el servicio.

---

# 2. Iniciar y activar el servicio

* `sudo systemctl start service_alumnoXX`, para iniciar el servicio.
* `sudo systemctl status service_alumnoXX`, para comprobar el estado actual del servicio.
* Otros comandos:
    * `sudo systemctl stop service_alumnoXX`, para parar el servicio.
    * `sudo systemctl restart service_alumnoXX`, para reiniciar el servicio.
* `sudo systemctl enable service_alumnoXX`, para activar el servicio y asegurarnos de que se iniciará al arramcar el sistema.

---

# 3. Cambiar el script del servicio.

* Crear otro script con la especificaciones del profesor para crear un servicio personalizado.
