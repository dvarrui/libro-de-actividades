#!/usr/bin/env ruby

print "Escribe tu edad:"
edad = gets.to_i

if edad > 17
  system("xcowsay 'Eres mayor de edad'")
else
  system("xcowsay 'Eres menor de edad'")
end

exit 0
