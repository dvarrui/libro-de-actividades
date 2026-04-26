
```
Estado : En construcción. Sólo está la idea
Curso  : 201920
```

---

# Redes TCP/IP: enfoque práctico

Vamos a hacer una práctica para conocer las redes TCP/IP y el modelo OSI.

Enlaces de interés:
* [Comunicaciones mediante arquitecturas de red - Javi López](https://www.youtube.com/watch?v=L0uXAHdyPBk&feature=youtu.be)

# 1. Preparativos

Crear 3 MV de la siguiente forma.

## 1.1 Máquina MV1

| Parámetro         | Valor       |
| ----------------- | ----------- |
| Sistema operativo | GNU/Linux   |
| hostname          | masterXXg   |
| Red-1 VirtualBox  | red interna |
| Red-1 IP          | 192.168.X.1 |
| Red-2 VirtualBox  | Modo puente |
| Red-2 IP          | (dhcp)      |

## 1.2 Máquina MV12

| Parámetro         | Valor       |
| ----------------- | ----------- |
| Sistema operativo | GNU/Linux   |
| hostname          | slaveXXg    |
| Red-1 VirtualBox  | red interna |
| Red-1 IP          | 192.168.X.2 |

## 1.3 Máquina MV3

| Parámetro         | Valor       |
| ----------------- | ----------- |
| Sistema operativo | Windows     |
| hostname          | slaveXXw    |
| Red-1 VirtualBox  | red interna |
| Red-1 IP          | 192.168.X.3 |

> Donde pone XX, sustituir por el número asociado al alumno.

---
# 2. ARP

Nada más iniciar el equipo, la tabla ARP debe estar vacía. A medida que la máquina se comunica con otras, la tabla ARP va incluyendo información de los equipos vecinos. Esto es: IP interfaz-de-red y MAC de nuestros vecinos.

Ir a MV1.
* Instalar Wireshark
* `ip neight`, consultar tabla ARP actual. Debe estar vacía.
* Abrir consola como `root`, y ejecutar `wireshark`.
* Ir a `Wireshark -> Start capturing packages`.
* Hacer `ping -c 4 192.168.X.2`.
* Ir a `Wireshark -> Stop capturing packages`.
* `ip neight`, consultar tabla ARP actual. Comprobamos que hay nueva información. *Sigue leyendo para averiguar cómo han llegado estos datos*.
* Ir filtro. Poner `arp` y aplicar.

**Preguntas**
* ¿Cuántos paquetes ARP envía el origen? ¿A qué MAC se envía?
* ¿Cuántos paquetes ARP recibe el origen? ¿Qué información recibe?

> Con el comando `arp-scan 192.168.X.0/24`, esto haría un barrido por toda nuestra red LAN, y obtendríamos la IP-MAC de todos nuestros equipos vecinos.
>

---
# 3. ICMP (Ping)

* Ir filtro. Poner `icmp` y aplicar.

**Preguntas**
* ¿Cuántos paquetes envía el comando PING?
* ¿Qué procolo se está usando TCP/IP v4 o TCP/IP ¿Dónde se ve?
* ¿Aparecen las IP's de MV1 y MV2? ¿Dónde se ve?
* ¿Aparecen las MAC's de MV1 y MV2? ¿Dónde se ve?
* ¿Aparece información del interfaz de red utilizado? ¿Dónde se ve?

**Pensar**
* Si salimos a Internet, como mínimo, siempre tenemos un vecino... nuestro router. Si vaciamos la tabla ARP y al reiniciar el equipo hacemos `ping 8.8.4.4`... ¿Qué IP-MAC nueva aparecerá en la tabla ARP?

---
# 4. UDP (DNS)

---
# 5. Telnet a MV3

Ir a MV3.
* Instalar característica "Servidor Telnet".
* Activar servicio.
* Crear usuario `nombre-del-alumno` y añadirlo a los grupos `Adminitradores` y `TelnetClients`.

Ir a MV1.
* Activar sniffing, hacer `telnet IP-MV3`, desactivar sniffing.
* Analizar los resultado (Usar filtros).

Plantear preguntas.

---
# 6. HTTP

---
# 7. SSH
