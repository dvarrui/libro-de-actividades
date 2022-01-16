# Montar una carpeta compartida en GNU/Linux con SAMBA

Si quieres mapear una carpeta compartida desde Windows en un equipo GNU/Linux con [SAMBA](https://www.samba.org/), hay una forma sencilla de hacerlo desde la línea de comandos.

## Instalar los paquetes necesarios

Desde un Ubuntu u otra distribución basada en Debian, podemos instalar el software necesario ejecutando los siguientes comandos:

```bash
sudo apt install smbclient cifs-utils
```

## Comprobar la conexión

Una vez tenemos las herramientas necesarias instaladas, debemos verificar si desde GNU/Linux podemos acceder a la carpeta compartida. Para esto utilizamos el siguiente comando:

```bash
smbclient –L servidor [ –U usuario ]
```

> En `servidor` debemos escribir el nombre o la IP del equipo que comparte la carpeta, y `usuario` es el nombre del usuario con permisos para acceder al servidor.

Este comando nos pedirá la contraseña del usuario.

Si todo va bien, mostrará un listado con los recursos compartidos por `servidor`.

## Crear el punto de montaje

Cada vez que queramos montar una unidad en GNU/Linux debemos crear un directorio donde montarlo. En este caso, vamos montar la carpeta compartida en el directorio `/mnt/datos`, así que vamos a crearlo:

```bash
sudo mkdir /mnt/datos
```

>  Se utiliza sudo porque se está creando un directorio dentro de un directorio del sistema.

## Montar la carpeta compartida

El siguiente comando montará la carpeta compartida `remota` en `servidor` en el directorio `/mnt/datos`:

```bash
mount –t cifs –o username=usuario,rw //servidor/remota /mnt/datos
```

Donde `usuario` es el nombre de un usuario con permisos para acceder a la carpeta compartida, `remota`es el nombre de la carpeta compartida y `rw` significa que queremos montarla con permisos de lectura y escritura (Read-Write). 

Si queremos montar la carpeta compartida con acceso como invitado con permisos de sólo lectura (Read-Only) debemos hacer lo siguiente:

```bash
mount –t cifs –o guest,ro //servidor/remota /mnt/datos
```