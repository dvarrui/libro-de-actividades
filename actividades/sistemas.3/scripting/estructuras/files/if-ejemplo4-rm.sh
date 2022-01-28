#!/bin/bash

echo "Ejecutando $0"

DIRNAME=backupXX
if [ -d $DIRNAME ]
then
  echo " => Borrar directorio $DIRNAME"
  rm -r -i $DIRNAME
end

cowsay "Fin del script"
exit 0
