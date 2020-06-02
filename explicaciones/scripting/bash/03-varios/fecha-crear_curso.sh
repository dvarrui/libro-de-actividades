#Ejecucion de comando borrar.sh
./borrar.sh


FECHA1=`date +%y`
FECHA2=`expr $FECHA1 + 1`
CURSO=curso$FECHA1$FECHA2
NOMBRE="Miqueas "
APELLIDOS="García González "

# Crear directorios
mkdir $CURSO
mkdir $CURSO/idp
mkdir $CURSO/fuw
mkdir $CURSO/lnd

# Crear permisos
chmod 700 $CURSO -R

# Crear ficheros de texto
echo "Sistemas Operativos" > $CURSO/idp/readme.txt
echo $NOMBRE$APELLIDOS>> $CURSO/idp/readme.txt
echo "address = 172.19.42.11" >> $CURSO/idp/nagios.cfg
echo "Fundamentos de Hardware" > $CURSO/fuw/readme.txt
echo $NOMBRE$APELLIDOS>> $CURSO/fuw/readme.txt

