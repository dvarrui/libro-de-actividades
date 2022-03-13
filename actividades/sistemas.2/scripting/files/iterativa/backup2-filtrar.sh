#!/bin/bash

FOLDER=/home/profesor
EXT=*.png
FILES=$(find $FOLDER -name $EXT) # Localizar los nombres de los archivos

# ESTRUCTURA ITERATIVA
for I in $(cat $FILES); do     # Bucle para todos los ficheros
  cp $I /backup/$(basename $I) # Copiar el archivo en backup
  echo -n '.'                  # Mostrar un punto por cada iteración
done
