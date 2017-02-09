
*(Actividad adaptada de eventos-locales-windows-debian para el curso 201617)*

# 1. Introducción

Toda la actividad importante del sistema debe quedar registrada en los
ficheros de registro. Esto nos permite tener un histórico del comportamiento
del sistema, que nos ayuda a modo de *"caja negra"*, a reconstruir situaciones
del pasado para diversos fines. Esta es la utilidad de la monitorización y la auditoría,
saber lo que ha pasado.

---

# 2. SO Windows

* [Configuramos la MV Windows](../../global/configuracion/windows.md)

## 2.1 Auditar inicios de sesión

Vamos realizar las siguientes tareas en SO Windows.
* Activar unas directivas de seguridad, para auditar los inicios de sesión
al sistema (Correctos e incorrectos). Incluir captura de pantalla con la directiva activada.
* Nosotros queremos auditar los *"Sucesos de inicio de sesión"*.

> NOTA:
> * Los "Sucesos de inicio de sesión de cuenta" no los vamos a necesitar ahora.
> * Éstos sirven para auditar a los usuarios del dominio. Esto todavía no lo hemos visto.

A continuación se muestra imagen de ejemplo de la directiva desactivada:

![activar-directivas](./images/activar-directivas.png)

* Reiniciar la MV para que empiecen a funcionar las auditorías.
* Crear los usuarios `soldado1`, `soldado2` y `soldado3`.
* Hacer las siguientes acciones:
    * Entrar al sistema con `soldado1` de forma correcta.
    * Intentar entrar con `soldado2` poniendo la clave mal.
    * y no entrar con `soldado3`.
* Buscar en el sistema, la herramienta visor de sucesos.
* Comprobar cómo se registran los eventos anteriores en la sección "Seguridad".
Incluir captura de pantalla.
* Exportar los eventos a ficheros CSV. ¡OJO!: Filtrar los eventos para NO
incluirlos todos (Elegir los generados hoy, o en las últimas horas).
* Incluir fichero en la entrega con el nombre `nombre-alumno-registro-windows.csv`.
* Los ficheros con formato CSV se pueden abrir y manipular cómodamente usando hojas
de cálculo (Por ejemplo: Excel de Microsoft, Calc de LibreOffice, etc.). Comprobarlo.

> Realmente los CSV son ficheros de texto donde cada fila es como in registro de una tabla.
Y se usa la coma para delimitar los campos dentro de cada fila.

## 2.2 Auditar acceso a un fichero

* Crear el archivo `c:\public\estrellita.txt`
* Activar auditoría de accesos al archivo anterior.
* Acceder con los usuarios `soldado1` y `soldado2`.
* Mostrar los resultados de la auditoría.

---

# 3. GNU/Linux OpenSUSE

El servicio Audit es una herramienta que nos permite auditar eventos en los sistemas
GNU/Linux. En este tutorial instalar, configurar y usar la herramienta de auditoría
audit.

Usando herramientas potentes como audit, el sistema puede ser relizar un seguimiento
de muchos eventos y monitorizar y auditar el sistema. Ejemplos:
* Auditar el acceso y modificación de ficheros.
    * Ver quién cambió un fichero concreto.
    * Detectar cambios no autorizados.
* Monitorizar las llamadas al sistema y funciones
* Detectar anomalías como procesos dañados.
* Establecer marcas (tripwires) para detectar intrusiones.
* Grabar comandos de usuarios concretos.

Componentes de audit:
* auditd: Demonio que captura los eventos y los almacena (log file)
* auditctl: Herramienta cliente para configurar auditd
    * auditctl -e, habilitar o deshabilitar audit
    * auditctl -r, controlar la ratio límite de mensajes
    * auditctl -s, consultar el estado actual del demonio
* audispd: daemon to multiplex events
* aureport: Herramienta de informes que leer de los ficheros de log (auditd.log)
* ausearch: Visor de eventos (auditd.log)
* autrace: Componente del kernel para hacer seguimiento de los binarios.
* aulast: Similar a last, pero usando audit
* aulastlog: Similar a lastlog, pero usando audit
* ausyscall: mapea los syscall ID y nombre
* auvirt: Muestra información relacionada con las máquinas virtuales.

Ficheros:
* audit.rules: Usado por auditctl para leer las reglas que se van a aplicar.
* auditd.conf: Fichero de configuración de auditd.

Enlaces de interés:
* [Systemd Journal](https://es.opensuse.org/SDB:Systemd_journal)
* [Systemd Optimización](https://es.opensuse.org/SDB:Systemd_optimizacion)
* [Linux audit](https://doc.opensuse.org/documentation/leap/security/html/book.security/cha.audit.comp.html)
* [Configuring and auditing Linux with audit](https://linux-audit.com/configuring-and-auditing-linux-systems-with-audit-daemon/)

## 3.1 Instalación y teoría

* Instalar los paquetes auditd y audispd-plugins/audit-libs
* `auditctl -s`, consultar el estado del demonio.

La configuración del demonio audit la llevan dos ficheros, uno para el demonio
(auditd.conf) y otro para las reglas usadas por la herramienta auditctl (audit.rules).

### auditd.conf

El fichero auditd.conf configura el demonio auditd centrándose en dónde y cómo
se deben registrar los eventos. Define como tratar con los discos llenos,
rotaciones de log y el número de log a mantener. Normalmente la configuración
por defecto será apropiada para la mayoría de los casos.

* Consultar el fichero `/etc/audit/auditd.conf`
* Siginificado de los siguientes parámetros: log_file, log_format, log_group,
freq, num_logs, max_log_file, max_log_file_action.

### audit.rules

Para configurar los eventos que deben ser auditados se usa el fichero audit.rules.
Empezaremos con una configuración limpia.

* Consultar el fichero `/etc/audit/audit.rules`.
* `auditctl -l`, para ver las reglas activas. Al principio no debemos tener nada.
* Para eliminar reglas usaremos auditctl y el parámetro -D.

## 3.2 Auditar fichero

Vemos una ejemplo auditando el fichero `/etc/passwd`.

* `auditctl -a exit,always -F path=/etc/passwd -F perm=wa`
* path, define la ruta del fichero o directorio a observar.
* Los permisos determinan qué tipo de acceso disparará un evento
(r=read, w=write, x=execute, a=attribute change)
* Para encontrar los eventos relaciones con los acceso al fichero hacemos:

```
[root@host audit]# ausearch -f /etc/passwd

time->Tue Mar 18 15:17:25 2014
type=PATH msg=audit(1395152245.230:533): item=0 name=”/etc/passwd” inode=137627 dev=fd:00 mode=0100644 ouid=0 ogid=0 rdev=00:00 obj=system_u:object_r:etc_t:s0 nametype=NORMAL
type=CWD msg=audit(1395152245.230:533):  cwd=”/etc/audit”
type=SYSCALL msg=audit(1395152245.230:533): arch=c000003e syscall=188 success=yes exit=0 a0=d14410 a1=7f66eec38db7 a2=d4ea60 a3=1c items=1 ppid=1109 pid=4900 auid=0 uid=0 gid=0 euid=0 suid=0 fsuid=0 egid=0 sgid=0 fsgid=0 tty=pts0 ses=2 comm=”vi” exe=”/bin/vi” subj=unconfined_u:unconfined_r:unconfined_t:s0-s0:c0.c1023 key=(null)
```

El tiempo del evento y el nombre del objeto, el directorio de trabajo(cwd),
llamadas al sistema, ID usuario auditado (auid) y el ejecutable (exe) que ejecuta
la acción sobre el fichero.

Hacemos lo siguiente:
* Crear los usuarios `rebelde1`, `rebelde2` y `rebelde3`.
* Crear el fichero `\home\estrellita.txt` con permisos para todos los usuarios.
* Auditar los accesos al fichero anterior.
* Acceder al fichero con los usuarios `rebelde1` y `rebelde2`.
* Consultar la auditoria sobre el fichero.
* Crear un informe sobre los ficheros (`aureport -f`). Este comando genera una lista
nemrada de todos los eventos asociados a ficheros incluyendo fecha, hora, nombre
del fichero, número de llamadas al sistema, éxito/fallo del comando, el ejecutable
que lo accedió, un ID y número de evento.

## 3.3 Auditar procesos en Linux

Audit tiene la herramienta autrace que es similar a strace.
Veamos un ejemplo:

```
root@host:~# autrace /bin/ls /tmp
autrace cannot be run with rules loaded.
Please delete all rules using ‘auditctl -D’ if you really wanted to
run this command.
root@host:~# auditctl -D
No rules
root@host:~# autrace /bin/ls /tmp
Waiting to execute: /bin/ls
atop.d  mc-root  mongodb-27017.sock  suds
Cleaning up…
Trace complete. You can locate the records with ‘ausearch -i -p 20314’
```

Mostrar los eventos capturados usando ausearch:

```
root@host:~# ausearch –start recent -p 21023 –raw | aureport –file –summary

File Summary Report
===========================
total  file
===========================
1  /bin/ls
1  (null) inode=1975164 dev=08:02 mode=0100755 ouid=0 ogid=0 rdev=00:00
1  /etc/ld.so.cache
1  /lib/x86_64-linux-gnu/libselinux.so.1
1  /lib/x86_64-linux-gnu/librt.so.1
1  /lib/x86_64-linux-gnu/libacl.so.1
1  /lib/x86_64-linux-gnu/libc.so.6
1  /lib/x86_64-linux-gnu/libdl.so.2
1  /lib/x86_64-linux-gnu/libpthread.so.0
1  /lib/x86_64-linux-gnu/libattr.so.1
1  /proc/filesystems
1  /usr/lib/locale/locale-archive
1  /tmp
```

Hacer lo siguiente:
* Activar auditoría sobre el programa/comando `mkdir`.
* Crear el directorio `/home/rebelde1/rogue-one`.
* Consultar informe de auditoría.
* Crear un informe de los evecntos de todos los ejecutables (`aureport -x`).
Este comando genera una lista numerada de todos los eventos de ejecutables
incluyendo fecha, hora, nombre del ejecutable, terminal donde se ejecuta, el
host, ID y número de evento. Ver ejemplo:
```    
Executable Report
====================================
# date time exe term host auid event
====================================
1. 13/02/09 15:08:26 /usr/sbin/sshd sshd 192.168.2.100 -1 12
2. 13/02/09 15:08:28 /usr/lib/gdm/gdm-session-worker :0 ? -1 13
3. 13/02/09 15:08:28 /usr/sbin/sshd ssh 192.168.2.100 -1 14
```

## 3.4 Auditar acceso a fichero

Si se quiere saber que ficheros han sido accedidos por un usuario (UID) concreto
`auditctl -a exit,always -F arch=x86_64 -S open -F auid=80`

Explicación de los parámetros:
* -F arch=x86_64, define la arquitectura (uname -m)
* -S open, elige las llamadas “open” al sistema
* -F auid=80, el UID del usuario

Este tipo de información es realmente úitl para la detección de intrusos, también
incluso cuando se ejecutan procesos de análisis forense.

---

# ANEXO

## Converting system calls

Syscalls are logged by an numeric value. Since there will be an overlap in these values between different architectures, the active architecture is also logged.

By using uname -m we can determine the architecture and use ausyscall to determine what numeric call 188 represents.

    [root@host audit]# ausyscall x86_64 188
    setxattr

We now know it was a change in attribute, which makes sense as we defined our watch to trigger an event on an attribute change (perm=a).

Used a temporary rule and want to use the old rules again? Refresh the audit rules from a file:

    auditctl -R /etc/audit/audit.rules

## Systemd y journal

Enlace de interés:
* [OpenSUSE systemd journal](https://es.opensuse.org/SDB:Systemd_journal)
* [Ver los logs del sistema en Linux con journalctl](http://lamiradadelreplicante.com/2015/03/29/ver-los-logs-del-sistema-en-linux-con-journalctl/)
