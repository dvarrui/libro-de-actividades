#!/usr/bin/env ruby
#
# Objetivo:
# * Automatizar la configuración software de nuestra máquina
# * Por seguridad, por ahora, sólo mostraremos los comandos en pantalla.
# * Comprobar antes de instalar

packages = ['geany', 'netstat', 'nmap']

def action(command)
  puts command
end

packages.each do |package|
  puts "Package => #{package}"
  puts "├── [INFO] Searching package #{package}..."
  instalado = system("zypper info #{package} | grep Instalado > /dev/null")
  if instalado
    puts "└── [WARN] Package #{package} installed!"
  else
    puts "└── [INFO] Installing #{package}..."
    action("zypper install -y #{package}")
  end
end
