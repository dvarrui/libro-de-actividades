
```
Curso       : 202021
Área        : Sistemas operativos, servidor, instalar, software
Software    : Windows Server 2016 y Windows 10
Descripción : Servidor de actualizaciones WSUS
Requisitos  :
Tiempo      :
```

# 1. Servidor de actualizaciones WSUS

## 1.1 Teoría

Inconvenientes de no tener nuestros sistemas actualizados:
* Un atacante puede aprovechar las vulnerabilidades.
* Aumenta el tiempo de inactividad de los PC's comprometidos.
* La información de la empresa puede verse comprometida.

Ventajas de tener un servidor WSUS que almacena y distribuye las actualizaciones de software MS:
* Reducción del consumo del ancho de banda WAN.
* Mejora de los tiempos de actualización global.
* Mayor control de las actualizaciones que se desean.
* Servidor pone las actualizaciones disponibles a los clientes.
* El administrador del servidor aprueba y prioriza las actualizaciones.
* El cliente se conecta a WSUS e instala los paquetes usando Windows Update.

> **Resumen:**
>
> * WSUS proporciona control a la hora de aprobar y distribuir actualizaciones de Microsoft entre sus ordenadores clientes.
> * Un servidor WSUS puede copiar actualizaciones desde Microsoft y almacenarlas localmente. Entonces, los ordenadores clientes descargarán las actualizaciones desde su servidor WSUS en vez de descargarlas desde Microsoft a través de Internet.
> * Para soportar organizaciones con muchas oficinas, los servidores WSUS de descarga pueden sincronizar actualizaciones, aprobaciones y opciones de configuración de los servidores WSUS de subida.
> * WSUS también requiere IIS.

## 1.2 Preparativos

Vamos a necesitar 2 MVs.

| MV  | Función  | Sistema Operativo | Configuración |
| --- | -------- | ----------------- | ------------- |
| MV1 | Servidor | Window Server 2016 (No PDC) | [Configurar](../../../global/configuracion/windows-server.md) como se indica. |
| MV2 | Cliente  | Windows 10        | [Configurar](../../../global/configuracion/windows.md) como se indica. |

# 2. Servidor WSUS

## 2.1 Teoría

Consideraciones a tener en cuenta:
* NO es necesario tener un Active Directory para montar el servicio WSUS.
* Servidor WSUS se conecta vía HTTP/HTTPS con el exterior. Se usa IIS en WSUS para que se conecten los clientes.
* Se requiere al menos 6GB de disco duro para almacenar las actualizaciones.
* Tendremos que hacer copias de seguridad de la BBDD de WSUS periódicamente.
* Necesitamos 1 servidor WSUS por cada 10 PC's. En caso de fallo disponemos de 1 semana para reemplazar el servidor.
* Podremos usar como fuente origen de las actualizaciones a los servidor
 de Microsoft u otros servidores WSUS.
* La base de datos que guarda la información, la podemos tener en
C:\WSUS\UpdateServicesDbFiles\SUSDB.mdf (WS2008) o montar un SQL-Server.
* En nuestro caso lo vamos a hacer SIN SQL-Server.

## 2.2 Instalación del servidor

> Enlaces de interés:
> * [Instalar WSUS en Windows Server 2016](https://docs.microsoft.com/es-es/windows-server/administration/windows-server-update-services/deploy/1-install-the-wsus-server-role#:~:text=foro%20de%20WSUS.-,Para%20instalar%20el%20rol%20de%20servidor%20de%20WSUS,en%20Agregar%20roles%20y%20caracter%C3%ADsticas)
> * [Instalación y puesta en marcha Servidor de actualizaciones (WSUS I)](http://cerowarnings.blogspot.com.es/2011/11/servidor-de-actualizaciones-wsus.html)
> * [Instalación y configuración de WSUS - Parte 1](https://hackpuntes.com/wsus-windows-server-update-services-instalacion-y-configuracion-parte-i/)
> * [Vídeo WSUS en Windows 2012 Server R2](https://www.youtube.com/watch?v=2YPtfrwVObg)

Ir a la MV1 Windows Server.
* Añadir una disco extra de 10 GB y montarlo en la unidad E:.
* Asegurarse de que tenemos la instalación estado `No ilegal`.

La forma más sencilla para instalar el software WSUS es usar la propia
herramienta de administrar del servidor Windows Server.
* `Administrar el servidor -> Roles -> Agregar Roles -> Windows Server Update Services (WSUS)`.
* Capturar imagen de las opciones que vayamos eligiendo.
* No usaremos la base de datos SQL-Server.
* Almacenar las actualizaciones en `E:\actualizacionesXX` (donde XX es el número del alumno).

## 2.3 Configuración del servidor

> Enlace de interés: [Configurar WSUS - Windows Server 2016](https://docs.microsoft.com/es-es/windows-server/administration/windows-server-update-services/deploy/2-configure-wsus)

![](image/wsus-configurar.png)

* Podemos acceder a la configuración de WSUS por: `Panel -> Herramientas -> Windows Server Update Services (WSUS) -> Opciones -> Asistente de configuración`.

A continuación configuramos el servidor WSUS con los siguientes parámetros:
* Sincronizar desde Microsoft.
* No usar proxy.
* Iniciar conexión. Esto puede tardar un tiempo.
* Selección de Productos: Para minimizar descargas es conveniente hacer una
selección de idiomas y de productos concretos para actualizar. Elegir:
    * Idioma: `Español` e `Inglés`
    * Producto: `Windows 10`
    * Tipo de actualización: `Actualizaciones críticas y de seguridad`.
* Elegir sincronización manual en lugar de automática. Elegimos manual para hacer nuestras pruebas de clase. Cuando estemos en la empresa elegiremos sincronización automática.
* Iniciar sincronización inicial.

Desde el servidor WSUS hay que aprobar algunas actualizaciones:
* Ir a `Administrador del servidor -> Windows Server Update Services -> Actualizaciones`.
* Buscamos `Actualizaciones críticas`
    * Aprobación: `cualquiera`
    * Estado: `cualquiera`
* Seleccionar 3 actualizaciones críticas y aprobar su instalación en todos los equipos.
    * Si no nos aparece nada, esperar a que termine el proceso de sincronización (Esto puede durar bastante tiempo).
    * En caso de que haya terminado la sincronización y no aparezcan las actualizaciones reiniciar el servidor.
* Seleccionar también las actualizaciones en el estado "Necesarias" y aprobarlas.

## 2.4 Servicio con inicio automático

* Ir a `Administrar -> Herramientas -> Servicios`.
* Configurar el `Servicio WSUS` con inicio automático. Esto es, el servicio se iniciará de forma automática cada vez que se arranque la máquina.

> NOTA: Podemos consultar configuración del WSUS en el servidor, abriendo un navegador con el URL `http://WSUSServerName/iuident.cab`. Esto nos descarga el archivo `iuident.txt` que nos muestra la configuración del WSUS en el servidor.

---

# 3. Cliente WSUS

## 3.1 Configurar el cliente

### Configurar sin PDC (Recomendado)

Vamos a configurar Windows Update del cliente (MV2 con Windows 10), para usar el nuevo servidor WSUS.

**Configuración usando Políticas de Grupo Local**

> Enlaces de interés:
>    * [Configuración cliente para WSUS con o sin Active Directory](http://cosiis.com/blog/archives/69)
>    * [Configurar Windows7 para WSUS](http://soporte.fen.uchile.cl/mw/index.php/WSUS_para_Windows_7)

* Ir a la MV2 Cliente.
* Abrir un terminal como Administrador, y ejecutar `gpedit`.
* Ir a `Configuración del Equipo -> Plantillas administrativas -> Componentes de Windows -> Windows Update`.
    * En `Especificar la ubicación del servidor de Windows Update` pondremos la ubicación del servidor de la siguiente forma `http://ip-del-servidor:8530`. Pondremos el mismo valor en la ubicación del servidor de estadísticas. NOTA: Si quisiéramos conectar vía HTTPS pondríamos `https://ip-del-servidor:8531`.
    * Configurar actualizaciones automáticas -> Habilitar. Valor por defecto "Configurar cliente para descargar las actualizaciones sin instalarlas".
* Iniciar PowerShell como Administrador y ejecutar `gpupdate /force`. Esto fuerza a que se apliquen los cambios realizados en las directivas.

> **Otras formar de configurar el cliente WSUS:**
>
> **Registro de Windows**
> * Esta información está probada en Windows XP, pero no es Windows7.
> * Antes de ejecutar este proceso hacer una copia de seguridad del registro o bien hacer una instantánea de la MV.
> * Enlace de interés [Script para modificar el registro de Windows](http://servidorespararedes.blogspot.com.es/2008/10/configuracion-de-cliente-wsus-por.html)
>
> **Configurar con PDC**
>
> * Vamos a asignar el servidor WSUS a los equipos del dominio mediante directivas de grupo.
> * Política de Grupo (AD DS). Enlace de interés [Configuración de clientes y aprobación de actualizaciones WSUS](http://cerowarnings.blogspot.com.es/2011/11/servidor-de-actualizaciones-wsus-ii.html)  

## 3.2 Comprobacíón

Comprobación:
* Ir a MV2 Cliente.
* Ejecutar la siguiente consulta de registro en línea de comandos: `reg query HKLM\SOFTWARE\Policies\Microsoft\Windows\WindowsUpdate`
    * Se muestran parámetros configuración del servidor WSUS en pantalla.
* Capturar imagen.

> **[INFO] Otras comprobaciones**.
>
> Comprobación 2:
> * Ir a `Inicio -> Ejecutar` el programa `rsop.msc` o `gpedit.msc`.
> * Ir a `Configuración de Equipo > Plantillas Administrativas > Componentes de Windows > Windows Update.`
> * Comprobar que el valor de `Especificar ubicación del servicio de Windows Update`
apunta a nuestro servidor WSUS.
>
> Comprobación 3:
> * Inicio -> Ejecutar `regedit`.
> * Ir a `LOCAL MACHINE > Software > Policies > Microsoft > Windows > Windows Update`.
> * Consultar valores de `WUServer` y `WUStatusServer`
>
> Comprobación 4:
> * Examinar el archivo `C:\Windows\WindowsUpdate.log`.
> * Buscar líneas `WSUS server...` y `WSUS status server...`.
>

## 3.3 Servicio en el cliente

Cuando se cambia la configuración, hay reiniciar el servicio Windows Update en el cliente:
* `net stop wuauserv`, Para el servicio de Windows Update.
* `net start wuauserv`, Inicia el servicio de Windows Update.

Podemos esperar 10 minutos a que Windows Update conecte con el servidor, o también
invocar los siguientes comandos:
* `wuauclt.exe /detectnow`, trata de conectar con el servidor y registra el equipo cliente en WSUS.
* `wuauclt /a`, el cliente consulta al servidor.
* Buscar actualizaciones de Windows Update desde Windows 10.
    * Capturar imagen de las actualizaciones pendientes de instalar.
    * Deberían ser las mismas que tenemos aprobadas en el WSUS.
* Forzar las actualizaciones Windows Update desde el equipo cliente.

## 3.4 En el caso de tener PROBLEMAS

Sólo en caso de tener problemas de conexión del cliente WSUS con el servidor.

* Descargar y ejecutar [Herramienta de diagnóstico WSUS](http://download.microsoft.com/download/9/7/6/976d1084-d2fd-45a1-8c27-a467c768d8ef/WSUS%20Client%20Diagnostic%20Tool.EXE) desde línea de comandos.
* Algunos problemas de cliente WSUS se solucionan con la siguiente herramienta:
    * Descargar herramienta [Solucionador WSUS](http://aka.ms/diag_wu)
    * https://support.microsoft.com/es-es/help/10164/fix-windows-update-errors
* `sfc /scannow`, repara archivos dañados.

---

# 4. Auditar procesos

## 4.1 Auditar desde el servidor

* Ir a la MV del servidor WSUS
* Ir a `Herramientas -> WSUS -> Equipos`.
    * Capturar imagen del equipo Windows registrado como cliente WSUS.
    * En el caso de que no aparezca el equipo cliente, forzar las actualizaciones de Windows Update desde el equipo cliente.
* Si el equipo cliente aparece con un símbolo de advertencia (triángulo amarillo), entonces probablemente se requiere aprobar actualizaciones en el estado "Necesarias".
* Para auditar las actualizaciones de los clientes desde el servidor, vamos a
`Consola Windows Update -> Informes`.
    * Mostrar informe de las actualizaciones de nuestro equipo cliente.

---

# ANEXO A

## A.1 Otra forma de instalar WSUS

 Resumen de los pasos para la instalación del servidor WSUS.

* Descarga desde www.microsoft.com/WSUS.
* Inicio > Herr. Admin. > MS Win Server Update Services-
* Consola Up. Serv.
* Detalles > sincronizar hora (Esto puede tardar minutos u horas).

## A.2 Eliminar actualizaciones

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

## A.3 Consultar ficheros de Log

* Para realizar un diagnóstico de WSUS desde el servidor, consultamos el contenido
de los siguientes archivos:
    * `C:\Program Files\Update Services\Log Files\Change.txt`
    * `C:\Program Files\Update Services\Log Files\SoftwareDistribution.txt`
