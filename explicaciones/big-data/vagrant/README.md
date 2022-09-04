# Máquina Virtual

## Descargar la máquina virtual

Enlaces para descargar máquinas virtuales:
* [Cloudera QuickStart VM 5.13 VirtualBox](https://downloads.cloudera.com/demo_vm/virtualbox/cloudera-quickstart-vm-5.13.0-0-virtualbox.zip)
* [Cloudera QuickStart VM 5.13 VMWare](https://downloads.cloudera.com/demo_vm/vmware/cloudera-quickstart-vm-5.13.0-0-vmware.zip)
* [Oracle Big Data Lite Virtual Machine](https://www.oracle.com/database/technologies/bigdatalite-v411.html#hol)
* [Azure Data Science Virtual Machines](https://azure.microsoft.com/es-es/services/virtual-machines/data-science-virtual-machines/#product-overview)
* [HortonWorks SandBox](https://www.cloudera.com/downloads/hortonworks-sandbox/hdp.html)

## Problemas frecuentes con las máquinas virtuales

Asuntos comunes al resolver
* _Problemas con la conectividad de Red_: Es importante que la máquina virtual se conecte a la red por lo que habrá que revisar la configuración de los adaptadores de red de Virtual Box.
* _Problemas con los cambios de monitor_: A veces cuando se cambia de monitor/resolución se producen cambios en la ventana que muestra la máquina virtual. Será necesario ir adaptando el escalado de la pantalla (150%, 200%)
* [Problemas al importar por safe mode en NameNode](https://community.cloudera.com/t5/Support-Questions/Namenode-safe-mode/td-p/155251)
* [Error al manejar el sistema de archivos](https://community.cloudera.com/t5/Support-Questions/quickstart-VM-connection-issues/td-p/64544)
* [Problemas al importar datos de HDFS a Impala](https://community.cloudera.com/t5/Support-Questions/Impala-SQL-Unable-to-LOAD-DATA-from-HDFS-path-due-to-WRITE/td-p/51898)
* [Instalación de nuevos paquetes - por ahora no necesario](https://community.cloudera.com/t5/Support-Questions/quickstart-CDH-5-13-repo-issue/td-p/82902)

## Instalación de Hadoop


> Se puede probar con otras versiones más recientes como en otros cuadernos, pero siempre cuidando posteriormente que las versiones del resto de componentes (sqoop, hive, pig, etc.) sean compatibles. 

* Instalar Hadoop. 

```bash
wget https://downloads.apache.org/hadoop/common/hadoop-3.3.1/hadoop-3.3.1.tar.gz
tar -xzf hadoop-3.3.1.tar.gz
mv  hadoop-3.3.1/ /usr/local/
```
* Actualizar las variables de entorno
```python
import os
os.environ["JAVA_HOME"]="/usr/lib/jvm/java-11-openjdk-amd64"
os.environ["PATH"] = os.environ["PATH"] + ":" + "/usr/local/hadoop-3.3.1/bin"
```
* Comprobamos instalación: `!hadoop version`


## Sqoop/Hive (Máquina Virtual)

**Objetivo**: Importar y manejar datos de RDBMS - SQL a nuestro ecosistema Hadoop (Sqoop - Hive/Impala)

**NOTA:** Pre-requisitos: SQL

Vamos a utilizar una base de datos que ya existe en la máquina virtual de cloudera, pero que también podemos importar los datos de Internet. 


!wget https://github.com/skamalj/datasets/raw/master/retaildb_dump.tar.gz
!tar -xvzf retaildb_dump.tar.gz

También se pueden utilizar los datos que en github están en data_berka.zip y cuya descripción es está en el siguiente [enlace](https://webpages.uncc.edu/mirsad/itcs6265/group1/domain.html#:~:text=The%20Berka%20dataset%20is%20a%20collection%20of%20financial,all%20of%20which%20are%20represented%20in%20the%20data.)



!wget https://github.com/curso-iabd-uclm/hadoop/raw/main/data_berka.zip

1. Arrancar mysql  y comprobar usuarios

```
sudo service mysqld start
mysql -u root -p ....
show databases;
select user from mysql.user;

```

2. Crear usuario para las importaciones (falta en el video)

```
create user tut211026 identified by '1234';
grant all on retail_db.* to 'tut212026';
```



3. evitar el warning de accumulo

%%bash

sudo mkdir /var/lib/accumulo
ACCUMULO_HOME='/var/lib/accumulo'
export ACCUMULO_HOME

**Sqoop** es una aplicación con interfaz de línea de comando para transferir datos entre bases de datos relacionales y Hadoop. 

Soporta cargas incrementales de una sola tabla o de una consulta SQL en formato libre así como trabajos almacenados que pueden ser ejecutados múltiples veces para importar las actualizaciones realizadas en una base de datos desde la última importación.

```
sqoop list-tables 
--connect "jdbc:mysql://localhost/retail_db"
   --username tut211026 --password 1234
```



```
sqoop eval  
  --connect "jdbc:mysql://localhost/retail_db"    
  --username tut211026 --password 1234 
  --query "select * from customers limit 10"
```



**sqoop import to hdfs**


```
sqoop import  
  --connect "jdbc:mysql://localhost/retail_db"    
  --username tut211026 --password 1234 
  --table customers
```

Comprobaciones

```
hdfs dfs -ls customers
hdfs dfs -cat customers | head
```



Hive



**sqoop import to hive**



```
sqoop import  
   --connect "jdbc:mysql://localhost/retail_db" 
  --username tut211026 --password 1234 
  --table customers --hive-import

```

Comprobaciones: Utilizar HUE para consultar la Base de Datos por Defecto de HIVE


!hive
!create database test_db




```
sqoop import 
  --connect jdbc:mysql://localhost/retail_db      
  --username tut01home --password 1234   --split-by customer_id 
  --columns customer_id,customer_fname,customer_lname 
  --table customers 
  --target-dir /user/cloudera/test/raw/customers 
  --fields-terminated-by "," 
  --hive-import 
  --create-hive-table 
  --hive-table test_workspace.customers
```



**beeline**



```
!connect jdbc:hive2://localhost:10000
show databases;
```




# Sqoop

### Instalación de Sqoop

1. Download sqoop


%%bash 
wget http://archive.apache.org/dist/sqoop/1.4.7/sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz
tar -xf sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz
mv sqoop-1.4.7.bin__hadoop-2.6.0 /usr/local/sqoop 

2. Download libraries

%%bash
wget https://dlcdn.apache.org//commons/lang/binaries/commons-lang-2.6-bin.tar.gz
tar -xf commons-lang-2.6-bin.tar.gz
cp /content/commons-lang-2.6/commons-lang-2.6.jar /usr/local/sqoop/lib

3. Variables de Entorno

import os
os.environ["HADOOP_HOME"] = "/usr/local/hadoop-3.3.1"
os.environ["HADOOP_COMMON_HOME"] = "/usr/local/hadoop-3.3.1" 
os.environ["HADOOP_MAPRED_HOME"] = "/usr/local/hadoop-3.3.1"
os.environ["SQOOP_HOME"] = "/usr/local/sqoop"
os.environ["PATH"] = os.environ["PATH"] + ":" + "/usr/local/sqoop/bin" 
os.environ["HADOOP_COMMON_LIB_NATIVE_DIR"] = "/usr/local/hadoop-3.3.1/lib/native"
os.environ["CLASSPATH"] = "/content/tmp"
# variables de partes no instaladas pero que sqoop busca (eliminar warnings)
os.environ["HBASE_HOME"] = "/usr/local/hadoop-3.3.1"
os.environ["HCAT_HOME"] ="/usr/local/hadoop-3.3.1"
os.environ["ACCUMULO_HOME"] ="/usr/local/hadoop-3.3.1"
os.environ["ZOOKEEPER_HOME"] = "/usr/local/hadoop-3.3.1"

os.environ["CLASSPATH"]

4. Test

!sqoop-version

 Revisión de los drivers y librerías preinstaladas en sqoop

!ls "/usr/local/sqoop/lib"

Sqlite



```
gdown --id 0BwUVtTEFk6GAMmc0WmF0SUl2d3c
mv sqlite-jdbc-3.7.2.jar "/usr/local/sqoop/lib"
```



%%bash
wget http://ftp.ntu.edu.tw/MySQL/Downloads/Connector-J/mysql-connector-java-5.1.49.tar.gz
tar -zxf mysql-connector-java-5.1.49.tar.gz
cd mysql-connector-java-5.1.49
mv mysql-connector-java-5.1.49-bin.jar /usr/local/sqoop/lib
ls /usr/local/sqoop/lib

### Ejecución de Sqoop

La base de datos Rfam es una colección de familias de secuencias de ARN estructurales que incluyen genes de ARN no codificantes, así como elementos cis-reguladores. Cada familia está representada por un alineamiento múltiple de secuencias y un modelo de covarianza (MC).

[Enlace](https://docs.rfam.org/en/latest/about-rfam.html)



```
sqoop import 
  --connect jdbc:mysql://mysql-rfam-public.ebi.ac.uk:4497/Rfam 
  --username rfamro --password "" 
  --table family --m 1 
```



%%bash
sqoop import -Dmapreduce.job.user.classpath.first=true \
  --connect jdbc:mysql://mysql-rfam-public.ebi.ac.uk:4497/Rfam  \
  --username rfamro --password "" \
  --table family \
  --m 1 \
  --bindir /usr/local/hadoop-3.3.1/etc/hadoop \
  --class-name family \

!hdfs dfs -cat family/* | head

**RECORDATORIO**: 
-  `-m 1` Dado que es un entorno standalone no debemos forzar el split de los datos sino al contrario, establecemos el número de maps a 1
- bindir a un directorio dentro del classpath de hadoop

Se ha creado una base de datos en el servicio db4free.net a imagen y semejanza de los datos que proporciona Cloudera en su base de datos con el fin de que en sqoop en Google Colab podamos hacer lo mismo que en la máquina virtual. 

**NOTA: db4free es un servicio libre, a veces lento y que puede tener un funcionamiento no constante**

Importar base de datos cloudera



```
!sqoop import 
  --connect jdbc:mysql://85.10.205.173:3306/retail_db_iabd 
    --username retail_dba --password cloudera 
    --table orders --m 1 
```



%%bash
sqoop import --connect jdbc:mysql://85.10.205.173:3306/retail_db_iabd \
  --username retail_dba --password cloudera \
  --table orders \
  --m 1 \
  --bindir /usr/local/hadoop-3.3.1/etc/hadoop

!hdfs dfs -cat orders/* | head

Base de datos con registros de reclamaciones de seguros (una sola tabla)



```
!sqoop import 
  --connect jdbc:mysql://85.10.205.173:3306/claims_iabd 
  --username claims_dba --password cloudera 
  --table claims --m 1 
```



%%bash
sqoop import --connect jdbc:mysql://85.10.205.173:3306/claims_iabd \
     --username claims_dba \
     --password cloudera \
     --table claims \
     --m 1  \
     --bindir /usr/local/hadoop-3.3.1/etc/hadoop
