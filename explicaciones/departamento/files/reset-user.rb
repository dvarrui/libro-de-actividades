#!/usr/bin/ruby
# encoding: utf-8

puts "[INFO] Executing <#{$0}>..."

def reset_alumno
  puts "* Setting password"
  system('echo "alumno:tecnologia" | chpasswd')

  puts "* Remove home files"
  system('rm -rf /home/alumno')

  puts "* Restarting home files"
  system('tar xvf alumno.tar')
  system('mv alumno ..')
end

FILE="/home/guest/last_execution.dat"
last_execution=(`cat #{FILE}`).to_i
now=Time.now
today=now.year*10000+now.yday

if today>last_execution
  system("echo #{today.to_s} > #{FILE}")
  reset_alumno
  puts "[INFO] OK!"
else
  puts "[INFO] Nothing done!"
end


