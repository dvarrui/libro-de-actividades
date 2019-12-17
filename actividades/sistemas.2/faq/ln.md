
# ln

`ln` es un comando GNU/Linux para crear enlaces entre ficheros.

**inode**

Es necesario saber que todos los ficheros tienen un identificador numérico. Esta información de identificación se guarda en un bloque de metainformación llamado `inode` (o nodo-i). Para ver el valor o identificador de inode de un fichero podemos usar el comando `ls -i`. Veamos un ejemplo:

```
> ls -li
total 4
667819 drwxr-xr-x 2 david users 4096 dic 17 07:41 endor
651566 -rw-r--r-- 1 david users    0 dic 17 07:42 luke.txt
```

En este ejemplo podemos ver en la primera columna el valor del identificador inode de cada ficheros.

**Enlaces simbólicos**

Con el comando `ln -s` podemos crear enlaces simbólicos. Podemos pensar en ellos como accesos directos. Veamos un ejemplo:
```
> tree
.
├── endor
│   └── ewok.txt
└── luke.txt

> ln -s endor luna-boscosa
> tree
.
├── endor
│   └── ewok.txt
├── luke.txt
└── luna-boscosa -> endor

> ls luna-boscosa
ewok.txt
```
