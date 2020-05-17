FECHA1=`date +%y`
FECHA2=`expr $FECHA1 + 1`
CURSO=curso$FECHA1$FECHA2

rm -r $CURSO

