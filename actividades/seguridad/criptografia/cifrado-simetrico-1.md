

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
