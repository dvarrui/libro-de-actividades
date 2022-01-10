
```
Curso       : 2122
Area        : Sistemas operativos, automatización, aprovisionamiento, devops   
Descripción : Automatización de tareas con gestor de infraestructura "Ansible"
Requisitos  : 2 MV's con GNU/Linux
Tiempo      : 5 sesiones
```

# 1. Orquestación con Ansible

Enlaces de interés:
* [Automatización con Ansible. Una introducción](https://www.atareao.es/tutorial/ansible/)
* [¿Qué es la orquestación de sistemas](https://www.redhat.com/es/topics/automation/what-is-orchestration)
* [EN - Ansible Documentation](https://docs.ansible.com/ansible/latest/)
* [EN - Red Hat Ansible Automation Platform](https://www.ansible.com/)

## 1.1 Consultar los vídeos

## 1.2 Crear las máquinas

| MVs | Nombre   | Rol    | SO        | IP           |
| --- | -------- | ------ | --------- | ------------ |
| MV1 | masterXX | Master | GNU/Linux | 172.19.XX.51 |
| MV2 | slaveXXg | Slave  | GNU/linux | 172.19.XX.52 |
| MV3 | slaveXXw | Slave  | Windows10 | 172.19.XX.11 |

> Te recuerdo que puedes hacer uso de [Vagrant](../../global/vagrant) para crear estas máquinas.

* Definir las máquinas "slaveXXg" y "slaveXXw" dentro del /etc/hosts de "masterXX".

# 2. Instalación de SSH y Ansible

# 2.1 Instalar Ansible

Consultar los siguientes vídeos:
* [Ansible 1 - ¿Qué es Ansible](https://youtu.be/slNIwBPeQvE)
* [Ansible 2 - Instalación](https://youtu.be/Zimn-UCbQ0A)

> [EN - Installation Guide](https://docs.ansible.com/ansible/latest/installation_guide/index.html)

Ir a MV1 e instalar Ansible.

## 2.2 Acceso por SSH a las máquinas

* Instalar el servicio SSH en MV2 y MV3.
* El usuario "nombre-alumno" de la MV1 debe poder acceder por SSH sin clave a MV2 y MV3. Recordar el uso de clave pública/privada de SSH. Esto lo hicimos en la actividad SSH de la unidad 1.

## 2.3. El inventario

El **inventario** es el conjunto de máquinas que tenemos declaradas en nuestra instalación de Ansible para informarle al sistema que son aquellas máquinas con las que vamos a trabajar.

Consultar los siguientes vídeos:
* [Ansible 3 - Inventario](https://youtu.be/VgnidinNlkQ)

* Crear un grupo dentro del inventario llamado "alumnoXX".
* Poner las máquinas "slaveXXg" y "slaveXXw" dentro del grupo.

# 3. Comandos ad-hoc

Los comandos ad-hoc se lanzan desde la terminal y permiten realizar tareas rápidas en un servidor. También pueden ser usados para lanzar instrucciones concretas usando módulos específicos de Ansible en un servidor.

Consultar los siguientes vídeos:
* [Ansible 4 - Comandos básicos ad-hoc](https://youtu.be/83DBL6CGNmY)
* [Ansible 5 - Comandos ad-hoc para controlar módulos](https://youtu.be/-MuOmvN1tgY)

Ir a la MV1:
* Usar Ansible para comprobar la conectividad con las máquinas del inventario.
* [Ansible - Module Index](https://docs.ansible.com/ansible/2.8/modules/modules_by_category.html)
* Usar Ansible para instalar 'neofetch' en las máquinas del inventario.
* Comprobar que el paquete está instalado.
* Volver a ejecutar el comando Ansible de instalación del mismo paquete y ver qué ocurre.

# 4. Playbook

La forma reutilizable de usar Ansible es escribiendo playbooks, que son archivos en los que declaramos las tareas que necesitamos hacer con nuestro servidor que vayamos a aprovisionar.

Consultar los siguientes vídeos:
* [Ansible 6 - Redactando un playbook](https://youtu.be/Wuv0ZPOMLf0)
* [Playbooks de Ansible - Atareado](https://atareao.es/tutorial/ansible/playbooks-de-ansible/)

## 4.1 ping

Ir a la MV1:
* Crear directorio `/home/nombre-alumno/ansibleXX.d`
* Moverse dentro de la directorio anterior.
* Crear el fichero `tarea-01-ping.yaml`.
* Dentro del fichero anterior define un playbook de Ansible con lo siguiente:
    * Aplicar a todos los hosts del inventario
    * Comprobar conectividad ping
    * Crear el fichero `tarea-01-ping.yaml`.
* Comprobarlo.

## 4.2 Instalar y desinstalar

* Crear el fichero `tarea-02-install.yaml`.
* Dentro del fichero anterior define un playbook de Ansible con lo siguiente:
    * Aplicar al host GNU/Linux del inventario
    * Instalar el paquete `neofetch` y otros dos paquetes a elegir por el alumno.
* Comprobarlo.

* Crear el fichero `tarea-03-uninstall.yaml`.
* Dentro del fichero anterior define un playbook de Ansible con lo siguiente:
    * Aplicar al host GNU/Linux del inventario
    * Desinstalar los paquetes instalados por la tarea 02.
* Comprobarlo.

## 4.3 Nginx

Cómo conectarse a una máquina remota como otro usuario mediante el uso de remote_user.
* [Ansible 7 - Conectarse como otro usuario](https://youtu.be/ggTS32i2JmA)

* Crear el fichero `tarea-04-nginx-on.yaml`.
* Dentro del fichero anterior define un playbook de Ansible con lo siguiente:
    * Aplicar al host GNU/Linux del inventario
    * Definir `remote_user` con un usuario con privilegios de superusuario en las máquinas remotas.
    * Instalar Nginx.
    * Iniciar el servicio.
* Comprobarlo.
* Crear el fichero `tarea-05-nginx-off.yaml`.
* Dentro del fichero anterior define un playbook de Ansible con lo siguiente:
    * Aplicar al host GNU/Linux del inventario
    * Definir `remote_user` con un usuario con privilegios de superusuario en las máquinas remotas.
    * Desinstalar Nginx.
* Comprobarlo.

---
`EN CONSTRUCCIÓN!!!`

* [Ansible 8 - Handlers](https://youtu.be/G97sqHIG38w)

# ANEXO

Comandos de ansible:

ansible MV -m ping
ansible MV -m ping -i HOSTFILE
ansible MV -a 'echo "hola"'
ansible MV -m shell -a 'echo "hola"'
ansible MV -m apt -a "name=vim state=present"
ansible MV -m zypper -a "name=neofetch state=present"
ansible MV -m zypper -a "name=neofetch state=present" -b
ansible MV -m zypper -a "name=neofetch state=present" -b -k

https://docs.ansible.com/ansible/2.9/reference_appendices/interpreter_discovery.html

ansible-playbook FILE.yaml

## Ficheros de configuración de Ansible

```
ansible.cfg
HOME/.ansible.cfg
/etc/ansible/ansible.cfg
```

Contenido
```
[defaults]
remote_user = vagrant
```

## Clave privada con password
eval $(ssh-agent)
ssh-add

/etc/lsb-release
Muestra la versión del SO
