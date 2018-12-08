
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

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
