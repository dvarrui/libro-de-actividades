
# Problemas con Docker Compose

# 1.

**Descripción:** Este es el problema que tengo en el Compose, he cambiado el archivo .yaml varias veces, pero hay algo raro en mi MySQL porque no funciona cuando me quiero conectar, lo he instalado varias veces, pero me pone diferentes errores depende de como acceda, si accedo utilizando la ip utilizando el docker compose activo me da un error y si me conecto desde local me da otro.

![](04/mysql.error.png)

**Profesor:**

1. La primera imagen parece que hay un problemas con el cortafuegos. Luego hiciste algo que no comentas y pasamos a la segunda imagen.
2. En la segunda imagen tenemos un problema con los `sockets`.

El enunciado pone...

```
Ir a una máquina donde podamos tener el cliente MySQL. OpenSUSE no, porque por defecto se instala MariaDB que no es 100% compatible con MySQL.

nmap -Pn localhost, comprobar que el puerto (mysql) está abierto.

(El siguiente punto ha estado dando un fallo de sockets en algunas máquinas. En tal caso, se lo saltan hasta que averigüemos la causa)
```
