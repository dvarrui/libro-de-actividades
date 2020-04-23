
```
Última modificación: domingo, 18 de mayo de 2014, 12:22
```

# INFO: Creación de usuarios

Vamos a crear un script crear-usuarios.sh, que se encargará de la creación y personalización de un bloque de usuarios de forma masiva.

# Descripción: Creación de usuarios

En un fichero "listado-de-usuarios.txt", tendremos una lista de los usuarios que queremos crear y su grupo principal. Por ejemplo: "alumno1:alumnos".
El script leerá cada línea del fichero "listado-de-usuarios.txt", y extraerá la información de usuario y grupo.
* Crear el grupo si no existe. Consultar comandos groupadd y addgroup.
* Crear el usuario si no existe. Consultar comandos newusers, useradd y adduser.
* Crear en la carpeta del usuario ($HOME) los directorios siguientes, si no existen:
    * public_html: permisos 755
    * zona_comun: permisos 750
    * zona_privada: permisos 700.
* Establecer una clave aleatoria al usuario y registrar el "usuario:clave" en un fichero llamado "claves-de-usuario.txt" (Consultar script de ejemplo).
* [OPCIONAL] Establecer la cuota del usuario a 700 MB.

---

# Ventanas

Utilizar Zenity/KDialog para incorporar ventanas gráficas.

* Al principio del script se mostrará una ventana para seleccionar el ficheros de carga (Consultar comando zenity --file-selection).
* Al final del script para mostrar una ventana informativa de finalización (Consultar comando zenity --info).

---

# ANEXO

Ejemplo: script para poner claves

```
#!/bin/bash

show_ayuda() {
  echo "USO: $0 grupo"
  echo "DESCRIPCIÓN: Este script regenera las claves para un grupo de usuarios"
}

if [ $# -eq 0 ]; then
  show_ayuda
  exit 1
fi

# Inicializar el fichero de claves
FILE=/home/profesores/david/claves-$1.txt
echo "Generando claves para $1" > $FILE
date >> $FILE

# Establecer claves para los usuarios
USUARIOS=`ls /home/alumnos/$1* -d`
for I in $USUARIOS; do
  # Generar la clave
  USER=`basename $I`
  CLAVE=`pwgen -cn 8 1`
  # Registrar usuario/clave
  echo "Usuario ${USER}, clave ${CLAVE}" >> $FILE
  # Actualizar la clave
  echo "${USER}:${CLAVE}" | chpasswd
  # echo -e "${CLAVE}\n${CLAVE}" | (passwd --stdin $USER)
done
```
