#!/bin/bash

FOLDER=backupXX
NOMBREALUMNO="david"

echo "Creando el directorio <backupXX>"
mkdir $FOLDER
touch $FOLDER/README.txt
echo "Este es el fichero readme de $NOMBREALUMNO" >> $FOLDER/README.txt
echo "Fin del script"
