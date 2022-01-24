
```
Curso       : 202122
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura condicional
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripring: Estructura condicional

# 1. INFO: Teoría, explicaciones iniciales y ejemplos

## 1.1 INFO: Explicación del profesor usando ejemplos

**Ejemplo script secuencial**. Hacer explicación en clase usando el siguiente ejemplo:
* [ejemplo1-mkdir.sh](files/ejemplo1-mkdir.sh)
* [ejemplo2-rm.sh](files/ejemplo2-rm.sh)
* [ejemplo3-mkdir.sh](files/ejemplo3-mkdir.sh)
* [ejemplo4-rm.sh](files/ejemplo4-rm.sh)

## 1.2 INFO: Recordatorio de Bash

* Recordar que el script comienza con `#!/usr/bin/env bash`
* Para ejecutar un comando del sistema hacemos `COMANDO-DEL-SISTEMA`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.
* Ejemplo de condicional en Bash:
```
if [ CONDICION ]; then
  HACER ALGO
fi
```
* Se usa `read` para preguntar un valor al usuario.
* Se usa `$1` para leer el primer argumento, `$2` el segundo. etc.

## 1.3 INFO: Recordatorio de Ruby

* Recordar que el script comienza con `#!/usr/bin/env ruby`
* Para ejecutar un comando del sistema hacemos `system("COMANDO-DEL-SISTEMA")`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.
* Ejemplo de condicional en Ruby:
```
if (CONDICION)
  HACER ALGO
else
  HACER OTRA COSA
end
```
* Se usa `gets` para preguntar un valor al usuario de forma interactiva.
* Se usa `ARGV[0]` para leer el primer argumento, `ARGV[1]` para el segundo, etc.

---
# 2. Script condicional preguntando al usuario

Vamos a crear un script:
* usando la estructura condicional.
* y con entrada de datos del usuario.

## 2.1 Bash: condicional preguntando al usuario

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXconsulta.sh` en Bash.
* Al comienzo del script se le pregunta al usuario que quiere hacer. Su respuesta se guardará en la variable "option".
* Si option=='c' se ejecuta el script secuencial `crear-usuariosXX.sh`.
* Si option=='b' se ejecuta el script secuencial `borrar-usuariosXX.sh`.
* Si se elige otra cosa:
    * Ponemos un mensaje en pantalla.
    * Ejecutamos el comando `sl`
    * Terminamos con `exit 1`.

**Comprobamos**

* Estamos en la MV GNU/Linux.
* Abrir un terminal.
* Consultar contenido inicial de /etc/passwd con `getent passwd`.
    * ¿Qué diferencia hay entre `cat /etc/passwd` y `getent passwd`. ¿Hay uno mejor que otro? Sí/No ¿Por qué?
* Ejecutar el script eligiendo c y comprobar el resultado `getent passwd`.
* Ejecutar el script eligiendo b y comprobar el resultado  `getent passwd`.
* Ejecutar el script eligiendo e.
    * ¿Qué sucede?
    * ¿Por qué?
    * ¿Se puede solucinar?
    * ¿Cómo?

## 2.2 Ruby: condicional preguntando al usuario

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXuser.rb` en Ruby.
* Al comienzo del script se le pregunta al usuario que quiere hacer. Su respuesta se guardará en la variable "option".
* Si option=='c' se ejecuta el script secuencial `crear-usuariosXX.rb`.
* Si option=='b' se ejecuta el script secuencial `borrar-usuariosXX.rb`.
* Si se elige otra cosa:
    * Ponemos un mensaje en pantalla.
    * Ejecutamos el comando `sl`
    * Terminamos con `exit 1`.
* Comprobamos.

---
# 3. Script condicional usando variables

Vamos a crear un script:
* usando la estructura condicional.
* sin entrada de datos del usuario.
* Usaremos una variable para tomas las decisiones de control.

## 3.1 Bash: Modificar para usar variable manual

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXvar.sh` en Bash.
* Crear una variable al comienzo del script llamada "option" y le damos el valor manualmente en el script.
* Comprobarmos.

## 3.2 Ruby:  Modificar para usar variable manual

* Ir a una MV con GNU/Linux.
* Crear script `elegirXXvar.rb` en Ruby. Será una copia del anterior.
* Crear una variable al comienzo del script llamada "option" y le damos el valor manualmente en el script.
* Comprobarmos.

# 4. Script condicional usando argumentos

Vamos a crear un script:
* usando la estructura condicional.
* sin entrada de datos del usuario.
* Usaremos una variable para tomar las decisiones de control.
* La variable tomará el valor en el paso de argumentos.

## 4.1 Bash: Modificar el script para usar argumentos

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXarg.sh` en Bash. Será una copia del anterior.
* Leer el paso de argumentos al script. Los argumentos en Bash son $1, $2, etc. Entonces a la variable "option" se le asigna el valor del primer argumento. Veamos ejemplo:
    * `./elegirXXarg.sh c` para invocar las acciones de creación y
    * `./elegirXXarg.sh d` para invocar las acciones de borrado.
* Modificar el script y.
* Comprobamos.

## 4.2 Ruby: Modificar el script para usar argumentos

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXarg.rb` en Ruby. Será una copia del anterior.
* Leer el paso de argumentos. Los argumentos en Ruby son ARGV[0], ARGV[1], ARGV[2], etc. Entonces a la variable "option" se le asigna el valor del primer argumento. Veamos ejemplo:
    * `./elegirXXarg.rb c` para invocar las acciones de creación y
    * `./elegirXXarg.rb d` para invocar las acciones de borrado.
* Modificar el script y.
* Comprobamos.

# 5. Mejorar con código de salida (exit status)

Objetivos a mejorar:
* Comprobar que el usuario no existe antes de crearlo.
* Comprobar que el usuario SI existe antes de borrarlo.

Tendremos que añadir más condicionales en nuestro código.

Para cumplir estos objetivos vamos a hacerlo en 2 pasos:
* Paso 1: Averiguar que comando nos sirve para saber si existe un usuario.
* Paso 2: Recordar que cuando se termina la ejecución de un comando, éste nos devuelve un valor indicando si ha terminado bien o ha terminado mal.

## 5.1 Bash: exit status

Veamos ejemplos en Bash:

```
# Ejemplo: ha ido BIEN en Bash

> id david
uid=1000(david) gid=100(users) grupos=497(wheel),466(vboxusers),465(docker),100(users)
> echo $?
0
```

```
# Ejemplo: ha ido MAL en Bash

> id vader
id: «vader»: no existe ese usuario
> echo $?
1
```

* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXXexitstatus.sh` en Bash. Será una copia de `crear-usuariosXX.sh`.
* Modificar el script para:
   * Comprobar que el usuario NO existe antes de crearlo.
* Hacer script `borrar-usuariosXXexitstatus.sh` en Bash. Será una copia de `borrar-usuariosXX.sh`.
* Modificar el script para:
  * Comprobar que el usuario SI existe antes de borrarlo.

## 5.2 Ruby: exit status

Veamos ejemplos en Ruby:

```
# Ejemplo: ha ido BIEN en Ruby

ok = system("id david")  # => true (ok is true)
```

```
# Ejemplo: ha ido MAL en Ruby

ok = system("id vader")  # => false (ok is false)
```

* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXXexitstatus.rb` en Ruby. Será una copia de `crear-usuariosXX.rb`.
* Modificar el script para:
   * Comprobar que el usuario NO existe antes de crearlo.
* Hacer script `borrar-usuariosXXexitstatus.rb` en Ruby. Será una copia de `borrar-usuariosXX.rb`.
* Modificar el script para:
  * Comprobar que el usuario SI existe antes de borrarlo.

# 6. OPCIONAL: para subir un punto extra

Aplicar cambios mezclando Bash y Ruby.


## 6.1 INFO: esto es lo que vamos a mezclar

```
# Ejemplo: ha ido BIEN en Ruby

ok = system("id david")       # => ok is true
exitstatus = `echo $?`.to_i   # => exitstatus is 0
```

```
# Ejemplo: ha ido MAL en Ruby

ok = system("id vader")       # ok is false
exitstatus = `echo $?`.to_i   # exitstatus is 1
```

## 6.2 Mezclar Ruby con Bash

Copiar ficheros:
* Copiar `crear-usuariosXXexitstatus.rb` a `crear-usuariosXXmezcla.rb`
* Copiar `borrar-usuariosXXexitstatus.rb` a `borrar-usuariosXXmezcla.rb`

Modificar ficheros:
* Modificar `crear-usuariosXXmezcla` para leer el código de salida usando Bash.
* Modificar `borrar-usuariosXXmezcla.rb`, paara leer el código de salida usando Bash.
* Comprobar

## 4.5
