#!/bin/bash
#
# Filename: /usr/local/bin/kupd
# Version: 1

ALUMNO=nombre-del-alumno
MAQUINA=`hostname`
DIRCONF=/etc/kup
LOGFILE=$DIRCONF/kup.log

touch $DIRCONF/running
echo "Iniciando el log..." > $LOGFILE

while [ -f $DIRCONF/running ]; do
  MESSAGE="[$ALUMNO @ $MAQUINA] $0: `date`"
  echo $MESSAGE >> $LOGFILE
  sleep 1
done
