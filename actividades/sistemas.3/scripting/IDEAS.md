
# IDEAS: scripting

## A.1 Restricción sobre una IP con `iptables`

* Hacer copia de seguridad (snapshot de la MV) antes de hacer esta parte.
* Enlace de interés:
    * [ Howto Ejemplos de iptables ](http://www.seavtec.com/en/content/soporte/documentacion/iptables-howto-ejemplos-de-iptables-para-sysadmins)
    * [ 20 ejemplos de iptables para sysadmins ](https://elbauldelprogramador.com/20-ejemplos-de-iptables-para-sysadmins/#parar--iniciar--reiniciar-el-firewall)
* Usar `iptables` para restringir el acceso al puerto 22 desde `ssh-clientXXb`.
* Comprobar.

## A.2 Automatizar la apertura de SSH en un intervalo temporal

* Automatizar un script que en el minuto XX (crontab) inicie el servicio SSH (systemctl).
* A partir del minuto XX+5 (crontab), si no hay ninguna conexión SSH activa (lsof), entonces bajar el servicio SSH(systemctl).

## A.3 Automatizar copias de seguridad con rsync

* Crear un script1 para hacer las copias de seguridad rsync del HOME de un usuario que se pase por parámetros.
* Crear un script2 que use script1 para crear las copias de seguridad de todos los directorios del /home.
* Automatizar script2 para que se ejecute una vez al día usando crontab.
* Automatizar script2 usando Systemd.

## Más ideas

| Idea               | Descripción |
| ------------------ | ------------------------------|
| Autoyast + script  | Incluir un script de post-instalación dentro de la configuración de autoyast. Para tener una instalación desatendida |
| Registro de hostname e IP | Crear un servicio en los clientes que se encarga de enviar información al servidor sobre su [nombre, IP] cada vez que se reinicia |
| Troyano            | Script de instalación de una aplicación que ya exista, pero que introduzca un lanzador modificado, De modo que el script primero captura información del sistema cada vez que se ejecuta y luego inicia la aplicación real.|
| History con fecha  | Script para tener un history de todas las terminales que guarde la fecha hora y el código de salida del comando. También se podrían anexar tags o descripción para ayudar a buscar.|
| Wireshark          | capturar y buscar un patrón determinado.
| KVM Proxmox        ||
