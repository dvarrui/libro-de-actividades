#!/bin/bash
#script: bucle.sh
#param1: delay seconds
#param2: message

DELAY=$1
MSG=$2
[ "$DELAY" ] || DELAY=5
[ "$MSG" ] || MSG="Hola Mundo!"

echo -n "Parámetros {${DELAY}, \"${MSG}\"} (Pulsa una tecla para empezar)"
read P

while [ true ] ; do
	echo $MSG
	sleep $DELAY
done


