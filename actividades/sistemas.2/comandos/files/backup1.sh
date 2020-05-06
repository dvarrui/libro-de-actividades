#!/bin/bash

FOLDER=/home/profesor
EXT=*.png

for I in $(find $FOLDER -name $EXT); do
  echo "cp $I /backup/$(basename $I)"
done
