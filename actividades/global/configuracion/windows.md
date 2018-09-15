
> Documentos relacionados
>
> * Configurar [VirtualBox](../virtualbox/general.md)
> * Configurar [Acceso remoto](../acceso-remoto/windows7.md)

# Configurar MV Windows 7

> * Donde aparezca AA debemos poner el código asignado al aula:
>     * 18 para el aula108
>     * 19 para el aula109
> * Donde aparezca XX debemos poner el código asignado al alumno.
>
> Para averiguar XX ejecutar en la máquina real, `ip a` o `ifconfig` o `if a s`, si muestra IP 172.16.8.30 entonces XX=30.

Configuración de la máquina Windows 7 Enterprise:
* Configuramos el interfaz de red puente en modo estático.
* IP: `172.AA.XX.11` (Donde XX corresponde al nº de cada puesto).
    * Si tenemos varias máquinas usaremos las IP 172.AA.XX.12, 172.AA.XX.13, etc.
    * Máscara de red: `255.255.0.0`
    * Gateway: `172.AA.0.1`
    * Servidor DNS: `8.8.4.4`
* Nombre de equipo: `primer-apellido-del-alumno+XXw`.
    * El nombre de equipo se cambia en `Inicio -> Equipo -> (Botón derecho) -> Propiedades`
    * Por ejemplo: vargas30w
    * El nombre NetBIOS sólo puede tener 16 caractéres.
    * Si tenemos varias máquinas las llamaremos vargas30w, vargas30x, vargas30y, etc.
* Los nombres de usuario, máquina y dominio deben estar en minúsculas.
Sin usar caracteres especiales como ñ, tildes, espacios, etc.
* Grupo de trabajo: `curso1516` (Modificar los números al curso actual)
* Tarjeta de red VBox en modo puente.
* Configurar [acceso remoto](../acceso-remoto/windows7.md).
* Configurar [firewall](../firewall.md).

## Comprobaciones finales

Capturar imágenes de las configuraciones desde *PowerShell*.
```
date
hostname
ipconfig
route PRINT
nslookup www.iespuertodelacruz.es
ping 8.8.4.4
```

## Periodo de pruebas

Una vez instalado el SO Windows 7 disponemos de unos 30 días trabajar con el sistema,
antes de que pase al estado *"Copia ilegal"*.

Al finalizar este plazo de tiempo podemos:

1. Activar el SO introduciendo un código de activación válido.
2. Renovar el perido de pruebas por 30 días más, mediante el comando: `slmgr -rearm`.
Podemos renovar varias veces, pero el tiempo máximo que podemos usar el SO antes de activarlo
es de 90 días.

---

# Enlaces de interés

* Descargar MV's Windows de 90 días: https://developer.microsoft.com/en-us/microsoft-edge/tools/vms/?ranMID=24542&ranEAID=je6NUbpObpQ&ranSiteID=je6NUbpObpQ-W0V2C8ws0lPKDJhnOzgr.w&epi=je6NUbpObpQ-W0V2C8ws0lPKDJhnOzgr.w&irgwc=1&OCID=AID681541_aff_7593_1243925&tduid=(ir_w-gzCwyO92LwwJ23Ax1SsytrUkgxfZX9jzr%3A1Q0)(7593)(1243925)(je6NUbpObpQ-W0V2C8ws0lPKDJhnOzgr.w)()&irclickid=w-gzCwyO92LwwJ23Ax1SsytrUkgxfZX9jzr%3A1Q0)
* [Bash en Windows10](http://www.xataka.com/aplicaciones/asi-es-usar-la-consola-bash-de-ubuntu-en-windows-10)
* [Cómo aceder a una partición GNU/Linux desde Windows](https://es.opensuse.org/SDB:Acceder_a_la_particion_de_GNU/Linux_desde_Windows)
