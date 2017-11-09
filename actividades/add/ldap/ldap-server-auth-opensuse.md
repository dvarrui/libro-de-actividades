```
* Curso 2015-2016:  adaptada para OpenSUSE
* Curso 2016-2017
```

# Servidor LDAP - OpenSUSE

![arbol](./images/arbol.png)

> Enlaces de interés sobre teoría LDAP:
> * VÍDEO [¿Qué es LDAP?](http://www.youtube.com/watch?v=CXe0Wxqep_g)
> * VÍDEO [Los ficheros LDIF](http://www.youtube.com/watch?v=ccFT94M-c4Y)

---

## 1. Servidor LDAP

Hay varias herramientas que implementan el protocolo LDAP, por ejemplo:
OpenLDAP, 389-DC, Active Directory, etc. En esta guía vamos a instalar y
configurar del servidor LDAP con OpenLDAP.

> Enlaces de interés:
> * Guía sobre  [Configurar_LDAP_usando_YaST](https://es.opensuse.org/Configurar_LDAP_usando_YaST)
> * VIDEO [Configurar servidor LDAP en OpenSUSE con Yast](http://www.youtube.com/watch?v=NsQ1zPpoVBc)
> * VIDEO [LD01: Instalar Servidor OpenLDAP](http://www.youtube.com/watch?v=E0mIYO_vbx8) Min 38: Crear config dir a partir de config text.
> * Consultar documento `ldap-auth-pminik-taller.pdf`, página 20.

## 1.1 Preparar la máquina

* Vamos a usar una MV OpenSUSE para montar nuestro servidor LDAP con:
    * [Configuración MV](../../global/configuracion/opensuse.md)
    * Nombre equipo: `ldap-serverXX`
    * Además en `/etc/hosts` añadiremos:
```
127.0.0.2   ldap-serverXX.curso1718   ldap-serverXX
127.0.0.3   nombrealumnoXX.curso1718  nombrealumnoXX
```

Veamos imagen de ejemplo:

![opensuse-host-names.png](./images/opensuse-host-names.png)

## 1.2 Instalación del Servidor LDAP

* Procedemos a la instalación del módulo Yast que sirve para gestionar el servidor LDAP (`yast2-auth-server`).

> Enlaces de interés:
>
> * [Servidor LDAP Leap 42.1](https://en.opensuse.org/SDB:LDAP_server)
> * [Vídeo](https://www.youtube.com/watch?v=F14x3fGPN9E)
> * [Servidor LDAP Suse 11](https://es.opensuse.org/Configurar_LDAP_usando_YaST)

Hacemos lo siguiente:
* Ir a Yast -> Servidor de autenticación. Aparecerá como `Authentication Server`.
* Se requiere, además, instalar los paquetes: openldap2, krb5-server y krb5-client.
* Iniciar servidor LDAP -> Sí
* Registrar dameon SLP -> No
* Puerto abierto en el cortafuegos -> Sí -> Siguiente
* Tipo de servidor -> autónomo -> Siguiente
* Configuración TLS -> NO habilitar -> Siguiente
* Tipo de BD -> hdb
* DN base -> `dc=nombre-del-alumnoXX,dc=curso1718`. Donde XX es el número del puesto de cada uno.
* DN administrador -> `cn=Administrator`
* Añadir DN base -> Sí
* Contraseña del administrador
* Directorio de BD -> `/var/lib/ldap`
* Usar esta BD predeterminada para clientes LDAP -> Sí -> Siguiente

![opensuse-ldapserver-config-form.png](./images/opensuse-ldapserver-config-form.png)

* Habilitar kerberos -> No

Veamos ejemplo de la configuración final:

![opensuse-ldapserver-config-resume.png](./images/opensuse-ldapserver-config-resume.png)

Comprobaciones
* `slaptest -f /etc/openldap/slapd.conf` para comprobar la sintaxis del fichero
do configuración.
* `systemctl status slapd`, para comprobar el estado del servicio.

> `systemctl enable slapd`, para activar el servicio automáticamente al reiniciar la máquina.

* `nmap -Pn localhost | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde la red.
* `slapcat` para comprobar que la base de datos está bien configurada.
* Podemos comprobar el contenido de la base de datos LDAP usando la herramienta `gq`.
Esta herramienta es un browser LDAP.
* Comprobar que tenemos creadas las unidades organizativas: `groups` y `people`.

![gq-browser.png](./images/gq-browser.png)

## 1.3 Problemas

Si tenemos que desinstalar el software anterior, hacemos lo siguiente:
```
zypper remove yast2-auth-server
zypper remove openldap2 krb5-server krb5-client
mv /etc/openldap /etc/openldap.000
mv /var/lib/ldap /var/lib/ldap.000
```
---

## 1.4 Crear usuarios y grupos LDAP

* `Yast -> Usuarios Grupos -> Filtro -> LDAP`.
* Crear el grupo `piratas2` (Estos se crearán dentro de la `ou=groups`).
* Crear los usuarios `pirata21`, `pirata22` (Estos se crearán dentro de la `ou=people`).
* Usar `gq` para consultar/comprobar el contenido de la base de datos LDAP.

> Vemos un ejemplo de un árbol de datos en LDAP:
> ![gq-browser-users.png](./images/gq-browser-users.png)
>
> Imagen de ejemplo:
> ![userPassword_empty-gq](./images/userPassword_empty-gq.png)

* `ldapsearch -x -L -u -t "(uid=nombre-del-usuario)"`, comando para consultar en la base de datos LDAP la información del usuario con uid concreto.

> Veamos imagen de ejemplo:
>
> ![userPassword_empty-ldapsearch](./images/userPassword_empty-ldapsearch.png)

---

# 2. Cliente LDAP

En este punto vamos a escribir información en el servidor LDAP.

> Enlaces de interés:
>
> * [ Crear usuarios y grupos LDAP ](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
> * [ Autenticación con OpenLDAP ](http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m6/autentificacin_del_sistema_con_openldap.html).
> * VIDEO [LPIC-2 202 LDAP Client Usage](http://www.youtube.com/embed/ZAHj93YWY84).

## 2.1 Preparativos

* Vamos a otra MV OpenSUSE.
* Cliente LDAP con OpenSUSE:
    * [Configuración MV](../../global/configuracion/opensuse.md)
    * Nombre equipo: `ldap-clientXX`
    * Dominio: `curso1718`
    * Asegurarse que tenemos definido en el fichero /etc/hosts del cliente,
el nombre DNS con su IP correspondiente:
```
127.0.0.2         ldap-clientXX.curso1718   ldap-clientXX
ip-del-servidor   ldap-serverXX.curso1718   ldap-serverXX   nombredealumnoXX.curso1718   nombrealumnoXX
```

## Comprobación

* `nmap -Pn ldap-serverXX | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde el cliente.
* Usar `gq` en el cliente para comprobar que se han creado bien los usuarios.
    * `File -> Preferencias -> Servidor -> Nuevo`
    * URI = `ldap://ldap-serverXX`
    * Base DN = `dc=davidXX,dc=curso1718`

## 2.2 Instalar cliente LDAP

* Debemos instalar el paquete `yast2-auth-client`, que nos ayudará a configurar la máquina para autenticación. En Yast aparecerá como `Authentication Client`.

Vamos a configurar de la conexión del cliente con el servidor LDAP.

> Información extraída de https://forums.opensuse.org/showthread.php/502305-Setting-up-LDAP-on-13-2

* `Yast -> Authentication client`
* Hacemos click sobre el botón sssd.
    * Aparece una ventana de configuración.
        * config_file_version = 2
        * services = nss, pam
        * domains = LDAP, nombre-de-alumnoXX
    * Escribir LDAP en la sección dominio.
    * Pulsamos OK y cerramos la ventana.
* Creamos un nuevo dominios.
    * domains = `nombre-de-alumnoXX`
    * id_provider = `ldap`
    * auth_provider = `ldap`
    * chpass_provider = `ldap`
    * ldap_schema = `rfc2307bis`
    * ldap_uri = `ldap://ldap-serverXX`
    * ldap_search base = `dc=davidXX, dc=curso1718`

Ver imagen de ejemplo:

![opensuse-ldap-client-conf.png](./images/opensuse-ldap-client-conf.png)

Consultar el fichero `/etc/sssd/sssd.conf` para confirmar el valor de ldap_schema.
```
# A native LDAP domain
[domain/LDAP]
enumerate = true
cache_credentials = TRUE

id_provider = ldap
auth_provider = ldap
chpass_provider = ldap

ldap_uri = ldap://ldap-serverXX
ldap_search_base = dc=davidXX,dc=curso1617
```

## 2.3 Comprobamos desde el cliente

* Vamos a la consola con nuestro usuario normal, y probamos lo siguiente:
```
systemctl status sssd | grep domain
getent passwd pirata21
getent group piratas2
id pirata21
finger pirata21
cat /etc/passwd | grep pirata21
cat /etc/group | grep piratas2
su pirata21
```

---

# HASTA AQUÍ ES LA ENTREGA DEL INFORME
Para el curso 2016-2017

---

Con autenticacion LDAP prentendemos usar la máquina servidor LDAP, como repositorio
centralizado de la información de grupos, usuarios, claves, etc.
Desde otras máquinas conseguiremos autenticarnos (entrar al sistema) con los
usuarios definidos no en la máquina local, sino en la máquina remota con
LDAP. Una especie de *Domain Controller*.

> **Default Re: Setting up LDAP on 13.2**
>
> Did you ever resolve your secondary group issues? I'm seeing the same problem and have already changed ldap_schema to rfc2307bis.
>
> I have resolved this issue. My solution was in `/etc/sssd/sssd.conf` comment out the lines
> # ldap_user_uuid = entryuuid
> # ldap_group_uuid = entryuuid

## 2.3 Crear usuarios y grupos en LDAP

Vamos a crear los usuarios y grupos en LDAP.

> Enlace de interés:
>
> * [Introducir datos de usuarios y grupos](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)

* `Yast -> Usuarios Grupos -> Filtro -> LDAP`.
* Crear los grupos `aldeanos2` y `soldados2` (Estos se crearán dentro de la `ou=groups`).
* Crear los usuarios `aldeano21`, `aldeano22`, `soldado21`, `soldado22` (Estos se crearán dentro de la `ou=people`).

## 2.5 Autenticación desde el cliente


* Comprobar que podemos entrar (Inicio de sesión) en la MV `ldap-slaveXX`
usando los usuarios definidos en el LDAP.
* Capturar imagen de la salida de los siguientes comandos:
```
hostname -f                          # Muestra nombre de la MV actual
ip a                                 # Muestra datos de red de la MV actual
date                                 # Fecha actual
cat /etc/passwd |grep nombre-usuario # No debe existir este usuario en la MV local
finger nombre-usuario                # Consulta info del usuario
id nombre-usuario
su nombre-usuario
```

---

# A. ANEXO

Podemos tener un problema con las claves si el método de encriptación de las claves
del sistema operativo es diferente al utilizado en el servidor LDAP.

## A.1 Cambiar el método de encriptación en el SO

Veamos ejemplo donde se establece el método de encriptación durante la instalación del SO.

![opensuse-password-encryption-method.png](./images/opensuse-password-encryption-method.png)

Veamos otro ejemplo donde podemos cambiar el método de encriptación de claves con el SO
ya instalado, usando Yast.

![opensuse-yast-password-encryption-method.png](./images/opensuse-yast-password-encryption-method.png)

## A.2 Cambiar el método de encriptación en la base de datos LDAP

*(Pendiente)*

## A.3 Configuración

* Seguir las instrucciones del siguiente [enlace](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
para crear el grupo LDAP `aldeanos` y dentro de éste los usuarios `aldeano21` y `aldeano22`.
* Usar la herramienta `gq` para comprobar los datos del servidor LDAP.
* Comprobar que podemos entrar (Inicio de sesión) en la MV `ldap-slaveXX` usando los usuarios
definidos en el LDAP remoto.
* Capturar imagen de la salida de los siguientes comandos:
```
hostname -f                          # Muestra nombre de la MV actual
ip a                                 # Muestra datos de red de la MV actual
date                                 # Fecha actual
cat /etc/passwd |grep nombre-usuario # No debe existir este usuario en la MV local
finger nombre-usuario                # Consulta info del usuario
id nombre-usuario
su nombre-usuario
```
