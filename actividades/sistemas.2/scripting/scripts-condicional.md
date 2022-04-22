
```
Curso       : 201920
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura condicional
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripring: Estructura condicional

# 1. INFO: Explicación del profesor usando ejemplos

**Ejemplo script condicional**. Hacer explicación en clase usando los siguientes [ejemplos](files/condicional).

# 2. Crear usuarios y borrar usuarios en GNU/Linux

## 2.1 Crear el script

Vamos a crear un script que usando la estructura condicional iterativa van a realizar la siguiente tareas:
* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXX.sh` en shell script para crear un número MAX usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1g", "nombre-alumno2g", etc.
    * Recordar que también queremos que se cree el HOME del usuario.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42g", tendrá la clave "david42g".
* Hacer script `borrar-usuariosXX.sh` en shell script para borrar un número MAX usuarios del sistema.
    * Los usuarios que se van a borrar tendrán los siguientes nombres: "nombre-alumno1", "nombre-alumno2", etc.
    * Recordar que al borrar el usuario se tendrá que eliminar su HOME.

## 2.2 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal Bash.
* Consultar contenido de /etc/passwd.
* Ejecutar el script de creación.
* Consultar contenido de /etc/passwd.
* Ejecutar el script de borrado.
* Consultar contenido de /etc/passwd.

# 3. Crear usuarios y borrar usuarios en Windows

## 3.1 Crear el script

* Ir a una MV con GNU/Linux.
* Primero empezamos creando un script Bash al que llamaremos `crear-ficherosXX.sh`. Sustituir XX por el número de cada alumno.
* Este script creará dos ficheros bat (`crearXX.bat` y `borrarXX`.bat).
    * El comando de Bash para escribir texto dentro de un archivo.bat es: `echo "Hola" >> NOMBRE-DEL-ARCHIVO.bat`.
* El fichero `crearXX.bat` contendrá las instrucciones necesarias para crear un número (variable MAX) de usuarios en Windows (Comando: "net user DAVID42 /add").
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1w", "nombre-alumno2w", etc.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42w", tendrá la clave "david42w".
* El fichero `borrarXX.bat` contendrá las instrucciones necesarias para eliminar un número (variable MAX) usuarios en Windows (Ejempo:"net user DAVID42 /del")
* Tendremos que hacer uso de la estructura iterativa.

## 3.2 Comprobar

* Ir a la MV Windows.
* Traarse a esta MV los ficheros `crearXX.bat`y `borrarXX.bat`.
* Abrir un terminal CMD.
* Consultar la lista de usuarios (net... Lo debes recordar de la Unidad 2).
* Ejecutar el script de creación.
* Consultar la lista de usuarios.
* Ejecutar el script de borrado.
* Consultar la lista de usuarios.
