
```
Estado     : EN CONSTRUCCIÓN!!!
Requisitos : 2 MV's
```
---

# 1. Saltstack

Saltstack es un gestor de infrastructura como Puppet, Chef y Ansible.
En esta actividad vamos a practicar Saltstack con OpenSUSE.

# 2. Preparativos

| Configuración | MV1 | MV2 |
| -------- | --- | --- |
| Hostname | salt-masterXXg | salt-minionXXg |
| SO       | GNU/Linux OpenSUSE | GNU/Linux OpenSUSE |
| IP       | 172.19.XX.31 | 172.19.XX.32 |

* Configurar `/etc/hosts` con "IP nombre" de MV1 y MV2.

> "XX" es el número asignado a cada alumno.

---
# 3. Master: instalación de configuración.

Enlaces de interés:
* [OpenSUSE -Saltstack](https://docs.saltstack.com/en/latest/ref/configuration)

Instalación:
* Ir a la MV1
* `zypper install salt-master`, instalar el software del master.

Configuración:
* Modificar `/etc/salt/master` para configurar nuestro interfaz de red:
```
interface: 172.19.XX.31
file_roots:
  base:
    - /srv/salt
```
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
# 4. Minion

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
# salt '*' test.version
salt-minionXXg:
    2019.2.0
# salt '*' test.ping
salt-minionXXg:
    True
```

> `'*'` representa a todos los minions aceptados. Se puede especificar un minion o conjunto de minios concretos.

---
# 5. Salt States

* Buscar cómo está definido `file_roots` en `/etc/salt/master`. Esta es la variable que define dónde se almacenarán los estados de Salt.
* En OpenSUSE, tendrá el valor `/srv/salt`. Entonces tenemos que FILEROOTS=/srv/salt.

## 5.1 Crear el estado apache2

Los estados de Salt se definen en ficheros SLS.
* Crear fichero `FILEROOTS/apache2/init.sls`:
```
apache2:
  pkg.installed: []
  service.running:
    - require:
      - pkg: apache2
```

Entendamos las definiciones:
* La primera línea es un identificador (ID = apache2) que se usa cuando se quiere invocar. El resto del fichero contiene el estado.
* pkg.installed se asegura que el paquete esté instalado.
* service.running: se asegura de que el servicio esté iniciado.
* require: Establece como requisito de que el servicio se inicia después de la instalación del paquete "apache2".

## 5.2 Asociar minions a roles/estados

* Crear `FILEROOTS/top.sls`, donde asociamos a todos los minions con el estado que acabamos de definir.
```
base:       
  '*':
    - apache2/init
```

Seguimos:
* Comprobar que  que no tenemos instalado apache2 en el minion.
* `salt-call '*' state.apply`, para aplicar los estados en todos los minions.
`salt '*' state.apply`
