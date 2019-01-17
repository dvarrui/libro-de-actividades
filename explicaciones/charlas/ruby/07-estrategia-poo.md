
# I Love Ruby

![](./images/iloveruby.png)

---

# Estrategias para programar

* Estructuras de control (Secuencial, condicional, repetitiva)
* Programación Estructurada: Subrutinas y 3 estructuras básicas.
* Programación modular: dividir en subprogramas.
* POO (Programación Orientada a Objetos)

---

# Diseño de Patrones

![](./images/libro.png)

* ITERADOR
* Singleton
* Decorador
* Builder
* Observer
* Template
* Wrapper
* Etc.

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

* Ruby incluye algunos aspectos del estilo de programación funcional.

| Paradigma  | Descripción |
| ---------- | ----------- |
| Imperativo | decir cómo hacer algo para llegar a un objetivo |
| Funcional  | decir qué queremos conseguir sin especificar el cómo|

> La POO está muy asentada, pero la programación funcional puede darnos algunas facilidades.

---

# Con Ruby salgo a todos lados...

* GUI, CLI, Web (Cliente y Server), Scripting, Etc

> Veamos más ejemplos...
