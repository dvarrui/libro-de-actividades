Johnny 1.01
Simulation of a
Simplified von Neumann Computer
Peter Dauscher, 2009-2014

#1. Remark
El autor de este manual no habla Inglés de forma nativa. De modo que podrás encontrar 
errores ortográficos, gramaticales y de otros tipos, por favor, informa de ellos para mejorar
este manual de usuario. ¡Muchas gracias por adelantado!

Peter Dauscher
peter.dauscher@gmail.com

#2. Introduccion
La mayoría de los ordenadores actuales están basados en la arquitectura de Von Neumann.
A causa de la miniaturización, la mayoría de los procesos de los ordenadores modernos
están escondidos para el usuario y no pueden ser observados. Para esto, la simulación
es un método poderoso para dar a los estudiantes una visión sobre lo que ocurre
en los ordenadores

JOHNNY ha sido desarrollado especialmente con un propósito educativo, y como
resultado tiene algunas simplificaciones sustanciales en comparación 
con los equipos reales.

#3. Simplificaciones
* El simulador puede ser observado a nivel de microinstrucciones y macroinstrucciones.
La estructura interna de la Unidad de Control puede ocultarse para hacer las cosas
más simples al principio.
* Los usuarios pueden programar el simulador usando un lenguaje estilo ensamblador.
Las macroinstrucciones se pueden escribir directamente en la memoria usando el
GUI de modo que la sintaxis se descartan durante la construcción.
* La Unidad Aritmético Lógica consiste en un único registro que actúa como acumulador.
* El simulador y su GUI usan el sistema decimal. Aunque esto no es muy realista en
un sentido técnico, simplifica el uso especialmente para aquellos que no están familiarizados
con el sistema hexadecimal: De modo que, en Johnny, 9+8 es igual a 17 y no 0x11.
* El rango de datos es 0..19999; el rango de direcciones es 0..999. No está permitido el
desbordamiento: 0-1 → 0 y 19999+1 → 19999.
* El conjunto de instrucciones es muy pequeño, con sólo 10 macroinstrucciones.
Por motivos de simplicidad, todas las instrucciones usan direccionamiento absoluto,
con una dirección por instrucción. El código de operación viene determinado por
la posición de decenas de millar y millares; el resto de posiciones representa
la dirección. (ADD 42, por ejemplo se representa como 02 042).

● In real processors, a micro instruction activates, in general, several lines of the con-
trolling bus. Activating more than one line in real processors, however, can easily ef-
fect bus errors if not performed properly. Therefore, a (quite unrealistic) simplifica-
tion has been made: Each micro instruction corresponds to one single push button
that also can be pushed manually by the user.
● A macro instruction is simply a sequence of such micro instructions. In order to
avoid the necessity for simultaneity, the data bus has the ability to store a data word
(which, of course, is not realistic: real buses are simply lines; storage is done by
processor registers).
Transferring a data word from one place to another via the data bus is performed in
two steps: a) the sender copies the word onto the bus; b) the receiver copies the
word from the bus. Bus conflicts are therefore ruled out by construction.
● Micro Code is editable: The user can create his own macro instructions simply by
choosing an appropriate name and then clicking the sequence of micro instructions
with the mouse.

4. The Processor
The processor consists of three parts: the Arithmetic Logic Unit, the Memory (RAM) and
the Control Unit. These Units are interconnected by busses.
