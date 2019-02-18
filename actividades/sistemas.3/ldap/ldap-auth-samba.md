

# Integración Samba con autenticación en LDAP

# 1. Requisitos.

Partimos de que tenemos:
* MV `ldap-serverXX`, con BD servidor LDAP.
* MV `smb-serverXX`, con el servicio Samba.
* MV `smb-clientXXa` que actuará de cliente de los recusos SMB/CIFS del servidor Samba.

# 2. Servidor LDAP

Parámetros:
* `dn=Administrator,dc=nombre-del-alumnoXX,dc=curso1617`, administrador de la BD LDAP.
* `ou=groups,dc=nombre-del-alumnoXX,dc=curso1617`, donde tendremos los grupos LDAP.
* `ou=people,dc=nombre-del-alumnoXX,dc=curso1617`, donde tendremos los usuarios LDAP.
* Confirmar estos valores usando `gq` para navegar por la BD LDAP.

# 3. Servidor Samba

* Primero vamos a asegurarnos de que nuestra instalación de samba esa compilada para ldap: `smbd -b |grep ldap`
* Modificar fichero de configuración de Samba para enlazar la autenticación con el servidor LDAP. Veamos ejemplo:

```

[global]
...
passdb backend = ldapsam:ldap://ip-ldap-server
ldap ssl = start_tls
ldap admin dn = cn=Administrator,dc=nombrealumnoXX,dc=curso1617

ldap suffix = dc=nombrealumnoXX,dc=curso1617
ldap user suffix = ou=people
ldap machine suffix = ou=groups
ldap group suffix = ou=groups
ldap idmap suffix = ou=idmap
...
```

* Establecemos la clave Smaba para este usuario:
```
smbpasswd -W
Setting ... for "cd=Admin,...
```
* Reiniciar el servicio.

# 4. Comprobar el acceso

* Crear un recurso compartido, sólo válido para usuarios definidos en el LDAP.
* Tratar de acceder al recurso desde otro PC y validarse.
