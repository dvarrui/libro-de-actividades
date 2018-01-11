
# OLD: Examen práctico (SSH, VNC, Git)

Examen práctico

Vamos a necesitar 2 MV's que las descargaremos del servidor, según nos indique el profesor. A partir de ahí, las debemos ir personalizando y configurando según se especifica en este documento.

# MV1

La primera máquina virtual (MV1) será un Debian 7.

    Crear usuario: nombre-del-alumno
    Grupo principal del usuario: udremote
    Domainname: 1er-apellido-del-alumno
    ip: 172.16.109.1XX
    Clave de root: dni-del-alumno-con-letra-singuiones
    Hostname: debian
    Definir resolución de nombres local para la MV2.

# MV2

La segunda máquina virtual (MV2) será un OpenSUSE 13.

    Crear usuario: nombre-del-alumno
    Grupo principal del usuario: udremote
    Domainname: 1er-apellido-del-alumno
    ip: 172.16.109.2XX
    Clave de root: dni-del-alumno-con-letra-singuiones
    Hostname: opensuse
    Definir resolución de nombres local para la MV1.

# SSH

    Configurar las MV's para poderse acceder vía SSH con el usuario creado para cada alumno, sin poner la clave desde la MV1 a MV2.

# VNC

    Instalar servidor VNC "tightvncserver" en MV1.
    Activar servicio VNC en el desktop 1 de MV1.

# GIT

    Instalar GIT en MV1 y MV2.
    Clonar repo GitHub "profesor-david/add1314profesor" en el directorio home del usuario creado para el alumno de la MV2.
