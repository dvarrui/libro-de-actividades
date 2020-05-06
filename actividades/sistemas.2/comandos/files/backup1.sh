#!/bin/bash

mkdir backup

for I in $(find /home/profesor -name *.png); do
  cp $I backup/$(basename $I)
done
