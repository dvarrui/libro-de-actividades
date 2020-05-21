
```
Curso       : 201920
Area        : Sistemas operativos, comandos, scripting, backup
Descripción : EXPLICACIÓN: Localizar archivos en el sistema para backup
Requisitos  : Bash, Ruby
Tiempo      :
```

# 1. Ejemplos

**Ejemplo script secuencial**. Hacer explicación en clase usando el siguiente ejemplo:
* [ejemplo1.sh](files/ejemplo1.sh)

**Ejemplos: Find and copy**. Hacer explicación en clase usando los siguientes ejemplos:
* [backup1.sh](files/backup1.sh)
* [backup2.sh](files/backup2.sh)
* [backup3.sh](files/backup3.sh)
* [backup5.rb](files/backup5.rb)
* [backup-windows.rb](files/backup-windows.rb)

**Ejemplos: Create users**. Hacer explicación en clase usando los siguientes ejemplos:
* [create-users1.sh](files/create-users1.sh)
* [create-users2.sh](files/create-users2.sh)
* [create-users3.sh](files/create-users3.sh)
* [create-users5.rb](files/create-users5.rb)

# 2. Crear usuarios de forma masiva

Crear y borrar usuarios
* Hacer script `crear-usuariosXX.sh` en shell script para crear MAX usuarios en el sistema.
* Hacer script `borrar-usuariosXX.sh` en shell script para borrar MAX usuarios del sistema.

Crear ficheros BAT

Hacer un script en Bash al que llamaremos "crear-ficherosXX.sh". Este script creará dos ficheros bat (crearXX.bat y borrarXX.bat).

    El fichero crearXX.bat tendrá las instrucciones necesarias para crear MAX usuarios en Windows (Ejemplo: net user PEPE /add)
    El fichero borrarXX.bat tendrá las instrucciones necesarias para eliminar MAX usuarios en Windows (Ejempo: net user PEPE /del)

NOTA: El comando para escribir texto dentro de un archivo.bat es: echo "Hola" >> archivo.bat

Para comprobar los ficheros BAT, los ejecutaremos dentro de una consola Windows.
Última modificación: martes, 30 de abril de 2019, 11:37
