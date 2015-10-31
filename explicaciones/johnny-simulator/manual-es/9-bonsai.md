[INDICE](./README.md)

#9. El modo BONSAI
En los 90, Klaus Merkert y Walter Zimmer implementaron un simulador similar, BONSAI.
Johnny se puede cambiar al modo BONSAI, para poder usar el conjunto de 
instrucciones BONSAI (el cual consiste de sólo cinco instrucciones 
llamadas INC , DEC , TST , JMP y HLT ).
El menú desplagable se adapta a estas instrucciones, algunas microinstrucciones
(que no se necesitan en este modo) son suprimidas.

#9.1. Cambiando al modo Bonsai
Usando ![Modo Bonsai](./imagenes/9-bonsai.md)

![Opciones](./imagenes/9-options.md)

a window is shown which
enables the user to change
the mode.
Attention: Changing the op-
tion deletes the RAM con-
tent completely.

9.2. Loading and Saving Programs
BONSAI machine programs (.bma files) of the original BONSAI simulator can be opened
and written. Therefore, it is possible to create a BONSAI machine program using JOHNNY
and then transfer it to the original simulator (which is more complex but also more
realistic).
Therefore there are three file formats for RAM content:
.ram Standard JOHNNY RAM content
.bma Standard Bonsai-Machine Programs
.bij Bonsai-Programs saved in Standard JOHNNY format
