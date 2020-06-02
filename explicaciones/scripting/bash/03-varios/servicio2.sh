#Variable
EXIT=false
LOGFILE=servicio2.log
STOPFILE=servicio2.parar
 
#Borrado fichero
rm $LOGFILE
rm $STOPFILE

while [ $EXIT == false ]; do 
  rm prueba/*.txt
  sleep 5
  if [ -f $STOPFILE ]; then 
    EXIT=true
  fi
done

echo "Servicio parado" >> $LOGFILE
