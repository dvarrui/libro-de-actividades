
```
Actividad realizada los cursos: 201415, 201516
``` 

#1. Políticas o directivas de grupo

* Leer la documentación que se proporciona. Concretamente el fichero `M34_directivas_grupos.pdf`.
* Consultar las dudas al profesor.
* Incluir capturas de pantalla de:
    * El proceso de configuración en el servidor
    * y de los resultados producidos en los clientes.

#2. Aplicar directivas (I)

Realizar las siguientes tareas:

* Antes de empezar la práctica vamos a crear un "snapshot" (instantánea) de la máquina virtual.
* Crear las OU (Unidades Organizativas) `jediXXc1516` y `sithXXc1516`.
* Mover los usuarios a su correspondiente OU.


* Enlace sobre [cómo aplicar una GPO a un grupo](http://www.aprendeinformaticaconmigo.com/windows-server-2008-filtrar-una-gpo-para-aplicarla-a-grupos/).

> **IMPORTANTE**: No aplicar la directivas a todo el dominio. 
> Sólo a las unidades organizativas que se especfiquen.

* Vamos a crear una GPO diferente para cada OU.

> **INFO**
> Para editar configuraciones de Directiva de grupo:
> * En Group Policy Management (Administración de directivas de grupo), en el árbol de consola, desplegar Group Policy Objects (Objetos de Directiva de grupo). Click con el botón derecho del ratón en el GPO y seleccionar Edit (Editar).
> * En el Editor de objetos de Directiva de grupo, buscar la Directiva de grupo que queremos modificar y hacemos doble clic. En el cuadro de diálogo Propiedades, cambiamos la configuración y Aceptar.

* Vamos a aplicar las siguientes directivas a las OU anteriores. Elegir unas para una OU y otras para la otra.

* `Quitar el menú Ejecutar del menú Inicio`
    * Ubicación: Configuración de usuario / Plantillas administrativas / Menú Inicio y barra de tareas (User configuration / Administrative Templates / Start Menu and Taskbar)
    * Configuración de Directiva de grupo: Quitar el menú Ejecutar del menú Inicio (Remove Run menu from Start Menu)
    * Opción Habilitada
* `Prohibir el acceso al Panel de control`
    * Ubicación: Configuración de usuario / Plantillas administrativas / Panel de control (User configuration / Administrative Templates / Control Panel)
    * Configuración de Directiva de grupo: Prohibir el acceso al Panel de control (Prohibit access to the Control Panel)
    * Opción Habilitada
* `Ocultar el icono Mis sitios de red del escritorio`
    * Ubicación: Configuración de usuario / Plantillas administrativas / Escritorio ( User configuration / Administrative Templates / Desktop)
    * Configuración de Directiva de grupo: Ocultar el icono Mis sitios de red del escritorio (Hide My Network Places icon on desktop)
    * Opción Habilitada
* `Quitar el icono Mis sitios de red del menú inicio`
    * Ubicación: Configuración de usuario / Plantillas administrativas / Menú Inicio y barra de tareas (User configuration / Administrative Templates / Start Menu and Taskbar)
    * Configuración de Directiva de grupo: Quitar el icono Mis sitios de red del men ú Inicio (Remove My Network Places icon from Start Menu)
    * Opción Habilitada
* `Quitar Conexiones de red del menú Inicio`
    * Ubicación: Configuración de usuario / Plantillas administrativas / Menú Inicio y barra de tareas (User configuration / Administra tive Templates / Start Menu and Taskbar)
    * Configuración de Directiva de grupo: Quitar Conexiones de red del menú Inicio (Remove Network Connections from the Start Menu)
    * Opción Habilitada
* `Ocultar unidades específicas en Mi PC`
    * Ubicación: Configurac ión de usuario / Plantillas administrativas / Componentes de Windows / Explorador de Windows (User configuration / Administrative Templates / Windows Components / Windows Explorer)
    * Configuración de Directiva de grupo: Ocultar estas unidades específicas en Mi PC (Hide these specified drives in My Computer) o Impedir el acceso a las unidades desde Mi PC (Prevent Access to drives from my computer).
    * Opción Habilitada. Elegir un combinación adecuada como bloquear las unidades A y B (Restrict A y B drives only).
* `Habilitar Quitar “Conectar a unidad de red” y “Desconectar de unidad de red”`
    * Ubicación: Configuración de usuario / Plantillas administrativas / Componentes de Windows / Explorador de Windows (User configuration / Administrative Templates / Windows Components / Windows Explorer)
    * Configuración de Directiva de grupo: Quitar “Conectar a unidad de red” y “Desconectar de unidad de red” (Remove “Map Network Drive” and “Disconnect Network Drive”).
    * Opción Habilitada

#3. Aplicar directivas (II)

> Enlaces de interés
>
> * Crear política de instalación para nuestro paquete MSI 
>    * [Crear GPO de despliegue de software](http://www.aprendeinformaticaconmigo.com/windows-server-2008-crear-gpo-de-despliegue-de-software/)
>    * La política de despliegue la vamos a crear a nivel de cuenta de usuario. Marcamos "Asignada" e "Instalar al iniciar Sesión".
> * Crear y probar las directivas del siguiente enlace Windows Server 2008
>    * [Active Directory directivas a usuarios](https://losindestructibles.wordpress.com/2011/05/22/windows-server-2008-active-directory-gpo-directivas-a-usuarios/)

* Vamos a crear otro "snapshot" de la máquina virtual.

Vamos a crear nuestro propio paquete MSI.
* Consultar enlace sobre cómo [Crear paquetes MSI con WinINSTALL](http://www.ite.educacion.es/formacion/materiales/85/cd/windows/11Directivas/crear_paquetes_msi.html).

En el servidor
* [Descargar el programa WinINSTALL](http://www.downloadsource.es/3414/WinINSTALL-LE/)
* Una vez instalada la aplicación hemos de asignar permisos de acceso remoto a la carpeta compartida WinINSTALL.
* Crear la carpeta `e:\softwareXX`.
* Crear un recurso compartido de red `E:\softwareXX`. 
* Crear la subcarpeta `e:\softwareXX\firefox`.

En el cliente
* Entramos con el usuario administrador del dominio.
* Descargar el instalador de Firefox.
* Ejecutamos `\\ip-del-servidor\WinINSTALL\Bin\Discover.exe`, 
para ejecutar la aplicación WinINSTALL LE de modo remoto, 

![pdc-wininstall-discover.png](.files/pdc-wininstall-discover.png)

* Indicamos el nombre que vamos a asociar al paquete MSI (`firefoxXX.msi`).
* Ruta de red donde almacenaremos el MSI, en nuestro caso 
`\\ip-del-servidor\softwareXX\firefox\firefoxXX.msi`.
* Unidad donde se almacenarán los ficheros temporales => C:.
* Unidades que serán analizadas para realizar la foto inicial; 
en nuestro caso sobre la unidad C: de nuestro equipo cliente.
* Indicar los ficheros que serán excluidos del análisis; 
en nuestro caso aceptaremos las opciones propuestas por el asistente por defecto.
* Pulsamos Finish para comenzar la generación de la foto inicial del equipo.

> En el tiempo comprendido entre la ejecución de este proceso y la ejecución 
del proceso de la foto final, es crítico ejecutar únicamente el software 
de instalación del paquete MSI a generar, pues cualquier modificación 
que realizáramos en dicho periodo temporal, al margen de la propia de instalar
 el software correspondiente del que deseamos generar el paquete MSI, 
 se grabaría en el paquete MSI obtenido, cuando realmente no formaría parte de las modificaciones que realizó dicha aplicación durante su instalación. 

* Una vez que la foto inicial haya sido realizada, pulsamos Aceptar, y 
a continuación se nos mostrará otra ventana en el que seleccionaremos el fichero 
de instalación de la aplicación de la que vamos a generar el paquete MSI.
En nuestro caso el fichero firefox.exe que nos habíamos descargado.
* Comienza la instalación de la aplicación de firefox.exe de modo manual.
* A continuación, comienza el proceso de creación de la foto final del sistema.
Este que puede durar varios minutos. 
* Podremos confirmar que el paquete ha sido creado correctamente en el equipo "SERVIDOR", yendo a la carpeta E:\SoftAdm\FilZip306 y comprobando que todos los ficheros necesarios para distribuir FilZip por medio del paquete msi se encuentran en dicha ubicación.
* Limpiamos el equipo cliente:
    * Eliminar el fichero firexfox.exe que nos habíamos descargado.
    * Desinstalar el programa Firefox del cliente.
    
Vamos al servidor:
* Creamos la directiva de instalación de software para firefox.msi.
* Asociamos a la directiva de grupo de Instalación de software ubicada en 
`Configuración del equipo -> Directivas -> Configuración de software`, 
un nuevo paquete de instalación de software de la aplicación.


