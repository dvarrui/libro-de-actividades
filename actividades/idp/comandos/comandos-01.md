
# Comandos 01

## Máquina Virtual

Partimos que ya tenemos creada la MV GNU/Linux [bien configurada](../../global/configuracion/opensuse.md).

## Acceso remoto

Vamos a configurar la máquina para permitir el acceso remoto al profesor.

* Comprobar [configuración](../../global/configuracion/opensuse.md).
* Comprobamos que la configuración está bien con:
```
ifconfig
ip a
nslookup www.google.es
ping www.google.es
```

* Comprobar [acceso remoto](../../global/acceso-remoto/opensuse.md).

## Tarea

Realizar las siguientes acciones en la MV usando los comandos:
* Crear la siguiente estructura de directorios en la carpeta Documentos de nuestro home:
```
curso1718/hardware
curso1718/sistemas-operativos
curso1718/lenguaje-de-marcas
curso1718/ingles
```
* Dentro de cada carpeta crear documento `leeme.txt`
* Dentro de dicho documento escribir el nombre y apellidos en minúsculas, sin tildes ni eñes.
