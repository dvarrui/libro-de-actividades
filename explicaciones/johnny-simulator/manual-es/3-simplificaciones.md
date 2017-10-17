
[INDICE](./README.md)

# 3. Simplificaciones

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
* En los procesadores reales, una micro instrucción activa, por lo general, varias
líneas del bus de control. Activar más de una línea en los procesadores reales
, sin embargo, puede fácilmente provocar errores de bus si no se ejecuta adecuadamente.
Por consiguiente, se ha hecho una simplificación (bastante poco realista): A cada
micro instrucción le corresponde un único botón que puede ser pulsado por el
usuarios manualmente.
* Una macro instruccion es simplemente una secuencia de varias micro instrucciones.
Para evitar simultaneidades inncesarias, el bus de datos tiene la capacidad de
almacenar una palabra de datos (Por supuesto, esto no es realista: los buses
reales son simplemente líneas; los registros del procesador son los que guardan los datos)
La transferencia de una palabra de datos desde un lugar a otro vía el bus de datos se hace
en dos pasos: a) El emisor copia la palabra en el bus; b) El receptor copia la palabra
desde el bus. Los conflictos del Bus se han descartado por diseño.
* El micro código es editable: El usuario puede crear su propio conjunto de macro
instrucciones simplemente eligiendo un nombre adecuado y seleccionando la secuencia
de microinstrucciones con el ratón.
