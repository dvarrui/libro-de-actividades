#!/bin/bash
#!/usr/bin/env bash

echo "Ejecutando $0:"

DIRNAME=backupXX
if [ -d "$DIRNAME" ]
then
  echo " => ."
else
  echo " => Crear directorio ${DIRNAME}"
  mkdir $DIRNAME
fi

FILEPATH="$DIRNAME/readme.txt"
if [ -f "$FILEPATH" ]
then
  echo " => ."
else
  echo " => Crear fichero $FILEPATH"
  touch $FILEPATH
  echo "Este es el fichero readme de $NOMBREALUMNO" >> $FILEPATH
fi

exit 0
