A1: Servidor LDAP

INDICE
1. Instalación del servidor LDAP
2. Introducir datos al LDAP
3. Configurar Samba con LDAP
4. Comprobar el acceso

logo
Introducción
Enlaces de interés:

        Min 38: Crear config dir a partir de config text.
    Tool Openfile: http://www.openfiler.com/
    Tool Zentyal


1. Instalación Servidor LDAP
Realizar las siguientes tareas:

    Descargar y leer documento "ldap-cnice-taller.pdf" para realizar esta actividad.
    Utilizar preferentemente una máquina virtual con SO Debian/Ubuntu para instalar el servidor LDAP.
    Esta máquina deberá tener una IP fija. Cada alumno dispone de las IP's. 172.16.109.XX, 172.16.109.1XX y 172.16.109.2XX. Donde XX es su número de puesto.
    Proceder a la instalación del servidor LDAP. Si el paquete "db4.2-util" no estuviera disponible, buscar el paquete "db-util" disponible en los repositorios.

    Configurar el servidor LDAP, usando como DN el siguiente: dc=nombredealumno, dc=aula109.
    Asegurarse que tenemos definido el nombre DNS de la máquina "nombredealumno.aula109" en el fichero /etc/hosts.
    Una vez instalado, comprobar a parar y reiniciar el servicio de forma manual.
    Comprobar también que el servicio se inicia automáticamente al reiniciar la máquina.
    Instalar alguna de las herramientas cliente LDAP propuestas en la documentación. Mediante el cliente LDAP podremos escribir y/o consultar información en la base de datos LDAP.

NOTA: Para el correcto funcionamiento de phpldapadmin necesitaremos tener correctamente instalado apache2 con php. Instalar "apache2", "libapache2-mod-php5", "php5-ldap".

2. Introducir datos en LDAP
Vídeo "LPIC-2 202 LDAP Client Usage".


    Crear las unidades organizativas: grupos y usuarios.
    Crear dentro de ou=grupos, los grupos de profesores y alumnos.
    Crear dentro de ou=usuarios, varios usuarios profesores y alumnos.
    Usar jxplorer desde otra máquina y comprobar que podemos acceder al contenido del servidor LDAP.

Vemos un ejemplo de un árbol de datos en LDAP:

arbol

ANEXO 1
A1.1 phpLDAPadmin: cambiar el almacén

Tras instalar phpLDAPadmin y loguearse en "http://127.0.0.1/phpldapadmin/" te aparece la configuración de ejemplo 'dc=example,dc=com'. Para cambiar esto por tus datos, hay que cambiar el archivo "config.php" de phpLDAPadmin que se encuentra en "/etc/phpldapadmin/config.php".

Después en la línea 300 del archivo, modifican lo siguiente por sus datos:

Original: $servers->setValue('server','base',array('dc=example,dc=com'));
Modificado: $servers->setValue('server','base',array('dc=efrain,dc=aula108'));

A1.2 phpLDAPadmin: cambiar el usuario
SI en phpLDAPadmin a la hora de crear de un usuario, no puedes crearlo porque te tira un error que dice lo siguiente: "Error trying to get a non-existant value (appearance,password_hash)".

Para arreglarlo vamos al archivo de configuración '/usr/share/phpldapadmin/lib/TemplateRender.php' y editamos la línea 2469 de la siguiente forma:

Original --> $default = $this->getServer()->getValue('appearance','password_custom');

Modificado --> $default = $this->getServer()->getValue('appearance','password_hash_custom');

Guardamos y listo.
