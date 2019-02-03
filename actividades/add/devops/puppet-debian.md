
```
INCOMPATIBILIDADES
Puppet 4.8 incompatible con Debian 9
https://puppet.com/docs/puppet/4.8/system_requirements.html
```

---

# 1. Introducción

Existen varias herramientas para realizar instalaciones desde un punto central, como Chef, Ansible, SaltStack, SUSE Manager, CFEngine, etc. En este ejemplo, vamos a usar Puppet.

Según Wikipedia, Puppet es una herramienta diseñada para administrar la configuración de sistemas Unix-like y de Microsoft Windows de forma declarativa. El usuario describe los recursos del sistema y sus estados, ya sea utilizando el lenguaje declarativo de Puppet o un DSL (lenguaje específico del dominio) de Ruby.

Ejemplo de rúbrica:

| Sección              | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| -------------------- | ------------ | ----------- | ----------------- |
| Comprobar v1 | | | |
| Comprobar v2 | | | |
| Comprobar v3 | | |. |

> Enlaces de interés:
>
> * [Puppetcookbook](http://www.puppetcookbook.com/posts/show-resources-with-ralsh.html)
> * [Vídeo sin audio - 14 minutos de duración](https://youtu.be/kPyaI--iAcA)
> * [Vídeo en inglés - minuto 15, 36 minutos de duración](https://youtu.be/Hiu_ui2nZa0)

## 1.1 Requisitos

Vamos a necesitar las siguientes MVs:

| Nombre       | IP            | Dominio  | Descripción |
| ------------ | ------------- | --------- | ----------- |
| pp-masterXX  | 172.AA.XX.100 | curso1819 | Es la MV que dará las órdenes de instalación/configuración a los clientes |
| pp-clientXXd | 172.AA.XX.101 | curso1819 | MV Debian que recibe órdenes del master |
| pp-clientXXw | 172.AA.XX.102 |           | MV Windows que recibe órdenes del master, pero con un SO distinto de client1 |


## 1.2 Hostname y dnsdomainname

Una forma de cambiar nombre de host y de dominio:
* Modificar /etc/hostname. Por ejemplo con "vargas42d1.curso1819".
* Añadir a /etc/hosts `127.0.0.2  vargas42d1.curso1819  vargas42d1` .
* Comprobamos con "hostname -a", "hostname -d".
* Otra forma de cambiar el nombre de host y de dominio:

```
hostname vargas42d1.curso1819
systemctl restart hostname     # Reiniciar servicio
hostname                       # Comprobar los nombre de máquina
dnsdomainname                  # Comprobar los nombre de dominio
```

## 1.3 Resolución de nombres (DNS)

Para Debian (por esta vez):
* Desinstalar el paquete `network-manager`.
* Configurar la red y DNS por el fichero `/etc/network/interfaces`.

> Reiniciar el PC. Si el fichero `/etc/resolv.conf` está vacío. Completar con `nameserver 8.8.4.4`.

---

# 2 Instalación y configuración del MASTER

* Vamos a la máquina master.

## 2.1 Configurar servidor DNS

* Modificar /etc/resolv.conf y poner al comienzo:

```
domain curso1819
search curso1819
nameserver 8.8.4.4
```

> **Formas de configurar DNS**
>
> La forma más sencilla de configurar el servidor DNS es añadiendo la siguiente línea `dns-nameservers 8.8.4.4` al "/etc/network/interfaces":
>
> **Opción A: SO con configuración manual**
> * Si usamos SO Debian, Ubuntu Server o similar NO van a tener problemas con la configuración manual del fichero /etc/resolv.conf. Para otros SO's la cosa puede ser diferente.
>
> **Opción B: Adaptar la configuración automática**
> * En el caso de Ubuntu Desktop o Xubuntu Desktop existen dos servicios instalados: resolvconf y dnsmasq. El servicio resolvconf configura automáticamente el fichero /etc/resolv.conf, y machaca cualquier cambio que realicemos de forma manual.
> * El sentido de este servicio es configurar el fichero para establecer el propio equipo como servidor DNS. Entendemos que DNS-caché. Y el servicio local que lo implementa en dnsmasq. Entonces deducimos que para que funcione correctamente dnsmasq, resolvconf configura el sistema.
> * Una opción es modificar la configuración que establece este servicio modificando el fichero /etc/resolvconf/resolv.conf.d/base, de forma adecuada.
>
> **Opción C: Desactivar la configuración automática**
> * Otra posible solución es desactivar el servicio resolvconf (/etc/init/resolvconf.conf )mientras hagamos estas pruebas.

## 2.2 Nombre de máquina

* Cambiar nombre de máquina: `echo "vargas42d1.curso1819" > /etc/hostname`
* Añadir a `/etc/hosts` los nombres de todas las MV's.
```
127.0.0.1   localhost
127.0.0.2   pp-masterXX.curso1819   pp-masterXX
IP-client1  pp-clientXXd.curso1819  pp-clientXXd
IP-client2  pp-clientXXw
```
* Reiniciar el equipo (Sería suficiente con reiniciar el servicio networking).
* Comprobar lo siguiente:
```
hostname                    # nombre-del-master
dnsdomainname               # nombre-del-grupo
ping pp-masterXX.curso1819  # -> Ok
```

---

# 3. Fichero pp VERSION-1

* Instalando y configurando Puppet en el master:
```
    apt-get install puppetmaster
    mkdir /etc/puppet/files
    mkdir /etc/puppet/manifests
    mkdir /etc/puppet/manifests/classes
    touch /etc/puppet/files/readme.txt
    touch /etc/puppet/manifests/site.pp
    touch /etc/puppet/manifests/classes/hostlinux1.pp
```
* Contenido para readme.txt: `"¡Que la fuerza te acompañe!"`
* Contenido para site.pp:
```
import "classes/*"

node default {
  include hostlinux1
}
```

* Contenido para hostlinux1.pp, versión 1. :
```
class hostlinux1 {
  package { 'tree':
    ensure => 'installed',
  }
  package { 'geany':
    ensure => 'installed',
  }

  package { 'traceroute':
    ensure => 'purged',
  }

}
```

> **OJO**
>
> La ruta del fichero es `/etc/puppet/manifests/classes/hostlinux1.pp`.

* Reiniciamos servicio: `systemctl restart puppetmaster`

> * Si el servicio puppetmaster se ejecuta con el usuario puppet, entonces el usuario puppet debería ser propietario de los archivos /etc/puppet/manifests
> * Consultar el fichero de log por si hay errores: `tail /var/log/syslog`

---

# 4. Instalación y configuración del cliente puppet Debian

## 4.1 Preparativos para CLIENT1

* Vamos a la máquina client1. Comprobar que todas las máquinas tienen la fecha/hora correcta.
* Comprobar el nombre de máquina.
* Comprobar IP y DNS.
* Añadir a /etc/hosts todos los equipos de la práctica.
* Reiniciar el servicio de red o el equipo.
* Comprobar lo siguiente:
```
hostname -a
hostname -d
hostname -f
dnsdomainname
ping pp-masterXX
ping pp-clientXXd
```

## 4.2 Instalación del agente

Vamos al cliente.
* `apt install puppet`, instalar y configurar Puppet en el cliente.
* El cliente puppet debe ser informado de quien será su master. Para ello,
añadimos a `/etc/puppet/puppet.conf`:

```
[main]
server=pp-masterXX.curso1819
```

* `systemctl enable puppet`, para que el servicio Pupper se inicie automáticamente al iniciar el equipo.
* `systemctl restart puppet`, reiniciar servicio en el cliente.

> * Comprobar el fichero de log del cliente por si hay problemas `tail /var/log/syslog`.

---

# 5. TEORIA: Ejemplo manual

Vamos al cliente:

* Tenemos instalado `puppet` y se han aceptado los certificados.
* Comprobar que podemos instalar correctamente en el sistema usando el comando `apt`. Hacer una prueba.
* Crear el usuario `yoda` y
* Crear la carpeta `/home/yoda/endor`.

Vamos a averiguar la configuración que lee puppet de estos recursos, y guardamos los datos obtenidos de puppet en el fichero `yoda.pp`. Para ello ejecutamos los comandos siguientes:

* `puppet resource package tree > prueba-manual.pp`, agregar configuración Puppet para instalar el paquete.
* `puppet resource user yoda >> prueba-manual.pp`, agregar configuración Puppet para crear usuario.
* `puppet resource file /home/yoda/endor >> prueba-manual.pp`, agregar configuración Puppet para crear directorio.
* El contenido del fichero `prueba-manual.pp` debe ser parecido a:
```
package { 'tree':
  ensure => 'installed',
}

user { 'yoda':
  ensure => 'installed',
  home => '/home/yoda',
  password => '$6$G09ynAifi7mX$6pag6BIvQWT6iLa8fT4BXdSYfZKdSKOPdBIivyGJpSIxIe5HAKpbt7.jQx20nEev3PabB6HdbqBX37oXrmP6y0',
  shell => '/bin/bash',
}

file { '/home/yoda/endor/':
  ensure => 'directory',
  group => '100',
  mode => '755',
  owner => '1001',
  type => 'directory',
}
```

Si nos lleváramos el fichero `prueba-manual.pp` a otro PC (NO hay que hacerlo) con puppet instalado, podemos forzar a que se creen estos cambios con el comando: `puppet apply prueba-manual.pp`. O también si quitamos del equipo local el paquete, usuario o carpeta podemos ejecutarlo en local y se vuelven a crear.

---

# 6. Aceptar certificado

Antes de que el master acepte a `pp-clientXXd.curso1819`, como cliente, se deben intercambiar los certificados.

## 6.1 Aceptar certificado

* Vamos a la MV master.
* Nos aseguramos de que somos el usuario `root`.
* `puppet cert list`, consultamos las peticiones pendientes de unión al master:
```
root@pp-master42# puppet cert list
"pp-client42g.curso1819" (D8:EC:E4:A2:10:55:00:32:30:F2:88:9D:94:E5:41:D6)
```

> **En caso de no aparecer el certificado en espera**
>
> * Si no aparece el certificado del cliente en la lista de espera del servidor,
quizás el cortafuegos del servidor y/o cliente, está impidiendo el acceso.
> * Volver a reiniciar el servicio en el cliente y comprobar su estado.

* `puppet cert sign "nombre-máquina-cliente"`, aceptar al nuevo cliente desde el master:

```
root@master42# puppet cert sign "cli1alu42.curso1718"
notice: Signed certificate request for cli1alu42.curso1718
notice: Removing file Puppet::SSL::CertificateRequest cli1alu42.curso1718 at '/var/lib/puppet/ssl/ca/requests/cli1alu42.curso1718.pem'

root@master42# puppet cert list

root@master42# puppet cert print cli1alu42.curso1718
Certificate:
Data:
....
```

## 6.2 Comprobación

Vamos a comprobar que las órdenes (manifiesto) del master, llega bien al cliente y éste las ejecuta.
* Vamos a cliente1.
* Reiniciamos la máquina y/o el servicio Puppet.
* Comprobar que los cambios configurados en Puppet se han realizado. Esto es, que el fichero de configuración VERSION-1 a hecho su trabajo.
* Nos aseguramos de que somos el usuario `root`.
* Ejecutar comando para forzar la ejecución del agente puppet:
    * `puppet agent --test`
    * o también `puppet agent --server master42.curso1718 --test`
* En caso de tener errores:
    * Para ver el detalle de los errores, podemos reiniciar el servicio puppet en el cliente, y consultar el archivo de log del cliente: `tail /var/log/puppet/puppet.log`.
    * Puede ser que tengamos algún mensaje de error de configuración del fichero `/etc/puppet/manifests/site.pp` del master. En tal caso, ir a los ficheros del master y corregir los errores de sintaxis.

## 6.3 TEORIA: ¿Cómo eliminar certificados?

**Esto NO HAY QUE HACERLO. Sólo es informativo**

 Sólo es información, para el caso que tengamos que eliminar los certificados. Cuando tenemos problemas con los certificados, o los identificadores de las máquinas han cambiado suele ser buena idea eliminar los certificados y volverlos a generar con la nueva información.

Si tenemos problemas con los certificados, y queremos eliminar los certificados actuales, podemos hacer lo siguiente:
* En el servidor:
    * `puppet cert revoke cli1alu42.curso1718`, revocar certificado del cliente.
    * `puppet cert clean  cli1alu42.curso1718`, eliminar ficheros del certificado del cliente.
    * `puppet cert print --all`, Muestra todos los certificados del servidor. No debe verse el del cliente que queremos eliminar.
* En el cliente:
    *  `rm -rf /var/lib/puppet/ssl`, eliminar los certificados del cliente. Apagamos el cliente.

> Recordatorio:
>
> * `lsof -i -P`, comando para ver los puertos ocupados por los servicios locales.
> * `8140` es el puerto de escucha del servidor Puppet.
> * `chown puppet -R /etc/puppet`, Poner al usuario `puppet` como propietario de los ficheros de puppet.

---

# 7. Fichero pp VERSION-2

Primero hemos probado una configuración sencilla en PuppetMaster.
Ahora podemos pasar a algo más complejo en este apartado.

Contenido para `hostlinux2.pp`, versión 2:

```
class hostlinux2 {
  package { "tree": ensure => 'installed', }
  package { "traceroute": ensure => 'installed', }
  package { "geany": ensure => 'installed', }
  package { "gnomine": ensure => 'purged', }

  group { "jedy": ensure => "present", }
  group { "admin": ensure => "present", }

  user { 'obi-wan':
    home => '/home/obi-wan',
    shell => '/bin/bash',
    password => 'kenobi',
    groups => ['jedy','admin','sudo','root'],
  }

  file { "/home/obi-wan":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 750,
  }

  file { "/home/obi-wan/share":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 750,
  }

  file { "/home/obi-wan/share/private":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 750,
  }

  file { "/home/obi-wan/share/public":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 755,
  }

}
```

> Las órdenes de configuración de puppet significan lo siguiente:
>
> * **package**: indica paquetes que queremos que estén o no en el sistema.
> * **group**: creación o eliminación de grupos.
> * **user**: Creación o eliminación de usuarios.
> * **file**: directorios o ficheros para crear o descargar desde servidor.

Modificar `site.pp` con:

```
import "classes/*"

node default {
  include hostlinux2
}
```

---

# 8. Fichero pp VERSION-3: Cliente puppet windows

* Enlace de interés: [http://docs.puppetlabs.com/windows/writing.html](http://docs.puppetlabs.com/windhttpsows/writing.html)
* En el master vamos a crear una configuración puppet para las máquinas windows, dentro del fichero `/etc/puppet/manifests/classes/hostwindows3.pp`, con el siguiente contenido:

```
class hostwindows3 {
  file {'C:\warning.txt':
    ensure => present,
    content => "Hola Mundo Puppet!",
  }
}
```

* De momento, esta configuración es muy básica. Al final la ampliaremos.
* Ahora vamos a modificar el fichero `site.pp` del master, para que tenga en cuenta la configuración de clientes GNU/Linux y clientes Windows, de la siguiente forma:

```
import "classes/*"

node 'client1.nombredegrupo' {
  include hostlinux2
}

node 'client2' {
  include hostwindows3
}
```

* Reiniciamos el servicio PuppetMaster.
* Localizar el fichero hosts de Windows. Ir a la ruta de la imagen:

![windows-dir-etchosts.png](./images/windows-dir-etchosts.png)

* El contenido del fichero hosts de Windows tiene este aspecto:

![windows-edit-etchosts](./images/windows-edit-etchosts.png)

* Modificar el fichero de la misma forma que hicimos para client1.
* Ir al master y ejecutar el comando `facter`, para ver la versión de Puppet que está usando el master.
* Ahora vamos a instalar puppet en Windows. Consultar URL:
    * [http://docs.puppetlabs.com/windows?/installing.html](http://docs.puppetlabs.com/windows?/installing.html)
    * [https://downloads.puppetlabs.com/windows/](https://downloads.puppetlabs.com/windows/)

> Una vez instalado el AgentePuppet en Windows podemos hacer uso de comandos puppet

* Iniciar consola puppet como administrador y probar los comandos:
    * `puppet agent --server master.nombregrupo --test`: Comprobar el estado del agente puppet.
    * `facter`: Para consultar datos de la máquina windows
    * `puppet resource user profesor`: Para ver la configuración puppet del usuario.
    * `puppet resource file c:\Users`: Para var la configuración puppet de la carpeta.

![puppet-resource-windows](./images/puppet-resource-windows.png)

* Con los comandos anteriores podemos hacernos una idea de como terminar de configurar
el fichero `/etc/puppet/manifests/classes/hostwindows2.pp` del master.

```
class hostwindows2 {
  user { 'darth-sidius':
    ensure => 'present',
    groups => ['Administradores']
  }

  user { 'darth-maul':
    ensure => 'present',
    groups => ['Usuarios']
  }
}
```

---

# ANEXO

## A.1 Alternativas al problema de la incompatibilidad

Como la versión de puppet que estamos usando es incompatible con nuestro sistema operativo podemos hacer:
1. Pasar los pp del master al cliente y ejecutarlos manualmente.
2. Instalar Puppet 6.2 en Debian 9
3. Instalar Puppet 6.2 en OpenSUSE 15
4. Actualizar el Puppet 4.8 del Debian 9
5. Crear Docker Debian 8 con Puppet 4.8.

## A.2 Comandos que han cambiado

Relación de comandos de puppet que han cambiado al cambiar la versión:

|Pre-2.6        | Post-2.6          |
|-------------- |-------------------|
| puppetmasterd | puppet master     |
| puppetd       | puppet agent      |
| puppet        | puppet apply      |
| puppetca      | puppet cert       |
| ralsh         | puppet resource   |
| puppetrun     | puppet kick       |
| puppetqd      | puppet queue      |
| filebucket    | puppet filebucket |
| puppetdoc     | puppet doc        |
| pi            | puppet describe   |

# A.3 Problemas con Debian

cd /etc
mv /etc/puppet /etc/puppet.bak
dpkg -l puppet*
=> Error?!
