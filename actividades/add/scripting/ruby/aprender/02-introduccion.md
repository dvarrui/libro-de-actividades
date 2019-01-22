
# Ruby: scripting

![](../images/iloveruby.png)

---

# 1. Estilos de programación:

Paradigmas:
* Imperativa estructurada
* Imperativa modular
* Imperativa Orientada a Objetos
* Declarativa funcional
* Declarativa lógica
* Orientada a eventos
* Concurrente

Dominios de aplicación:
* Programación "clásica"
* Programación web
* Programación multimedia
* Programación científica
* Programación gráfica
* Programación de videojuegos
* Programación de sistemas embebidos.
* _Programar **scripts** de sysadmin/devops_.

> Sysadmin/Devops: ¡Somos PROGRAMADORES de scripts!

---

# 2. ¿Qué es hacer scripting?

**Scripting language from Wikipedia**

* A scripting or script language is a **programming language that supports scripts** — programs written for a special run-time environment that **automate the execution of tasks** that could alternatively be executed one-by-one by a human operator.
* Scripting languages **are often interpreted (rather than compiled)**.
* Primitives are usually the elementary tasks or API calls, and the language allows them to be combined into more complex programs.
* Environments that can be automated through scripting include software applications, web pages within a web browser, usage of the shells of operating systems (OS), embedded systems, as well as numerous games.
* A scripting language can be viewed as a domain-specific language for a particular environment; in the case of scripting an application, it is also known as an extension language.
* Scripting languages are also sometimes referred to as **very high-level programming languages, as they operate at a high level of abstraction, or as control languages, particularly for job control languages on mainframes**.
* The term "scripting language" is also used loosely to refer to **dynamic high-level general-purpose languages**, such as Perl, PowerShell, Python, and Tcl with the term "script" often used for small programs (up to a few thousand lines of code) in such languages, or in domain-specific languages such as the text-processing languages sed and AWK.

---

# 3. ¿Para qué le interesa a un sysadmin?

* Regla nº1: Automatizar.
* Regla nº2: Primero aprender regla nº1.

> Automatizar es uno de nuestros superpoderes.

---

# 4. Ejemplos

**Scripting: características**

* Lenguaje interpretado.
* Programa claro, sencillo y limpio.
* Documentar sólo si es necesario (no debería serlo)
* Cada fichero... un cometido (una misión u objetivo concreto)
* Sólo mostrar mensajes cuando hay problemas.
* Códigos de salida: `0 = OK`, `1 = Error`
* Programas NO interactivos. Nuestro objetivo es automatizar.

> Lenguajes libres para hacer scripting multiplataforma Bash, Perl, Python... Ruby

Vamos a seguir aprendiendo con ejemplos reales.
Viendo código desde los más sencillo y que iremos complicando
poco a poco.

* [show-info](./03-show-info): Hacer un que muestre alguna información del sistema en pantalla.
* [do-undo](./04-du-undo): Hacer uns script que cree directorios con determinados permisos.
* [radar](./05-radar): Hacer un script para "monitorizar" ciertos hosts.
* [software](./06-software): Configurar nuestra máquina con determinado software.

---

# ANEXO

**Enlaces de interés:**
* Andrew Mallett (@theurbanpenguin): Ruby Scripting in Linux - See our mini-series of video tutorials!
    * http://ow.ly/BYIi30nifgZ
    * https://www.theurbanpenguin.com/scripting-power-repetition/ruby-scripting-linux/
