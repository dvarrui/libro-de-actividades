
```
Estado : EN CONSTRUCCIÓN
```

# Metasploit: escaneo de puertos

Metasploit tiene integrados varios scanners. Ejecutando el comando `search portscan` los veremos.

Ejemplos de escaneos de puertos incluidos en Metasploit Framework:
```
auxiliary/scanner/http/wordpress_pingback_access normal Wordpress Pingback Locator
auxiliary/scanner/natpmp/natpmp_portscan normal NAT-PMP External Port Scanner
auxiliary/scanner/portscan/ack normal TCP ACK Firewall Scanner
auxiliary/scanner/portscan/ftpbounce normal FTP Bounce Port Scanner
auxiliary/scanner/portscan/syn normal TCP SYN Port Scanner
auxiliary/scanner/portscan/tcp normal TCP Port Scanner
auxiliary/scanner/portscan/xmas normal TCP “XMas” Port Scanner
auxiliary/scanner/sap/sap_router_portscanner
```

> Consider running 'msfupdate' to update to the latest version.

* `nmap -Pn 172.28.128.3`
* `sudo nmap -sS -Pn 172.28.128.3`

```
msf > use auxiliary/scanner/portscan/tcp
msf auxiliary(scanner/portscan/tcp) > options

Module options (auxiliary/scanner/portscan/tcp):

   Name         Current Setting  Required  Description
   ----         ---------------  --------  -----------
   CONCURRENCY  10               yes       The number of concurrent ports to check per host
   DELAY        0                yes       The delay between connections, per thread, in milliseconds
   JITTER       0                yes       The delay jitter factor (maximum value by which to +/- DELAY) in milliseconds.
   PORTS        1-10000          yes       Ports to scan (e.g. 22-25,80,110-900)
   RHOSTS                        yes       The target address range or CIDR identifier
   THREADS      1                yes       The number of concurrent threads
   TIMEOUT      1000             yes       The socket connect timeout in milliseconds


msf auxiliary(scanner/portscan/tcp) > set RHOSTS 172.28.128.3
 RHOSTS => 172.28.128.3
msf auxiliary(scanner/portscan/tcp) > options

  ...
  RHOSTS       172.28.128.3     yes       The target address range or CIDR ...
  ...

msf auxiliary(scanner/portscan/tcp) > run

[+] 172.28.128.3:         - 172.28.128.3:21 - TCP OPEN
[+] 172.28.128.3:         - 172.28.128.3:22 - TCP OPEN
```
