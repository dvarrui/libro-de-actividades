
#Aula 206

Documento sobre la configuración software/hardware del aula 206, 
realizada en junio de 2016.

#Historia

| Fecha      | Acción | Responsable |
| :--------: | :----- | :---------- |
| 2016-06-02 | Se instala el equipo `PC1` del aula 206 , con GNU/Linux | David |
| 2016-06-17 | Se clonan los equipos `PC2`, `PC3`, `PC4` y `PC5` | David |
| 2016-06-20 | Se solicita al técnico informático del centro la clonación del resto de equipos | David |


#Clonación

* En `/etc/fstab`, se modifica el identificador de montaje para la swap. 
Se cambia el UUID por el nombre dispositivo en lugar de UUID, para facilitar las clonaciones.
* Realizamos la clonación con Clonezilla.
* La imagen está disponible para su descarga en el servidor LEELA.
    * `http://leela/~general/aulas/aula206`,
    * `http://172.20.1.2/~general/aulas/aula206`

#Configuración

* Se instala el sistema operativo Xubuntu 14.4 LTS.
    * Sabemos que el soporte de esta versión acaba el 2017 y que existe 
    la versión 16 LST, pero de momento queremos mantener el mismo SO/versión
    en las aulas 103,108,109,206 y comodín1.
* Se instala el software según [script-instalar-aula206.rb](./files/script-instalar-aula206.rb).

> **TODO**
> Falta incluir en el script:
> * Instrucciones para instalar VirtualBox
> * Instrucciones para instalar algunos programas Windows que se instalan 
y ejecutan mediante el emulador wine. Estos son: SketchUp, VirtualDub.

Esquema de particiones MBR:

| Partición | Montaje | Uso  | Tamaño |
| :-------- | :------ | :--- | -----: | 
| /dev/sda1 |         | Swap |   4 GB |
| /dev/sda2 | /root   | Sistema Operativo | 28 GB |
| /dev/sda3 | /home   | Datos de los usuarios| 200 GB |

Usuarios configurados:

| Usuario | Uso  |
| :------ | :--- |
| alumno  | Para usar programas emulados con wine (Tecnología). Los datos se borran al reiniciar la máquina |
| guest   | Para usar en la ESO y Bachillerato. Los datos se borran al cerrar la sesión |
| dam     | Para usar en FP Informática. Los datos se guardan en el disco |
| super   | Sólo para tareas de mantenimiento del sistema |
| root    | No usar en entorno gráfico. Tareas de mantenimiento del sistema |

Ficheros personalizados:

```
/home/guest/
├── alumno-con-cocodrile.tar
├── alumno-sin-cocodrile.tar
├── alumno.tar
├── last_execution.dat
├── reset-user.rb
└── script-instalar-aula206_v3.rb
``` 

* Los ficheros `alumno*.tar` son distintas versiones de los datos para `/home/alumno`. En el aula206
tenemos una versión sin Cocodrile (por limitaciones en la licencia) y con VirtualDub (por requeirimientos
de informática).
* En cada reinicio de la máquina se ejecuta el script `reset-user.rb`.
    * En el fichero /etc/rc.local se incluye orden para ejecutar el script.
    * Este script restaura el home (`/home/alumno`) de usuario alumno.
    * Consultar script [reset-user.rb](./files/reset-user.rb).

#Inventario

| PC  | RAM | HDD  | MAC | CPU  | Estado |
| --: | --: | ---: | :--: | :--- | :------ |
| 1   |   4 GB | 250 GB | 00-18-71-71-10-f5 | Pentium 3.2 GHz | Pdte. activar VTx en BIOS |
| 2   |   4 GB | 250 GB | 00-40-f4-64-f8-01, 00-18-71-71-11-43 |  | Pdte. activar VTx en BIOS, Tiene 2 tarjetas de red |
| 3   |   4 GB | 250 GB | 00-06-4f-09-e2-99, 00-18-71-71-3e-ce |  | Pdte. activar VTx en BIOS, Tiene 2 tarjetas de red |
| 4   | 3.5 GB | 232 GB | 00-40-f4-be-c2-ba, 00-18-71-71-2f-5a |  | Pdte. activar VTx en BIOS, Tiene 2 tarjetas de red |
| 5   | 3.5 GB | 250 GB |  |  | Pendiente |
| 6 | GB | GB |  |  | Pendiente |
| 7 | GB | GB |  |  | Pendiente |
| 8 | GB | GB |  |  | Pendiente |
| 9 | GB | GB |  |  | Pendiente |
|10 | GB | GB |  |  | Pendiente |
|11 | GB | GB |  |  | Pendiente |
|12 | GB | GB |  |  | Pendiente |
|13 | GB | GB |  |  | Pendiente |
|14 | GB | GB |  |  | Pendiente |
|15 | GB | GB |  |  | Pendiente |
|16 | GB | GB |  |  | Pendiente |
|17 | GB | GB |  |  | Pendiente |
|18 | GB | GB |  |  | Pendiente |
|19 | GB | GB |  |  | Pendiente |
|20 | GB | GB |  |  | Pendiente |
