
# RAILSINGER-Z

![](images/malo1.png)

# Links

* Añadir links en Show, Edit a Index.

```
<%= link_to 'Destroy',  @post,  method: :delete, data: { confirm: 'Are you sure?' } %>
```

# Action destroy

* Añadir una nueva acción en el controlador:

```
  def destroy
    @tag = Tag.find(params[:id])
    @tag.destroy
    redirect_to '/tag'
  end
```

---

Ir a [comandos](99-commands.md) para ver el resumen de órdenes de rails.
