# whoami

`bash`

## Descripción

Muestra el de usuario que somos actualmente (identificador de usuario efectivo, que es diferente al usuario que inició sesión).

## Sintaxis

```bash
whoami [--help] [--version]
```

## Ejemplos

Muestra el usuario actual:

```bash
$ whoami
fvarrui
```

Muestra la versión del comando:

```bash
$ whoami --version
whoami (GNU coreutils) 8.28
Copyright (C) 2017 Free Software Foundation, Inc.
License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
This is free software: you are free to change and redistribute it.
There is NO WARRANTY, to the extent permitted by law.

Written by Richard Mlynarik.
```

Muestra la ayuda del comando:

```bash
$ whoami --help
Usage: whoami [OPTION]...
Print the user name associated with the current effective user ID.
Same as id -un.

      --help     display this help and exit
      --version  output version information and exit

GNU coreutils online help: <http://www.gnu.org/software/coreutils/>
Report whoami translation bugs to <http://translationproject.org/team/>
Full documentation at: <http://www.gnu.org/software/coreutils/whoami>
or available locally via: info '(coreutils) whoami invocation'
```



