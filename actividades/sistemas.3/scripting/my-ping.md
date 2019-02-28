```
Última modificación: viernes, 21 de febrero de 2014, 12:32
```

# Scriptiong: MyPing

Script MyPing con salida HTML

# MyPing

La idea es crear un script que monitorice la conectividad con diversas máquinas (usando el comando ping), y que devuelva los resultado por pantalla o en fichero HTML.

---

# Configuración

Crear el fichero `/etc/myping/myping.conf` con las IP's que vamos a monitorizar. Veamos un ejemplo:
```
192.168.1.1:router
192.168.1.104:quigon
192.168.1.107:obiwan
192.168.1.112:darth-maul
192.168.1.113:dath-vader
```

Como se ve, cada línea contiene IP y nombre/descripción de máquina, separados por dos puntos.

---

# Parámetros del script

Crear un script en `/usr/local/bin/myping`.

Dicho script responderá a los siguientes parámetros de entrada:

| Parámetro | Descripción | Salida |
| --------- | ----------- | ------ |
| --screen, -s | Revisa las IP's del fichero de configuración y muestra los resultados por pantalla |	Pantalla |
| --html, -o, --output | Revisa las IP's del fichero de configuración y escribe los resultados en el fichero myping.html | Fichero |
| (Sin argumentos), -h, --help | Muestra esta ayuda | Pantalla |

---

# Revisar las IP's

El script debe lanza un ping a cada IP del fichero de configuración, para comprobar la conectividad (Comandos ping, grep, wc, echo, touch, rm, etc.)

En el caso de no haber conexión la salida mostrará el mensaje de ERROR.

---

# Salida HTML

La salida HTML crea el fichero `myping.html` en el directorio adecuado para poder ser consultado desde un navegador Web con el URL http://ip-de-la-maquina/myping.html.
