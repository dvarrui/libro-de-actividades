
[INDICE](./README.md)

# 5. El conjunto de macro intrucciones standard

El almacén de micro instrucciones contiene los microprogramas para las 10 
macro instrucciones siguientes:
* `TAKE` El contenido de la dirección (proporcionado por la direccion absoluta) 
se pasa al acumulador.
* `SAVE` El contenido del acumulador se pasa a la dirección dada por la dirección absoluta.
* `ADD` El coThe value of a location (given by the absolute address) is added to the value
in the accumulator.
● ADD ● SUB ● INC The value of the location (given by the absolute address) is incremented.
● DEC The value of the location (given by the absolute address) is decremented.
● NULL The value of the location (given by the absolute address) is set to zero.
● TST ● JMP The program is continued at the given location.
● HLT The simulator shows a message that the program is finished.
The value of a location (given by the absolute address) is subtracted from the
value in the accumulator.
If and only if the location (given by the absolute address) has a zero value,
the next macro instruction is skipped.
(This is a substantial change compared to version 0.98 of the simulator where the
value of the accumulator has been tested directly. The change has been made for
the sake of uniformity.)
