
# Configurar acceso remoto SSH en Debian

## Instalación

* Para averiguar si lo tenemos instalado: `dpkg -l ssh`.
* Instalar openssh-server para que el profesor pueda acceder
de forma remota:
    * `apt-get install ssh`, por comandos.
    * Usar gestor de paquetes del entorno gráfico.
* Modificar el fichero `/etc/ssh/sshd_config`:
     * Quitar y/o comentar la línea `PermitRootLogin without-password`.
     * Dejar la siguiente configuración `PermitRootLogin yes`.
* Reiniciar el servicio: `service ssh restart`

## Comprobación

Para comprobar el acceso remoto SSH, vamos a la máquina real
y ejecutamos:
* `ssh nombre-del-alumno@ip-de-la-mv` para entrar en la MV
* `hostname -f` para ver el nombre de la MV donde hemos entrado.
