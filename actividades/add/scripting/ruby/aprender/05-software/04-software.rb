#!/usr/bin/env ruby
#
# Objetivo:
# * Automatizar la configuración software de nuestra máquina
# * Por seguridad, por ahora, sólo mostraremos los comandos en pantalla.
# * Comprobar antes de instalar
# * Elegir verbosidad

packages = ['geany', 'netstat', 'nmap']

def show_help
  puts "Usage:"
  puts " -n , no verbose mode"
  puts " -v , verbose mode"
  exit
end

# Comprobar la entrada
if ARGV.empty?
  show_help
elsif ARGV[0]=='-v'
  VERBOSE = true
elsif ARGV[0]=='-n'
  VERBOSE = false
else
  show_help
end

# Definir métodos
def action(command)
  puts command
end

def echo(text)
  puts text if VERBOSE
end

# Instalar los paquetes
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
