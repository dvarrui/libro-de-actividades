
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

Listado de MVs:
1. MV1 GNULinux
2. MV2 GNULinux
3. MV3 RbPI
4. MV4 Windows

En todas las MVs:
* Configurar IP estáticas.
* Activar servicio SSH.
* Habilitar acceso SSH a root.

## 1.2 Instalación

> Enlace de interés:
> * https://github.com/teuton-software/teuton

* Entender los modos de trabajo de Teuton: T-NODE y S-NODE.

Instalación de "teuton" (T-NODE)
* Tener ruby instalado. `ruby -v` para consultar versión de ruby.
* `gem install teuton`
* `teuton version`, comprobar versión.

---
# 2. Crear Test Unit

Ir a la MV1:
* Ir al directorio `Documentos` para trabajar ahí.
* `teuton create alumnoXX/test1`. Los ficheros principales son:
    * `congig.yaml`, fichero de configuración de las máquinas
    * `start.rb`, definición de las unidades de prueba.
* Modificar `config.yaml` para incluir todas las máquinas que queremos monitorizar y sus configuraciones:

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
- :tt_members: S-NODE-XX Raspberry PI
  :host_ip: 172.19.XX.51
  :host_password: clave-secreta
- :tt_members: S-NODE-XX Windows
  :host_ip: 172.19.XX.11
  :host_password: clave-secreta
```

* Vamos a modificar `start.rb` para comprobar lo siguiente en las máquinas remotas:
    * Puerta de enlace: `ping 8.8.4.4 -c 1`
    * Servidor DNS: `host www.nba.com`

```
group "alumnoXX - test1" do

  target "La puerta de enlace funciona correctamente"
  goto :host, :exec => "ping 8.8.4.4 -c 1"
  expect ["1 received", "0% packet loss"]

  target "Servidor DNS funciona corectamente"
  goto :host, :exec => "ping 8.8.4.4 -c 1"
  expect "has address"
end

play do
  show
  export
end
```

* `teuton alumnoXX/test1`, Ejecutar el test1.
