

```
﻿Actividades unidad Criptografía
Ciclos : CFGS ASIR - CFGM SMR
Autor  : Román Carceller
```

# SAD-Cifrado asimétrico - Obtener claves públicas de terceros

# Introducción

El cifrado asimétrico, al incluir las claves privadas y públicas, resuelve dos de los problemas del cifrado simétrico:
* La gran cantidad de contraseñas necesarias.
* La interceptación de la contraseña cuando viaja del emisor al receptor.

Respecto a este último problema, cuando más personas conozcan tu clave pública, más personas podrán:
* comprobar tus documentos firmados y
* cifrar documentos para que solo tú los veas.

Uno de los sistemas usados para difundir las claves públicas son los servidores de claves públicas que, normalmente, están interconectados entre sí para compartir las claves que alojan.

---

# Tareas

He subido mi clave pública 8A4B3A8C cuyo uid es Román Carceller (SAD) <roman@ieselcaminas.org> al servidor pgp.rediris.es con el comando:
`$ gpg2 --send-keys --keyserver pgp.rediris.es 8A4B3A8C`

* Consulta el recurso Manual de GPG: cifra, firma y envía datos de forma segura y el man del comando gpg para:
* Obtener mi clave pública y agregarla a tu anillo de claves públicas de Ubuntu Mate.
* Ejecuta el comando que liste por pantalla las claves públicas que tienes en tu anillo y haz una captura de pantalla tanto del comando como de la salida.

Esta operación también la puedes hacer usando el sistema gráfico de Ubuntu gracias a un navegador web o al programa Seahorse. Accede a la aplicación "Contraseñás y claves", despliega el menú Remota y selecciona la opción Buscar claves remotas. Puedes buscar por ID o por el nombre.
Observación: Las claves subidas a un servidor son accesibles desde cualquier otro, así que si tienes problemas de conexión con el servidor de claves propuesto, puedes usar cualquiera de los siguientes. Si tampoco funcionan, coméntalo en el foro.

* hkp://keyserver.ubuntu.com:11371
* hkp://pool.sks-keyservers.net
* ldap://keyserver.pgp.com

---

# Entrega
Sube la captura de pantalla generada.
