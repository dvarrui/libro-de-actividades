
# Procesos


# Limitar el uso de la CPU

Enlace de interés:
* [Limitar el uso de CPU por parte de un proceso en Linux](http://lamiradadelreplicante.com/2016/11/20/limitar-el-uso-de-cpu-por-parte-de-un-proceso-en-linux/)

* Instalar el programa cpulimit.
* Comprobar estado de la CPU.
* Ejecutar un programa con alto consumo de CPU.
* Comprobar estado de la CPU.
* Limitar el uso de la CPU.
* Comprobar estado de la CPU.

# Limitar el uso de la RAM

* `free` consultar estado de la memoria
* `ps, htop, top` para consultar los consumos de memoria y CPU de los Procesos

* `$ ulimit -Sv 500000 # Set ~500 mb limit`, Limitar el consumo de memoria para un único proceso.
* Si reducimos mucho el límite ¿el proceso muere?... Comprobarlo.

> [Configurar ulimit de forma permanente](http://ernestogamez.es/configurar-ulimit-y-dejarlo-permanente/)

---

# ANEXO

> Another way besides setrlimit, which can be set using the ulimit utility:
> is to use Linux's control groups, because it limits a process's (or group of processes') allocation of physical memory distinctly from virtual memory. For example:
>   $ cgcreate -g memory:/myGroup
>   $ echo $(( 500 * 1024 * 1024 )) > /sys/fs/cgroup/memory/myGroup/memory.limit_in_bytes
>   $ echo $(( 5000 * 1024 * 1024 )) > /sys/fs/cgroup/memory/myGroupmemory.memsw.limit_in_bytes
>
> will create a control group named "myGroup", cap the set of processes run under myGroup up to 500 MB of >physical memory and up to 5000 MB of swap. To run a process under the control group:
>
>   $ cgexec -g memory:myGroup COMMAND
>
> Note: For what I can understand setrlimit will limit the virtual memory, although with cgroups you can limit the physical memory.

> free, vmstat. top, htop
> gnome-system-monitor para entornos GNOME y ksysguard para entornos KDE.
>
> http://rm-rf.es/linux-listar-procesos-cpu-memoria/
