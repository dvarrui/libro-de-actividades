
# Raspberry PI conectado a la red

---

# Preparar el Software

* Instalar el SO en la RbPI.
* Crear usuario "nombre-del-alumno".
* Poner hostname "rbpiNN", donde NN es el identificador de la Raspberry.
* Activar el servidor SSH.
* Comprobar que funciona correctamente el acceso remoto SSH con nuestro usuario.

---

# Identificación

Pegar una etiqueta (papel) a la RbPI donde ponga:
* ID de la RbPI (Por ejemplo "RbPI16")
* MAC de la tarjeta de red del dispositivo.

---

# Conectar en la red

* Conectar la RbPI a la red, en el switch del aula, usando la boca (puerto) XX, donde XX es el identificador del alumno.
* Conectar la RbPI a la corriente.

---

# Localizar la IP

Para conocer la IP de nuestra RbPI tenemos dos opciones:
* (a) Ya la sabemos porque le pusimos la IP estática 172.18.XX.51.
* (b) Tenemos que averiguarla porque tiene la IP dinámica. Sigue leyendo.

Para averiguar la IP hacemos lo siguiente:
* Ir a nuestro PC desktop.
* `sudo arp-scan 172.18.0.0/16` para hacer un barrido por un rango de IP's.

> Otras formas de hacer el barrido IP:
>
> * ping -b -c1 -i 20 172.18.99.255
> * ping -b -c3 -i 20 172.18.255.255
> * nmap -Pn 172.18.99.255
> * nmap -Pn 172.18.255.255

* Ejecutando `arp > arp.txt`, se cre el fichero arp.txt. Consulta el fichero y busca tu MAC.
* Ya tenemos la IP.

---

# Acceso remoto

* Comprobar acceso remoto a nuestra RbPI, desde el PC desktop ('ssh nombre-alumno@ip-rbpi')
