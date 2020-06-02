#!/bin/bash

TARGET=$((1+$RANDOM%10))
FAILS=0
EXIT=false

while [ $EXIT == false ]
do
  echo "Llevas $FAILS fallos"
  echo "Escribe un número entre 1 y 10"
  read NUM
  
  if [ $NUM -eq $TARGET ]; then
    echo "Enhorabuena! Lo has adivinado en $FAILS fallos"
    EXIT=true
  elif [ $NUM -gt $TARGET ]; then
    echo "Te has pasado"
    let FAILS=FAILS+1
  else
    echo "Te has quedado corto"
    let FAILS=FAILS+1
  fi  
done
