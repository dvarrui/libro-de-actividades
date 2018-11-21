#!/bin/bash
# Autor: David Vargas <dvargas@canarias.org>
#

NUM_PASOS=50
NUM_RETARDO=1000

function codigo_del_hilo
{
	id=$1
	for i in $(seq 1 50); do
		echo "Hijo[${id}]: i=${i}"
		for j in $(seq 1 1000); do
			#no hace nada
			s=s+1
		done
	done
	echo "Hijo[${id}] terminado."
}

if [ $1 ]; then
	codigo_del_hilo $1
else
	./$0 1 &
	./$0 2 &
	./$0 3 &
	./$0 4 &
	echo "Fin del script " $0
fi

