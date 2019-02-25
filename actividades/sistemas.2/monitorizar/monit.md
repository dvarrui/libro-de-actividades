
# Monit

* Instalar Monit en el servidor.
* Renombrar el fichero `/etc/monit/monitrc` a `/etc/monit/monitrc.bak`.
* Copiar el fichero de ejemplo proporcionado por el profesor a la ruta `/etc/monit/monitrc`.
* Veamos un ejemplo:

```
 # Fichero /etc/monit/monirc de ejemplo
 # config general
set daemon 120
set logfile /var/log/monit.log
set mailserver localhost

 # Plantilla de email que se envía en las alertas
set alert nombreusuarios@correousuario.com
set mail-format {
  from: ALUMNO@EMAIL.ES
  subject: $SERVICE $EVENT at $DATE
  message: Monit $ACTION $SERVICE at $DATE on $HOST: $DESCRIPTION.
  Yours sincerely, monit
}

set httpd port 2812 and use address localhost
allow NOMBRE_ALUMNO:CLAVE_ALUMNO

 # Monitorizar los recursos del sistema
check system localhost
if loadavg (1min) > 4 then alert
if loadavg (5min) > 2 then alert
if memory usage > 75% then alert
if cpu usage (user) > 70% then alert
if cpu usage (system) > 30% then alert
if cpu usage (wait) > 20% then alert

 # Monitorizar el servicio SSH
check process sshd with pidfile /var/run/sshd.pid
start program "service sshd start"
stop program  "service sshd stop"
if failed port 22 protocol ssh then restart
if 5 restarts within 5 cycles then timeout
```

* Modificar el fichero /etc/monit/monitrc para adaptarlo a nuestra máquina.
* Reiniciar el servicio: /etc/init.d/monit restart
* Comprobar la lectura de datos de monit vía comandos: `monit status`
* Comprobar la lectura de datos de monit vía GUI.
    * Abrir un navegador web en la propia máquina, y poner URL `http://localhost:2812`.
    * Escribir nombreusuario/claveusuario de monit (Según hayamos configurado en monitrc).
* Capturar pantalla.
