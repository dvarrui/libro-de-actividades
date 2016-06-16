
```
* Documento sobre la configuración del aula 206
``` 

#Aula 206

#Historia

| Fecha      | Acción |
| :--------: | :----- |
| 2016-06-02 | Se instala el equipo `PC1` del aula 206 , con GNU/Linux |

#Clonación

* En `/etc/fstab`, se modifica información demontaje swap con nombre dispositivo en lugar de UUID.
    * Para facilitar las clonaciones.
* Realizamos la clonación con Clonezilla.
* La imagen está disponible para su descarga en el servidor LEELA.
    * `http://leela/~general/aulas/aula206`
    * `http://172.20.1.2/~general/aulas/aula206`

#Configuración

* Se instala el sistema operativo Xubuntu 14.4 LTS
* Se instala el software según este [script](./files/script-instalar-aula206.md).

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
* En cada reinicio de la máquina se ejecuta el script [reset-user.rb](./files/reset-user.rb)
    * Este script restaura `/home/alumno`.

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
