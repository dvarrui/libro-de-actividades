
# I Love Ruby

![](./images/libro.png)

---

# Estrategias para programar

* Estructuras de control (Secuencial, condicional, repetitiva)
* Programación Estructurada: Subrutinas y 3 estructuras básicas.
* Programación modular: dividir en subprogramas.
* POO (Programación Orientada a Objetos)
* Diseño de Patrones (POO)
* Algunos aspectos del estilo de programación funcional.

---

# POO: Clases, instancias, atributos

Ejemplo 1:

```
class Persona

  def initialize(name)
    @name = name
  end

  def saludar
    puts “Hola, me llamo ” + @name
  end
end

jedi = Persona.new("Obiwan")
jedi.saludar # => Hola, me llamo Obiwan
```

---

# Imperativo vs funcional

| Paradigma  | Descripción |
| ---------- | ----------- |
| Imperativo | decir cómo hacer algo para llegar a un objetivo |
| Funcional  | decir qué queremos conseguir sin especificar el cómo|

> La POO está muy asentada, pero la programación funcional puede darnos algunas facilidades.

---

# Con Ruby salgo a todos lados...

* GUI, CLI, Web (Cliente y Server), Scripting, Etc

> Veamos más ejemplos...
