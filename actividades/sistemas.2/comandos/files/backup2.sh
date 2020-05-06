#!/bin/bash

FOLDER=/home/profesor
EXT=*.png
TEMPFILE=lista.txt

find $FOLDER -name $EXT > $TEMPFILE
for I in $(cat $TEMPFILE | grep -v 'thumb'); do
  echo "cp $I /backup/$(basename $I)"
done
