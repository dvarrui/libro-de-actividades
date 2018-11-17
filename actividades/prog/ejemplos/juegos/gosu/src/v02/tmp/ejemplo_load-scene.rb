#!/usr/bin/ruby

i=1
File.open("scene.txt").each do |line| 
	cadena=""
	if i<10 then
		cadena=" "
	end
	print cadena, i,": ",line
	i=i+1
end
