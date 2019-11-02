#!/bin/bash
#Script: iterar.sh
#Parámetro 1: Número de iteraciones
#Parámetro 2: Mensaje

MAX=$1
[ "$MAX" ] || MAX=10

MSG=$2
[ "$MSG" ] || MSG="No tengo nada que decir."

for i in `seq 1 $MAX`; do
	echo "${MSG}:" $i
	sleep 10
done

