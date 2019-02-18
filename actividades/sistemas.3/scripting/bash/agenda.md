
# Agenda

Entregar el script agenda.sh.

> Enlaces de interés:
>
> * [Probar scripts de bash](http://www.shellcheck.net/)

---

# Funciones

Tendremos dos ficheros: agenda.sh y agenda.dat.
* El fichero `agenda.dat` será un fichero de texto, que contendrá los datos de nuestra agenda en la forma de `nombre:teléfono`.
* Y donde `agenda.sh` será un shell script.

Funciones que debe implementar el script:
* Cuando el script `agenda.sh` se ejecuta sin parámetros  muestra el menú
con las opciones del script.
* El script trabaja de forma no interactiva.
* Si el fichero agenda.dat no existe se crea automáticamente.
* Opción añadir contacto: El script nos pregunta el nombre y teléfono del contacto y lo añade a nuestra agenda.
* Opción eliminar contacto: El script nos pregunta un nombre o teléfono de un contacto y lo elimina de nuestra agenda.
* Opción buscar contacto: El script nos pregunta la cadena a buscar en la agenda y muestra la información de todos los contactos que coincidan con el criterio de búsqueda, ordenados alfabéticamente.

---

# NOTA

* Para ordenar existe el comando sort.
* Para añadir líneas a un fichero usamos la redirección.
* Para eliminar líneas de un fichero1, podemos crear un fichero2 con las líneas que deben permanecer y luego renombramos fichero2 a fichero1.
* Para eliminar líneas también podemos usar la secuencia siguiente: sed -i "/criterio/d" filename
