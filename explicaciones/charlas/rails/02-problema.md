
# Rails

![](images/doctor-hell.png)

# Solucionar un problema

Hace falta un programa/utilidad para enviar mensajes de forma selectiva o filtrada. Al estilo twitter.
* Necesitamos `users`, `tags` y `messages`.
* Cada usuario tendrá asociado a su perfil un conjunto de `tags`.
* Cada usuario podrá escribir `messages` con asunto y cuerpo pero sin remitente. En su lugar se asocian al mensaje una serie de `tags`.
* Cada usuario podrá leer/consultar sólo los mensajes que tengan alguna coincidencia de `tags` con su perfil.

Llamaremos a la aplicación Messenger.
