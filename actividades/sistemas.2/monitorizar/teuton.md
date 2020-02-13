
```
Curso       : 201920
Area        : Sistemas operativos, monitorización
Descripción : Practicar con una herramienta para hacer
              test de infraestructura.
Requisitos  :
Tiempo      :
```

---
# 1.Test de infrastructura

* ¿Qué son los test de infraestructura?
* Infraestructura como código (IaC).

* Comparativa

| Features | Teuton | Testinfra | Goss |
| -------- | ------ | --------- | ---- |
| URL      | https://github.com/teuton-software/teuton | https://testinfra.readthedocs.io/en/latest/index.html | https://github.com/aelsabbahy/goss |
| License  | GPL v.3 | Apache license 2.0 | Apache license 2.0 |
| Programming language | Ruby | Python | Go |
| Platforms | Multiplatform | Multiplatform | GNU/Linux |
| Remote connections | SSH, Telnet | SSH | ? |
| Installed on | Master | Slave |  |
| Test Units Lenguage definition | Teuton DSL | Python | YAML |
| Configuration input files | YAML, JSON | Python | YAML |
| Output formats      | Documentation, TXT, YAML, JSON, silent | ? | rspecish, documentation, JSON, TAP, JUnit, nagios, silent |
| Builtin functions | Yes | Yes | ... |
| Function creation | Yes | ?   | ? |

## 1.1 Preparativos

* MV1 GNULinux
* MV2 GNULinux
* MV3 Windows
* MV4 RbPI

Configurar IP estáticas.
Activar servicio SSH.
Habilitar acceso SSH a root.

## 1.2 Instalación

Enlace de interés:
* https://github.com/teuton-software/teuton

Instalación de "teuton" (T-NODE)
* Tener ruby instalado. `ruby -v` para consultar versión de ruby.
* `gem install teuton`
* `teuton version`, comprobar versión.

---
# 2. Crear Test Unit

Ir a la MV1:
* Ir al directorio `Documentos` para trabajar ahí.
* `teuton create alumnoXX/test1`.
* Modificar `config.yaml` para incluir todas las máquinas que queremos monitorizar:

```
---
:alias:
:global:
  :host_username: root
:cases:
- :tt_members: T-NODE-XX
  :host_ip: localhost
  :host_password: clave-secreta
- :tt_members: S-NODE-XX GNU/Linux
  :host_ip: 172.19.XX.32
  :host_password: clave-secreta
- :tt_members: S-NODE-XX Windows
  :host_ip: 172.19.XX.11
  :host_password: clave-secreta
- :tt_members: S-NODE-XX Raspberry PI
  :host_ip: 172.19.XX.51
  :host_password: clave-secreta
```
* Modificar `start.rb` para comprobar lo siguiente en las máquinas:
    * hostname
    * ping 8.8.4.4
    * host www.nba.com
