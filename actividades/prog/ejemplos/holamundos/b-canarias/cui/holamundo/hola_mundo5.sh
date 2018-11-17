#!/bin/bash

function saludar {
  for i in `seq 1 $1`; do
    echo 'Hola Mundo 5!'
  done
}

saludar 5
