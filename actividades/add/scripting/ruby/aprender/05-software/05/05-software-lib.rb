
def action(command)
  puts command
end

def echo(text)
  puts text if VERBOSE
end

def install(packages)
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
end
