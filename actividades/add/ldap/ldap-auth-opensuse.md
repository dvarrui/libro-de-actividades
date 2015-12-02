
*(Práctica nueva del curso 2015-2016)*

#Autenticación LDAP
Con autenticacion LDAP prentendemos usar una máquina como servidor LDAP,
donde se guardará la información de grupos, usuarios, claves, etc. Y desde
otras máquinas conseguiremos autenticarnos (entrar al sistema) con los 
usuarios definidos no en la máquina local, sino en la máquina remota con
LDAP. Una especie de *Domain Controller*.

> *Enlaces de interés*
> * [Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
> * [Configurar_LDAP_usando_YaST](https://es.opensuse.org/Configurar_LDAP_usando_YaST)
>
> Otros
> * Consultar documento `ldap-auth-pminik-taller.pdf`, página 20.
> * Enlaces de interés [Autenticación con OpenLDAP](http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m6/autentificacin_del_sistema_con_openldap.html).

##1. Preparativos
Vamos a usar dos MV con GNU/Linux OpenSUSE 13.2
* Servidor LDAP:
    * IP estática del servidor 172.18.XX.51 (Donde XX es su número de puesto).
    * Nombre equipo: `ldap-server-XX`
    * Dominio: `curso1516`
    * Asegurarse que tenemos definido en el fichero `/etc/hosts` del servidor, el nombre DNS con su IP correspondiente: 
        * `127.0.0.2   ldap-serverXX.curso1516   ldap-serverXX`.
        * `127.0.0.3   nombredealumnoXX.curso1516   nombrealumnoXX`. 
    * Instalar servidor SSH.
    * Ejecutar `ip a` (Capturar imagen)
    * Ejecutar `lsblk` (Capturar imagen)
    * Ejecutar `blkid` (Capturar imagen)
* Cliente LDAP:    
    * IP estática del cliente 172.18.XX.52
    * Nombre equipo: `ldap-client-XX`
    * Dominio: `curso1516`
    * Asegurarse que tenemos definido en el fichero `/etc/hosts` del cliente, 
el nombre DNS con su IP correspondiente: 
        * `127.0.0.2         ldap-clientXX.curso1516   ldap-clientXX`.
        * `ip-del-servidor   ldap-serverXX.curso1516   ldap-serverXX   nombredealumnoXX.curso1516   nombrealumnoXX`.
    * Instalar servidor SSH.
    * Ejecutar `ip a` (Capturar imagen)
    * Ejecutar `lsblk` (Capturar imagen)
    * Ejecutar `blkid` (Capturar imagen)

##2. Configurar cliente
* Consultar enlace sobre [Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
* Configurar la MV cliente para usar usuarios locales y usuarios del servidor LDAP.
* Añadir más usuarios LDAP. Como por ejemplo: `jedi31`, `jedi32`, `sith31` y `sith32`.
* Añadir los grupos LDAP. Como por ejemplo:  `jedis3` y `siths3`.

##3. Comprobaciones
Vamos a comprobar que con los usuarios definidos en el LDAP se puede entrar a la MV cliente.

* Entrar en `.ldap-clientXX`, con `jedi21` y `sith31` y ejecutar los comandos siguientes
capturando su salida:
```
id (Muestra información del usuario actual)
cat /etc/passwd |grep nombre-usuario (No debe existir este usuario en la MV local)
pamtest passwd nombre-usuario
finger nombre-usuario
hostname -f (Muestra nombre de la MV actual)
ip a (Muestra datos de red de la MV actual)
date (Muestra la fecha actual)
```

