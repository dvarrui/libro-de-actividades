
```
EN CONSTRUCCIÓN
Usar scripting para configurar esta práctica
```

# How to build a network of Linux routers using quagga

Enlace de interés:
* http://www.brianlinkletter.com/how-to-build-a-network-of-linux-routers-using-quagga/



This post lists the commands required on each node to build a network
of three Ubuntu Linux routers. Each router is connected to the other two routers
and is running quagga. Each router is also connected to a PC running Ubuntu Linux.

I use this network configuration to evaluate network emulators and open-source
networking software in a simple scenario. Readers may find these commands
useful in building their own configuration scripts.

I provide “copy and paste” commands so the network can be configured quickly.

Creating a basic topology

The physical — or virtual — network installation and the management network setup
is outside the scope of this post. The method used to build the lab topology
depends on the equipment, and/or the network emulator and hypervisor technology you are using.

I assume you already have six machines running and connected in a network
as shown above, and I assume you have a management network set up so that each
machine can communicate with the host computer and with the Internet. I assume
the management interface is the first Ethernet interface on each node, enp0s3.

# Router configuration

Each router needs to install the quagga router package, configure quagga,
 and then configure the network using the quagga VTY shell. Optionally,
 quagga daemon configuration files may be created.
Router-1

Skip to Copy-and-paste shell commands below if you want to quickly configure
the node Router-1. This section shows the commands to configure Router-1 step by step.

Install the quagga package and then configure the Quagga VTY shell. This will create the basic setup for a router.

Enter the commands:
```
sudo su
apt-get update
apt-get install quagga quagga-doc
```

Then, configure the Quagga daemons by editing the file /etc/quagga/daemons and start the zebra and ospfd daemons. `nano /etc/quagga/daemons`

Modify the file so it looks like:

```
zebra=yes
bgpd=no
ospfd=yes
ospf6d=no
ripd=no
ripngd=no
isisd=no
babeld=no
```
Save the file and quit the editor.

Create config files for the zebra and ospfd daemons.

```
cp /usr/share/doc/quagga/examples/zebra.conf.sample /etc/quagga/zebra.conf
cp /usr/share/doc/quagga/examples/ospfd.conf.sample /etc/quagga/ospfd.conf
chown quagga.quaggavty /etc/quagga/*.conf
chmod 640 /etc/quagga/*.conf
```

Start Quagga: `/etc/init.d/quagga start`

Set up environment variables so we avoid the vtysh END problem. Edit the /etc/bash.bashrc file:

# nano /etc/bash.bashrc

Add the following line at the end of the file:

export VTYSH_PAGER=more

Save the file and quit the editor. Then, edit the /etc/environment file:

# nano /etc/environment

Then add the following line to the end of the file:

VTYSH_PAGER=more

Save the file and quit the editor.

Start the Quagga shell with the command vtysh on Router-1:

# vtysh

Enter the following Quagga commands:

```
configure terminal
router ospf
 network 192.168.1.0/24 area 0
 network 192.168.100.0/24 area 0
 network 192.168.101.0/24 area 0
 passive-interface enp0s8
 exit
interface enp0s8
 ip address 192.168.1.254/24
 exit
interface enp0s9
 ip address 192.168.100.1/24
 exit
interface enp0s10
 ip address 192.168.101.2/24
 exit
exit
ip forward
write
exit
```

Router-1 copy-and-paste shell commands

If you wish to copy-and-paste commands to quickly configure Router-1, then skip the previous section and enter the following commands. We use the here documents feature of the Bash shell to redirect the pasted block of input to the bash shell command line:

```
bash <<EOF2
apt-get update
apt-get install quagga quagga-doc traceroute
cp /usr/share/doc/quagga/examples/zebra.conf.sample /etc/quagga/zebra.conf
cp /usr/share/doc/quagga/examples/ospfd.conf.sample /etc/quagga/ospfd.conf
chown quagga.quaggavty /etc/quagga/*.conf
chmod 640 /etc/quagga/*.conf
sed -i s'/zebra=no/zebra=yes/' /etc/quagga/daemons
sed -i s'/ospfd=no/ospfd=yes/' /etc/quagga/daemons
echo 'VTYSH_PAGER=more' >>/etc/environment
echo 'export VTYSH_PAGER=more' >>/etc/bash.bashrc
cat >> /etc/quagga/ospfd.conf << EOF
interface enp0s8
interface enp0s9
interface enp0s10
interface lo
router ospf
 passive-interface enp0s8
 network 192.168.1.0/24 area 0.0.0.0
 network 192.168.100.0/24 area 0.0.0.0
 network 192.168.101.0/24 area 0.0.0.0
line vty
EOF
cat >> /etc/quagga/zebra.conf << EOF
interface enp0s8
 ip address 192.168.1.254/24
 ipv6 nd suppress-ra
interface enp0s9
 ip address 192.168.100.1/24
 ipv6 nd suppress-ra
interface enp0s10
 ip address 192.168.101.2/24
 ipv6 nd suppress-ra
interface lo
ip forwarding
line vty
EOF
/etc/init.d/quagga start
exit
EOF2
```

I will configure the remaining routers with the quick shell commands so you can copy and paste the configuration for each router.

# Router-2

On Router-2, install quagga and configure OSPF on the router’s interfaces. Copy-and-paste the following commands into the Router-2 terminal window:

```
bash <<EOF2
apt-get update
apt-get install quagga quagga-doc traceroute
cp /usr/share/doc/quagga/examples/zebra.conf.sample /etc/quagga/zebra.conf
cp /usr/share/doc/quagga/examples/ospfd.conf.sample /etc/quagga/ospfd.conf
chown quagga.quaggavty /etc/quagga/*.conf
chmod 640 /etc/quagga/*.conf
sed -i s'/zebra=no/zebra=yes/' /etc/quagga/daemons
sed -i s'/ospfd=no/ospfd=yes/' /etc/quagga/daemons
echo 'VTYSH_PAGER=more' >>/etc/environment
echo 'export VTYSH_PAGER=more' >>/etc/bash.bashrc
cat >> /etc/quagga/ospfd.conf << EOF
interface enp0s8
interface enp0s9
interface enp0s10
interface lo
router ospf
 passive-interface enp0s8
 network 192.168.2.0/24 area 0.0.0.0
 network 192.168.100.0/24 area 0.0.0.0
 network 192.168.102.0/24 area 0.0.0.0
line vty
EOF
cat > /etc/quagga/zebra.conf << EOF
interface enp0s8
 ip address 192.168.2.254/24
 ipv6 nd suppress-ra
interface enp0s9
 ip address 192.168.100.2/24
 ipv6 nd suppress-ra
interface enp0s10
 ip address 192.168.102.2/24
 ipv6 nd suppress-ra
interface lo
ip forwarding
line vty
EOF
/etc/init.d/quagga start
exit
EOF2
```

# Router-3

On Router-3 install quagga and configure OSPF on the router’s interfaces. Copy-and-paste the following commands into the Router-3 terminal window:

```
bash <<EOF2
apt-get update
apt-get install quagga quagga-doc traceroute
cp /usr/share/doc/quagga/examples/zebra.conf.sample /etc/quagga/zebra.conf
cp /usr/share/doc/quagga/examples/ospfd.conf.sample /etc/quagga/ospfd.conf
chown quagga.quaggavty /etc/quagga/*.conf
chmod 640 /etc/quagga/*.conf
sed -i s'/zebra=no/zebra=yes/' /etc/quagga/daemons
sed -i s'/ospfd=no/ospfd=yes/' /etc/quagga/daemons
echo 'VTYSH_PAGER=more' >>/etc/environment
echo 'export VTYSH_PAGER=more' >>/etc/bash.bashrc
cat >> /etc/quagga/ospfd.conf << EOF
interface enp0s8
interface enp0s9
interface enp0s10
interface lo
router ospf
 passive-interface enp0s8
 network 192.168.3.0/24 area 0.0.0.0
 network 192.168.101.0/24 area 0.0.0.0
 network 192.168.102.0/24 area 0.0.0.0
line vty
EOF
cat > /etc/quagga/zebra.conf << EOF
interface enp0s8
 ip address 192.168.3.254/24
 ipv6 nd suppress-ra
interface enp0s9
 ip address 192.168.101.1/24
 ipv6 nd suppress-ra
interface enp0s10
 ip address 192.168.102.1/24
 ipv6 nd suppress-ra
interface lo
ip forwarding
line vty
EOF
/etc/init.d/quagga start
exit
EOF2
```

---

# PC configuration

Each PC in the network needs to configured with an IP address and a default route.

## PC-1

Skip to copy-and-paste shell commands below if you want to quickly configure the node PC-1. This sections shows the commands step by step, for clarity.

In the PC-1 xterm window, use a text editor to add the following lines to the /etc/network/interfaces file:

$ sudo su
# nano /etc/network/interfaces

Add the following lines to the file:

auto enp0s8
iface enp0s8 inet static
   address 192.168.1.1
   netmask 255.255.255.0

Then, add a static route the sends all traffic in the 102.168.0.0/16 network out enp0s3. Enter the following in teh same /etc/network/interfaces file:

up route add -net 192.168.0.0/16 gw 192.168.1.254 dev enp0s8

Restart the networking service to make the configuration change operational:

# /etc/init.d/networking restart

PC-1 copy-and-paste shell commands

If you wish to copy-and-paste commands to quickly configure PC-1, then enter the following commands:

```
bash <<EOF2
cat >> /etc/network/interfaces << EOF
auto enp0s8
iface enp0s8 inet static
   address 192.168.1.1
   netmask 255.255.255.0
up route add -net 192.168.0.0/16 gw 192.168.1.254 dev enp0s8
EOF
/etc/init.d/networking restart
exit
EOF2
```

The remaining PC nodes are configured in the same way, with different IP addresses. I list shell commands that may be copied and pasted to quickly configure the nodes:

PC-2

On PC-2, add the interface configuration to the network interfaces file and set up a static route:

```
bash <<EOF2
cat >> /etc/network/interfaces << EOF
auto enp0s8
iface enp0s8 inet static
   address 192.168.2.1
   netmask 255.255.255.0
up route add -net 192.168.0.0/16 gw 192.168.2.254 dev enp0s8
EOF
/etc/init.d/networking restart
exit
EOF2
```

PC-3

On PC-3, add the interface configuration to the network interfaces file and set up a static route:

```
bash <<EOF2
cat >> /etc/network/interfaces << EOF
auto enp0s8
iface enp0s8 inet static
   address 192.168.3.1
   netmask 255.255.255.0
up route add -net 192.168.0.0/16 gw 192.168.3.254 dev enp0s8
EOF
/etc/init.d/networking restart
exit
EOF2
```

# Conclusion

We listed commands that you can copy and paste to set up a simple network consisting of Ubuntu Linux computers. We can use this network to test open-source network emulators and open source networking software.
