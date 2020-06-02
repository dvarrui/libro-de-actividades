#!/bin/bash

VALORES=`cat listado.txt`

for I in $VALORES
do
  echo "Iteración para I => $I"
done
