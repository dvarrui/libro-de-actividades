#!/usr/bin/env ruby

def ensure_we_are_not_root
  user_id = `id -u`.to_i

  if user_id == 0
    puts " ==> Debes ser usuario root para ejecutar el script"
    exit 1
  end
end

def create_users
  for number in 1..10 
    command = "useradd -p alejandro alejandro#{number}w"
    system command
  end
end

def delete_users
  for number in 1..10 
    command = "userdel alejandro#{number}w"
    system command
  end
end

# Begining
ensure_we_are_not_root
action = ARGV.first

if action == "-c"
  create_users
elsif action == "-d"
  delete_users
else
  puts " ==> Introduzca un parámetro válido: -c para crear usuarios o -d para eliminarlos"
end

exit 0

