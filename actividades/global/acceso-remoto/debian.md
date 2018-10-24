
# Configurar acceso remoto SSH en Debian

# Instalar servicio SSH

* Para averiguar si lo tenemos instalado: `dpkg -l *ssh*`.
* Instalar servidor SSH para que el profesor pueda acceder de forma remota:
    * `apt-get install openssh-server`, por comandos.
    * Usar gestor de paquetes del entorno gráfico.

---

# Cambiar la configuración de SSH

* Entrar en la consola con el usuario `root`.
* Editar el fichero `/etc/ssh/sshd_config`:
     * Quitar y/o comentar la línea `PermitRootLogin without-password`.
     * Dejar la siguiente configuración `PermitRootLogin yes`.
* `systemctl restart sshd`, iniciar el servicio. Antes se hacía con `service ssh restart`.
* `systemctl status sshd`, comprobar que el servicio está iniciado.    

---

# Comprobación

Para comprobar el acceso remoto SSH:
* Ir a la máquina real.
* `ssh root@ip-de-la-mv` para entrar en la MV.
* `hostname -f` para ver el nombre de la MV donde hemos entrado.
