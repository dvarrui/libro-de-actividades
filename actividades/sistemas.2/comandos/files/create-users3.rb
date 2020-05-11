#!/bin/bash
# Script para crear usuarios en Windows de forma masiva (version 1)

# PASO1a: Leer el fichero con los nombres de los usuarios
NOMBRES=$(cat nombres.txt)

# PASO1b: Crear un fichero vacío
SALIDA=salida.bat
echo '' > $SALIDA

# PASO2: Para cada nombre de usuario hacer lo siguiente:
for I in $NOMBRES; do
  echo "=> Leyendo el nombre de $I"         # Mostrar mensaje en pantalla
  echo "net user $I 123456 /add" >> $SALIDA # Añadir línea al fichero de salida
done

# PASO3: Mensaje final
echo "================================"
echo "Se ha creado el fichero $SALIDA!"
echo "1. Ahora habría que llevar el archivo BAT al equipo Windows"
echo "2. y ejecutarlo como usuario Administrador."
