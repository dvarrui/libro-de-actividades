#!/usr/bin/env ruby
#
# Objetivo:
# * Automatizar la configuración software de nuestra máquina
# * Por seguridad, por ahora, sólo mostraremos los comandos en pantalla.
# * Comprobar antes de instalar
# * Elegir verbosidad

VERBOSE = true
packages = ['geany', 'netstat', 'nmap']

def action(command)
  puts command
end

def echo(text)
  puts text if VERBOSE
end

packages.each do |package|
  echo "Package => #{package}"
  echo "├── [INFO] Searching package #{package}..."
  instalado = system("zypper info #{package} | grep Instalado > /dev/null")
  if instalado
    echo "└── [WARN] Package #{package} installed!"
  else
    echo "└── [INFO] Installing #{package}..."
    action("zypper install -y #{package}")
  end
end
