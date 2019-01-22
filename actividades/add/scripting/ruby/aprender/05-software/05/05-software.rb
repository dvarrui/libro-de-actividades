#!/usr/bin/env ruby

require_relative '05-software-lib'

def show_help
  puts "Usage:"
  puts " -n , no verbose mode"
  puts " -v , verbose mode"
  exit
end

# Comprobar la entrada
if ARGV.empty?
  show_help
elsif ARGV[0]=='-v'
  VERBOSE = true
elsif ARGV[0]=='-n'
  VERBOSE = false
else
  show_help
end

# Instalar los paquetes
packages = ['geany', 'netstat', 'nmap']
install packages
