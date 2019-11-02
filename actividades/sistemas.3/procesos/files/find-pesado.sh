#!/bin/bash
#Script: find_pesado.sh
#Parámetro 1: Número de iteraciones
#Parámetro 2: Mensaje

MAX=$1
[ "$MAX" ] || MAX=10

MSG=$2
[ "$MSG" ] || MSG="Estoy haciendo algo pesado..."

for i in `seq 1 $MAX`; do
	echo "${MSG} (${i} de ${MAX})"
	find / -name a 2> /dev/null
done

