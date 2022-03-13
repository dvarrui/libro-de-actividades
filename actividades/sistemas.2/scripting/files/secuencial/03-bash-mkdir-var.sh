#!/usr/bin/env bash

# Variables del script
FOLDER=backupXXb
NOMBREALUMNO="david"

# Empezamos con las acciones secuenciales
echo "[+] Creando el directorio $FOLDER"
mkdir $FOLDER
touch $FOLDER/README.txt

echo "[+] Creando el fichero $FOLDER/README.txt"
echo "Este es el fichero readme de $NOMBREALUMNO" >> $FOLDER/README.txt

echo "[+] Fin del script $0"
exit 0
