
# IDEAS: scripting

| Idea               | Descripción |
| ------------------ | ------------------------------|
| Registrar hostname | Servicio en el cliente que envía información al servidor sobre su nombre e IP cada vez que se reinicia |
| Capturar imagen | Capturar la pantalla de forma silenciosa (scrot o "convert x:root image.png") y enviar el resultado de forma remota.
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
