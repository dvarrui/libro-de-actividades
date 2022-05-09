#!/usr/bin/env bash

if [ "$(id -u)" -ne 0 ];
then
  echo " ==> Debes ser usuario root para ejecutar el script"
  exit 1
fi

if [ "$1" == "-c" ];
then
  useradd -p alejandro alejandro1w
  useradd -p alejandro alejandro2w
  useradd -p alejandro alejandro3w
  useradd -p alejandro alejandro4w
  useradd -p alejandro alejandro5w
  useradd -p alejandro alejandro6w
  useradd -p alejandro alejandro7w
  useradd -p alejandro alejandro8w
  useradd -p alejandro alejandro9w
  useradd -p alejandro alejandro10w
elif [ "$1" == "-d" ];
then
  userdel alejandro1w
  userdel alejandro2w
  userdel alejandro3w
  userdel alejandro4w
  userdel alejandro5w
  userdel alejandro6w
  userdel alejandro7w
  userdel alejandro8w
  userdel alejandro9w
  userdel alejandro10w
else
  echo " ==> Introduzca un parámetro válido: -c para crear usuarios o -d para eliminarlos"
fi

exit 0

