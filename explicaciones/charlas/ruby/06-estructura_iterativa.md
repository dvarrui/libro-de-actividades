
# I Love Ruby

![](./images/caminar.png)

---

# Estructura iterativa (I): los bucles

Ejemplo 1:
```
i = 0
while (i<10)
  puts i
  i++
end

# <i> es un objeto de tipo Integer
```

Ejemplo 2:

```
for i in 0..9
  puts i
end

# <0..9> es un objeto de tipo Range
```

Ruby tiene integrado completamente el paradigma de programación orientado a objetos.

> TODO en Ruby son objetos. Herencia de Smalltalk y Eiffel.

---

# Estructura iterativa (II): iterar

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

---

# Estructura iterativa (III): los arrays

Ejemplo 1:

```
a = Array.new
a << 4
a << 5
a << 6

puts a
```
* <a> es [3,14,27]
* <a> es un objeto Array

Ejemplo 2:

```
a = [3, 14, 27]
a.each do |i|
  a << 3
  puts i
end

puts a
```

* <each> es un método del objeto a
* <a> es un objeto Array
* Estilo de programación funcional.

> El iterador por todas partes...
