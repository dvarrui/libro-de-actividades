```
Nuevo curso 2018-2019
```

---

# Servidor 389-DS - OpenSUSE

> **Últimas noticias** [Red Hat y Suse retiran su apoyo a OpenLDAP2](https://www.ostechnix.com/redhat-and-suse-announced-to-withdraw-support-for-openldap/).
>
> Por este motivo hemos decido a partir de noviembre de 2018 cambiar el OpenLDAP2 por 389-DS.

![arbol](./images/arbol.png)

> Enlaces de interés sobre teoría LDAP:
> * VÍDEO [¿Qué es LDAP?](http://www.youtube.com/watch?v=CXe0Wxqep_g)
> * VÍDEO [Los ficheros LDIF](http://www.youtube.com/watch?v=ccFT94M-c4Y)

Hay varias herramientas que implementan el servidor de directorios LDAP
(389-DS, OpenLDAP, Active Directory, etc). En esta guía vamos a instalar y
configurar del servidor LDAP con 389-DS.

---

# 1. Prerequisitos

> Enlaces de interés:
>
> * [389 Directory Server Documentation](http://directory.fedoraproject.org/docs/389ds/documentation.html)
> * [389-DS installation](https://access.redhat.com/documentation/en-us/red_hat_directory_server/10/html/installation_guide/)

## 1.1 Nombre de equipo FQDN

* Vamos a usar una MV OpenSUSE para montar nuestro servidor LDAP con:
    * [Configuración MV](../../global/configuracion/opensuse.md)
* Nuestra máquina debe tener un FQDN.
    * Nombre equipo: `ldap-serverXX.curso1819`
    * Además en `/etc/hosts` añadiremos:
```
ip-del-servidor   ldap-serverXX.curso1819   ldap-serverXX
127.0.0.3         nombrealumnoXX.curso1819  nombrealumnoXX
```

> Veamos imagen de ejemplo:
>
> ![opensuse-host-names.png](./images/opensuse-host-names.png)

## 1.2 Opening the Required Ports in the Firewall

* `systemctl status firewalld` Make sure the firewalld service is running.
* `systemctl enable firewalld`, configure it to start automatically when the system boots.
* `systemctl start firewalld`, to start firewalld.  
* `firewall-cmd --permanent --add-port={389/tcp,636/tcp,9830/tcp}
`, Open the required ports using the firewall-cmd utility. For example, to open the Directory Server default ports in the default firewall zone.

> For details on using firewall-cmd to open ports on a system, see the Red Hat Security Guide or the firewall-cmd(1) man page.

* `firewall-cmd --reload`, Reload the firewall configuration to ensure that the change takes place immediately.

---

# 2. Instalar el Servidor

## 2.1 Información

Durante el proceso de instalación será necesario:
* Crear un usuario y grupo para el servidor de directorios (dirsrv).
* El sufijo del directorio es la primera entrada al árbol de directorios.
Ejemplo: si el host del servidor de directorios se llama `ldap.example.com`, entonces el sufijo es `dc=example,dc=com`.

## 2.2 Script de Perl

La documentación indica que debemos usar el script `setup-ds-admin.pl`
para hacer la instalación y configuración inicial del 389-DS.

Este script se ejecuta como superusuario y de forma interactiva iremos configurando los parámetros de la instalación.


* Abrir una consola como root.
* La documentación de 389-DS dice que ahora debemos ejecutar el script `setup-ds-admin.pl`, pero no lo encontramos:
    * `find / -name setup-ds-admin.pl`, pero no encuentramos este script.
    * Enlaces de interés: https://serverfault.com/questions/658042/how-to-install-and-setup-389-ds-on-centos-7
* Se nos ocurre otro camino. `cnf setup-ds.pl`
    * Buscar paquete del SO `zypper se 389-ds`.
    * Instalar `zypper in 389-ds`
* `find / -name setup-ds.pl`, el script tiene un nombre diferente al que aparece en la documentación.
    * Yeray lo ha encontrado en `/usr/sbin/setup-ds.pl`
* Ejecutar con el usuario root y el resultado lo tenemos [aquí](./files/salida-setup-ds.txt). Veamos un resumen:

```
ldap-server27:~ # setup-ds.pl

==============================================================================
This program will set up the 389 Directory Server.
==============================================================================
Choose a setup type:
   2. Typical
       Allows you to specify common defaults and options.

Choose a setup type [2]: 2

==============================================================================
Enter the fully qualified domain name of the computer
on which you're setting up server software. Using the form
<hostname>.<domainname>

Computer name [ldap-server27]: ldap-server27.curso1819

==============================================================================
The server must run as a specific user in a specific group.

System User [dirsrv]:
System Group [dirsrv]:

==============================================================================
The standard directory server network port number is 389.

Directory server network port [389]:

==============================================================================

Usuario administrador de la BD LDAP [Directory Manager]:
...
```

> Recordar el nombre y clave de nuestro usuario administrador del servidor de directorios LDAP

## 2.3 Comprobamos el servicio

* `systemctl status dirsrv@ldap-serverXX`, comprobar si el servicio está en ejecución.
* `systemctl enable dirsrv@ldap-serverXX`, activar al inicio.
* `systemctl start dirsrv@ldap-serverXX`, iniciar el servicio.
* `ps -ef |grep ldap`, para comprobar si el demonio está en ejecución.
* `nmap -Pn localhost | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde la red.

---

# 3. Browser LDAP

Hay varias herramientas que pueden servir como browser LDAP:
* [ldapadmin](http://www.ldapadmin.org/).
* [phpLDAPadmin](http://phpldapadmin.sourceforge.net/wiki/index.php/Main_Page)
* gq
* mozldap-tools
* etc
Podemos usar cualquiera.

## 3.1 Instalar browser LDAP

* Instalar un browser LDAP.
* Usar un browser LDAP para comprobar el contenido del servidor de directorios LDAP:
    * `File -> Preferencias -> Servidor -> Nuevo`
    * URI = `ldap://ldap-serverXX`
    * Base DN = `dc=ldap-serverXX,dc=curso1819`
    * Admin user =`cn=Administrator,dc=ldap-serverXX,dc=curso1819`
* ¿Tenemos creadas las unidades organizativas: `groups` y `people`?

![gq-browser.png](./images/gq-browser.png)

## 3.2 Crear usuarios y grupos dentro del LDAP

En este punto vamos a escribir información dentro del servidor de directorios LDAP.

> Enlaces de interés:
>
> * [ Crear usuarios y grupos LDAP ](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
> * VIDEO [LPIC-2 202 LDAP Client Usage](http://www.youtube.com/embed/ZAHj93YWY84).

* `Yast -> Usuarios Grupos -> Filtro -> LDAP`.
* Crear el grupo `soldados` (Estos se crearán dentro de la `ou=groups`).
* Crear los usuarios `soldado1`, `soldado2` (Estos se crearán dentro de la `ou=people`).
* Usar el browser LDAP para consultar/comprobar el contenido de la base de datos LDAP.

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

# 4. Cliente para autenticación LDAP

Ahora vamos a configurar otra MV GNU/Linux para que podamos hacer autenticación en ella, pero usando los usuarios y grupos definidos en el servidor de directorios LDAP.

## 4.1 Preparativos

* Vamos a otra MV.
    * SO OpenSUSE.
    * [Configuración MV](../../global/configuracion/opensuse.md)
    * Nombre equipo: `ldap-clientXX`
    * Dominio: `curso1819`
    * Asegurarse que tenemos definido en el fichero `/etc/hosts` del cliente,
el nombre DNS con su IP correspondiente:
```
127.0.0.2         ldap-clientXX.curso1718   ldap-clientXX
IP-DEL-SERVIDOR   ldap-serverXX.curso1718   ldap-serverXX   
```

## 4.2 Comprobación

* `nmap -Pn ldap-serverXX | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde la MV cliente.
* Usar un browser LDAP en el cliente para comprobar que se han creado bien los usuarios.
    * `File -> Preferencias -> Servidor -> Nuevo`
    * URI = `ldap://ldap-serverXX`
    * Base DN = `dc=ldap-serverXX,dc=curso1819`

## 4.3 Instalar y configurar la autenticación

Vamos a configurar de la conexión del cliente con el servidor LDAP.

* Debemos instalar el paquete `yast2-auth-client`, que nos ayudará a configurar la máquina para autenticación.
* Ir a `Yast -> LDAP y cliente Kerberos`.
* Configurar como la imagen de ejemplo. Al final usar la opción de `Probar conexión`

![opensuse422-ldap-client-conf.png](./images/opensuse422-ldap-client-conf.png)

## 4.4 Comprobamos desde el cliente

* Vamos a la consola con nuestro usuario normal, y probamos lo siguiente:
```
getent group soldados           # Comprobamos los datos del grupo
cat /etc/group | grep soldados  # El grupo NO es local

getent passwd soldado1          # Comprobamos los datos del usuario
cat /etc/passwd | grep soldado1 # El usuario NO es local
```

---

# 5. Autenticación

Con autenticacion LDAP prentendemos usar la máquina servidor LDAP, como repositorio centralizado de la información de grupos, usuarios, claves, etc.
Desde otras máquinas conseguiremos autenticarnos (entrar al sistema) con los
usuarios definidos no en la máquina local, sino en la máquina remota con
LDAP. Una especie de *Domain Controller*.

* Ir a la MV cliente.
* Iniciar sesión gráfica con algún usuario LDAP.
* Iniciar sesión con usuario local.
* Abrir una consola y hacer lo siguiente:
```
id soldado1
finger soldado1
su -l soldado1   # Entramos con el usuario definido en LDAP
```

> **Si tenemos problemas al reiniciar la MV cliente**
>
> Hacer lo siguiente:
> * Iniciar MV con Knoppix
> * Deshacer los cambios ldap en el fichero `/etc/nsswitch.conf`
>     * `passwd: files nis ldap`
>     * `shadow: files nis`
>     * `group: files nis ldap`
> * Reiniciar MV cliente
> * Repetir configuración Yast.
