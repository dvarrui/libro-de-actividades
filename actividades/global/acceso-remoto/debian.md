
# Configurar acceso remoto SSH en Debian

# Instalar servicio SSH

* Para averiguar si lo tenemos instalado: `dpkg -l *ssh*`.
* Instalar servidor SSH para poder acceder de forma remota:
    * `apt-get install openssh-server`, por comandos.
    * Usar gestor de paquetes del entorno gráfico.

---

# Cambiar la configuración de SSH

* Entrar en la consola con el usuario `root`.
* Editar el fichero `nano /etc/ssh/sshd_config`:
     * Quitar y/o comentar la línea `PermitRootLogin without-password`.
     * Dejar la siguiente configuración `PermitRootLogin yes`. SIN ALMOHADILLA.
     Las almohadillas `#` al comienzo de la línea la desactivan/deshabilitan/la comentan.
* `systemctl restart sshd`, iniciar el servicio. Antes se hacía con `service ssh restart`.
* `systemctl enable sshd`, para asegurarnos de que se va a iniciar el servicio cuando se encienda la máquina.
* `systemctl status sshd`, comprobamos.

---

# Comprobación

Para comprobar el acceso remoto SSH:
* Ir a otra máquina con GNU/Linux y abrir una consola.
* `ssh root@ip-de-la-mv` para entrar en la MV.
* `hostname -f` para ver el nombre de la MV donde hemos entrado.
