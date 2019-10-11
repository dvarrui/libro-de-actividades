
# Comandos 01

## Máquina Virtual

Partimos de la MV (sin entorno gráfico), que ya tenemos creada.

---

## Acceso remoto (Revisar)

Vamos a configurar la máquina para permitir el acceso remoto al profesor.

* Comprobar [configuración](../../global/configuracion/debian.md).
* Comprobamos que la configuración está bien con:
```
ip a               # Comprobar la IP
ping 8.8.4.4       # Comprobar Gateway
host www.google.es # Comprobar DNS
```

* Comprobar [acceso remoto](../../global/acceso-remoto/opensuse.md).

---

## Tarea

Realizar las siguientes acciones en la MV usando los comandos:
* Crear la siguiente estructura de directorios en la carpeta HOME de nuestro usuario (`/home/usuario`):
```
curso1920/hardware
curso1920/sistemas-operativos
curso1920/lenguaje-de-marcas
curso1920/ingles
```
* Dentro de cada carpeta crear documento `leeme.txt`
* Dentro de dicho documento escribir el nombre y apellidos en minúsculas, sin tildes ni eñes.
