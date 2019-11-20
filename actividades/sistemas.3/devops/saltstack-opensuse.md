
```
Estado     : EN CONSTRUCCIÓN!!!
Requisitos : 2 MV's
```
---

# 1. Saltstack

Saltstack es un gestor de infrastructura como Puppet, Chef y Ansible.
En esta actividad vamos a practicar Saltstack con OpenSUSE.

# 2. Preparativos

MV1:
* Hostname: salt-masterXXg
* SO: GNU/Linux OpenSUSE
* IP: 172.19.XX.31
* Configurar `/etc/hosts` con "IP nombre" de MV1 y MV2.

MV2:
* Hostname: salt-minionXXg
* SO: GNU/Linux OpenSUSE
* IP: 172.19.XX.32
* Configurar `/etc/hosts` con "IP nombre" de MV1 y MV2.

---
# 3. salt-master

Enlaces de interés:
* [OpenSUSE -Saltstack](https://docs.saltstack.com/en/latest/ref/configuration)

## 3.1 Instalación del Master

Ir a la MV1
* `zypper install salt-master`, instalar el software del master.

## 3.2 Configuración del Master

* Modificar `/etc/salt/master` para configurar nuestro interfaz de red:
`interface: 172.19.XX.31`.
* `systemctl enable salt-master.service`, activiar servicio en el arranque del sistema.
* `systemctl start salt-master.service`, iniciar servicio.
* `salt-key -L`, para consultar minions. Vemos que no hay ninguno todavía.
```
Accepted Keys:
Denied Keys:
Unaccepted Keys:
Rejected Keys:
```

---
# 4. salt-minion

## 4.1 Instalación y configuración

* `zypper install salt-minion`, instalar el software del agente (minion).
* Modificar `/etc/salt/minion`:
```
master: 172.19.XX.31
```
* `systemctl enable salt-minion.service`, activar Minion en el arranque del sistema.
* `systemctl start salt-minion.service`, iniciar el servico del Minion.

## 4.2 Aceptación desde el Master

Ir a MV1:
* `salt-key -L`, vemos que el Master recibe petición del Minion.
```
Accepted Keys:
Denied Keys:
Unaccepted Keys:
salt-minionXXg
Rejected Keys:
```
* `salt-key -a salt-minionXXg`, para que el Master acepte a dicho Minion.
* `salt-key -L`, comprobamos.

## 4.3 Comprobamos conectividad

Comprobamos la conectividad desde el Master a los Minions.
```
# salt salt-minionXXg test.version
salt-minionXXg:
    2019.2.0
# salt salt-minionXXg test.ping
salt-minionXXg:
    True
```

---
# 5. Salt States


---
# ANEXO

Enlaces de interés:
* https://riptutorial.com/es/salt-stack/example/5504/instalacion-o-configuracion
* https://www.elmundoenbits.com/2013/10/primeros-pasos-saltstack.html?m=1
* https://www.linode.com/docs/applications/configuration-management/beginners-guide-to-salt/


> For openSUSE Tumbleweed run the following as root:
>
> zypper addrepo http://download.opensuse.org/repositories/systemsmanagement:/saltstack/openSUSE_Tumbleweed/systemsmanagement:saltstack.repo
> zypper refresh
> zypper install salt salt-minion salt-master

* `salt-master`, ejecutar el master.
> Usar `salt-master -d` para ejecutar en segundo plano (daemon).
* `salt-minion`, ejecutar el minion.
> Usar `salt-minion -d` para ejecutar en segundo plano (daemon).
