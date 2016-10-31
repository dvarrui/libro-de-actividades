#!/bin/bash

## FLUSH de reglas
iptables -F
iptables -Z
iptables -F -t nat
iptables -Z -t nat

## Establecemos politica por defecto
iptables -P INPUT DROP
iptables -P OUTPUT ACCEPT
iptables -P FORWARD DROP

# Acepto paquetes entrantes de conexiones ya establecidas
iptables -A INPUT -p tcp -m state --state ESTABLISHED -j ACCEPT

