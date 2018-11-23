
`Autor: Juan Carlos Pérez`

---

# Enunciado para iptables

Supongamos que tenemos un aula con una red local 172.18.0.0/16, y
que también vamos a tener en cuenta la red de las máquinas virtuales
(host-only de virtualbox que pone dhcp en 192.168.56.0/24).

Entonces vamos a abrir los puertos para salida externa hacia:

| Servicio | Puerto |
| -------- | ------ |
| mysql    | 3306   |
| oracle   | 1521   |
| netbeans | 8080   |
| Web      | 80     |
| SSH      | 22     |

---

Ejemplo:

```
#!/bin/bash
iptables -P OUTPUT DROP
iptables -A INPUT -i lo -j ACCEPT
iptables -A OUTPUT -o lo -j ACCEPT
iptables -A OUTPUT -p tcp --dport 443 -j DROP # Esto ya lo coge la regla 1?

iptables -A OUTPUT -p tcp -d 172.18.0.0/14  --dport 22 -j ACCEPT

iptables -A OUTPUT -p tcp -d 172.18.0.0/14  --dport 3306 -j ACCEPT
iptables -A OUTPUT -p udp -d 172.18.0.0/14  --dport 3306 -j ACCEPT
iptables -A OUTPUT -p tcp -d 192.168.56.0/14 --dport 3306 -j ACCEPT
iptables -A OUTPUT -p udp -d 192.168.56.0/14  --dport 3306 -j ACCEPT

iptables -A OUTPUT -p tcp -d 192.168.56.0/14 --dport 1521 -j ACCEPT
iptables -A OUTPUT -p udp -d 192.168.56.0/14  --dport 1521 -j ACCEPT

iptables -A OUTPUT -p tcp -d 172.18.0.0/14  --dport 80 -j ACCEPT
iptables -A OUTPUT -p tcp -d 172.18.0.0/14  --dport 8080 -j ACCEPT
iptables -A OUTPUT -p tcp -d 192.168.0.0/14  --dport 8080 -j ACCEPT

iptables -A OUTPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
iptables -A OUTPUT -p tcp  --dport 80 -j DROP #regla 1?

iptables -nL
```
