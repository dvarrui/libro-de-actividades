
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
* Crear la siguiente estructura de directorios en la carpeta HOME de nuestro usuario (`/home/nombre-del-alumno`):
```
curso1920
├── hardware
├── ingles
├── lenguaje-de-marcas
└── sistemas-operativos
```

* Dentro de cada carpeta crear documento `leeme.txt`. Podemos usar el comando `nano` para crear y editar ficheros de texto plano.
* Dentro de dicho documento escribir el nombre y apellidos en minúsculas, sin tildes ni eñes.

---
# ANEXO

**Videotutoriales:**
* [Curso de Linux Básico - Clase 1](https://www.youtube.com/watch?v=09hrTL5w-Jc)
