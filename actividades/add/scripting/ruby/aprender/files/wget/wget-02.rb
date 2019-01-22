#!/usr/bin/ruby
require 'open-uri'

localfile="miarchivo.xml"
remotefile='http://resultados.elpais.com/elecciones/2011/municipales/05/38/28.xml2'

begin
	puts "Descargando XML..."
	File.open(localfile,"w") do |saved_file|
		open(remotefile,'r') do |read_file|
			saved_file.write(read_file.read)
		end
	end
rescue Errno::ENOENT
	puts "Error ENOENT {"+remotefile+"}"
end
