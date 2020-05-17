
# Ejemplo

echo "Esto es un ejemplo"

FECHA1=$(date +%y)
FECHA2=`expr $FECHA1 + 1`

echo "FECHA1=" $FECHA1
echo "FECHA2=" $FECHA2
