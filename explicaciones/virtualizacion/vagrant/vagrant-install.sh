#!/bin/bash
VAGRANT_VERSION=2.2.14

# checks if run as root
if [ "$USER" != "root" ];
then
        echo "Must run as root"
	exit 1
fi

# checks if unzip is installed
which unzip
if [ $? != 0 ];
then
	echo "Must install unzip"
	exit 1
fi

# download last vagrant version
wget -O /tmp/vagrant.zip https://releases.hashicorp.com/vagrant/${VAGRANT_VERSION}/vagrant_${VAGRANT_VERSION}_linux_amd64.zip

# unzip vagrant in temporary dir
unzip /tmp/vagrant.zip -d /opt/vagrant

# creates symlink pointing to vagrant binary
ln -s /opt/vagrant/vagrant /usr/bin/vagrant
