#!/bin/bash

# Leer el fichero con los nombres de los usuarios
NOMBRES=$(cat nombres.txt)

# Crear un fichero vacío
SALIDA=salida.bat
echo '' > $SALIDA

# Para cada nombre de usuario hacer lo siguiente:
for I in $NOMBRES; do
  echo "=> Leyendo el nombre de $I"   # Mostrar mensaje en pantalla
  echo "net user /add $I" >> $SALIDA  # Añadir línea al fichero de salida
done

echo "================================"
echo "Se ha creado el fichero $SALIDA!"
echo "1. Ahora habría que llevar el archivo BAT al equipo Windows"
echo "2. y ejecutarlo como usuario Administrador."
