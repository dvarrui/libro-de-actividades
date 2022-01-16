
# Problemas con Ansible

# 1. Fallo SSH en MV Windows

**Descripción:** Ando haciendo la practica de ansible, y el servidor SSH (de windows) me deniega el acceso y no se si me falta algo.

![](01/problema.png)

**Alumno**: En versiones actuales, las claves públicas no me funcionaban ya que ahora, por defecto, si estás en el grupo de administradores, se almacenan en `C:\ProgramData\ssh\administrators_authorized_keys` en lugar de `C:\Users\..\.ssh\authorized_keys`. Así lo hice, pero seguían sin funcionar.

Lo que hice fue al archivo de configuración y comentar las dos últimas líneas, por si acaso reinicié el servicio usando 'sc stop sshd' y 'sc stop sshd' desde la terminal de legado, cmd, y empezó a funcionar.

![](01/windows-sshd-config.png)


# 2. Fallo SSH en MV Windows

**Descripción:** Tengo un problema en el punto 3 de la actividad de Ansible.

Tengo conectividad en las máquinas y el servicio SSH está en funcionamiento en la MV Slave de Linux como la de Windows.
El problema solo me ocurre con la máquina de Windows.

![](02/problema.png)

**Alumno**: Desde GNU/Linux a Windows no me deja, pero desde Windows a GNU/Linux si. Puse una regla del firewall de Windows para el servicio SSH y tampoco.

Lo arreglé. Me instalé el OpenSSH Server y ya me va perfecto.

![](02/permission-denied.png)

Ahora hay un problema de SSH permisso denegado por las claves SSH.

**Profesor**: El error te está indicando un problema con las claves SSH.
Hay que poder entrar al SSH de Windows desde GNU/Linux sin poner la clave o usar ansible desde GNU/Linux... con el parámetro adecuado... que nos pregunte la clave SSH de Windows como muestra el vídeo.
