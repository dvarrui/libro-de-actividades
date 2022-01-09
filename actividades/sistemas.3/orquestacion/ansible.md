
```
EN CONSTRUCCIÓN!!!
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

Consultar los siguientes vídeos:
* [Ansible 1 - ¿Qué es Ansible](https://youtu.be/slNIwBPeQvE)
* [Ansible 2 - Instalación](https://youtu.be/Zimn-UCbQ0A)
* [Ansible 3 - Inventario](https://youtu.be/VgnidinNlkQ)

## 1.2 Crear las máquinas

| MVs | Nombre   | Rol    | SO        | IP           |
| --- | -------- | ------ | --------- | ------------ |
| MV1 | masterXX | Master | OpenSUSE  | 172.19.XX.31 |
| MV2 | slaveXXg | Slave  | OpenSUSE  | 172.19.XX.32 |
| MV3 | slaveXXw | Slave  | Windows10 | 172.19.XX.11 |

> Te recuerdo que puedes hacer uso de [Vagrant](../../global/vagrant) para crear estas máquinas.

# 2. Instalación de SSH y Ansible

# 2.1 Instalar Ansible

> [EN - Installation Guide](https://docs.ansible.com/ansible/latest/installation_guide/index.html)

Ir a MV1 e instalar Ansible.

## 2.2 Acceso por SSH a las máquinas

* Instalar el servicio SSH en MV2 y MV3.
* El usuario "nombre-alumno" de la MV1 debe poder acceder por SSH sin clave a MV2 y MV3. Recordar el uso de clave pública/privada de SSH. Esto lo hicimos en la actividad SSH de la unidad 1.

# 3. El inventario

El **inventario** es el conjunto de máquinas que tenemos declaradas en nuestra instalación de Ansible para informarle al sistema que son aquellas máquinas con las que vamos a trabajar.

# ANEXO

## Clave privada con password
eval $(ssh-agent)
ssh-add

/etc/lsb-release
Muestra la versión del SO
