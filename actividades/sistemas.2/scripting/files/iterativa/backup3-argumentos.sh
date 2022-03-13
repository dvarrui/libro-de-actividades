#!/bin/bash

echo "Buscando ficheros dentro de $1"

mkdir backup                  # Crear directorio para las copias
FILES=$(find $1 -name *.png)  # Localizar nombres de los ficheros

for I in $FILES; do           # Bucle para todos los ficheros
  cp $I backup/$(basename $I) # Copiar un archivo
  echo -n "."                 # Mostrar un . por pantalla
done

echo "Fin del script!"
