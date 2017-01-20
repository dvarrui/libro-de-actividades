
#Configurar acceso remoto

## Instalación

* Para veriguar si lo tenemos instalado: `zypper search openssh`
* Instalar openssh-server para que el profesor pueda acceder
de forma remota:
    * Instalar SSH usando entorno gráfico `Yast -> Instalar Software`.
    * Instalar SSH usando comandos `zypper install openssh`.
* Modificar el fichero `/etc/ssh/sshd_config` y cambiar
`PermitRootLogin yes`. La línea debe estar descomentada.
    * Debemos ser superusuario para modificar este fichero.
* Reiniciar el servicio: `systemctl restart sshd`

## Comprobación

Para comprobar el acceso remoto SSH, vamos a la máquina real
y ejecutamos:
* `ssh nombre-del-alumno@ip-de-la-mv` para entrar en la MV
* `hostname -f` para ver el nombre de la MV donde hemos entrado.

En caso de error en el acceso podemos hacer las siguientes comprobaciones:
* `ping ip-de-la-mv`, si funciona la MV tiene bien configurado el interfaz de red.
* `nmap -Pn ip-de-la-mv`, si muestra puerto 22/ssh, es que el servicio está disponible en la MV remota.
* `systemctl status ssh`, para comprobar si tenemos el servicio SSH iniciado en localhost.

## Instalación de el CD/DVD

* En la ventana de la MV, ir a panel superior de VirtualBox-> dispositivos -> montar CD de GNU/Linux.
* Ejecutar como superusuario:
   ifdown eth0
   ifup eth0
   yast2

* Ir a Configuración del contafuegos -> Servicios Autorizados -> Añadir Servicio SSH.
* Ir a Servicios del sistema -> sshd -> Activar
* Cuando la instalación termine, volver a ir a Dispositivos -> desmontar el CD de GNU/Linux.
* Cerrar terminal y apagar el sistema
