
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

#Asignación de Bloques Adyacentes (ABA)

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
o adyacentes. Esto recibe el nombre de `fragmentación externa`. Comunmente llamado
sólo fragmentación. Existen programas que ejecutan el proceso de desfragmentar
el espacio libre y por tanto, eliminan la fragmentación externa.
* Este formato ABA tiene el problema de que se queda inoperativo cuando la
fragmentación externa es muy elevada.

#Asignación de Bloques por Lista Ligada (ABBL)

* En el sector 1 del disco se guardará la `Información del formato ABLL`. Esto es,
una tabla con `name;start`. Donde:
    * `name` es el nombre del fichero que se guarda.
    * `start` es el número del sector donde se comienza a guardar el fichero.
* ABBL se crea con la idea de resolver el problema que tiene ABA cuando el disco
tiene una alta fragmentación y deja de funcionar. Este sistema funcionará con o
sin fragmentación externa.
* Los sectores que tienen 512 bytes, se dividen en dos partes (510 y 2). Donde
se usan 510 para guardar datos del usuario y los 2 bytes sobrantes para guardar
información de los enlaces (ligamientos). Por tanto, se reduce la capacidad de
almacenamiento más que en el sistema ABA.
* La asignación de espacio en disco al fichero es por sectores que no tienen que
ser consecutivos o adyacentes. La sucesión de sectores se refleja en la información
de los enlaces o ligaduras (2 bytes que guardan la identificación del siguiente
sector en el orden establecido por el fichero).
* Entonces no importa que los sectores del mismo fichero estén desordenados
por el disco porque lo enlaces (ligaduras) nos ayudan a identificar la secuencia
ordenada de los mismos.
* Problemas:
    * Perdemos 2 bytes en cada sector
    * Para saber el tamaño de un archivo (en número de sectores) debemos recorrer
    todos los enlaces hasta llegar al final.
* Se podría aplicar un desfragmentado para reducir la `fragmentación externa`.
* La `fragmentación interna` es inevitable.

* Cuando el fichero no consume completamente el último sector. Dicho espacio queda
libre. Sólo lo puede usar el fichero asignado cuando aumente su tamaño. A esta
situación se le llama `fragmentación interna`.
* A medida que se van creando más ficheros, ampliando/reduciendo los tamaños,
borando ficheros... en el disco van apareciendo sectores libres no consecutivos
o adyacentes. Esto recibe el nombre de `fragmentación externa`. Comunmente llamado
sólo fragmentación. Existen programas que ejecutan el proceso de desfragmentar
el espacio libre y por tanto, eliminan la fragmentación externa.
* Este formato ABA tiene el problema de que se queda inoperativo cuando la
fragmentación externa es muy elevada.

#MiniFAT
