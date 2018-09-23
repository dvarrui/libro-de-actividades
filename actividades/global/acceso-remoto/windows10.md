    EN CONSTRUCCIÓN!!!

    Autor: Francisco Vargas Ruiz
    Versión: v201711211859

# Acceso remoto con SSH en Windows 10

Vamos a ver dos formar de acceder vía SSH al SO Windows 10.

# 1. Introducción

El servidor SSH (Secure SHell) es un servicio similar a Telnet, de forma que permite que un usuario acceda de forma remota a un sistema GNU/Linux, con la particularidad de que, al contrario que Telnet, las comunicaciones entre el cliente y el servidor son encriptadas. Así, si un usuario malintencionado intercepta paquetes de datos entre el cliente y el servidor, será muy difícil que pueda extraer algo de información de dichos paquetes.

El servicio SSH ha tenido tanto éxito que Telnet prácticamente no se usa. De hecho, se recomienda usar siempre SSH en lugar de Telnet.

Para que un usuario se conecte a un sistema mediante SSH éste deberá disponer de un cliente SSH. Al establecer la conexión, mediante un proceso de encriptación asimétrica, las comunicaciones se encriptan, incluido el proceso de autentificación del usuario, cuando se proporciona el usuario y la contraseña. También se proporciona una clave de encriptación simétrica por su
menor necesidad de procesamiento.

---

# 2. Servidor SSH integrado en Windows 10

En Windows 10 dispone de un servidor SSH integrado: `Microsoft SSH Server for Windows`.
Debemos tener en cuenta que no se trata de un servidor SSH completo, con todas las características que cabría esperar de un
servicio de este tipo (no soporta todos los métodos de autenticación de un servidor SSH, compresión,...), pues está pensado para la comunicación entre dispositivos para cuestiones de depuración.

Otro de los inconvenientes es que es necesario cambiar nuestro sistema a `Modo Desarrollador` (Developer Mode), afectando
esto al nivel de aplicación de las políticas de seguridad del sistema, por lo que es conveniente leer bien cómo va a afectar esto a nuestro sistema antes de proceder a realizar este cambio.

## 2.1 Instalar el servicio SSH integrado

1. Abrir la aplicación `Configuración` de Windows.
2. Seleccionar `Actualización y Seguridad`.
3. Seleccionar en el panel de la izquierda `Para programadores`.
4. Cambiar a `Modo programador` (confirmar el cambio) y esperar que se descarguen e instalen los paquetes necesarios.
5. Una vez termina el proceso, reiniciar en caso necesario (si nos lo pide).
6. Activar la opción `Detección de dispositivos`.
7. Volver a desactivar la opción `Detección de dispositivos` (a no ser que queremos tenerla activada).

Este proceso habilita los servicios “SSH Server Broker” y “SSH Server Proxy”, que aceptarán conexiones TCP al puerto 22.

* `PS> Get-Service SshBroker,SshProxy`, para comprobar la existencia de estos dos nuevos servicios desde PowerShell.
* `CMD> netstat –anop tcp`, para comprobar que el puerto 22 está ahora levantado podemos ejecutar el siguiente comando.
* Mediante los siguientes comandos PowerShell podemos configurar los servicios anteriores para que se inicien automáticamente
(hacer como “Administrador):
```
PS> Set-Service –Name SshBroker –StartupType Automatic
PS> Set-Service –Name SshProxy –StartupType Automatic
```

## 2.2 Configurar usuarios para usar SSH

Los usuarios que quieran conectar de forma remota al servidor SSH integrado en Windows 10 deben pertenecer al grupo `Ssh
Users`. Desde la consola de administración de usuarios y grupos locales podremos añadir los usuarios a este grupo:
lusrmgr.msc

## 2.3 Inicio y parada manual de los servicios

* Para iniciar los servicios de forma manual es posible usar los siguientes comandos PowerShell: `PS> Start-Service SshBroker,SshProxy`
* Para detenerlos de forma manual, los siguientes comandos PS: `PS> Stop-Service SshBroker,SshProxy`
* Para consultar el estado de los servicios (Running/Stopped): `PS> Get-Service SshBroker,SshProxy`

## 2.4 Desactivar el servicio SSH

Para desactivar el servicio (no desinstalarlo) podemos “revertir” el proceso que seguimos al instalarlo:
1. Abrir la aplicación “Configuración” de Windows.
2. Seleccionar “Actualización y Seguridad”.
3. Seleccionar en el panel de la izquierda “Para programadores”.
4. Desactivar la opción “Detección de dispositivos” (si estuviera activada).
5. Cambiar de nuevo a “Aplicaciones de la Tienda de Windows”.

* `PS> Get-Service SshBroker,SshProxy`, comprobar el estado de los servicios.
* También podemos deshabilitar desde PowerShell los servicios anteriores para que no se inicien de ninguna forma (hacer como
“Administrador):
```
PS> Set-Service –Name SshBroker –StartupType Disable
PS> Set-Service –Name SshProxy –StartupType Disable
```

---

# 3. Servidor OpenSSH en Windows

Microsoft está trabajando en portar a Windows el servicio OpenSSH disponible en los sistemas UNIX/Linux. En el momento de
redactar este tutorial está disponible la versión v0.0.23.0 de este servicio.
AVISO: Se trata aún de una versión de prueba (test release) y no es recomendable su uso en sistemas en producción.

## 3.1 Instalar el servicio

Para instalarlo seguimos los siguientes pasos:

1. Descargar la última versión de [OpenSSH-Win64.zip](https://github.com/PowerShell/Win32-OpenSSH/releases/latest/).
2. Descomprimir en `C:\Program files\OpenSSH`. En caso de haber descargado la versión de 32 bits (OpenSSH-Win32), extraer el contenido del ZIP en `C:\Program files\OpenSSH (x86)`.
3. `PS> cd ‘C:\Program files\OpenSSH’`, Iniciar PowerShell como Administrador y movernos hasta `C:\Program files\OpenSSH`:
4. Ejecutar el script para instalar los servicios “sshd” y “ssh-agent”:
```
PS> Set-ExecutionPolicy –ExecutionPolicy Bypass
PS> .\install-sshd.ps1
```
5. Al terminar debe indicar que los servicios se han instalado de forma satisfactoria. Podemos comprobar que se han
instalado los servicios con el siguiente comando: `PS> Get-Service sshd,ssh-agent`
6. Generar las claves (certificados) del servidor:
```
PS> .\ssh-keygen.exe –A
PS> .\FixHostFilePermissions.ps1 -Confirm:$false
```
7. Habilitar la regla de nombre “SSH” en el Firewall de Windows para permitir (Allow) conexiones TCP entrantes
(Inbound) en el puerto 22 (SSH): `PS> New-NetFirewallRule -Protocol TCP -LocalPort 22 -Direction Inbound -Action Allow -DisplayName SSH`
8. Configuramos los servicios para que inicien automáticamente:
```
PS> Set-Service sshd -StartupType Automatic
PS> Set-Service ssh-agent -StartupType Automatic
```
9. Iniciamos el servicio: `PS> Start-Service sshd`

## 3.2 Configurar la Shell por defecto

Es posible configurar el intérprete de comandos (Shell) que se ejecutará cuando nos conectemos de forma remota al servidor SSH en Windows. Las Shell más comunes en Windows son el “Símbolo del sistema” (cmd.exe) y PowerShell (powershell.exe).

Para hacerlo debemos realizar la siguiente modificación en el “Registro de Windows”:
1. Abrimos el “Editor del Registro” de Windows (regedit.exe).
2. Nos desplazamos hasta la clave `Equipo\HKEY_LOCAL_MACHINE\SOFTWARE`.
3. Creamos la clave “OpenSSH”.
4. Dentro de la clave “OpenSSH”, creamos un “Valor de cadena” (REG_SZ) con el nombre “DefaultShell” y cuyo valor es
la ruta al ejecutable de PowerShell:
`C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe”`

Al terminar el proceso, el “Registro de Windows” debe quedar de la siguiente forma:
![Imagen](images/w10-registro-powershell.png)

Ahora, en la próxima conexión por SSH al servidor se iniciará por defecto PowerShell en vez del “Símbolo del sistema”.

## 3.3 Desinstalar el servicio

Para desinstalar el servidor OpenSSH de Windows seguimos los siguientes pasos:
1. Iniciar PowerShell como Administrador y movernos hasta “C:\Program files\OpenSSH”:
`PS> cd ‘C:\Program files\OpenSSH’``
2. Ejecutar el script para desinstalar los servicios “sshd” y “ssh-agent”:
```
PS> Set-ExecutionPolicy –ExecutionPolicy Bypass
PS> .\uninstall-sshd.ps1
```
Al terminar debe indicar que los servicios se han desinstalado de forma satisfactoria.
