
```
Actividad realizada los cursos: 201415, 201516
``` 

#1. Políticas o directivas de grupo

* Leer/consultar la documentación que se proporciona. Concretamente el fichero M34_directivas_grupos.pdf.
* Incluir capturas de pantalla del proceso en el servidor, y de los resultados producidos en los clientes.

#2. Aplicar directivas (I)

Realizar las siguientes tareas:

* Antes de empezar la práctica vamos a crear un "snapshot" (instantánea) de la máquina virtual.
* Crear las OU (Unidades Organizativas) "jedi1516" y "sith1516".
* Mover los usuarios a su correspondiente OU

> **IMPORTANTE**
>
> No aplicar la directivas a todo el dominio. Sólo a las unidades organizativas que se especfiquen.

* Crear una GPO para cada OU.

> **INFO**
> Para editar configuraciones de Directiva de grupo:
> * En Group Policy Management (Administración de directivas de grupo), en el árbol de consola, desplegar Group Policy Objects (Objetos de Directiva de grupo). Click con el botón derecho del ratón en el GPO y seleccionar Edit (Editar).
> * En el Editor de objetos de Directiva de grupo, buscar la Directiva de grupo que queremos modificar y hacemos doble clic. En el cuadro de diálogo Propiedades, cambiamos la configuración y Aceptar.

* Vamos a aplicar diferentes directivas a las OU anteriores.

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

* Vamos a crear otro "snapshot" (instantánea) de la máquina virtual.
* Enlace sobre [cómo aplicar una GPO a un grupo](http://www.aprendeinformaticaconmigo.com/windows-server-2008-filtrar-una-gpo-para-aplicarla-a-grupos/).
* Vamos a crear nuestro propio paquete MSI.
    * [Crear paquetes MSI](http://www.ite.educacion.es/formacion/materiales/85/cd/windows/11Directivas/crear_paquetes_msi.html).
    * [Descargar el programa WinINSTALL](http://www.downloadsource.es/3414/WinINSTALL-LE/)
* Crear política de instalación para nuestro paquete MSI 
    * [Crear GPO de despliegue de software](http://www.aprendeinformaticaconmigo.com/windows-server-2008-crear-gpo-de-despliegue-de-software/)
    * La política de despliegue la vamos a crear a nivel de cuenta de usuario. Marcamos "Asignada" e "Instalar al iniciar Sesión".
* Crear y probar las directivas del siguiente enlace Windows Server 2008
    * [Active Directory directivas a usuarios](https://losindestructibles.wordpress.com/2011/05/22/windows-server-2008-active-directory-gpo-directivas-a-usuarios/)
