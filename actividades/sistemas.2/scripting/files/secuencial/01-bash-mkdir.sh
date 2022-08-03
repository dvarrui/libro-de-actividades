#!/usr/bin/env bash

echo "[+] Creando el directorio <backupXXb>"
mkdir backupXXb
touch backupXXb/README.txt

echo "[+] Creando el fichero README"
touch backupXXb/README.txt

echo "[+] Poniendo contenido al fichero README"
echo "Nombre del responsable: OBIWAN" >> backupXXb/README.txt
echo "Este es el contenido del fichero README.txt" >> backupXXb/README.txt

echo "[+] Fin del script $0"
exit 0
