
```
EN CONSTRUCCIÓN!!!
```

# systemd

Enlaces de interés:
* EN - [Use systemd to Start a Linux Service at Boot](https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/)
* EN - [Systemd System and Service Manager](https://www.freedesktop.org/wiki/Software/systemd/)

---

# 1. Crear un servicio (S1)

* Vamos a crear un script para gestionar nuestro servicio.
Por ejemplo usando el siguiente script Bash (`alumnoXXs1.sh`):
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
* Crear el `Unit file` para definir el servicio de systemd. Creamos el fichero `/lib/systemd/system/alumnoXXs1.service`:
```
    [Unit]
    Description=Servicio 1 de alumnoXX.

    [Service]
    Type=simple
    ExecStart=/bin/bash /usr/local/bin/alumnoXXs1.sh

    [Install]
    WantedBy=multi-user.target
```
> Así se define un servicio sencilla. La directiva ExecStart especifica el comando que se ejecutará para iniciar el servicio.

---

# 2. Iniciar y activar el servicio (S1)

* `sudo systemctl start alumnoXXs1`, para iniciar el servicio.
* `sudo systemctl status alumnoXXs1`, para comprobar el estado actual del servicio.
* Otros comandos:
    * `sudo systemctl stop alumnoXXs1`, para parar el servicio.
    * `sudo systemctl restart alumnoXXs1`, para reiniciar el servicio.
* `sudo systemctl enable alumnoXXs1`, para activar el servicio y asegurarnos de que se iniciará al arramcar el sistema.

---

# 3. Crear servicio S2

Vamos a crear un nuevo servicio con las siguientes especificaciones:
* Nombre del servicio `alumnoXXs2`. Por tanto tendremos el `Unit file` en el fichero `/lib/systemd/system/alumnoXXs2.service`.
* El servicio se ejecutará una vez, en cada reinicio de la máquina.
* Crearemos un script ruby `/usr/local/bin/alumnoXXs2.rb` que se encargará de gestionar las acciones del servicio.
* Debemos tener definido en el sistema el usuario `visitaXX`.
* Debe existir el fichero `/home/visitaXX/Escritorio/leeme.txt` con el siguiente contenido:
```
AVISO

Esta cuenta de usuario no puede
guardar los cambios de forma permanente.
Es sólo temporal.
```
* El script se encargará de restaurar en cada reinicio de máquina el directorio HOME del usuario `visitaXX` (`/home/visitaXX`). El usuario `visitaXX` puede hacer cambios en los contenidos de su HOME pero al reiniciar la máquina, volverá a estar todo en su estado inicial.

---


# ANEXO

```
alumno@telesforo:~> more /usr/lib/systemd/system/alumno.service 
[Unit]
Description=Reset User alumno

[Service]
Type=simple
ExecStart=/usr/bin/ruby /home/auto/reset-user.rb

[Install]
WantedBy=multi-user.target
```

```
alumno@telesforo:/home/auto> more reset-user.rb 
#!/usr/bin/ruby
# encoding: utf-8

puts "[INFO] Executing <#{$0}>..."

def reset_alumno
  puts "* Setting password"
  system('echo "alumno:alumno" | chpasswd')

  puts "* Remove home files"
  system('rm -rf /home/alumno')

  puts "* Restarting home files"
  system('cd /home/auto && tar xvf alumno.tar')
  system('mv /home/auto/alumno /home')
end

FILE="/home/auto/last_execution.dat"
last_execution=(`cat #{FILE}`).to_i
now=Time.now
today=now.year*10000+now.yday

if today>last_execution
  system("echo #{today.to_s} > #{FILE}")
  reset_alumno
  puts "[INFO] OK!"
else
  puts "[INFO] Nothing done!"
end

alumno@telesforo:/home/auto> 

```
