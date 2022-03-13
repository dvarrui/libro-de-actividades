#!/bin/bash

# PASO1: Leer el fichero con los nombres de los usuarios
# El nombre del fichero se pasa como el primer argumento del script
NOMBRES=$(cat $1)

# PASO2: Para cada nombre de usuario hacer lo siguiente:
for I in $NOMBRES; do
  echo "=> Leyendo el nombre de $I" # Mostrar mensaje en pantalla
  useradd $I -m -p 123456           # Crear un nuevo usuario
done
