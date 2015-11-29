
[INDICE](./README.md)

# 5. El conjunto estándar de macro intrucciones

El almacén de micro instrucciones contiene los microprogramas para las 10 
macro instrucciones siguientes:
* `TAKE` El contenido de la dirección (proporcionado por la dirección absoluta) 
se pasa al acumulador.
* `SAVE` El contenido del acumulador se pasa a la dirección dada por la dirección absoluta.
* `ADD` El contenido de la dirección (dada por la dirección absoluta) 
se suma al valor del acumulador.
* `SUB` El contenido de la dirección (dada por la dirección absoluta)
se resta al valor del acumulador.
* `INC` Se incrementa el valor de la dirección (dada por su dirección absoluta).
* `DEC` Se reduce el valor de la dirección (dada por su dirección absoluta).
* `NULL` Se pone a cero el valor de la dirección (dada por su dirección absoluta).
* `TST` Sólo si el contenido de la dirección (dada por su dirección absoluta) es cero,
entonces se salta la ejecución de la siguiente macro instrucción.
(Este es un cambio sustancial en comparación con la versión 0.98 del simulador,
donde el valor del acumulador se examinaba directamente. El cambio se ha realizado
en aras de la uniformidad.)
* `JMP` El programa salta a la dirección especificada.
* `HLT` El simulador muestra un mensaje indicando que el programa ha terminado.

