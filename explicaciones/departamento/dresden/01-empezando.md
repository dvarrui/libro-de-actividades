
# Crear usuarios

Update local /etc/hosts
ssh remote servidor2
adduser david
ssh-copy-id -i .ssh/id_rsa.pub david@servidor2
Copiar alias personales .alias -> .bash_aliases
Copiar alias al usuario root

# Personalizar servidor2

Rename server to dresden
/etc/hostname
/etc/hosts
hostname dresden

# Actualizar el sistema

apt-get update
apt.get upgrade

Mensaje para revisar
```
LANGUAGE = (unset),
LC_ALL = (unset),
LC_CTYPE = "es_ES.utf8",
LANG = "en_US.UTF-8" are supported and installed on your system.
perl: warning: Falling back to a fallback locale ("en_US.UTF-8").
locale: Cannot set LC_CTYPE to default locale: No such file or directory
locale: Cannot s
```

# Fix problems with locales

```console
$> locale-gen es_ES.UTF-8
$> dpkg-reconfigure locales
```


# Desactivar acceso SSH de Root
PermitRootLogin No
/etc/ssh/sshd_config

# otros paquetes
apt-get install htop tree
