#!/usr/bin/env ruby

user_id = `id -u`.to_i

if user_id == 0
  puts " ==> Debes ser usuario root para ejecutar el script"
  exit 1
end

action = ARGV.first

if action == "-c"
  system "useradd -p alejandro alejandro1w"
  system "useradd -p alejandro alejandro2w"
  system "useradd -p alejandro alejandro3w"
  system "useradd -p alejandro alejandro4w"
  system "useradd -p alejandro alejandro5w"
  system "useradd -p alejandro alejandro6w"
  system "useradd -p alejandro alejandro7w"
  system "useradd -p alejandro alejandro8w"
  system "useradd -p alejandro alejandro9w"
  system "useradd -p alejandro alejandro10w"
elsif action == "-d"
  system "userdel alejandro1w"
  system "userdel alejandro2w"
  system "userdel alejandro3w"
  system "userdel alejandro4w"
  system "userdel alejandro5w"
  system "userdel alejandro6w"
  system "userdel alejandro7w"
  system "userdel alejandro8w"
  system "userdel alejandro9w"
  system "userdel alejandro10w"
else
  puts " ==> Introduzca un parámetro válido: -c para crear usuarios o -d para eliminarlos"
end

exit 0

