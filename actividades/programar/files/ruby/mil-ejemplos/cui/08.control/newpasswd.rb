#!/usr/bin/ruby

opcion = 0
until opcion == 2
   puts " 1 − Introducir nuevo password "
   puts " 2 − Salir "

   opcion = 0
   until(1..2) === opcion
      print " Introduce opcion : "
      opcion = STDIN.gets.to_i
   end
 
   if opcion == 1
      print "Introduce nuevo password: "
      password = STDIN.gets.rstrip

      until password.match(/^.{8,15}$/)
         puts "El password ha de tener de 8 a 15 caracteres"
         print "Introduce nuevo password:"
         password = STDIN.gets.rstrip
      end

      puts " Password actualizado"
   end
end

