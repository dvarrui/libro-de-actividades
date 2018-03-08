
`En construcción`

# 1. Introducción

Enlaces de interés:

http://dchaparro.net/visualizacion-de-metricas-grafana-influxdb-y-collectd/

Vamos a trabajar con varias herramientas que nos servirán para visualizar
estadísticas de rendimiento del servidor, y tener controlados todos sus recursos:
CPU, memoria, disco.

Estas son: Collectd, InfluxDB y Grafana.

---

# 2. CollectD

Es una herramienta que se encarga de recolectar diferentes contadores
y métricas de rendimiento de sistema y/o aplicación y se encarga de guardar esos
valores de diferentes formas, como por ejemplo enviarlo por red (IP:puerto)
a distintos sistemas de almacenamiento, como por ejemplo a una base de datos InfluxDB.

## 2.1 Instalación

* Actualizar el catágolo de paquetes disponibles: `apt-get update`.
* Instalar collectd: `apt-get install collectd`.

## 2.2 Configuración

* Hacemos una copia de backup al fichero de configuración antes de modificarlo:
`cp /etc/collectd/collectd.conf /etc/collectd/collectd.conf.bak`.
* Recuerda: Las líneas con el símbolo # están comentadas.
* Editamos el fichero de configuración `collectd.conf` y ponemos lo siguiente:

```
Hostname "nombre-del-equipo"
Interval 10
ReadThreads 5
```
* Además nos aseguramos de tener activos los siguientes plugins:

```
LoadPlugin cpu
LoadPlugin df
LoadPlugin disk
LoadPlugin interface
LoadPlugin load
LoadPlugin memory
LoadPlugin network
LoadPlugin swap
LoadPlugin uptime
LoadPlugin users
```

* Vamos configurar el plugin "network" para que envíe los datos recopilados al siguiente IP/puerto (que es donde escuchará la base de datos InfluxDB que instalaremos a continuación):

```
<Plugin network>
     Server "127.0.0.1" "25826"
</Plugin>
```

## 2.3 Comprobación

* Ejecutamos el siguiente comando para comprobar que la sintaxis del fichero es la correcta:  `collectd -t`
* Ejecutamos el siguiente comando para comprobar que la sintaxis de los Plugins es correcta:  `collectd -T`
* Paramos el servicio `systemctl start collectd`.
* Iniciamos el servicio `systemctl stop collectd`.
* Comprobamos el estado actual del servicio `systemctl start collectd`.

---

# 3. InfluxDB

Es un servidor de base de datos NOSQL utilizado para gestionar
series de tiempo. Es decir, datos cuyo "índice" es una marca de fecha/hora y unos
cuantos campos asociados a ese registro. Ideal para almacenar datos de rendimiento,
contadores, eventos y cosas similares. Está escrita en Go, desarrollada por Google
y promete tiempos de respuesta impresionantes comparados con otras alternativas.

## 3.1 Instalación

* Instalamos InfluxDB con `apt-get isntall influxdb`.

## 3.2 Configuración

* Hacemos una copia de backup al fichero de configuración antes de modificarlo:
`cp /etc/influxdb/influxdb.conf /etc/influxdb/influxdb.conf.bak`.
* Editar el fichero `influxdb.conf` con lo siguientes parámetros:

```
[collectd]
enabled = true
bind-address = ":25826"
database = "collectd"
typesdb = "/usr/share/collectd/types.db"
```

## 3.3 Comprobamos

---

# 4. Grafana

Es una herramienta para consultar y visualizar series de datos de
forma "bonita". Es una herramienta muy potente, con un editor de consultas muy
elaborado que te permite elegir entre las métricas que tengas registradas y
realizar con ellas todo el tratamiento que necesites. Como origen de datos
también tiene gran variedad, pudiendo elegir entre CloudWatch, ElasticSearch,
Graphite, InfluxDB, OpenTSDB o Prometheus.
