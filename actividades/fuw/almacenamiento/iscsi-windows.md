
# iSCSI en Windows Server 2012

Vamos a montar un almacenamiento iSCSI con Windows Server (64 bits).

*(El siguiente texto está copiado de un enlace de Internet)*

```
Para los que no estén familiarizados con iSCSI, digamos que es una manera de “encapsular”
comandos SCSI en paquetes IP. De esta manera podemos acceder a sistemas de almacenamiento externos usando una red IP en lugar de los tradicionales buses SCSI o los canales de fibra. Es decir, una buena forma de montarnos una SAN.

La solución consta de al menos dos componentes. Un iSCSI Initiator y un Target.
* El initiator es lo que utilizamos en el equipo que va a aceder a esos volumenes,
* y el Target es lo que nos permitirá crear el sistema de almacenamiento compartido,
y el que permitira el acceso a las LUNs que se hayan creado a cada cliente específico.

Generalmente esta tecnología está ya incluida en el propio hardware de los servidores
y de los sistemas SAN, que ofrecen este tipo de conectividad a través de dispositivos multifunción.
Sin embargo esto no excluye que un iniciador software se pueda conectar a un Target hardware o viceversa.

El iSCSI initiator puede descargarse gratuitamente, para Windows XP y Windows server 2003. En Windows Vista y Windows Server 2008 viene ya incluido por defecto. Los iniciadores software son muy útiles en entornos virtualizados, ya que permiten a las máquinas virtuales el acceso a sistemas de tipo SAN mediante tarjetas de red, generalmente dedicadas en el host y en el guest.
```

---

# 1 Preparativos

La MV target es la encargada de ofrecer espacio de almacenamiento, y la MV Initiator será la que
consumirá el espacio de almacenamiento.

Necesitamos 2 MV's con Windows Server (Consultar [configuraciones](../../global/configuracion/windows-server.md)).
* MV1: Esta MV actuará de `Initiator`.
    * Con dos interfaces de red.
    * Una en modo puente (172.19.XX.21)
    * la otra en red interna (192.168.XX.21) con nombre `san`.
        * Esta interfaz NO tiene gateway.
* MV2: Esta MV actuará de `Target`.
    * Necesitamos Windows Server 64 bits para poder instalar el software de Target.
    * Con un interfaz de red (192.168.XX.22) en modo red interna `san`.
    * Añadir un segundo disco de 800 MB a la MV de VirtualBox.
* Las IP's las pondremos todas estáticas.
* Las IP's de la red interna estarán en el rango 192.168.XX.NN/24.
Donde XX será el número correspondiente al puesto de cada alumno.

---

# 2 Enlaces de interés

Enlaces de interés orientados a Windows Server 2008:
* Vídeo de referencia [ES - Crear y conectar recursos iSCSI](https://youtu.be/_77UL2kZEEA).
* Cómo usar un TARGET hardware - [How to use iSCSI target on Windows 2008 server](https://www.synology.com/en-global/knowledgebase/DSM/tutorial/Virtualization/How_to_use_iSCSI_Targets_on_a_Windows_Server)
* INITIATOR - [Guía paso a paso del iniciador Windows](https://technet.microsoft.com/es-es/library/ee338476%28v=ws.10%29.aspx)

---

# 3. Iniciador iSCSI para WS2012

Las máquinas que intervienen en iSCSI usan un identificador llamado IQN. Al instalar el sistema
operativo se pone un valor por defecto para el identificador IQN. Nosotros vamos a personalizar estos valores.

Vamos a cambiar el identificador IQN de nuestro iniciador.
* Ir a MV iniciador.
* `Herramientas -> iSCSI Iniciador -> Identificador`
* Poner como IQN lo siguiente: `iqn.2018-05.initiatorXXw`. Donde XX es el código del alumno.

> IMPORTANTE: El iniciador tiene 2 IP's, pero se comunica con el target usando el interfaz de red de la red interna.

---

# 4. Target iSCSI para WS2012

Vamos a cambiar el identificador IQN de nuestro target.
* Ir a MV Target.
* `Herramientas -> iSCSI Iniciador -> Identificador`. ¡OJO! Estamos en la MV Target.
* Poner como IQN lo siguiente: `iqn.2018-05.targetXXw:test`. Donde XX es el código del alumno.

Instalación:
* Se instala el software iSCSI por `Agregar roles/funciones`.
    * Agregar el rol `Servidor de Destino iSCSI` (iSCSI Target Server).
    * El rol se encuentra en `Almacenamiento -> Servicio iSCSI -> Servidor de Destino iSCSI`.

Configuración:
* Disco virtual -> Se guardará en el disco E:
* Nombre de disco virtual
    * Nombre: `alumnoXXdisco01`
    * Descripción: `Disco01 - nombre del alumno y la fecha de hoy`
    * Ruta `(valor por defecto)`
* Tamaño: 600 MB

Los destinos (según las definiciones del protocolo iSCSI) es una definición de un espacio de almacenamiento concreto.
* Destino -> Nuevo
    * Nombre: `alumnoXXdestino01`
    * Descripción: `Destino01 - nombre del alumno y la fecha de hoy`
* Servidor de Acceso
    * IQN iniciador iSCSI
* Servicio de autenticación: `NO HABILITAR`
* Capturar imagen del resumen final de la configuración.

# 5. Configurar Iniciador para WS2012

* Ir a MV Iniciador
* Ir a `Iniciador iSCSI -> destino`
* Poner IP de la MV target.
* Nos aseguramos que el destino está conectado.
* Ir a `Herramientas -> Administrador de Equipos -> Almacenamiento -> Discos`.
    * Comprobar que tenemos un nuevo disco de 600 MB.
* Inicializar el disco, formatearlo y montarlo usando la letra F.
Ya tenemos el nuevo almacenamiento disponible en el Iniciador.
* Guardar datos en el nuevo disco.

# Preguntas

* ¿Podemos aumentar el espacio del disco virtual en el target?

---

# ANEXO

Pendiente para revisar:
* Comprobar los datos, montando el disco virtual en el servidor target.
* Modificar IQN del target.
* Añadir un segundo disco al destino 1.
* Añadir un nuevo destino.
