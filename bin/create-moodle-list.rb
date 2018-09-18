#!/usr/bin/ruby
# encoding: utf-8

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
  grupo
  clave(dni)
  nombre
  apellido1
  apellido2
  email

Formato de salida:
  username, password, firstname, lastname, email, city, country
  juanb, secreto, Juan, Benítez, janb@algo.edu, DAW, Spain

=end
class ListPeople

	def initialize
		@debug=false
		@verbose=true
		@outputfilename='usuarios'
		@output={}

		@change=[ ['á','a'], ['é','e'], ['í','i'], ['ó','o'], ['ú','u'], ['Á','a'] , ['É','e'], ['Í','i'], ['Ó','o'], ['Ú','u'], ['Ñ','n'], ['ñ','n'], [' ',''] ]
	end

	def create_list_for(pArg)
		if pArg=='--help' then
			show_help
		else
			@filename=pArg
			process
		end
	end

	def process
		verbose "\n[INFO] Processing..."

		if !File.exists? @filename then
			puts "[ERROR] <#{@filename}> dosn't exist!\n"
			raise "[ERROR] <#{@filename}> dosn't exist!\n"
		end

		@file=File.open(@filename,'r')
		@data=@file.readlines

		@data.each do |line|
			items=line.split(",")
			#items=line.force_encoding("iso-8859-1").split(",")
			grupo=items[0].downcase
			dni=items[1]
			nombre=items[2]
			apellido1=items[3]
			apellido2=items[4]
			apellidos=apellido1+" "+apellido2
			email=items[5]

			#username
			u=nombre[0..2]+apellido1.gsub(' ','')[0..2]
			u=u+(apellido2.gsub(' ','')[0..2]||apellido1.gsub(' ','')[0..2])
			username=u.downcase
			sanitize!(username)
			#@change.each { |i| username.gsub!(i[0],i[1]) }

      email.gsub!("\n",'')
			if email.size<2
				email="#{nombre}.#{apellido1}#{apellido2}@iespuertodelacruz.es"
				sanitize!(email.downcase!)
			end
			dni="123456" if dni.size<2

			if @output[grupo.to_sym].nil? then
				f=File.open("#{@outputfilename}_#{grupo}.txt",'w')
				@output[grupo.to_sym]=f
				#f.write("username;password;firstname;lastname;email;city;country\n")
				f.write("username;password;firstname;lastname;email;city\n")
			end
			#username, password, firstname, lastname, email, city, country
			verbose("#{username};#{dni};#{nombre};#{apellidos};#{email};#{grupo}")
			@output[grupo.to_sym].write("#{username};#{dni};#{nombre};#{apellidos};#{email};#{grupo}\n")
		end

		@file.close
		@output.each_value { |i| i.close }
	end

private

	def execute_command(lsCommand)
		verbose "(*) Executing: #{lsCommand}"
		system(lsCommand) if !@debug
	end

  def sanitize!(text)
		@change.each { |i| text.gsub!(i[0],i[1]) }
		text
	end

	def show_help
		puts "Uso:\n"
		puts " #{$0} FICHERO.csv"
		puts "\nFormato de entrada:"
    puts "  grupo, clave(dni), nombre, apellido1, apellido2, email"
		puts "\nFormato de salida:"
		puts "  username; password; firstname; lastname; email; city"
    puts "  manrodper; clave; Manuel; Rodríguez Pérez; manrodper@iespuertodelacruz.es; DAW"
	end

	def verbose(lsText)
		if @verbose then
			puts lsText
		end
	end
end

i = ListPeople.new
i.create_list_for (ARGV.first||'--help')
