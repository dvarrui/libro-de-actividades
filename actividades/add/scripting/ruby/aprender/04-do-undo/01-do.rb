#!/usr/bin/env ruby
#
# Objetivo:
# * Crear directorios y establecer permisos

# do: crear directorios
system('mkdir private')
system('mkdir group')
system('mkdir public')

# do: establecer permisos
system('chmod 700 private')
system('chmod 750 group')
system('chmod 755 public')

# % ./01-do.rb                                                                  19-01-19 - 14:42:35
# % ./01-do.rb                                                                  19-01-19 - 14:42:35
# mkdir: no se puede crear el directorio «private»: El fichero ya existe
# mkdir: no se puede crear el directorio «group»: El fichero ya existe
# mkdir: no se puede crear el directorio «public»: El fichero ya existe
