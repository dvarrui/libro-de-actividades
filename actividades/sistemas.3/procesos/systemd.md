
```
Curso       : 202122, 201920
Area        : Sistemas operativos, procesos, servicios, scripting
Descripción : Combinar procesos, servicios y scripting para solucionar problemas.
Requisitos  : Systemd, Bash, Ruby
Tiempo      :
```

# Systemd

Enlaces de interés:
* EN - [Use systemd to Start a Linux Service at Boot](https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/)
* EN - [Systemd System and Service Manager](https://www.freedesktop.org/wiki/Software/systemd/)


# 1. Crear un servicio (S1) con Bash

Vamos a ir "creando" diferentes "piezas" que acabaremos "uniendo" para crear nuestro propio servicio.

## 1.1 Script Bash que ejecuta la tarea principal

Vamos a crear un script Bash para ejecutar la tarea principal de nuestro servicio.
Este script registra el instante cuando se inicia y luego entra en un bucle infinito.

* Empezamos creando el siguiente script Bash (`alumnoXXs1.sh`):
```
#!/usr/bin/env bash
DATE=`date '+%Y-%m-%d %H:%M:%S'`

# Registra el instante en que se inicia
echo "[nombre-alumnoXX] service started at ${DATE}" | systemd-cat -p info

# El script entra en un bucle infinito
echo "Bucle infinito!";

while :
do
  # Muestra un punto en la terminal cada 1 segundo
  echo -n ".";  
  sleep 1;
done
```

> Pregunta lo que no entiendas porque caerán preguntas en los cuestionarios.

Comprobamos:
* Ejecuta el script (Recuerda los permisos de ejecución al fichero).
* Espera unos segundos y lo paras.
* Usamos el comando" journalctl" para consultar la información de monitorización registrada por systemd. Ejemplo: `sudo journalctl|grep alumno`.

## 1.2 Las rutas del PATH

Para poder invocar el script desde cualquier ruta hacemos lo siguiente:
* `echo $PATH` nos muestra las rutas de PATH. Son las rutas donde se van a buscar los comandos. Vemos que hay una ruta para poner nuestros scripts. Es /usr/local/bin que ahora está vacío.
* Copiar el script a `/usr/local/bin`.

> NOTA: Si invocamos al script desde el fichero `/home/nombre-alumno/.bashrc`, entonces nuestro programa se iniciará cuando el usuario nombre-alumno inicie sesión en el sistema. Pero como queremos que el programa se inicie al arrancar en sistema operativo... entonces seguimos con la práctica...

## 1.3 Unit file

"Unit file" es un fichero de configuración que usa systemd para establecer la configuración de cada servicio. Cada servicio tiene su propio "Unit file". Así que vamos a crea el nuestro.

* Creamos el fichero `/lib/systemd/system/alumnoXXs1.service` de la siguiente forma:

```
# Fichero `Unit file` para definir el servicio de systemd.
[Unit]
Description=Servicio 1 de alumnoXX.

[Service]
Type=simple
ExecStart=/bin/bash /usr/local/bin/alumnoXXs1.sh

[Install]
WantedBy=multi-user.target
```

Así se define un servicio sencillo. Recuerda personalizar los parámetros del fichero para tus necesidades. Pregunta las dudas porque caerán preguntas en los cuestionarios.

La directiva ExecStart especifica el comando que se ejecutará para iniciar el servicio.

## 1.4 Iniciar y activar el servicio (S1)

Recuerda que usamos el comando" journalctl" para consultar la información de monitorización registrada por systemd. Ejemplo: `sudo journalctl|grep alumno`.

* `sudo systemctl start alumnoXXs1`, para iniciar el servicio.
* `sudo systemctl status alumnoXXs1`, para comprobar el estado actual del servicio.
* Otros comandos:
    * `sudo systemctl stop alumnoXXs1`, para parar el servicio.
    * `sudo systemctl restart alumnoXXs1`, para reiniciar el servicio.
* `sudo systemctl enable alumnoXXs1`, para activar el servicio y asegurarnos de que se iniciará al arrancar el sistema.

# 2. Crear servicio (S2) con Ruby

## 2.1 Unit file

Vamos a crear un nuevo servicio con las siguientes especificaciones:
* Nombre del servicio `alumnoXXs2`. Por tanto tendremos el `Unit file` en el fichero `/lib/systemd/system/alumnoXXs2.service`.
* El servicio se ejecutará una vez, en cada reinicio de la máquina.

## 2.2 Requisitos previos

* Debemos tener un usuario `visitaXX` en el sistema.
* Debe existir el fichero `/home/visitaXX/Escritorio/leeme.txt` con el siguiente contenido:
```
AVISO

Esta cuenta de usuario no puede
guardar los cambios de forma permanente.
Es sólo temporal.
```

## 2.3 Fichero principal en Ruby

El script se encargará de restaurar en cada reinicio de máquina el directorio HOME del usuario `visitaXX` (`/home/visitaXX`).

* Crearemos un script ruby `/usr/local/bin/alumnoXXs2.rb`.
* En la ruta `/srv/alumnoXXs2/visitaXX`, tendremos una copia del HOME del usuario visitaXX.

Cada vez que se ejecute el script:
1. Se borrará el HOME del usuario visitaXX y
2. Se volverá a restaurar de la copia de seguridad.

El usuario `visitaXX`, cada vez que entre al sistema, podrá hacer cambios en los contenidos de su HOME pero al reiniciar la máquina, volverá a estar todo en su estado original.

## 2.4 Comprobamos

* Activamos el servicio(enable)
* Entramos como visitaXX
* hacemos cambios en el entorno
* Reiniciamos la máquina y
* Comprobamos que se ha restaurado el entorno.

# ANEXO

Comandos para gestionar el runlevel:
* `systemctl get-default`, para ver el target por defecto.
* `systemctl set-default multi-user.target`, para cambiar el target por defecto.
* `systemctl isolate multi-user.target`, para cambiar a runlevel 3.
* `systemctl isolate graphical.target`, para cambiar a runlevel 5.

Ficheros
* /usr/lib/systemd/system/alumno.service

```
[Unit]
Description=Reset User alumno

[Service]
Type=simple
ExecStart=/usr/bin/ruby /home/auto/reset-user.rb

[Install]
WantedBy=multi-user.target
```

* more reset-user.rb

```
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
```
