#!/bin/bash


function install_software() {
	apt-get update
	apt-get upgrade -y

	apt-get install vim
	#apt-get install gcc g++
	apt-get install openjdk-6-jdk
	apt-get install openjdk-6-jre
	apt-get install openjdk-6-doc
	#apt-get install slt-manual
	apt-get install eclipse
}

#Main body
if [ -z $1 ]; then
	OPTION="default"
else
	OPTION=$1
fi
echo "Option selected: $OPTION"

case $OPTION in
	"-i")
		install_software;;
esac

