#!/usr/bin/ruby

puts "Ejecutando #{$0}..."
action = ARGV.first

# define global variables
USERS = [ 'profesor' ]
PROCESS = [ 'nano' ]

# define methods
def show_help
  puts "Modo de uso:"
  puts " help|h , Mostrar esta ayuda"
  puts " show|s , Mostrar las 'infracciones'"
  puts " exec|e , Castigar a los 'infractores'"
end

def show_guilty
  puts "Mostrar las 'infracciones'"
  USERS.each do |user|
  print "<#{user}> "
  output=`ps -u #{user} -o pid|grep -v 'PID'`
  pids = output.split("\n")
  if pids.size > 0 then
    print "Killing #{pids.count} processes:"
    pids.each { |p| print "#{p}, " }
  end
    print "\n"
  end
end

def exec_guilty
  puts "Castigar a los 'infractores'"
end

# Main block
if action=='help' or action=='h'
  show_help
elsif action=='show' or action=='s'
  show_guilty
elsif action=='exec' or action=='e'
  exec_guilty
else
  show_help
end
