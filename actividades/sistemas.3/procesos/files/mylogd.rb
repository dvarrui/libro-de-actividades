#!/usr/bin/ruby
#
# Filename: /usr/local/bin/kupd
# Version: 1

ALUMNO="david"
MAQUINA=`hostname`
DIRCONF="/etc/kup"
LOGFILE=DIRCONF+"/kup.log"

system("touch #{DIRCONF}/running")
system("echo \"Iniciando el log...\" > #{LOGFILE}")

while File.exists?(DIRCONF+"/running") do
  message = "#{ALUMNO}@#{MAQUINA} #{$0}: "+Time.now.to_s
  system("echo \"#{message}\" >> #{LOGFILE}")
  sleep 1
end
