
```
Curso       : 201920
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura secuencial
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripting: Estructura secuencial

# 1. INFO: Explicación del profesor usando ejemplos

[Ejemplos de scripts secuenciales](secuencial).
* Consultarlos. Leerlos y tratar de hacerse una idea de su funcionamiento.
* Anotar las dudas.
* Probar y ejecutar los scripts. Comprobar los resultados.
* ¿Se ha entendido? ¿Todas las dudas están resueltas?
    * Si: seguimos al siguiente apartado.
    * No: debemos resolver las dudas antes de seguir.

# 2. Crear usuarios y borrar usuarios en Windows

## 2.1 Crear el script

Vamos a crear 2 scripts que usando la estructura iterativa van a realizar la siguiente tareas:
* Ir a una MV con Windows
* Hacer script `01-crear-usuariosXX.bat` en shell script para crear un número de 10 usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1w", "nombre-alumno2w", etc.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42w", tendrá la clave "david42w".
* Hacer script `02-borrar-usuariosXX.bat` en shell script para borrar un número de 10 usuarios del sistema.
    * Los usuarios que se van a borrar tendrán los siguientes nombres: "nombre-alumno1w", "nombre-alumno2w", etc.

## 2.2 Comprobar

* Estamos en la MV Windows
* Abrir un terminal CMD.
* Consultar la lista de usuarios (net... Lo debes recordar de la Unidad 2).
* Ejecutar el script de creación.
* Consultar la lista de usuarios.
* Ejecutar el script de borrado.
* Consultar la lista de usuarios.

## 2.3 PowerShell

Crear script `03-crear-usuariosXX.ps`:
* Usar como ejemplo e script 01.
* Sustituir las instrucciones `net` por otras de PowerShell para crear cada usuario.

Crear script `04-crear-usuariosXX.ps`:
* Usar como ejemplo e script 02.
* Sustituir las instrucciones `net` por otras de PowerShell para eliminar cada usuario.

# 3 Scripts en Ruby

## 3.1 Crear usuarios

Crear script `05-crear-usuariosXX.rb`:
* Usar como ejemplo el script 01.
* Modificar las intrucciones de fichero BAT por instrucciones de Ruby.
    * puts: para imprimir mensajes en pantalla.
    * system: para ejecutar comandos propios del sistema operativo.
    * exit: para terminar el script.

# Reflexiones

* Si tuvieras que modificar el script para crear/borrar... 100 usuarios en lugar de 10 ¿qué harías?
* ¿Por qué acabamos los script con exit? ¿Para qué sirve?

```
> id root
uid=0(root) gid=0(root) grupos=0(root)

❯ echo $?
0

❯ id root > /dev/null                   

❯ id root > /dev/null && echo "[ OK ]"
[ OK ]
```

```
❯ id vader          
id: «vader»: no existe ese usuario

❯ echo $?
1

❯ id vader 2> /dev/null

❯ id vader 2> /dev/null || echo "[FAIL]"
[FAIL]
❯
```
