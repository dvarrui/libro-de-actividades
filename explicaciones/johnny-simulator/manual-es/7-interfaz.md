
[INDICE](./README.md)

# 7. El interfaz de usuario

Los botones se organizan en dos grupos (Macro código y Micro código),
el último de los cuales sólo están visibles si la Unidad de Control
se muestra en detalle.

|Imagen   | Significado de los botones del grupo de Macro Código|
|-------- | -----------|
|![1](./imagen/7-ram_to_zero.png)  | Pone la RAM completamente a zero (00 000 para cada dirección) |
|![2](./imagen/7-open_program.png) | Abre un programa del disco duro |
|![3](./imagen/7-save_program.png) | Graba un programa en el disco duro |
|![4](./imagen/7-execute_next.png) | Ejecuta la siguiente macro instrucción de la RAM |
|![5](./imagen/7-execute.png)      | Ejecuta el programa de forma automática (la velocidad puede controlarse mediante la barra de scroll y el botón derecho) |
|![6](./imagen/7-stop.png) | Detiene la ejecución del programa |
|![7](./imagen/7-set_program_counter.png) | Pone a cero al contador de programa y a el resto de registros |
|![8](./imagen/7-show_options.png) | Muestra la ventana de opciones |

El botón ![detail](./imagen/7-detail.png) se usa para mostrar todos los detalles de la
Unidad de Control. Si está activo se verán los siguientes botones:

|Imagen   | Significado de los botones del grupo de Micro Código|
|-------- | -----------|
|![1](./imagen/7-open_micro.png)  | Abrir in fichero con micro código desde el disco duro |
|![2](./imagen/7-save_micro.png)  | Guarda el micro código actual en un fichero del disco duro |
|![3](./imagen/7-execute_1_micro.png)  | Ejecuta una micro instrucción |
|![4](./imagen/7-record_micro_sequence.png)  | Graba una secuencia de micro instrucciones para formar una nueva macro instrucción |

Si haces click sobre una dirección de la RAM, aparece una ventana donde se
puede modificar el valor, o se puede elegir una de las macro instrucciones desde
un menú de selección. La direcciñon se puede escribir usando el teclado real
o el virtual. Un doble click sobre la dirección establece el valor a cero.

|Imagen   | Significado de los botones|
|-------- | -----------|
|![1](./imagen/7-location_to_zero.png)  | El contenido de la dirección se pone a cero |
|![2](./imagen/7-write_to_ram.png)  | Los cambio se escriben en la direccion respectiva |
|![1](./imagen/7-cancel.png)  | El valor de la dirección permanece sin cambios |


Usando el botón derecho del ratón aparece un menú conextual. Este menú permite
la inserción y el borrado de la RAM.

![Insert delete cell](./imagen/7-insert_delete_cell.png)

Con una inserción, el bloque de la siguiente dirección con valor no cero
se desplaza hacia abajo una posición; se elimina una dirección con valor cero
al final del bloque. De forma similar, con un borrado el bloque con valor no cero
se desplaza hacia arriba y una dirección con valor cero se inserta
debajo del bloque.
