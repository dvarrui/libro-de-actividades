#!/bin/bash
#
# Author : David Vargas
# Data   : 20-11-2015
# Description:
#
# This script execute remotely commands on several hosts at a time.
#
# It's necesary generate on your local PC the SSH key pairs (ssh-keygen), and
# copy the public key on every remote hosts (ssh-copy-id). So you can execute
# the script without write the password to access the remote host.
#
# Sorry about my english! I'm Spanish speaker.
#

ROOT_PASSWORD="change-this"

function change_departamento() {
	# Apply this actions to PC's into departamento
	for i in `seq 1 5`;
	do
    		IP="172.31.$i.0"
    		echo "* host[$IP]"
    		ssh root@$IP 'hostname -f' #show remote hostname
    		ssh root@$IP "echo \"root:$ROOT_PASSWORD\" | chpasswd" # set root password
	done  
}

function change_aula109() {
	# Apply this actions to PC's into aula109
	for i in `seq 11 18`; #OK 1,10
	do
    		IP="172.19.$i.0"
    		echo "* host[$IP]"
    		#ssh root@$IP 'hostname -f' #show remote hostname
    		#ssh root@$IP "echo \"root:$ROOT_PASSWORD\" | chpasswd" # set root password
    		ssh root@$IP 'mount /dev/sda2 /mnt/entorno2; rm -r /mnt/entorno2/VHDs/*' #remove VHDs files
	done  
}

change_aula109

