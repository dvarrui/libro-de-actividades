#!/bin/bash

mkdir backup                             # Crear directorio para las copias
FILES=$(find /home/profesor -name *.png) # Localizar nombres de los ficheros

for I in $FILES; do           # Bucle para todos los ficheros
  cp $I backup/$(basename $I) # Copiar un archivo
  echo -n "."                 # Mostrar un . por pantalla
done
