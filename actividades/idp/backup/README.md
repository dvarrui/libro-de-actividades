*(Actividad del curso1415)*

#Copias de Seguridad

#1. SO Windows

Enlace de interés:
* [Copia de seguridad y recuperación](https://technet.microsoft.com/es-es/library/cc754097%28v=ws.10%29.aspx).

##1.1 Configuración Windows
Configurar máquina1 Windows 7 Enterprise con:
* IP: 172.19.XX.11 (Donde XX corresponde al nº de cada puesto).
* Nombre de equipo: primer-apellido-del-alumno+1

Configurar máquina2 Windows2008Server Enterprise con:
* IP: 172.19.XX.21 (Donde XX corresponde al nº de cada puesto).
* Nombre de equipo: primer-apellido-del-alumno+2
* Para desactivar las contraseñas complejas: `Inicio -> Herramientas Administrativas -> Directivas de seguridad local -> Directivas de cuenta -> Directivas de contraseña`. Deshabilitar complejidad de contraseñas.
* En Windows2008Server hay que instalar la característica `Copias de seguridad` (Ver imagen):

[w2k8-backup-tools](./images/w2k8-backup-tools.png)

Configurar ambas máquinas con:
* Máscara de red: 255.255.0.0
* Gateway: 172.19.0.1
* Servidor DNS: 8.8.4.4
* Grupo de trabajo: AULA109
* Tarjeta de red VBox en modo puente.

Capturar imágenes de las configuraciones.

>**NOTA:**
>
> * En el caso de trabajar con WXP podremos hacer la copia de seguridad directamente en 
una carpeta `c:\backup\nombre-del-alumno` dentro de la propia máquina.
> * En el caso de Windows7/Window2008server, se nos obliga a que la copia de seguridad se 
realice en un almacenamiento externo. Para ello podemos crear la carpeta `c:\backup\nombre-del-alumno`, 
y la definimos accesible por la red. Permisos de la carpeta compartida lectura/escritura para 
el usuario que vamos a usar en el acceso por red. ¡OJO la carpeta compartida por red puede 
estar en la misma máquina!
> * Otra posibilidad sería la de añadir un 2º disco duro a la máquina virtual, y 
realizar el backup en este 2º disco.
> * En Windows, cada vez que nos conectamos a un recurso compartido de red y nos pide 
información de usuario/clave. Ésta queda guardada hasta un nuevo inicio de sesión. 
Podemos limpiar estos datos ejecutando el comando `net use /d *`.

##1.2 Configuración GNU/Linux
Configurar el equipo con:
* IP: 172.16.109.XX (Donde XX corresponde al nº de cada puesto).
* Máscara de red: 255.255.0.0
* Gateway: 172.16.1.1
* Servidor DNS: 172.16.1.1
* Nombre de equipo: primer-apellido-del-alumno.
* Tarjeta de red VBox en modo puente.


#2. Copia en entorno gráfico
##2.1 Copia en entorno gráfico Windows

    Con el usuario alumno1, crear en la carpeta "Documentos", dos archivos de texto: carta11.txt y carta12.txt. Escribir algún contenido dentro de los archivos para que no estén vacíos.
    Comprobar que existe el recurso compartido de red \server\backup\alumno1. Lo utilizaremos para almacenar las copias de seguridad que vayamos realizando de momento.
    Copia de seguridad. Usar la herramienta de entorno gráfico que proporciona el sistema operativo. Vamos a Inicio, escribimos "Copia de seguridad" para buscar el programa de backup. Realizar una copia de seguridad de los datos del usuario.
    Restaurar la copia de seguridad en el directorio local c:\restore\alumno1.
    Realizaremos los siguientes cambios: Modificar el contenido del fichero carta11.txt. Eliminar el archivo carta12.txt, y crear un nuevo archivo carta13.txt
    Realizar una segunda copia de seguridad. Comprobar el contenido restaurando el contenido en c:\restore\alumno1
    Restaurar únicamente el fichero borrado desde la copia de seguridad a su lugar original.
    Programar la copia de seguridad, a las 11:00 horas diariamente.


##2.2 Entorno gráfico GNU/Linux

    Con el usuario profesor1, crear en dos archivos de texto: manual11.txt y manual12.txt.
    Crear los siguientes directorios:
        /var/backup/profesor1, lo utilizaremos para almacenar las copias de seguridad que vayamos realizando de momento.
        /var/restore/profesor1, lo usaremos para las pruebas de restauración.
    Comprobar permisos. El usuario propietario será profesor1, y el grupo root. Todos los permisos para usuario y grupo. Ninguno para el resto.
    Copia de seguridad: Usaremos la herramienta de entorno gráfico que proporcione el sistema operativo. Realizar la copia del home completo del usuario profesor1 (/home/profesor1).
    Instalar alguna de estas herramientas de backup a través de Synaptic:
        Back in Time (backintime.le-web.org)
        Déjà Dup (mterry.name/deja-dup)
        BackupPC (nackuppc.sourceforge.net/index.html)
        Duplicity (duplicity.nongnu.org)
        Areca Backup (www.areca-backup.org)
        Amanda (www.amanda.org)
        Bacula (www.bacula.org/en)
        Flyback (flyback-project.org)
        luckyBackup (luckybackup.sourceforge.net)
        Remastersys (www.remastersys.klikit-linux.com)
        Rsync (rsync.samba.org)
        Time Vault (launchpad.net/timevault)
    Restaurar la copia de seguridad en /var/restore/profesor1.
    Realizaremos los siguientes cambios: Modificar el contenido del fichero manual11.txt. Eliminar el archivo manual12.txt, y crear un nuevo archivo manual13.txt.
    Volver a realizar la copa de seguridad.
    Restaurar únicamente el archivo eliminado a partir de la copia de seguridad.
    Programar una copia de seguridad, a las 11:00 horas diariamente.


3. Copia de seguridad con comandos
3.1 Comandos Windows

    Instalar Cygwin en W7. Cygwin es una aplicación que crea un entorno de comandos similar al de GNU/Linux.
    Con el usuario alumno2, crear en dos archivos de texto: carta21.txt y carta22.txt
    Realizar copia de seguridad de la carpeta "Documentos" del usuario alumno2. Las copias de seguridad las podemos almacenar en un directorio local (Si usamos Cygwin) o en un recurso de red (Si usamos wbadmin o ntbackup).
    Usaremos el directorio local c:\restore\alumno2, para las pruebas de restauración.
          
    [INFO]
        Podemos usar Cygwin para realizar la copia de la misma forma que lo haríamos en GNU/Linux.
        Dentro de Cygwin la ruta "c:\Users\profesor" será "/cygdrive/c/Users/profesor"
    [INFO]
              
        Windows7/2008server proporciona el comando "wbadmin" para manejar copias de seguridad.
        Abrimos consola (cmd) con el usuario administrador.
        Ejecutamos "wbadmin get versions", para comprobar que funciona.
        Ejemplo de copia parcial: "wbadmin start backup -backupTarget:\vargas1\backup\alumno1 -include:c:\Users\Alumno1\*"
        Ejemplo de copia total: "wbadmin start backup -backupTarget:\vargas1\backup\alumno1 -include:c:"
    [INFO]
        Otra posibilidad, usar los mismo comandos de GNU/Linux en Windows a través de la herramienta Cygwin.
        Windows XP proporciona el comando "ntbackup" para hacer copias de seguridad. Veamos un ejemplo: ntbackup backup "C:\Documents and Settings\alumno2" /F C:\backup\alumno2\backup.bkf /V:yes

      

cygwin-rutas

          
    Copia de seguridad: Realizar una copia de seguridad del home del usuario alumno2.
    Restaurar la copia de seguridad en c:\restore\alumno2.


3.2 Comandos en GNU/Linux

    Con el usuario profesor2, crear en dos archivos de texto: manual21.txt y manual22.txt.
    Crear los siguientes directorios:
        /var/backup/profesor2, lo utilizaremos para almacenar las copias de seguridad que vayamos realizando de momento.
        /var/restore/profesor2, lo usaremos para las pruebas de restauración.
    Comprobar permisos. El usuario propietario será profesor2, y el grupo root. Todos los permisos para usuario y grupo. Ninguno para el resto.
    Copia de seguridad: Usaremos comandos como tar y zip. Vamos realizar la copia de seguridad del home completo del usuario profesor2 (/home/profesor2).
    Restaurar la copia de seguridad en /var/restore/profesor2.
    Realizaremos los siguientes cambios: Modificar el contenido del fichero manual21.txt. Eliminar el archivo manual22.txt, y crear un nuevo archivo manual23.txt.
    Restaurar únicamente el archivo eliminado a partir de la copia de seguridad.


#ANEXO

    Instalar Cygwin junto con el servidor OpenSSH.
        ¿Cómo configurar SSH desde Cygwin?
        ¿Cómo actualizar paquetes desde Cygwin?
