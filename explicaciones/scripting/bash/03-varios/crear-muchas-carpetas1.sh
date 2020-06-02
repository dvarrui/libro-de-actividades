#Variable
CONTADOR=1
MAX=10000
 
#Preguntar clave usuario
while [ $CONTADOR -lt $MAX ]
do 
  echo "Creando directorio $CONTADOR"
  mkdir "prueba/dir$CONTADOR"
  let CONTADOR=CONTADOR+1
done
