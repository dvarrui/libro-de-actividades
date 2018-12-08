
# RAILSINGER-Z

![](images/super-brazo.png)

# CRUD (new, create)

* Vamos a completar los métodos en el controlador:
```
class TagController < ApplicationController

...

  def new
    @tag = Tag.new
  end

  def create
    @tag = Tag.new(tag_params)
    if @tag.save
      flash[:success] = "Tag #{@tag.name} created!"
      redirect_to @tag
    else
      render 'new'
    end
  end

...

```

---

# Views (new, create)

* `app/views/tag/new.html.erb`
```
<h1>New Tag</h1>

<%= form_for @tag, url: '/tag'  do |f| %>
  <%= f.label :name %>
  <%= f.text_field :name %>

  <%= f.submit "Create" %>
<% end %>

```
* Create no tiene vista. Jajajaja.

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
