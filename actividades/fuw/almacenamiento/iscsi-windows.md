```
* Práctica creada en el curso 201415
* Actualizada para el curso 201516
* Problemas en Windows
    * al instalar el Target
    * usar el dispositivo
```

# iSCSI en Windows 2008 Server

Vamos a montar un iSCSI con Windows Server.

---

# 1 Preparativos

Necesitamos 2 MV's con Windows Server (Consultar [configuraciones](../../global/configuracion-aula109.md)).
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

* TARGET - [How to use iSCSI target on Windows 2008 server](https://www.synology.com/en-global/knowledgebase/DSM/tutorial/Virtualization/How_to_use_iSCSI_Targets_on_a_Windows_Server)
* TARGET - [Targets iSCSI software para Windows](https://blogs.technet.microsoft.com/davidcervigon/2007/08/29/targets-iscsi-gratuitos-para-windows)
* INITIATOR - [Guía paso a paso del iniciador Windows](https://technet.microsoft.com/es-es/library/ee338476%28v=ws.10%29.aspx)

> **NOTA**
>
> En el firewall de Windows habilitar regla de entrada `eco ICMP v4` para
permitir que funcione la respuesta al comando ping.
>
> Vídeo de referencia [ES - Crear y conectar recursos iSCSI](https://youtu.be/_77UL2kZEEA).

---

# 3 Instalar el target

* Vamos al target, instalar el software de target en el `Administrador Servidor`.
    * `Característiscas/Funciones -> Agregar Almacenamiento SAN`.

---

# 4 Configurar Iniciador

* Vamos al iniciador. El software Iniciador ya viene preinstalado.
Sólo hay que configurarlo para conectar con el target.

---

# 5 Comprobación final

* Crear una carpeta en Initiator, llamada `C:\remote_target`, de modo que la información
que se guarde en ella se almacena en el Target remoto.
* Como resultado final la máquina `Initiator` debe guardar información en el sistema de
almacenamiento proporcionado por la máquina `Target`.
