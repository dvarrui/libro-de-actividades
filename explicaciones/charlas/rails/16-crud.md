
# RAILSINGER-Z

![](images/mazinger-y-afrodita.png)

[Volver](README.md)

# CRUD para Mesasges

Ahora vamos con el CRUD para Messages.

# Rutas

* `config/routes.rb`, añadir `resources :message`

# Controlador

* `rails g controller message`, creamos los ficheros.
* Completamos el controlador:

```
class MessageController < ApplicationController
  def index
    @messages = Message.all
  end

  def show
    @message = Message.find(params[:id])
  end

  def edit
    @message = Message.find(params[:id])
  end

  def update
    @message = Message.find(params[:id])
    if @message.update_attributes(message_params)
      # Handle a successful update.
      redirect_to @message
    else
      render 'edit'
    end
  end

  def new
    @message = Message.new
  end

  def create
    @message = Message.new(message_params)
    if @message.save
      redirect_to @message
    else
      render 'new'
    end
  end

  def destroy
    @Message = Message.find(params[:id])
    @message.destroy
    redirect_to '/message'
  end

  private

  def message_params
    params.require(:message).permit(:title, :body)
  end
end
```

---

# Partial

* Header
```
h2>CRUD: Messages</h2>

```

---

# View

* Crear `app/views/message/index.html.erb`

```
<%= render 'header' %>
<h3>Listing Message (<%= @messages.count%>)</h3>

<table>
<% @messages.each do |i| %>
  <tr>
    <td>[<%= i.created_at %>]</td>
    <td><b><%= i.title %><b></td>
    <td>
      <%= link_to "Show", message_path(i) %> |
      <%= link_to "Edit", edit_message_path(i) %> |
      <%= link_to 'Destroy', i,  method: :delete, data: { confirm: 'Are you sure?' } %>
    </td>
  </tr>
<% end %>
</table>

<br>
<%= link_to "New", new_message_path %>
```

* `app/views/message/show.html.erb`

```
<%= render 'header' %>

<h3>Show Message</h3>

<ul>
  <li>id: <%= @message.id %></li>
  <li>title: <%= @message.title %></li>
  <li>body: <%= @message.body %></li>
</ul>

<p>
  <%= link_to "List", '/message' %> |
  <%= link_to "Edit", edit_message_path(@message) %> |
  <%= link_to 'Destroy', @message, method: :delete, data: { confirm: 'Are you sure?' } %>
</p>
```

* `app/views/message/edit.html.erb`

```
<%= render 'header' %>
<h3>Edit Message</h3>
<%= form_for(@message) do |f| %>
  <p><%= f.label :title %><%= f.text_field :title %></p>
  <p><%= f.label :body %><%= f.text_field :body %></p>

  <%= f.submit "Save changes" %>
<% end %>
```

* `app/views/message/new.html.erb`

```
<%= render 'header' %>
<h3>New Message</h3>

<%= form_for @message, url: '/message'  do |f| %>
  <p><%= f.label :title %><%= f.text_field :title %></p>
  <p><%= f.label :body %><%= f.text_field :body %></p>

  <%= f.submit "Create" %>
<% end %>
```

---

# Layout

```
<body>
  <h1>Messenger with RAILSINGER-Z</h1>
  <p>
    <%= link_to "Tags", tag %> |
    <%= link_to "Messages", message %> |
    <%= Time.now %>
  </p>
  <hr>

  <%= yield %>
</body>
```
---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
