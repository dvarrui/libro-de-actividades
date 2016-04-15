```
* Práctica creada en el curso 201415
* Actualizada para el curso 201516
```

#1. iSCSI en Windows 2008 Server

##1.1 Objetivo

Vamos a montar un iSCSI con Windows Server.

Necesitamos 2 MV's con Windows Server (Consultar [configuraciones](../../global/configuracion-aula109.md)).
* MV1: Esta MV actuará de "Initiator" . 
    * Con dos interfaces de red. 
    * Una en modo puente (172.19.XX.21) y la otra en red interna (192.168.XX.1). (Iniciador iSCSI).
* MV2: Esta MV actuará de "target".
    * Con un interfaz de red en modo red interna (192.168.XX.2).
* Las IP's las pondremos todas estáticas.
* Las IP's de la red interna estarán en el rango 192.168.XX.NN/24. 
Donde XX será el número correspondiente al puesto de cada alumno.

##1.2 Enlaces de interés

* TARGET - [How to use iSCSI target on Windows 2008 server](https://www.synology.com/en-global/knowledgebase/DSM/tutorial/Virtualization/How_to_use_iSCSI_Targets_on_a_Windows_Server)
* TARGET - [Targets iSCSI software para Windows](https://blogs.technet.microsoft.com/davidcervigon/2007/08/29/targets-iscsi-gratuitos-para-windows) 
* INITIATOR - [Guía paso a paso del iniciador Windows](https://technet.microsoft.com/es-es/library/ee338476%28v=ws.10%29.aspx)

* Vídeo: [ES - Crear y conectar recursos iSCSI](https://youtu.be/_77UL2kZEEA).

> **NOTA**
>
> En el firewall de Windows habilitar regla de entrada `eco ICMP v4` para 
permitir que funcione la respuesta al comando ping.


#2. iSCSI en OpenSUSE

##2.1 Objetivo
Vamos a montar la práctica de iSCSI con OpenSUSE 13.2 (Consultar [configuraciones](../../global/configuracion-aula109.md) ).

Necesitamos 2 MV's.
* MV1: Esta MV actuará de "Initiator" . 
    * Con dos interfaces de red. 
    * Una en modo puente (172.19.XX.31) y la otra en red interna (192.168.XX.1). (Iniciador iSCSI).
* MV2: Esta MV actuará de "target". 
    * Con un interfaz de red en modo red interna (192.168.XX.2).
* Las IP's las pondremos todas estáticas.
* Las IP's de la red interna estarán en el rango 192.168.XX.NN/24. 
Donde XX será el número correspondiente al puesto de cada alumno.

##2.2 Enlaces de interés

* TARGET - [Setting up iSCSI target on OpenSUSE](https://www.suse.com/documentation/sles10/book_sle_reference/data/sec_inst_system_iscsi_target.html) 
* INITIATOR - [Setting up iSCSI initiator on OpenSUSE](https://www.suse.com/documentation/sles11/stor_admin/data/sec_inst_system_iscsi_initiator.html) 

* Vídeo: [EN - LINUX: ISCSI Target and Initiator Command Line configuration](https://youtu.be/5yMSxqUs4ys) 
* Vídeo: [EN - Configure iSCSI initiator (client)](https://youtu.be/8UojNONhQDo) 

#ANEXO

##A.1 iSCSI en Debian

Enlaces de interés:
* iSCSI - Using iSCSI (target and initiator) on Debian
* TARGET - Create targer iSCSI on Debian
