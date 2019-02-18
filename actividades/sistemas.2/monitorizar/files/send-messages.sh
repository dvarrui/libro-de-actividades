#!/bin/bash
#Filename: send-messages.sh
#
#Description: Este script envía 100 mensajes/eventos para ser registrados
#  por rsyslog.
#
#NOTA: Para ejecutarlo hay que ponerle permisos de ejecución.
#   chmod +x send-messages.sh
#   ./send-messages.sh
#

echo "Iniciando proceso"
for i in $(seq 1 100); do
	echo -n "."
	logger -p local0.info -t "Script" "Mensaje de prueba ${i} de 100"
done
echo "*"
echo "Fin del script"

