
#Router BENDER

##Cambiar la asociación IP-MAC

Entrar al router:
* `ssh user@IP-de-Bender`, Iniciar conexión SSH para entrar al router
* `BENDER# show running-config`, para consultar la configuración y localizar el nombre
PC a cambiar (NAME). Debe tener la forma `aula109pcXX`.

Modificar la configuración:
* `BENDER# clear ip dhcp binding *`, limpiar las asociaciones pervias de IP para PC's invitados.
* `BENDER# conf terminal`, activar el modo de configuración.
* `BENDER(config)# ip dhcp pool NAME`, entrar en la configuración de NAME.
* `BENDER(dhcp-config)# hardware-address XXXX.XXXX.XXXX`, cambiar la MAC del PC NAME.
* `BENDER(dhcp-config)# exit`
* `BENDER(config)# exit`

Grabar los cambios de configuración de forma permanente:
* `BENDER# show running-config`, consultar los cambios realizados
* `BENDER# copy running-config startup-config`, grabar los cambios de forma permanente.
* `BENDER# exit`, salir del router.
