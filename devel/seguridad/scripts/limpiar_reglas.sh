#!/bin/bash

## FLUSH de reglas
iptables -F
iptables -Z
iptables -F -t nat
iptables -Z -t nat

## Establecemos politica por defecto
iptables -P INPUT ACCEPT
iptables -P OUTPUT ACCEPT
iptables -P FORWARD ACCEPT

