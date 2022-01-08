
```
Curso       : 201920
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura iterativa
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripting: Estructura iterativa

# 1. INFO: Explicación del profesor usando ejemplos

**Ejemplos: Find and copy**. Hacer explicación en clase usando los siguientes ejemplos:
* [backup1-iterar.sh](files/backup1-iterar.sh)
* [backup2-filtrar.sh](files/backup2-filtrar.sh)
* [backup3-argumentos.sh](files/backup3-argumentos.sh)
* [backup4-ruby.rb](files/backup4-ruby.rb)
* [backup5-windows.rb](files/backup5-windows.rb)

# 2. Crear usuarios y borrar usuarios en GNU/Linux

## 2.1 Crear scripts en Bash

Vamos a crear 2 scripts que usando la estructura iterativa van a realizar la siguiente tareas:
* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXX.sh` en shell script para crear un número MAX usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1g", "nombre-alumno2g", etc.
    * La variable MAX contiene el número de usuarios a crear.
    * Recordar que también queremos que se cree el HOME del usuario.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42g", tendrá la clave "david42g".
* Hacer script `borrar-usuariosXX.sh` en shell script para borrar un número MAX usuarios del sistema.
    * Los usuarios que se van a borrar tendrán los siguientes nombres: "nombre-alumno1", "nombre-alumno2", etc.
    * La variable MAX contiene el número de usuarios a crear.
    * Recordar que al borrar el usuario se tendrá que eliminar su HOME.

## 2.2 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal Bash.
* Consultar contenido de /etc/passwd.
* Ejecutar el script de creación.
* Consultar contenido de /etc/passwd.
* Ejecutar el script de borrado.
* Consultar contenido de /etc/passwd.

# 3. Crear scripts en Ruby

## 3.1 Crear el script

Vamos a crear el mismo script del apartado anterior pero en Ruby.
* Recordar que el script comienza con `#!/usr/bin/env ruby`
* Para ejecutar un comando del sistema hacemos `system("COMANDO-DEL-SISTEMA")`.
* Los iteradores o bucle de Ruby son igual que en Bash. Por ejemplo:

```
for i in VALORES do
  HACER ALGO CON CADA i
end
```

## 3.2 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal Bash.
* Consultar contenido de /etc/passwd.
* Ejecutar el script de creación.
* Consultar contenido de /etc/passwd.
* Ejecutar el script de borrado.
* Consultar contenido de /etc/passwd.
