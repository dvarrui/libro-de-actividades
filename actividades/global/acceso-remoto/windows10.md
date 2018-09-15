    EN CONSTRUCCIÓN!!!

    Autor: Francisco Vargas Ruiz
    Versión: v201711211859

# Acceso remoto con SSH en Windows 10

## Introducción

El servidor SSH (Secure SHell) es un servicio similar a Telnet, de forma que permite que un usuario acceda de forma remota a un sistema GNU/Linux, con la particularidad de que, al contrario que Telnet, las comunicaciones entre el cliente y el servidor son encriptadas. Así, si un usuario malintencionado intercepta paquetes de datos entre el cliente y el servidor, será muy difícil que pueda extraer algo de información de dichos paquetes.

El servicio SSH ha tenido tanto éxito que Telnet prácticamente no se usa. De hecho, se recomienda usar siempre SSH en lugar de
Telnet.

Para que un usuario se conecte a un sistema mediante SSH éste deberá disponer de un cliente SSH. Al establecer la conexión, mediante un proceso de encriptación asimétrica, las comunicaciones se encriptan, incluido el proceso de autentificación del usuario, cuando se proporciona el usuario y la contraseña. También se proporciona una clave de encriptación simétrica por su
menor necesidad de procesamiento.

## Servidor SSH integrado en Windows 10

En Windows 10 dispone de un servidor SSH integrado: `Microsoft SSH Server for Windows`.
Debemos tener en cuenta que no se trata de un servidor SSH completo, con todas las características que cabría esperar de un
servicio de este tipo (no soporta todos los métodos de autenticación de un servidor SSH, compresión,...), pues está pensado para la comunicación entre dispositivos para cuestiones de depuración.

Otro de los inconvenientes es que es necesario cambiar nuestro sistema a `Modo Desarrollador` (Developer Mode), afectando
esto al nivel de aplicación de las políticas de seguridad del sistema, por lo que es conveniente leer bien cómo va a afectar esto a nuestro sistema antes de proceder a realizar este cambio.

## Instalar el servicio SSH

1. Abrir la aplicación `Configuración` de Windows.
2. Seleccionar `Actualización y Seguridad`.
3. Seleccionar en el panel de la izquierda `Para programadores`.
4. Cambiar a `Modo programador` (confirmar el cambio) y esperar que se descarguen e instalen los paquetes necesarios.
5. Una vez termina el proceso, reiniciar en caso necesario (si nos lo pide).
6. Activar la opción `Detección de dispositivos`.
7. Volver a desactivar la opción `Detección de dispositivos` (a no ser que queremos tenerla activada).

Este proceso habilita los servicios “SSH Server Broker” y “SSH Server Proxy”, que aceptarán conexiones TCP al puerto 22.
Podemos comprobar la existencia de estos dos nuevos servicios desde PowerShell:

`PS> Get-Service SshBroker,SshProxy`

Para comprobar que el puerto 22 está ahora levantado podemos ejecutar el siguiente comando:

`CMD> netstat –anop tcp`

Mediante los siguientes comandos PowerShell podemos configurar los servicios anteriores para que se inicien automáticamente
(hacer como “Administrador):
```
PS> Set-Service –Name SshBroker –StartupType Automatic
PS> Set-Service –Name SshProxy –StartupType Automatic
```

## Permitir el acceso por SSH a los usuarios del sistema

Los usuarios que quieran conectar de forma remota al servidor SSH integrado en Windows 10 deben pertenecer al grupo `Ssh
Users`. Desde la consola de administración de usuarios y grupos locales podremos añadir los usuarios a este grupo:
lusrmgr.msc

## Inicio y parada manual de los servicios

* Para iniciar los servicios de forma manual es posible usar los siguientes comandos PowerShell: `PS> Start-Service SshBroker,SshProxy`
* Para detenerlos de forma manual, los siguientes comandos PS: `PS> Stop-Service SshBroker,SshProxy`
* Para consultar el estado de los servicios (Running/Stopped): `PS> Get-Service SshBroker,SshProxy`
