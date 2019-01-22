#!/usr/bin/env ruby
#
# Objetivo
# * "Monitorizar" varios hosts
# * Sólo mostrar información necesaria
# * Dar color a la salida
# * Mantener un bucle constante de vigilancia

require 'rainbow'

ips = [ '127.0.0.1', '192.168.1.1', '8.8.4.4', '172.16.1.1']

def scan(ips)
  results = []
  ips.each_with_index do |ip, index|
    state = system("ping -c2 #{ip} > /dev/null")
    if state
      results << "ON  Host #{Rainbow(ip).bright}"
    else
      results << "#{Rainbow('OFF').red.bright} Host #{Rainbow(ip).bright}"
    end
  end
  return results
end

def show_results(results)
  system('clear')
  results.each_with_index do |line, index|
    puts "#{index} : #{line}"
  end
end

max = 1
max = ARGV[0].to_i if not ARGV.nil?

(1..max).each do |i|
  data = scan ips
  show_results data
  puts "[#{i}/#{max}] Iteración"
end
