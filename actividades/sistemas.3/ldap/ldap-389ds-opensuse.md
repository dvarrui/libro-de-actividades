
```
Curso           : 201920, 201819
Software        : 389-DS, OpenSUSE
Tiempo estimado :
Comentarios     : REVISAR la parte de autenticación con FreeIPA+389-DS
```

---

# Servidor 389-DS - OpenSUSE

> Enlaces de interés:
>
> * VÍDEO Teoría [¿Qué es LDAP?](http://www.youtube.com/watch?v=CXe0Wxqep_g)
> * VÍDEO Teoría [Los ficheros LDIF](http://www.youtube.com/watch?v=ccFT94M-c4Y)

## Introducción

![arbol](./images/arbol.png)

Hay varias herramientas que implementan el servidor de directorios LDAP (389-DS, OpenLDAP, Active Directory, etc).

Según parece [Red Hat y Suse retiran su apoyo a OpenLDAP2](https://www.ostechnix.com/redhat-and-suse-announced-to-withdraw-support-for-openldap/), por este motivo, hemos decido a partir de noviembre de 2018 cambiar OpenLDAP2 por 389-DS.

En esta guía vamos a instalar y configurar del servidor LDAP con 389-DS.

## Entrega

Ejemplo de rúbrica:

| Sección | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| ------- | ------------ | ----------- | ----------------- |
| (2.4) Comprobar contenido del DS LDAP | | | |
| (3.3) Browser LDAP: Usuarios y grupos | | | |

---
# 1. Prerequisitos

> Enlaces de interés:
>
> * [389 Directory Server Documentation](http://directory.fedoraproject.org/docs/389ds/documentation.html)
> * [389-DS installation RH](https://access.redhat.com/documentation/en-us/red_hat_directory_server/10/html/installation_guide/)
Enlaces de interés:
> * [389-DS installation Fedora](https://directory.fedoraproject.org/docs/389ds/howto/quickstart.html#installing-the-software)

## 1.1 Nombre de equipo FQDN

* Vamos a usar una MV OpenSUSE para montar nuestro servidor LDAP ([Configuración MV](../../global/configuracion/opensuse.md)).
* Nuestra máquina debe tener un FQDN=`serverXX.curso1920`.
    * Revisar `/etc/hostname`
    * Revisar `/etc/hosts`

```
127.0.0.2   serverXX.curso1920   serverXX
```

* Comprobar salida de: `hostname -a`, `hostname -d` y `hostname -f`.

## 1.2 Cortafuegos

Abrir los puertos en en cortafuegos:

* `systemctl status firewalld`, comprobar el estado del cortafuegos. Debe estar en ejecución.
* `firewall-cmd --permanent --add-port={389/tcp,636/tcp,9830/tcp}
`, abre determinados puertos en el cortafuegos usando la herramienta  "firewall-cmd"
* `firewall-cmd --reload`, recargar la configuración del cortafuegos para asegurarnos de que se han leído los nuevos cambios.

> Recordatorio:
> * `systemctl enable firewalld`, activar contafuegos en el inicio del sistema.
> * `systemctl start firewalld`, iniciar el cortafuegos.  

---

# 2. Instalar el Servidor LDAP

## 2.1 Instalación del paquete

* Abrir una consola como root.
* Instalar `zypper in 389-ds`
* Ahora debemos tener un script en `/usr/sbin/setup-ds.pl`.

## 2.2 Script de Perl

* Abrir una consola como root.
* `/usr/sbin/setup-ds.pl`, ejecutar el script y responder a las preguntas de configuración del servicio.

Veamos un resumen de la salida del script:
```
==================================================
* Choose a setup type  => 2. Typical
* FQDN of the computer => server42.curso1920
* System User          => dirsrv
* System Group         => dirsrv
* Network port number  => 389
* DS identifier        => ldap42
* Suffix (valid DN)    => dc=ldap42,dc=curso1920
* Administrative user  => cn=Directory Manager
==================================================
```

Nos aparece este Warning de SELinux, pero la instancia LDAP se ha creado:
```
ImportError: No module named selinux
Your new DS instance 'ldap42' was successfully created.
Exiting . . .
Log file is '/tmp/setupuofQkd.log'
```

> **IMPORTANTE**:
> * Recordar el nombre y clave de nuestro usuario administrador del servidor de directorios LDAP
> * Los ficheros de configuración de nuestro servicio/instancia los tenemos en `/etc/dirsrv/slapd-ldap42`

## 2.3 Comprobamos el servicio

* `systemctl status dirsrv@ldapXX`, comprobar si el servicio está en ejecución.
* `nmap -Pn ldapXX | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde la red. En caso contrario, comprobar cortafuegos.

> Más ayuda:
> * `ps -ef |grep ldap`, para comprobar si el demonio está en ejecución.
> * `systemctl enable dirsrv@ldapXX`, activar al inicio.
> * `systemctl start dirsrv@ldapXX`, iniciar el servicio.

## 2.4 Comprobamos el acceso al contenido del LDAP

* `ldapsearch -b "dc=ldap42,dc=curso1920" -x | grep dn`, muestra el contenido de nuestra base de datos LDAP.
* Comprobar que existen las OU Groups y People.
* `ldapsearch -H ldap://localhost -b "dc=ldap42,dc=curso1920" -W -D "cn=Directory Manager" | grep dn`, en este caso hacemos la consulta usando usuario/clave.

| Parámetro                   | Descripción                |
| --------------------------- | -------------------------- |
| -x                          | No se valida usuario/clave |
| -b "dc=ldap42,dc=curso1920" | Base/sufijo del contenido  |
| -H ldap://localhost:389     | IP:puerto del servidor     |
| -W                          | Se solicita contraseña     |
| -D "cn=Directory Manager"   | Usuario del LDAP           |

---

# 3. Añadir usuarios LDAP con Yast

## 3.3 Crear usuarios y grupos dentro del LDAP

En este punto vamos a escribir información dentro del servidor de directorios LDAP.
* `Yast -> Usuarios Grupos`.
* Set filter: `LDAP users`.
* Bind DN: `cn=Directory Manager`.
* Crear el grupo `villanos` (Estos se crearán dentro de la `ou=groups`).
* Crear los usuarios `drinfierno`, `baron` (Estos se crearán dentro de la `ou=people`).
* Usar el browser LDAP para consultar/comprobar el contenido de la base de datos LDAP.
* `ldapsearch -x -L -u -t "(uid=nombre-del-usuario)"`, comando para consultar en la base de datos LDAP la información del usuario con uid concreto.

---
# 4. Cliente para autenticación LDAP

Ahora vamos a configurar otra MV GNU/Linux para que podamos hacer autenticación en ella, pero usando los usuarios y grupos definidos en el servidor de directorios LDAP.

## 4.1 Preparativos

Vamos a otra MV.
* SO OpenSUSE.
* [Configuración MV](../../global/configuracion/opensuse.md)
* Nombre equipo: `1er-apellido-alumnoXXg2`
* Dominio: `curso1920`
* Asegurarse que tenemos definido en el fichero `/etc/hosts` del cliente, el nombre DNS con su IP correspondiente:

```
172.19.XX.31   serverXX.curso1920   serverXX   
127.0.0.2      1er-apellidoXXg2.curso1920   1er-apellidoXXg2
```

## 4.2 Comprobación

* `nmap -Pn ldapXX | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde la MV cliente.
* Usar un browser LDAP en el cliente para comprobar que se han creado bien los usuarios.
    * `File -> Preferencias -> Servidor -> Nuevo`
    * URI = `ldap://serverXX`
    * Base DN = `dc=ldapXX, dc=curso1920`

## 4.3 Instalar y configurar la autenticación

Vamos a configurar de la conexión del cliente con el servidor LDAP.

* Debemos instalar el paquete `yast2-auth-client`, que nos ayudará a configurar la máquina para autenticación.
* Ir a `Yast -> LDAP y cliente Kerberos`.
* Configurar como la imagen de ejemplo. Al final usar la opción de `Probar conexión`

![opensuse422-ldap-client-conf.png](./images/opensuse422-ldap-client-conf.png)

## 4.4 Comprobamos desde el cliente

* Vamos a la consola con nuestro usuario normal, y probamos lo siguiente:
```
getent group villanos           # Comprobamos los datos del grupo
cat /etc/group | grep villanos  # El grupo NO es local

getent passwd baron             # Comprobamos los datos del usuario
cat /etc/passwd | grep baron    # El usuario NO es local
```

---

# 5. Autenticación

Con autenticacion LDAP prentendemos usar la máquina servidor LDAP, como repositorio centralizado de la información de grupos, usuarios, claves, etc. Desde otras máquinas conseguiremos autenticarnos (entrar al sistema) con los usuarios definidos no en la máquina local, sino en la máquina remota con LDAP. Una especie de *Domain Controller*.

Ejemplo que muestra la plataforma de autenticación gobcan:

```
Página Principal / ► Entrar al sitio
El módulo LDAP no puede conectarse a ninguno de los servidores:
Server: 'ldap://directorio.gobiernodecanarias.net/',
Connection: 'Resource id #29', Bind result: ''
```

## 5.1 Comprobar autenticación desde el cliente

* Ir a la MV cliente.
* Iniciar sesión gráfica con algún usuario LDAP.
* Iniciar sesión con usuario local.
* Abrir una consola y hacer lo siguiente:
```
id drinfierno
finger drinfierno
su -l drinfierno   # Entramos con el usuario definido en LDAP
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

---

# ANEXO

## 3.1 [OPCIONAL] Instalar browser LDAP

Hay varias herramientas que pueden servir como browser LDAP:
* [ldapadmin](http://www.ldapadmin.org/).
* [phpLDAPadmin](http://phpldapadmin.sourceforge.net/wiki/index.php/Main_Page)
* gq
* mozldap-tools
* etc

Podemos usar cualquiera. Aunque suele ser recomendable usar la que venga por defecto con nuestra distribución.
* Instalar un browser LDAP.

## 3.2 [OPCIONAL] Browser LDAP

* Usar un browser LDAP para comprobar el contenido del servidor de directorios LDAP:
    * `File -> Preferencias -> Servidor -> Nuevo`
    * URI = `ldap://serverXX`
    * Base DN = `dc=ldapXX, dc=curso1920`
    * Admin user = `cn=Directory Manager`.

    > Enlaces de interés:
    >
    > * [How To Set Up Centralized Linux Authentication with FreeIPA on CentOS 7](https://www.digitalocean.com/community/tutorials/how-to-set-up-centralized-linux-authentication-with-freeipa-on-centos-7)
    > * [ Crear usuarios y grupos LDAP ](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
    > * VIDEO [LPIC-2 202 LDAP Client Usage](http://www.youtube.com/embed/ZAHj93YWY84).
