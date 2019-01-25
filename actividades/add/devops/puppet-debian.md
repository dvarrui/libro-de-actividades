_(Usada en los cursos 201415, 201314, 201819)_

# 1. Introducción

Existen varias herramientas para realizar instalaciones desde un punto central, 
como Chef, Ansible, CFEngine, etc. En este ejemplo, vamos a usar Puppet.

Según Wikipedia, Puppet es una herramienta diseñada para administrar la configuración 
de sistemas Unix-like y de Microsoft Windows de forma declarativa. El usuario describe 
los recursos del sistema y sus estados, ya sea utilizando el lenguaje declarativo de 
Puppet o un DSL (lenguaje específico del dominio) de Ruby.

Enlaces de interés:

* [Puppetcookbook](http://www.puppetcookbook.com/posts/show-resources-with-ralsh.html)
* [Vídeo sin audio - 14 minutos de duración](https://youtu.be/kPyaI--iAcA)
* [Vídeo en inglés - minuto 15, 36 minutos de duración](https://youtu.be/Hiu_ui2nZa0)


## 1.1 Requisitos

Vamos a necesitar las siguientes MVs:

| Nombre       | IP            | Dominio  | Descripción |
| ------------ | ------------- | --------- | ----------- |
| pp-masterXX  | 172.18.XX.100 | curso1819 | Es la MV que dará las órdenes de instalación/configuración a los clientes |
| pp-clientXXd | 172.18.XX.101 | curso1819 | MV Debian que recibe órdenes del master |
| pp-clientXXw | 172.18.XX.102 1 curso1819 | MV Windows que recibe órdenes del master, pero con un SO distinto de client1 |

## 1.2 Servidor DNS y el fichero /etc/resolv.conf

La forma más sencilla de configurar el servidor DNS es añadiendo la siguiente 
línea al fichero /etc/network/interfaces: `dns-nameservers 172.16.1.1`

> **Formas de configurar DNS**
>
> * Opción A: SO con configuración manual
>    * Si usamos SO Debian, Ubuntu Server o similar NO van a tener problemas con la configuración manual del fichero /etc/resolv.conf. Para otros SO's la cosa puede ser diferente.
> * Opción B: Adaptar la configuración automática
>    * En el caso de Ubuntu Desktop o Xubuntu Desktop existen dos servicios instalados: resolvconf y dnsmasq. El servicio resolvconf configura automáticamente el fichero /etc/resolv.conf, y machaca cualquier cambio que realicemos de forma manual.
>    * El sentido de este servicio es configurar el fichero para establecer el propio equipo como servidor DNS. Entendemos que DNS-caché. Y el servicio local que lo implementa en dnsmasq. Entonces deducimos que para que funcione correctamente dnsmasq, resolvconf configura el sistema.
>    * Una opción es modificar la configuración que establece este servicio modificando el fichero /etc/resolvconf/resolv.conf.d/base, de forma adecuada.
> * Opción C: Desactivar la configuración automática
>    * Otra posible solución es desactivar el servicio resolvconf (/etc/init/resolvconf.conf )mientras hagamos estas pruebas.

## 1.3 Hostname y dnsdomainname

* Una forma de cambiar nombre de host y de dominio:
    * Modificar /etc/hostname. Por ejemplo con "marte.starwars".
    * Añadir a /etc/hosts "127.0.0.2 marte.starwars marte".
    * Comprobamos con "hostname -a", "hostname -d".
    * Otra forma de cambiar el nombre de host y de dominio:

```
    hostname marte.starwars
    /etc/init.d/hostname restart
    service hostname restart
```

* **IMPORTANTE**: Comprobar los nombres.

```
    hostname
    dnsdomainname
```

---

# 2 Instalación y configuración del MASTER

Preparativos para el MASTER:

* Vamos a la máquina master.
* Cambiar nombre de máquina: `echo "master.nombregrupo" > /etc/hostname`
* Modificar /etc/resolv.conf y poner al comienzo:

```
    domain nombregrupo
    search nombregrupo
    ...
```

* Añadir a `/etc/hosts` los nombres de todas las MV's.
```
    127.0.0.1  localhost
    127.0.1.1  master.nombregrupo  master
    IP-master  master.nombregrupo  master
    IP-client1 client1.nombregrupo client1
    IP-client2 client2.nombregrupo client2
    ...
```
* Reiniciar el equipo (Sería suficiente con reiniciar el servicio networking).
* Comprobar los siguiente:
    * `hostname` -> nombre-del-master
    * `dnsdomainname` -> nombre-del-grupo
    * `ping master.nombregrupo` -> Ok.

## 2.1 Ejemplo manual

Al instalar puppetmaster en la máquina master, también tenemos instalado "puppet" (Agente puppet).
Nuestro objetivo es usar puppet para conseguir lo siguiente:
* Instalar el paquete `tree`.
* Crear el usuario `yoda` y 
* Crear la carpeta `/home/yoda/endor`.

Vamos a averiguar la configuración que lee puppet de estos recursos, y guardamos los datos
obtenidos de puppet en el fichero `yoda.pp`. Para ello ejecutamos los comandos siguientes:
```
    puppet resource package tree > yoda.pp
    puppet resource user yoda >> yoda.pp
    puppet resource file /home/yoda/endor >> yoda.pp
```
El contenido del fichero `yoda.pp` debe ser parecido a:
```
package { 'tree':
  ensure => 'present',
}

user { 'yoda':
  ensure => 'present',
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

Si nos lleváramos el fichero `yoda.pp` a otro PC con puppet instalado, 
podemos forzar a que se creen estos cambios con el comando: `puppet apply yoda.pp`

## 2.2 Primera versión del fichero pp

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
  package { "tree": ensure => installed }
  package { "traceroute": ensure => installed }
  package { "geany": ensure => installed }
}
```

> **OJO** 
>
> La ruta del fichero es `/etc/puppet/manifests/classes/hostlinux1.pp`.

* Reiniciamos servicio: `systemctl restart puppetmaster`
* Consultamos log por si hay errores: `tail /var/log/syslog`

---

# 3. Instalación y configuración del cliente puppet Debian

## 3.1 Preparativos para CLIENT1

* Vamos a la máquina client1. Comprobar que todas las máquinas tienen la fecha/hora correcta.
* Cambiar nombre de máquina: `echo "client1.nombregrupo" > /etc/hostname`
* Modificar /etc/resolv.conf y poner al comienzo:
```
    domain nombregrupo
    search nombregrupo
```
* Añadir a /etc/hosts
```
    127.0.0.1 localhost
    127.0.1.1  client1.nombregrupo client1
    IP-master  master.nombregrupo  master
    IP-client1 client1.nombregrupo client1
```
* Reiniciar el equipo (Sería suficiente con reiniciar el servicio networking).
* Comprobar lo siguiente:
   * El comando hostname -> client1
   * El comando dnsdomainname -> nombredegrupo
   * ping a client1.nombregrupo debe funcionar.

## 3.2 Instalación del agente

* Instalando y configurando Puppet en el cliente: `apt-get install puppet`
* El cliente puppet debe ser informado de quien será su master. Para ello, 
añadimos a `/etc/puppet/puppet.conf`:
```
    [main]
    server=master.nombregrupo
```
* Para que el servicio Pupper se inicie automáticamente al iniciar el equipo, 
editar el archivo `/etc/default/puppet`, y modificar la línea

```
    #start puppet on boot
    START=yes
```

* Reiniciar servicio en el cliente: `systemctl restart puppet`
* Comprobamos los log del cliente: `tail /var/log/syslog`

---

# 4. Aceptar certificado

Antes de que el master acepte a `client1.nombredegrupo`, como cliente, se deben intercambiar los certificados.

## 4.1 Aceptar certificado

* Vamos al master y consultamos las peticiones pendiente de unión al master:
```
    root@master# puppetca --list
    "client1.nombregrupo" (D8:EC:E4:A2:10:55:00:32:30:F2:88:9D:94:E5:41:D6)
    root@master#
```
* Aceptar al nuevo cliente desde el master:
```
    root@master# puppetca --sign "client1.nombregrupo"
    notice: Signed certificate request for client1.nombregrupo
    notice: Removing file Puppet::SSL::CertificateRequest client1.nombregrupo at '/var/lib/puppet/ssl/ca/requests/client1.nombregrupo.pem'
    root@master# puppetca --list
    root@master# puppetca --print client1.nombregrupo
    Certificate:
    Data:
    ....
```

## 4.2 Comprobación final

* Vamos a client1
* Reiniciamos la máquina.
* Comprobar que los cambios configurados en Puppet se han realizado.
* En caso contrario, ejecutar comando para comprobar errores: `puppet agent --server master.nombregrupo --test`
* Para ver el detalle de los errores, podemos reiniciar el servicio puppet en el cliente, y 
consultar el archivo de log del cliente: `tail /var/log/syslog`.
* Puede ser que tengamos algún mensaje de error de configuración del fichero manifiests del master. 
En tal caso, ir a los ficheros del master y corregir los errrores de sintaxis.

> **¿Cómo eliminar certificados?**
>
> *Esto NO HAY QUE HACERLO*. Sólo es información, para el caso que tengamos que eliminar los certificados
> 
> Si tenemos problemas con los certificados, y queremos eliminar los certificados actuales, podemos hacer lo siguiente:
> * `puppetca --revoke client1.nombregrupo`: Lo ejecutamos en el master para revocar certificado del cliente.
> * `puppetca --clean client1.nombregrupo`: Lo ejecutamos en el master para eliminar ficheros del certificado del cliente.
> *  `rm -rf /var/lib/puppet/ssl`: Lo ejecutamos en el cliente para eliminar los certificados del cliente.
>
> Consultar [URL https://wiki.tegnix.com/wiki/Puppet](https://wiki.tegnix.com/wiki/Puppet), para más información.

---

# 5. Segunda versión del fichero pp

Primero hemos probado una configuración sencilla en PuppetMaster. 
Ahora podemos pasar a algo más complejo en este apartado.

Contenido para `hostlinux2.pp`, versión 2:

```
class hostlinux2 {
  package { "tree": ensure => installed }
  package { "traceroute": ensure => installed }
  package { "geany": ensure => installed }
  package { "gnomine": ensure => purged }

  group { "jedy": ensure => "present", }
  group { "admin": ensure => "present", }

  user { 'obi-wan':
    home => '/home/obi-wan',
    shell => '/bin/bash',
    password => 'kenobi',
    groups => ['jedy','admin','sudo','root'] 
  }

  file { "/home/obi-wan":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 750 
  }

  file { "/home/obi-wan/share":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 750 
  }

  file { "/home/obi-wan/share/private":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 750 
  }

  file { "/home/obi-wan/share/public":
    ensure => "directory",
    owner => "obi-wan",
    group => "jedy",
    mode => 755 
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

# 6. Cliente puppet windows

* Enlace de interés: [http://docs.puppetlabs.com/windows/writing.html](http://docs.puppetlabs.com/windows/writing.html)
* En el master vamos a crear una configuración puppet para las máquinas windows, 
dentro del fichero `/etc/puppet/manifests/classes/hostwindows1.pp`, con el siguiente contenido:

```
class hostwindows1 {
  file {'C:\warning.txt':
    ensure => present,
    content => "Hola Mundo Puppet!",
  }
}
```

* De momento, esta configuración es muy básica. Al final la ampliaremos.
* Ahora vamos a modificar el fichero `site.pp` del master, para que tenga en cuenta
la configuración de clientes GNU/Linux y clientes Windows, de la siguiente forma:

```
import "classes/*"

node 'client1.nombredegrupo' {
  include hostlinux2
}

node 'client2' {
  include hostwindows1
}
```

* Reiniciamos el servicio PuppetMaster.
* Localizar el fichero hosts de Windows`. Ir a la ruta de la imagen:

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
