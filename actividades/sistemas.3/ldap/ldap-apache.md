
```
Última modificación: jueves, 23 de noviembre de 2017, 08:50
```

---
# LDAP y Servidor Web Apache

Vamos a proceder a implementar el proceso de autenticación del servidor web Apache contra nuestro servidor LDAP.

* Descargar el documento "linux06_apache.pdf" del servidor del departamento (Página 12).
* Entregar documento en formato ODT o PDF con las respuestas. Incluir capturas de pantalla.

## Preparativos

Para ello dispondremos de dos máquinas virtuales diferentes una con el servidor Web (WEBSERVER) y la otra con el servidor LDAP (LDAPSERVER).

Seguiremos los pasos detallados en la documentación para crear páginas web con autenticación. Pero atención que cambian algunas cosas de la versión del documento con Apache1.3 a la versión actual de Apache2.

## Activar módulos

* En apache2 ya tenemos instalados los módulos para ldap. Consultar directorio /etc/apache2/mods-available.
* Para activar el módulo de autenticación de apache2 con ldap ejecutamos el comando "a2enmod authnz_ldap". Consultar directorio /etc/apache2/mods-enabled para comprobar el resultado.

## Fichero de configuración

* Hacer copia de seguridad al fichero /etc/apache2/sites-available/default
* Añadir la configuración del nuevo directorio al final del fichero /etc/apache2/sites-available/default.

## Ejemplo de configuración

```
Alias privada "/var/www/webprivada/"

<Directory "/var/www/webprivada">
Options Indexes FollowSymLinks
AllowOverride None
Order allow,deny
Allow from all
AuthType basic
AuthName "Identificacion LDAP gerardo.aula109"
AuthBasicProvider ldap

AuthLDAPUrl ldap://XXX.XXX.XXX.XXX:389/ou=usuarios,dc=gerardo,dc=aula109?uid?sub
AuthLDAPBindDN "cn=admin,dc=gerardo,dc=aula109"
AuthLDAPBindPassword *********
AuthLDAPGroupAttributeIsDN off
AuthLDAPGroupAttribute memberUid
require ldap-attribute gidNumber=1100
</Directory>
```
