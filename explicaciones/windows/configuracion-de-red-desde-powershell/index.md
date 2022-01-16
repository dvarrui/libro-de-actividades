# Configuración de red en Windows desde PowerShell

## Interfaces de red

Obtener la configuración IP  de las interfaces de red (similar a `ipconfig` ):

```bash
Get-NetIPConfiguration
```

Listar todos los adaptadores de red:

```bash
Get-NetAdapter
```

Obtener información de un adaptador de red específico:

```bash
Get-NetAdapter -Name *Ethernet*
```

Obtener información como `Speed` y  `Connection status`:

```bash
Get-NetAdapter | Format-Table Name, Status, Linkspeed
```

Obtener información acerca de los drivers (controladores):

```bash
Get-NetAdapter | Format-Table Name, DriverName, DriverVersion, DriverInformation, DriverFileName
```

Deshabilitar y habilitar una interfaz de red:

```bash
Disable-NetAdapter -Name "Wireless Network Connection"
Enable-NetAdapter -Name "Wireless Network Connection"
```

Renombrar una interfaz de red:

```bash
Rename-NetAdapter -Name "Wireless Network Connection" -NewName "Wi-Fi"
```

## Configuración IP

Obtener IP y servidores DNS de una interfaz de red:

```bash
Get-NetAdapter -Name "Wi-Fi" | Get-NetIPAddress
```

Obtener sólo la dirección IP:

```bash
(Get-NetAdapter -Name "Wi-Fi" | Get-NetIPAddress).IPv4Address
```

Obtener la información de los servidores de DNS:

```bash
Get-NetAdapter -Name "Wi-Fi" | Get-DnsClientServerAddress
```

Añadir una configuración de red a una interfaz:

```bash
New-NetIPAddress -InterfaceAlias "Wi-Fi" -IPAddress 10.0.1.95 -PrefixLength "24" -DefaultGateway 10.0.1.1
```

Cambiar la dirección IP de una interfaz:

```bash
Set-NetIPAddress -InterfaceAlias "Wi-Fi" -IPAddress 192.168.12.25 -PrefixLength "24"
```

Eliminar las direcciones IP de una interfaz:

```bash
Get-NetAdapter -Name "Wi-Fi" | Remove-NetIPAddress
```

Establecer los servidores de DNS:

```bash
Set-DnsClientServerAddress -InterfaceAlias "Wi-Fi" -ServerAddresses "10.10.20.1","10.10.20.2"
```

Establecer la configuración de una interfaz mediante DHCP:

```bash
Set-NetIPInterface -InterfaceAlias "Wi-Fi" -Dhcp Enabled
```

## Caché de DNS

Mostrar la caché de DNS (traducciones realizadas de nombres en IPs):

```bash
Get-DnsClientCache
```

Vaciar la caché de DNS:

```bash
Clear-DnsClientCache
```

## Ping

Para hacer un **ping** desde PowerShell,  usamos el cmdlet `Test-Connection`:

```bash
Test-Connection iesdomingoperezminik.es
```

Obtener información más detallada:

```bash
Test-NetConnection iesdomingoperezminik.es -InformationLevel Detailed
```

Hacer ping a un rango de IPs (p.ej. 192.168.0.1-192.168.0.99):

```bash
1..99 | ForEach-Object { Test-NetConnection 192.168.0.$_ } | Format-Table -AutoSize
```

## Rutas

Averiguar la ruta (IPs de los routers que atraviesan los paquetes de datos) para llegar a una máquina:

```bash
Test-NetConnection www.iesdomingoperezminik.es –TraceRoute
```

Consultar la tabla de ruteo para una interfaz determinada:

```bash
Get-NetRoute -InterfaceAlias Wi-Fi
```

Añadir una nueva ruta a la tabla de ruteo::

```bash
New-NetRoute –DestinationPrefix "10.0.0.0/24" –InterfaceAlias "Wi-Fi" –NextHop 192.168.192.1
```

> Los paquetes destinados a la red 10.0.0.0/24 se envían al router 192.168.192.1 a través de la interfaz "Wi-Fi"

## Escaneo de puertos

Comprueba si el puerto 80 (HTTP) está abierto en **www.iesdomingoperezminik.es**:

```bash
Test-NetConnection -ComputerName www.iesdomingoperezminik.es -Port 80
```

## Resolución de nombres

Averigua la IP asociada al nombre de dominio **www.iesdomingoperezminik.es**:

```bash
Resolve-DnsName www.iesdomingoperezminik.es
```

Averigua la entradas de tipo `MX` (Mail eXchange) asociadas al nombre de dominio **www.iesdomingoperezminik.es** en el servidor de DNS 8.8.8.8:

```bash
Resolve-DnsName google.com -Type MX -Server 8.8.8.8
```

> En este caso devuelve , al usar MX, devuelve los servidores de correo asociados al nombre de dominio google.com.

## Conexiones y puertos abiertos

Consultar las conexiones activas y los puertos abiertos:

```bash
Get-NetTCPConnection
```

Conexiones activas:

```bash
Get-NetTCPConnection –State Established
```

Puertos abiertos:

```bash
Get-NetTCPConnection –State Listen
```

Comprobar si el equipo tiene abierto el puerto 22 (SSH):

```bash
(Get-NetTCPConnection -State listen -LocalPort 22).Count -gt 0
```

## Referencias

- [Basic networking PowerShell  cmdlets cheatsheet to replace netsh, ipconfig, nslookup and more](https://www.thomasmaurer.ch/2016/02/basic-networking-powershell-cmdlets-cheatsheet-to-replace-netsh-ipconfig-nslookup-and-more/)

- [Set an IP address and configure DHCP with PowerShell](https://4sysops.com/archives/set-an-ip-address-and-configure-dhcp-with-powershell/)