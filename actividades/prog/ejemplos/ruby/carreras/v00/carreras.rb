#!/usr/bin/ruby
APPS="carreras"
REV="0"
AUTOR="David Vargas"

var = {}
NUMCOCHES=3

def mostrar_presentacion
	system("clear")
	puts "JUEGO DE CARRERAS"
	puts "Aplicación="+APPS+", Versión="+REV+", Autor="+AUTOR
end

def inicializar_variables(v)
	#Inicializar variables
	coches = Array.new
	NUMCOCHES.times { |i| coches << 0 }
	v[:coches]=coches
	v[:carroceria1]="=("
	v[:carroceria2]=")>"
	v[:fin]=false
	v[:ganador]=-1
end

def pintar(v)
	puts " "
	coches = v[:coches]
	coches.each_with_index do |c,i|
		c.times { print " "}
		print v[:carroceria1], i.to_s, v[:carroceria2], "\n"
	end
end

def mover(v)
	coches = v[:coches]
	coches[0]=+1
	coches[1]=+2
end

def fin?(v)
	coches=v[:coches]
end

def jugar(v)
end

mostrar_presentacion
inicializar_variables var
pintar var
jugar var
