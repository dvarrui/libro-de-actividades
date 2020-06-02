#Variable
PASS_VALUE=SEED
CONTADOR=3

 
#Preguntar clave usuario
while [ $CONTADOR -gt 0 ]
do 
  echo "Contraseña"
  read -s PASS
  let CONTADOR=$CONTADOR-1

  if [ $PASS == $PASS_VALUE ]
  then
    echo "Acceso correcto"
    CONTADOR=3
  else
    echo "Contraseña incorrecta (Le quedan $CONTADOR intentos)"
  fi

done
