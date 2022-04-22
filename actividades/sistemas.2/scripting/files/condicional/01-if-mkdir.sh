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

exit 0
