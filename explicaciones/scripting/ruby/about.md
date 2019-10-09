
# About Ruby

```
class HelloWorld
  def self.hello(name = 'World')
    "Hello, #{name}!"
  end
end
```

Ruby is a dynamic, open source programming language with a focus on simplicity and productivity.
It has an elegant syntax that is natural to read and easy to write.

Ruby was created as a language of careful balance. Its creator, Yukihiro *“Matz”* Matsumoto,
blended parts of his favorite languages (Perl, Smalltalk, Eiffel, Ada, and Lisp)
to form a new language that balanced functional programming with imperative programming.

He has often said that he is *"trying to make Ruby natural, not simple,"*
in a way that mirrors life.

Building on this, he adds: *"Ruby is simple in appearance, but is very complex inside, just like our human body."*

---

# Características

* Es un lenguaje interpretado creado en 1993.
    * Inspirado en Smalltalk y Perl.
    * Implementaciones: MRI, jruby, mruby, rbx, ree, truffle-ruby, etc.
    * Lenguajes compilados: Java, C/C++, etc.
* Filosofía:
    * Principio de menor sorpresa.
    * Buscar la felicidad del programador.
* Reflexivo
    * Capacidad que tiene un programa para observar y opcionalmente modificar su estructura de alto nivel.
    * Normalmente, la reflexión es dinámica o en tiempo de ejecución, aunque algunos lenguajes de programación permiten reflexión estática o en tiempo de compilación.
* POO (Programación Orientada a Objetos)
    * TODO en Ruby es un objeto.
    * Todos los datos son objetos.
    * La variables son referencias a objetos (Object#object_id).
* Tipado fuerte y dinámico.
* Herencia con enlace dinámico.
    * Se escoge, en tiempo de ejecución. Es útil cuando este no puede ser determinado de forma estática, es decir, en tiempo de compilación.
    * Esta característica de la POO permite definir varias implementaciones usando la misma interfaz, por tanto el enlace Dinámico constituye un tipo de polimorfismo. 
