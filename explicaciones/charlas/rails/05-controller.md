
# Rails

![](images/koji-kabuto.png)

# Controlando al controlador

> Recordemos que estamos dentro de un MVC

* Modificar el controlador `app/controllers/welcome_controller.rb`:
```
  def greet
    @name = 'Mazinger-Z'
  end
```
* Modificar la vista `app/views/welcome/greet.html.erb`:
```
<p>Hello <%= @name %>!</p>
<p>Now. Your are into... MY CONTROLLER! Hahahaha!</p>
```
* Probamos URL `camaleon:3000/hello`

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
