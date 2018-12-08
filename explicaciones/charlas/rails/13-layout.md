
# RAILSINGER-Z

![](images/cientificos.png)

[Volver](README.md)

---

# Layout

El layout es una plantilla html que usarán todas las vistas. Es una forma sencilla de hacer cambios en todas las vistas de un solo golpe.

* `app/view/layouts/application.html.erb`

```
<!DOCTYPE html>
<html>
  <head>
    <title>RAILSINGER-Z</title>
    <%= csrf_meta_tags %>
    <%= csp_meta_tag %>

    <%= stylesheet_link_tag    'application', media: 'all', 'data-turbolinks-track': 'reload' %>
    <%= javascript_include_tag 'application', 'data-turbolinks-track': 'reload' %>
  </head>

  <body>
    <h1>Messenger with RAILSINGER-Z</h1>
    <%= Time.now %>
    <hr>

    <%= yield %>
  </body>
</html>
```

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
