
```
Curso : 202122
FAQ   : Scripting, comandos
```

# Preguntas frecuentes asociadas a problemas de Scripting


## 1. Pero secuencial, ¿qué significa exactamente?

**Alumno**: Pero secuencial, ¿qué significa exactamente?
Como si lo tuviera que hacer 10 veces lo de crear usuarios, ¿te refieres a eso?

**Profesor**: En un script sólo secuencial:
1) No puedes usar condicionales.
2) No puedes usar bucles, ni iteradores.
3) Sólo instrucciones en secuencia.... una detrás de otra.

Ahora ¿hazme la pregunta que te viene a la cabeza? :-)

## 2. Comprobar si existe un usuario X

**Profesor**: Hay varias formas de comprobar si existe un usuario X. Las pongo de "mejor" a "peor"
* Usar el comando `getent`. El comando "getent passwd" devuelve la información de passwd, y el comando "getent shadow" la información de shadow, de usuarios locales o del dominio. ¿Recuerdas lo que era dominio?
* Usar el comando `id`.
* [REGULAR] Usar el comando `cat | grep XXX` sobre los ficheros de passwd o shadow.
* [MALA IDEA]: Comprobar si existe el HOME. Esto NO es buena idea porque puedes tener HOME sin existir el usuario.

## 3. Uso de colores

**Profesor**

El uso de colores esn Bash... es un poco "raro" pero funciona. En Ruby se puede usar el mismo sistema que Bash pero... mejor usaremos alguna de las siguientes gemas:
* colorize (https://rubygems.org/gems/colorize)
* rainbow (https://rubygems.org/gems/rainbow)
