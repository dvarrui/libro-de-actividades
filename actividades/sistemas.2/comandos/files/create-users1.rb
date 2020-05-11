#!/bin/bash

# Leer el fichero con los nombres de los usuarios
NOMBRES=$(cat nombres.txt)

# Para cada nombre de usuario -> crear ese usuario
for I in $NOMBRES; do
  echo "=> Leyendo el nombre de $I" # Mostrar mensaje en pantalla
  useradd $I -m -p 123456           # Crear un nuevo usuario
done
