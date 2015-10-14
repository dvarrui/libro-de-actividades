#!/usr/bin/ruby
# encoding: utf-8
# author: David Vargas
#


# sleep 3 && import -window root captura.jpg 
project_name='charla'
delay=10
end_at=30

start_time=Time.now
current_time=start_time
end_time=Time.now+end_at

while current_time<end_time do
  system("sleep #{delay}")
  filename=current_time.strftime("%Y%m%d-%H%M%S-#{project_name}.png") 
  system("import -window root #{filename}") 
  print "."
  current_time=Time.now
end

print "\n"
puts start_time
puts end_time

