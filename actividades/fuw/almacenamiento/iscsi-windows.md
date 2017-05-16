```
* Práctica creada en el curso 201415
* Actualizada para el curso 201516
* Problemas en Windows
    * al instalar el Target
    * usar el dispositivo
```

# iSCSI en Windows 2008 Server

Vamos a montar un iSCSI con Windows Server.

Para los que no estéis familiarizados con iSCSI, digamos que es una manera de “encapsular”
comandos SCSI en paquetes IP. De esta manera podemos acceder a sistemas de almacenamiento externos usando una red IP en lugar de los tradicionales buses SCSI o los canales de fibra. Es decir, una buena forma de montarnos una SAN.

La solución consta de al menos dos componentes. Un iSCSI Initiator y un Target.
El initiator es lo que utilizamos en el equipo que va a aceder a esos volumenes,
y el Target es lo que nos permitirá crear el sistema de almacenamiento compartido,
y el que permitira el acceso a las LUNs que se hayan creado a cada cliente específico.

Generalmente esta tecnología esta ya incluida en el propio hardware de los servidores y de los sistemas SAN, que ofrecen este tipo de conectividad a través de dispositivos multifunción. Sin embargo esto no excluye que un iniciador software se pueda conectar a un Target hardware o viceversa.

El iSCSI initiator puede descargarse gratuitamente, para Windows XP y Windows server 2003. En Windows Vista y Windows Server 2008 viene ya incluido por defecto. Los iniciadores software son muy útiles en entornos virtualizados, ya que permiten a las máquinas virtuales el acceso a sistemas de tipo SAN mediante tarjetas de red, generalmente dedicadas en el host y en el guest.

---

# 1 Preparativos

Necesitamos 2 MV's con Windows Server (Consultar [configuraciones](../../global/configuracion/windows-server.md)).
* MV1: Esta MV actuará de `Initiator`.
    * Con dos interfaces de red.
    * Una en modo puente (172.19.XX.21)
    * la otra en red interna (192.168.XX.21) con nombre `san_windows`.
        * Esta interfaz NO tiene gateway.
* MV2: Esta MV actuará de `Target`.
    * Con un interfaz de red (192.168.XX.22) en modo red interna `san_windows`.
    * Esta interfaz usa como gateway 192.168.XX.21.
* Las IP's las pondremos todas estáticas.
* Las IP's de la red interna estarán en el rango 192.168.XX.NN/24.
Donde XX será el número correspondiente al puesto de cada alumno.

---

# 2 Enlaces de interés

* Vídeo de referencia [ES - Crear y conectar recursos iSCSI](https://youtu.be/_77UL2kZEEA).
* TARGET - [How to use iSCSI target on Windows 2008 server](https://www.synology.com/en-global/knowledgebase/DSM/tutorial/Virtualization/How_to_use_iSCSI_Targets_on_a_Windows_Server)
* INITIATOR - [Guía paso a paso del iniciador Windows](https://technet.microsoft.com/es-es/library/ee338476%28v=ws.10%29.aspx)

> **NOTA**
>
> En el firewall de Windows habilitar regla de entrada `eco ICMP v4` para
permitir que funcione la respuesta al comando ping.
>

---

# 3 Instalar el target

* Vídeo de referencia [ES - Crear y conectar recursos iSCSI](https://youtu.be/_77UL2kZEEA).
* Hay que descargar el software iSCSI Target para Windows
* Crear un destino iSCSI
* Identificar: ayuda a identificar el Initiador que hará uso del almacenamiento.
* Crear disco virtual para el destino iSCSI
* Le da formato al nuevo dispositivo.
* Desmontar dispositivo en Target.

---

# 4 Configurar Iniciador

* Vídeo de referencia [ES - Crear y conectar recursos iSCSI](https://youtu.be/_77UL2kZEEA).
* Vamos al iniciador. El software Iniciador ya viene preinstalado.
Sólo hay que configurarlo para conectar con el target.

---

# 5 Comprobación final

* Crear una carpeta en Initiator, llamada `C:\remote_target`, de modo que la información
que se guarde en ella se almacena en el Target remoto.
* Como resultado final la máquina `Initiator` debe guardar información en el sistema de
almacenamiento proporcionado por la máquina `Target`.
