#!/bin/bash
#script: update.sh

IPSERVER=127.0.0.1
SCRIPT=today.sh
URL="http://${IPSERVER}/sec/${SCRIPT}"
LOGFILE=update.log

#Logging inicio
echo "---------------------------------------" >> $LOGFILE
echo -n "Starting $0 : " >> $LOGFILE
date "+%Y-%m-%d %H:%M">> $LOGFILE
echo

#Doing today tasks
#echo "wget $URL"
chmod u+x $SCRIPT
./$SCRIPT >> $LOGFILE

#Logging fin
echo 
echo -n "End of $0   : " >> $LOGFILE
date "+%Y-%m-%d %H:%M">> $LOGFILE

