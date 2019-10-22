
```
Estado : En construcción. Sólo está la idea
Curso  : 201920
```

---

# Redes TCP/IP y el modelo OSI: enfoque práctico

Vamos a hacer una práctica para conocer las redes TCP/IP y el modelo OSI.

# 1. Preparativos

Crear 3 MV de la siguiente forma:


| ID  | Hostname  | OS        | Red-1       | IP-1         | Red-2       |
| --- | --------- | --------- | ----------- | ------------ | ----------- |
| MV1 | masterXXg | GNU/Linux | Red interna | 192.168.XX.1 | Modo puente |
| MV2 | slaveXXg  | GNU/Linux | Red interna | 192.168.XX.2 | -           |
| MV3 | slaveXXw  | Windows   | Red interna | 192.168.XX.3 | -           |

---
# 2. ARP

* Instalar Wireshark
* Consultar tabla ARP actual. Debe estar vacía.
* Activar sniffing, hacer ping a IP-MV2 y parar sniffing.
* Analizar los resultados (Usar filtro... ARP)

Plantear preguntas...

---
# 3. ICMP (Ping)

* Analizar los resultado (Usar filtro ICMP).

Plantear preguntas.

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
