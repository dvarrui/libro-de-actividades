#!/usr/bin/ruby

#
# $File: create-moodle-list.rb
#
# $Description:
#   A partir de un fichero CSV pasado por parámetro, con los datos
#   de los alumnos de informática (según lo genera el programa PinceEkade),
#   este script crea unos ficheros TXT por cada grupo para su carga en
#   Moodle.
#   Además cada fichero TXT servirá para que cada tutor comunique dichos
#   datos a sus alumnos de grupo.

=begin
Formato de entrada:
  username (Ńúmero de expediente)
  clave(dni)
  nombre
  apellidos
  email

Formato de salida:
  username, password, firstname, lastname, email, city
  juanb, secreto, Juan, Benítez, janb@algo.edu, DAW

=end
class ListPeople

	def initialize
		@outputfilename = 'usuarios_emyd'
		@change = [ ['Á','a'] , ['É','e'], ['Í','i'], ['Ó','o'], ['Ú','u'], ['Ñ','n'], ['  ',' '] ]
	end

	def create_list_for(arg='--help')
		if arg == '--help'
			show_help
		else
			process(arg)
		end
	end

	def process(filename)
		verbose "\n[INFO] Processing..."

		unless File.exists? filename
			puts "[ERROR] Filename #{filename} unknown!\n"
			exit 1
		end

		@data = File.open(filename,'r').readlines

		file = File.open("#{@outputfilename}.txt",'w')
		file.write("username;password;firstname;lastname;email;city\n")

		@data.each do |line|
			items = line.split(',')
			# items = line.force_encoding('iso-8859-1').split(',')
			username = items[0].downcase
			password = items[1]
			firstname = items[2].strip.upcase
			lastname = items[3].strip.upcase
			email = items[4]
			city = items[5]

			password = "123456" if password.size < 2
			sanitize!(firstname)
			sanitize!(lastname)
			if email.size < 2
				email = "exp#{username}@notienecorreo.com"
				sanitize!(email.downcase!)
			end
			city.gsub!("\n",'')

			file.write("#{username};#{password};#{firstname};#{lastname};#{email};#{city}\n")
		end

		file.close
	end

private

  def sanitize!(text)
		@change.each { |i| text.gsub!(i[0],i[1]) }
		text
	end

	def show_help
		puts "Uso:\n"
		puts " #{$0} FICHERO.csv"
		puts "\nFormato de entrada:"
    puts "  username, password, firstname, lastname, email, city"
		puts "\nFormato de salida:"
		puts "  username; password; firstname; lastname; email; city"
	end
end

i = ListPeople.new
i.create_list_for (ARGV.first)
