
```
Descripción : Instalación desatendida en OpenSUSE 13.X
Requisitos  : OpenSUSE 13.X
```

---
## A.2 OpenSUSE 13.2 (modo 1)

`Documentación antigua (curso1516), sobre el proceso de creación del fichero XML en OpenSUSE 13.2`.

En este caso actualizamos XML con la siguiente información:

* Seleccionar los paquetes instalados yendo a la sección Software -> Selección de paquetes -> Clonar
* Seleccionar las particiones yendo a la sección Hardware -> Partitioning -> Clonar
* Seleccionar el boot loader yendo a la sección System -> BootLoader -> Clonar
* Seleccionar fecha/hora yendo a la sección System -> Date and Time -> Clonar
* Seleccionar el idioma yendo a la sección System -> Languages -> Clonar.
* Seleccionar la configuración de red yendo a la sección Network Devices -> Network Setting -> Clonar
* Seleccionar los usuarios y grupos yendo a la sección Security and Users -> User and Group Managent -> Clonar
* Al terminar de "clonar" los datos que nos interesan vamos a grabarlos en un XML,
vamos a File -> Save as. Y lo grabamos con "nombre-del-alumno.xml".

## A.2 OpenSUSE 13.2 (modo 2)

`Documentación antigua (curso1516), sobre el proceso de creación del fichero XML en OpenSUSE 13.2`.

Con OpenSUSE 13.2 podemos hacer una nueva instalación en MV y guardar el fichero `autoyast.xml` durante el proceso.

* Incluir los programas/paquetes siguientes: tree, nmap, traceroute, vim, ruby, geany, putty, minicom, gtk-recordmydesktop.
* Crear el usuario `nombre-alumnoXX`.
* Configurar el nombre de máquina con `primer-apellido-alumnoXX`.
* Configurar dominio con `curso1516`.
* Asegurarse de que se guarda el fichero `autoyast.xml` durante el proceso.
Este fichero guarda las decisiones que tomamos sobre la configuración de nuestra instalación.

`autoyast.xml`  es  nuestro "Control File".
Esto es, un fichero XML con las definiciones que elijamos para nuestra instalación desatendida.
