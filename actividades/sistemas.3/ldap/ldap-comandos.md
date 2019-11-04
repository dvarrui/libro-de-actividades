
```
Estado     : En construcción
Curso      : 201920
Requisitos : Servidor LDAP instalado y configurado
```

---
# LDAP comandos

---
# 0. TEORIA

## 0.1 ¿Que es LDAP?

Extraído de:
* [Curso de LDAP en GNU/Linux](https://docplayer.es/1981696-Curso-de-ldap-en-gnu-linux-60-horas.html) Página 7.

LDAP en inglés Lightweight Directory Access Protocol. Traducido   al   español su significado es: Protocolo Ligero para Acceder al Servicio de Directorio, ésta implementación se basa en un conjunto de estándares de redes de computadoras (X.500) sobre el servicio de directorios.  

LDAP se ejecuta sobre TCP/IP o sobre otros servicios de transferencia   orientado a conexión; que permite el acceso a los datos de un directorio ordenado y distribuido para buscar información.

Habitualmente se almacena información de los usuarios que conforman
una red de computadores, como por ejemplo el nombre de usuario, contraseña, directorio hogar, etc. Es posible almacenar otro tipo de información tal como, bebida preferida, número de teléfono celular, fecha de cumpleaños, etc.

En  conclusión, LDAP es un protocolo de acceso unificado a un conjunto  de información sobre los usuarios de una red de computadores

## 0.2 ¿Qué tipo de información se puede almacenar en un directorio?

En principio en un servicio de directorio se puede almacenar cualquier tipo
de información. Como por ejemplo, nombre, dirección de habitación, nombre de
la mascota, música preferida, bebida favorita, etc. Sin embargo, la información
que se almacena es aquella que permita organizar de manera jerárquica todos
los usuarios de la red. Estructurar la información de los usuarios de la red es de utilidad a la hora de restringir el acceso a los servicios y recursos de la red; Permitiendo gestionar con mayor facilidad la red.

---
# 1. Preparativos

## 1.1 Configurar las MVs

Configurar MV1:

| Atributo | Valor         |
| -------- | ------------- |
| Rol      | Servidor LDAP |
| hostname | ldap-serverXX |

Configurar MV2:

| Atributo | Valor         |
| -------- | ------------- |
| Rol      | Cliente LDAP  |
| hostname | ldap-clientXX |

## 1.2 Comprobar el servicio directorio LDAP

Ir a la MV1:
* Asegurarse de que el servicio LDAP está corriendo
    * `lsof -i -T -n -P`
    * `systemctl status ...`
* `ldapsearch -x -b "dc=apellidoXX,dc=asir"`, comprobar accesibilidad en local.

Ir a la MV2:
* Asegurarse de que el servicio LDAP es accesible desde el cliente.
    * `nmap -Pn ldap-serverXX`

---
# 2. Comandos LDAP

## 2.1 Consultar contenido del directorio LDAP

Enlaces de interés:
* [Consultas a directorios LDAP utilizando ldapsearch](https://www.linuxito.com/gnu-linux/nivel-alto/1023-consultas-a-directorios-ldap-utilizando-ldapsearch)

Normalmente se usa la clase `inetOrgPerson`, para almacenar usuarios dentro de un directorio LDAP. Dicha clase posee el atributo `uid`.
Por tanto, para listar los usuarios de un directorio, podemos filtrar por `"(uid=*)"`. Veamos un ejemplo:

```
ldapsearch -z 0
           -H ldap://localhost:389
           -W
           -D "cn=root,dc=apellidoXX,dc=asir"
           -b "dc=apellidoXX,dc=asir" "(uid=*)"
```

| Parámetro                  | Descripción                     |
| -------------------------- | ------------------------------- |
| -z 0                       | No hay límite en las respuestas |
| -H ldap://localhost:389    | IP:puerto del servidor          |
| -W                  | Se solicita contraseña para autenticar |
| -D "cn=root,dc=apellidoXX,dc=asir"  | Usuario de la conexión |
| -b "dc=apellidoXX,dc=aisr" | Base donde comenzar la búsqueda |
| "(uid=*)"                  | Filtro para la búsqueda         |

> Importante: No olvidar especificar la base (-b). De lo contrario probablemente no haya resultados en la búsqueda.

## 2.2 Escrituras en LDAP

Uno de los usos más frecuentes para el directorio LDAP es para la administración de usuarios. Vamos a utilizar ficheros **ldif** para agregar usuarios.

### Crear las unidades organizativas (OU)

* Fichero `ou_people.ldif` para la crear la OU "people":
```
dn: ou=people,dc=apellidoXX,dc=asir
ou: people
objectclass: organizationalUnit
```
* `# ldapadd -x -W -D "cn=admin,dc=apellidoXX,dc=asir" -f people.ldif`
* Fichero `ou_group.ldif`, para crear la UO "group":
```
dn: ou=group,dc=apellidoXX,dc=asir
ou: group
objectclass: organizationalUnit
```
* `# ldapadd -x -W -D "cn=admin,dc=apellidoXX,dc=asir" -f group.ldif`

### Crear los grupos

* Fichero `g_users.ldif`, para crear el grupo "users":
```
dn: cn=users,ou=group,dc=apellidoXX,dc=asir
objectclass: posixGroup
objectclass: top
cn: users
userPassword: {crypt}*
gidNumber: 100
```
* `# ldapadd -x -W -D "cn=admin,dc=apellidoXX,dc=asir" -f users.ldif`
* `# ldapsearch -x -b "dc=apellidoXX,dc=asir"`, para comprobar los resultados.

## Agregar usuarios

Enlace de interés:
* [Cómo configurar el password de root de LDAP en MD5 o SHA-1](https://www.linuxito.com/seguridad/991-como-configurar-el-password-de-root-de-ldap-en-md5-o-sha-1)

* Fichero `mazinger.ldif`:
```
dn: uid=mazinger,ou=people,dc=apellidoXX,dc=asir
uid: mazinger
cn: Mazinger Z
objectClass: account
objectClass: posixAccount
objectClass: top
objectClass: shadowAccount
userPassword: {CLEARTEXT}clave secreta
shadowLastChange: 14001
shadowMax: 99999
shadowWarning: 7
loginShell: /bin/bash
uidNumber: 2001
gidNumber: 100
homeDirectory: /home/mazinger
gecos: Mazinger Z
```

* `# ldapadd -x -W -D "cn=admin,dc=apellidoXX,dc=asir" -f mazinger.ldif`

---

# 3. Contraseñas

Enlaces de interés:
* [Configurar password LDAP en MD5 o SHA-1](https://www.linuxito.com/seguridad/991-como-configurar-el-password-de-root-de-ldap-en-md5-o-sha-1)
* [UNIX/GNU/Linux md5sum Command Examples](https://linux.101hacks.com/unix/md5sum/)´

En el ejemplo anterior la clave se puso en texto plano. Cualquiera puede leerlo y no es seguro. Vamos generar valores de password encriptados.

La herramienta `slappasswd` provee la funcionalidad para generar un valor userPassword adecuado. Con la opción -h es posible elegir uno de los siguientes esquemas para almacenar la contraseña:
* {CLEARTEXT} (texto plano),
* {CRYPT} (crypt),
* {MD5} (md5sum),
* {SMD5} (MD5 con salt),
* {SHA} (1ssl sha) y
* {SSHA} (SHA-1 con salt, esquema por defecto).

**Ejemplo SHA-1**

Para generar un valor de contraseña hasheada utilizando SHA-1 con salt compatible con el formato requerido para un valor userPassword, ejecutar el siguiente comando:

```
root@ldap-serverXX:~# slappasswd -h {SSHA}
New password:
Re-enter new password:
{SSHA}5uUxSgD1ssGkEUmQTBEtcqm+I1Aqsp37
```

**Ejemplo MD5**

También podemos usar el comando `md5sum` para crear claves md5. Ejemplo:
```
$ md5sum
clave secreta
43cff9e9a30167a1e383026bf61108f2  -
```

Ahora, crear los siguientes usuarios en LDAP con una clave encriptada:

| Full name       | Login acount | uid  |
| --------------- | ------------ | ---- |
| Koji Kabuto     | koji         | 2002 |
| Doctor Infierno | infierno     | 2003 |
| Boss            | boss         | 2004 |

---
# 4. Usar el servicio LDAP

Ir a la MV `ldap-clientXX`:
* Crear un script que haga uso del servicio LDAP.

Por ejemplo:
* Crear un script llamado `set-users-from-ldap`.
* Dicho script leerá los usuarios definidos en el servidor LDAP.
* Para cada usuario:
    * Si el usuario no existe, entonces lo creará en el sistema local.
    * Si el usuario existe, entonces se muestra un mensaje en pantalla.
* El resto de usuarios del sistema con uid>2000, serán desactivados.
