#!/bin/bash

mkdir backup                      # Crear directorio para las copias
FOLDER=/home/profesor
FILES=$(find $FOLDER -name *.png) # Localizar nombres de los ficheros

# ESTRUCTURA ITERATIVA
for I in $FILES; do           # Bucle para todos los ficheros
  cp $I backup/$(basename $I) # Copiar un archivo
  echo -n "."                 # Mostrar un . por pantalla
done

echo "Fin del script!"
