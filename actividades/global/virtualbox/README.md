
# VirtualBox

* [Debian](debian.md)
* [OpenSUSE](opensuse.md)

## Ampliar un disco de VBox

Pasos para ampliar un disco duro ya existente de una MV.
1. En VirtualBox, ir a `Archivo=>Administrador de medios virtuales`, una vez ahí ir al disco duro que queremos ampliar, seleccionarlo y ampliar el tamaño. Si tenemos instantáneas de la MV también tenemos que ampliar el tamaño de las instantáneas.
2. Iniciar la MV con el live CD KNOPPIX. Abrimos GParted, si todo funciona correctamente, GParted debería mostrar un error alertando del uso de espacio del disco duro, si es así darle al botón "Fix" o su equivalente.
3. A continuación debería aparecer la tabla de particiones con el disco duro ampliado, modificamos la cantidad de espacio que nos interese del disco, guardamos los cambios y reiniciamos la MV.
4. Comprobar que se ha ampliado el disco correctamente.
