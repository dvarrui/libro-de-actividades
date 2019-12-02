
```
Curso           : 201920, 201819
Software        : 389-DS, OpenSUSE
Tiempo estimado :
Ultimos cambios : Se quita browser ldap por fallos de las app
                  Se pasa autenticación desde cliente a otra actividad
                  Se añaden comandos ldap para creación de usuarios
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
| (2.3) Comprobar contenido del DS LDAP | | | |
| (3.3) Comprobar nuevo usuario | | | |
| (4.3) Comprobar los usuarios creado | | | .|

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
* `/usr/sbin/setup-ds.pl`, para ejecutar el script de instalación.
A continuación iremos respondiendo a las preguntas de configuración del servicio (Cambiar XX por identificador de cada alumno):

```
==================================================
* Choose a setup type  => 2. Typical
* FQDN of the computer => serverXX.curso1920
* System User          => dirsrv
* System Group         => dirsrv
* Network port number  => 389
* DS identifier        => ldapXX
* Suffix (valid DN)    => dc=ldapXX,dc=curso1920
* Administrative user  => cn=Directory Manager
==================================================
```

Nos aparece este "Warning2 de SELinux, pero la instancia LDAP se ha creado:

```
ImportError: No module named selinux
Your new DS instance 'ldap42' was successfully created.
Exiting . . .
Log file is '/tmp/setupuofQkd.log'
```

> **IMPORTANTE**:
> * Recordar el nombre y clave de nuestro usuario administrador del servidor de directorios LDAP
> * Los ficheros de configuración de nuestro servicio/instancia los tenemos en `/etc/dirsrv/slapd-ldap42`

## 2.2 Comprobamos el servicio

* `systemctl status dirsrv@ldapXX`, comprobar si el servicio está en ejecución.
* `nmap -Pn ldapXX | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde la red. En caso contrario, comprobar cortafuegos.

> Más ayuda:
> * `ps -ef |grep ldap`, para comprobar si el demonio está en ejecución.
> * `systemctl enable dirsrv@ldapXX`, activar al inicio.
> * `systemctl start dirsrv@ldapXX`, iniciar el servicio.

## 2.3 Comprobamos el acceso al contenido del LDAP

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
# 3. Añadir usuarios LDAP por comandos

> Enlaces de interés:
> * [Consultas a directorios LDAP utilizando ldapsearch](https://www.linuxito.com/gnu-linux/nivel-alto/1023-consultas-a-directorios-ldap-utilizando-ldapsearch)

## 3.1 Buscar Unidades Organizativas

Deberían estar creadas las OU People y Groups, es caso contrario hay que crearlas (Consultar ANEXO). Ejemplo para buscar las OU:

```
ldapsearch -H ldap://localhost:389
           -W -D "cn=Directory Manager"
           -b "dc=ldap42,dc=curso1920" "(ou=*)"
```

| Parámetro                  | Descripción                     |
| -------------------------- | ------------------------------- |
| -H ldap://localhost:389    | IP:puerto del servidor          |
| -W                         | Se solicita contraseña |
| -D "cn=Directory Manager"  | Usuario de la conexión |
| -b "dc=ldap42,dc=curso1920" | Base donde comenzar la búsqueda |
| "(uid=*)"                  | Filtro para la búsqueda         |

> Importante: No olvidar especificar la base (-b). De lo contrario probablemente no haya resultados en la búsqueda.

## 3.2 Agregar usuarios

Uno de los usos más frecuentes para el directorio LDAP es para la administración de usuarios. Vamos a utilizar ficheros **ldif** para agregar usuarios.

* Fichero `mazinger-add.ldif` con la información para crear el usuario `mazinger`:

```
dn: uid=mazinger,ou=people,dc=ldapXX,dc=curso1920
uid: mazinger
cn: Mazinger Z
objectClass: account
objectClass: posixAccount
objectClass: top
objectClass: shadowAccount
userPassword: {CLEARTEXT}escribir la clave secreta
shadowLastChange: 14001
shadowMax: 99999
shadowWarning: 7
loginShell: /bin/bash
uidNumber: 2001
gidNumber: 100
homeDirectory: /home/mazinger
gecos: Mazinger Z
```

* `ldapadd -x -W -D "cn=Directory Manager" -f mazinger.ldif
`, escribir los datos del fichero **ldif** anterior:

## 3.3 Comprobar el nuevo usuario

* `ldapsearch -W -D "cn=Directory Manager" -b "dc=ldap42,dc=curso1920" "(uid=*)"`, para comprobar si se ha creado el usuario en el LDAP.

Normalmente se usa la clase `inetOrgPerson`, para almacenar usuarios dentro de un directorio LDAP. Dicha clase posee el atributo `uid`.
Por tanto, para listar los usuarios de un directorio, podemos filtrar por `"(uid=*)"`.

> **Eliminar usuario del árbol del directorio**
>
> * Crear un archivo `mazinger-delete.ldif`:
>
> ```
> dn: uid=mazinger,ou=people,dc=ldap42,dc=curso1920
> changetype: delete
> ```
>
> * Ejecutamos el siguiente comando para eliminar un usuario del árbol LDAP: `ldapmodify -x -D "cn=Directory Manager" -W -f mazinger-delete.ldif`

---
# 4. Contraseñas encriptadas

> Enlace de interés:
> * [Configurar password LDAP en MD5 o SHA-1](https://www.linuxito.com/seguridad/991-como-configurar-el-password-de-root-de-ldap-en-md5-o-sha-1)
> * [UNIX/GNU/Linux md5sum Command Examples](https://linux.101hacks.com/unix/md5sum/)´
> * [Cómo configurar el password de root de LDAP en MD5 o SHA-1](https://www.linuxito.com/seguridad/991-como-configurar-el-password-de-root-de-ldap-en-md5-o-sha-1)

En el ejemplo anterior la clave se puso en texto plano. Cualquiera puede leerlo y no es seguro. Vamos generar valores de password encriptados.

## 4.1 TEORIA: Herramienta slappasswd

La herramienta `slappasswd` provee la funcionalidad para generar un valor userPassword adecuado. Con la opción -h es posible elegir uno de los siguientes esquemas para almacenar la contraseña:
* {CLEARTEXT} (texto plano),
* {CRYPT} (crypt),
* {MD5} (md5sum),
* {SMD5} (MD5 con salt),
* {SHA} (1ssl sha) y
* {SSHA} (SHA-1 con salt, esquema por defecto).

**Ejemplo SHA-1**

Para generar un valor de contraseña hasheada utilizando SHA-1 con salt compatible con el formato requerido para un valor userPassword, ejecutar el siguiente comando:

```bash
$ slappasswd -h {SSHA}
New password:
Re-enter new password:
{SSHA}5uUxSgD1ssGkEUmQTBEtcqm+I1Aqsp37
```

**Ejemplo MD5**

También podemos usar el comando `md5sum` para crear claves md5. Ejemplo:

```bash
$ md5sum
clave secreta
43cff9e9a30167a1e383026bf61108f2  -
```

## 4.2 Agregar más usuarios

* Ir a la MV servidor LDAP.
* Crear los siguientes usuarios en LDAP con una clave encriptada:

| Full name       | Login acount | uid  |
| --------------- | ------------ | ---- |
| Koji Kabuto     | koji         | 2002 |
| Boss            | boss         | 2003 |
| Doctor Infierno | drinfierno   | 2004 |


## 4.3 Comprobar los usuarios creados

* Ir a la MV cliente LDAP.
* Ejecutar comando `ldpasearch ...` para consultar los usuarios LDAP en el servidor de directorios remoto.

---

# ANEXO

## Crear las unidades organizativas (OU)

* Fichero `ou_people.ldif` para la crear la OU "people":

```bash
dn: ou=people,dc=apellidoXX,dc=asir
ou: people
objectclass: organizationalUnit
```

* Ejecutar :

```bash
ldapadd -x -W -D "cn=admin,dc=apellidoXX,dc=asir" -f people.ldif
```

* Fichero `ou_group.ldif`, para crear la UO "group":

```bash
dn: ou=group,dc=apellidoXX,dc=asir
ou: group
objectclass: organizationalUnit
```

* Ejecutar

```bash
ldapadd -x -W -D "cn=admin,dc=apellidoXX,dc=asir" -f group.ldif
```

## Crear los grupos

* Fichero `g_users.ldif`, para crear el grupo "users":

```bash
dn: cn=users,ou=group,dc=apellidoXX,dc=asir
objectclass: posixGroup
objectclass: top
cn: users
userPassword: {crypt}*
gidNumber: 100
```

* Ejecutar los comandos:

```bash
ldapadd -x -W -D "cn=admin,dc=apellidoXX,dc=asir" -f users.ldif  # Agregar registro
ldapsearch -x -b "dc=apellidoXX,dc=asir" # Comprobar los resultados.
```
---
## 3.3 Crear usuarios y grupos dentro del LDAP

En este punto vamos a escribir información dentro del servidor de directorios LDAP.
* `Yast -> Usuarios Grupos`.
* Set filter: `LDAP users`.
* Bind DN: `cn=Directory Manager`.
* Crear el grupo `villanos` (Estos se crearán dentro de la `ou=groups`).
* Crear los usuarios `drinfierno`, `baron` (Estos se crearán dentro de la `ou=people`).
* Usar el browser LDAP para consultar/comprobar el contenido de la base de datos LDAP.
* `ldapsearch -x -L -u -t "(uid=nombre-del-usuario)"`, comando para consultar en la base de datos LDAP la información del usuario con uid concreto.
