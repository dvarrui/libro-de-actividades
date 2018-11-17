#!/usr/bin/ruby
require 'open-uri'

fileweb='http://resultados.elpais.com/elecciones/2011/municipales/05/38/28.xml2'

puts open(fileweb).read()
