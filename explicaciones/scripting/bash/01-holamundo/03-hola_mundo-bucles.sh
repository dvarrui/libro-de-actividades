#!/bin/bash

# Bucle sobre el resultado del comando "seq 1 3"
for i in `seq 1 3`; do
	echo "($i) Hola Mundo!"
done
  
# Bucle sobre: A, B y C
for i in "A" "B" "C"; do
	echo "($i) Hola Mundo!"
done
