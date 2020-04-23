
```
Última modificación: domingo, 9 de marzo de 2014, 13:27
```

# Scripting: MyNetwork

Cambiar configuración de red

# MyNetwork

La idea es crear un script que permita cambiar la configuración de red de una máquina GNU/Linux Debian, de forma sencilla.

---

# Configuración

En el directorio `/etc/mynetwork` tendremos los ficheros de las configuraciones de red, para cada situación que se nos ocurra.

Supongamos que queremos crear una configuración de red para el caso de "casa", entonces crearemos:
* El fichero /etc/mynetwork/casa.interfaces, con el contenido que debe tener el fichero /etc/network/interfaces.
* El fichero /etc/mynetwork/casa.resolvconf, con el contenido que debe tener el fichero /etc/resolv.conf.

Si necesitamos otras configuraciones, como por ejemplo para "ies" y para "dhcp", crearemos más ficheros de configuración, de modo que nos quedaría algo como:
* /etc/mynetwork/casa.interfaces
* /etc/mynetwork/casa.resolvconf
* /etc/mynetwork/dhcp.interfaces
* /etc/mynetwork/dhcp.resolvconf
* /etc/mynetwork/ies.interfaces
* /etc/mynetwork/ies.resolvconf

---

# Parámetros del script

Crear un script en /usr/local/bin/mynetwork.

Dicho script responderá a los siguientes parámetros de entrada:

| Parámetro  | Descripción |
| ---------- | ----------- |
| --list, -l | Muestra todos los nombres de configuraciones disponibles. |
| --info NAME, -i NAME |Muestra en pantalla el contenido de la configuración NAME.  |
| --set NAME, -s NAME |	Cambia la configuración de red actual por la configuración de red NAME. |
| (Sin argumentos), --help, -h |	Muestra la ayuda del script |

Por ejemplo:
```
# mynetwork --list
Configuraciones disponibles:
* casa
* dhcp
* ies

# mynetwork --info casa
=> Muestra el contenido del fichero casa.interfaces
=> Muestra el contenido del fichero casa.resolvconf

# mynetwork --set casa
=> Copia casa.interfaces en /etc/network/interfaces
=> Copia cada.resolvconf en /etc/resolv.conf
=> Reiniciar el servicio de red para que lea los cambios.
```
