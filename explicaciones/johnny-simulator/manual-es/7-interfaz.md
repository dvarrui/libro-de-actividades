
[INDICE](./README.md)

#7. El interfaz de usuario
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
|![8](./imagen/7-show.png) | Muestra la ventana de opciones |

El botón ![detail](./imagen/7-detail.png) se usa para mostrar todos los detalles de la
Unidad de Control. Si está activa se verán los siguientes botones:

Open a micro code file on HD.
Save the current micro code to HD.
Execute a single micro instruction.
Record a micro instruction sequence to form a new macro instruction.
If you click on one RAM location, a window appears where the numerical value can be
changed or a macro instruction can be chosen from a pull-down menu. The address can
be entered by using the real or a virtual keyboard. A double click on the address part sets it
to zero.
