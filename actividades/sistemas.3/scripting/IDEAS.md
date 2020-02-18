
# IDEAS: scripting

| Idea               | Descripción |
| ------------------ | ------------------------------|
| Registrar hostname | Servicio en el cliente que envía información al servidor sobre su nombre e IP cada vez que se reinicia |
| Iniciar App        | Script de instalación de una aplicación que ya exista, pero que introduzca un lanzador previo que captura información del sistema cada vez que se ejecuta.|
| History con fecha  | Script para tener un history de todas las terminales que guarde la fecha hora y el código de salida del comando. También se podría anexar tags o descripción para ayudar a buscar.|
| Cortafuegos        | Gestionar configuraciones más usadas.|
| Wireshark          | capturar y buscar un patrón determinado.
| KVM Proxmox        ||


## Idea.1 Restricción sobre una IP con `iptables`

* Hacer copia de seguridad (snapshot de la MV) antes de hacer esta parte.
* Enlace de interés:
    * [ Howto Ejemplos de iptables ](http://www.seavtec.com/en/content/soporte/documentacion/iptables-howto-ejemplos-de-iptables-para-sysadmins)
    * [ 20 ejemplos de iptables para sysadmins ](https://elbauldelprogramador.com/20-ejemplos-de-iptables-para-sysadmins/#parar--iniciar--reiniciar-el-firewall)
* Usar `iptables` para restringir el acceso al puerto 22 desde `ssh-clientXXb`.
* Comprobar.

## Idea.2 Automatizar la conexión SSH

* Automatizar un script que en el minuto XX (crontab) inicie el servicio SSH (systemctl).
* A partir del minuto XX+5 (crontab), si no hay ninguna conexión SSH activa (lsof), entonces bajar el servicio SSH(systemctl).

## Idea 3. Copias de seguridad con rsync

* Crear un script1 para hacer las copias de seguridad rsync del HOME de un usuario que se pase por parámetros.
* Crear un script2 que use script1 para crear las copias de seguridad de todos los directorios del /home.
* Automatizar script2 para que se ejecute una vez al día usando crontab.
* Automatizar script2 usando Systemd.

## Idea.4 Captura de pantalla

* Crear script que cada intervalo de 5 segundos hace una captura de pantalla no interactiva(scrot o "convert x:root image.png").
* Si la imagen N es diferente a la N-1 entonces se guarda.
* Las capturas se guardarán como /var/capturas/image01.png.
