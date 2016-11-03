
#Formatos de la particiones

Supongamos que tenemos un disco con 16 sectores.
* Cada sector del disco tienen tamalo 512 bytes.
* Por lo tanto nuestro disco es de 16*512 bytes = 8*1024 bytes = 8 KiB.
* Los sectores se numeran empezando por el cero.
* El sector 0 contiene el MBR (Master Boot Record). El MBR guarda información
de las particiones del disco, y por tanto, no se puede usar para almacenar datos
de usuario.
* Si creamos una única partición que ocupe todo el disco (Excepto sector 0),
tenemos una partición de tamaño 15 sectores.

Cuando le damos formato (formatear) al disco, éste pierde capacidad de almacenamiento
puesto que el propio sistema de formateo debe usar parte de ese espacio en disco.
* Supongamos que nuestro sistema de formateo ocupa sólo el sector 1 del disco.
* Ahora tenemos una capacidad de almacenamiento para el usuario de 14 sectores.

#Asignación de Bloques Adyacentes

* En el sector 1 del disco se guardará la `Información del formato ABA`. Esto es,
una tabla con `name;start;size`. Donde:
    * `name` es el nombre del fichero que se guarda.
    * `start` es el número del sector donde se comienza a guardar el fichero.
    * `size` es el número de sectores que ocupa el fichero.
* La asignación de espacio en disco al fichero es por sectores consecutivos o
adyacentes en este sistema de formateo.
* Cuando el fichero no consume completamente el último sector. Dicho espacio queda
libre. Sólo lo puede usar el fichero asignado cuando aumente su tamaño. A esta
situación se le llama `fragmentación interna`.
* A medida que se van creando más ficheros, ampliando/reduciendo los tamaños,
borando ficheros... en el disco van apareciendo sectores libres no consecutivos
o adyacentes.
