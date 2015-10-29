[NO] WSUS
INDICE
1. Introducción
2. Servidor WSUS
2.1 Instalación del servidor
2.2 Configuración del servidor
3. Cliente WSUS
4. Auditar los procesos
4.1 Auditar desde el servidor
4.2 Diagnosticar desde los clientes
4.3 Eliminar actualizaciones




1. Introducción
Existe la necesidad del mantenimiento constante de las actualizaciones de software. En caso contrario:

    Un atacante puede aprovechar las vulnerabilidades.
    Tiempo prolongado de inactividad de los PC's comprometidos.
    La información de la empresa puede verse comprometida.

El servidor WSUS almacena y distribuye las actualizaciones de software MS, reduciendo el ancho de banda. Conseguimos:

    Mejora del consumo del ancho de banda WAN.
    Mejora de los tiempos de actualización global.
    Mayor control de las actualizaciones que se desean.

La solución para ayudar a distribuir las actualizaciones por la empresa es el servicio WSUS. Funcionamiento:

    Servidor WSUS usa Windows Update para descargar el catálogo de actualizaciones de Microsoft.
    El administrador aprueba y prioriza las actualizaciones.
    Servidor WSUS pone las actualizaciones disponibles a los clientes.
    El cliente se conecta a WSUS e instala los paquetes usando Windows update.

Resumen:

    WSUS proporciona control a la hora de aprobar y distribuir actualizaciones de Microsoft entre sus ordenadores clientes.
    Un servidor WSUS puede copiar actualizaciones desde Microsoft y almacenarlas localmente. Entonces, los ordenadores clientes descargarán las actualizaciones desde su servidor WSUS en vez de descargarlas desde Microsoft a través de Internet.
    Para soportar organizaciones con muchas oficinas, los servidores WSUS de descarga pueden sincronizar actualizaciones, aprobaciones y opciones de configuración de los servidores WSUS de subida.
    WSUS también requiere IIS.



2. Servidor WSUS
Tener en cuenta que:

    Necesitamos 1 servidor WSUS por cada 10 PC's.
    Almacén de actualizaciones: 6GB de disco duro.
    Tendremos que hacer copias de seguridad de la BBDD de WSUS.
    En caso de fallo disponemos de 1 semana para reemplazar el servidor.
    Servidor WSUS se conecta vía HTTP/HTTPS con el exterior.
    IIS en WSUS para que se conecten los clientes.

Configurar servidor:

    Fuente: Microsoft u otros servidor WSUS.
    Base de datos en C:\WSUS\UpdateServicesDbFiles\SUSDB.mdf o MSQL-Server.
    Minimizar descargas: Selección de idiomas, selección de productos para actualizar.

2.1 Instalación del servidor
Pasos para la instalación del servidor WSUS.

    Descarga desde www.microsoft.com/WSUS.
    Inicio > Herr. Admin. > MS Win Server Update Services-
    Consola Up. Serv.
    Detalles > sincronizar hora (Esto puede tardar minutos u horas).

2.2 Configuración del servidor

Pasos para la configuración del servidor WSUS:

    Editar opciones: Updates Services > Opciones. Definir proxy, productos a actualizar, idiomas, etc.
    Configurar equipos y grupos: 3 tipos prueba/piloto/producción. Asignar servidor al cliente (Directivas d egrupo). ¿De dónde salen estos equipos? ¿Del AD? ¿Equipos previamente unidos al dominio?
    Aprobar actualizaciones: aprobar y descartar.
    Ver informes.



3. Cliente WSUS
Tener en cuenta que:

    Sistemas operativos clientes son: W2K, WXP, WVista, WS2K3 y WS2K8.
    Los clientes se conectan vía HTTP con el servidor WSUS.
    Cliente Windows Update usa la firma digital y HASH (SHA1) para comprobar la autenticidad. En WXP y W2K se usa "cliente de actualizaciones automáticas"
    Se pueden usar directivas de grupo para distribuir la configuración de la organización (Conf Eq. > Direct > Plant. Admin. > Componen > Win Update)

Parámetros de configuración en el cliente:

    Máquina Servidor WSUS
    Frecuencia, notificaciones, grupo asignado.
    Reinicio automático / confirmación / retrasar
    Administración de energía: Iniciar PC si está apagado para ejecutar actualización.



4. Auditar los procesos

4.1 Auditar desde el servidor

Para realizar el diagnóstico de WSUS tenemos 3 archivos de registro:

    Registro de eventos de Aplicación (http://support.microsoft.com)
    C:\Program Files\ Update Services\Log Files\ Change.txt
    C:\Program Files\ Update Services\Log Files\ SoftwareDistribution.txt

¿Se instala bien el software en los clientes? Herramientas para auditar las actualizaciones desde el servidor:

    (A) Consola Windows Update > Nodo Equipos e Informes.
    (B) MS System Center Conf. Manager 2007. Se ajusta a AD (www.microsoft.com/smserver)
    (C) MS Baseline Security Analyzer (MBSA). Escaneo de la red completa para auditar (www.microsoft.com/mbsa).

4.2 Diagnosticar desde el cliente
Diagnosticar el cliente Windows Update.

    Examinar el archivo %SystemRoot%\WindowsUpdate.log (http://support.microsoft.com/kb/902093).
    Navegador URL http://<WSUSServerName>/iuident.cab
    Herramienta Rsop.msc > Conf. Equipo > Plantillas Admin. > Comp. Wind > Win Update.
    Más información en Reg. de Apps > MS > Win > WinUpdateClient > Operational.log.
    Para ver las actualizaciones instaladas en un PC WinVista o W2k8: Inicio > Panel de Control > Sistema y Mantenimiento > Windows Update > Ver historial de actualizaciones.

Si se cambia la configuración, reiniciar el servicio Windows Update en el cliente:
net stop wuauserv
net start wuauserv

Esperar 10 minutos y Win Update intentará conectar con el servidor. Para que el cliente consulte al servidor hacemos:

wuauclt /a


4.3 Eliminar actualizaciones

Cuando una actualización ocasiona problemas de compatibilidad, éstas se pueden desinstalar. Para ellos hacemos:

    Usar Windows Update para ver el historial de actualizaciones. Consultar los detalles de cada una para identificar dónde puede estar el problema. Anotar el número KB.
    Inicio -> Panel de control -> Programas -> desinstalar programas.
    Tareas -> ver actualizaciones instaladas.
    Selecionar por el número KB y desinstalarla.
    Siga las instrucciones que aparecen y reinicie si fuese necesario.

NOTA: Si eliminar la actualización NO resuelve el problema, debe volver a aplicar esa actualización. A continuación, CONTACTE con el desarrollador de la aplicación (en caso de incompatibilidad con el programa) o con su representante de soporte de Microsoft para informarle de la incompatibilidad.





