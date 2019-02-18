*(Realizado en el curso 2014-2015 y anteriores)*


#Autenticación LDAP
Con autenticacion LDAP prentendemos usar una máquina como servidor LDAP,
donde se guardará la información de grupos, usuarios, claves, etc. Y desde
otras máquinas conseguiremos autenticarnos (entrar al sistema) con los 
usuarios definidos no en la máquina local, sino en la máquina remota con
LDAP. Una especie de *Domain Controller*.

Realizar las siguientes tareas:
* Consultar documento `ldap-auth-pminik-taller.pdf`, página 20.
* Enlaces de interés [Autenticación con OpenLDAP](http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m6/autentificacin_del_sistema_con_openldap.html).
* Vamos a utilizar dos máquinas virtuales con SSOO Debian. 
    * Una máquina deberá tener el servidor LDAP instalado y configurado según la actividad anterior 
    (Lo llamaremos SERVER1).
    * El otro será el equipo cliente que vamos a modificar para la autenticación (Lo llamaremos HOST2).
* Modificar HOST2 para tener un sistema de autenticación por LDAP según se indica en la documentación suministrada.

>**NOTA:**
>
> * Al configurar `libpam_ldap`, elegir como URI del servidor LDAP lo siguiente 
`ldap://ip-del-servidor-ldap/`. OJO con el formato.
> * Al configurar `libpam_ldap`, elegir el sistema de codificación de las claves 
similar al elegido al guardar las claves en el servidor LDAP. Por ejemplo: elegir MD5 en ambos.
> * Al configurar los módulos de PAM common-auth, common-account, common-session 
y common-passwd, tener en cuenta que las líneas que debemos añadir deben estar justo encima 
de la línea que contiene `pam_unix.so`.


