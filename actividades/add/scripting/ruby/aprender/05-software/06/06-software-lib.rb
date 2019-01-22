
require 'rainbow'

def action(command)
  puts command
end

def echo(text)
  puts text if VERBOSE
end

def install(packages)
  packages.each do |package|
    echo "\n"+Rainbow(package).bright
    echo "├── [INFO] Searching package #{package}..."
    instalado = system("zypper info #{package} | grep Instalado > /dev/null")
    if instalado
      echo "└── ["+Rainbow('WARN').yellow.bright+"] Package #{package} installed!"
    else
      echo "└── ["+Rainbow(' OK ').green.bright+"] Installing #{package}..."
      action("zypper install -y #{package}")
    end
  end
end
