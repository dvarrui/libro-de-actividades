
#4. El procesador
El procesador consta de tre partes: La Unidad Aritmético Lógica,
la memoria (RAM) y la Unidad de Control. Estas unidades están
interconectadas por los buses.

![procesador](./4-procesador.png)

A continuación se explica cada unidad en detalle.

##4.1 La memoria (RAM)
La memoria de acceso aleatorio (RAM) consta de 1000 posiciones,
cada una con la posibilidad de almacenar números de 0 a 19999.
De modo qie tres dígitos decimales son suficientes para direccionar
cada posición de memoria.

La posición 10000 y la 1000 están separadas un bit de otras posiciones
puesto que representan el código de operación de las macro instrucciones.

Hay dos botones que se usan (disparando las microinstrucciones `ram->db` y `db->ram`)
para mover los datos desde la localización direccionada al bus y viceversa.

![memoria](./4.1-memoria.png)

Las posiciones se pueden editar usado en GUI; mediante el menú desplegable se pueden
elegir las macro intrucciones. En el GUI, se muestran dos secciones de memoria RAM
(Las cuales se pueden superponer). De esta forma se pueden mostrar simultáneamente
las instrucciones y los datos afectados.

##4.2. La Unidad Aritmético Lógica
La Unidad Aritmético Lógica consta simplemente del acumulador.
El acumulador puede resetearse ( acc:=0 ), incrementarse ( acc++ ), y
decrementarse ( acc-- ). `db->acc` mueve la palabra de datos desde el
bus al acumulador; `acc->db` hace la acción opuesta.
Se puede sumar ( plus ) un valor desde el bus o restarlo ( minus ).

![alu](./4.2-alu.png)


En el modo BONSAI (Que se explica en la sección  9) se han eliminado 
algunas de estas micro instrucciones.
