
# Trabajar en casa

En principio, todas las actividades están pensadas para que de tiempo a terminarlas en clase. A veces puede ocurrir que algún alumno requiera continuar la actividad en casa. Esto es posible, sólo que habrá que tener en cuenta algunos aspectos.

## Exportar/importar MV

Las MV's de VirtualBox se pueden exportar en clase, copiar a un disco duro externo y volver a importar en casa para seguir trabajando.

También es posible copiar directamente la carpeta de la MV de VirtualBox en un disco duro externo, copiar en casa y en VirtualBox ir a "agregar máquina", y listo.

## Configuración de las MV's

En principio debemos mantener las configuraciones de las MV's para que sean las mismas que tenemos en clase y a la hora de corregir estén tal y como se ha especificado en la práctica (IP's, nombres de equipos, etc)

Si estamos usando el rango de IP's 172.19.0.0/16 para nuestras MV's en clase... esto también funcionará en casa y en cualquier sitio, siempre y cuando SOLO queramos tener conectividad entre nuestras propias MV's. Si salir a Internet usando MV en casa. ¿Se entiende? Es un tema de redes.

Si por cualquier causa estamos es casa, usando MV con rango 172.19.0.0/16, en casa tenemos el rango de red 192.168.1.0/24 y queremos que nuestas MV's accedan a Internet... podemos añadir un segundo interfaz de red con configuración DHCP a nuestra MV. De esta forma mantendremos la configuración de IP estática requerida en clase y otra configuración de red DHCP para adaptarse a la red donde estemos en cada caso.
