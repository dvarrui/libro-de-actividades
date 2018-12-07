
# Rails

![](images/super-brazo.png)

# Las armas secretas de Railsinger-Z: CRUD y ORM

Ahora tendríamos que rellenar la tabla `tags` con contenido. Pero no lo vamos a hacer con SQL... debemos montar algo en la aplicación web para hacerlo:
* CRUD: Create-Read-Update-Delete functions.
* ORM:Object Relational Mapping (ActiveRecord)

El camino que hemos seguido hasta ahora es:
1. Crear las bases de datos.
1. Crear rutas.
1. Generar controladores.
1. Generar modelos.
1. Crear algún registro.
1. Ahora tocan las vistas.

---

# Rutas

```
messenger> rails routes
           Prefix Verb URI Pattern
        Controller#Action
            hello GET /hello(.:format)  
        welcome#greet
```
* Modificar `config/routes.rb`
```
Rails.application.routes.draw do
  # For details see http://guides.rubyonrails.org/routing.html
  get '/hello', to: 'welcome#greet'
  resources :tags
end
```
* Consultamos las rutas CRUD para `tags`...
```
messenger> rails routes

    Prefix            Verb   URI Pattern
    Controller#Action

    hello             GET    /hello(.:format)
    welcome#greet
    tag               GET    /tag(.:format)
    tag#index         POST   /tag(.:format)
    tag#create
    new_tag           GET    /tag/new(.:format)
    tag#new
    edit_tag          GET    /tag/:id/edit(.:format)
    tag#edit
    tag               GET    /tag/:id(.:format)
    tag#show          PATCH  /tag/:id(.:format)
    tag#update        PUT    /tag/:id(.:format)
    tag#update        DELETE /tag/:id(.:format)
    tag#destroy
```
---

# New

* `rails g controller tag`
* Editar `app/controller/tag_controller.rb`
```
class TagController < ApplicationController
  def new
  end
end
```

> Erb es el motor de plantillas que usa Rail

* Crear `app/views/tag/new.html.erb`
```
<h1>New Tag</h1>

<%= form_with scope: :tag, url: '/hello' do |form| %>
  <p>
    <%= form.label :name %><br>
    <%= form.text_field :name %>
  </p>
  <p><%= form.submit %></p>
<% end %>
```

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
