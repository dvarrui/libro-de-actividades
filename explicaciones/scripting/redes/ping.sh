#!/bin/bash
#
# Autor: David Vargas <dvargas@canarias.org>
# Descripción: Realiza una barrido por un conjunto de IP's para
#              comprobar si existe conectividad con dicha máquina.
VERSION=1
FICHERO='data.ping' 

#Cargando librería
. /lib/lsb/init-functions

#Definición de las funciones
mostrar_ayuda() {
  echo
  echo 'Autor: David Vargas <dvargas@canarias.org>'
  echo
  echo ' -h            , mostrar esta ayuda'
  echo ' -v            , ver la versión del script'
  echo ' sin parámetros, se ejecuta el barrido de comprobación de IPs'
  echo
  echo 'Este programa lee el fichero <ping.data>'
  echo 'el cual contiene una lista de IPv4 para comprobar'
  echo 'Las líneas que empiezan con # se entenderán como'
  echo 'comentarios.'
  echo
}

realizar_barrido(){
  for HOST in `cat $FICHERO | grep -v '#'`; do
    if `ping -c1 $HOST > /dev/null`; then
      #Hay conexión con la IP
      log_begin_msg $HOST "ok"
      log_end_msg 0
    else
      #No hay conexión con la IP
      log_begin_msg $HOST "error"
      log_end_msg 1	
    fi
  done
}

#
#Ejecución del script
#
case $1 in
-h)
        mostrar_ayuda;;
-v)
        echo "ping.sh versión $VERSION";;
*)
        realizar_barrido
        exit 0;;
esac
exit 1

