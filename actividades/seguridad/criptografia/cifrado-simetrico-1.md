

```
﻿Actividades unidad Criptografía
Ciclos : CFGS ASIR - CFGM SMR
Autor  : Román Carceller
```

# SAD-Cifrado simétrico (I)

# Introducción

El cifrado simétrico permite proteger un archivo de forma que, si se ha usado una contraseña robusta en el proceso cifrado, solo quien la conozca será capaz de descifrarlo. Lee la parte correspondiente al cifrado o criptografía simétrica de este artículo:
* Tipos de criptografía: simétrica, asimétrica e híbrida

> Observación: durante el curso vamos a usar el comando gpg2, compatible con su versión anterior gpg. Iremos avisándonos si observamos alguna diferencia entre el uso de gpg2 y los apuntes.

---
# Tarea

El archivo adjunto ha sido cifrado con el programa gpg2 y esta contraseña: `y[b8n9q1s[`
```
$ gpg2 -ca article.png
```

* Consulta el recurso Manual de GPG: cifra, firma y envía datos de forma segura  y el man del comando gpg2 para descífralo desde GNU/Linux.

> Consejo: puede que te parezca que no lo descifra, pero posiblemente sí lo estés haciendo bien. ¿Dónde lo almacenarás?

---

# Entrega

Sube el archivo descifrado.
Archivos
article.png.asc

---

# SAD-Cifrado simétrico (II)

# Introducción

La parte más débil del cifrado simétrico es el intercambio de la clave de cifrado y descifrado entre el emisor y el receptor.

---

# Tarea

* Sube un archivo cifrado en tu ordenador al debate "Cifrado simétrico" del foro de esta unidad indicando a quién se lo envías. Hazle llegar la contraseña para que pueda descifrarlo, pero ojo, solo puedes usar esta plataforma Moodle y nadie más que vosotros (y yo) debéis conocer la contraseña.
* Si este debate aún no existe, créalo.
* Consulta el recurso Manual de GPG: cifra, firma y envía datos de forma segura  y el man del comando gpg2 para descífralo desde Ubuntu Mate.
* Una vez descifrado, contesta a tu compañero subiendo el archivo ya descifrado.
* Estad atentos al foro por si alguien ha subido y archivo para vosotros.

---

# Entrega

Una vez subido el archivo al foro y compartida la contraseña solo con tu pareja:
* Escribe la URL del debate del foro.
* Mándame la contraseña
* Explícame cómo le has hecho llegar la contraseña a tu compañero.
