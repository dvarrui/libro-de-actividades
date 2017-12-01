
# Requisitos

* MV Windows7 Professional [configurada](../../global/configuracion/windows.md).
* MV OpenSUSE [configurada](../../global/configuracion/opensuse.md).
* Acceso remoto telnet en Windows7 y SSH en OpenSUSE.

# Windows 7

* OJO. Usar minúsculas.
* Crear los grupos:
    * personajes
    * humanos
    * enemigos
* Crear usuarios:
    * mario
        * Miembro de personajes y humanos
        * Debe existir su directorio HOME
    * princesa
        * Miembro de personajes y humanos
        * Debe existir su directorio HOME
    * tortuga
        * Miembro de personajes y enemigos
        * Debe existir su directorio HOME
    * seta
        * Miembro de personajes y enemigos
        * Debe existir su directorio HOME
* Crear directorios:
    * c:\Users\mario\casa.d
        * Permiso control total a mario
        * Permiso control total a sysadmingame
        * Nadie más tendrá permisos
    * c:\Users\mario\plataformas.d
        * Permiso control total a mario
        * Permiso control total a sysadmingame
        * Permiso lectura/mostrar a Todos
        * Nadie más tendrá permisos

# OpenSUSE

* OJO. Usar minúsculas.
* Crear los grupos:
    * personajes
    * humanos
    * enemigos
* Crear usuarios con password 123456
    * mario
        * Grupo principal humanos
        * Miembro de personajes
        * El directorio home será /home/mario
        * Debe existir su directorio HOME
    * princesa
        * Grupo principal humanos
        * Miembro de personajes
        * El directorio home será /home/castillo/princesa
        * Debe existir su directorio HOME
    * tortuga
        * Grupo principal enemigos
        * Miembro de personajes
        * El directorio home será /home/tortuga
        * Debe existir su directorio HOME
    * seta
        * Grupo principal enemigos
        * Miembro de personajes
        * El directorio home será /home/seta
        * Debe existir su directorio HOME
* Crear directorios:
    * /home/mario/casa.d
        * Permiso control total a mario
        * Ninguń permiso a nadie más.
    * /home/mario/plataformas.d
        * Todos los permisos a propietario, grupo y otros.
* Software
    * Instalar los paquetes: nano y tree.
    * Desintalar los paquetes: geany y wget.
