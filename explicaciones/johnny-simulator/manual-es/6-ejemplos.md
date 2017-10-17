
[INDICE](./README.md)

# 6. Ejemplos de programas simples

## 6.1. Sumando números

El siguiente ejemplo suma los valores de las direcciones <10> y <11>. Almacena
el resultado en la dirección <12>:
```
001: TAKE 010
002: ADD  011
003: SAVE 012
004: HLT  000
```

# 6.2. Multiplicación de números

La multiplicación de números se lleva a cabo haciendo sumando repetidas veces el contenido
de la direccion <10> en la dirección <12>, la cual se inicializada a cero.
```
000: NULL 012
001: TAKE 012
002: ADD  010
003: SAVE 012
004: DEC  011
005: TST  011
006: JMP  001
007: HLT  000
```

En cada ciclo el valor de la dirección <11> se reduce en 1. El bucle continúa hasta que el valor
de la dirección <11> haya llegado al valor 0.
