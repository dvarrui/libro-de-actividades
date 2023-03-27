
```
EN CONSTRUCCION!!!
```

# Gestión de identidad y accesso con Keycloack

Enlaces de interés:
* https://ichi.pro/es/que-es-keycloak-como-usarlo-un-ejemplo-con-nodejs-parte-1-40971011506157
* [Keycloak: una solución de gestión de acceso e identidad de código abierto](https://blog.desdelinux.net/keycloak-una-solucion-de-gestion-de-acceso-e-identidad-de-codigo-abierto/)

# Introducción

IAM es software que se encarga de:
* La gestión de identidad y
* La gestión de accesos/permisos

Keycloack es un IAM que se puede configurar para usarse por otros programas que requieran los servicios anteriores. A estos programas que hacen uso de Keycloak se les llama **clientes**.

Keycloak es capaz de hablar con los clientes usando diversos protocolos:
* 0Auth
* OpenID
* SAML

Keycloack se puede usar en combinación con otros proveedores de identidad:
* Sitios de inicio de sesión de redes sociales
* Bases de datos de usuarios externos federados como servidores LDAP
* etc.

# Instalación y configuración

* Vamos a "instalar" KC (Keycloack) mediante contenedor.

```
$ docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:latest
```

* Abrir el navegador en http://127.0.0.1:8080/auth/ y haga clic en Consola de administración (Debería redirigirnos a http://127.0.0.1:8080/auth/admin/master/console/).
* Inicie sesión en la consola con el usuario/clave administrador (comando docker).

# REALM, roles, grupos y usuarios

* Creamos un nuevo **REALM**, llamado `realm1aXX`. Un REALM es como un espacio de nombres o un dominio.
* Creamos **roles**: users y admins.
* Creamos **usuarios**: user1 y user2 en el grupo de usuers, y admin en el grupo de admins.

# Clientes

* Creamos un cliente KC para nuestra aplicación, llamado `myapp1`.


* Elegimos "tipo confidencial".
* Habilitamos la autorización.

???

* Cree un alcance de escritura (asegúrese de que su nombre sea exactamente "escribir").
* Ahora haga clic en la pestaña Recursos y haga clic en crear.
* haga clic en Crear política y seleccione Rol en el menú desplegable.
* Haga clic en la pestaña Permisos y haga clic en Crear permiso y seleccione Basado en alcance en el menú desplegable.

Observe cómo vinculamos un recurso, su alcance y la política deseada para crear un permiso, que se convierte en nuestra lógica de evaluación para otorgar acceso.

## Comprobar el permiso

Antes de terminar, probemos la configuración que hicimos hasta ahora. Se puede hacer a través de la interfaz de usuario.

* Haga clic en la pestaña Evaluar, * seleccione el cliente, el usuario y el recurso, el alcance
* y luego haga clic en evaluar los resultados

## API

Con el usuario que tenemos podemos conectar por el API KC

* Empezamos averiguando nuestro "token" de acceso. NOTA: **admin_cli**, es un cliente que viene por defecto ya creado en KC.

```
curl \
-d "client_id=admin-cli" \
-d "username=admin" \
-d "password=admin" \
-d "grant type=password"
"http://localhost:8080/auth/realms/master/procotol/openid-connect/token"
```

* Esto nos devuelve un `access_token` necesario para las conexiones al servicio. Lo apuntamos.
* Hacemos una consulta vía API que devuelve información de KC en formato json.

```
curl \
-H "Authorization: bearer <access_token>" \
"http://localhost:8080/auth/realms/master"
```

* Si no lo hacemos rápido puede ser que se nos caduque el "token". Vamos a montarnos un script que nos los haga por nosotros.
* Si vamos al panel de KC, sesiones vemos las sesiones que hemos abierto.

# Securizar un servicio que tengamos

Servicio:
* Iniciamos un servidor Apache: `docker run -rm httpd`

Keycloack:
* Vamos a KC y nos creamos un nuevo cliente
* ID: `myapacheXX`
* Protocolo: `openid`
* Name: `MyApacheXX`
* AccessType: `confidential` (Consultar ANEXO A.1)
* Valid Redirect URL: `http:/localhost:4180/oauth2/callback`
* Ir a Credential -> Secret y copiamos ese token secreto.

OAuth2: Lanzar el servicio OAuth2 para nuestro cliente
COmando oauth2-proxy
--http-address=0.0.0.0:4180 ?
--upstream=http://172.17.0.3 (servicio funcionando)
--provider=oidc
--client-id=myapacheXX (client-id de KC)
--client-secret=TOKEN-SECRETO (secret KC)
--redirect-url=(redirect url de KC)

## Comprobamos

* Abrimos URL https://172.0.0.1:4180 en el navegador. Esto nos redirige al proceso de login gestionado por KC.
* Ponemos nuestro usuario (el que hemos definido en KC)
* Vemos que nos lleva a nuestro servicio securizado.
* Si vamos al panel de KC vemos que tenemos la sesión abierta con nuestro usuario.

---

# ANEXO

## A.1 Tipos de clientes

* **Cliente público**: cuando no podemos transmitir secretos del cliente a través del canal a la aplicación. Por ejemplo en el caso de navegadores y aplicaciones móviles. Debido a que cada instancia de una pestaña del navegador de una aplicación móvil es diferente y, y casi siempre se encuentra en Internet público, no podemos pasarles un secreto; y también existe un problema de confianza, porque el usuario final puede tener acceso al secreto si se transmite a su aplicación (navegador / móvil, etc.).
* **Cliente solo de portador**: cuando queremos que nuestra aplicación de backend verifique simplemente el token y obtenga detalles sobre los alcances del token emitido, no tenga un control de acceso detallado en la aplicación en particular. No tiene ningún recurso (activo) asociado.
* **Cliente confidencial**: cuando queremos una característica de control de acceso de grano fino habilitada por el servidor de capa de claves en la aplicación cliente (que se ejecuta en el backend). Tiene recursos (activos) asociados.
