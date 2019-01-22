#!/usr/bin/env ruby
#
# Objetivo:
# * Automatizar la configuración software de nuestra máquina
# * Por seguridad, por ahora, sólo mostraremos los comandos en pantalla.

packages = ['geany', 'tree', 'nmap']

def action(command)
  puts command
end

packages.each do |package|
  action("zypper install -y #{package}")
end
