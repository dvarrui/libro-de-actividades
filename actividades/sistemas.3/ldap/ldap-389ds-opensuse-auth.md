
```
Curso       : 202021
Area        : Sistemas operativos, Servicio de Directorio LDAP, Autenticación
Descripción : Configurar autenticación a través del servicio de directorio 389-DS
Requisitos  : Partimos de 389-DS en OpenSUSE
Tiempo      :
```

---
# Cliente para autenticación LDAP

Con autenticacion LDAP prentendemos usar la máquina servidor LDAP, como repositorio centralizado de la información de grupos, usuarios, claves, etc. Desde otras máquinas conseguiremos autenticarnos (entrar al sistema) con los usuarios definidos no en la máquina local, sino en la máquina remota con LDAP. Una especie de *Domain Controller*.

En esta actividad, vamos a configurar otra MV (GNU/Linux OpenSUSE) para que podamos hacer autenticación en ella, pero usando los usuarios y grupos definidos en el servidor de directorios LDAP de la MV1.

# 1. Preparativos

* Supondremos que tenemos una MV1 (serverXX) con DS-389 instalado, y con varios usuarios dentro del DS.
* Necesitamos MV2 con SO OpenSUSE ([Configuración MV](../../global/configuracion/opensuse.md))

Comprobamos el acceso al LDAP desde el cliente:
* Ir a MV cliente.
* `nmap -Pn IP-LDAP-SERVERXX | grep -P '389|636'`, para comprobar que el servidor LDAP es accesible desde la MV2 cliente. En caso contrario revisar que el cortafuegos está abierto en el servidor y que el servicio está activo.
* `ldapsearch -H ldap://IP-LDAP-SERVERXX:389 -W -D "cn=Directory Manager" -b "dc=ldapXX,dc=curso2021" "(uid=*)" | grep dn`, comprobamos que los usuarios del LDAP remoto son visibles en el cliente.

# 2. Configurar autenticación LDAP

> Enlaces de interés:
> * https://doc.opensuse.org/documentation/leap/archive/15.3/security/html/book-security/cha-security-ldap.html
> * [Configurar_servidor_de_autenticacion_usando_YaST](https://es.opensuse.org/Configurar_servidor_de_autenticacion_usando_YaST)

## 2.1 Crear conexión con servidor

Vamos a configurar de la conexión del cliente con el servidor LDAP.

* Ir a la MV cliente.
* No aseguramos de tener bien el nombre del equipo y nombre de dominio (`/etc/hostname`, `/etc/hosts`)
* Ir a `Yast -> LDAP y Kerberos`. En el caso de que no nos aparezca esta herramienta, la podemos instalar con el paquete `yast2-auth-client`.
* Configurar como la imagen de ejemplo:
    * BaseDN: `dc=ldapXX,dc=curso2021`
    * DN de usuario: `cn=Directory Manager`
    * Contraseña: CLAVE del usuario cn=Directory Manager

![opensuse422-ldap-client-conf.png](./images/opensuse422-ldap-client-conf.png)

* Pulsar el botón para `Probar conexión`.
* Aceptar.

## 2.2 Comprobar con comandos

* Vamos a la consola con usuario root, y probamos lo siguiente:

```
id mazinger
su -l mazinger   # Entramos con el usuario definido en LDAP

getent passwd mazinger          # Comprobamos los datos del usuario
cat /etc/passwd | grep mazinger # El usuario NO es local
```

# 3. Crear usuarios usando otros comandos

Ir a la MV del servidor:
* `dsidm localhost user list`, consultar la lista de usuarios.
* Crear usuario robot1
```
dsidm localhost user create --uid robot1 \
   --cn robot1 --displayName 'robot1' --uidNumber 2101 --gidNumber 100 \
  --homeDirectory /home/robot1
```
* Poner la clave al usuario robot1:
```
dsidm localhost account reset_password \
  uid=robot1,ou=people,dc=ldapXX,dc=curso2122
```
* `dsidm localhost user list`, consultar la lista de usuarios.

Ir a la MV cliente:
* Abrir terminal.
* `su robot1`, entrar como ese usuario.

# 3. Crear usuarios y grupos dentro del LDAP

En este punto vamos a escribir información dentro del servidor de directorios LDAP.
Este proceso se debe poder realizar tanto desde el Yast del servidor, como desde el Yast
del cliente.

* Ir a la MV cliente.
* `Yast -> Usuarios Grupos`.
* Set filter: `LDAP users`.
* Bind DN: `cn=Directory Manager,dc=ldapXX,dc=curso2021`.
* Crear el grupo `villanos` (Estos se crearán dentro de la `ou=groups`).
* Crear los usuarios `robot`, `baron` (Estos se crearán dentro de la `ou=people`).
* Consultar/comprobar el contenido de la base de datos LDAP.
    * `ldapsearch -H ldap://IP-LDAP-SERVER -W -D "cn=Directory Manager" -b "dc=ldapXX,dc=curso2021" "(uid=NOMBRE-DEL-USUARIO)"` comando para consultar en la base de datos LDAP la información del usuario con uid concreto.

# 4. Autenticación

Con autenticacion LDAP prentendemos usar la máquina servidor LDAP, como repositorio centralizado de la información de grupos, usuarios, claves, etc. Desde otras máquinas conseguiremos autenticarnos (entrar al sistema) con los usuarios definidos no en la máquina local, sino en la máquina remota con LDAP. Una especie de *Domain Controller*.

## 4.1 Comprobamos autenticación desde el cliente con comandos

* Vamos a la consola con nuestro usuario normal, y probamos lo siguiente:
```
id robot
su -l robot   # Entramos con el usuario definido en LDAP

getent group villanos           # Comprobamos los datos del grupo
cat /etc/group | grep villanos  # El grupo NO es local

getent passwd baron             # Comprobamos los datos del usuario
cat /etc/passwd | grep baron    # El usuario NO es local
```

## 4.2 Comprobar autenticación desde el cliente por el entorno gráfico

* Ir a la MV cliente.
* Iniciar sesión gráfica con algún usuario LDAP.
* Iniciar sesión con usuarios definidos en el LDAP remoto.

> También podemos probar desde el terminal haciendo lo siguiente:
> * `id robot`
> * `su -l robot`, entramos con el usuario definido en LDAP

---
# ANEXO

## Generar Certificados

* https://www.linuxito.com/seguridad/598-como-crear-un-certificado-ssl-autofirmado-en-dos-simples-pasos
* https://www.adictosaltrabajo.com/2003/08/07/iisssl/
* https://www.linuxito.com/gnu-linux/nivel-alto/994-como-implementar-ldap-sobre-ssl-tls-con-openldap

Opción 1:
* Crear certificado autofirmado: `openssl req -newkey rsa:1024 -x509 -nodes -out server.pem -keyout server.pem -days 365`.
* Export firma PKCS12: `openssl pkcs12 -export -out server.p12 -in server.pem`.

Opción 2:
* `openssl genrsa -des3 -out server.key 1024`
* `openssl rsa -in server.key -out server.pem`
* `openssl req -new -key server.key -out server.csr`
* `openssl x509 -req -days 360 -in server.csr -signkey server.key -out server.crt`

## [PENDIENTE] de completar la información sobre los certificados.

Esto no funciona, pero es un intento de crear certificado y firma para LDAPS.

* Crear certificado autofirmado: `openssl req -newkey rsa:1024 -x509 -nodes -out server.pem -keyout server.pem - days 265`.
* Export firma PKCS12: `openssl pkcs12 -export -out server.pfx -in server.pem`.
