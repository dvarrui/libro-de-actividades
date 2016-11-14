
# Particiones usando **fdisk**

Realizar las siguientes tareas con el SO Debian que habíamos instalado
anteriormente que no disponía de entorno gráfico:

Entregar documento en formato ODT o PDF con los pasos realizados. Incluyendo capturas de pantalla.

# 1. Añadir disco

Vamos a necesitar 300MB para la siguiente actividad. Si nos ha sobrado espacio en suficiente al final del disco, lo podemos usar ahora. En caso contrario tendremos que añadir un nuevo disco a nuestra máquina.

También podemos añadir un segundo disco duro de 300MB, a nuestra máquina virtual (Para ello apagamos la MV, vamos a configuración -> almacenamiento -> añadir disco duro).

# 2. Particionar

* En el espacio libre del disco, vamos a crear 3 nuevas particiones de 100MB, usando el comando fdisk.
* Nos convertimos a superusuario.
* Con `fdisk -l`, veremos los discos y particiones que tenemos actualmente.
* Escribimos `fdisk /dev/sdb` para empezar a particionar el disco 2.

> Ahora estamos dentro de la aplicación fdsik.
> Con la opción "m", vemos el menú de ayuda.
> Con la opción "p", vemos las particiones que tenemos.
> Con la opción "n", creamos una nueva partición.

* Creamos nueva partición.
* En cilindro de inicio, dejamos el valor por defecto.
* En cilindro de fin o tamaño. Por ejemplo, ponemos +100M, para crear una partición de 100 MBytes.
* Pulsamos "p" para ver lo que tenemos por ahora.
* Repetimos el proceso para las otras 2 particiones.
* Al terminar de crear todas las particiones pulsamos "w" para grabar los cambios realizados.
* Salir del programa.

# 3. Formatear
* Después de crear las particiones, ejecutamos `fdisk -l`, para comprobar que están creadas.
* Si no aparecen las particiones que acabamos de crear, entonces reiniciaremos el SO, y lo volvemos a comprobar.

> Consultar comando mkfs para formatear. Por ejemplo: `mkfs -t ext3 /dev/sdb1`

* Para formatear NTFS habrá que instalar un paquete que no viene por defecto. Para ello hacemos: apt-get install ntfs-3g
* Formatear las particiones de la siguiente forma
```
/dev/sdb1 como ext2
/dev/sdb2 como ntfs
/dev/sdb3 como ext4
```

# 4. Montar manualmente
Ahora vamos a montar las particiones formateadas. Montar significa que vamos a "conectar" con ellas para empezar a usarlas.
* Crear los directorios /mnt/part1, /mnt/part2 y /mnt/part3.

> Estos directorios los usaremos para montar en ellos cada una de las particiones creadas.

* Consultar las particiones montadas actualmente: `df -hT`.
* Montar cada partición en el directorio correspondiente. Para montar las particiones manualmente podemos hacer, por ejemplo: "mount /dev/sdb1 /mnt/part1"
* Consultar el contenido, y el tamaño disponible de las nuevas particiones. Por ejemplo: `df -hT`

> PREGUNTA: ¿Por qué las particiones recien creadas tienen parte del espacio ocupado?.
>
> RESPUESTA: El hecho de formatear una partición hace que ya se consuma un poco de espacio en disco.

* Al reiniciar el equipo comprobaremos que las particiones se han desmontado.

# 5. Montaje automático

Para que las particiones se monten automáticamente al iniciar el equipo, debemos hacer lo siguiente:
* Copia el fichero /etc/fstab con el nombre /etc/fstab.000.

>  Por seguridad vamos a hacer una copia del fichero antes de modificarlo.

* Consultar el contenido del fichero /etc/fstab.
* Modificar el fichero "/etc/fstab" para que las particiones se monten de forma automática al iniciar el equipo en /mnt/part1, /mnt/par2 y /mnt/part3 respectivamente.
* Veamos un ejemplo:
```
/dev/sdb1 /mnt/part1 ext2 defaults 0 1
/dev/sdb2 /mnt/part2 ntfs defaults 0 1
/dev/sdb3 /mnt/part3 ext4 defaults 0 1
```
* Capturar imagen de `/etc/fstab`
