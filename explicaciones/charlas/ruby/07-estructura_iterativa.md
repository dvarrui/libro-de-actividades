
# I Love Ruby

![](./images/caminar.png)

---

# Estructura iterativa: los bucles (II)

Ejemplo 1:

```
10.times do |i|
  puts i
end
```

* <times> es un método del objeto <10>
* <each> es un método
* do ... end es un bloque.

# del Range <0..9>

Ejemplo 2:

```
(0..9).each { |i| puts i }
```

* { ... } es un bloque
* Los bloques son objetos también.

El uso de los bloques permite aplicar el paradigma funcional (al menos en parte).

> ¡El estilo Ruby!
