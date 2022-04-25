#!/usr/bin/env bash

echo "Escribe tu edad:"
read edad

if [ $edad -ge "17" ]
then
  echo "Eres mayor de edad"
else
  echo "Eres menor de edad"
fi

exit 0
