```
* Práctica creada en el curso 201415
* Actualizada para el curso 201516
```

#iSCSI en OpenSUSE

#1 Preparativos

Vamos a montar la práctica de iSCSI con OpenSUSE 13.2 (Consultar [configuraciones](../../global/configuracion-aula109.md) ).

Necesitamos 2 MV's.
* MV1: Esta MV actuará de `Initiator` . 
    * Con dos interfaces de red. 
    * Una en modo puente (172.19.XX.31) 
    * y la otra en red interna (192.168.XX.31) con nombre `san`.
        * Este interfaz NO tiene gateway.
* MV2: Esta MV actuará de `Target`. 
    * Con un interfaz de red (192.168.XX.32) en modo red interna `san`. 
    * Este interfaz tiene como gateway 192.168.XX.31.
* Las IP's las pondremos todas estáticas.
* Las IP's de la red interna estarán en el rango 192.168.XX.NN/24. 
Donde XX será el número correspondiente al puesto de cada alumno.

> **Enrutamiento**
>
> * [Enrutamiento en GNU/Linux](http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m6/enrutamiento_en_linux.html)
>
> *  Ejemplo de script que activa el enrutamiento y el NAT:
> ```
>     // activar-enrutamiento.sh
>     echo "1" > /proc/sys/net/ipv4/ip_forward
>     iptables -A FORWARD -j ACCEPT
>     iptables -t nat -A POSTROUTING -s IP_RED_INTERNA/MASCARA_RED_INTERNA -o eth0 -j MASQUERADE
> ```
> *  Ejemplo de script que desactivara el enrutamiento:
> ```
>     // desactivar-enrutamiento.sh
>     echo "0" > /proc/sys/net/ipv4/ip_forward
> ```

#2 Enlaces de interés

* TARGET - [Setting up iSCSI target on OpenSUSE](https://www.suse.com/documentation/sles10/book_sle_reference/data/sec_inst_system_iscsi_target.html)
* INITIATOR - [Setting up iSCSI initiator on OpenSUSE](https://www.suse.com/documentation/sles11/stor_admin/data/sec_inst_system_iscsi_initiator.html) 
* Vídeo: [EN - LINUX: ISCSI Target and Initiator Command Line configuration](https://youtu.be/5yMSxqUs4ys) 
* Vídeo: [EN - Configure iSCSI initiator (client)](https://youtu.be/8UojNONhQDo) 

#3 Resultado final

Como resultado final la máquina `Initiator` debe guardar información en el sistema de
almacenamiento proporcionado por la máquina `Target`.

Crear una carpeta en Initiator, llamada `/home/remote_target`, de modo que la información
que se guarde en ella se almacena en el Target remoto.


#ANEXO

##A.1 iSCSI en Debian

Enlaces de interés:
* iSCSI - [Using iSCSI (target and initiator) on Debian](https://www.howtoforge.com/using-iscsi-on-debian-lenny-initiator-and-target).
* TARGET - [Create targer iSCSI on Debian](https://wiki.debian.org/SAN/iSCSI/iscsitarget). 
