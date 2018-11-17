#!/usr/bin/ruby

header="123456789|"
header=header*4
header="    "+header
print header,"\n"

i=1
File.open("scene.txt").each do |line| 
	cadena=""
	if i<10 then
		cadena=" "
	end
	print cadena, i,": ",line
	i=i+1
end
