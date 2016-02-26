
#Clonezilla device-device

Vamos a hacer una práctica de clonación usando el software Clonezilla.

##1 Entrega

* Trabajar en parejas.
* Entregar un PDF con el informe del trabajo realizado acompañado de capturas
de pantalla/fotos.


##2 Pasos

1. Coger una máquina del taller. Debe tener una partición de 20GB con el So OpenSUSE 13.2.
Usuario profesor/profesor y usuario root/profesor.
1. Crear una segunda partición del mismo tamaño.
1. Obtener un CD/DVD con el Clonezilla. Descargar ISO de Leela y quemar un CD/DVD.
1. Iniciar el PC con la distro Clonezilla.
1. Realizar clonación tipo *device-device*.
1. Modificar el gestor de arranque para que detecte los dos SO instalados.
    * `cp /boot/grub2/grub.cgf /boot/grub2/grub.cgf.000`
    * `grub2-mkconfig -o /boot/grub2/grub.cgf`
1. Iniciar el 2º SO, grabar archivos en /home/profesor/Documentos
1. Iniciar el 1er SO, comprobar que no hay nada en /home/profesor/Documentos.

