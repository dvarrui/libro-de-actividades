
```
EN CONSTRUCCION!!!
```

# Keycloack

Enlaces de interés:
* https://ichi.pro/es/que-es-keycloak-como-usarlo-un-ejemplo-con-nodejs-parte-1-40971011506157


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

![](https://ichi.pro/assets/images/max/724/1*VCq4kNW_ndIrrsCEfjNQgQ.png)

![](https://ichi.pro/assets/images/max/724/1*X4KVVVhkk670OExxcd5oGg.png)
