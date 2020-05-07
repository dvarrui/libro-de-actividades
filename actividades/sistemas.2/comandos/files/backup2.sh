#!/bin/bash

FOLDER=/home/profesor
EXT=*.png
TEMPFILE=lista.txt
FILES=$(find $FOLDER -name $EXT > $TEMPFILE)

for I in $(cat $TEMPFILE | grep -v 'thumb'); do # Bucle para todos los ficheros
  echo "cp $I /backup/$(basename $I)"           # Mostrar en pantalla
done
