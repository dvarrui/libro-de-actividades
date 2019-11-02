#!/bin/bash

NOMBRE=$1
[ "$NOMBRE" ] || NOMBRE="Mundo"

echo "Hola ${NOMBRE}!"
