
# RAILSINGER-Z

![](images/cientificos.png)

[Volver](README.md)

# Partials

* Crear una cabecera `app/views/tag/_header.html.erb`
```
h1>CRUD: Tags</h1>
<hr>
```

* Crear un pie con `app/views/tag/_footer.html.erb`
```
<p><%= link_to "Goto to Tags", '/tag' %></p>
```

# Modificar las vistas

* Modificar las vistas para usar los parciales.
* `app/views/tag/edit.html.erb`
```
<%= render 'header' %>
<h2>Edit Tags</h2>
...
<%= render 'footer' %>
```
* `app/views/tag/index.html.erb`
```
<%= render 'header' %>
<h2>Listing Tags</h2>
...
```
* `app/views/tag/new.html.erb`
```
<%= render 'header' %>
<h2>New Tags</h2>
...
<%= render 'footer' %>
```
* `app/views/tag/show.html.erb`
```
<%= render 'header' %>
<h2>Show Tags</h2>
...
<%= render 'footer' %>
```
---

# Root route

* `config/routes.rb`
```
Rails.application.routes.draw do
  get '/hello', to: 'welcome#greet'
  resources :tag
  root to: 'tag#index'
end
```

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
