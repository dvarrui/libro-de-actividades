
```
Curso       : 201920
Area        : Sistemas operativos, monitorización, devops
Descripción : Practicar test de infraestructura
Requisitos  : Varias máquinas. Una al menos con GNU/Linux
Tiempo      :
```

---
# 1.Test de infrastructura

Propuesta de rúbrica:

| Criterio        | Muy bien(2) | Regular(1) | Poco adecuado(0) |
| --------------- | ----------- | ---------- | ---------------- |
| (2.2) Comprobar ||||
| (3.2) Comprobar ||||
| (4.2) Comprobar ||||
| (5.2) Comprobar ||||
| (6.2) Comprobar |||.|

## 1.1 Introducción

* ¿Qué son los test de infraestructura?
* Ver la infraestructura como código (IaC).

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

## 1.2 Preparativos

Listado de las máquinas que necesitamos:

| ID | Sistema  | Hostname     | IP           |
| -- | -------- | ------------ | ------------ |
|  1 | [OpenSUSE](../../global/configuracion/opensuse.md) | apellidoXXg  | 172.AA.XX.31 |
|  2 | [OpenSUSE](../../global/configuracion/opensuse.md) | apellidoXXg2 | 172.AA.XX.32 |
|  3 | [RbPI](../../global/configuracion/rbpi.md)     | apellidoXXrb | 172.AA.XX.51 |
|  4 | [Windows](../../global/configuracion/windows.md)  | apellidoXXw  | 172.AA.XX.11 |

En todas las máquinas:
* Configurar IP estática.
* Activar servicio SSH.
* Habilitar acceso SSH a root.
* Comprobar acceso remoto con `ssh root@ip-mv`.

## 1.3 Modos de trabajo

> Enlaces de interés:
> * [Modos de uso](https://github.com/teuton-software/teuton/blob/devel/docs/install/modes_of_use.md)

* Entender los modos de trabajo de Teuton: T-NODE y S-NODE.
* T-NODE: Máquina con Teuton.
* S-NODE: Máquina con el servicio SSH.

## 1.4 Instalación

> Enlace de interés:
> * [Instalación](https://github.com/teuton-software/teuton/blob/devel/docs/install/install.md)

Instalación de "teuton" (T-NODE)
* Tener ruby instalado. `ruby -v` para consultar versión de ruby.
* `gem install teuton`
* `teuton version`, comprobar versión.

---
# 2. Test: conectividad

## 2.1 Crear el test

Ir a la MV1:
* Ir al directorio `Documentos` para trabajar ahí.
* `teuton create alumnoXX/test2`. Los ficheros principales son:
    * `congig.yaml`, fichero de configuración de las máquinas
    * `start.rb`, definición de las unidades de prueba.
* Modificar `config.yaml` para incluir todas las máquinas que queremos monitorizar:

```
---
:alias:
:global:
:cases:
- :tt_members: T-NODE-XX
  :host_ip: localhost
- :tt_members: S-NODE-XX GNU/Linux
  :host_ip: 172.19.XX.32
- :tt_members: S-NODE-XX Raspberry PI
  :host_ip: 172.19.XX.51
- :tt_members: S-NODE-XX Windows
  :host_ip: 172.19.XX.11
```

* Vamos a modificar `start.rb` para comprobar que hay conectividad con las máquinas:

```
group "alumnoXX - test2" do

  target "Hay conectividad con la Máquina #{get(:host_ip)}"
  run, :exec => "ping -c 1 #{get(:host_ip)}"
  expect ["1 received", "0% packet loss"]
end

play do
  show
  export
end
```

## 2.2 Comprobar

* `teuton alumnoXX/test2`, ejecutar el test.
* Tenemos los resultados en el directorio `var/test2`.

**Entregar**

* Ficheros `alumnoXX/test2/*`
* Ficheros `var/test2/*`

---
# 3. Test: Configuración de red

## 3.1 Crear el test

* Crear el test `alumnoXX/test3`.
* Personalizar `config.yaml`

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
  :tt_skip: true
  :host_ip: 172.19.XX.11
  :host_password: clave-secreta
```

> De momento vamos a excluir (skip==true) de la monitorización a la máquina con Windows, por que los comandos son diferentes.

* Vamos a modificar `start.rb` para comprobar lo siguiente en las máquinas remotas:
    * Nombre de equipo: `hostname`
    * Usuario alumno: `id nombre-alumno`
    * Puerta de enlace: `ping 8.8.4.4 -c 1`
    * Servidor DNS: `host www.nba.com`

```
group "alumnoXX - test3" do

  target "La puerta de enlace funciona correctamente"
  goto :host, :exec => "ping 8.8.4.4 -c 1"
  expect ["1 received", "0% packet loss"]

  target "Servidor DNS funciona corectamente"
  goto :host, :exec => "host www.nba.com"
  expect "has address"

end

play do
  show
  export
end
```

## 3.2 Comprobar

* `teuton alumnoXX/test3`, ejecutar el test.
* Tenemos los resultados en el directorio `var/test3`.

**Entregar**

* Ficheros `alumnoXX/test3/*`
* Ficheros `var/test3/*`

---
# 4. Test: configuración básica

## 4.1 Modificar el test

* Copiar test3 en `alumnoXX/test4`.
* Ampliar los targets para comprobar lo siguiente en las máquinas remotas:
    * Nombre de equipo: `hostname`
    * Usuario alumno: `id nombre-alumno`
* Modificar el fichero de configuración para incluir nuevos parámetros (hostname y username):

```
---
:alias:
:global:
  :host_username: root
  :username: nombre-del-alumno
:cases:
- :tt_members: T-NODE-XX
  :host_ip: localhost
  :host_password: clave-secreta
  :hostname: apellidoXXg
- :tt_members: S-NODE-XX GNU/Linux
  :host_ip: 172.19.XX.32
  :host_password: clave-secreta
  :hostname: apellidoXXg2
- :tt_members: S-NODE-XX Raspberry PI
  :host_ip: 172.19.XX.51
  :host_password: clave-secreta
  :hostname: apellidoXXrb
- :tt_members: S-NODE-XX Windows
  :tt_skip: true
  :host_ip: 172.19.XX.11
  :host_password: clave-secreta
  :hostname: apellidoXXw
```

* Ejemplo de configuración en `start.rb`:

```
  target "Configurar nombre de equipo con #{get(:hostname)}"
  goto :host, :exec => "hostname"
  expect get(:hostname)
```

## 4.2 Comprobar

* `teuton alumnoXX/test4`, ejecutar el test.
* Tenemos los resultados en el directorio `var/test4`.

**Entregar**

* Ficheros `alumnoXX/test4/*`
* Ficheros `var/test4/*`

---
# 5. Test: directorios y permisos

* Crear un nuevo test `alumnoXX/test5`.
* Definir las comprobaciones necesarias en `start.rb`para:
    * Comprobar que existe el grupo `jedis`.
    * Comprobar que existe el usuario `obiwan`.
    * Comprobar que `obiwan`es miembro del grupo `jedis`.
    * Comprobar que existe el directorio `/home/obiwan/private`.
    * Comprobar que existe el directorio `/home/obiwan/group`.
    * Comprobar que existe el directorio `/home/obiwan/public`.
    * Comprobar `/home/obiwan/private` tiene los permisos `700`.
    * Comprobar `/home/obiwan/group` tiene los permisos `750`.
    * Comprobar `/home/obiwan/public` tiene los permisos `755`.

## 5.2 Comprobar

* `teuton alumnoXX/test5`, ejecutar el test.
* Tenemos los resultados en el directorio `var/test5`.

**Entregar**

* Ficheros `alumnoXX/test5/*`
* Ficheros `var/test5/*`

---
# 6. Test: Otros sistemas

## 6.1 Crear el test

* Copiar el contenido del test3 en `alumnoXX/test6`.
* Modificar `config.yaml` para monitorizar únicamente a la máquina Windows.
    * Poner `:tt_skip: true` o `:tt_skip: false` según convenga.
* Adaptar los comandos de comprobación al sistema operativo Windows. Por ejemplo, cambiar `id nombre-alumno` por `net user alumno`.

## 6.2 Comprobar

* `teuton alumnoXX/test6`, ejecutar el test.
* Tenemos los resultados en el directorio `var/test6`.

**Entregar**

* Ficheros `alumnoXX/test6/*`
* Ficheros `var/test6/*`
