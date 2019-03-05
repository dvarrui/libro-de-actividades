#!/usr/bin/ruby
# Objetivo: hacer ping a un grupo de hosts para ver si están activos.
# Argumentos:
#   --screen, -s => Mostrar datos en pantalla
#   --html,   -h => Genera la salida en un fichero html

option=ARGV[0]

def show_help
	puts "Modo de uso:"
	puts "--screen, -s Mostrar datos en la pantalla"
	puts "--html, -h   Mostrar datos en fichero html"
end

def ping_on_screen
	content=`cat /etc/myping/myping.conf`
	lines=content.split("\n")
	lines.each do |line|
		items=line.split(":")
		ip=items[0]
		nombre=items[1]

		flag = system("ping -c 1 #{ip} > /dev/null")
		if flag then
			puts "[ OK ] ip=#{ip} nombre=#{nombre}"
		else
			puts "[ERRO] ip=#{ip} nombre=#{nombre} ERR!"
		end
	end
end

def ping_on_html
	system("echo \"<h1>MyPing</h1>\" > /var/www/myping.html")
	content=`cat /etc/myping/myping.conf`
	lines=content.split("\n")
	lines.each do |line|
		items=line.split(":")
		ip=items[0]
		nombre=items[1]
		flag=`ping -c 1 #{ip} | grep Unreachable| wc -l`
		if flag=="0\n" then
			system("echo \"#{ip}<br>\" >> /var/www/myping.html")
		else
			system("echo \"#{ip} ERROR <br>\" >> /var/www/myping.html")
		end
	end
end

if option=='--screen' or option=='-s' then
	ping_on_screen
elsif option=='--html' or option=='-h' then
	ping_on_html
else
	show_help
end
