#Variable
EXIT=false
LOGFILE=servicio1.log
STOPFILE=servicio1.parar
 
#Borrado fichero
rm $LOGFILE
rm $STOPFILE

while [ $EXIT == false ]; do 
  date >> $LOGFILE
  sleep 5
  if [ -f $STOPFILE ]; then 
    EXIT=true
  fi
done

echo "Servicio parado" >> $LOGFILE
