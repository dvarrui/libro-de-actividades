
```
Curso       : 201819
Area        : Sistemas Operativos, dominios
Descripción : Instalar un PDC y configurarlo para
              autenticación y perfiles móviles
Requisitos  : Windows 2012 Server, Windows 10
Tiempo      : 6 horas
```

# Introducción

En esta práctica vamos a montar un PDC (Controlador Primario de Dominio) con Windows Server.

Propuesta de rúbrica:

| Criterio                         | Nivel 0 | Nivel 1 | Nivel 2 |
| -------------------------------- | ------- | ------- | ------- |
| (2.3) PDC: Instalar y configurar | | | |
| (4.4) Unir equipos al dominio    | | | |
| (5.4) Perfiles móviles           | | | |
| (6.0) Perfil obligatorio         | | |.|

# 1. Preparativos

## 1.1 Máquinas virtuales

Necesitaremos las siguientes máquinas virtuales:

| MV | Sistema operativo | Configuración | DNS |
| -- | ----------------- | ------------- | --- |
| 01 | Windows Server 2012 Enterprise | [Consultar](../../global/configuracion/windows-server.md) | DNS1 = `127.0.0.1` |
| 02 | Windows 10 Enterprise | [Consultar](../../global/configuracion/windows.md) | DNS1 = IP Windows Server |
| 03 | Windows 10 Enterprise | [Consultar](../../global/configuracion/windows.md) | DNS1 = IP Windows Server |

## 1.2 OBSERVACIONES

* Realizaremos las prácticas en MV's que pueden estar todas en el mismo PC o en varios diferentes.
* Las diferencias entre las distintas versiones de SO Windows: Standard, Professional, Enterprise, etc. son las funcionalidades/características que vienen incluidas.
* Windows Server es estricto con la política de seguridad, en cuanto a cómo deben definirse las claves (Mayúsculas, minúsculas, números y caracteres especiales y longitud superior a 10). Se puede deshabilitar en las `Directivas de seguridad local -> Directivas de cuenta`, pero reduciríamos la seguridad de las contraseñas. Un ejemplo de contraseñas segura: `obiwanKENOBI2016!`.
* Windows Server tiene una herramienta en `Inicio -> Administrar el Servidor`, que nos permite consultar la configuración del servidor, instalar/desinstalar paquetes/funciones/servicios, y acceder a los paneles de administración de los distintos servicios.

# 2. Instalar el Controlador de dominio

## 2.1 Definiciones

**AD**:
* El AD (Directorio Activo o Active Directory) es una base de datos LDAP, que guarda la información de los objetos de nuestro dominio.

**NOMBRE DE DOMINIO**:
* Cada PDC se identifica con su nombre de dominio, el cuál debe ser único. Hay que evitar que el nombre de dominio se repita con la configuración de otro compañero.
* En realidad podríamos poner cualquier nombre, pero lo haremos según indique el profesor, para organizar mejor las distintas máquinas de la clase.
* Los nombres de dominio NO debe ser muy largos. Preferiblemente menos de 10 letras, para evitar problemas con los clientes Windows anteriores a Vista/7/8.

## 2.2 Instalar en Windows 2012 Server

Instalación:
* Hacer una instantánea de la MV antes de nada.
* Para activar la función de controlador de dominios ir a `Inicio -> Administrar el servidor -> Agregar roles`.
    (Servicio de Dominio de Directorio Activo y el Servicio DNS).
* `Configurar Directorio Activo` con los siguientes valore:

| Parámetro                  | Valor |
| -------------------------- | ----- |
| Crear un bosque nuevo      | SI |
| FQDN del dominio (Este es el nombre del dominio) | segundoapellidoXXdom.curso1920 |
| Nivel funcional del bosque | Windows Server 2012 R2 |
| Servidor DNS               | SI |
| Carpetas de almacenamiento | Dejar valores por defecto |

> INFO [Advertencia con la delegación del servicio DNS](https://social.technet.microsoft.com/Forums/es-ES/d77ff7bb-0204-4cfd-94fd-c5160f794793/problema-durante-dcpromo?forum=wsades)

* Al terminar hay que reiniciar el sistema.
* Capturar imagen de `Panel del servidor` donde se muestren los servicios instalados.

## 2.3 Comprobaciones

* Ir a `Herramientas -> DNS` y comprobar que dentro de `Zona de búsqueda directa` aparece nuestro `nombre-de-dominio`.
* Abrir una consola y ejecutar `nslookup nombre-de-dominio`. Debe aparecer la IP de nuestro servidor PDC.

# 3. Usuarios del dominio

## 3.1 Teoría

* Leer/consultar los apuntes para entender los conceptos siguientes:
usuario local, usuario del dominio, equipo del dominio, grupo local, grupo del dominio.
* Enlace de interés:
    * [Definiciones de ámbito local, global y universal](https://msdn.microsoft.com/es-es/library/cc755692%28v=ws.10%29.aspx)
    * [Deshabilitar políticas de clave en Windows 2008 Server](http://www.radians.com.ar/blog/?p=403)  
    * [Usuarios y Grupos](http://www.ite.educacion.es/formacion/materiales/85/cd/windows/10Usuarios/index.html).
    * Información sobre [Tipos y ámbitos de grupo en Windows Server](https://es.slideshare.net/cesartg65/tipos-y-mbitos-de-grupo-windows-server)

## 3.2 Práctica

Vamos a crear usuarios y grupos del dominio:
* Ir a `Inicio -> Herramientas Administrativas -> Usuarios y Equipos de Active Directory`
* Crear los siguientes grupos:

| Grupo      | jediXX     | sithXX  |
| ---------- | ---------- | --------- |
| **Ámbito** | global     | global    |
| **Tipo**   | Seguridad  | Seguridad |
| **Usuarios de dominio** | yoda y obiwan | vader y maul |

> ¡OJO! No confundir usuarios locales con usuarios del dominio.

Vemos imagen con los usuarios del dominio creados:

![pdc-usuarios-dominio](./files/pdc-usuarios-dominio.png)

# 4. Equipos del dominio

## 4.1 Preparativos

Configurar las MV's clientes de la siguiente forma:
* Necesitaremos 2 MV's con Windows 7/10, que actuarán como equipos del dominio.
* **¡OJO!** Podemos crear una MV, y luego clonarla, modificando la MAC de la segunda MV, para no tener problemas de conectividad por tarjetas de red duplicadas.
* [Configurar las MVs](../../global/configuracion/windows.md)
* Poner la misma **fecha/hora y zona horaria** a las MV's. Todos los equipos deben estar sincronizados en cuanto al reloj. No puede haber diferencias de más de 5 minutos.
* Cada equipo cliente debe tener como DNS1 la IP del PDC, y como DNS2 la IP 8.8.4.4.
* Abrir una consola y ejecutar `nslookup nombre-de-dominio` para **comprobar que el DNS está correcto**. Debe aparecer una respuesta desde la IP de nuestro servidor PDC.
* Ejecutar `hostname` en una consola powershell. Debe aparecer el nombre correcto de la máquina.
* **Comprobar la conectividad** entre PDC-cliente y cliente-PDC usando el comando `ping` (Deshabilitar el cortafuegos si fuera necesario).

## 4.2 Unir equipo al dominio

Podemos unir el equipo al dominio por entorno gráfico o por comandos.

**Unir el equipo cliente al dominio por entorno gráfico**

* Ir al equipo cliente Windows.
* Ir a `Equipos (Botón derecho) -> Propiedades -> Cambiar configuración -> Cambiar -> Dominio`
* Escribir el nombre del dominio corto. Por ejemplo, si el dominio largo es `vargas42dom.curso1920`, nosotros pondremos sólo `vargas42dom`.
* Se nos pide poner un usuario/clave del dominio. Usaremos el usuario `Administrador` del dominio, que tenemos definido en el PDC.

Veamos imagen de ejemplo:

![pdc-unir-al-dominio](./files/pdc-unir-al-dominio.png)     

**Unir un equipo al dominio usando comandos**

* [Comando para unir equipo a dominio Windows Server](https://www.solvetic.com/tutoriales/article/2706-como-adicionar-windows-10-en-dominio-windows-server/)
* `netdom.exe join %nombreequipo% /domain:NombreDominio /UserD:NombreDominio\nombreUsuario /PasswordD:Password`, comando para unir un equipo al dominio.

> NOTA: `netdom.exe remove %nombreequipo%`, Para quitar y eliminar equipo de dominio.

## 4.3 Problemas en la unión al dominio

_Espero que no tengas problemas, y puedas saltar este apartado, si no es así... sigue leyendo._

Si tuviéramos poblemas al realizar esta tarea de unión del equipo al dominio, tenemos varias opciones:
* Esperar 5 minutos y repetir el proceso. Las redes SMB/CIFS tardan un tiempo en propagar la información de los equipos por la red.
* Volver a comprobar que todas las configuraciones son correctas.
    * Repite el paso uno (NOTA: Pon un compañero contigo mientras lo haces. 4 ojos ven más que 2).
    * (NOTA: Una configuración incorrecta del servidor DNS hará que no se puedan unir los equipos al dominio).
* ¿Repetimos?

## 4.4 Comprobaciones

Entraremos en los equipos cliente usando los usuarios del dominio.
Podemos comprobarlo por entorno gráfico o usando comandos.

### Por entorno gráfico

* Ir al PDC (Servidor) y comprobar que aparecen los equipos CLIENTE1 y CLIENTE2 como equipos del dominio.

![pdc-equipo-dominio](./files/pdc-equipo-dominio.png)


* Ir a la máquina cliente (Windows 7) y entrar con un usuario del dominio.

![pdc-login-cliente](./files/pdc-login-cliente.png)

![usuarios-cliente](./files/pdc-usuarios-cliente.png)

> INFO: Para entrar como usuario local poner "VARGAS42W\profesor". Esto es, "nombre-de-máquina\nombre-usuario-local".


### Por comandos

* Ir a la máquina cliente (Windows 7) y entrar con un usuario del dominio.
* Abrimos consola PowerShell y ejecutamos los comandos:
    * `whoami`, muestra nuestro usuario actual. Que debe ser un usuario del dominio.
    * `net user`, muestra los usuarios locales del sistema y no debe mostrar el usuario anterior.
    * `hostname`, muestra el nombre del equipo local.

---

# 5. Perfiles móviles

Material de lectura/estudio/consulta:
* Leer/consultar los apuntes para entender los conceptos de perfiles, perfil móvil y perfil obligatorio.
* Consultar Vídeo [Windows Server 2008 - Perfiles moviles con Windows 7](https://youtu.be/kgGmLWO6dzs)

## 5.1 Crear un segundo disco

* Crear un segundo disco (1GB). Lo usaremos para guardar los perfiles de los usuarios del dominio.

![pdc-disco-extra](./files/pdc-disco-extra.png)

* Hay que formatear el disco NTFS y asignarle la letra E.

![pdc-format-extra](./files/pdc-format-extra.png)

## 5.2 Configurar la ruta de perfil de cada usuario

* En el PDC, crear la carpeta `E:\perfiles`.
* En dicha carpeta, definir permisos lectura/escritura para el grupo `Usuarios del dominio`.
* Usar modo avanzado para compartir la carpeta por red con el nombre `perfiles$`.
    * Se recuerda que para acceder a la carpeta compartida de red, los usuarios
    deben tener permisos en el recurso de red y en la carpeta del sistema de ficheros.
* En el PDC, vamos a la herramienta de gestión de `Usuarios del dominio`. Modificar el atributo `ruta de acceso al perfil` de los siguientes usuarios del dominio:
    * yoda: `\\ip-del-PDC\perfiles$\%username%`
    * obiwan: `\\ip-del-PDC\perfiles$\obiwan`
    * vader: `\\ip-del-PDC\perfiles$\%username%`
    * maul: `\\ip-del-PDC\perfiles$\maul`


## 5.3 Personalizar los perfiles en CLIENTE1

* Iniciar sesión en CLIENTE1 con los usuarios obiwan y maul.
* Para cada usuario modificar el entorno del escritorio, colores, iconos.
* Para el usuario del dominio obiwan
    * Crear la carpeta `jedi` en el escritorio.
    * Crear fichero `Escritorio/jedi/personajes.txt`. Escribir dentro los nombres de los 2 jedis.
* Para el usuario dominio maul
    * Crear la carpeta `sith` en el escritorio.
    * Crear fichero `Escritorio/sith/personajes.txt`. Escribir dentro los nombres de los 2 siths.

> De este modo el "perfil" de cada usuario será diferente en aspecto y contenido.

* Debemos comprobar que se han creado las carpetas con los perfiles en el servidor para los usuarios anteriores.
A continuación se muestra una imagen de ejemplo:

![pdc-perfiles](./files/pdc-perfiles.png)

## 5.4 Comprobar perfiles desde CLIENTE2

> **Esto sólo hay que hacerlo con versiones anteriores a W2008server**
>
> Ir al equipo CLIENTE1: Vamos a limpiar el equipo cliente.
> * Iniciar sesión en CLIENTE1 con el "administrador" del dominio.
> * Ir a `Inicio -> Panel de Control -> Sistema -> Opciones Avanzadas -> Configuración de Perfiles de usuario`.
> * Comprobamos que los usuarios del dominio no tienen perfiles en local. En tal caso, vamos a liminar las copias de los perfiles locales en el equipo cliente para estos usuarios.

Ir al equipo CLIENTE2: Vamos a comprobar el perfil móvil.
* Entrar en el equipo CLIENTE2, con los usuarios del dominio (obiwan y maul).
    * Abrir PowerShell y ejecutar los siguientes comandos.
    * `hostname`, para mostrar nombre del equipo.
    * `whoami`, para mostrar nuestro usuario actual.
* Comprobar que tenemos perfiles móviles para ellos. El perfil móvil permite al usuario moverse por PC's diferentes y ver el mismo entorno con sus datos.

---

# 6. Perfiles obligatorios

* Enlaces de interés:
    * [Crear perfil obligatorio dando los permisos adecuados](http://somebooks.es/?p=3400).  
    * Vídeo [Usuario con perfil obligatorio Windows Server 2008](https://youtu.be/TKCmAFcKSGA).
* Primero vamos a dar permisos al usuario `Administrador` sobre el perfil de `maul`.
    * Desde Windows7. Entramos con usuario `maul`
    * Accedemos al recurso compartido `\\ip-del-pdc\perfiles$`
    * Añadimos al usuario `Administrador` para que tenga control total en la carpeta `maul.V2`.
* Convertir el perfil móvil del  Sith Maul, a perfil obligatorio (Los ficheros que hay que cambiar están ocultos y son del sistema).
* Comprobar que ahora el perfil no cambia.
* Ir a `Inicio -> Panel de Control -> Sistema -> Opciones Avanzadas -> Configuración de Perfiles de usuario`,
y comprobar que el perfil es ahora obligatorio.

---

# 7. Control de tiempo

Modificar los permisos de acceso de los usuarios del dominio, de la siguiente forma:
* Configurar a cada uno de los usuarios "jedis", para que sólo puedan acceder de 08:00 a 14:00 (de lunes a viernes) y
* Configurar a cada uno de los usuarios "siths", para que sólo pueden acceder de 14:00 a 20:00 (de lunes a viernes).

---

# ANEXO

## Duda con la complejidad de contraseñas

En Windows 2012 Server, queremos quitar la opcion de complejidad de contraseña pero aparece deshabilitada.
¿Cómo podemos habilitarla para poder desactivar la complejidad de contraseña?

* Open Run and type: `gpedit.msc`.
* From Local Group Policy Editor, choose from the tree (left): `Computer Configuration -> Windows Settings -> Security Settings -> Account Policies -> Password Policy`.
* Choose on the right: `Password must meet complexity requirements`
* Choose Disable.

Si no puedes realizar esto es porque tienes un GPO forzada por politicas desde un Domain Controller o estas usando la SecPol.msc.

## Comandos de Windows

* [Cambiar nombre equipo Windows con comando](https://www.solvetic.com/topic/5426-cambiar-nombre-equipo-windows-con-comando/)
    * `WMIC computersystem where caption='nombreDEahora' rename nuevoNombre`
    * Ahora tendrás que reiniciar tu PC Windows para que tenga efecto el nuevo nombre del equipo.
    * Este comando es válido para todos los sistemas Windows 10, 8, 7, Vista, XP, Server...
