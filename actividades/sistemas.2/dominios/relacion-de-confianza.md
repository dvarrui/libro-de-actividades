

# Relación de confianza

Entregar documento ODT o PDF con los pasos realizados. Incluir algunas capturas de pantalla.

> Enlaces de interés:
>
> * [Crear una relación de confianza de bosque](https://www.microsoft.com/es-ES/download/details.aspx?id=53314)

# 1. Preparación de las máquinas

Para realizar esta práctica, vamos a trabajar en parejas,
y prepararemos nuestras máquinas de la siguiente forma:

* SERVIDOR1 será un Windows Server del alumno1. Es un controlador del DOMINIO1.
* CLIENTE1 será un Windows XP del alumno1. Pertenece al DOMINIO1.
* SERVIDOR2 será un Windows Server del alumno2. Es un controlador del DOMINIO2.
* CLIENTE2 será un Windows XP del alumno2. Pertenece al DOMINIO2.
* En cada servidor debemos tener definidos dos grupos de usuarios como mínimo, con 1 o más usuarios por cada grupo.

> NOTA: Los clientes deben tener como servidores DNS a cada uno de los servidores. Además todas las máquina debe tener la misma fecha/hora.

# 2. Directivas y configuración

* Seguir el documento suministrado para definir políticas y configuraciones de aplicaciones personalizadas.
* Con el comando **gpupdate** forzamos a aplicación de las directivas de forma inmediata.

# 3. Preparamos el recurso Compartido

* Crearemos una carpeta compartida en el servidor. Por ejemplo `C:\Compartido`.
* Creamos un fichero `C:\Compartido\inicio.bat`, y dentro ponemos el comando: `net use S: \\ip-del-servidor\Compartido`.

> Este fichero BAT ejecuta los comandos necesarios para montar un recurso compartido de red sobre una letra de unidad determinada.

# 4. Perfil del usuario

Ahora vamos a personalizar el perfil de un usuario:
* Vamos a la configuración de un usuario concreto, pestaña perfil y vemos dos campos. En ruta de acceso ponemos `C:\Compartido` y en secuencia de comandos al inicio ponemos `inicio.bat`.

> Con esto estamos configurando el usuario para que ejecute el script BAT al inicio de su sesión.

# 5. Relación de confianza

* Establecer relación de confianza bidireccional entre ambos dominios.
* Comprobar que la relación de confianza funciona correctamente, tratando de entrar en CLIENTE1 con usuarios definidos en SERVIDOR2, y viceversa.
