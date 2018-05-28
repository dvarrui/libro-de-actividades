
# 1. Servidor de actualizaciones WSUS

## 1.1 Teoría

El mantenimiento de las actualizaciones de software es una necesidad constante.
Si no mantienen los sistemas actualizados:
* Un atacante puede aprovechar las vulnerabilidades.
* Aumenta el tiempo de inactividad de los PC's comprometidos.
* La información de la empresa puede verse comprometida.

El servidor WSUS almacena y distribuye las actualizaciones de software MS,
consiguiendo:
* Reducción del consumo del ancho de banda WAN.
* Mejora de los tiempos de actualización global.
* Mayor control de las actualizaciones que se desean.

Los servidores de instalación son la solución para ayudar a distribuir las actualizaciones
Funcionamiento de WSUS:
* Servidor pone las actualizaciones disponibles a los clientes.
* El administrador del servidor aprueba y prioriza las actualizaciones.
* El cliente se conecta a WSUS e instala los paquetes usando Windows update.

> **Resumen:**
>
> * WSUS proporciona control a la hora de aprobar y distribuir actualizaciones
de Microsoft entre sus ordenadores clientes.
> * Un servidor WSUS puede copiar actualizaciones desde Microsoft y almacenarlas
localmente. Entonces, los ordenadores clientes descargarán las actualizaciones
desde su servidor WSUS en vez de descargarlas desde Microsoft a través de Internet.
> * Para soportar organizaciones con muchas oficinas, los servidores WSUS de descarga
pueden sincronizar actualizaciones, aprobaciones y opciones de configuración de
los servidores WSUS de subida.
> * WSUS también requiere IIS.

## 1.2 Preparativos

Vamos a necesitar varias MVs.
* 1 MV Window Server 2012 (No es necesario que sea PDC). [Configurar](../../../global/configuracion/windows-server.md) la MV's como se indica.
* 1 MV Windows 7. [Configurar](../../../global/configuracion/windows.md) las MV como se indica.

---

# 2. Servidor WSUS

Vamos la MV con Windows Server.

## 2.1 Teoría

Tener en cuenta que:
* NO es necesario tener un Active Directory para montar el servicio WSUS.
* Necesitamos 1 servidor WSUS por cada 10 PC's.
* Servidor WSUS se conecta vía HTTP/HTTPS con el exterior.
* IIS en WSUS para que se conecten los clientes.
* Se requiere al menos 6GB de disco duro parfa almacenar las actualizaciones.
* Tendremos que hacer copias de seguridad de la BBDD de WSUS periódicamente.
* En caso de fallo disponemos de 1 semana para reemplazar el servidor.

Consideraciones a tener en cuenta a la hora de configurar servidor:

* Podremos usar como fuente origen de las actualizaciones a los servidor
 de Microsoft u otros servidores WSUS.
* La base de datos que guarda la información, la podemos tener en
C:\WSUS\UpdateServicesDbFiles\SUSDB.mdf o montar un MSQL-Server.
* En nuestro caso lo vamos a hacer SIN MSQL-Server.

## 2.2 Instalación del servidor

Enlace de interés:
* [Instalación y puesta en marcha Servidor de actualizaciones (WSUS I)](http://cerowarnings.blogspot.com.es/2011/11/servidor-de-actualizaciones-wsus.html)
* [Instalación y configuración de WSUS - Parte 1](https://hackpuntes.com/wsus-windows-server-update-services-instalacion-y-configuracion-parte-i/)
* [Vídeo WSUS en Windows 2012 Server R2](https://www.youtube.com/watch?v=2YPtfrwVObg)

La forma más sencilla para instalar el software WSUS es usar la propia
herramienta de Administrar del servidor Windows Server.
* Asegurarse de que tenemos la instalación estado `No ilegal`.
* `Administrar el servidor -> Roles -> Agregar Roles -> WSUS`
    * Capturar imagen de las opciones que vayamos eligiendo.

## 2.3 Configuración del servidor

Vamos a configurar servidor con:
* Fuente: `Microsoft`.
* Base de datos:
    * WS2012: Almacenar en `C:\Actualizaciones`
    * WS2008: Usar como base de datos `C:\WSUS\UpdateServicesDbFiles\SUSDB.mdf`.
* Para minimizar descargas es conveniente hacer una selección de idiomas
y de productos concretos para actualizar. Elegir:
    * Idioma: `Español`
    * Producto: `Windows 7`
    * Tipo de actualización: `Actualizaciones críticas, de seguridad, etc`.
    * `Sincronización manual`.

> **Sincronización Manual vs Automática**
>
> Elegimos manual para hacer nuestras pruebas en clase.
> Cuando estemos en la empresa elegiremos sincronización automática.

Aprobar algunas de las actualizaciones del Windows 7, en el servidor WSUS.
* Ir a `Administrador del servidor -> Windows Server -> Update Services -> Actualizaciones`, seleccionar las actualizaciones críticas y aprobarlas.
* `Windows Server -> Update Services -> Sincronizar`, para conectar con los
servidor de Microsoft y comenzar la descarga de los paquetes aprobados.

## 2.4 Servicio con inicio automático

* Asegurarnos en `Administración -> Servicios` que el servicio WSUS tiene
configurado inicio automático al arrancar la máquina.

---

# 3. Cliente WSUS

Vamos a una MV Windows 7.

## 3.1 Teoría

Tener en cuenta que:
* Sistemas operativos clientes son: W2K, WXP, WVista, WS2K3 y WS2K8.
* Los clientes se conectan vía HTTP con el servidor WSUS.
* Cliente Windows Update usa la firma digital y HASH (SHA1) para comprobar
la autenticidad. En WXP y W2K se usa "cliente de actualizaciones automáticas".
* Se pueden usar directivas de grupo para distribuir la configuración de
la organización ( `Conf Eq. > Direct > Plant. Admin. > Componen > Win Update`)

Parámetros de configuración en el cliente:
* Máquina Servidor WSUS
* Frecuencia, notificaciones, grupo asignado.
* Reinicio automático / confirmación / retrasar
* Administración de energía: Iniciar PC si está apagado para ejecutar actualización.

## 3.2 Configurar el cliente

Vamos a una MV con Windows 7 como cliente WSUS.
Tenemos dos métodos para configurar. Elegir sin PDC.

### Windows Update de sólo descarga

Configurar el Windows Update en el cliente, para hacer las descargas de las
nuevas actualizaciones automáticamente, pero dejando elegir al usuario el momento
de instalarlas.

### Configurar sin PDC (Recomendado)

Vamos a configurar Windows Update de cada cliente de forma local. Para ello
tenemos dos formas:

**Forma 1: Política de Grupo (Local)**
* En `especificar la ubicación del servidor` hay que poner la IP del WSUS.
* Enlaces de interés:
    * [Configuración cliente para WSUS con o sin Active Directory](http://cosiis.com/blog/archives/69)
    * [Configurar Windows7 para WSUS](http://soporte.fen.uchile.cl/mw/index.php/WSUS_para_Windows_7)

**Forma 2: Registro de Windows**
* Esta información está probada en Windows XP, pero no es Windows7.
* Antes de ejecutar este proceso hacer una copia de seguridad del registro o bien hacer una instantánea de la MV.
* Enlace de interés [Script para modificar el registro de Windows](http://servidorespararedes.blogspot.com.es/2008/10/configuracion-de-cliente-wsus-por.html)

> **INFO: Configurar con PDC**
>
> * Vamos a asignar el servidor WSUS a los equipos del dominio mediante directivas de grupo.
> * Política de Grupo (AD DS). Enlace de interés [Configuración de clientes y aprobación de actualizaciones WSUS](http://cerowarnings.blogspot.com.es/2011/11/servidor-de-actualizaciones-wsus-ii.html)  

## 3.3 Comprobar configuración desde el cliente

Comprobación 1:
* Ir a `Inicio -> Ejecutar` el programa `rsop.msc` o `gpedit.msc`.
* Ir a `Configuración de Equipo > Plantillas Administrativas > Componentes de Windows > Windows Update.`
* Comprobar que el valor de `Especificar ubicación del servicio de Windows Update`
apunta a nuestro servidor WSUS.
* Capturar imagen.

Comprobación 2:
* Inicio -> Ejecutar `regedit`.
* Ir a `LOCAL MACHINE > Software > Policies > Microsoft > Windows > Windows Update`.
* Consultar valores de `WUServer` y `WUStatusServer`
* Capturar imagen.

Comprobación 3:
* Examinar el archivo `C:\Windows\WindowsUpdate.log`.
* Buscar líneas `WSUS server...` y `WSUS status server...`.
* Capturar imagen.

> INOF: `Reg de Apps > MS > Win > WinUpdateClient > Operational.log`.

> * Navegador URL `http://WSUSServerName/iuident.cab`. Esto nos descarga el archivo
`iuident.txt` que nos muestra la configuración del WSUS en el servidor.

## 3.4 Servicio en el cliente

Si se cambia la configuración, reiniciar el servicio Windows Update en el cliente:
* `net stop wuauserv`, Para el servicio de Windows Update.
* `net start wuauserv`, Inicia el servicio de Windows Update.

Podemos esperar 10 minutos a que WindowaUpdate conecte con el servidor, o también
invocar los siguientes comandos:
* `wuauclt.exe /detectnow`: trata de conectar con el servidor y registra el equipo cliente en WSUS.
* `wuauclt /a`, el cliente consulta al servidor.

> Comandos para ejecutar en el cliente como administrador:
>
> * `gpupdate /force`: Esto fuerza a que se apliquen los cambios realizados en las directivas

* Buscar actualizaciones de Windows Update desde Windows7.
    * `sfc /scannow`, si hay un error.
* Capturar imagen de las actualizaciones pendientes de instalar. Deberían ser
las mismas que tenemos aprobadas en el WSUS.

---

# 4. Auditar procesos

## 4.1 Auditar desde el servidor

* Para realizar un diagnóstico de WSUS desde el servidor, consultamos el contenido
de los siguientes archivos:
    * `C:\Program Files\Update Services\Log Files\Change.txt`
    * `C:\Program Files\Update Services\Log Files\SoftwareDistribution.txt`
* Para auditar las actualizaciones de los clientes desde el servidor, vamos a
`Consola Windows Update > Nodo Equipos e Informes`.

---

# ANEXO A

## A.1 Otra forma de instalar WSUS

 Resumen de los pasos para la instalación del servidor WSUS.

* Descarga desde www.microsoft.com/WSUS.
* Inicio > Herr. Admin. > MS Win Server Update Services-
* Consola Up. Serv.
* Detalles > sincronizar hora (Esto puede tardar minutos u horas).

## A.2 Auditar los clientes

¿Se instala bien el software en los clientes? Herramientas para auditar
las actualizaciones desde el servidor:
* (A) Consola Windows Update > Nodo Equipos e Informes.
* (B) MS System Center Conf. Manager 2007. Se ajusta a AD (www.microsoft.com/smserver)
* (C) MS Baseline Security Analyzer (MBSA). Escaneo de la red completa para auditar (www.microsoft.com/mbsa).

Comprobación 3:
* Para ver las actualizaciones instaladas en un PC WinVista o W2k8: Inicio > Panel de Control > Sistema y Mantenimiento > Windows Update > Ver historial de actualizaciones.

## A.3 Eliminar actualizaciones

Cuando una actualización ocasiona problemas de compatibilidad, éstas se
pueden desinstalar. Para ellos hacemos:
* Usar Windows Update para ver el historial de actualizaciones.
Consultar los detalles de cada una para identificar dónde puede estar el problema. Anotar el número KB.
* Inicio -> Panel de control -> Programas -> desinstalar programas.
* Tareas -> ver actualizaciones instaladas.
* Selecionar por el número KB y desinstalarla.
* Siga las instrucciones que aparecen y reinicie si fuese necesario.

> NOTA:
>
> Si eliminar la actualización NO resuelve el problema, debe volver a aplicar esa actualización.
> A continuación, CONTACTE con el desarrollador de la aplicación
(en caso de incompatibilidad con el programa) o con su representante de soporte
de Microsoft para informarle de la incompatibilidad.

## A.4 Instalar y configurar WSUS en Windows Server 2012 R2
