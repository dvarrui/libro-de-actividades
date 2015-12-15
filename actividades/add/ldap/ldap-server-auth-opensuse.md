
*(Actividad nueva para e curso 2015-2016)*

> Enlaces de interés:
> * Teoría
>     * [Presentación: ¿Qué es LDAP?](http://www.youtube.com/watch?v=CXe0Wxqep_g)
>     * [Presentación: Los ficheros LDIF](http://www.youtube.com/watch?v=ccFT94M-c4Y)
> * OpenSUSE
>     * [Configurar_LDAP_usando_YaST](https://es.opensuse.org/Configurar_LDAP_usando_YaST)
>     * VIDEO [Configurar servidor LDAP en OpenSUSE con Yast](http://www.youtube.com/watch?v=NsQ1zPpoVBc)
>     * [Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
> * Otros
>     * VIDEO [LD01: Instalar Servidor OpenLDAP](http://www.youtube.com/watch?v=E0mIYO_vbx8) Min 38: Crear config dir a partir de config text.
>     * Consultar documento `ldap-auth-pminik-taller.pdf`, página 20.
>     * [Autenticación con OpenLDAP web ite](http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m6/autentificacin_del_sistema_con_openldap.html).
>     * VIDEO [LPIC-2 202 LDAP Client Usage](http://www.youtube.com/embed/ZAHj93YWY84).

#Servidor LDAP - OpenSUSE

##1.1 Preparar la máquina
Comenzamos la instalación del servidor LDAP:
* Vamos a usar una MV para montar nuestro servidor LDAP con:
    * SO OpenSUSE 13.2
    * Instalar servidor SSH.
    * IP estática del servidor 172.18.XX.51 (Donde XX es su número de puesto).
    * Nombre equipo: `ldap-serverXX`
    * Dominio: `curso1516`
    * Además en `/etc/hosts` añadiremos:
```
127.0.0.2   ldap-serverXX.curso1516   ldap-serverXX
127.0.0.3   nombrealumnoXX.curso1516  nombrealumnoXX
```
* Capturar imagen de la salida de los siguientes comandos: `ip a`, `hostname -f`, `lsblk`, `blkid`

Veamos imagen de ejemplo:

![opensuse-host-names.png](./images/opensuse-host-names.png)

##1.2 Instalación del Servidor LDAP
* Procedemos a la instalación del módulo Yast para gestionar el servidor LDAP (`yast2-auth-server`).
En Yast aparecerá como `Authentication Server`.
* Apartir de aquí seguimos los pasos indicados en [servidor LDAP](https://es.opensuse.org/Configurar_LDAP_usando_YaST)
de la siguiente forma:
   * Ir a Yast -> Servidor de autenticación.
   * Tipo de servidor: autónomo
   * Configuración TLS: NO habilitar
   * Usar como DN el siguiente: `dc=nombredealumnoXX, dc=curso1516`. Donde XX es el número del puesto de cada uno.

![opensuse-ldapserver-config-form.png](./images/opensuse-ldapserver-config-form.png)

   * NO habilitar kerberos.
   
Veamos ejemplo de la configuración final:

![opensuse-ldapserver-config-resume.png](./images/opensuse-ldapserver-config-resume.png)

* Una vez instalado, comprobar el servicio `systemctl  status slapd`. 
Comprobar también que el servicio se inicia automáticamente al reiniciar la máquina. 
* Continuar los pasos del enlace hasta el final, donde se puede comprobar el contenido
de la base de datos LDAP usando la herramienta `gq`. Esta herramienta es un browser LDAP.
* Comprobar que ya tenemos las unidades organizativas: `groups` y `people`.

##1.3. Crear usuarios y grupos en LDAP
Ahora vamos a [introducir datos de usuarios y grupos](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
en el servidor LDAP siguiendo los pasos indicados en el enlace, pero personalizado la información de la siguiente
forma:

* Debemos instalar el paquete `yast2-auth-client`, que nos ayudará a configurar la máquina para autenticación.
En Yast aparecerá como `Authentication Client`.

> * El parámetro LDAP URI es un localizador del recurso de la base de datos LDAP. 
Veamos un ejemplo: `ldap://ldap-serverXX/dc=nombrealumnoXX,dc=curso1516`.
> * Las unidades organizativas: `groups` y `people`. Han sido creadas 
automáticamente por Yast en el paso anterior.

* Crear los grupos `jedis2` y `siths2` (Estos se crearán dentro de la `ou=groups`).
* Crear los usuarios `jedi21`, `jedi22`, `sith21`, `sith22` (Estos se crearán dentro de la `ou=people`).

Vemos un ejemplo de un árbol de datos en LDAP:

![arbol](./images/arbol.png)

* Comprobar mediante un browser LDAP (`gq`) la información que tenemos en la base de datos LDAP.
* Con el comando `ldapsearch -x -L -u -t "(uid=nombre-del-usuario)"`, podemos hacer una consulta en la base
de datos LDAP de la información del usuario.

Veamos imágenes de ejemplo

![userPassword_empty-gq](./images/userPassword_empty-gq.png)

![userPassword_empty-ldapsearch](./images/userPassword_empty-ldapsearch.png)

##1.4. Autenticación
Con autenticacion LDAP prentendemos usar una máquina como servidor LDAP,
donde se guardará la información de grupos, usuarios, claves, etc. Y desde
otras máquinas conseguiremos autenticarnos (entrar al sistema) con los 
usuarios definidos no en la máquina local, sino en la máquina remota con
LDAP. Una especie de *Domain Controller*.

* Comprobar que podemos entrar (Inicio de sesión) en la MV `ldap-serverXX` usando los usuarios
definidos en el LDAP.
* Capturar imagen de la salida de los siguientes comandos:
```
hostname -f (Muestra nombre de la MV actual)
ip a (Muestra datos de red de la MV actual)
date
cat /etc/passwd |grep nombre-usuario (No debe existir este usuario en la MV local)
finger nombre-usuario
id nombre-usuario
su nombre-usuario
```

#2. Otro equipo
Ahora que tenemos una máquina con la información cargada en LDAP, vamos a tratar de hacer uso
de ella desde otra máquina distinta.
 
##2.1 Preparativos
* Slave LDAP:    
    * IP estática del esclavo 172.18.XX.52
    * Nombre equipo: `ldap-slaveXX`
    * Dominio: `curso1516`
    * Asegurarse que tenemos definido en el fichero /etc/hosts del cliente, 
el nombre DNS con su IP correspondiente: 
```
127.0.0.2         ldap-slaveXX.curso1516   ldap-slaveXX
ip-del-servidor   ldap-serverXX.curso1516   ldap-serverXX   nombredealumnoXX.curso1516   nombrealumnoXX
```
* Capturar imagen de la salida de los siguientes comandos: `ip a`, `hostname -f`, `lsblk`, `blkid`

##2.2 Configuración
* Seguir las instrucciones del siguiente [enlace](https://es.opensuse.org/Ingreso_de_usuarios_y_grupos_en_LDAP_usando_YaST)
para crear e grupo LDAP `troopers` y dentro de éste los usuarios `trooper21` y `trooper22`.
* Usar la heeramienta `gq` para comprobar los datos del servidor LDAP.
* Comprobar que podemos entrar (Inicio de sesión) en la MV `ldap-slaveXX` usando los usuarios
definidos en el LDAP remoto.
* Capturar imagen de la salida de los siguientes comandos:
```
hostname -f (Muestra nombre de la MV actual)
ip a (Muestra datos de red de la MV actual)
date
cat /etc/passwd |grep nombre-usuario (No debe existir este usuario en la MV local)
finger nombre-usuario
id nombre-usuario
su nombre-usuario
```

#A1. ANEXO

Podemos tener un problema con las claves si el método de encriptación de las claves
del sistema operativo es diferente al utilizado en el servidor LDAP.

##A1.2. Cambiar el método de encriptación en el SO

Veamos ejemplo donde se establece el método de encriptación durante la instalación del SO.

![opensuse-password-encryption-method.png](./images/opensuse-password-encryption-method.png)

Veamos otro ejemplo donde podemos cambiar el método de encriptación de claves con el SO
ya instalado, usando Yast.

![opensuse-yast-password-encryption-method.png](./images/opensuse-yast-password-encryption-method.png)

##A1.3. Cambiar el método de encriptación en la base de datos LDAP

*(Pendiente)* 
