
```
Estado     : En construcción
Curso      : 201920
Requisitos : Servidor LDAP instalado y configurado
```

---
# LDAP comandos

---
# Preparativos

Configurar MV1:

| Atributo | Valor         |
| -------- | ------------- |
| Rol      | Servidor LDAP |
| hostname | ldap-serverXX |

Configurar MV2:

| Atributo | Valor         |
| -------- | ------------- |
| Rol      | Cliente LDAP  |
| hostname | ldap-clientXX |

---
# TEORIA: ¿Que es LDAP?

Extraído de:
* [Curso de LDAP en GNU/Linux](https://docplayer.es/1981696-Curso-de-ldap-en-gnu-linux-60-horas.html) Página 7.

LDAP en inglés Lightweight Directory Access Protocol. Traducido   al   español su significado es: Protocolo Ligero para Acceder al Servicio de Directorio, ésta implementación se basa en un conjunto de estándares de redes de computadoras (X.500) sobre el servicio de directorios.  

LDAP se ejecuta sobre TCP/IP o sobre otros servicios de transferencia   orientado a conexión; que permite el acceso a los datos de un directorio ordenado y distribuido para buscar información.

Habitualmente se almacena información de los usuarios que conforman
una red de computadores, como por ejemplo el nombre de usuario, contraseña, directorio hogar, etc. Es posible almacenar otro tipo de información tal como, bebida preferida, número de teléfono celular, fecha de cumpleaños, etc.

En  conclusión, LDAP es un protocolo de acceso unificado a un conjunto  de información sobre los usuarios de una red de computadores

---
# Servicio directorio LDAP

Ir a la MV1:
* Asegurarse de que el servicio LDAP está corriendo
    * `lsof -i -T -n -P`
    * `systemctl status ...`
* `ldapsearch -x -b "dc=apellidoXX,dc=asir"`, comprobar accesibilidad en local.

Ir a la MV2:
* Asegurarse de que el servicio LDAP es accesible desde el cliente.
    * `nmap -Pn ldap-serverXX`

---


Tecno-Redes Sistemas VCG
                          Agosto-2008                                  LDAP-v01

El resultado del comando anterior debe ser algo como
Administración de usuarios
En este apartado se mostrará como agregar un usuario al
LDAP
 utilizando
para   ello   los  
ldif
    (
LDAP   Data   Interchange   Format
).   A   continuación   se
mostrará cual es la estructura de los
ldif.
ldif   para   la   creación   de   una   unidad   organizacional   “people”.   El   nombre   del
archivo es people.ldif

---
# Consultar contenido del directorio LDAP

Enlaces de interés:
* [Consultas a directorios LDAP utilizando ldapsearch](https://www.linuxito.com/gnu-linux/nivel-alto/1023-consultas-a-directorios-ldap-utilizando-ldapsearch)

Normalmente se usa la clase `inetOrgPerson`, para almacenar usuarios dentro de un directorio LDAP. Dicha clase posee el atributo `uid`.
Por tanto, para listar los usuarios de un directorio, podemos filtrar por `"(uid=*)"`. Veamos un ejemplo:

```
ldapsearch -z 0
           -H ldap://localhost:389
           -W
           -D "cn=root,dc=apellidoXX,dc=asir"
           -b "dc=apellidoXX,dc=asir" "(uid=*)"
```

| Parámetro                  | Descripción                     |
| -------------------------- | ------------------------------- |
| -z 0                       | No hay límite en las respuestas |
| -H ldap://localhost:389    | IP:puerto del servidor          |
| -W                  | Se solicita contraseña para autenticar |
| -D "cn=root,dc=apellidoXX,dc=asir"  | Usuario de la conexión |
| -b "dc=apellidoXX,dc=aisr" | Base donde comenzar la búsqueda |
| "(uid=*)"                  | Filtro para la búsqueda         |

> Importante: No olvidar especificar la base (-b). De lo contrario probablemente no haya resultados en la búsqueda.
