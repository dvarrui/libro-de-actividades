#!/usr/bin/env ruby

def buscar(id)
	lista = []
    	File::open("/etc/passwd").each do |linea|
		lista<<linea if linea.match(/#{id}/i)
        end
        lista.collect! do |datos|
		datos = datos.split(":")
		valor = datos[0], datos[4] #me quedo con lo que me interesa
	end
        if lista.length>1 #multiples matchs
        	lista.each_with_index do |valor,indice|
			puts "[#{indice}]−#{valor[1]}"
                end
                print "Elije uno: "
                until(0...lista.length)===(indice=STDIN.gets.to_i)
                	print "Elije uno: "
                end
                lista[indice]
        else
        	lista [0]
        end
end

if ARGV.length==1
	login , nombre=buscar(ARGV[0])
        if login or nombre
        	puts "Login  : #{login}"
                puts "Nombre : #{nombre}"
        else
        	puts "No he encontrado ese usuario"
	end
else
	puts "Numero erroneo de argumentos"
end


