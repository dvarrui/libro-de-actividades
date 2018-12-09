
# RAILSINGER-Z

![](images/laboratorio.png)

[Volver](README.md)

# Partials

* Crear una cabecera `app/views/tag/_header.html.erb`
```
<h2>CRUD: Tags</h2>
```

* Crear un pie con `app/views/tag/_footer.html.erb`
```
<p><%= link_to "Goto to Tags", '/tag' %></p>
```

---

# Modificar las vistas

* Modificar las vistas para usar los parciales.
* `app/views/tag/edit.html.erb`
```
<%= render 'header' %>
<h3>Edit Tags</h3>
...
<%= render 'footer' %>
```
* `app/views/tag/index.html.erb`
```
<%= render 'header' %>
<h3>Listing Tags</h3>
...
```
* `app/views/tag/new.html.erb`
```
<%= render 'header' %>
<h3>New Tags</h3>
...
<%= render 'footer' %>
```
* `app/views/tag/show.html.erb`
```
<%= render 'header' %>
<h3>Show Tags</h3>
...
<%= render 'footer' %>
```

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
