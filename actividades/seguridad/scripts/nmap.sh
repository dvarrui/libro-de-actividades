#!/bin/bash

#HOST=www.iespuertodelacruz.es
HOST=informatica.iespuertodelacruz.es

echo "=================="
echo "nmap $HOST"
nmap $HOST

echo "=================="
echo "nmap -A -T4 $HOST"
nmap -A -T4 $HOST

echo "=================="
echo "nmap --version-all -O $HOST"
nmap --version-all -O $HOST
