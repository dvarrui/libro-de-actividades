
# RAILSINGER-Z

![](images/malo1.png)

[Volver](README.md)

# Links

* `app/views/tag/index.html.erb`

```
<h1>Listing Tags</h1>

<table>

<% @tags.each do |i| %>
  <tr>
    <td><%= i.name %></td>
    <td><%= link_to "Show", tag_path(i) %></td>
    <td><%= link_to "Edit", edit_tag_path(i) %></td>
  </tr>
<% end %>
</table>
```

![](images/10-tag-index.png)

* `app/view/tag/show.html.erb`
```
<%= render 'header' %>
<h3>Show Tag</h3>

<ul>
  <li>id: <%= @tag.id %></li>
  <li>name: <%= @tag.name %></li>
</ul>
<p><%= link_to "Edit", edit_tag_path(@tag) %></p>
<%= render 'footer' %>
```

* `app/view/tag/edit.html.erb`
```
<%= render 'header' %>

<h3>Edit Tag</h3>

<%= form_for(@tag, url: tag_path) do |f| %>
  <%= f.label :name %>
  <%= f.text_field :name %>

  <%= f.submit "Save changes" %>
<% end %>

<p><%= link_to "Show", tag_path(@tag) %></p>

<%= render 'footer' %>
```

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
