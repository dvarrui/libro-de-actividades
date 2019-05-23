
```
Curso          : 201819, 201718
Software       : SO OpenSUSE
Tiempo estimado: 7 horas
```
---

# iSCSI en OpenSUSE

Vamos a montar un iSCSI sin autenticación, usando dos máquinas GNU/Linux OpenSUSE.

---

# 1 Preparativos

Vamos a montar la práctica de iSCSI con GNU/Linux OpenSUSE.

Necesitamos 2 MV's (Consultar [configuraciones](../../global/configuracion/opensuse.md)).
* MV1: Actuará de `Initiator`.
    * SSOO OpenSUSE Leap
    * Hostname `initiatorXXg`.
    * Con dos interfaces de red.
    * Una en modo puente (172.AA.XX.31)
    * y la otra en red interna (192.168.XX.31) con nombre `san`. Este interfaz NO tiene gateway.
* MV2: Actuará de `Target`.
    * SSOO OpenSUSE Leap
    * Hostname `targetXXg`.
    * Con un interfaz de red (192.168.XX.32) en modo red interna `san`.
    * Este interfaz tiene como gateway a 192.168.XX.31.
* Las IP's las pondremos todas estáticas.
* Las IP's de la red interna estarán en el rango 192.168.XX.NN/24.
Donde XX será el número correspondiente al puesto de cada alumno.

**Acceso a Internet desde el Target**

Como vamos a necesitar acceso a los repositorios de Internet en el Target
para instalar el software, podemos hacerlo de varias formas:
* (a) Poner temporalmente un 2º interfaz puente y DHCP para instalar y luego lo desactivamos. Es sencillo y rápido para nuestro entorno de aprendizaje.
* (b) Poner el interfaz de red temporalmente en puente y DHCP, instalar y cambiar.
* (c) Activar/configurar enrutamiento y NAT en el Initiator. Esto es más complejo, pero más profesional.

---

# 2 Un poco de teoría

La configuración del Target contiene:
* El nombre de nuestro target
* El nombre de usuario y la contraseña para la conexión del iniciador
* El dispositivo que ofreceremos como target

### 2.1 Nombre

El estándar iSCSI define que tanto los target como los iniciadores deben
tener un nombre (identificador iqn) que sigue el siguiente patrón: `iqn.YYYY-MM.NOMBRE-DEL_DOMINIO_INVERTIDO:IDENTIFICADOR`.
Donde:
* `iqn` es un término fijo y debe figurar al principio.
* `YYYY-MM` es la fecha de alta del dominio de la organización para la que estamos
configurando el equipo.
* A continuación debe figurar el nombre del dominio invertido.
* Después de ":", un identificador del almacenamiento, que podemos ponerlo a nuestro gusto, y que puede en muchos casos brindar información del target.

Ejemlos válidos serían: `iqn.2005-02.au.com.empresa:san.200G.samba`, `iqn.2017-05.curso1617.target42:test`.

Como vemos el identificador aunque es variable y personalizable, puede
reflejar el nombre dado al target, la capacidad y el servicio donde lo usaremos.

### 2.2 Autenticación

Si queremos que nuestro target requiera autenticación, podemos definir
un usuario y una contraseña para que solo se conecten los iniciadores que nosotros queremos.
Ejemplo: `IncomingUser usuario-iniciador clave-iniciador`

Hay 3 tipos de autenticación:
* Sin autenticación
* Autenticación de entrada y
* Autenticación de salida

### 2.3 Dispositivos/Destinos

Luego debemos definir qué dispositivo ofreceremos como target.
Debemos poner una línea como la siguiente: `Lun 0 Path=/dev/sda3,Type=fileio`

En este ejemplo el primer dispositivo que estamos ofreciendo es la
partición /dev/sda3 del servidor. La documentación nos dice que además
de particiones podemos usar discos enteros, volúmenes LVM y RAID,
e incluso archivos. En cualquier caso hay que definirlo en el path.

El archivo contiene muchos parámetros más de configuración,
que en la mayoría de los casos tienen que ver con la performance del servidor.
En nuestro ejemplo, configurando estos tres parámetros nos bastaría.

---

## 3 Práctica: Initiator

Vamos al equipo que será nuestro iniciador:
* Por entorno gráfico, `Yast -> Iniciador SCSI`
* Modificamos el identificador iqn del Initiator con
`iqn.2017-05.initiatorXXg`.
* Comprobamos por comandos, `more /etc/iscsi/initiatorname.iscsi`

Veamos imagen de ejemplo en Yast:
![iscsi-opensuse-initiator-iqn.png](files/iscsi-opensuse-initiator-iqn.png)

---

# 4 Práctica: configuración del Target

## 4.1 Enlaces de interés

Enlaces recomendados:
* [OpenSUSE - tutorial iSCSI Target usando comandos](http://es.opensuse.org/iSCSI)
* [OpenSUSE - iSCSI Target documentation ](https://www.suse.com/documentation/sles11/stor_admin/data/sec_inst_system_iscsi_target.html)

Otros enlaces de interés:
* INITIATOR - [Setting up iSCSI initiator on OpenSUSE](https://www.suse.com/documentation/sles11/stor_admin/data/sec_inst_system_iscsi_initiator.html)
* Vídeo: [EN - LINUX: ISCSI Target and Initiator Command Line configuration](https://youtu.be/5yMSxqUs4ys)
* Vídeo: [Linux Configure an iSCSI Target](https://www.youtube.com/watch?v=cWPY3lH3qTQ)
* Vídeo: [Linux Configure iSCSI Initiator ( client ) ](https://www.youtube.com/watch?v=8UojNONhQDo)
* Vídeo: [EN - Configure iSCSI initiator (client)](https://youtu.be/8UojNONhQDo)

## 4.2 Crear los dispositivos

Crear los dispositivos en el equipo target.
* Creamos el `dispositivo1` a partir de un fichero.
    * `dd if=/dev/zero of=/home/nombre-alumnoXXdisco01.img bs=1M count=500`
    * Hemos creado un fichero con tamaño 500M.
    * `du -sh /home/nombre-alumnoXXdisco01.img`, lo comprobamos.
* Creamos el `dispositivo2` a partir de un disco extra.
    * Añadiremos un 2º disco de 700M a la MV Target.
    * `/dev/sdb` será nuestro dispositivo2.

## 4.3 Instalar y configurar el Target

* Vamos a la máquina target.
* `zypper in yast2-iscsi-lio-server`, instala el software para crear un Target iSCSI y sus dependencias.
* Ir a `Yast -> Objetivo LIO iSCSI`, para configurar el Target.
* Inicio del Servicio
    * Durante el arranque = Sí
    * Abrir el cortafuegos = Sí
* Global
    * Sin autenticación
* Destinos(Dispositivos)
    * Nombre `iqn.2017-05.targetXXg`.
    * Identificador `test`
    * Seleccionar los LUN (dispositivos creados anteriormente)
        * `Lun 0 Path=/home/nombre-alumnoXXdisco01.img,Type=fileio`
        * `Lun 1 Path=/dev/sdb,Type=fileio` (Escribir la ruta del dispositivo)
    * Utilizar autenticación => NO

![iscsi-opensuse-target-destino.png](files/iscsi-opensuse-target-destino.png)

> El target tiene un identificador iqn y el iniciador tendrá otro iqn diferente.
> En el target hay que habilitar permiso de acceso al iqn del Iniciador.

* Pulsamos siguiente y vamos a configurar los permisos para el iniciador -> Añadir -> Ponemos el identificador de nuestro iniciador.

![iscsi-opensuse-target-initiator.png](files/iscsi-opensuse-target-initiator.png)

* Comprobamos los LUN's(dispositivos) disponibles.

![iscsi-opensuse-target-lun.png](files/iscsi-opensuse-target-lun.png)

* Siguiente -> Terminar.

![iscsi-opensuse-target-finalconfig.png](files/iscsi-opensuse-target-finalconfig.png)

## 4.4 Comprobamos

Para activar todos los cambios hay que reiniciar el servidor Target iSCSI.

* `systemctl start target.service`, inicia el servicio Target manualmente.
* `systemctl status target.service`, comprueba el estado del servicio Target.
* `systemctl enable target.service`, habilita el servicio Target para que se inicie automáticamente con cada reinicio.

Ya tenemos nuestro servidor Target iSCSI instalado. Ahora necesitamos un iniciador iSCSI para que se conecte a nuestro target y empezar a usar el almacenamiento.

---

# 5 Initiator

Enlaces recomendados:
* [OpenSUSE - tutorail iSCSI Initiator con comandos](http://es.opensuse.org/iSCSI)
* [OpenSUSE - iSCSI Initiador documentation](https://www.suse.com/documentation/sles11/stor_admin/data/sec_inst_system_iscsi_initiator.html)

## 5.1 Instalar y configurar acceso

Vamos a la máquina Iniciador.
* El software necesario viene preinstalado en OpenSUSE Leap:
    *  Si tenemos que hacer la instalación ejecutar `zypper in open-iscsi yast2-iscsi-client`.

## 5.2 Descubrir

* `Yast -> configurar Initiator -> Descubrir`, para descubrir los destinos de targets disponibles.
    * Debemos especificar la IP del equipo target donde queremos descubrir los destinos disponibles.
    * Puerto 3260
    * Sin autenticación.

![iscsi-opensuse-initiator-descubrir.png](files/iscsi-opensuse-initiator-descubrir.png)

> Otra forma de descubrir target es usando el siguiente comando por la consola:
>
> * `iscsiadm -m discovery -t sendtargets -p IP-DEL-TARGET`
> * `iscsiadm -m discovery -t st -p IP-DEL-TARGET`
>
> El target ofrece su servicio por defecto en el puerto 3260.
> * `iscsiadm -m discovery`, para descubrir los puertos de trabajo del Target.

## 5.3 Conectar

* `Yast -> configurar Initiator -> Conectar` para conectar con el destino que hemos descubierto.
* Elegimos:
   * Inicio en el arranque
   * Sin autenticación.

![iscsi-opensuse-initiator-conectado.png](files/iscsi-opensuse-initiator-conectado.png)

> Otra forma de conectar con el destino del Target vía comandos:
>
> * `iscsiadm -m node --targetname iqn.2017-05.targetXXg:test -p IP-TARGET --login`,
conectar un target concreto.
> * `iscsiadm -m node -l`, conectar con todos los targets, usando una configuración básica sin autenticación.

* Si hacemos `fdisk -l`, veremos que nos aparece dos nuevos discos en el equipo iniciador.

## 5.4 Usar almacenamiento

Vamos a equipo Iniciador:
* `lsscsi`, encontrar la ruta del dispositivo local para el dispositivo Target iSCSI.

![iscsi-opensuse-initiator-lsscsi.png](files/iscsi-opensuse-initiator-lsscsi.png)

* Crear directorio `/mnt/remote_targetXXsdb`.
* Crear directorio `/mnt/remote_targetXXsdc`.
* `Yast -> Particionador`, elegir el disco.
    * Crear partición y formatear el disco sdb.
    * Editar -> Montar -> Punto de montaje -> `/mnt/remote_targetXXsdb`.
    * Crear partición y formatear el disco sdc.
    * Editar -> Montar -> Punto de montaje -> `/mnt/remote_targetXXsdc`.
* Guardar datos en el disco SAN.

> IDEAS para el futuro
>
> * Crear en el target un destino (test2) con un lun0 que sea un volumen lógico (lvm)
> * Conectar destinos de un target Windows con un iniciador GNU/Linux y viceversa.
> * Hacer configuraciones usando las autenticaciones de entrada y salida.

---

# ANEXO

## A.1 Otros enlaces de interés Debian

Enlaces de interés:
* [federicosayd - ISCSI Target en GNU/Linux Debian](https://federicosayd.wordpress.com/2007/09/11/instalando-un-target-iscsi/)
* iSCSI - [Using iSCSI (target and initiator) on Debian](https://www.howtoforge.com/using-iscsi-on-debian-lenny-initiator-and-target).
* TARGET - [Create targer iSCSI on Debian](https://wiki.debian.org/SAN/iSCSI/iscsitarget).

## A.2 Enrutamiento en GNU/Linux

* [Enrutamiento en GNU/Linux](http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m6/enrutamiento_en_linux.html)
*  Ejemplo de script que activa el enrutamiento y el NAT:

```
// activar-enrutamiento.sh
echo "1" > /proc/sys/net/ipv4/ip_forward
iptables -A FORWARD -j ACCEPT
iptables -t nat -A POSTROUTING -s IP_RED_INTERNA/MASCARA_RED_INTERNA -o eth0 -j MASQUERADE
```

*  Ejemplo de script que desactivara el enrutamiento:

```
// desactivar-enrutamiento.sh
echo "0" > /proc/sys/net/ipv4/ip_forward
```
