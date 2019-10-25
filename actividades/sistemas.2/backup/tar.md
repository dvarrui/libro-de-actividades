
```
Estado: En construcción
Objetivo : Copias de seguridad usando tar
```

---

# Copias de seguridad con tar

Enlaces de interés:
* [Backups con tar: fullbackups, incrementales y diferenciales](https://nebul4ck.wordpress.com/2015/03/20/backups-con-tar-full-backups-e-incrementales/)

Preparar una MV a elegir:
1. SO GNU/Linux
2. SO Windows + Cygwin

# 1. Preparativos

| Parámetro  | Valor                          |
| ---------- | ------------------------------ |
| DOCFOLDER  | /home/nombre-del-alumno/mydocs |

* `tar --version`, para comprobar que lo tenemos instalado en nuestro sistema.
* Crear directorio DOCFOLDER.
    * Crear archivo DOCFOLDER/a.txt. Dentro escribir nombre completo del alumno.
    * Crear archivo DOCFOLDER/b.txt. Dentro escribir la fecha actual.
* `cd /home/nombre-del-alumno`

```
mydocs
├── a.txt
└── b.txt
```

---

# 2. Copia de seguridad total (fullbackup)

* `tar cvf backupXX-total-1.tar mydocs`, parea realizar una copia de seguridad total.
* `tar tvc backupXX-total-1.tar`, comprobar el contenido de la copia de seguridad total.
* Crear archivo DOCFOLDER/c.txt. Escribir dentro el título de tu película favorita.
* Realiza copia seguridad total con el nombre `backupXX-total-2.tar`.
* Comprueba el contenido.

Las copias de seguridad total son sencillas de hacer, pero no son eficientes en cuanto a optimizar el almacenamiento. No era necesario volver a copiar los archivos a.txt, ni b.txt porque no han cambiado.

---
# 3. Copia de seguridad incremental
